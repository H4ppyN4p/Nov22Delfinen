package com.company.domain;

import com.company.domain.CompetitiveMember;
import com.company.domain.Member;

import java.util.ArrayList;

public class Subscription {

    private double juniorSubscriptionPrice = 1000;
    private double adultSubscriptionPrice = 1600;
    private double seniorSubscriptionPrice = adultSubscriptionPrice * 0.75;
    private double passiveSubscriptionPrice = 500;

    public double expectedIncome(ArrayList<Member> listOfAllMembers, ArrayList<CompetitiveMember> listOfAllCompetetiveMembers) {
        int juniorMembers = 0;
        int adultMembers = 0;
        int seniorMembers = 0;
        int passiveMembers = 0;

        ArrayList<Member> members = listOfAllMembers;
        ArrayList<CompetitiveMember> competitiveMembers = listOfAllCompetetiveMembers;

        for (int i = 0; i < members.size(); i++){
            if (members.get(i).getSubscriptonType().equals("youth")){
                juniorMembers++;
            } else if (members.get(i).getSubscriptonType().equals("adult")){
                adultMembers++;
            } else if (members.get(i).getSubscriptonType().equals("senior")){
                seniorMembers++;
            } else if (members.get(i).getSubscriptonType().equals("passive")) {
                passiveMembers++;
            }
        }

        for (int i = 0; i < competitiveMembers.size(); i++){
            if (competitiveMembers.get(i).getSubscriptonType().equals("youth")){
                juniorMembers++;
            } else if (competitiveMembers.get(i).getSubscriptonType().equals("adult")){
                adultMembers++;
            } else if (competitiveMembers.get(i).getSubscriptonType().equals("senior")){
                seniorMembers++;
            } else if (competitiveMembers.get(i).getSubscriptonType().equals("passive")) {
                passiveMembers++;
            }
        }
        return (juniorMembers * juniorSubscriptionPrice) + (seniorMembers * seniorSubscriptionPrice)
                + (adultMembers * adultSubscriptionPrice) + (passiveMembers * passiveSubscriptionPrice);
    }
}