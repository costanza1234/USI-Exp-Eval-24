package experiment1.src.main.java;

import java.util.Arrays;
import java.util.Random;

public class SortingExperiment {

    // Initialize sorting algorithms
    private static Sorter[] algorithms = {
            new BubbleSortUntilNoChange(),
            new BubbleSortWhileNeeded(),
            new QuickSortGPT(),
            new SelectionSortGPT()
    };

    // Function to measure execution time of a sorting algorithm
    public static long measureExecutionTime(Sorter sorter, Comparable[] array) {
        Comparable[] copy = Arrays.copyOf(array, array.length);
        long startTime = System.nanoTime();
        sorter.sort(copy);
        return System.nanoTime() - startTime;
    }

    // Hypothesis 1: Compare algorithms with a fixed-size dataset
    public static void hypothesis1(Comparable[] dataset) {
        for (Sorter sorter : algorithms) {
            long totalTime = 0;
            for (int i = 0; i < 10; i++) { // Run multiple times for averaging
                totalTime += measureExecutionTime(sorter, dataset);
            }
            System.out
                    .println("Average time for " + sorter.getClass().getSimpleName() + ": " + (totalTime / 10) + " ns");
        }
    }

    // Hypothesis 2: Effect of sortedness level on one sorting algorithm
    public static void hypothesis2(Sorter sorter, Comparable[] randomData, Comparable[] reversedData,
            Comparable[] firstKSorted, Comparable[] lastKSorted) {
        long timeRandom = measureExecutionTime(sorter, randomData);
        long timeReversed = measureExecutionTime(sorter, reversedData);
        long timeFirstK = measureExecutionTime(sorter, firstKSorted);
        long timeLastK = measureExecutionTime(sorter, lastKSorted);

        System.out.println("Sortedness Experiment with " + sorter.getClass().getSimpleName());
        System.out.println("Random data time: " + timeRandom + " ns");
        System.out.println("Reversed data time: " + timeReversed + " ns");
        System.out.println("First-k-sorted time: " + timeFirstK + " ns");
        System.out.println("Last-k-sorted time: " + timeLastK + " ns");
    }

    // Hypothesis 3: Effect of dataset size on one sorting algorithm
    public static void hypothesis3(Sorter sorter, Comparable[][] datasets) {
        System.out.println("Dataset Size Experiment with " + sorter.getClass().getSimpleName());
        for (Comparable[] dataset : datasets) {
            long time = measureExecutionTime(sorter, dataset);
            System.out.println("Size " + dataset.length + " time: " + time + " ns");
        }
    }

    // Hypothesis 4: Effect of data type on one sorting algorithm
    public static void hypothesis4(Sorter sorter, Comparable[][] datasetsByType) {
        String[] dataTypes = { "int", "long", "float", "double" };
        System.out.println("Data Type Experiment with " + sorter.getClass().getSimpleName());
        for (int i = 0; i < datasetsByType.length; i++) {
            long time = measureExecutionTime(sorter, datasetsByType[i]);
            System.out.println("Data type " + dataTypes[i] + " time: " + time + " ns");
        }
    }

    // Utility function to generate random data
    public static Integer[] generateRandomData(int size) {
        Random random = new Random();
        Integer[] data = new Integer[size];
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(1000); // Adjust range as needed
        }
        return data;
    }

    // Main function to run all hypotheses
    public static void main(String[] args) {
        // Example datasets for different hypotheses
        Integer[] fixedDataset = generateRandomData(1000); // for Hypothesis 1

        // Hypothesis 1 test
        System.out.println("1 | Fixed Dataset Experiment");
        hypothesis1(fixedDataset);

        // Hypothesis 2 test: sortedness levels
        // Assuming generateReversedData, generateFirstKSorted, generateLastKSorted are
        // similar utility methods
        Integer[] randomData = generateRandomData(1000);
        Integer[] reversedData = generateRandomData(1000); // Implement similar method for reversed data
        Integer[] firstKSorted = generateRandomData(1000); // Implement method for first-k-sorted
        Integer[] lastKSorted = generateRandomData(1000); // Implement method for last-k-sorted
        System.out.println("2 | Sortedness Level Experiment");
        hypothesis2(algorithms[0], randomData, reversedData, firstKSorted, lastKSorted);
        hypothesis2(algorithms[1], randomData, reversedData, firstKSorted, lastKSorted);
        hypothesis2(algorithms[2], randomData, reversedData, firstKSorted, lastKSorted);
        hypothesis2(algorithms[3], randomData, reversedData, firstKSorted, lastKSorted);

        // Hypothesis 3 test: dataset size
        Integer[][] datasetsBySize = { generateRandomData(100), generateRandomData(1000), generateRandomData(10000),
                generateRandomData(100000) };
        System.out.println("3 | Dataset Size Experiment");
        hypothesis3(algorithms[0], datasetsBySize);
        hypothesis3(algorithms[1], datasetsBySize);
        hypothesis3(algorithms[2], datasetsBySize);
        hypothesis3(algorithms[3], datasetsBySize);


        // Hypothesis 4 test: data types
        // Implement method to generate int, long, float, and double arrays
        // Populate with int, long, float, and double datasets
        Comparable[][] datasetsByType = { generateRandomData(1000), generateRandomData(1000), generateRandomData(1000),
                generateRandomData(1000) };
        System.out.println("4 | Data Type Experiment");
        hypothesis4(algorithms[0], datasetsByType);
        hypothesis4(algorithms[1], datasetsByType);
        hypothesis4(algorithms[2], datasetsByType);
        hypothesis4(algorithms[3], datasetsByType);
    }
}