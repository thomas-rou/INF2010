import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreePerformanceTest {

    private BinarySearchTree<Integer> bst;
    private AvlTree<Integer> avl;
    private SplayTree splay;

    @BeforeEach
    public void setUp() {
        bst = new BinarySearchTree<>();
        avl = new AvlTree<>();
        splay = new SplayTree();
    }

    @Test
    public void testInsertionNormalCase() {
        int[] elements = {6, 5, 4, 3, 2, 1};

        long bstStartTime = System.nanoTime();
        for (int element : elements) {
            bst.add(element);
        }
        long bstEndTime = System.nanoTime();

        long avlStartTime = System.nanoTime();
        for (int element : elements) {
            avl.add(element);
        }
        long avlEndTime = System.nanoTime();

        long splayStartTime = System.nanoTime();
        for (int element : elements) {
            splay.insert(element);
        }
        long splayEndTime = System.nanoTime();
        System.out.println("Temps d'insertion cas normal arbre BST: " + (bstEndTime - bstStartTime) + " ns, arbre AVL:" + (avlEndTime - avlStartTime) + " ns, " + "arbre Splay: " + (splayEndTime - splayStartTime) + " ns");
    }

    @Test
    public void testInsertionWorstCase() {
        int[] elements = {3, 1, 5, 0, 2, 4, 6};

        long bstStartTime = System.nanoTime();
        for (int element : elements) {
            bst.add(element);
        }
        long bstEndTime = System.nanoTime();

        long avlStartTime = System.nanoTime();
        for (int element : elements) {
            avl.add(element);
        }
        long avlEndTime = System.nanoTime();

        long splayStartTime = System.nanoTime();
        for (int element : elements) {
            splay.insert(element);
        }
        long splayEndTime = System.nanoTime();
        System.out.println("Temps d'insertion pire cas arbre BST: " + (bstEndTime - bstStartTime) + " ns, arbre AVL:" + (avlEndTime - avlStartTime) + " ns, " + "arbre Splay: " + (splayEndTime - splayStartTime) + " ns");
    }

    @Test
    public void testSearchNormalCase(){
        int[] elements = {3, 1, 5, 0, 2, 4, 6};
        int[] searchKeys = {5, 4, 1};
        for (int element : elements) {
            bst.add(element);
            avl.add(element);
            splay.insert(element);
        }

        long bstStartTime = System.nanoTime();
        for (int key : searchKeys) {
            bst.contains(key);
        }
        long bstEndTime = System.nanoTime();

        long avlStartTime = System.nanoTime();
        for (int key : searchKeys) {
            avl.contains(key);
        }
        long avlEndTime = System.nanoTime();

        long splayStartTime = System.nanoTime();
        for (int key : searchKeys) {
            splay.search(key);
        }
        long splayEndTime = System.nanoTime();
        System.out.println("Temps de recherche cas normal arbre BST: " + (bstEndTime - bstStartTime) + " ns, arbre AVL:" + (avlEndTime - avlStartTime) + " ns, " + "arbre Splay: " + (splayEndTime - splayStartTime) + " ns");
    }

    @Test
    public void testSearchWorstCase(){
        int[] elements = {6, 5, 4, 3, 2, 1};
        int[] searchKeys = {1, 1, 1};
        int[] splaySearchKeys = {1, 6, 3};
        for (int element : elements) {
            bst.add(element);
            avl.add(element);
            splay.insert(element);
        }

        long bstStartTime = System.nanoTime();
        for (int key : searchKeys) {
            bst.contains(key);
        }
        long bstEndTime = System.nanoTime();

        long avlStartTime = System.nanoTime();
        for (int key : searchKeys) {
            avl.contains(key);
        }
        long avlEndTime = System.nanoTime();

        long splayStartTime = System.nanoTime();
        for (int key : splaySearchKeys) {
            splay.search(key);
        }
        long splayEndTime = System.nanoTime();
        System.out.println("Temps de recherche pire cas arbre BST: " + (bstEndTime - bstStartTime) + " ns, arbre AVL:" + (avlEndTime - avlStartTime) + " ns, " + "arbre Splay: " + (splayEndTime - splayStartTime) + " ns");
    }

    @Test
    public void testRemoveNormalCase(){
        int[] elements = {3, 1, 5, 0, 2, 4, 6};
        int[] removeKeys = {1, 3, 4};
        for (int element : elements) {
            bst.add(element);
            avl.add(element);
            splay.insert(element);
        }

        long bstStartTime = System.nanoTime();
        for (int key : removeKeys) {
            bst.remove(key);
        }
        long bstEndTime = System.nanoTime();

        long avlStartTime = System.nanoTime();
        for (int key : removeKeys) {
            avl.remove(key);
        }
        long avlEndTime = System.nanoTime();

        long splayStartTime = System.nanoTime();
        for (int key : removeKeys) {
            splay.remove(key);
        }
        long splayEndTime = System.nanoTime();
        System.out.println("Temps de retrait cas normal arbre BST: " + (bstEndTime - bstStartTime) + " ns, arbre AVL:" + (avlEndTime - avlStartTime) + " ns, " + "arbre Splay: " + (splayEndTime - splayStartTime) + " ns");
    }

    @Test
    public void testRemoveWorstCase(){
        int[] elements = {1, 2, 3, 4, 5, 6};
        int[] removeKeys = {6, 5, 4};
        int[] secondRemoveKeys = {3, 6, 1};
        for (int element : elements) {
            bst.add(element);
            avl.add(element);
            splay.insert(element);
        }

        long bstStartTime = System.nanoTime();
        for (int key : removeKeys) {
            bst.remove(key);
        }
        long bstEndTime = System.nanoTime();

        long avlStartTime = System.nanoTime();
        for (int key : secondRemoveKeys) {
            avl.remove(key);
        }
        long avlEndTime = System.nanoTime();

        long splayStartTime = System.nanoTime();
        for (int key : secondRemoveKeys) {
            splay.remove(key);
        }
        long splayEndTime = System.nanoTime();
        System.out.println("Temps de retrait pire cas arbre BST: " + (bstEndTime - bstStartTime) + " ns, arbre AVL:" + (avlEndTime - avlStartTime) + " ns, " + "arbre Splay: " + (splayEndTime - splayStartTime) + " ns");
    }
}
