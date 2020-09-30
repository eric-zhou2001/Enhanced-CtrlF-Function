// --== CS400 File Header Information ==--
// Name: <Sid .S. Khirwar
// Email: skhirwar@wisc.edu
// Team: IG
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

public class HashPair <KeyType, ValueType>{
 
    KeyType key;
    ValueType value;
    HashPair next;
    HashPair(KeyType key,ValueType value){//constructor
        this.key=key;
        this.value=value;
        next=null;
    }
    
}
