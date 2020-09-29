// --== CS400 File Header Information ==--
// Name: Carter Craney
// Email: wcraney@wisc.edu
// Team: IG
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.ArrayList;
public class TestEnhancedCtrlF {
  
  
  /**
   * Test to check the basic functions of the hash table. It first makes a string, than a hash table of said string. 
   * It then searches for a word, and checks that the number of occurrences is correct. 
   * @return boolean
   */
  public static boolean testHashTable() {
    DataWrangler1<String, Integer> data = new DataWrangler1("This is a test for our advanced control f function."
        + "There will be many tests, including a test of the print function, a test of the chaining, and an"
        + "overall test of the Hash Table.");
    data.countUniqueWords();
    Backend1 test = new Backend1(data);
    //Checks to see if the search method returns the correct amount of instances of test
    if(test.search("test") == 4) {
        return true;
    }
    else {
      return false;
    }  
  }
  
  
  /**
   * Test method to check if the unique word and word occurence arraylists work as expected.
   * Just made a datawrangler with a string, than 2 array lists with the correct answers
   * and checked for similarity
   * 
   * @return boolean
   */
  public static boolean testWordOccurences() {
    DataWrangler1<String, Integer> data = new DataWrangler1("dog cheese dog folder cheese hello cheese");
    ArrayList<Integer> occurences = new ArrayList();
    ArrayList<String> words = new ArrayList();
    occurences.add(2);
    occurences.add(3);
    occurences.add(1);
    occurences.add(1);
    words.add("dog");
    words.add("cheese");
    words.add("folder");
    words.add("hello");
    data.countUniqueWords();
    data.getWordOccurrences();
   //Checks the similarity of the two sets of ArrayLists
    if(data.getWordOccurrences().equals(occurences)) {
      if(data.getUniqueWords().equals(words)) {
        return true;
      } 
    }
    return false;
  }
  
  
  /**
   * Test that first creates a string of test, and then creates a hashtable of said text. It then trys
   * to use the search function on a word that is not in the text, and checks if it returns correctly.
   * @return boolean
   */
  public static boolean testSearchNoWord() {
    DataWrangler1<String,Integer> data = new DataWrangler1("This is a test for our advanced control f function."
        + "There will be many tests, including a test of the print function, a test of the chaining, and an"
        + "overall test of the Hash Table.");
    Backend1 test = new Backend1(data);
    //Searches for a word that was not in the original data
    if(test.search("lemons")==0) {
      return true;
    }
    else {
      return false;
    }
  }
  
  public static void main(String[] args) {
    if(testHashTable()) {
      System.out.println("Basic hash table test passed");
    }
    else {
      System.out.println("Basic hash table test failed");
    }
    if(testSearchNoWord()) {
      System.out.println("Null search test passed");
    }
    else {
      System.out.println("Null search test failed");
    }
    if(testWordOccurences()) {
      System.out.println("Word occurence and uniqueness test passed");
    }
    else {
      System.out.println("Word occurence and uniqueness test failed");
    }
  }
}
