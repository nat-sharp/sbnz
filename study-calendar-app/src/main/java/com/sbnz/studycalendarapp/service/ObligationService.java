package com.sbnz.studycalendarapp.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.studycalendarapp.model.Obligation;
import com.sbnz.studycalendarapp.model.Student;
import com.sbnz.studycalendarapp.model.StudyCalendar;
import com.sbnz.studycalendarapp.model.StudySession;
import com.sbnz.studycalendarapp.model.Subject;
import com.sbnz.studycalendarapp.repository.ObligationRepository;
import com.sbnz.studycalendarapp.repository.StudyCalendarRepository;
import com.sbnz.studycalendarapp.repository.StudySessionRepository;

@Service
public class ObligationService {

	@Autowired
	private ObligationRepository repository;
	
	@Autowired
	private StudySessionRepository studySessionRepository;
	
	@Autowired
	private StudyCalendarRepository studyCalendarRepository;
	
	private static Logger log = LoggerFactory.getLogger(ObligationService.class);

	private final KieContainer kieContainer;
	
	@Autowired
	public ObligationService(KieContainer kieContainer) {
		log.info("Initialising session for obligations.");
		this.kieContainer = kieContainer;
	}
	
	public List<Obligation> registerObligations(List<Obligation> obligations) {
		
		List<Obligation> saved = new ArrayList<>();
		for(Obligation o : obligations) {
			saved.add(repository.save(o));
		}
		
		Student student = saved.get(0).getSubject().getStudent();
		
		StudyCalendar calendar = new StudyCalendar();
		calendar.setObligations(saved);
		calendar.setStudent(student);
		
		//////////////////////////////////////////////////////////////////////////
		
		KieSession kieSession = kieContainer.newKieSession();
		
		kieSession.insert(calendar);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		////////////////////////////////////////////////////////////////////////		
		
		for(StudySession session : calendar.getSessions()) {
			studySessionRepository.save(session);
		}
		
		studyCalendarRepository.save(calendar);
		
		return obligations;
	}

	public Obligation findOneById(Integer id) {
		return repository.findOneById(id);
	}
	
	public Obligation save(Obligation obligation) {
		return repository.save(obligation);
	}
	
	public Obligation finishObligation(Obligation obligation) {
		if (obligation.getEarnedPoints() > obligation.getMaxPoints() * 0.5 && !obligation.isSkipped()) {
			obligation.setPassed(true);
		} else {
			obligation.setPassed(false);
		}
		
		setObligationFields(obligation);
		
		KieSession kieSession = kieContainer.newKieSession("obligations");
		
		kieSession.insert(obligation);
		Subject subject = obligation.getSubject();
		kieSession.insert(subject);
		Student student = subject.getStudent();
		kieSession.insert(student);
		kieSession.setGlobal("$finishedObligations", new ArrayList<Obligation>());
		kieSession.fireAllRules();
		kieSession.dispose();
		
		calculateStudentActivity(student);
		
		return save(obligation);
	}
	
	private void setObligationFields(Obligation obligation) {
		InputStream template = ObligationService.class.getResourceAsStream("/com/sbnz/templates/finish-obligation.drt");
        
        DataProvider dataProvider = new ArrayDataProvider(new String[][]{
            new String[]{"false", "true", "true", "true", "$o.getEarnedPoints()", "$o.getDateAndTime()"}, //1
            new String[]{"false", "true", "false", "true", "$o.getEarnedPoints()", "$o.getDateAndTime()"}, //1
            new String[]{"false", "false", "true", "false", "0.0", "null"}, //2
            new String[]{"false", "false", "false", "true", "0.0", "$o.getDateAndTime()"}, //3
            new String[]{"true", "true", "true", "false", "0.0", "null"}, //4
            new String[]{"true", "false", "true", "false", "0.0", "null"}, //4
            new String[]{"true", "true", "false", "true", "0.0", "$o.getDateAndTime()"}, //5
            new String[]{"true", "false", "false", "true", "0.0", "$o.getDateAndTime()"}, //5
        });
        
        DataProviderCompiler converter = new DataProviderCompiler();
        String drl = converter.compile(dataProvider, template);
        
        KieSession kieSession = createKieSessionFromDRL(drl);
        kieSession.insert(obligation);
        kieSession.fireAllRules();
        kieSession.dispose();
	}
	
	private void calculateStudentActivity(Student student) {
		InputStream template = ObligationService.class.getResourceAsStream("/com/sbnz/templates/student-activity.drt");
        
        DataProvider dataProvider = new ArrayDataProvider(new String[][]{
            new String[]{"-100000", "100", "BEGGINER"},
            new String[]{"100", "1000", "HARD_WORKING"},
            new String[]{"1000", "100000", "PROFESSIONAL"}
        });
        
        DataProviderCompiler converter = new DataProviderCompiler();
        String drl = converter.compile(dataProvider, template);
        
        KieSession kieSession = createKieSessionFromDRL(drl);
        kieSession.insert(student);
        kieSession.fireAllRules();
        kieSession.dispose();
	}
	
	private KieSession createKieSessionFromDRL(String drl){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        
        Results results = kieHelper.verify();
        
        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }
            
            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }
        
        return kieHelper.build().newKieSession();
    }
	
	public List<Obligation> findAllBySubject(Subject subject) {
		return repository.findAllBySubject(subject);
	}
}
