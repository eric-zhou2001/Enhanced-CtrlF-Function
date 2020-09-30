// --== CS400 File Header Information ==--
// Name: <Sid .S. Khirwar
// Email: skhirwar@wisc.edu
// Team: IG
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType>{

    private int capacity;
    private int size;
    private LinkedList<HashPair<KeyType,ValueType>> hashTable[];
    
    public HashTableMap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        hashTable = new LinkedList[capacity];
       // for(int i = 0; i < capacity; i++) {
       //     hashTable[i].add(null);
       // }
    }
    
    public HashTableMap() {
        this.capacity = 10;
        this.size = 0;
        hashTable = new LinkedList[capacity];
       // for(int i = 0; i < capacity; i++) {
        //    hashTable[i].add(null);
       // }
    }
    
    int hashCode(KeyType key) {
        String hashString = "" + key;
        int hashIntermediate = hashString.hashCode();
        int hashNo = java.lang.Math.abs(hashIntermediate % capacity);
        return hashNo;
    }
    
    private void doublerTable() {
        if(size >= (0.8 * capacity)) {
            LinkedList<HashPair<KeyType,ValueType>> tempHashTable[] = this.hashTable;
            LinkedList<HashPair<KeyType,ValueType>> tempHashTable2[] = new LinkedList[2*capacity];
            
            this.capacity = 2*(this.capacity);
            this.hashTable = tempHashTable2;
            
           // tempSize= size;
            this.size = 0;
            //size = tempSize;
            //for(int i = 0; i < capacity; i++) {
            //    hashTable[i].add(null);
          //  }
            for(int i = 0; i < tempHashTable.length; i++) {
                if(tempHashTable[i] == null) {
                    hashTable[i] = null;
                }else {
                    for( HashPair<KeyType, ValueType> leadingPair : tempHashTable[i]) {
                        while (leadingPair != null) 
                        {   
                             
                            put(leadingPair.key, leadingPair.value);
                            leadingPair = leadingPair.next;
                        } 
                    }
               }
            }
        }
    }
    
    public boolean put(KeyType key, ValueType value){
        int index = hashCode(key);
        if (hashTable[index] == null)
        {
            hashTable[index]= new LinkedList<HashPair<KeyType, ValueType>>();
            HashPair<KeyType, ValueType> head = new HashPair<KeyType, ValueType>(key, value); 
            head.next = null;
            hashTable[index].add(head);
        }
        else
        {
        HashPair<KeyType, ValueType> head = hashTable[index].getFirst();
        while(head != null) {
            if(head.key.equals(key)) {
                return false;
            }
            head = head.next;
        }
        head = hashTable[index].getFirst();
        HashPair<KeyType, ValueType> newPair = new HashPair<KeyType, ValueType>(key, value); 
        newPair.next = head; 
        hashTable[index].addFirst(newPair);
        }
        size++;
        doublerTable();
        return true;
    }
    
    public ValueType get(KeyType key) {
        int index = hashCode(key);
        if(hashTable[index] == null) {
            throw new NoSuchElementException(); 
        }
        else {
            HashPair<KeyType, ValueType> head = hashTable[index].getFirst();
            while (head != null) 
            { 
                if (head.key.equals(key)) { 
                    return head.value; 
                }
                head = head.next; 
            } 
        }
    
        return null;
    }
    
    public boolean containsKey(KeyType key) {
        int index = hashCode(key);
        if(hashTable[index] == null) {
            return false; 
        }
        else {
            HashPair<KeyType, ValueType> head = hashTable[index].getFirst();
            while (head != null) 
            { 
                if (head.key.equals(key)) { 
                    return true; 
                }
                head = head.next; 
            } 
        }
        return false;
    }
    
    public ValueType remove(KeyType key) {
       /* ValueType temp = null;
        int index = hashCode(key);
        if(hashTable[index] == null) {
            return null;
        }else {
            HashPair<KeyType, ValueType> head = hashTable[index].getFirst();
            HashPair<KeyType, ValueType> prevPair = null;
            while(head != null) {
                if(head.key.equals(key)) {
                    break;
                }
                else {
                    prevPair = head;
                    head = head.next;
                }
            }
            if(head == null) {
                return null;
           }
            if(prevPair != null) {
                prevPair.next = head.next;
                temp = head.value;
                head.key = null;
                head.value = null;
           }
            else {
                System.out.println(index);
                System.out.println(head);
                System.out.println(head.next);
                if (head.next == null) {
                    temp = head.value;
                    head.key = null;
                    head.value = null;
               }else {
                   hashTable[index].set(index, head.next);
                   temp = head.value;
                   head.key = null;
                   head.value = null;
                   
                }
            }
           size--;
           return temp;
        }*/
        if(!containsKey(key)) {
            return null;
        }
        
        ValueType val1 = null;
        
        for(int i = 0; i < capacity; i++) {
            if(hashTable[i] != null) {
                for(int j = 0; j < hashTable[i].size();j++){
                    HashPair<KeyType, ValueType> leadingPair = (HashPair) hashTable[i].get(j);
                    if(leadingPair.key == key) {
                        if(leadingPair.value != null) {
                            val1 = leadingPair.value;
                            leadingPair.key = null;
                            leadingPair.value = null;
                        }
                        else {
                            leadingPair = null;
                        }
                    }
                }
            }
        }
        size--;
        return val1;
    }
    
    public void clear() {
        hashTable = new LinkedList[capacity];
        size = 0;  
}
    
    public int size() {
        return size;
    }
    
    public int capacity() {
        return capacity;
    }
    
    public LinkedList<HashPair<KeyType,ValueType>>[] hashTable(){
        return hashTable;
    }
    
}
