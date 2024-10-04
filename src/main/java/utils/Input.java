package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    private Scanner scan = new Scanner(System.in);

    public int getNum(String message) {
        while (true) {
            try {
                System.out.println(message != "" ? (">>> " + message + ":") : ">>> Enter number: ");
                return scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("[-] Please enter valid number.\n");
                scan.nextLine();
            }
        }
    }
    public String getStr(String message){
        while (true) {
            System.out.println(message != "" ? (">>> " + message + ":") : ">>> Enter text:");
            String input = scan.nextLine();
            if(input.replaceAll(" ", "").matches("[a-zA-Z]+")) return input; else System.out.println("[-] Inpute must contain only letters.");
        }
    }
    public String getStr(String message, boolean includeNumbers){
        if(!includeNumbers) return getStr(message);
        while (true) {
            System.out.println(message != "" ? (">>> " + message + ":") : ">>> Enter text:");
            String input = scan.nextLine();
            if (input.replaceAll(" ", "").matches("[\\w&&[^_]]+")) return input; else System.out.println("[-] Inpute must contain only letters and numbers.\n");                     
        }
    }
    public LocalDate getLocalDate(String message, boolean canBeAboveNow){
        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate now = LocalDate.now();
        while (true) {
            try{
                System.out.println(message != "" ? (">>> " + message + ":") : ">>> Enter date (DD/MM/YYYY):");
                String input = scan.nextLine();
                LocalDate inputDate = LocalDate.parse(input, form);
                if (canBeAboveNow) return inputDate;
                if (!canBeAboveNow && inputDate.isBefore(now)) return inputDate; else System.out.println("[-] Please enter a date that is before " + now + "\n");
            } catch (DateTimeParseException e){
                // throw error("[-] Invalid date, please enter a date in this format (DD/MM/YYYY)\n");
                System.out.println("[-] Invalid date, please enter a date in this format (DD/MM/YYYY)\n");
            }
        }
    }

    public boolean getYesNo(String message){
        while (true) {
            System.out.println(message != "" ? (">>> " + message + ":") : ">>> You want ? (y/n) : ");
            String input = scan.nextLine();
            if (input.toLowerCase().matches("y||n")) return input.equalsIgnoreCase("y");
            else System.out.println("[-] Input must be either \"y/Y\" or \"n/N\"\n");
        }
    }

    public String getPhone(String message){
        while (true) {
            System.out.println(message != "" ? (">>> " + message + ":") : ">>> Enter phone number (+212700000000) : ");
            String input = scan.nextLine();
            if (input.replaceAll("[\\s\\-]", "").matches("\\+[0-9]{10,14}")) return input.replaceAll("[\\s\\-]", ""); else System.out.println("[-] Input must be like +XXXXXXXXXX (10 to 14 numbers, note: can contain \" \" or \"-\").\n");
        }
    }

}