/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of a Map using a collection of MyLinearMap, and
 * using `hashCode` to determine which map each key should go in.
 * 
 * @author downey
 * @param <K>
 * @param <V>
 *
 */
public class MyBetterMap<K, V> implements Map<K, V> {
	
	// MyBetterMap uses a collection of MyLinearMap
	protected List<MyLinearMap<K, V>> maps;
	
	/**
	 * Initialize the map with `n` sub-maps.
	 * 
	 * @param n
	 */
	public MyBetterMap(int n) {
		makeMaps(n);
	}

	/**
	 * Makes a collection of `n` MyLinearMap
	 * 
	 * @param n
	 */
	protected void makeMaps(int n) {
		maps = new ArrayList<MyLinearMap<K, V>>(n);
		for (int i=0; i<n; i++) {
			maps.add(new MyLinearMap<K, V>());
		}
	}
		
	@Override
	public void clear() {
		// clear the sub-maps
		for (int i=0; i<maps.size(); i++) {
			maps.get(i).clear();
		}
	}

	/**
	 * Uses the hashCode to find the list that would/should contain the target.
	 * 
	 * @param target
	 * @return
	 */
	protected MyLinearMap<K, V> findList(Object target) {
		int index = target==null ? 0 : target.hashCode() % maps.size();
		return maps.get(index);
	}	

	@Override
	public boolean containsKey(Object target) {
		// to find a key, we only have to search one map
		MyLinearMap<K, V> map = findList(target);
		return map.containsKey(target);
	}

	@Override
	public boolean containsValue(Object target) {
		// to find a value, we have to search all map
		for (MyLinearMap<K, V> map: maps) {
			if (map.containsValue(target)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		// We can't instantiate Set<java.util.Map.Entry<K, V>>, so we have to instantiate Set<Object>
		// and then typecast it.
		@SuppressWarnings("unchecked")
		Set<? extends Map.Entry<K, V>> set = (Set<? extends Map.Entry<K, V>>) new HashSet<Object>();

		// The only way I could make it work is to typecast in two steps.  Not sure if there's
		// a better way, but this will do for now.
		@SuppressWarnings("unchecked")
		Set<java.util.Map.Entry<K, V>> set2 = (Set<Map.Entry<K, V>>) set;
		
		for (MyLinearMap<K, V> map: maps) {
			set2.addAll((Collection<? extends Map.Entry<K, V>>) map.entrySet());
		}
		return set2;
	}

	@Override
	public V get(Object key) {
		MyLinearMap<K, V> map = findList(key);
		return map.get(key);
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Set<K> keySet() {
		// add up the keySets from the sub-maps
		Set<K> set = new HashSet<K>();
		for (MyLinearMap<K, V> map: maps) {
			set.addAll(map.keySet());
		}
		return set;
	}

	@Override
	public V put(K key, V value) {
		MyLinearMap<K, V> map = findList(key);
		V oldValue = map.put(key, value);
		return oldValue;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		for (Map.Entry<? extends K, ? extends V> entry: map.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public V remove(Object key) {
		MyLinearMap<K, V> map = findList(key);
		return map.remove(key);
	}

	@Override
	public int size() {
		// add up the sizes of the sub-maps
		int total = 0;
		for (MyLinearMap<K, V> map: maps) {
			total += map.size();
		}
		return total;
	}

	@Override
	public Collection<V> values() {
		// add up the valueSets from the sub-maps
		Set<V> set = new HashSet<V>();
		for (MyLinearMap<K, V> map: maps) {
			set.addAll(map.values());
		}
		return set;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Integer> map = new MyBetterMap<String, Integer>(4);
		map.put("Word", 1);
		Integer value = map.get("Word");
		System.out.println(value);
	}
}
