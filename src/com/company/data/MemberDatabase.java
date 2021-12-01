package com.company.data;

import com.company.domain.Team;
import com.company.domain.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MemberDatabase {


    private ArrayList<Member> members;
    private ArrayList<CompetitiveMember> competitiveMembers;
    private Team juniorTeam = new Team(AgeGroup.JUNIOR);
    private Team seniorTeam = new Team(AgeGroup.SENIOR);
    private ArrayList<Subscription> subscriptions = new ArrayList<>();
    private FileHandler fh = new FileHandler();

    public MemberDatabase() throws FileNotFoundException {
        members = fh.readFromFile();
        competitiveMembers = fh.readFromFileCompMembers();
        juniorTeam.addMemberToTeam(competitiveMembers);
        seniorTeam.addMemberToTeam(competitiveMembers);
    }



    public void createMember() throws FileNotFoundException {
        Controller controller = new Controller();
        ArrayList<String> memberInfo = controller.createMember();
        if (memberInfo.get(3).equals("n")) {
            Member member = new Member(memberInfo.get(0), memberInfo.get(1), memberInfo.get(2));
            members.add(member);
            fh.writeToFile(member);

            /*Subscription subscription = new Subscription(member);
            subscriptions.add(subscription);

             */
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

            /*Subscription subscription = new Subscription(competitiveMember);
            subscriptions.add(subscription);
             */
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

    public ArrayList<Member> getTop5FastestSwimTimesForEachDiscipline(){
        return members;
    }

    public ArrayList<Member> getAmountOfJuniorMembers(){
        return members;
    }

    public ArrayList<Member> getAmountOfAdultMembers(){
        return members;
    }


    public ArrayList<Member> getAmountOfSeniorMembers(){
        return members;
    }

    public ArrayList<Member> getAmountOfPassiveMembers(){
        return members;
    }

    public ArrayList<Member> getAmountOfActiveMembers(){
        return members;
    }

    public ArrayList<Member> getAmountOfMembersInRestance(){
        return members;
    }

    public void deleteMember(String memberType, int index) {

        if (memberType.equals("normal")) {
            members.remove(index - 1);
            fh.refreshMemberData(members);
        } else {
            competitiveMembers.remove(index - 1);
            fh.refreshCompMemberData(competitiveMembers);
        }
    }

    public void editMember(String memberType, int index, String attribute, String info) {

        if (memberType.equals("normal")){
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

        if (memberType.equals("competitive")){
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


}