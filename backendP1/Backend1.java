// --== CS400 File Header Information ==--
// Name: Pintao Zou
// Email: pzou4@wisc.edu
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
            for(int i=0;i<data.getHashNodeList().size();i++){
                if(data.getHashNodeList().get(i).getKey().equals(newWord)){//update the hash node list in data process
                    data.getHashNodeList().get(i).setValue(tempValue+1);
                    break;
                }
            }
            this.table.loopToNode(newWord).setValue(tempValue+1);
            //the loopToNode function should be public
        }
        else{//if the word doesn't exist, put a new hash node into the table
            this.table.put(newWord,1);
            data.getHashNodeList().add(new HashNode<String, Integer>(newWord, 1));//update the hash node list in data process
        }
    }
// Note from meeting: Data Wrangler I implementation already has printall() function.
}
