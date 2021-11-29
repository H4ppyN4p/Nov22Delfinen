package com.company;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    private boolean running = true;
    private Scanner scanner = new Scanner(System.in);
    private UserInterface ui = new UserInterface();
    private MemberDatabase mdb = new MemberDatabase();

    public Controller() throws FileNotFoundException {
    }

    public void start() throws FileNotFoundException {
        ui.printWelcome();


        while (running) {
            ui.printMenu();

            String input = scanner.nextLine();
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
                    handleSubscription();
                    break;
                case "5":
                    viewAllSubscriptions();
                    break;
                case "6":
                    viewAllResults();
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
        for (Member member : members) {
            ui.printMessage(member.toString());
        }
        ui.printMessage("\n");

        ui.printMessage("Competitive members: ");
        for (CompetitiveMember competitiveMember : compMembers) {
            ui.printMessage(competitiveMember.toString());
        }

        ui.printMessage("\n");

    }

    public void viewTeamMembers() {
        Team juniorTeam = mdb.getJuniorTeam();
        Team seniorTeam = mdb.getSeniorTeam();


        ui.printMessage("Junior team:");
        for (CompetitiveMember juniorMember : juniorTeam.getTeamMembers()) {
            ui.printMessage(juniorMember.toString());
        }

        ui.printMessage("Senior team:");
        for (CompetitiveMember seniorMember : seniorTeam.getTeamMembers()) {
            ui.printMessage(seniorMember.toString());
        }

    }

    public void handleSubscription() {

    }

    public void viewAllSubscriptions() {

    }

    public void viewAllResults() {

    }


}