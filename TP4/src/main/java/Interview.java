import java.util.*;

public class Interview {
    protected enum Type{
        MinHeap,
        MaxHeap,
        NotHeap
    };

    public Type part1Interview(BinaryNode<Integer> root)
    {
        // TODO
        if (root == null) {
            return Type.NotHeap;
        }

        boolean isMax = isHeap(root, Integer.MAX_VALUE, true);
        boolean isMin = isHeap(root, Integer.MIN_VALUE, false);

        if (isMax && isMin) {
            return Type.MaxHeap;
        } else if (isMax) {
            return Type.MaxHeap;
        } else if (isMin) {
            return Type.MinHeap;
        } else {
            return Type.NotHeap;
        }
    }

    private boolean isHeap(BinaryNode<Integer> node, int compareValue, boolean isMaxHeap) {
        if (node == null) {
            return true;
        }

        if (isMaxHeap && node.getValue() > compareValue) {
            return false;
        }
        if (!isMaxHeap && node.getValue() < compareValue) {
            return false;
        }

        return isHeap(node.left, node.getValue(), isMaxHeap) && isHeap(node.right, node.getValue(), isMaxHeap);

    }
    /*
    - 'part1Interview' détermine le type de heap en vérifiant les propriétés de MaxHeap et MinHeap sur
    l'arbre binaire. Elle parcourt chaque nœud une fois, d'où sa complexité temporelle en O(N).

    - 'isHeap' est une fonction récursive qui vérifie si un sous-arbre est un MaxHeap ou un MinHeap.
    Elle a également une complexité temporelle en O(N) car chaque nœud est visité une fois.
    Sa complexité spatiale est O(h) due aux appels récursifs, où h est la hauteur de l'arbre.
     */






    public Integer part2Interview(Integer[] arr, Integer k) {
        // Compter les fréquences de chaque nombre
        // Complexité Temporelle: O(N) - Parcourir une fois tous les éléments
        // Complexité Spatiale: O(N) - Stockage des fréquences pour chaque élément unique
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (Integer num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // PriorityQueue pour maintenir les K éléments les plus fréquents
        // Complexité Temporelle: O(N log K) - Insertion/Suppression dans la PriorityQueue
        // Complexité Spatiale: O(K) - La PriorityQueue contient jusqu'à K éléments
        PriorityQueue<Integer> heap = new PriorityQueue<>(
                (a, b) -> {
                    int freqCompare = Integer.compare(frequencyMap.get(a), frequencyMap.get(b));
                    if (freqCompare != 0) {
                        return freqCompare;
                    }
                    return a.compareTo(b);
                }
        );

        // Ajouter tous les éléments à la PriorityQueue et maintenir sa taille à K
        for (Integer num : frequencyMap.keySet()) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        // Extraire les éléments et les stocker dans une liste pour l'inverser
        // Complexité Temporelle: O(K log K) - Extraire K éléments de la PriorityQueue
        // Complexité Spatiale: O(K) - Stockage des K éléments dans une liste
        List<Integer> topKElements = new ArrayList<>();
        while (!heap.isEmpty()) {
            topKElements.add(heap.poll());
        }
        Collections.reverse(topKElements);

        // Retourner le k-ième élément
        // Complexité Temporelle: O(1)
        // Complexité Spatiale: O(1)
        return k <= topKElements.size() ? topKElements.get(k - 1) : null;
    }


    /*
    La partie qui prend le plus de temps est la construction et la manipulation de la
    PriorityQueue qui a une complexité temporelle de O(N log K) pour insérer N éléments
    en une taille max de K. La complexité spatiale, c'est surtout le stockage des
    fréquences et des éléments dans la PriorityQueue et la liste finale. Tout cela conduit donc
    à une complexité spatiale globale de O(N + K).
     */
}
