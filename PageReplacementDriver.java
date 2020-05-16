package PageReplacement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class PageReplacementDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Page Replacement Algorithm!");
        System.out.print("Insert size of frame: ");
        int frameSize = scanner.nextInt();
        System.out.print("Insert size of page reference string: ");
        int refSize = scanner.nextInt();
        System.out.println("Select Mode : ");
        System.out.print("Press r to random or any key to insert manually: ");

        int[] pageRef = new int[refSize];
        scanner.nextLine();
        if (scanner.nextLine().equalsIgnoreCase("r")) {
            Random random = new Random();
            for (int i = 0; i < refSize; ++i) {
                pageRef[i] = random.nextInt(10) + 1;
            }
        } else {
            System.out.println("Insert process for each page reference string (separate by whitespace)");
            for (int i = 0; i < refSize; ++i) {
                pageRef[i] = scanner.nextInt();
            }
        }
        System.out.println("Page Reference String is \n" + Arrays.toString(pageRef));
        Page page = new Page(frameSize, pageRef);
        System.out.println("Created Page already.........................");
        System.out.println("---------------------------------------------");
        System.out.println("Select Algorithm to find page fault");
        System.out.println("1 : Fifo           2 : Optimal");
        System.out.println("3 : Lru            4 : All Algorithms");
        System.out.print("Your input : ");
        int mode = scanner.nextInt();
        if (mode == 4) {
            ArrayList<PageStrategy> algorithms = new ArrayList<>();
            algorithms.add(new Fifo());
            algorithms.add(new Optimal());
            algorithms.add(new Lru());

            ArrayList<ArrayList<PageResult>> results = page.getPageFaultAllAlgo(algorithms);
            for (int i = 0; i < results.size(); ++i) {
                String algoName = algorithms.get(i).getClass().toString();
                System.out.println(algoName.substring(algoName.lastIndexOf('.')+1));
                results.get(i).forEach(System.out::print);
                System.out.println("Total Page faults are " + results.get(i).stream().filter(PageResult::isFault).count());
                System.out.println("---------------------------------------------");
            }
        } else {
            if (mode == 1) page.setStrategy(new Fifo());
            else if (mode == 2) page.setStrategy(new Optimal());
            else page.setStrategy(new Lru());
            page.getPageFault().forEach(System.out::print);
        }
    }
}