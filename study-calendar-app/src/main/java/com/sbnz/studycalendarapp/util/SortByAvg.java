package com.sbnz.studycalendarapp.util;

import java.util.Comparator;

import com.sbnz.studycalendarapp.model.Obligation;

public class SortByAvg implements Comparator<Obligation> {
	 
    public int compare(Obligation a, Obligation b)
    {
        return  (int) (a.getStudyHours()/ (a.getStudyStartDate().datesUntil(a.getStudyEndDate().plusDays(1))).count()
        		- b.getStudyHours()/ (b.getStudyStartDate().datesUntil(b.getStudyEndDate().plusDays(1))).count());
    }
}