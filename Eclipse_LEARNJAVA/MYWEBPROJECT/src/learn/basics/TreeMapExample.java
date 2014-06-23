package learn.basics;

import java.util.TreeMap;

public class TreeMapExample {
	
	public static void main(String[] args) {
		System.out.println("Tree Map Example ! \n");
		
		TreeMap <Integer, String> tMap = new TreeMap();
		// Adding the Data into Tree Map
		
		tMap.put(1, "Sun");
		tMap.put(2, "Mon");
		tMap.put(3, "Tues");
		tMap.put(4, "Wed");
		tMap.put(5, "Thu");
		tMap.put(6, "Fri");
		tMap.put(7, "Sat");
		
		// Retrieving All Keys
		System.out.println("keys of tree map:"+tMap.keySet());
		
		//Retrieving All Values
		System.out.println("keys of tree map:"+tMap.values());
		//Rerieving the value from key with key number 5
		
		System.out.println("Key: 5 value: " + tMap.get(5)+ "\n");
		
		//Rerieving the First key and its value
		System.out.println("First key: " + tMap.firstKey() + " Value: " + tMap.get(tMap.firstKey()) + "\n");
		
		//Rerieving the Last key and value
		System.out.println("Last key: " + tMap.lastKey() + " Value: " + tMap.get(tMap.lastKey()) + "\n");
		
		//Removing the first key and value
		System.out.println("Removing first data: " + tMap.remove(tMap.firstKey()));
		System.out.println("Now the tree map Keys: " + tMap.keySet());
		System.out.println("Now the tree map contain: " + tMap.values() + "\n");
		
		//Removing the last key and value
		System.out.println("Removing last data: " + tMap.remove(tMap.lastKey()));
		System.out.println("Now the tree map Keys: " + tMap.keySet());
		System.out.println("Now the tree map contain: " + tMap.values());
		
	}//main
		
		
		
	}//class


