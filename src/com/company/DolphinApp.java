//Lasse
package com.company;

import com.company.domain.Controller;

import java.io.FileNotFoundException;

public class DolphinApp {

    public static void main(String[] args) throws FileNotFoundException {
        Controller controller = new Controller();
        controller.start();
    }
}