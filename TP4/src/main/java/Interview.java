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

    public List<Integer> part2Interview(Integer[] arr, Integer k)
    {
        // TODO
        //Complexité Temporelle Globale: O(M log M), où M est le nombre d'éléments uniques dans arr.
        //Complexité Spatiale Globale: O(M).
        //
        //
        // Création de la Map des fréquences
        // Complexité Temporelle: O(N) - parcours de chaque élément une fois
        // Complexité Spatiale: O(M) - stockage des éléments uniques et leurs fréquences
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (Integer num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Conversion de la Map en List pour le tri
        // Complexité Temporelle: O(M) - création de la liste à partir des clés
        // Complexité Spatiale: O(M) - stockage des clés uniques dans la liste
        List<Integer> sortedList = new ArrayList<>(frequencyMap.keySet());

        // Tri de la liste
        // Complexité Temporelle: O(M log M) - tri log-linéaire des éléments uniques
        // Complexité Spatiale: O(1) - tri sur place (dépend de l'implémentation du tri)
        sortedList.sort((a, b) -> {
            int freqCompare = Integer.compare(frequencyMap.get(b), frequencyMap.get(a));
            if (freqCompare != 0) {
                return freqCompare;
            }
            return a.compareTo(b);
        });

        // Récupération du k-ième élément
        // Complexité Temporelle: O(1) - accès direct à un élément dans la liste
        // Complexité Spatiale: O(1)
        if (k > 0 && k <= sortedList.size()) {
            return sortedList.get(k - 1);
        } else {
            return null; // Gestion des valeurs de k hors limites
        }
    }

}
