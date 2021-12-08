package com.company.ui;

import com.company.data.MemberDatabase;
import com.company.domain.Controller;
import com.company.domain.SubscriptionType;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    public void printWelcome() {
        System.out.println("\033[4;33mWelcome to The Dolphin. Choose what you want to do");
    }

    public void printMenu() {
        System.out.print("""     
                \033[0;34m         
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
                11) View top five lap times in a discipline
                0) Exit
                \033[4;34m                
                Enter command:  """);
    }

    public void printMessage(String s) {
        System.out.println(s);
    }

    public ArrayList<String> getMemberInfo(Scanner scanner) {
        ArrayList<String> memberInfo = new ArrayList<>();
        boolean loop = true;
        System.out.print("Enter members name: ");
        String memberName = scanner.nextLine();
        memberInfo.add(memberName);
        System.out.print("Enter members age (0-122): ");
        String memberAge = "";

        memberAge = getAge(scanner, loop, memberAge);
        memberInfo.add(memberAge);

        boolean go = true;

        while (go) {
            System.out.print("Enter subscription type ('a' for active and 'p' for passive): ");
            String subscriptionType = scanner.next();

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

        boolean loop2 = true;

        while (loop2) {
            System.out.print("Is member competitive? (y/n): ");
            String compStatus = scanner.next();
            if (compStatus.equals("y")) {

                System.out.println("Which swimming disciplines does the member want to participate in? (y/n)");

                memberInfo.add(disciplineChoice("Crawl", scanner));
                memberInfo.add(disciplineChoice("Butterfly", scanner));
                memberInfo.add(disciplineChoice("Backstroke", scanner));
                memberInfo.add(disciplineChoice("Breaststroke", scanner));

                memberInfo.add(compStatus);
                loop2 = false;
            } else if (compStatus.equals("n")) {
                memberInfo.add(compStatus);
                loop2 = false;
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

    public String[] getMemberTypeAndIndexDeleteEdit(Scanner scanner, int deleteEdit) {
        String[] typeAndIndex = new String[2];
        boolean loop1 = true;
        boolean loop2 = true;
        String type = "";

        if (deleteEdit == 1) {
            System.out.println("Which type of member do you wish to delete? (Type 'n' for normal and 'c' for competitive)");
        } else if (deleteEdit == 2) {
            System.out.println("Which type of member do you wish to edit? (Type 'n' for normal and 'c' for competitive)");
        }

        while (loop1) {
            type = scanner.next();

            if (type.equals("n")) {
                type = "normal";
                loop1 = false;
            } else if (type.equals("c")) {
                type = "competitive";
                loop1 = false;
            } else {
                System.out.println("Type either 'n' or 'c' to continue");
            }
        }


        if (deleteEdit == 1) {
            System.out.println("Type the number next to the member you wish to delete");
        } else if (deleteEdit == 2) {
            System.out.println("Type the number next to the member you wish to edit");
        }

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

            info = getAge(scanner, loop2, info);
        } else {
            System.out.println("Type in the subscription type you want to change to ('a' for active and 'p' for passive");

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

        while (loop) {
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
        scanner.nextLine();
        String compName = scanner.nextLine();
        resultInfo.add(compName);

        System.out.print("Enter competition location: ");
        String compLocation = scanner.nextLine();
        resultInfo.add(compLocation);


        System.out.print("Enter swimming time in seconds (decimal number allowed, use punctuation and not commas as a separator) : ");
        String time = scanner.next();
        resultInfo.add(time);

        String discipline = "";
        getDiscipline(scanner, loop, discipline);
        resultInfo.add(discipline);

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

    public String[] getDisciplineAndTeam(Scanner scanner) {
        String discipline = "";
        String team = "";
        boolean loop = true;
        boolean loop2 = true;
        String[] disciplineAndTeam = new String[2];

        disciplineAndTeam[0] = getDiscipline(scanner, loop, discipline);

        System.out.print("Enter team ('j' for junior team or 's' for senior team): ");
        while (loop2) {
            team = scanner.next();

            if (team.equals("j")) {
                team = "junior team";
                loop2 = false;
            } else if (team.equals("s")) {
                team = "senior team";
                loop2 = false;
            } else {
                System.out.println("Enter either 'j' or 's' to continue");
            }
        }
        disciplineAndTeam[1] = team;

        return disciplineAndTeam;
    }

    private String getAge(Scanner scanner, boolean loop, String memberAge) {
        while (loop) {
            memberAge = scanner.next();

            if (!isInteger(memberAge)) {
                System.out.println("Type in a whole number you moron");
            } else if (Integer.parseInt(memberAge) < 0 || Integer.parseInt(memberAge) > 122) {
                System.out.println("Invalid age, try again");
            } else {
                loop = false;
            }
        }
        return memberAge;
    }

    private String getDiscipline(Scanner scanner, boolean loop, String discipline) {
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
        }
        return discipline;
    }

    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
