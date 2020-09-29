// --== CS400 File Header Information ==--
// Name: Franklin Wang
// Email: fwang263@wisc.edu
// Team: IG
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;
import java.util.LinkedList;

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  private LinkedList<HashNode<KeyType, ValueType>>[] table;
  
  /**
   * Constructor used when user specifies table size.
   * 
   * @param capacity
   */
  public HashTableMap(int capacity) {
    if (capacity < 1) { // create table of default size if given capacity < 1
      this.table = new LinkedList[10];
    }
    else {
      this.table = new LinkedList[capacity];
    }
  }
  
  /**
   * Default constructor. Sets table size to 10 by default.
   */
  public HashTableMap() {// with default capacity = 10
    this.table = new LinkedList[10];
  }
  
  /**
   * Generates hash index for a key-value pair. Hash code is calculated using java's .hashCode()
   * function, then taking the absolute value of the generated hash code.
   * @return hash index for key-value pair
   */
  private int calcHashIndex(KeyType key) {
    int keyHashCode = Math.abs(key.hashCode());
    int hashIndex = keyHashCode % table.length; // mod by the table size to generate hashIndex
    
    return hashIndex;
  }

  /**
   * Hashes data elements into the table.
   */
  private void hashIntoTable(KeyType key, ValueType value) {
    int hashIndex = calcHashIndex(key);
    HashNode<KeyType, ValueType> newHashNode = new HashNode<KeyType, ValueType>(key, value);
    
    if (table[hashIndex] == null) {
      table[hashIndex] = new LinkedList<HashNode<KeyType, ValueType>>();
    }
    
    table[hashIndex].add(newHashNode);
  }

  /**
   * Adds new element to array. Returns true when successfully storing a new pair, false when key 
   * already exists in table. Calls hashCode() method to calculate hash code of new value. Stores key 
   * and value inside of array element. 
   * 
   * @return true if successfully storing new pair, false when pair exists already
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    
    if (containsKey(key)) {
      return false;
    }
    else {
      hashIntoTable(key, value);
      
      // grow hash table if load factor >= 80%
      double size = size();
      double capacity = table.length;
      double loadFactor = size/capacity;
      if (loadFactor >= .8) {
        growTable();
      }
      
      return true;
    }
  }

  /**
   * Grows the table when load factor >= 80%. Creates a new table with double the capacity of the old
   * table. Rehashes each value, copying into the new table. Finally sets old table reference to new
   * table instance.
   */
  private void growTable() {
    LinkedList<HashNode<KeyType, ValueType>>[] newTable = new LinkedList[table.length * 2];
    
    for (int i = 0; i < table.length; ++i) {
      if (table[i] != null) {
        for (int j = 0; j < table[i].size(); ++j) { // rehash every linkedNode
          HashNode curNode = table[i].get(j);
          KeyType curNodeKey = (KeyType) curNode.getKey();
          
          int newIndex = calcHashIndex(curNodeKey); // calculate new hash index
          
          if (newTable[newIndex] == null) {
            newTable[newIndex] = new LinkedList<HashNode<KeyType, ValueType>>();
          }
          
          newTable[newIndex].add(curNode);
        }
      }
    }
    
    table = newTable;
  }

  /**
   * Finds value using key (call hash index function) and returns the value corresponding to that
   * particular key.
   * 
   * @return value corresponding to the key
   * @throws NoSuchElementException if key does not exist within table
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    int hashIndex = calcHashIndex(key);
    
    if (containsKey(key)) {
      return loopToNode(key).getValue();
    }
    else {
      throw new NoSuchElementException();
    }
    
  }
  
  private HashNode<KeyType, ValueType> loopToNode(KeyType key) {
    int hashIndex = calcHashIndex(key);
    
    if (table[hashIndex] == null) { // return null when table doesn't exist
      return null;
    }
    
    for (int i = 0; i < table[hashIndex].size(); ++i) {
      HashNode<KeyType, ValueType> curNode = table[hashIndex].get(i);
      
      if (curNode.getKey().equals(key)) {
        return curNode;
      }
    }
    
    return null;
  }

  /**
   * Returns number of key-value pairs stored in collection (NOT SIZE OF TABLE).
   * Loops through every array element, checking for a non-null value. If the value is not null, 
   * get size of LinkedList, which is number of total nodes in the list. Adds this number to counter
   * of total nodes in the entire list.
   * 
   * @return total number of key-value pairs (number of HashNodes)
   */
  @Override
  public int size() {
    int numNodes = 0;
    
    for (int i = 0; i < table.length; ++i) { // loop through every array element
      if (table[i] == null) {
        continue;
      }
      else {
        numNodes += table[i].size(); // add number of linkedNodes
      }
    }
    
    return numNodes;
  }
  
  /**
   * Returns the number of buckets of the table.
   * 
   * @return total number of buckets in the table.
   */
  public int capacity() {
    return table.length;
  }

  /**
   * Checks to see whether the value exists in the array for a certain key. (call hash index function)
   * 
   * @return true when the value exists in array, false otherwise
   */
  @Override
  public boolean containsKey(KeyType key) {
    int hashIndex = calcHashIndex(key);
    
    if (table[hashIndex] == null) {
      return false; // since LinkedList doesn't exist, key doesn't either
    }
    
    HashNode<KeyType, ValueType> curNode = loopToNode(key);
    
    return curNode != null; // return true if curNode is not null, return false if curNode == null
  }
  

  /**
   * Removes a key-value pair based on the specified key. Returns the value of the node if it exists.
   * Returns null if node does not exist.
   * 
   * @return HashNode containing the key-value pair, null otherwise
   */
  @Override
  public ValueType remove(KeyType key) {
    HashNode<KeyType, ValueType> curNode = loopToNode(key); // get current node
    
    if (curNode == null || curNode.getKey() == null || curNode.getValue() == null) {
      return null;
    }
    
    ValueType nodeVal = curNode.getValue(); // get value of key
    
    int hashIndex = calcHashIndex(key);
    if (table[hashIndex].removeFirstOccurrence(curNode)) {
      return nodeVal;
    }
      
    return null;
  }

  /**
   * Clears every data element within the table. Sets table to a blank array of LinkedLists.
   */
  @Override
  public void clear() {
    int curCapacity = table.length;
    
    table = new LinkedList[curCapacity];
  }
  
  /**
   * Increments a node after a duplicate occurrence has been found in file.
   * 
   * @return The new number of occurrences of a word
   */
  public int incrementNode() {
    return 0;
  }
  
  
  /**
   * Prints all key-value pairs in this format: key : "Number of occurrences " + value
   * 
   */
  public void printContents() {
    
  }
  
}
