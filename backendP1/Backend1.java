// --== CS400 File Header Information ==--
// Name: Pintao Zou
// Email: fwang263@wisc.edu
// Team: IG
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Backend1 {
    DataWrangler1<String, Integer>data;
    HashTableMap<String, Integer>table;
    
    public Backend1(){
        this.data=new DataWrangler1<>();
        this.table=new HashTableMap<>();
    }
    
    // Just an FYI, I fixed a couple methods.
    public Backend1(DataWrangler1<String, Integer>data){//receive data from data wrangler and put them into the hash table
        this.data=data;
        this.table = new HashTableMap<>();
        for(int i=0;i<this.data.getUniqueWords().size();i++){
            this.table.put(this.data.getUniqueWords().get(i),this.data.getWordOccurrences().get(i));
            //note: there should be a function to get uniqueWords arraylist in class DataWrangler1 as it is private
        }
    }
    
    public Integer search(String goal){
        try{
            return this.table.get(goal);
        }catch (NoSuchElementException e) {
            System.out.println("No Such Word!");
            return 0;
        }
    }
    
    public void input(String newWord){
        if(this.table.containsKey(newWord)){//if the word exists, add 1 to its occurrence
            Integer tempValue=this.table.get(newWord);
            this.getHashNode(newWord).setValue(tempValue+1);
            //we have to write a new function to get the hash node because get() only return an integer
        }
        else{//if the word doesn't exist, put a new hash node into the table
            this.table.put(newWord,1);
        }
    }
// Note from meeting: Data Wrangler I implementation already has printall() function.
    public void print(){
        //there should be a function to iterate our hash table in HashTableMap.java
    }
}
