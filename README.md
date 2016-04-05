# cs-implementing-the-map-interface-lab

## Learning goals 

1.  Write a simple implementation of the `Map` interface.


## Overview

In the next few labs, we present several implementations of the `Map` interface.  One of them, like the `HashMap` provided by Java, is based on a **hash table**, which is arguably the most magical data structure ever invented.  Another, which is similar to `TreeMap`, is not quite as magical, but it has the added capability that it can iterate its elements in order.

You will have a chance to implement these data structures, and then we will analyze their performance.

But before we can explain hash tables, we'll start with a simple implementation of a `Map` using a `List` of key-value pairs.


## Implementing `MyLinearMap`

As usual, we provide starter code and you will fill in the missing methods.  Here's the beginning of the `MyLinearMap` class definition:

```java
public class MyLinearMap<K, V> implements Map<K, V> {

	private List<Entry> entries = new ArrayList<Entry>();
```

This class uses two type parameters, `K`, which is the type of the keys, and `V`, which is the type of the values.  `MyLinearMap` implements `Map`, which means it has to provide the methods in the `Map` interface.

A `MyLinearMap` object has a single instance variable, `entries`, which is an `ArrayList` of `Entry` objects.  Each `Entry` contains a key-value pair.  Here is the definition:

```java
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
	}
```

There's not much to it; an `Entry` is just a container for a key and a value.
This definition is nested inside `MyLinearList`, so it uses the same type parameters, `K` and `V`.

That's all you need to do the lab, so let's get started.

## Instructions

When you check out the repository for this lab, you should find a file structure similar to what you saw in previous labs.  The top level directory contains `CONTRIBUTING.md`, `LICENSE.md`, `README.md`, and the directory with the code for this lab, `javacs-lab07`.

In the subdirectory `javacs-lab07/src/com/flatironschool/javacs` you'll find the source files for this lab:

    *  `MyLinearMap.java` contains starter code for the first part of the lab.
    
    *  `MyLinearMapTest.java` contains the unit tests for `MyLinearMap`.

And in `javacs-lab07`, you'll find the Ant build file `build.xml`.

*  In `javacs-lab07`, run `ant build` to compile the source files.  Then run `ant test`, which runs `MyLinearMapTest`.  Several tests should fail, because you have some work to do!

*  First, fill in the body of `findEntry`.  This is a helper function that is not part of the `Map` interface, but once you get it working, you can use it for several methods.  Given a target key, it should search through the entries and return the entry that contains the target (as a key, not a value) or `null` if it's not there.  Notice that we have provided an `equals` method that compares two keys and handles `null` correctly.

    You can run `ant test` again, but even if your `findEntry` is correct, the tests won't pass because `put` is not complete.

*  Fill in `put`.  You should read the documentation of [`Map.put`](https://docs.oracle.com/javase/7/docs/api/java/util/Map.html#put(K,%20V)) so you know what it is supposed to do.  You might want to start with a version of `put` that always adds a new entry and does not modify an existing entry; that way you can test the simple case first.  Or if you feel more confident, you can write the whole thing at once.

    Once you've got `put` working, the test for `containsKey` should pass.

*  Read the documentation of [`Map.get`](https://docs.oracle.com/javase/7/docs/api/java/util/Map.html#get(java.lang.Object)) and then fill in the method.  Run the tests again.

*  Finally, read the documentation of [`Map.remove`](https://docs.oracle.com/javase/7/docs/api/java/util/Map.html#remove(java.lang.Object)) and then fill in the method.

    At this point, all tests should pass.  Congratulations!
    
In the next lesson, we'll present our solutions, analyze the performance of the core `Map` methods, and introduce a more efficient implementation.


