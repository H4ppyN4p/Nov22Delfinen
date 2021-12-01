package com.company.domain;

import com.company.domain.AgeGroup;

public class Member {

    //Personal info
    private String memberName;
    private String memberAge;

    //Svømme info
    private String subscriptionType;
    private AgeGroup ageGroup;

    public Member(String memberName, String memberAge, String subscriptionType) {
        this.memberName = memberName;
        this.subscriptionType = subscriptionType;

        if (Integer.parseInt(memberAge) >= 18) {
            this.ageGroup = AgeGroup.SENIOR;
            this.memberAge = memberAge;
        } else {
            this.ageGroup = AgeGroup.JUNIOR;
            this.memberAge = memberAge;
        }
    }

    public AgeGroup getAgeGroup(){
        return this.ageGroup;
    }

    public int getAge(){
        return Integer.parseInt(memberAge);
    }

    public String getSubscriptonType(){
        return subscriptionType;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberAge(String memberAge) {
        this.memberAge = memberAge;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    @Override
    public String toString() {
        return memberName + ";" + memberAge + ";" + subscriptionType + ";" + ageGroup;
    }
}