import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RobinHoodTest {
    private RobinHoodHashTable<Integer> rHTable = new RobinHoodHashTable<>();
    private RobinHoodHashTable<Integer> rHTable2 = new RobinHoodHashTable<>(7);
    private QuadraticProbingHashTable<Integer> qPTable = new QuadraticProbingHashTable<>();

    private static List<Integer> readFromFile(String filename) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            if ((line = br.readLine()) != null) { // Read the single line
                String[] tokens = line.split(","); // Split the line by commas
                for (String token : tokens) {
                    numbers.add(Integer.parseInt(token.trim())); // Parse and add each number to the list
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return numbers;
    }
    @Test
    public void testBasicInsertion() {
        rHTable.insert(5);
        rHTable.insert(6);
        rHTable.insert(16);
        rHTable.insert(16);
        rHTable.insert(27);
        assertEquals(5, rHTable.contains(5));
        assertEquals(6, rHTable.contains(16));
        assertEquals(7, rHTable.contains(6));
        assertEquals(9, rHTable.contains(27));
    }

    @BeforeEach
    void setrHTable2() {
        rHTable2.insert(3);
        rHTable2.insert(10);
        rHTable2.remove(3);
        rHTable2.insert(17);
    }

    @Test
    public void testContains() {
        assertEquals(4, rHTable2.contains(10));
        assertEquals(-1, rHTable2.contains(3));
    }

    //currentsize should not decrease unless in a rehash case
    //if we replace an inactive element, RobinHood does not add it again, and size stays the same only in this case
    //if collide with an inactive element, should not replace it, the only criteria is the probing distance.
    @Test
    public void currentsize() {
        assertEquals(3, rHTable2.currentSize);
        rHTable2.remove(17);
        rHTable2.insert(24);
        assertEquals(2, rHTable2.currentSize);

        rHTable.insert(0);
        rHTable.insert(10);
        rHTable.remove(0);
        rHTable.insert(6);
        rHTable.insert(21);

        assertEquals(0, rHTable.contains(21));
        assertEquals(3, rHTable.currentSize);

    }

    @Test
    public void testProbingDistance() {
        assertEquals(2, rHTable2.array[0].probeDistance);
        assertEquals(1, rHTable2.array[4].probeDistance);
    }

    @Test
    public void testRehash() {
        rHTable2.insert(4);
        rHTable2.remove(17);
        rHTable2.insert(1);
        assertEquals(17, rHTable2.array.length);
    }

    @Test
    public void executionTimeTest() {
        RobinHoodHashTable rHTable3 = new RobinHoodHashTable<Integer>();
        QuadraticProbingHashTable qPTable3 = new QuadraticProbingHashTable<Integer>();
        List<Integer> numbers = readFromFile("./src/test/resources/inputArrays.txt");
        // Insert the numbers first
        for (Integer number : numbers) {
            {
                if(number%2==1){
                    rHTable3.insert(number);
                    qPTable3.insert(number);
                }
            }
        }
        // Measure Execution Time for contains in QuadraticProbingHashTable
        long startQuadratic = System.nanoTime();
        for (Integer number : numbers)
            qPTable3.contains(number);

        long endQuadratic = System.nanoTime();
        long durationQuadratic = endQuadratic - startQuadratic;
        // Measure Execution Time for contains in RobinHoodHashTable
        long startRobin = System.nanoTime();
        for (Integer number : numbers)
            rHTable3.contains(number);
        long endRobin = System.nanoTime();
        long durationRobin = endRobin - startRobin;

        System.out.println("Robin Hood HashTable took: " + durationRobin + " nanoseconds");
        System.out.println("Quadratic Probing HashTable took: " + durationQuadratic + " nanoseconds");
        //System.out.println("Robin Hood number of collision: " + rHTable3.collisionNbr);
        //System.out.println("Quadratic Probing number of collision: " + qPTable3.collisionNbr);
    }


}
