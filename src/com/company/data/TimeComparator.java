package com.company.data;

import com.company.domain.CompetitiveResult;

import java.util.Comparator;

public class TimeComparator implements Comparator<CompetitiveResult> {

    private String discipline;

    @Override
    public int compare(CompetitiveResult time1, CompetitiveResult time2) {
        int result = Double.compare(time1.getTime(), time2.getTime());

        return result;
    }
}
