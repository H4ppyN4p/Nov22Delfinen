package com.company;

public class CompetitiveMember extends Member {

    private CompetitiveResult competitiveResults;

    private boolean isCrawlSwimmer;
    private boolean isButterflySwimmer;
    private boolean isBackStrokeSwimmer;
    private boolean isBreastStrokeSwimmer;

    private double bestLapCrawl = 0;
    private double bestLapButterfly = 0;
    private double bestLapBackCrawl = 0;
    private double bestLapBreast = 0;

    private boolean isMemberOfTeam;


    public CompetitiveMember(String memberName, String memberAge, String subscriptionType,
                             boolean isCrawlSwimmer, boolean isButterflySwimmer,
                             boolean isBackStrokeSwimmer, boolean isBreastStrokeSwimmer) {
        super(memberName, memberAge, subscriptionType);
        this.isCrawlSwimmer = isCrawlSwimmer;
        this.isButterflySwimmer = isButterflySwimmer;
        this.isBackStrokeSwimmer = isBackStrokeSwimmer;
        this.isBreastStrokeSwimmer = isBreastStrokeSwimmer;

    }

    public CompetitiveMember(String memberName, String memberAge, String subscriptionType,
                             boolean isCrawlSwimmer, boolean isButterflySwimmer,
                             boolean isBackStrokeSwimmer, boolean isBreastStrokeSwimmer,
                             double bestLapBackCrawl, double bestLapButterfly,
                             double bestLapBreast, double bestLapCrawl) {
        super(memberName, memberAge, subscriptionType);
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
                isBreastStrokeSwimmer;
    }
}