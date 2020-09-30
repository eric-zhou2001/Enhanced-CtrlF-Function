// --== CS400 File Header Information ==--
// Name: <Sid .S. Khirwar
// Email: skhirwar@wisc.edu
// Team: IG
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class DataWrangler2<KeyType, ValueType> {

    private HashTableMap<String, Integer> hashMap;
    private String processString;
    private File givenFile;
    private ArrayList<String> words;
    private ArrayList<Integer> occurrencesPerWord;
    
    public DataWrangler2() {
        this.hashMap = new HashTableMap();
        processString = null;
        this.givenFile = new File("tale.txt");
        words = new ArrayList<>();
        occurrencesPerWord = new ArrayList<>();
    }
    
    public DataWrangler2(int capacity) {
        this.hashMap = new HashTableMap(capacity);
        processString = null;
        this.givenFile = new File("tale.txt");
        words = new ArrayList<>();
        occurrencesPerWord = new ArrayList<>();
    }
    
    public DataWrangler2(int capacity, File file) {
        this.hashMap = new HashTableMap(capacity);
        processString = null;
        this.givenFile = file;
        words = new ArrayList<>();
        occurrencesPerWord = new ArrayList<>();
    }
    
    public DataWrangler2(int capacity, String givenString) {
        this.hashMap = new HashTableMap(capacity);
        processString = givenString;
        this.givenFile = null;
        words = new ArrayList<>();
        occurrencesPerWord = new ArrayList<>();
    }
    
    public void noOfWords() {
        Scanner scr = null;
        
        if(givenFile != null) {
            try {
            
                scr = new Scanner(givenFile);
            
            }catch(FileNotFoundException fNFE) {
            
                System.out.println(" File does not exist.");
            
            }
        }else {
            scr = new Scanner(processString);
        }
        
        while (scr.hasNextLine()) {
            String scanLine = scr.nextLine(); 
            Scanner lineScr = new Scanner(scanLine);
            
            while(lineScr.hasNext()) {
                String word = lineScr.next();
                if(!word.matches("[a-zA-Z]+")) {
                    continue;
                }
                word = word.toLowerCase();
                if(!words.contains(word)) {
                    words.add(word);
                    occurrencesPerWord.add(1);
                }
                else {
                    int indexNo = words.indexOf(word);
                    int count = occurrencesPerWord.get(indexNo);
                    count++;
                    occurrencesPerWord.set(indexNo, count);
                }
            }
        }
        for(int i = 0; i < words.size(); i++) {
            HashPair<String, Integer> newPair = new HashPair(words.get(i), occurrencesPerWord.get(i));
            hashMap.put(words.get(i), occurrencesPerWord.get(i));
        }
    }
    
    public void printHashMap() {
      
        LinkedList<HashPair<String, Integer>> hashList[] = hashMap.hashTable();
        for(int i = 0; i < hashMap.capacity(); i++) {
            if(hashList[i] != null) {
                for(int j = 0; j < hashList[i].size();j++){
                    HashPair<KeyType, ValueType> leadingPair = (HashPair) hashList[i].get(j);
                    System.out.println("Word: " + leadingPair.key + " : " 
                        + "Value: " + leadingPair.value);
        }
      }

        }
    }
    public HashTableMap<String, Integer> getHashMap() {
        return hashMap;
    }
    
    public void setHashMap(HashTableMap<String, Integer> hashMap) {
        this.hashMap = hashMap;
    }
    
    public ArrayList<Integer> getOccurrencesPerWord() {
        return occurrencesPerWord;
      }
    
    public ArrayList<String> getWords() {
        return words;
    }
    
}
