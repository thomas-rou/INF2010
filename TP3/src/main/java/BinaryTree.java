public class BinaryTree<T> {
    protected BinaryNode<T> root = null;

    /**
     * Prints the values of the binary tree in post-order traversal
     */
    public void printPostOrder() {
        printPostOrder(root);
    }

    /**
     * Recursively prints the values of the binary tree in post-order traversal
     * @param node the node to start the traversal from
     */
    private void printPostOrder(BinaryNode<T> node) {
        // TODO
        if(node == null) return;
        this.printPostOrder(node.left);
        this.printPostOrder(node.right);
        System.out.printf("%d ",node.getValue());
    }

    /**
     * Prints the values of the binary tree in pre-order traversal
     */
    public void printPreOrder() {
        printPreOrder(root);
    }

    /**
     * Recursively prints the values of the binary tree in pre-order traversal
     * @param node the node to start the traversal from
     */
    private void printPreOrder(BinaryNode<T> node) {
        // TODO
        if(node == null) return;
        System.out.printf("%d ",node.getValue());
        this.printPreOrder(node.left);
        this.printPreOrder(node.right);
    }

    /**
     * Prints the values of the binary tree in in-order traversal
     */
    public void printInOrder() {
        printInOrder(root);
    }

    /**
     * Recursively prints the values of the binary tree in in-order traversal
     * @param node the node to start the traversal from
     */
    private void printInOrder(BinaryNode<T> node) {
        // TODO
        if(node == null) return;
        printInOrder(node.left);
        System.out.printf("%d ",node.getValue());
        printInOrder(node.right);
    }
}

