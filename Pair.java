// --== CS400 File Header Information ==--
// Name: Liam Jogal
// Email: ljogal@wisc.edu
// Team: KA
// TA: Siddharth Mohan
// Lecturer: Gary Dahl
// Notes to Grader: Used Pair class to store key value pairs

/**
 * Class used to store values in HashList (Linked List implementation)
 * 
 * @author Liam Jogal
 *
 */
public class Pair<KeyType, ValueType> {

	private KeyType key; // key associated with the key-value pair
	private ValueType value; // value associated with the key-value pair

	/**
	 * Sets both key and value into an array
	 * 
	 * @param key
	 * @param value
	 */
	public Pair(KeyType key, ValueType value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Returns the key corresponding to the key-value pair
	 * 
	 * @return key of pair
	 */
	public KeyType getKey() {
		return this.key;
	}

	/**
	 * Returns the value corresponding to the key-value pair
	 * 
	 * @return value of pair
	 */
	public ValueType getValue() {
		return this.value;
	}

}