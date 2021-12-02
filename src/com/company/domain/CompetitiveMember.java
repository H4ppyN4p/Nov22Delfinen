package com.company.domain;

import java.util.ArrayList;

public class CompetitiveMember extends Member {

    private CompetitiveResult competitiveResults;
    private boolean isCrawlSwimmer;
    private boolean isButterflySwimmer;
    private boolean isBackStrokeSwimmer;
    private boolean isBreastStrokeSwimmer;

    private ArrayList<CompetitiveResult> competitions = new ArrayList<>();

    public CompetitiveMember(String memberName, String memberAge, String subscriptionType, boolean hasPaidSubscription,
                             boolean isCrawlSwimmer, boolean isButterflySwimmer,
                             boolean isBackStrokeSwimmer, boolean isBreastStrokeSwimmer) {
        super(memberName, memberAge, subscriptionType, hasPaidSubscription);
        this.isCrawlSwimmer = isCrawlSwimmer;
        this.isButterflySwimmer = isButterflySwimmer;
        this.isBackStrokeSwimmer = isBackStrokeSwimmer;
        this.isBreastStrokeSwimmer = isBreastStrokeSwimmer;

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

        addCompetition(new CompetitiveResult(compName, compPlace, swimTime, swimCategory, compDate));

    }

    public void addCompetition(CompetitiveResult competition) {
        competitions.add(competition);
    }

    public CompetitiveResult getCompetitiveResults() {
        return competitions.get(0);
    }

    public ArrayList<CompetitiveResult> getCompetitions() {
        return competitions;
    }


    @Override
    public String toString() {
        return super.toString() + ";" + isCrawlSwimmer + ";" + isButterflySwimmer + ";" + isBackStrokeSwimmer + ";" +
                isBreastStrokeSwimmer;
    }


}
