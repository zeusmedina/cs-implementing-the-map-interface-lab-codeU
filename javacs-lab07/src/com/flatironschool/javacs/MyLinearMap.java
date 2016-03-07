/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of a Map using a List of entries, so most
 * operations are linear time.
 * 
 * @author downey
 * @param <K>
 * @param <V>
 *
 */
public class MyLinearMap<K, V> implements Map<K, V> {

	public class Entry implements Map.Entry<K, V> {
		private K key;
		private V value;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		@Override
		public K getKey() {
			return key;
		}
		@Override
		public V getValue() {
			return value;
		}
		@Override
		public V setValue(V newValue) {
			value = newValue;
			return value;
		}
	}
	
	private ArrayList<Entry> entries = new ArrayList<Entry>();
	
	@Override
	public void clear() {
		entries.clear();
	}

	@Override
	public boolean containsKey(Object target) {
		return findEntry(target) != null;
	}

	@Override
	public boolean containsValue(Object target) {
		for (Entry entry: entries) {
			if (equals(target, entry.getValue())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param target
	 */
	private Entry findEntry(Object target) {
		for (Entry entry: entries) {
			if (equals(target, entry.getKey())) {
				return entry;
			}
		}
		return null;
	}

	private boolean equals(Object target, Object obj) {
		if (target == null) {
			return obj == null;
		}
		return target.equals(obj);
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
		
		// Now we can add the entries (with just one more typecast!) 
		set2.addAll((Collection<? extends Map.Entry<K, V>>) entries);
		return set2;
	}

	@Override
	public V get(Object key) {
		Entry entry = findEntry(key);
		if (entry == null) {
			return null;
		}
		return entry.getValue();
	}

	@Override
	public boolean isEmpty() {
		return entries.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new HashSet<K>();
		for (Entry entry: entries) {
			set.add(entry.getKey());
		}
		return set;
	}

	@Override
	public V put(K key, V value) {
		Entry entry = findEntry(key);
		if (entry == null) {
			entries.add(new Entry(key, value));
			return null;
		} else {
			V oldValue = entry.getValue();
			entry.setValue(value);
			return oldValue;
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		for (Map.Entry<? extends K, ? extends V> entry: map.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public V remove(Object key) {
		Entry entry = findEntry(key);
		if (entry == null) {
			return null;
		} else {
			V value = entry.getValue();
			entries.remove(entry);
			return value;
		}
	}

	@Override
	public int size() {
		return entries.size();
	}

	@Override
	public Collection<V> values() {
		Set<V> set = new HashSet<V>();
		for (Entry entry: entries) {
			set.add(entry.getValue());
		}
		return set;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Integer> map = new MyLinearMap<String, Integer>();
		map.put("Word", 1);
		Integer value = map.get("Word");
		System.out.println(value);
		
		for (Map.Entry entry: map.entrySet()) {
			System.out.println(entry.getKey() + ", " + entry.getValue());
		}
	}
}
