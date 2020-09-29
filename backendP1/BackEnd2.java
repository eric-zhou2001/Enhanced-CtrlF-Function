
public class BackEnd<KeyType> {
	HashTableMap<String, Integer> table;
	String[] keys;
	int size;
	
	/**
	 *  No argument constructor creates starting key array size of 10
	 */
	public BackEnd() {
		table = new HashTableMap<String, Integer>();
		keys = new String[10];
		size = 0;
	}
	
	/**
	 *  Constructor allows for the default size of the array of key to be set
	 * 
	 * @param size - default size of key array
	 */
	public BackEnd(int size) {
		table = new HashTableMap<String, Integer>(size);
		keys = new String[size];
		size = 0;
	}
	
	/**
	 *  Method returns the quantity of a given word's occurrences
	 * 
	 * @param key - the word to search for
	 * @return - the number of occurrences
	 */
	public int search(String key) {
		try {
			return table.get(key);
		} catch (java.util.NoSuchElementException e) {
			System.out.println("Desired word not found");
			return 0;
		}
		
	}
	
	/**
	 *  Method to add one occurrence of the word to the HashTable
	 * 
	 * @param wordToAdd - the word to add
	 */
	public void sendInput(String wordToAdd) {
		if (table.containsKey(wordToAdd)) {
			Integer currentAmount = table.get(wordToAdd);
			currentAmount++;
			table.remove(wordToAdd);
			table.put(wordToAdd, currentAmount);
		} else {
			table.put(wordToAdd, 1);
		}
			
	}
	/**
	 * returns the HashTable as a String of key, occurrence pair separated by a ";"
	 */
	@Override
	public String toString() {
		String outputString = "";
		String currentKey;
		String currentAmount;
		for (int i = 0; i < size; i++) {
			currentKey = keys[i];
			currentAmount = table.get(currentKey).toString();
			outputString += "" + currentKey + ", " + currentAmount + ";";
		}
		return outputString;
	}
}
