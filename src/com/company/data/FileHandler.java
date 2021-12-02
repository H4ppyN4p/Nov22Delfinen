package com.company.data;

import com.company.domain.CompetitiveMember;
import com.company.domain.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private final String FILE_NAME = "data/Members.csv";
    private final String FILE_NAME_COMP = "data/competitiveMembers.csv";
    private PrintStream ps = new PrintStream(new FileOutputStream(FILE_NAME, true));
    private PrintStream psComp = new PrintStream(new FileOutputStream(FILE_NAME_COMP, true));

    public FileHandler() throws FileNotFoundException {
        readFromFile();
        readFromFileCompMembers();
    }

    public ArrayList<Member> readFromFile(){
        ArrayList<Member> listOfAllMembers = new ArrayList<>();

        // Åbn en fil
        // Læs hver linje i filen
        try{
            //Opret fil object for at arbejde med csv-filen
            File membersFile = new File(FILE_NAME_MEMBER);

            //Scan csv-filen
            Scanner reader = new Scanner(membersFile);

            //Vælg hvordan csv-filen skal splittes
            reader.useDelimiter(";");

            //så længe der er en ny linje
            while(reader.hasNext()) {

                String line = reader.nextLine();

                //Opret et Member object ud fra dataen i den linje
                String[] newMember = line.split(";");

                Member member = new Member(newMember[0], newMember[1], newMember[2], Boolean.parseBoolean(newMember[4]));
                listOfAllMembers.add(member);
            }

        } catch(FileNotFoundException e){
            // No file found - just ignore, and start with empty database!
            listOfAllMembers.clear();
        }
        return listOfAllMembers;
    }

    public ArrayList<CompetitiveMember> readFromFileCompMembers(){
        ArrayList<CompetitiveMember> listOfAllCompMembers = new ArrayList<>();

        // Åbn en fil
        // Læs hver linje i filen
        try{
            //Opret fil object for at arbejde med csv-filen
            File membersFile = new File("data/competitiveMembers.csv");
            //Scan csv-filen
            Scanner reader = new Scanner(membersFile);
            //Vælg hvordan csv-filen skal splittes
            reader.useDelimiter(";");

            //så længe der er en ny linje
            while(reader.hasNext()) {


                String line = reader.nextLine();
                //Opret et Member object ud fra dataen i den linje
                String[] newMember = line.split(";");

                CompetitiveMember member = new CompetitiveMember(newMember[0], newMember[1], newMember[2],
                        Boolean.parseBoolean(newMember[3]), Boolean.parseBoolean(newMember[4]),
                        Boolean.parseBoolean(newMember[5]), Boolean.parseBoolean(newMember[6]),
                        Boolean.parseBoolean(newMember[7]), newMember[8], newMember[9],
                        Double.parseDouble(newMember[10]), newMember[11], newMember[12]);

                listOfAllCompMembers.add(member);
            }

        } catch(FileNotFoundException e){
            // No file found - just ignore, and start with empty database!
            listOfAllCompMembers.clear();
        }
        return listOfAllCompMembers;
    }

    public void writeToFile(Member member){
        ps.println(member);
    }

    public void writeToFileComp(CompetitiveMember competitiveMember){
        psComp.println(competitiveMember);
    }

    public void refreshMemberData(ArrayList<Member> members) {
        File memberFile = new File(FILE_NAME);

        try {
            PrintStream psm = new PrintStream(new FileOutputStream(memberFile, false));

            for (Member member : members) {
                psm.println(member);
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }

    public void refreshCompMemberData(ArrayList<CompetitiveMember> compMembers) {
        File compMemberFile = new File(FILE_NAME_COMP);

        try {
            PrintStream psm = new PrintStream(new FileOutputStream(compMemberFile, false));

            for (Member member : compMembers) {
                psm.println(member);
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }

}
