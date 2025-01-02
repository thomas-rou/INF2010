public class Interview {

    /**
     * Expliquez votre complexité temporelle et spatiale en cas moyen et en pire cas à l'aide de commentaire dans le code
     * Indiquez les équivalences telles que O(n + 1) => O(n) et O(2n) => O(n)
     * * TODO Time Complexity : Worst Case O(n), explain Worst and Average Case
     * Dans cette fonction, il est visible que la complexité temporelle dans le pire des cas est de
     * O(n). Cette conclusion est tirée du fait qu'il y a une boucle "for" contenant plusieurs
     * conditions "if". Dans le pire des scénarios, cette boucle sera parcourue intégralement sur
     * toute la longueur du tableau. Si nous analysons cette complexité, nous obtenons : O(n*(1*
     * (1+1)+1)) = O(n*3) = O(n). Cependant, dans un cas moyen, la situation est légèrement
     * différente. Par exemple, si la taille du tableau est de 1, la complexité sera de O(1), car il n'y aura
     * pas de boucle à parcourir. Mais étant donné que le pire cas est plus fréquent dans notre
     * contexte, la complexité moyenne tend aussi vers O(n). Ainsi, elle est de O(n).
     * <p>
     * * TODO Space Complexity : Determine and Explain Worst and Average Case in comments
     * Pour évaluer la complexité spatiale de cet algorithme, nous devons examiner chaque ligne
     * pour déterminer l'espace qu'elle occupe. Il est aussi connu que la complexité spatiale est la
     * somme de l'espace auxiliaire et de l'espace utilisé pour les valeurs d'entrée. Toutes les
     * variables présentes dans cet algorithme occupent un espace constant, d'où une complexité
     * spatiale de O(1) pour ces variables. Cependant, il y a une itération sur un tableau. Donc, la complexité
     * spatiale sera proportionnelle à la taille du tableau que nous appelons n, multipliée
     * par la taille constante d'un entier. Cela nous donne une complexité spatiale de O(n) quand
     * le tableau a une taille supérieure à 1. Dans le meilleur des cas, si le tableau n'a qu'un seul élément,
     * la complexité est de O(1) car il n'y a pas d'itération nécessaire. Cependant, en moyenne,
     * la complexité reste O(n) car, généralement, le tableau contient plusieurs éléments.
     * <p>
     * <p>
     * <p>
     * * TODO HAS TO BE ITERATIVE, , NOT RECURSIVE
     *
     * @param numbers List of numbers sorted in ascending order containing 1 non-duplicate
     * @return non-duplicate number
     */
    public static Integer findNonDuplicateIterativeLinear(Integer[] numbers) {
        if (numbers.length == 1) {
            return numbers[0];
        }
        for (int i = 0; i < numbers.length - 1; i++) {
            if (i == 0 && !numbers[i].equals(numbers[i + 1])) {
                return numbers[i];
            }
            if (!numbers[i].equals(numbers[i + 1]) && !numbers[i].equals(numbers[i - 1])) {
                return numbers[i];
            }
            if (i == numbers.length - 2 && !numbers[i].equals(numbers[i + 1])) {
                return numbers[i + 1];
            }
        }
        return null;

    }

    /**
     * Expliquez votre complexité temporelle et spatiale à l'aide de commentaire dans le code
     * Indiquez les équivalences telles que O(n + 1) => O(n) et O(2n) => O(n)
     * * TODO Time Complexity : Worst Case O(log(n)), explain Worst and Average Case
     * Dans cette fonction, il n'est pas aisé de déterminer directement la complexité temporelle du pire cas.
     * Cependant, en s'appuyant sur nos connaissances de certaines fonctions et de l'informatique binaire,
     * on peut observer que la partie de la fonction où nous entrons dans la boucle "while" présente
     * une complexité de O(log(n)). Ceci est dû au fait que nous utilisons une approche de type "diviser pour régner".
     * Au lieu de parcourir tous les éléments du tableau, nous réduisons notre temps de recherche en divisant
     * successivement le tableau par deux jusqu'à arriver à un tableau d'un seul élément.
     * Cette démarche s'apparente à une complexité temporelle de O(log(n)), comme on peut le constater
     * dans des algorithmes tels que le tri fusion ou le tri rapide. En termes de calcul, la boucle "while" a une
     * complexité de O(log(n)) que nous pouvons ensuite multiplier par les autres complexités internes à cette
     * boucle : O(log(n) * 1 * 1(1+1) * 1(1+1)) ce qui donne O(log(n)*4), soit finalement O(log(n)). Dans le meilleur
     * des cas, nous obtenons O(1) grâce à la première condition "if". Toutefois, cette situation est moins
     * courante, donc en moyenne, la complexité temporelle reste O(log(n)).
     * <p>
     * * TODO Space Complexity : Determine and Explain Worst and Average Case in comments
     * Pour définir la complexité spatiale de cet algorithme, il est nécessaire d'analyser l'espace occupé
     * par chaque ligne de code. On sait aussi que la complexité spatiale est la somme de l'espace auxiliaire
     * et de l'espace nécessaire pour les valeurs d'entrée. Toutes les variables présentes dans cet
     * algorithme occupent un espace constant. Ainsi, elles contribuent à une complexité spatiale de O(1).
     * Cependant, une itération se fait sur un tableau avec une complexité temporelle de O(log(n)).
     * Ainsi, la complexité spatiale serait le produit d'une constante (la taille d'un entier dans le tableau) et de log(n).
     * Donc, si le tableau a plus d'un élément, la complexité spatiale est de O(log(n)). Dans le cas optimal,
     * si le tableau ne contient qu'un seul élément, la complexité spatiale est O(1) car nous n'aurons pas à effectuer
     * l'itération log(n) fois, grâce à notre condition "if". Néanmoins, en moyenne, la complexité sera plutôt de O(log(n)),
     * car le tableau contiendra généralement plus d'un élément.
     * * TODO HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * @param numbers List of numbers sorted in ascending order containing 1 duplicate
     * @return non-duplicate number
     */
    public static Integer findNonDuplicateIterative(Integer[] numbers) {
        if (numbers.length == 0) {
            return null;
        }

        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (!numbers[mid].equals(numbers[mid - 1]) && !numbers[mid].equals(numbers[mid + 1])) {
                return numbers[mid];
            }

            if (mid % 2 == 0) {
                if (numbers[mid].equals(numbers[mid + 1])) {
                    left = mid + 2;
                } else {
                    right = mid - 1;
                }
            } else {
                if (numbers[mid].equals(numbers[mid - 1])) {
                    left = mid + 1;
                } else {
                    right = mid - 2;
                }
            }
        }

        return numbers[left];
    }

    /**
     * Expliquez votre complexité temporelle et spatiale à l'aide de commentaire dans le code
     * Indiquez les équivalences telles que O(n + 1) => O(n) et O(2n) => O(n)
     * * TODO Time Complexity : Worst Case O(log(n)), explain Worst and Average Case
     * Même si cet algorithme est récursif, son essence reste simple. Il applique le principe de "diviser pour régner".
     * Concrètement, nous divisons notre tableau en deux à chaque itération. Ainsi, même dans le pire scénario,
     * nous examinons seulement la moitié de ce tableau à chaque étape. En divisant continuellement par deux,
     * nous aboutissons à une complexité temporelle de O(log(n)) tant pour le cas moyen que pour le pire cas.
     * Bien sûr, dans le meilleur des cas où nous trouvons l'élément unique dès la première itération, la complexité serait O(1).
     * Toutefois, un tel scénario est exceptionnel. En général, nous pouvons nous attendre à une complexité de O(log(n)).
     * Calculs: O(log(n)*1*1*(O(log(n)*1*1...)) => O(log(n))
     * <p>
     * * TODO Space Complexity : Determine and Explain Worst and Average Case in comments
     * La complexité spatiale est principalement dictée par l'entrée de la fonction. Toutefois,
     * contrairement à la complexité temporelle, la récursion n'utilise pas un espace constant. A chaque
     * étape de la récursion, nous allouons de l'espace sur la pile d'exécution.
     * Étant donné que nous divisons notre tableau en deux à chaque itération, et que chaque
     * division génère un nouvel appel récursif, nous avons une complexité spatiale de O(log(n))
     * à la fois pour le cas moyen et le pire cas.
     * <p>
     * * TODO HAS TO BE RECURSIVE, NOT ITERATIVE
     *
     * @param numbers List of numbers sorted in ascending order containing 1 non-duplicate
     * @return non-duplicate number
     */
    public static Integer findNonDuplicateRecursive(Integer[] numbers) {
        return findNonDuplicateRecursive(numbers, 0, numbers.length - 1);
    }

    private static Integer findNonDuplicateRecursive(Integer[] numbers, int left, int right) {
        if (numbers.length == 0) {
            return null;
        }

        if (left < right) {
            int mid = left + (right - left) / 2;

            if (!numbers[mid].equals(numbers[mid - 1]) && !numbers[mid].equals(numbers[mid + 1])) {
                return numbers[mid];
            }

            if (mid % 2 == 0) {
                if (numbers[mid].equals(numbers[mid + 1])) {
                    return findNonDuplicateRecursive(numbers, mid + 2, right);
                } else {
                    return findNonDuplicateRecursive(numbers, left, mid - 1);
                }
            } else {
                if (numbers[mid].equals(numbers[mid - 1])) {
                    return findNonDuplicateRecursive(numbers, mid + 1, right);
                } else {
                    return findNonDuplicateRecursive(numbers, left, mid - 2);
                }
            }
        }

        return numbers[left];
    }
}
