
import static org.junit.jupiter.api.Assertions.*;

import com.sun.jdi.ArrayReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Pour la partie 2
 * Choix entre utiliser un arbre binaire ou un arbre AVL (les deux sont comparables en termes de performance par rapport à la Heap) :
 *  - Choix arbre binaire
 * Vous pouvez utiliser la file de priorité (priority queue) de Java, il n'est donc pas nécessaire d'implémenter la classe Heap fournie dans le code de départ.
 *  - Utilisation de priority queue de Java
 * Lorsqu'il est question de suppression, on fait référence à :
 * - La suppression de n'importe quel nœud pour un arbre binaire / AVL,
 * - La suppression de la racine pour un tas.
 * Important, veuillez utiliser les méthodes .poll() ou .peek() pour la file de priorité, et non la méthode remove.
 */

public class Partie2Test {
    private BinarySearchTree<Integer> binaryTree;
    private BinarySearchTree<Integer> worstBinaryTree;
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    @BeforeEach
    public void setUp(){
        binaryTree = new BinarySearchTree<>();
        worstBinaryTree = new BinarySearchTree<>();
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        ArrayList<Integer> inOrderList = new ArrayList<Integer>();
        ArrayList<Integer> shuffleList = new ArrayList<Integer>();
        int normalCaseNumberOfValues = 10000;
        int worstCaseNumberOfValues = 5000;

        for (int i = 0; i < worstCaseNumberOfValues; i++){
            inOrderList.add(i);
        }
        for (int i = 0; i < normalCaseNumberOfValues; i++){
            shuffleList.add(i);
        }
        Collections.shuffle(shuffleList);

        for (Integer element : inOrderList){
            worstBinaryTree.add(element);
        }

        for (Integer element : shuffleList) {
            binaryTree.add(element);
            minHeap.offer(element);
            maxHeap.offer(element);
        }
    }

    @Test
    public void testInsertionNormalCase(){
        ArrayList<Integer> insertionShuffleList = new ArrayList<Integer>();
        int insertionNumberOfValues = 20000;

        for (int i = 10000; i < insertionNumberOfValues; i++){
            insertionShuffleList.add(i);
        }
        Collections.shuffle(insertionShuffleList);

        long bstStartTime = System.nanoTime();
        for (Integer element : insertionShuffleList) {
            binaryTree.add(element);
        }
        long bstEndTime = System.nanoTime();

        long minHeapStartTime = System.nanoTime();
        for (Integer element : insertionShuffleList) {
            minHeap.offer(element);
        }
        long minHeapEndTime = System.nanoTime();

        long maxHeapStartTime = System.nanoTime();
        for (Integer element : insertionShuffleList) {
            maxHeap.offer(element);
        }
        long maxHeapEndTime = System.nanoTime();

        System.out.println("Temps d'insertion cas normal arbre BST: " + (bstEndTime - bstStartTime) + " ns, min-Heap:" + (minHeapEndTime - minHeapStartTime) + " ns, " + "max-Heap: " + (maxHeapEndTime - maxHeapStartTime) + " ns");
    }

    @Test
    public void testInsertionWorstCase(){
        ArrayList<Integer> insertionShuffleList = new ArrayList<Integer>();
        int insertionNumberOfValues = 20000;

        for (int i = 10000; i < insertionNumberOfValues; i++){
            insertionShuffleList.add(i);
        }

        long bstStartTime = System.nanoTime();
        for (Integer element : insertionShuffleList) {
            worstBinaryTree.add(element);
        }
        long bstEndTime = System.nanoTime();

        long minHeapStartTime = System.nanoTime();
        for (Integer element : insertionShuffleList) {
            minHeap.offer(element);
        }
        long minHeapEndTime = System.nanoTime();

        long maxHeapStartTime = System.nanoTime();
        for (Integer element : insertionShuffleList) {
            maxHeap.offer(element);
        }
        long maxHeapEndTime = System.nanoTime();

        System.out.println("Temps d'insertion pire cas arbre BST: " + (bstEndTime - bstStartTime) + " ns, min-Heap:" + (minHeapEndTime - minHeapStartTime) + " ns, " + "max-Heap: " + (maxHeapEndTime - maxHeapStartTime) + " ns");
    }

    @Test
    public void testSuppressionWorstCase(){
        ArrayList<Integer> suppressionList = new ArrayList<Integer>();
        int suppressionNumberOfValues = 10000;

        for (int i = suppressionNumberOfValues; i >= 0; i--) {
            suppressionList.add(i);
        }

        long bstStartTime = System.nanoTime();
        for (Integer element : suppressionList) {
           worstBinaryTree.remove(element);
        }
        long bstEndTime = System.nanoTime();

        long minHeapStartTime = System.nanoTime();
        for (Integer element : suppressionList) {
            minHeap.poll();
        }
        long minHeapEndTime = System.nanoTime();

        long maxHeapStartTime = System.nanoTime();
        for (Integer element : suppressionList) {
            maxHeap.poll();
        }
        long maxHeapEndTime = System.nanoTime();

        //System.out.println("vérification si vide max-heap : " + maxHeap.size() + "min-heap : " + minHeap.size());
        System.out.println("Temps de suppression pire cas arbre BST: " + (bstEndTime - bstStartTime) + " ns, min-Heap:" + (minHeapEndTime - minHeapStartTime) + " ns, " + "max-Heap: " + (maxHeapEndTime - maxHeapStartTime) + " ns");
    }

    @Test
    public void testGetMin(){
        int getMinIterations = 10000;

        long bstStartTime = System.nanoTime();
        for (int i = 0; i < getMinIterations; i++) {
            binaryTree.findMin(binaryTree.root);
        }
        long bstEndTime = System.nanoTime();

        long minHeapStartTime = System.nanoTime();
        for (int i = 0; i < getMinIterations; i++) {
            minHeap.peek();
        }
        long minHeapEndTime = System.nanoTime();

        System.out.println("Temps de recuperation du minimum arbre BST: " + (bstEndTime - bstStartTime + " ns, min-Heap:" + (minHeapEndTime - minHeapStartTime) + " ns"));
    }

    @Test
    void testGetMax(){
        int getMaxIterations = 10000;

        long bstStartTime = System.nanoTime();
        for (int i = 0; i < getMaxIterations; i++) {
            binaryTree.findMax(binaryTree.root);
        }
        long bstEndTime = System.nanoTime();

        long maxHeapStartTime = System.nanoTime();
        for (int i = 0; i < getMaxIterations; i++) {
            maxHeap.peek();
        }
        long maxHeapEndTime = System.nanoTime();

        System.out.println("Temps de recuperation du maximum arbre BST: " + (bstEndTime - bstStartTime + " ns, max-Heap:" + (maxHeapEndTime - maxHeapStartTime) + " ns"));
    }

}
