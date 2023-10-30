public class BinarySearchTree<T extends Comparable<T>> implements Tree<T>{
    protected BinaryNode<T> root = null;
    /**
     * Adds a new node with the specified data to the tree
     * @param data The data to be stored in the new node
     */
    @Override
    public void add(T data) {
        this.root = add(data, root);
    }

    /**
     * Adds a new node with the specified value to the specified subtree
     * Tree do not contain duplicates and must throw a RuntimeException : "Value is duplicated"
     * @param value The value to be stored in the new node
     * @param curNode The root of the subtree to add the new node to
     * @return The root of the modified subtree
     */
    protected BinaryNode<T> add(T value, BinaryNode<T> curNode) {
        if (curNode == null) {
            return new BinaryNode(value);
        }

        int comparison = value.compareTo(curNode.value);

        if (comparison < 0) {
            curNode.left = add(value, curNode.left);
        }
        else if (comparison > 0) {
            curNode.right = add(value, curNode.right);
        }
        else {
            throw new RuntimeException("Value is duplicated");
        }
        return curNode;
    }

    /**
     * Determines if the tree contains a node with the specified value
     * @param value The value to search for in the tree
     * @return True if the tree contains a node with the specified value, false otherwise
     */
    @Override
    public boolean contains(T value) {
        return contains(value, root);
    }

    /**
     * Determines if the specified subtree contains a node with the specified value
     * @param value The value to search for in the subtree
     * @param curNode The root of the subtree to search
     * @return True if the subtree contains a node with the specified value, false otherwise
     */
    private boolean contains(T value, BinaryNode<T> curNode) {
        if(curNode == null){
            return false;
        }
        int comparison = value.compareTo(curNode.value);

        if(comparison == 0){
            return true;
        }

        else if(comparison < 0){
            return contains(value, curNode.left);
        }

        return contains(value, curNode.right);
    }

    /**
     * Removes a node with the specified value from the tree
     * @param value The value of the node to be removed
     */
    @Override
    public void remove(T value) {
        this.root = remove(value, root);
    }

    /**
     * Removes a node with the specified value from the specified subtree
     * @param value The value of the node to be removed
     * @param curNode The root of the subtree to remove the node from
     * @return The root of the modified subtree
     */
    protected BinaryNode<T> remove(T value, BinaryNode<T> curNode) {
        if (curNode == null) {
            return null; // Node doesn't exist, return null
        }
        int comparison = value.compareTo(curNode.value);
        if(comparison < 0){
            curNode.left = remove(value, curNode.left);
        }
        else if(comparison > 0){
            curNode.right = remove(value, curNode.right);
        }
        else {
            if (curNode.left == null){
                return curNode.right;
            }
            else if (curNode.right == null) {
                return curNode.left;
            }
            curNode.value = findMin(curNode.right).value;
            curNode.right = remove(curNode.value, curNode.right);
        }
        return curNode;
    }

    /**
     * Finds the node containing the minimum value in the subtree rooted at the given node
     * @param curNode the root of the subtree to search for the minimum value
     * @return the node containing the minimum value in the subtree rooted at the given node, or null if the subtree is empty
     */
    protected BinaryNode<T> findMin(BinaryNode<T> curNode){
        if(curNode == null){
            return null;
        }
        else if (curNode.left == null){
            return curNode;
        }
        return findMin(curNode.left);
    }

    /**
     * Finds the node containing the maximum value in the subtree rooted at the given node
     * @param curNode the root of the subtree to search for the maximum value
     * @return the node containing the maximum value in the subtree rooted at the given node, or null if the subtree is empty
     */
    protected BinaryNode<T> findMax(BinaryNode<T> curNode){
        if(curNode == null){
            return null;
        }
        else if (curNode.right == null){
            return curNode;
        }
        return findMax(curNode.right);
    }
}