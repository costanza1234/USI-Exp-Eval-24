package experiment1.src.main.java;

public class Main {
    public static void main(String[] args) {
        Integer[] items = { 3, 2, 1 };
        Sorter<Integer> sorter = new SelectionSortGPT<>();
        sorter.sort(items);
        for (Integer item : items) {
            System.out.println(item);
        }
    }
}
