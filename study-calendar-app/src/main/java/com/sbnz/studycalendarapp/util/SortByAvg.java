package com.sbnz.studycalendarapp.util;

import java.util.Comparator;

import com.sbnz.studycalendarapp.model.Obligation;

public class SortByAvg implements Comparator<Obligation> {
	 
    public int compare(Obligation a, Obligation b)
    {
        return  (int) (a.getStudyHours()/ (a.getStudyStartDate().datesUntil(a.getStudyEndDate())).count()
        		- b.getStudyHours()/ (b.getStudyStartDate().datesUntil(b.getStudyEndDate())).count());
    }
}