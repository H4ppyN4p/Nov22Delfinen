package com.company.domain;

import com.company.data.MemberDatabase;
import com.company.ui.UserInterface;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    private boolean running = true;
    private Scanner scanner = new Scanner(System.in);
    private UserInterface ui = new UserInterface();
    private MemberDatabase mdb = new MemberDatabase();
    private Subscription sub = new Subscription();

    public Controller() throws FileNotFoundException {
    }

    public void start() throws FileNotFoundException {
        ui.printWelcome();


        while (running) {
            ui.printMenu();

            String input = scanner.nextLine().toLowerCase();
            switch (input){
                case "1":
                    mdb.createMember();
                    break;
                case "2":
                    viewMembers();
                    break;
                case "3":
                    viewTeamMembers();
                    break;
                case "4":
                    viewAllMemberSubscriptionStatus();
                    break;
                case "5":
                    viewExpectedIncome();
                    break;
                case "6":
                    viewAllResults();
                    break;
                case "7":
                    deleteMemberFromList(1);
                    break;
                case "8":
                    editMemberFromList(2);
                    break;
                case "9":
                    addCompResult();
                    break;
                case "10":
                    viewCompMemberResult();
                    break;
                case "0":
                    running = false;
                    ui.printMessage("Shutting down");
                    break;
                default:
                    System.out.println("Cannot do that");
            }

        }

    }

    public ArrayList<String> createMember() {
        return ui.getMemberInfo(scanner);
    }

    public void viewMembers() {
        ArrayList<Member> members = mdb.getMembers();
        ArrayList<CompetitiveMember> compMembers= mdb.getCompetitiveMembers();

        ui.printMessage("All members: ");
        int count = 1;
        for (Member member : members) {
            ui.printMessage(count + ". " + member.toStringUI());
            count++;
        }
        ui.printMessage("\n");

        ui.printMessage("Competitive members: ");
        int count2 = 1;
        for (CompetitiveMember competitiveMember : compMembers) {
            ui.printMessage(count2 + ". " + competitiveMember.toStringUI());
            count2++;
        }

        ui.printMessage("\n");

    }

    public void viewTeamMembers() {
        Team juniorTeam = mdb.getJuniorTeam();
        Team seniorTeam = mdb.getSeniorTeam();


        ui.printMessage("Junior team:");
        for (CompetitiveMember juniorMember : juniorTeam.getTeamMembers()) {
            ui.printMessage(juniorMember.toStringUI());
        }

        ui.printMessage("Senior team:");
        for (CompetitiveMember seniorMember : seniorTeam.getTeamMembers()) {
            ui.printMessage(seniorMember.toStringUI());
        }

    }

    public void viewCompetitiveMembers() {
        ArrayList<CompetitiveMember> compMembers= mdb.getCompetitiveMembers();
        int count = 1;
        ui.printMessage("Competitive members: ");

        for (CompetitiveMember competitiveMember : compMembers) {

            ui.printMessage(count + ". " + competitiveMember.toStringUI());
            count++;
        }

        ui.printMessage("\n");
    }

    public void viewAllMemberSubscriptionStatus() {
        ArrayList<Member> members = mdb.getMembers();
        ArrayList<CompetitiveMember> compMembers= mdb.getCompetitiveMembers();

        ui.printMessage("Normal members");
        for (Member member : members) {
            ui.printMessage(member.getMemberSubscriptionStatus());
        }
        ui.printMessage("\n");

        ui.printMessage("Competitive members");
        for (CompetitiveMember competitiveMember : compMembers) {
            ui.printMessage(competitiveMember.getMemberSubscriptionStatus());
        }

        ui.printMessage("\n");
    }

    public void viewExpectedIncome() {
        System.out.println(sub.expectedIncome(mdb.getMembers(), mdb.getCompetitiveMembers()));
    }

    public void viewAllResults() {

    }

    public void deleteMemberFromList(int deleteEdit) {
        viewMembers();
        String[] typeAndIndex = ui.getMemberTypeAndIndexDeleteEdit(scanner, deleteEdit);
        mdb.deleteMember(typeAndIndex[0], Integer.parseInt(typeAndIndex[1]));
    }

    public void editMemberFromList(int deleteEdit) {
        viewMembers();
        String[] typeAndIndex = ui.getMemberTypeAndIndexDeleteEdit(scanner, deleteEdit);
        String[] attributeAndInfo = ui.getAttributeAndInfo(scanner);
        mdb.editMember(typeAndIndex[0], Integer.parseInt(typeAndIndex[1]), attributeAndInfo[0], attributeAndInfo[1]);
    }

    public void addCompResult() {
        viewCompetitiveMembers();
        int index = ui.getCompMemberIndex(scanner);
        ArrayList<String> resultInfo = ui.getCompResultInfo(scanner);
        mdb.addCompetitiveResult(resultInfo, index);
    }

    public void viewCompMemberResult() {
        ArrayList<CompetitiveResult> results = mdb.getCompetitiveResults(ui.getIndex(scanner));
        int count = 1;
        System.out.println("Competitive results: ");
        for (CompetitiveResult result : results) {
            ui.printMessage(count + ". " + result);
        }

    }



}