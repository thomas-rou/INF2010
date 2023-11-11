import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class CompletTree {
    // Partie 1

    public boolean isCompleteTree(Integer[] arr){
        int arrayLength = arr.length;

        for (int i = 0; i < arrayLength; i++){
            // élément null entre la racine et le dernier élément, complexité O(n)
            if (arr[i]== null && i < arrayLength - 1 && arr[i+1] != null){
                return false;
            }
        }
        return true;
    }

    public boolean isCompleteTree(BinaryNode<Integer> root){
        // arbre vide = arbre complet par def
        if (root == null) {
            return true;
        }

        /** Création d'une liste chaînée dans laquelle on ajoute la racine
         * l'utilisation d'une liste chaîné avec vérification en FIFO va permettre
         * une complexité O(n)
         */
        Queue<BinaryNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            // Retirer le noeud de la liste pour évaluation
            BinaryNode<Integer> current = queue.poll();

            // si le noeud n'est pas nulle => ajouter ses enfants de gauche et de droite à la file et recommencer
            if(current != null){
                queue.offer(current.left);
                queue.offer(current.right);
            }
            else {
                // le noeud courant est nulle => vérifier s'il s'agit du dernier noeud
                while (!queue.isEmpty()){
                    if (queue.poll() != null){
                        return false;
                    }
                }
            }
        }

        return true;
    }


}
