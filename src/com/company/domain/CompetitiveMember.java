package com.company.domain;

public class CompetitiveMember extends Member {

    private CompetitiveResult competitiveResults;

    private boolean isCrawlSwimmer;
    private boolean isButterflySwimmer;
    private boolean isBackStrokeSwimmer;
    private boolean isBreastStrokeSwimmer;

    private String bestLapCrawl = "0";
    private String bestLapButterfly = "0";
    private String bestLapBackCrawl = "0";
    private String bestLapBreast = "0";

    private boolean isMemberOfTeam;


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
                             String bestLapBackCrawl, String bestLapButterfly,
                             String bestLapBreast, String bestLapCrawl) {
        super(memberName, memberAge, subscriptionType, hasPaidSubscription);
        this.isCrawlSwimmer = isCrawlSwimmer;
        this.isButterflySwimmer = isButterflySwimmer;
        this.isBackStrokeSwimmer = isBackStrokeSwimmer;
        this.isBreastStrokeSwimmer = isBreastStrokeSwimmer;

        this.bestLapCrawl = bestLapCrawl;
        this.bestLapButterfly = bestLapButterfly;
        this.bestLapBackCrawl = bestLapBackCrawl;
        this.bestLapBreast = bestLapBreast;

    }

    @Override
    public String toString() {
        return super.toString() + ";" + isCrawlSwimmer + ";" + isButterflySwimmer + ";" + isBackStrokeSwimmer + ";" +
                isBreastStrokeSwimmer + ";" + bestLapCrawl + ";" + bestLapButterfly + ";" + bestLapBackCrawl  + ";" + bestLapBreast;
    }
}