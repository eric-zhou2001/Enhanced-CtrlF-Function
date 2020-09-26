import java.util.Scanner;

public class Main {
    
    // This is what we "run" the program with. It's a while(true) loop that 
    // will always run and wait upon user response.
    public static void main(String[] args) {
        String br = "----------------------------------------------";
        System.out.println("Welcome to Enhanced Control-F!");
        boolean textInput = false;
        while (true) {
            System.out.println(br);
            System.out.println("Please enter a command: ");
            System.out.println("\tEntering \"h\" will bring up a menu of options.");
            Scanner input = new Scanner(System.in);
            
            String command = input.nextLine().trim().toLowerCase();
            
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
                System.out.println("Error! Please enter a valid command! (One char...");
                System.out.println("\tEntering \"h\" will bring up a menu of options.");
                continue;
            }
            
            // Actual command input/processing.
            if (command.equals("h")) {
                // Help command
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
                continue;
            } else if (command.equals("t")) {
                // Text input command.
// TODO be aware of the fact that user can enter in \n to break stuff...
                // Be careful about .next() since it'll stop before \n!!!!
                System.out.println("Enter your text to be analyzed here: ");
                String text = input.nextLine();
                textInput = true;
            } else if (command.equals("s")) {
                // Search command
                System.out.println("Enter the word to search here: ");
                String word = input.next();
                // Error process it here...
            } else if (command.equals("p")) {
                // Print command.
            }
        }
        
    }
}
