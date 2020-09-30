// --== CS400 File Header Information ==--
// Name: <Sid .S. Khirwar
// Email: skhirwar@wisc.edu
// Team: -
// TA: <name of your team's ta>
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;
public class TestHashTable {

    public static boolean test1() {
        /* test code here */
        HashTableMap<Integer, String> testHashMap1 = new HashTableMap(30);
        
        testHashMap1.put(76, "sid");
        testHashMap1.put(70, "deepak");
        testHashMap1.put(30, "sanjeev");
        
        if (testHashMap1.size() != 3) {
            System.out.println("Test failed!!! Please try again.");
            return false;
        }
        
        testHashMap1.put(20, "rinku");
        testHashMap1.put(10, "max");
        
        if (testHashMap1.size() != 4) {
            System.out.println("Test failed!!! Please try again.");
            return false;
        }
        return true;
    }
    
    public static boolean test2() {
        /* test code here */ 
        HashTableMap<Integer, String> testHashMap2 = new HashTableMap(30);
        
        testHashMap2.put(81, "arsh");
        testHashMap2.put(70, "devansh");
        testHashMap2.put(75, "samir");
        
        testHashMap2.remove(75);
        
        if (testHashMap2.size() != 2) {
            System.out.println("Test failed!!! Please try again.");
            return false;
        }
        return true;
    }
    
    public static boolean test3() {
        /* test code here */ 
        HashTableMap<Integer, String> testHashMap3 = new HashTableMap(30);
        
        testHashMap3.put(3, "john");
        testHashMap3.put(7, "miguel");
        testHashMap3.put(10, "eileen");
        
        boolean result3 = testHashMap3.containsKey(3);
        
        if(result3) {
            System.out.println("Test failed!!! Please try again.");
            return false;
        }
        
        boolean result31 = testHashMap3.containsKey(20);
        
        if(!result31) {
            System.out.println("Test failed!!! Please try again.");
            return false;
        }
        return true;
        
    }
    
    public static boolean test4() { 
        /* test code here */
        HashTableMap<Integer, String> testHashMap4 = new HashTableMap(30);
        String name = "";
        
        testHashMap4.put(18, "pratham");
        testHashMap4.put(12, "rantaig");
        testHashMap4.put(56, "aditya");
        
        try {
            name = testHashMap4.get(18);
        }catch(NoSuchElementException nSEE){
            System.out.println("Test failed!!! Exception noticed. Please try again.");
        }
        
        if(name != "pratham") {
            System.out.println("Test failed!!! Please try again.");
            return false;
        }
        
        try {
            name = testHashMap4.get(15);
            System.out.println("Test failed!!! Please try again.");
            return false;
        }catch(NoSuchElementException nSEE){
            
        }
        return true;
    }
    
    public static boolean test5() {
        /* test code here */
        HashTableMap<Integer, String> testHashMap5 = new HashTableMap(30);
        
        testHashMap5.put(18, "protecc");
        testHashMap5.put(12, "attacc");
        testHashMap5.put(56, "bacc");
        
        testHashMap5.clear();
        
        if (testHashMap5.size() != 0) {
            System.out.println("Test failed!!! Please try again.");
            return false;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        HashTableMap<Integer, String> testHashMap6 = new HashTableMap<Integer, String>();
        
        test1();
        test2();
        test3();
        test4();
        test5();
    }
    
}
