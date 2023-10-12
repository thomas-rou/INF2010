import java.util.*;
import java.util.HashMap;

public final class Interview {

    /** Expliquez votre complexité temporelle et spatiale à l'aide de commentaire dans le code
     *  n représente le nombre de charactère de `phrase` et m le nombre de charactère de `stopwords`
     *  Indiquez les équivalences telles que O(n + 1 + m + 1) => O(n+m) et O(2n+3m) => O(n+m) lorsque possible
     *
     * Considérons T1(n), T1(m), T2(m), T2(n)... représentant les temps d'exécution respectifs.
     *        Attribuons P1(x)... comme la probabilité des exécutions correspondantes se produisant.
     *
     *        En examinant l'algorithme, nous pouvons agréger les complexités et les ajuster en fonction
     *        de leurs probabilités respectives pour obtenir la complexité temporelle moyenne. Nous
     *        observons que dans un scénario optimal, si words[0] est égal à "", l'algorithme renvoie
     *        instantanément une valeur, mettant fin à l'exécution. Par conséquent, la complexité
     *        pour ce cas est simplifiée à O(n+m), prenant en compte la complexité linéaire de ‘n’ et ‘m’.
     *
     *        Dans un scénario où les complexités sont à leur maximum, la somme de toutes les complexités
     *        et la prise en compte de celles imbriquées donnent O(2n+m+5). Cependant, elle
     *        se simplifie à O(n+m) car elle maintient une nature de complexité linéaire.
     *
     *        D'après les observations ci-dessus, il est évident que, quel que soit le scénario, la complexité
     *        de l'algorithme reste constamment à O(n+m).
     *
     *
     * @param phrase String containing a sequence of words separated by a space
     * @param stopwords String array containing all the stop words
     * @return Pair containing two elements, first being the most common word not in the stop words,
     * second being the number of occurences of this word
     */

    //code inspirer de:
    //lee215 (April 14, 2018) <[C++/Java/Python] Easy Solution with Explanation>.
    //https://leetcode.com/problems/most-common-word/solutions/123854/c-java-python-easy-solution-with-explanation/.

    public static Pair findMostCommonValidWord(String phrase, String[] stopwords) {
        // Conversion de la phrase en tableau de mots, complexité temporelle : O(n), complexité spatiale : O(n)
        String[] words = phrase.replaceAll("\\W+", " ").toLowerCase().split("\\s+");

        // Création d'un hashmap pour compter l'occurrence des mots, complexité spatiale : O(n) dans le pire des cas
        Map<String, Integer> count = new HashMap<>();

        // Conversion des mots de stop en minuscules, complexité temporelle : O(m), complexité spatiale : O(m)
        for (int x = 0; x < stopwords.length; x++){
            stopwords[x] = stopwords[x].toLowerCase();
        }

        // Vérification rapide pour éviter le traitement inutile
        if (words[0].equals(""))return new Pair(null, null);

        // Création d'un HashSet pour une recherche rapide, complexité spatiale : O(m)
        Set<String> stop = new HashSet<>(Arrays.asList(stopwords));

        // Parcours des mots et comptage des occurrences, exclusion des stopwords, complexité temporelle : O(n)
        for(String w:words){
            if (!stop.contains(w)) {
                count.put(w, count.getOrDefault(w, 0) + 1);
            }
        }

        // Dans le cas où aucun mot valide n'est trouvé
        if (count.isEmpty())return new Pair(null, null);

        // Recherche du mot le plus fréquent, complexité temporelle : O(n) dans le pire des cas
        return new Pair(
                Collections.max(count.entrySet(), Map.Entry.comparingByValue()).getKey(),
                Collections.max(count.entrySet(), Map.Entry.comparingByValue()).getValue()
        );
        // Complexité temporelle totale dans le cas moyen : O(n) pour le traitement de la phrase + O(m) pour les stopwords
        // => O(n + m)
        // Complexité spatiale totale dans le pire des cas : O(n) pour le stockage des mots + O(m) pour les stopwords
        // => O(n + m)
    }
}