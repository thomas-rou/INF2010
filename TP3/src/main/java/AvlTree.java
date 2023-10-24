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
        curNode = super.add(value,curNode);
        if (curNode==null) return null ;
        updateHeight(curNode);
        return balance(curNode);
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
        curNode = super.remove(value, curNode);
        if (curNode==null) return null ;
        updateHeight(curNode);
        return balance(curNode);
    }

    /**
     * Balances a node in the AVL tree if its balance factor is not in the range [-1, 1]
     * @param curNode the node to balance
     * @return curNode which might be updated depending on balancing operations
     */
    protected BinaryNode<T> balance(BinaryNode<T> curNode) {
        // TODO
        if (curNode == null) return null;

        if (getHeight(curNode.left) - getHeight(curNode.right) > 1) {
            if (getHeight(curNode.left.left) < getHeight(curNode.left.right))
                curNode.left = rotateLeft(curNode.left);
            return rotateRight(curNode);
        } else if (balanceFactor(curNode) > 1) {
            if (balanceFactor(curNode.right) < 0)
                curNode.right = rotateRight(curNode.right);
            return rotateLeft(curNode);
        }
        updateHeight(curNode);
        return curNode;
    }


    /**
     * Performs a right rotation on a node in the AVL tree to balance it
     * @param curNode the node to rotate
     * @return the node that replaces the rotated node in the tree
     */
    protected BinaryNode<T> rotateRight(BinaryNode<T> curNode) {
        // TODO
        BinaryNode<T> curLeftTemp = curNode.left;
        curNode.left = curLeftTemp.right;
        curLeftTemp.right = curNode;
        updateHeight(curNode);
        updateHeight(curLeftTemp);
        return curLeftTemp;
    }

    /**
     * Performs a left rotation on a node in the AVL tree to balance it
     * @param curNode the node to rotate
     * @return the node that replaces the rotated node in the tree
     */
    protected BinaryNode<T> rotateLeft(BinaryNode<T> curNode){
        // TODO
        BinaryNode<T> curRightTemp = curNode.right;
        curNode.right = curRightTemp.left;
        curRightTemp.left = curNode;
        updateHeight(curNode);
        updateHeight(curRightTemp);
        return curRightTemp;
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
