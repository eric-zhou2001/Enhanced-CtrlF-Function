// --== CS400 File Header Information ==--
// Name: Eric Zhou
// Email: ezhou22@wisc.edu
// Team: IG
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader: Hello there!

import java.util.Scanner;

public class frontend {
    
    private static void helpFunction() {
        System.out.println("Welcome to Enhanced Control-F Function!");
        System.out.println("We hope to provide additional insight into"
            + " your writing!");
        System.out.println("Here are our available commands:");
        System.out.println("\th - Help command. Lists available commands.");
        System.out.println("\tt - Text input command. Can paste/enter text to analyze.");
        System.out.println("\ts - Search command. Enter a word, and receive "
            + "if the word exists in the previously entered text input. \n\t"
            + "Additionally, if it appears, provides number of times it appears.");
        System.out.println("\tp - Print command. Prints each word in the page, as "
            + "well as the number of appearances of each word.");
    }
    // This is what we "run" the program with. It's a while(true) loop that 
    // will always run and wait upon user response.
    public static void main(String[] args) {
        DataWrangler1<String, Integer> dataProcess = null;
        Backend1 table = null;
        String br = "----------------------------------------------";
        String command = "";
        boolean textInput = false;
        System.out.println("Welcome to Enhanced Control-F!");
        while (true) {
            String text ="";
            System.out.println(br);
            System.out.println("Please enter a command: ");
            System.out.println("\tEntering \"h\" will bring up a menu of options.");
            Scanner input = new Scanner(System.in);
            
            command = input.nextLine().trim().toLowerCase();
            
            // Test case if user enters null (somehow?)
            if (command == null) {
                System.out.println("Error! Please do not enter null. Enter a valid command.");
                System.out.println("\tEntering \"h\" will bring up a menu of options.");
            }
            
            // Test case if user enters an empty string.
            if (command.length() <= 0) {
                System.out.println("Error! Please enter a valid command!");
                System.out.println("\tEntering \"h\" will bring up a menu of options.");
                continue;
            }
            
            // Test case if user enters a string > 1 (not a valid command!)
            if (command.length() > 1) {
                System.out.println("Error! Please enter a valid command! (One char...)");
                System.out.println("\tEntering \"h\" will bring up a menu of options.");
                continue;
            }
            
            // Actual command input/processing.
            if (command.equals("h")) {
                helpFunction();
                continue;
            } else if (command.equals("t")) {
                System.out.println("Enter your text to be analyzed here: ");
                text += input.nextLine();
                if (text.replaceAll("\\s+","").matches("^[a-zA-z]*$")) {
                    System.out.println("Processing text: " + text);
                 // Creates the table by doing this.
                  dataProcess = new DataWrangler1(text);
                  dataProcess.countUniqueWords();
                  table = new Backend1(dataProcess);
                  textInput = true;
                } else {
                    System.out.println("Error! Please enter only alphabetical"
                        + " characters!");
                    continue;
                }
            } else if (command.equals("s")) {
                // Search command
                if (!textInput) {
                    System.out.println("Error! Please enter text (t) before "
                        + "trying to search for a word!");
                    continue;
                }
                System.out.println("Enter the word to search here: ");
                String word = input.nextLine();
                if (word.equals("") || word.split(" ").length > 1) {
                    System.out.println("Error! Please enter one word.");
                    continue;
                }
                if (word.matches("[a-zA-z]+")) {
                    System.out.println("Searching for word: " + word);
// This is where we would search table.
                    // Search the table.
                    Integer searchResult = table.search(word);
                    System.out.println(word + " : " + searchResult);
                } else {
                    System.out.println("Error! Word is not valid. Please enter"
                        + " only characters.");
                }
            } else if (command.equals("p")) {
                // access table method.
                if (dataProcess != null) {
                    dataProcess.printHashNodeList();
                }
            }
        }
        
    }
}
