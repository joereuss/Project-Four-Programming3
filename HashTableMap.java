// --== CS400 File Header Information ==--
// Name: Liam Jogal
// Email: ljogal@wisc.edu
// Team: KA
// TA: Siddharth Mohan
// Lecturer: Gary Dahl
// Notes to Grader: Used Pair class to store key value pairs, display method was developed by back end devloper Joe Reuss. 
// Also for the readAndInputFile my implementation uses a DataWranlger class I made as opposed to some of my team members 
// used code from the back end developers that does the same thing but a different implementation


import java.util.LinkedList;
import java.util.NoSuchElementException;


/**
 * HashTable implementation that uses the MapADT interface and an array of
 * LinkedLists for chaining.
 * 
 * @author Liam Jogal
 *
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

	private int size; // size of the hash map
	private int capacity; // capacity of array

	private LinkedList<Pair<KeyType, ValueType>>[] table; // hashtable

	
	public int getCapacity() {
		return this.capacity;
	}
	
	public LinkedList[] getTable() {
		return this.table;
	}
	
	
	/**
	 * Constructor sets capacity of hashtester to users preference
	 * 
	 * @param capacity
	 */
	public HashTableMap(int capacity) {
		this.size = 0;
		this.capacity = capacity;
		this.table = new LinkedList[capacity]; // table initialized with capacity
	}

	/**
	 * No argument constructor that gives a default capacity of 10
	 */
	public HashTableMap() {
		this.size = 0;
		this.capacity = 10;
		this.table = new LinkedList[capacity];
	}

	/**
	 * Resizes and rehashes the hashtester once the load capacity becomes greater
	 * than or equal to 80%
	 */
	private void resize() {
		// create copy of hash table and double capacity
		LinkedList<Pair<KeyType, ValueType>>[] temp = table;
		capacity *= 2;
		table = new LinkedList[capacity];
		// go through each index of array, rehash each value to large hashtable
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] != null) {
				for (int j = 0; j < temp[i].size(); j++) {
					rehashPut(temp[i].get(j));

				}
			}
		}

	}

	/**
	 * Adds values from old table into bigger hash table with out increasing size
	 * 
	 * @param add - key value pair to add
	 */
	private void rehashPut(Pair<KeyType, ValueType> add) {

		// Compute hashcode for index
		if (this.table[getIndx(add.getKey())] == null) {
			this.table[getIndx(add.getKey())] = new LinkedList<>();
		}

		this.table[getIndx(add.getKey())].add(add);

	}

	/**
	 * Method used to place a value into the function with this index made by the
	 * keys hash code
	 * 
	 * @param key   - The key used to compute the index
	 * @param value - the value associated with the key
	 */
	@Override
	public boolean put(KeyType key, ValueType value) {

		// Check to see if key already in hashtester

		if (containsKey(key))
			return false;

		Pair<KeyType, ValueType> add = new Pair<KeyType, ValueType>(key, value);
		// Next check the size and adjust if needed

		// Compute hashcode for index
		if (this.table[getIndx(key)] == null) {
			this.table[getIndx(key)] = new LinkedList<>();
		}
		// adds value and resizes/rehashes if neccessary
		this.table[getIndx(key)].add(add);
		this.size++;
		if (((double) this.size / (double) this.capacity) >= 0.8) {
			resize();
		}
		return true;
	}

	/**
	 * Used to retrieve a value based off a key
	 * 
	 * @param key - KeyType used to search for a particular value
	 * @return the value associated with the key given
	 * @throws NoSuchElementException if there is no Pair associated with the key
	 */
	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		// thows exception if there is no linkedlist at index
		if (table[getIndx(key)] == null) {
			throw new NoSuchElementException();
		}
		for (int i = 0; i < table[getIndx(key)].size(); i++) {
			Pair<KeyType, ValueType> check = table[getIndx(key)].get(i);
			// returns value if the keys are the same
			if (check.getKey().equals(key)) {
				return check.getValue();
			}
		}
		// throws exception if key not in hashtable
		throw new NoSuchElementException();
	}

	/**
	 * returns size of hashtable
	 * 
	 * @return number of values in the hashtable
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Returns whether or not the hashtester contains a key
	 * 
	 * @param key - given key used to check if this value is the hashtester
	 * @return true or false depending on if there is a value in the hashtable
	 *         associated with the key
	 */
	@Override
	public boolean containsKey(KeyType key) {

		// if no linked list at index return false
		if (table[getIndx(key)] == null) {
			return false;
		}
		// if there is a list at the index check if key is in the list
		for (int i = 0; i < table[getIndx(key)].size(); i++) {
			if (table[getIndx(key)].get(i).getKey().equals(key)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Method used to remove a node and return a valuetype associated with the key
	 * 
	 * @param key - key used to search for the node to remove
	 * @return a ValueType associated with the key provided. Returns null if key not
	 *         found
	 */
	@Override
	public ValueType remove(KeyType key) {

		// First check whether or not there is a value at the key's index
		if (table[getIndx(key)] == null) {
			return null;
		}

		// look through each element in linkedlist and remove if keys are the same
		for (int i = 0; i < table[getIndx(key)].size(); i++) {
			Pair<KeyType, ValueType> check = table[getIndx(key)].get(i);
			if (check.getKey().equals(key)) {
				table[getIndx(key)].remove(i);
				size--;
				return check.getValue();
			}
		}

		return null;

	}

	/**
	 * Used to retrieve the indx of a given keytype
	 * 
	 * @param key - used to compute the indx using the keys hashcode
	 * @return the indx associated witht he given key
	 */
	private int getIndx(KeyType key) {
		int hashCd = key.hashCode();
		// takes absolute value of key and modulo by capacity to get an index
		if (hashCd < 0) {
			hashCd = -hashCd;
		}
		return hashCd % capacity;
	}

	/**
	 * Clears the hash tester of all its values and sets the size to 0
	 */
	@Override
	public void clear() {
		table = new LinkedList[capacity];
		size = 0;
	}




}