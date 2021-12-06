package com.company.ui;

import com.company.domain.SubscriptionType;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    public void printWelcome() {
        System.out.println("Welcome to The Dolphin. Choose what you want to do");
    }

    public void printMenu() {
        System.out.print("""              
                1) Create new member  
                2) View all members 
                3) View teams
                4) View all members subscription status
                5) View expected income from subscriptions
                6) View all results
                7) Delete member
                8) Edit member
                9) Add result to a competitive member
                10) View results for a competitive member
                0) Exit
                                
                Enter command:  """);
    }

    public void printMessage(String s) {
        System.out.println(s);
    }

    public ArrayList<String> getMemberInfo(Scanner scanner) {
        ArrayList<String> memberInfo = new ArrayList<>();
        System.out.print("Enter members name: ");
        String memberName = scanner.nextLine();
        memberInfo.add(memberName);
        System.out.print("Enter members age: ");
        String memberAge = scanner.nextLine();
        memberInfo.add(memberAge);

        boolean go = true;

        while (go) {
            System.out.print("Enter subscription type ('a' for active and 'p' for passive): ");
            String subscriptionType = scanner.nextLine();

            switch (subscriptionType) {
                case "a":
                    if (Integer.parseInt(memberAge) < 18) {
                        memberInfo.add(SubscriptionType.YOUTH.getType());
                    } else if (Integer.parseInt(memberAge) >= 18 || Integer.parseInt(memberAge) < 60) {
                        memberInfo.add(SubscriptionType.ADULT.getType());
                    } else {
                        memberInfo.add(SubscriptionType.SENIOR.getType());
                    }
                    go = false;
                    break;
                case "p":
                    memberInfo.add(SubscriptionType.PASSIVE.getType());
                    go = false;
                    break;
                default:
                    System.out.println("Something went wrong, try again");
            }
        }

        boolean loop = true;

        while (loop) {
            System.out.print("Is member competitive? (y/n): ");
            String compStatus = scanner.next();
            if (compStatus.equals("y")) {

                System.out.println("Which swimming disciplines does the member want to participate in? (y/n)");

                memberInfo.add(disciplineChoice("Crawl", scanner));
                memberInfo.add(disciplineChoice("Butterfly", scanner));
                memberInfo.add(disciplineChoice("Backstroke", scanner));
                memberInfo.add(disciplineChoice("Breaststroke", scanner));

                memberInfo.add(compStatus);
                loop = false;
            } else if (compStatus.equals("n")) {
                memberInfo.add(compStatus);
                loop = false;
            } else {
                System.out.println("Type either 'y' for yes or 'n' for no");
            }
        }

        System.out.println(memberName + " has been created\n");

        return memberInfo;
    }

    public String disciplineChoice(String discipline, Scanner scanner) {
        System.out.print(discipline + ": ");
        boolean loop = true;

        while (loop) {
            String choice = scanner.next();
            if (choice.equals("y")) {
                return "true";
            } else if (choice.equals("n")) {
                return "false";
            } else {
                System.out.println("Type either 'y' for yes of 'n' for no");
            }
        }
        return "false";
    }

    public String[] getMemberTypeAndIndexDelete(Scanner scanner) {
        String[] typeAndIndex = new String[2];
        boolean loop1 = true;
        boolean loop2 = true;
        String type = "";

        System.out.println("Which type of member do you wish to delete? (Type 'n' for normal and 'c' for competitive)");

        while (loop1) {
            type = scanner.next();

            if (type.equals("n")) {
                type = "normal";
                loop1 = false;
            } else if (type.equals("c")) {
                type = "competitive";
                loop2 = false;
            } else {
                System.out.println("Type either 'n' or 'c' to continue");
            }
        }

        System.out.println("Type the number next to the member you wish to delete");

        String index = scanner.next();

        typeAndIndex[0] = type;
        typeAndIndex[1] = index;

        return typeAndIndex;
    }

    public String[] getMemberTypeAndIndexEdit(Scanner scanner) {
        String[] typeAndIndex = new String[2];
        boolean loop1 = true;
        boolean loop2 = true;
        String type = "";

        System.out.println("Which type of member do you wish to edit? (Type 'n' for normal and 'c' for competitive)");

        while (loop1) {
            type = scanner.next();

            if (type.equals("n")) {
                type = "normal";
                loop1 = false;
            } else if (type.equals("c")) {
                type = "competitive";
                loop2 = false;
            } else {
                System.out.println("Type either 'n' or 'c' to continue");
            }
        }

        System.out.println("Type the number next to the member you wish to edit");

        String index = scanner.next();

        typeAndIndex[0] = type;
        typeAndIndex[1] = index;

        return typeAndIndex;
    }

    public String[] getAttributeAndInfo(Scanner scanner) {
        String[] attributeAndInfo = new String[2];
        boolean loop1 = true;
        boolean loop2 = true;
        boolean loop3 = true;
        String attribute = "";
        String info = "";

        System.out.println("Which attribute do you wish to edit? (Type 'n' for name, 'a' for age, and 's' for subscription type");

        while (loop1) {
            attribute = scanner.next();

            if (attribute.equals("n")) {
                attribute = "name";
                loop1 = false;
            } else if (attribute.equals("a")) {
                attribute = "age";
                loop1 = false;
            } else if (attribute.equals("s")) {
                attribute = "subscription type";
                loop1 = false;
            } else {
                System.out.println("Type either 'n', 'a' or 's' to continue");
            }
        }

        if (attribute.equals("name")) {
            System.out.println("Type in a new name to replace the old one");
            info = scanner.nextLine();
        } else if (attribute.equals("age")) {
            System.out.println("Type in an age between 0-122");

            while (loop2) {
                info = scanner.next();

                if (Integer.parseInt(info) < 0 || Integer.parseInt(info) > 122) {
                    System.out.println("Invalid age, try again");
                } else {
                    loop2 = false;
                }
            }
        } else {
            System.out.println("Type in the subcription type you want to change to ('a' for active and 'p' for passive");

            while (loop3) {
                info = scanner.next();

                if (info.equals("a")) {
                    info = "active";
                    loop3 = false;
                } else if (info.equals("p")) {
                    info = "passive";
                    loop3 = false;
                } else {
                    System.out.println("Type either 'a' or 'p' to continue");
                }
            }
        }

        attributeAndInfo[0] = attribute;
        attributeAndInfo[1] = info;

        return attributeAndInfo;
    }

    public int getCompMemberIndex(Scanner scanner) {
        System.out.print("Type the number beside the member you want to add a competition result to: ");
        boolean loop = true;

        while (loop){
            String index = scanner.next();

            if (Integer.parseInt(index) < 1) {
                System.out.println("Invalid input. Type a number next to a member to continue");
            } else {
                return Integer.parseInt(index);
            }
        }
        return 0;
    }
    
    public ArrayList<String> getCompResultInfo(Scanner scanner) {
        boolean loop = true;
        ArrayList<String> resultInfo = new ArrayList<>();

        System.out.print("Enter competition name: ");
        String compName = scanner.next();
        resultInfo.add(compName);

        System.out.print("Enter competition location: ");
        String compLocation = scanner.next();
        resultInfo.add(compLocation);

        System.out.print("Enter swimming time in seconds (decimal number allowed, use punctuation and not commas as a separator) : ");
        String time = scanner.next();
        resultInfo.add(time);

        String discipline = "";

        System.out.println("1. Crawl\n2. Butterfly\n3. Back stroke\n4. Breast stroke");
        System.out.print("Enter the number next to the swimming discipline: ");
        while (loop) {
            discipline = scanner.next();


            if (discipline.equals("1")) {
                discipline = "crawl";
                loop = false;
            } else if (discipline.equals("2")) {
                discipline = "butterfly";
                loop = false;
            } else if (discipline.equals("3")) {
                discipline = "back stroke";
                loop = false;
            } else if (discipline.equals("4")) {
                discipline = "breast stroke";
                loop = false;
            } else {
                System.out.println("Type a number to continue");
            }
            resultInfo.add(discipline);
        }


        System.out.print("Enter date of competition: ");
        String date = scanner.next();
        resultInfo.add(date);

        System.out.println("Result added to database");

        return resultInfo;
    }

    public int getIndex(Scanner scanner) {
        System.out.print("Type the number next to the member you wish to see the result of: ");
        boolean loop = true;
        String index = "";

        while (loop) {
            index = scanner.next();

            if (Integer.parseInt(index) < 1) {
                System.out.println("Invalid number");
                System.out.print("Type a number: ");
            } else {
                loop = false;
            }
        }
        return Integer.parseInt(index);
    }
}
