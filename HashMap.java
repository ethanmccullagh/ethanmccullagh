//Name: Ethan McCullagh
//V#: V00923049

import java.util.*;

public class HashMap<K extends Comparable<K>, V> implements Map<K,V> {

	private long getLoops;
	private long putLoops;
	private int  tableSize;

	private List<List<Entry<K,V>>> 	table;
	private int	count;

	public HashMap() {
		tableSize = 1000003; // prime number
		table = new ArrayList<List<Entry<K,V>>>(tableSize);

		// initializes table as a list of empty lists
		for (int i = 0; i < tableSize; i++) {
			table.add(new LinkedList<Entry<K,V>>());
		}
		
		count = 0;

		resetGetLoops();
		resetPutLoops();
	}

	public long getGetLoopCount() {
		return getLoops;
	}

	public long getPutLoopCount() {
		return putLoops;
	}

	public void resetGetLoops() {
		getLoops = 0;
	}
	public void resetPutLoops() {
		putLoops = 0;
	}

	public boolean containsKey(K key) {
		// gets the index in the table this key should be in
		int index = Math.abs(key.hashCode()) % tableSize;

		// TODO... complete this method

		boolean result = false;
		List<Entry<K, V>> list = table.get(index);
		Iterator<Entry<K, V>> iter = list.iterator();


		if (!list.isEmpty()){
			while(iter.hasNext()){
				if(iter.next().getKey() == key){
					result = true;
				}
			}
		}


		return result;
	}

	public V get (K key) throws KeyNotFoundException {
		// gets the index in the table this key should be in
		int index = Math.abs(key.hashCode()) % tableSize;

		// TODO... complete this method

		List<Entry<K, V>> list = table.get(index);
		Iterator<Entry<K, V>> iter = list.iterator();
		boolean keyNotFound = true;
		Entry<K, V> entry;

		V result = null;

		while(keyNotFound && iter.hasNext()){
			entry = iter.next();

			if(entry.getKey().compareTo(key) == 0){
				result = entry.getValue();
				keyNotFound = false;
			}
		}

		if (keyNotFound){
			throw new KeyNotFoundException();
		}

		return result;
	}

	public List<Entry<K,V> >	entryList() {
		List <Entry<K,V>> l = new LinkedList<Entry<K,V>>();
		// TODO... complete this method

		Iterator<List<Entry<K,V>>> iterTable = table.iterator();

		List<Entry<K, V>> list;
		Iterator<Entry<K, V>> iterList;

		while (iterTable.hasNext()){

			list = iterTable.next();
			iterList = list.iterator();

			while (iterList.hasNext()){
				l.add(iterList.next());
			}
		}


		// Tip: you will need to iterate through each index in the table (and each index holds a list)
		//      you will THEN need to iterate through each element in the linked list chain at a 
		//      specific index and add each element to l


		return l;
	}

	public void put (K key, V value){
		// gets the index in the table this key should be in
		int index = Math.abs(key.hashCode())%tableSize;
		
		// TODO... complete this method

		List<Entry<K, V>> list = table.get(index);
		Iterator<Entry<K, V>> iter = list.iterator();

		if (list.isEmpty()){
			list.add(new Entry<K, V>(key, value));
			count++;
		} else {

			boolean keyNotFound = true;
			Entry<K, V> entry;

			while(keyNotFound == true && iter.hasNext()){

				entry = iter.next();

				if (entry.getKey().compareTo(key) == 0){
					keyNotFound = false;
					iter.remove();
					list.add(new Entry<K, V>(key, value));
				}

			}

			if(keyNotFound){
				list.add(new Entry<K, V>(key, value));
				count++;
			}


		}
		
		// if key is found, update value.  if key is not found add a new Entry with key,value
		// The tester expects that you will add the newest Entry to the END of the list


	}

	public int size() {
		return count;
	}

	public void clear() {
		table.clear();
		count = 0;
	}

}
