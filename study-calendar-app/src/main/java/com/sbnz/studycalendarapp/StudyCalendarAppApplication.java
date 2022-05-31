package com.sbnz.studycalendarapp;


import javax.activation.DataSource;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@SpringBootApplication
public class StudyCalendarAppApplication{

	public static void main(String[] args) {
		SpringApplication.run(StudyCalendarAppApplication.class, args);
		
		
	}
	
	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.sbnz", "study-calendar-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		return kContainer;
	}

	@Bean
	public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final javax.sql.DataSource dataSource) {
	    ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
	    resourceDatabasePopulator.addScript(new ClassPathResource("/data.sql"));
	    DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
	    dataSourceInitializer.setDataSource(dataSource);
	    dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
	    return dataSourceInitializer;
	}

}
