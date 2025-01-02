import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InterviewTest {
    private Interview solution = new Interview();
    private BinarySearchTree<Integer> tree;
    // Partie 3.1

    @Test
    public void part3_1Case(){
        this.tree = new BinarySearchTree<>();
        this.tree.add(3);
        this.tree.add(1);
        this.tree.add(5);
        this.tree.add(0);
        this.tree.add(2);
        this.tree.add(4);
        this.tree.add(6);
        assertEquals(Interview.Type.NotHeap,solution.part1Interview(tree.root));
    }

    @Test
    public void part3_1Case1(){
        this.tree = new BinarySearchTree<>();
        tree.root = new BinaryNode<>(1);
        tree.root.left = new BinaryNode<>(9);
        tree.root.left.left = new BinaryNode<>(10);
        tree.root.left.right = new BinaryNode<>(15);
        tree.root.right = new BinaryNode<>(4);
        tree.root.right.left = new BinaryNode<>(20);
        assertEquals(Interview.Type.MinHeap,solution.part1Interview(tree.root));
    }

    @Test
    public void part3_1Case2(){
        this.tree = new BinarySearchTree<>();
        tree.root = new BinaryNode<>(20);
        tree.root.left = new BinaryNode<>(10);
        tree.root.left.left = new BinaryNode<>(4);
        tree.root.left.right = new BinaryNode<>(5);
        tree.root.right = new BinaryNode<>(15);
        tree.root.right.left = new BinaryNode<>(12);
        tree.root.right.right = new BinaryNode<>(14);
        assertEquals(Interview.Type.MaxHeap,solution.part1Interview(tree.root));
    }

    @Test
    public void part3_1Case3(){
        this.tree = new BinarySearchTree<>();
        tree.root = new BinaryNode<>(10);
        tree.root.right = new BinaryNode<>(15);
        tree.root.right.left = new BinaryNode<>(12);
        tree.root.right.right = new BinaryNode<>(20);
        assertEquals(Interview.Type.NotHeap,solution.part1Interview(tree.root));
    }



    // Partie 3.2
    @Test
    public void part3_2Case0(){
        Integer[] arr = {1, 1, 1, 2, 3, 4, 5, 7, 7, 7};
        assertEquals(7,solution.part2Interview(arr,1));
    }
    @Test
    public void part3_2Case1(){
        Integer[] arr = {1, 1, 1, 2, 3, 4, 5, 7, 7, 7};
        assertEquals(1,solution.part2Interview(arr,2));
    }
    @Test
    public void part3_2Case2(){
        Integer[] arr = {7, 10, 11, 5, 2, 5, 5, 7, 11, 8, 9};
        assertEquals(5,solution.part2Interview(arr,1));
    }
    @Test
    public void part3_2Case3(){
        Integer[] arr = {7, 10, 11, 5, 2, 5, 5, 7, 11, 8, 9};
        assertEquals(11,solution.part2Interview(arr,2));
    }

}
