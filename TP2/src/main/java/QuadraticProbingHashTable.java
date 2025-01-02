
// QuadraticProbing Hash table class
//
// CONSTRUCTION: an approximate initial size or default of 11
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// int contains( x )     --> Return position if x is present else return -1
// void makeEmpty( )      --> Remove al
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 * Probing table implementation of hash tables.
 * Note that all "matching" is based on the 'equals' method.
 * @author Mark Allen Weiss
 */
public class QuadraticProbingHashTable<AnyType> {

    /**
     * Construct the hash table.
     */
    //int collisionNbr = 0;
    public QuadraticProbingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    /**
     * Construct the hash table.
     *
     * @param size the approximate initial size.
     */
    public QuadraticProbingHashTable(int size) {
        allocateArray(size);
        makeEmpty();
    }

    /**
     * Insert into the hash table. If the item is
     * already present, do nothing.
     *
     * @param x the item to insert.
     */
    public void insert(AnyType x) {
        // Insert x as active
        int currentPos = findPos(x);
        if (isActive(currentPos))
            return;

        array[currentPos] = new HashEntry<AnyType>(x, true);

        // Rehash; see Section 5.5
        if (++currentSize > array.length * 0.5)
            rehash();
    }

    /**
     * Expand the hash table.
     */
    protected void rehash() {
        HashEntry<AnyType>[] oldArray = array;

        // Create a new double-sized, empty table
        allocateArray(nextPrime(2 * oldArray.length));
        currentSize = 0;

        // Copy table over
        for (int i = 0; i < oldArray.length; i++)
            if (oldArray[i] != null && oldArray[i].isActive)
                insert(oldArray[i].element);
    }

    /**
     * Method that performs quadratic probing resolution.
     * Assumes table is at least half empty and table length is prime.
     *
     * @param x the item to search for.
     * @return the position where the search terminates.
     */
    protected int findPos(AnyType x) {
        int offset = 1;
        int currentPos = myhash(x);

        while (array[currentPos] != null &&
                !array[currentPos].element.equals(x)) {
            currentPos += offset;  // Compute ith probe
            offset += 2;
            while (currentPos >= array.length)
                currentPos -= array.length;
            //++collisionNbr;
        }


        return currentPos;
    }

    /**
     * Remove from the hash table.
     *
     * @param x the item to remove.
     */
    public void remove(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos))
            array[currentPos].isActive = false;
    }

    /**
     * Find an item in the hash table.
     *
     * @param x the item to search for.
     * @return the position if found else returns -1.
     */
    public int contains(AnyType x) {
        int currentPos = findPos(x);
        return isActive(currentPos) ? currentPos : -1;
    }

    /**
     * Return true if currentPos exists and is active.
     *
     * @param currentPos the result of a call to findPos.
     * @return true if currentPos is active.
     */
    protected boolean isActive(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActive;
    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty() {
        currentSize = 0;
        Arrays.fill(array, null);
    }

    protected int myhash(AnyType x) {
        int hashVal = x.hashCode();

        hashVal %= array.length;
        if (hashVal < 0)
            hashVal += array.length;

        return hashVal;
    }

    protected static class HashEntry<AnyType> {
        public AnyType element;   // the element
        public boolean isActive;  // false if marked deleted

        public int probeDistance; //used for robinhood

        public HashEntry(AnyType e) {
            this(e, true);
        }

        public HashEntry(AnyType e, boolean i) {
            element = e;
            isActive = i;
        }
    }

    private static final int DEFAULT_TABLE_SIZE = 11;

    protected HashEntry<AnyType>[] array; // The array of elements
    protected int currentSize;              // The number of occupied cells

    /**
     * Internal method to allocate array.
     *
     * @param arraySize the size of the array.
     */
    @SuppressWarnings("unchecked")
    private void allocateArray(int arraySize) {
        array = new HashEntry[nextPrime(arraySize)];
    }

    /**
     * Internal method to find a prime number at least as large as n.
     *
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime(int n) {
        if (n <= 0)
            n = 3;

        if (n % 2 == 0)
            n++;

        for (; !isPrime(n); n += 2)
            ;

        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     *
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;

        if (n == 1 || n % 2 == 0)
            return false;

        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;

        return true;
    }

}
