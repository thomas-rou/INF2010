public class AvlTree<T extends Comparable<T>> extends BinarySearchTree<T>{

    /**
     * Adds a new value to the AVL tree and balances it if necessary
     * @param value the value to add
     */
    @Override
    public void add(T value) {
        this.root = add(value, this.root);
    }

    protected BinaryNode<T> add(T value, BinaryNode<T> curNode) {
        // TODO
        return null;
    }

    /**
     * Removes a value from the AVL tree and balances it if necessary
     * @param value the value to remove
     */
    @Override
    public void remove(T value) {
        this.root = remove(value, this.root);
    }

    protected BinaryNode<T> remove(T value, BinaryNode<T> curNode) {
        // TODO
        return null;
    }

    /**
     * Balances a node in the AVL tree if its balance factor is not in the range [-1, 1]
     * @param curNode the node to balance
     * @return curNode which might be updated depending on balancing operations
     */
    protected BinaryNode<T> balance(BinaryNode<T> curNode) {
        // TODO
        return null;
    }


    /**
     * Performs a right rotation on a node in the AVL tree to balance it
     * @param curNode the node to rotate
     * @return the node that replaces the rotated node in the tree
     */
    protected BinaryNode<T> rotateRight(BinaryNode<T> curNode) {
        // TODO
        return null;
    }

    /**
     * Performs a left rotation on a node in the AVL tree to balance it
     * @param curNode the node to rotate
     * @return the node that replaces the rotated node in the tree
     */
    protected BinaryNode<T> rotateLeft(BinaryNode<T> curNode){
        // TODO
        return null;
    }

    /**
     * Calculates the balance factor of a node in the AVL tree
     * @param node the node to calculate the balance factor for
     * @return the balance factor of the node
     */
    private int balanceFactor(BinaryNode<T> node) {
        return getHeight(node.right) - getHeight(node.left);
    }

    /**
     * Calculates the height of a node in the AVL tree
     * @param node the node to calculate the height for
     * @return the height of the node
     */
    private int getHeight(BinaryNode<T> node) {
        if(node == null) return 0;
        return node.height;
    }

    /**
     * Updates the height of a node in the AVL tree
     * @param node the node to update the height for
     */
    private void updateHeight(BinaryNode<T> node) {
        int leftChildHeight = getHeight(node.left);
        int rightChildHeight = getHeight(node.right);
        node.height = Math.max(leftChildHeight, rightChildHeight) + 1;
    }
}
