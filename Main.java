package com.computermatcher;

import com.computermatcher.model.Computer;
import com.computermatcher.service.ComputerMatcher;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ComputerMatcher matcher = new ComputerMatcher();

    public static void main(String[] args) {
        System.out.println("Welcome to Computer Matcher!");
        System.out.println("----------------------------");

        // Get budget
        double budget = getBudgetInput();

        // Get requirements
        boolean needsDedicatedGPU = getYesNoInput("Do you need a dedicated GPU for gaming or creative work? (yes/no): ");
        boolean needsPortable = getYesNoInput("Do you need the computer to be portable? (yes/no): ");
        boolean needsHighScreenQuality = getYesNoInput("Do you need high screen quality for content consumption? (yes/no): ");

        // Find and display matches
        List<Computer> matches = matcher.findMatches(
            budget, needsDedicatedGPU, needsPortable, needsHighScreenQuality
        );

        displayResults(matches);
        
        scanner.close();
    }

    private static double getBudgetInput() {
        while (true) {
            System.out.print("Enter your budget ($): ");
            try {
                double budget = Double.parseDouble(scanner.nextLine().trim());
                if (budget > 0) {
                    return budget;
                }
                System.out.println("Please enter a positive amount.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static boolean getYesNoInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("no") || input.equals("n")) {
                return false;
            }
            System.out.println("Please answer with 'yes' or 'no'.");
        }
    }

    private static void displayResults(List<Computer> matches) {
        System.out.println("\nMatching Results:");
        System.out.println("----------------");

        if (matches.isEmpty()) {
            System.out.println("No computers found within your budget.");
            return;
        }

        System.out.printf("Found %d matching computers:\n\n", matches.size());
        for (int i = 0; i < matches.size(); i++) {
            System.out.printf("Match #%d:\n", i + 1);
            System.out.println(matches.get(i));
        }
    }
}
