import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class Partie1Test{
    private CompletTree completTree;
    private BinarySearchTree<Integer> tree;

    @BeforeEach
    public void setup(){
        this.completTree = new CompletTree();
    }

    @Test
    public void testArrCase1(){
        Integer[] arr = {1,2,3,4,5,6,7,8,9,10};
        assertTrue(this.completTree.isCompleteTree(arr));

    }

    @Test
    public void testArrCase2(){
        Integer[] arr2 = {1,2,3,4,5,null,8,10};
        assertFalse(this.completTree.isCompleteTree(arr2));

    }
    @Test
    public void testArrCase3(){
        Integer[] arr3 = {1,null,3,4,5,6,7,2};
        assertFalse(this.completTree.isCompleteTree(arr3));
    }
    @Test
    public void testArrCase4(){
        Integer[] arr4 = {1,2,3,4,5,6,7,null,9};
        assertFalse(this.completTree.isCompleteTree(arr4));
    }

    @Test
    public void testArrCase5(){
        this.tree = new BinarySearchTree<>();
        this.tree.add(3);
        this.tree.add(1);
        this.tree.add(5);
        this.tree.add(0);
        this.tree.add(2);
        this.tree.add(4);
        this.tree.add(6);
        assertTrue(this.completTree.isCompleteTree(this.tree.root));
    }

    @Test
    public void testArrCase6(){
        this.tree = new BinarySearchTree<>();
        this.tree.add(3);
        this.tree.add(1);
        this.tree.add(5);
        this.tree.add(2);
        this.tree.add(4);
        this.tree.add(6);
        assertFalse(this.completTree.isCompleteTree(this.tree.root));
    }


}
