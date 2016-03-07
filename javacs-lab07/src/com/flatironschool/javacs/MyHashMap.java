/**
 * 
 */
package com.flatironschool.javacs;

import java.util.Map;
import java.util.Set;

/**
 * Implementation of a HashMap using a collection of MyLinearMap and
 * resizing when there are too many entries.
 * 
 * @author downey
 * @param <K>
 * @param <V>
 *
 */
public class MyHashMap<K, V> extends MyBetterMap<K, V> implements Map<K, V> {
	
	// average number of entries per map before we rehash
	private static final double FACTOR = 2.0;

	public MyHashMap(int n) {
		super(n);
	}

	@Override
	public V put(K key, V value) {
		MyLinearMap<K, V> map = findList(key);
		V oldValue = map.put(key, value);
		
		//System.out.println("Put " + key + " in " + map + " size now " + map.size());
		
		if (size() > maps.size() * FACTOR) {
			rehash();
		}
		return oldValue;
	}

	/**
	 * Doubles the number of maps and rehashes the existing entries.
	 */
	private void rehash() {
		// save the existing entries
		Set<Map.Entry<K, V>> entries = entrySet();

		// make more maps
		int newN = maps.size() * 2;
		makeMaps(newN);
		
		//System.out.println("Rehashing, n is now " + newN);
		
		// put the entries into the new map
		for (Map.Entry<K, V> entry: entries) {
			put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Integer> map = new MyHashMap<String, Integer>(2);
		for (int i=0; i<10; i++) {
			map.put(new Integer(i).toString(), i);
		}
		Integer value = map.get("3");
		System.out.println(value);
	}
}
