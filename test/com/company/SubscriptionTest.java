package com.company;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionTest {

    @Test
    void expectedIncome() {
        // Arrange
        ArrayList<Member> mb = new ArrayList<>();
        ArrayList<CompetitiveMember> cmb = new ArrayList<>();
        Member member = new Member("Jens", "28", "adult");
        CompetitiveMember competMember = new CompetitiveMember("Bob", "80", "senior", false, false, false, false,
                "0", "0", "0", "0");

        mb.add(member);
        cmb.add(competMember);
        
        double juniorSubscriptionPrice = 1000;
        double adultSubscriptionPrice = 1600;
        double seniorSubscriptionPrice = adultSubscriptionPrice * 0.75;
        double passiveSubscriptionPrice = 500;

        int juniorMembers = 0;
        int adultMembers = 0;
        int seniorMembers = 0;
        int passiveMembers = 0;

        for (int i = 0; i < mb.size(); i++){
            if (mb.get(i).getSubscriptonType().equals("youth")){
                juniorMembers++;
            } else if (mb.get(i).getSubscriptonType().equals("adult")){
                adultMembers++;
            } else if (mb.get(i).getSubscriptonType().equals("senior")){
                seniorMembers++;
            } else if (mb.get(i).getSubscriptonType().equals("passive")) {
                passiveMembers++;
            }
        }

        for (int i = 0; i < cmb.size(); i++){
            if (cmb.get(i).getSubscriptonType().equals("youth")){
                juniorMembers++;
            } else if (cmb.get(i).getSubscriptonType().equals("adult")){
                adultMembers++;
            } else if (cmb.get(i).getSubscriptonType().equals("senior")){
                seniorMembers++;
            } else if (cmb.get(i).getSubscriptonType().equals("passive")) {
                passiveMembers++;
            }
        }

        // Act
        double expected = (juniorMembers * juniorSubscriptionPrice) + (seniorMembers * seniorSubscriptionPrice)
                + (adultMembers * adultSubscriptionPrice) + (passiveMembers * passiveSubscriptionPrice);

        double result = adultSubscriptionPrice + seniorSubscriptionPrice;

        // Assert
        assertEquals(expected, result);
    }
}