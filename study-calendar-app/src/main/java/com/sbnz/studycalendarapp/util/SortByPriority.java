package com.sbnz.studycalendarapp.util;

import java.util.Comparator;

import com.sbnz.studycalendarapp.model.StudySession;

public class SortByPriority implements Comparator<StudySession> {
	 
    public int compare(StudySession a, StudySession b)
    {
 
        return  (int) (a.getObligation().getMaxPoints() - b.getObligation().getMaxPoints());
    }
}