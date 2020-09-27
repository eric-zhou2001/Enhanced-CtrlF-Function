// --== CS400 File Header Information ==--
// Name: Franklin Wang
// Email: fwang263@wisc.edu
// Team: IG
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class DataWrangler1<KeyType, ValueType> {
  private ArrayList<HashNode<KeyType, ValueType>> hashNodeList;
  private String textToProcess;
  private File file;
  private ArrayList<String> uniqueWords;
  private ArrayList<Integer> wordOccurrences;

  public DataWrangler1() {
    this.hashNodeList = new ArrayList<>();
    this.file = new File("sampletext.txt");
    this.uniqueWords = new ArrayList<>();
    this.wordOccurrences = new ArrayList<>();
  }
  
  public DataWrangler1(File file) {
    this.hashNodeList = new ArrayList<>();
    this.file = file;
    this.uniqueWords = new ArrayList<>();
    this.wordOccurrences = new ArrayList<>();
  }
  
  public DataWrangler1(String textToProcess) {
    this.hashNodeList = new ArrayList<>();
    this.file = null;
    this.textToProcess = textToProcess;
    this.uniqueWords = new ArrayList<>();
    this.wordOccurrences = new ArrayList<>();
  }
  
  /** 
   * Creates an Array of unique word entries.
   * 
   *  read each line, then check for unique words
     If there is a unique word, put it into the hash map as a node.
     If there is a duplicate word, increment the corresponding hash node.
     Repeat for all lines in the file.
     What counts as a word?
     - No numbers/symbols (includes hyphens)
     - More than one letter
     - Not case sensitive (set all words to lowercase)
   */
  public void getUniqueWords() {
    Scanner scnr = null;
    
    if (file != null) {
      try {
        scnr = new Scanner(file); // create a scanner for the .txt file
      } 
      catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
    else {
      scnr = new Scanner(textToProcess);
    }
    
    
    while (scnr.hasNextLine()) {
      String line = scnr.nextLine(); // read each line in the file
      Scanner lineScnr = new Scanner(line); // create new scanner to read each word in the file
      
      while (lineScnr.hasNext()) {
        String word = lineScnr.next();
        if (!word.matches("[a-zA-Z]+")) { // check if word contains any non-letter characters
          continue;
        }
        
        
        word = word.toLowerCase();
        
        if (!uniqueWords.contains(word)) { // if word is unique
          uniqueWords.add(word);
          wordOccurrences.add(1);
        }
        else { // if word is already in uniqueWords
          int wordIndex = uniqueWords.indexOf(word);
          int wordCount = wordOccurrences.get(wordIndex);
          wordCount++; // increment wordOccurrences array when duplicate word is found
          wordOccurrences.set(wordIndex, wordCount);
        }
      }
    }
    
    for (int i = 0; i < uniqueWords.size(); ++i) {
      HashNode<KeyType, ValueType> newNode = new HashNode(uniqueWords.get(i), wordOccurrences.get(i));
      hashNodeList.add(newNode);
    }
  }
  
  public void printHashNodeList() {
    for (int i = 0; i < hashNodeList.size(); ++i) {
      System.out.println("Word: " + hashNodeList.get(i).getKey() + " : " 
                          + "Value: " + hashNodeList.get(i).getValue());
    }
  }

  public ArrayList<HashNode<KeyType, ValueType>> getHashNodeList() {
    return hashNodeList;
  }

  public void setHashNodeList(ArrayList<HashNode<KeyType, ValueType>> hashNodeList) {
    this.hashNodeList = hashNodeList;
  }

  public ArrayList<Integer> getWordOccurrences() {
    return wordOccurrences;
  }


  public static void main(String[] args) {
    DataWrangler1<String, Integer> testData = new DataWrangler1();
    
    testData.getUniqueWords();
    testData.printHashNodeList();
    
  }
}
