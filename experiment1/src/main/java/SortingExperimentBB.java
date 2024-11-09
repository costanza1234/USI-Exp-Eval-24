package experiment1.src.main.java;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

/*
 * Hypothesis 1: Independent variable = sorting algorithm used.
 * Dependent variable = time taken to sort the array.
 * Counfounding variables = size of the array, sortedness level of the array
 * 
 * Hypothesis 2: Independent variable = sortedness level of the array.
 * Dependent variable = time taken to sort the array.
 * Counfounding variables = sorting algorithm used, size of the array,
 * sortedness level of the array
 * 
 * Hypothesis 3: Independent variable = size of the array.
 * Dependent variable = time taken to sort the array.
 * Counfounding variables = sorting algorithm used, size of the array,
 * sortedness level of the array
 */
public class SortingExperimentBB {
    /*
     * Function to measure the execution time of a given sorting algorithm on a
     * given dataset.
     * The dataset must be of a generic type as all the sorting algorithms can be
     * used on generics types
     * that extend then the Comparable type
     */
    public static <T extends Comparable<T>> long measureExecutionTime(Sorter<T> sorter, T[] array) {
        // First we copy the array to avoid modifying the original dataset
        T[] copy = Arrays.copyOf(array, array.length);
        // Start measuring the time
        long startTime = System.nanoTime();
        // Sort the array using the given sorting algorithm
        // They all have the same method 'sort'
        sorter.sort(copy);
        // Return the time taken to sort the array
        return System.nanoTime() - startTime;
    }

    /*
     * Function to generate a random dataset into a given array of a generic type
     */
    public static <T> void generateRandomData(T[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            if (array instanceof Integer[]) {
                array[i] = (T) Integer.valueOf(random.nextInt());
            } else if (array instanceof Long[]) {
                array[i] = (T) Long.valueOf(random.nextLong());
            } else if (array instanceof Float[]) {
                array[i] = (T) Float.valueOf(random.nextFloat());
            } else if (array instanceof Double[]) {
                array[i] = (T) Double.valueOf(random.nextDouble());
            }
        }
    }

    /*
     * Function to generate a dataset of a generic type with values in descending
     * order
     */
    public static <T> void generateReversedData(T[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array instanceof Integer[]) {
                array[i] = (T) Integer.valueOf(array.length - i);
            } else if (array instanceof Long[]) {
                array[i] = (T) Long.valueOf(array.length - i);
            } else if (array instanceof Float[]) {
                array[i] = (T) Float.valueOf(array.length - i);
            } else if (array instanceof Double[]) {
                array[i] = (T) Double.valueOf(array.length - i);
            }
        }
    }

    /*
     * Function to generate a dataset of the first k elements sorted and the rest of
     * the elements random
     */
    public static <T> void generateFirstKSortedData(T[] array, int k) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            if (i < k) {
                if (array instanceof Integer[]) {
                    array[i] = (T) Integer.valueOf(i);
                } else if (array instanceof Long[]) {
                    array[i] = (T) Long.valueOf(i);
                } else if (array instanceof Float[]) {
                    array[i] = (T) Float.valueOf(i);
                } else if (array instanceof Double[]) {
                    array[i] = (T) Double.valueOf(i);
                }
            } else {
                if (array instanceof Integer[]) {
                    array[i] = (T) Integer.valueOf(random.nextInt());
                } else if (array instanceof Long[]) {
                    array[i] = (T) Long.valueOf(random.nextLong());
                } else if (array instanceof Float[]) {
                    array[i] = (T) Float.valueOf(random.nextFloat());
                } else if (array instanceof Double[]) {
                    array[i] = (T) Double.valueOf(random.nextDouble());
                }
            }
        }
    }

    /*
     * Function to generate a dataset of the last k elements sorted and the rest of
     * the elements random
     */
    public static <T> void generateLastKSortedData(T[] array, int k) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            if (i >= array.length - k) {
                if (array instanceof Integer[]) {
                    array[i] = (T) Integer.valueOf(i);
                } else if (array instanceof Long[]) {
                    array[i] = (T) Long.valueOf(i);
                } else if (array instanceof Float[]) {
                    array[i] = (T) Float.valueOf(i);
                } else if (array instanceof Double[]) {
                    array[i] = (T) Double.valueOf(i);
                }
            } else {
                if (array instanceof Integer[]) {
                    array[i] = (T) Integer.valueOf(random.nextInt());
                } else if (array instanceof Long[]) {
                    array[i] = (T) Long.valueOf(random.nextLong());
                } else if (array instanceof Float[]) {
                    array[i] = (T) Float.valueOf(random.nextFloat());
                } else if (array instanceof Double[]) {
                    array[i] = (T) Double.valueOf(random.nextDouble());
                }
            }
        }
    }

    /*
     * We need to run for all the sorting algorithms, all possible types of
     * datasets, all different sizes of datasets and all different sortedness levels
     * of the datasets
     */
    public static void main(String[] args) {
        // Here we initialize an array of all the sorting algorithms we want to test
        // They are all extending the type Sorter
        Sorter[] algorithms = {
                new BubbleSortUntilNoChange(),
                new BubbleSortWhileNeeded(),
                new QuickSortGPT(),
                new SelectionSortGPT()
        };

        // First we initialize the different types of datasets
        // the possible sizes are 100, 1000, 10000, 100000

        // Integer datasets
        Integer[] randomData100 = new Integer[100];
        Integer[] randomData1000 = new Integer[1000];
        Integer[] randomData10000 = new Integer[10000];
        // Integer[] randomData100000 = new Integer[100000];
        // Array to contain all the datasets
        // Integer[][] datasetsBySize = { randomData100, randomData1000,
        // randomData10000, randomData100000 };
        Integer[][] datasetsBySize = { randomData100, randomData1000, randomData10000 };

        // Long datasets
        Long[] randomData100L = new Long[100];
        Long[] randomData1000L = new Long[1000];
        Long[] randomData10000L = new Long[10000];
        // Long[] randomData100000L = new Long[100000];
        // Array to contain all the datasets
        // Long[][] datasetsBySizeL = { randomData100L, randomData1000L,
        // randomData10000L, randomData100000L };
        Long[][] datasetsBySizeL = { randomData100L, randomData1000L, randomData10000L };

        // Float datasets
        Float[] randomData100F = new Float[100];
        Float[] randomData1000F = new Float[1000];
        Float[] randomData10000F = new Float[10000];
        // Float[] randomData100000F = new Float[100000];
        // Array to contain all the datasets
        // Float[][] datasetsBySizeF = { randomData100F, randomData1000F,
        // randomData10000F, randomData100000F };
        Float[][] datasetsBySizeF = { randomData100F, randomData1000F, randomData10000F };

        // Double datasets
        Double[] randomData100D = new Double[100];
        Double[] randomData1000D = new Double[1000];
        Double[] randomData10000D = new Double[10000];
        // Double[] randomData100000D = new Double[100000];
        // Array to contain all the datasets
        // Double[][] datasetsBySizeD = { randomData100D, randomData1000D,
        // randomData10000D, randomData100000D };
        Double[][] datasetsBySizeD = { randomData100D, randomData1000D, randomData10000D };

        try (FileWriter fileWriter = new FileWriter("sorting_experiment_results.csv");
                PrintWriter printWriter = new PrintWriter(fileWriter)) {

            // Write the CSV file header
            printWriter.println("Algorithm,DataType,DataSize,Sortedness,Time(ns)");

            // loop over the algorithms
            // then loop over the datasets by size
            // then loop over the datasets by type
            // then loop over the sortedness levels
            for (Sorter sorter : algorithms) {
                String algorithmName = sorter.getClass().getSimpleName();
                System.out.println("-------------------Experiment with " + algorithmName);

                // First we loop over all the Integer arrays
                System.out.println("Integer datasets");
                for (Integer[] dataset : datasetsBySize) {
                    int dataSize = dataset.length;

                    // Random data
                    System.out.println("Random data");
                    generateRandomData(dataset);
                    long time = 0;
                    for (int i = 0; i < 100; i++) {
                        time = measureExecutionTime(sorter, dataset);
                        printWriter.printf("%s,Integer,%d,Random,%d%n", algorithmName, dataSize, time);
                    }

                    // Reversed data
                    System.out.println("Reversed data");
                    generateReversedData(dataset);
                    for (int i = 0; i < 100; i++) {
                        time = measureExecutionTime(sorter, dataset);
                        printWriter.printf("%s,Integer,%d,Reversed,%d%n", algorithmName, dataSize, time);
                    }

                    // First k sorted data
                    System.out.println("First k sorted data");
                    generateFirstKSortedData(dataset, dataset.length / 2);
                    for (int i = 0; i < 100; i++) {
                        time = measureExecutionTime(sorter, dataset);
                        printWriter.printf("%s,Integer,%d,FirstKSorted,%d%n", algorithmName, dataSize, time);
                    }

                    // Last k sorted data
                    System.out.println("Last k sorted data");
                    generateLastKSortedData(dataset, dataset.length / 2);
                    for (int i = 0; i < 100; i++) {
                        time = measureExecutionTime(sorter, dataset);
                        printWriter.printf("%s,Integer,%d,LastKSorted,%d%n", algorithmName, dataSize, time);
                    }
                }

                System.out.println("Long datasets");
                // Now we loop over all the Long arrays
                for (Long[] dataset : datasetsBySizeL) {
                    int dataSize = dataset.length;

                    // Random data
                    System.out.println("Random data");
                    generateRandomData(dataset);
                    long time = 0;
                    for (int i = 0; i < 100; i++) {
                        time = measureExecutionTime(sorter, dataset);
                        printWriter.printf("%s,Long,%d,Random,%d%n", algorithmName, dataSize, time);
                    }

                    // Reversed data
                    System.out.println("Reversed data");
                    generateReversedData(dataset);
                    for (int i = 0; i < 100; i++) {
                        time = measureExecutionTime(sorter, dataset);
                        printWriter.printf("%s,Long,%d,Reversed,%d%n", algorithmName, dataSize, time);
                    }

                    // First k sorted data
                    System.out.println("First k sorted data");
                    generateFirstKSortedData(dataset, dataset.length / 2);
                    for (int i = 0; i < 100; i++) {
                        time = measureExecutionTime(sorter, dataset);
                        printWriter.printf("%s,Long,%d,FirstKSorted,%d%n", algorithmName, dataSize, time);
                    }

                    // Last k sorted data
                    System.out.println("Last k sorted data");
                    generateLastKSortedData(dataset, dataset.length / 2);
                    for (int i = 0; i < 100; i++) {
                        time = measureExecutionTime(sorter, dataset);
                        printWriter.printf("%s,Long,%d,LastKSorted,%d%n", algorithmName, dataSize, time);
                    }
                }

                System.out.println("Float datasets");
                // Now we loop over all the Float arrays
                for (Float[] dataset : datasetsBySizeF) {
                    int dataSize = dataset.length;

                    // Random data
                    System.out.println("Random data");
                    generateRandomData(dataset);
                    long time = 0;
                    for (int i = 0; i < 100; i++) {
                        time = measureExecutionTime(sorter, dataset);
                        printWriter.printf("%s,Float,%d,Random,%d%n", algorithmName, dataSize, time);
                    }

                    // Reversed data
                    System.out.println("Reversed data");
                    generateReversedData(dataset);
                    for (int i = 0; i < 100; i++) {
                        time = measureExecutionTime(sorter, dataset);
                        printWriter.printf("%s,Float,%d,Reversed,%d%n", algorithmName, dataSize, time);
                    }

                    // First k sorted data
                    System.out.println("First k sorted data");
                    generateFirstKSortedData(dataset, dataset.length / 2);
                    for (int i = 0; i < 100; i++) {
                        time = measureExecutionTime(sorter, dataset);
                        printWriter.printf("%s,Float,%d,FirstKSorted,%d%n", algorithmName, dataSize, time);
                    }

                    // Last k sorted data
                    System.out.println("Last k sorted data");
                    generateLastKSortedData(dataset, dataset.length / 2);
                    for (int i = 0; i < 100; i++) {
                        time = measureExecutionTime(sorter, dataset);
                        printWriter.printf("%s,Float,%d,LastKSorted,%d%n", algorithmName, dataSize, time);
                    }
                }

                System.out.println("Double datasets");
                // Now we loop over all the Double arrays
                for (Double[] dataset : datasetsBySizeD) {
                    int dataSize = dataset.length;

                    // Random data
                    System.out.println("Random data");
                    generateRandomData(dataset);
                    long time = 0;
                    for (int i = 0; i < 100; i++) {
                        time = measureExecutionTime(sorter, dataset);
                        printWriter.printf("%s,Double,%d,Random,%d%n", algorithmName, dataSize, time);
                    }

                    // Reversed data
                    System.out.println("Reversed data");
                    generateReversedData(dataset);
                    for (int i = 0; i < 100; i++) {
                        time = measureExecutionTime(sorter, dataset);
                        printWriter.printf("%s,Double,%d,Reversed,%d%n", algorithmName, dataSize, time);
                    }

                    // First k sorted data
                    System.out.println("First k sorted data");
                    generateFirstKSortedData(dataset, dataset.length / 2);
                    for (int i = 0; i < 100; i++) {
                        time = measureExecutionTime(sorter, dataset);
                        printWriter.printf("%s,Double,%d,FirstKSorted,%d%n", algorithmName, dataSize, time);
                    }

                    // Last k sorted data
                    System.out.println("Last k sorted data");
                    generateLastKSortedData(dataset, dataset.length / 2);
                    for (int i = 0; i < 100; i++) {
                        time = measureExecutionTime(sorter, dataset);
                        printWriter.printf("%s,Double,%d,LastKSorted,%d%n", algorithmName, dataSize, time);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}