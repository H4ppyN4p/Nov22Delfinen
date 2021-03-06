// Authors: Arboe & Luay
package com.company.data;

import com.company.domain.Team;
import com.company.domain.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class MemberDatabase {


    private ArrayList<Member> members;
    private ArrayList<CompetitiveMember> competitiveMembers;
    private ArrayList<CompetitiveResult> competitiveResults;
    private Team juniorTeam = new Team(AgeGroup.JUNIOR);
    private Team seniorTeam = new Team(AgeGroup.SENIOR);
    private ArrayList<Subscription> subscriptions = new ArrayList<>();
    private FileHandler fh = new FileHandler();
    private TimeComparator timeComparator = new TimeComparator();

    public MemberDatabase() throws FileNotFoundException {
        members = fh.readFromFile();
        competitiveMembers = fh.readFromFileCompMembers();
        competitiveResults = fh.readFromFileResults();
        juniorTeam.addMemberToTeam(competitiveMembers);
        seniorTeam.addMemberToTeam(competitiveMembers);
        joinResultWithMember();
    }


    public void createMember(ArrayList<String> info) throws FileNotFoundException {
        ArrayList<String> memberInfo = info;
        if (memberInfo.get(3).equals("n")) {
            Member member = new Member(memberInfo.get(0), memberInfo.get(1), memberInfo.get(2));
            members.add(member);
            fh.writeToFile(member);


        } else {
            CompetitiveMember competitiveMember = new CompetitiveMember(memberInfo.get(0),
                    memberInfo.get(1), memberInfo.get(2), true, Boolean.parseBoolean(memberInfo.get(4)),
                    Boolean.parseBoolean(memberInfo.get(5)), Boolean.parseBoolean(memberInfo.get(6)),
                    Boolean.parseBoolean(memberInfo.get(7)));
            competitiveMembers.add(competitiveMember);

            // tilføjer competitive member til det team der passer med members aldersgruppe
            if (Integer.parseInt(memberInfo.get(1)) < 18) {
                juniorTeam.addTeamMember(competitiveMember);
            } else {
                seniorTeam.addTeamMember(competitiveMember);
            }

            fh.writeToFileComp(competitiveMember);

        }

    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public ArrayList<CompetitiveMember> getCompetitiveMembers() {
        return competitiveMembers;
    }

    public Team getJuniorTeam() {
        return this.juniorTeam;
    }

    public Team getSeniorTeam() {
        return seniorTeam;
    }

    public void deleteMember(String memberType, int index) {
        if (memberType.equals("normal") && index-1 <= members.size()) {
            members.remove(index - 1);
            fh.refreshMemberData(members);
        } else if (index-1 <= competitiveMembers.size()){
            competitiveMembers.remove(index - 1);
            fh.refreshCompMemberData(competitiveMembers);
        }
    }

    public void editMember(String memberType, int index, String attribute, String info) {

        if (memberType.equals("normal") && index-1 <= members.size()) {
            if (attribute.equals("name")) {
                members.get(index - 1).setMemberName(info);
            } else if (attribute.equals("age")) {
                members.get(index - 1).setMemberAge(info);
            } else {
                if (info.equals("passive")) {
                    members.get(index - 1).setSubscriptionType(SubscriptionType.PASSIVE.getType());
                } else {
                    if (members.get(index - 1).getAge() < 18) {
                        members.get(index - 1).setSubscriptionType(SubscriptionType.YOUTH.getType());
                    } else if (members.get(index - 1).getAge() >= 18 || members.get(index - 1).getAge() < 60) {
                        members.get(index - 1).setSubscriptionType(SubscriptionType.ADULT.getType());
                    } else {
                        members.get(index - 1).setSubscriptionType(SubscriptionType.SENIOR.getType());
                    }
                }
            }
        }

        if (memberType.equals("competitive") && index-1 <= competitiveMembers.size()) {
            if (attribute.equals("name")) {
                competitiveMembers.get(index - 1).setMemberName(info);
            } else if (attribute.equals("age")) {
                competitiveMembers.get(index - 1).setMemberAge(info);
            } else {
                if (info.equals("passive")) {
                    competitiveMembers.get(index - 1).setSubscriptionType(SubscriptionType.PASSIVE.getType());
                } else {
                    if (competitiveMembers.get(index - 1).getAge() < 18) {
                        competitiveMembers.get(index - 1).setSubscriptionType(SubscriptionType.YOUTH.getType());
                    } else if (competitiveMembers.get(index - 1).getAge() >= 18 || competitiveMembers.get(index - 1).getAge() < 60) {
                        competitiveMembers.get(index - 1).setSubscriptionType(SubscriptionType.ADULT.getType());
                    } else {
                        competitiveMembers.get(index - 1).setSubscriptionType(SubscriptionType.SENIOR.getType());
                    }
                }
            }
        }
    }

    public void addCompetitiveResult(ArrayList<String> resultsInfo, int index) {
        CompetitiveResult competitiveResult = new CompetitiveResult(resultsInfo.get(0),
                resultsInfo.get(1), Double.parseDouble(resultsInfo.get(2)), resultsInfo.get(3), resultsInfo.get(4));

        competitiveResult.setCompetitiveNumber(competitiveMembers.get(index - 1).getCompetitiveNumber());

        competitiveMembers.get(index - 1).addCompetition(competitiveResult);
        fh.writeResultToFile(competitiveResult);
    }

    public void joinResultWithMember() {
        for (CompetitiveResult competitiveResult : competitiveResults) {
            for (CompetitiveMember competitiveMember : competitiveMembers) {
                if (competitiveResult.getCompetitiveNumber().equals(competitiveMember.getCompetitiveNumber())) {
                    competitiveMember.addCompetition(competitiveResult);
                }
            }
        }
    }

    public ArrayList<CompetitiveResult> getCompetitiveResults(int index) {
        return competitiveMembers.get(index - 1).getCompetitions();
    }

    public ArrayList<CompetitiveResult> topFiveTimesInDiscipline(String discipline, String team) {
        Team actualTeam;
        if (team.equals("junior team")) {
            actualTeam = juniorTeam;
        } else {
            actualTeam = seniorTeam;
        }

        ArrayList<CompetitiveResult> results;
        ArrayList<CompetitiveResult> resultsToBeCompared = new ArrayList<>();



        for (CompetitiveMember competitiveMember : actualTeam.getTeamMembers()) {
            results = competitiveMember.getCompetitiveResults();

            for (CompetitiveResult competitiveResult : results) {

                if (competitiveResult.getDiscipline().equals(discipline)) {
                    resultsToBeCompared.add(competitiveResult);
                }
            }
        }

        Collections.sort(resultsToBeCompared, timeComparator);

        return resultsToBeCompared;
    }
}