package com.company.domain;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class CompetitiveMember extends Member {

    private String competitiveNumber;
    private boolean isCrawlSwimmer;
    private boolean isButterflySwimmer;
    private boolean isBackStrokeSwimmer;
    private boolean isBreastStrokeSwimmer;
    private Random compNumberGenerator = new Random();

    private ArrayList<CompetitiveResult> competitions = new ArrayList<>();

    public CompetitiveMember(String memberName, String memberAge, String subscriptionType, boolean hasPaidSubscription,
                             boolean isCrawlSwimmer, boolean isButterflySwimmer,
                             boolean isBackStrokeSwimmer, boolean isBreastStrokeSwimmer) {
        super(memberName, memberAge, subscriptionType, hasPaidSubscription);
        this.isCrawlSwimmer = isCrawlSwimmer;
        this.isButterflySwimmer = isButterflySwimmer;
        this.isBackStrokeSwimmer = isBackStrokeSwimmer;
        this.isBreastStrokeSwimmer = isBreastStrokeSwimmer;

        if (competitiveNumber == null) {
            competitiveNumber = memberName.substring(0, 2).toLowerCase() +
                    compNumberGenerator.nextInt(10) + compNumberGenerator.nextInt(10);
        }

    }

    public CompetitiveMember(String competitiveNumber, String memberName, String memberAge, String subscriptionType,
                             boolean hasPaidSubscription,
                             boolean isCrawlSwimmer, boolean isButterflySwimmer,
                             boolean isBackStrokeSwimmer, boolean isBreastStrokeSwimmer) {
        super(memberName, memberAge, subscriptionType, hasPaidSubscription);
        this.isCrawlSwimmer = isCrawlSwimmer;
        this.isButterflySwimmer = isButterflySwimmer;
        this.isBackStrokeSwimmer = isBackStrokeSwimmer;
        this.isBreastStrokeSwimmer = isBreastStrokeSwimmer;
        this.competitiveNumber = competitiveNumber;


    }

    public CompetitiveMember(String memberName, String memberAge, String subscriptionType, boolean hasPaidSubscription,
                             boolean isCrawlSwimmer, boolean isButterflySwimmer,
                             boolean isBackStrokeSwimmer, boolean isBreastStrokeSwimmer,
                             String compName, String compPlace, double swimTime, String swimCategory, String compDate) {
        super(memberName, memberAge, subscriptionType, hasPaidSubscription);
        this.isCrawlSwimmer = isCrawlSwimmer;
        this.isButterflySwimmer = isButterflySwimmer;
        this.isBackStrokeSwimmer = isBackStrokeSwimmer;
        this.isBreastStrokeSwimmer = isBreastStrokeSwimmer;

        if (competitiveNumber == null) {
            competitiveNumber = memberName.substring(0, 2).toLowerCase() +
                    compNumberGenerator.nextInt(10) + compNumberGenerator.nextInt(10);
        }

        addCompetition(new CompetitiveResult(compName, compPlace, swimTime, swimCategory, compDate));

    }

    public void addCompetition(CompetitiveResult competition) {
        competitions.add(competition);
    }

    public ArrayList<CompetitiveResult> getCompetitiveResults() {
        return competitions;
    }

    public ArrayList<CompetitiveResult> getCompetitions() {
        return competitions;
    }

    public String getCompetitiveNumber() {
        return competitiveNumber;
    }

    public ArrayList<String> getDisciplineStatuses() {
        ArrayList<String> disciplineStatuses = new ArrayList<>();

        if (isCrawlSwimmer) {
            disciplineStatuses.add("yes");
        } else {
            disciplineStatuses.add("no");
        }

        if (isButterflySwimmer) {
            disciplineStatuses.add("yes");
        } else {
            disciplineStatuses.add("no");
        }

        if (isBackStrokeSwimmer) {
            disciplineStatuses.add("yes");
        } else {
            disciplineStatuses.add("no");
        }

        if (isBreastStrokeSwimmer) {
            disciplineStatuses.add("yes");
        } else {
            disciplineStatuses.add("no");
        }

        return disciplineStatuses;
    }


    @Override
    public String toString() {
        return competitiveNumber + ";" + super.toString() + ";" + isCrawlSwimmer + ";" + isButterflySwimmer
                + ";" + isBackStrokeSwimmer + ";" + isBreastStrokeSwimmer;
    }

    public String toStringUI() {
        return "Competitive number: " + competitiveNumber + "  " + super.toStringUI() + "  Crawl: "
                + getDisciplineStatuses().get(0) + "  Butterfly: " + getDisciplineStatuses().get(1) + "  Back stroke: "
                + getDisciplineStatuses().get(2) + "  Breast Stroke: " + getDisciplineStatuses().get(3);
    }


}
