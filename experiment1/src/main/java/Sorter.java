package experiment1.src.main.java;

interface Sorter<T extends Comparable<T>> {

	void sort(T[] items);

}
