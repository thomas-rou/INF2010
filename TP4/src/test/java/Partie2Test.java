import static org.junit.jupiter.api.Assertions.*;

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
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    @BeforeEach
    public void setUp(){
        binaryTree = new BinarySearchTree<>();
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        ArrayList<Integer> shuffleList = new ArrayList<Integer>();
        int numberOfValues = 10000;

        for (int i = 0; i < numberOfValues; i++){
            shuffleList.add(i);
        }
        Collections.shuffle(shuffleList);

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
    public void testSuppressionNormalCase(){
        /*
        ArrayList<Integer> suppressionShuffleList = new ArrayList<Integer>();
        int suppresionNumberOfValues = 10000;

        for (int i = 0; i < suppresionNumberOfValues; i++){
            suppressionShuffleList.add(i);
        }
        Collections.shuffle(suppressionShuffleList);

        long bstStartTime = System.nanoTime();
        for (Integer element : suppressionShuffleList) {
            binaryTree.remove(element);
        }
        long bstEndTime = System.nanoTime();

        long minHeapStartTime = System.nanoTime();
        for (Integer element : suppressionShuffleList) {
            minHeap.
        }
        long minHeapEndTime = System.nanoTime();

        long maxHeapStartTime = System.nanoTime();
        for (Integer element : suppressionShuffleList) {
            maxHeap.offer(element);
        }
        long maxHeapEndTime = System.nanoTime();

        System.out.println("Temps d'insertion cas normal arbre BST: " + (bstEndTime - bstStartTime) + " ns, min-Heap:" + (minHeapEndTime - minHeapStartTime) + " ns, " + "max-Heap: " + (maxHeapEndTime - maxHeapStartTime) + " ns");
         */
    }

    @Test
    public void testGetMin(){

    }

    @Test
    void testGetMax(){

    }

}
