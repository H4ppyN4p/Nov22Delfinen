package com.company.domain;

public class CompetitiveResult{
    private String competitionName;
    private String competitionLocation;
    private double time;
    private String discipline;
    private String date;

    public CompetitiveResult(String competitionName, String competitionLocation, double time, String discipline, String date){
        this.competitionName = competitionName;
        this.competitionLocation = competitionLocation;
        this.time = time;
        this.discipline = discipline;
        this.date = date;
    }

    public double getTime() {
        return time;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getDate() {
        return date;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getCompetitionLocation() {
        return competitionLocation;
    }

    public void setCompetitionLocation(String competitionLocation) {
        this.competitionLocation = competitionLocation;
    }


    @Override
    public String toString() {
        return competitionName + ";" + competitionLocation + ";" + time +
                ";" + discipline + ";" + date;
    }
}