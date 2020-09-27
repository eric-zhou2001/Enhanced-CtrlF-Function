// --== CS400 File Header Information ==--
// Name: Franklin Wang
// Email: fwang263@wisc.edu
// Team: IG
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

public class HashNode<KeyType, ValueType> {
  private KeyType key;
  private ValueType value;
  
  public HashNode(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
  }

  public KeyType getKey() {
    return key;
  }

  public void setKey(KeyType key) {
    this.key = key;
  }

  public ValueType getValue() {
    return value;
  }

  public void setValue(ValueType value) {
    this.value = value;
  }
}   
