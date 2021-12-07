package com.company.data;

import com.company.domain.CompetitiveMember;
import com.company.domain.CompetitiveResult;
import com.company.domain.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private final String FILE_NAME_MEMBER = "data/Members.csv";
    private final String FILE_NAME_COMP = "data/competitiveMembers.csv";
    private final String FILE_NAME_RESULTS = "data/competitiveResults.csv";
    private PrintStream ps = new PrintStream(new FileOutputStream(FILE_NAME_MEMBER, true));
    private PrintStream psComp = new PrintStream(new FileOutputStream(FILE_NAME_COMP, true));
    private PrintStream psRes = new PrintStream((new FileOutputStream(FILE_NAME_RESULTS, true)));

    public FileHandler() throws FileNotFoundException {
        readFromFile();
        readFromFileCompMembers();
        readFromFileResults();
    }

    public ArrayList<Member> readFromFile() {
        ArrayList<Member> listOfAllMembers = new ArrayList<>();

        // Åbn en fil
        // Læs hver linje i filen
        try {
            //Opret fil object for at arbejde med csv-filen
            File membersFile = new File(FILE_NAME_MEMBER);

            //Scan csv-filen
            Scanner reader = new Scanner(membersFile);

            //Vælg hvordan csv-filen skal splittes
            reader.useDelimiter(";");

            //så længe der er en ny linje
            while (reader.hasNext()) {

                String line = reader.nextLine();

                //Opret et Member object ud fra dataen i den linje
                String[] newMember = line.split(";");

                Member member = new Member(newMember[0], newMember[1], newMember[2], Boolean.parseBoolean(newMember[4]));
                listOfAllMembers.add(member);
            }

        } catch (FileNotFoundException e) {
            // No file found - just ignore, and start with empty database!
            listOfAllMembers.clear();
        }
        return listOfAllMembers;
    }

    public ArrayList<CompetitiveMember> readFromFileCompMembers() {
        ArrayList<CompetitiveMember> listOfAllCompMembers = new ArrayList<>();

        // Åbn en fil
        // Læs hver linje i filen
        try {
            //Opret fil object for at arbejde med csv-filen
            File membersFile = new File(FILE_NAME_COMP);
            //Scan csv-filen
            Scanner reader = new Scanner(membersFile);
            //Vælg hvordan csv-filen skal splittes
            reader.useDelimiter(";");

            //så længe der er en ny linje
            while (reader.hasNext()) {


                String line = reader.nextLine();
                //Opret et Member object ud fra dataen i den linje
                String[] newMember = line.split(";");

                CompetitiveMember member = new CompetitiveMember(newMember[0], newMember[1], newMember[2], newMember[3],
                        Boolean.parseBoolean(newMember[4]), Boolean.parseBoolean(newMember[5]),
                        Boolean.parseBoolean(newMember[6]), Boolean.parseBoolean(newMember[7]),
                        Boolean.parseBoolean(newMember[8]));

                listOfAllCompMembers.add(member);
            }

        } catch (FileNotFoundException e) {
            // No file found - just ignore, and start with empty database!
            listOfAllCompMembers.clear();
        }
        return listOfAllCompMembers;
    }

    public ArrayList<CompetitiveResult> readFromFileResults() {
        ArrayList<CompetitiveResult> listOfAllResults = new ArrayList<>();

        try {
            File resultFile = new File(FILE_NAME_RESULTS);

            Scanner reader = new Scanner(resultFile);
            //Vælg hvordan csv-filen skal splittes
            reader.useDelimiter(";");

            //så længe der er en ny linje
            while (reader.hasNext()) {


                String line = reader.nextLine();

                String[] newResult = line.split(";");

                CompetitiveResult competitiveResult = new CompetitiveResult(newResult[0], newResult[1], newResult[2],
                        Double.parseDouble(newResult[3]), newResult[4], newResult[5]);

                listOfAllResults.add(competitiveResult);
            }

        } catch (FileNotFoundException e) {
            // No file found - just ignore, and start with empty database!
            listOfAllResults.clear();
        }

        return listOfAllResults;
    }

    public void writeToFile(Member member) {
        ps.println(member);
    }

    public void writeToFileComp(CompetitiveMember competitiveMember) {
        psComp.println(competitiveMember);
    }

    public void writeResultToFile(CompetitiveResult competitiveResult) {
        psRes.println(competitiveResult);
    }

    public void refreshMemberData(ArrayList<Member> members) {
        File memberFile = new File(FILE_NAME_MEMBER);

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
