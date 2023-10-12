public class RobinHoodHashTable<AnyType> extends QuadraticProbingHashTable<AnyType> {
    //int collisionNbr = 0;
    public RobinHoodHashTable(){
        super();
    }

    public RobinHoodHashTable(int size){
        super(size);}

    /*
    //Recommandé mais pas obligatoire ( cette methode n'est pas un override)
    private int findPos(HashEntry<AnyType> x) {
     return -1;
    }*/

    private void insert (HashEntry<AnyType> x) {
        int initialPos = myhash(x.element);
        int currentPos = initialPos;
        int currentProbeDistance = x.probeDistance;
        int baseOffset = 1;
        int offset = baseOffset;

        while (true) {
            if (array[currentPos] == null) {
                array[currentPos] = new HashEntry<AnyType>(x.element, true);
                array[currentPos].probeDistance = currentProbeDistance;

                if (++currentSize > array.length * 0.5) {
                    rehash();
                    return;
                }
            } else if (array[currentPos].element == x.element) {
                return;
            } else if (currentProbeDistance > array[currentPos].probeDistance) {
                HashEntry<AnyType> temp = array[currentPos];
                array[currentPos] = new HashEntry<AnyType>(x.element, true);
                array[currentPos].probeDistance = currentProbeDistance;
                //++collisionNbr;

                if (++currentSize > array.length * 0.5) {
                    rehash();
                }

                if (temp.isActive) {
                    x = temp;
                    offset = baseOffset;
                    currentProbeDistance = x.probeDistance;
                    initialPos = myhash(x.element);
                } else {
                    --currentSize;
                    return;
                }
            } else {
                currentPos = (initialPos + offset * offset) % array.length;
                offset += 1;
                currentProbeDistance += 1;
            }
        }

    }

    @Override
    public int contains (AnyType x) {
        int currentPos = super.findPos( x );
        return isActive( currentPos )?currentPos:-1;
    }

    @Override
    public void insert (AnyType x){
        HashEntry X = new HashEntry<>(x);
        this.insert(X);
    }

}

/** Mettre ici votre réponse pour executionTimeTest
* executionTimeTest :
*   1.  Robin Hood HashTable took: 134191900 nanoseconds
*       Quadratic Probing HashTable took: 165562800 nanoseconds
*   2.  Robin Hood HashTable took: 172870800 nanoseconds
*       Quadratic Probing HashTable took: 173438000 nanoseconds
*   3.  Robin Hood HashTable took: 135631500 nanoseconds
*       Quadratic Probing HashTable took: 164165800 nanoseconds
*   4.  Robin Hood HashTable took: 202467300 nanoseconds
*       Quadratic Probing HashTable took: 166633000 nanoseconds
*   5.  Robin Hood HashTable took: 181901400 nanoseconds
*       Quadratic Probing HashTable took: 149796300 nanoseconds
*   6.  Robin Hood HashTable took: 221485300 nanoseconds
*       Quadratic Probing HashTable took: 192651200 nanoseconds
*   7.  Robin Hood HashTable took: 121459500 nanoseconds
*       Quadratic Probing HashTable took: 134997600 nanoseconds
*   8.  Robin Hood HashTable took: 122334300 nanoseconds
*       Quadratic Probing HashTable took: 138162800 nanoseconds
*   9.  Robin Hood HashTable took: 200463700 nanoseconds
*       Quadratic Probing HashTable took: 132510800 nanoseconds
*   10. Robin Hood HashTable took: 117249000 nanoseconds
*       Quadratic Probing HashTable took: 133193400 nanoseconds
* Moyennes :
 * Robin Hood HashTable : 161005470 ns
 * Quadratic Probing HashTable : 155111170 ns
*
* Nombre de collision avec le inputArrays.txt
* (le code utilisé pour cette étape à été mis en commentaire):
 *  Robin Hood number of collision: 856076
 *  Quadratic Probing number of collision: 801022
*
* Analyse comparative des performances des tables de hachage Robin Hood et Quadratic Probing :
 *
 * Les données recueillies lors de l'exécution des tests de performance indiquent
 * des différences significatives entre les tables de hachage Robin Hood et Quadratic Probing.
 * Ces différences se manifestent tant au niveau du temps moyen d'exécution que du nombre de collisions.
 *
 *  Temps moyen d'exécution :
 *      Les mesures montrent que la table de hachage Robin Hood présente un temps moyen d'exécution
 *      de 161005470 nanosecondes, alors que la table de hachage Quadratic Probing
 *      affiche un temps moyen d'exécution de 155111170 nanosecondes.
 *      Le Quadratic Probing semble légèrement plus rapide que le Robin Hood HashTable.
 *
 *      La table de hachage Robin Hood présente une variabilité plus importante
 *      dans ses temps d'exécution. Elle peut parfois surpasser la table de hachage Quadratic Probing,
 *      mais dans d'autres cas, elle peut être significativement plus lente.
 *      Cette variabilité peut être un désavantage si une exécution rapide et prévisible est essentielle.
 *
 *  Nombre de collisions :
 *     Le Robin Hood HashTable présente un total de 856076 collisions et
 *     le Quadratic Probing HashTable en compte 801 022.
 *     Le Robin Hood HashTable affiche un nombre de collisions supérieur, mais ce nombre élevé
 *     de collisions semble s'accompagner d'une meilleure efficacité dans des situations idéales.
 *
 *  Avantages et désavantages :
 *
 *      Avantages du Robin Hood HashTable :
 *          Peut être plus rapide que le Quadratic Probing HashTable dans des conditions idéales
 *
 *     Désavantages du Robin Hood HashTable :
 *         La variabilité des performances peut poser des problèmes si l'on nécessite une latence prévisible.
 *         Le nombre élevé de collisions peut être un désavantage dans certaines situations.
 *
 *     Avantages du Quadratic Probing HashTable :
 *         Offre une exécution plus prévisible et des performances stables dans diverses situations.
 *         Le nombre de collisions est plus faible peut être un avantage dans des situations sensibles à la latence.
 *
 *     Désavantages du Quadratic Probing HashTable :
 *         Peut être légèrement plus lent que le Robin Hood HashTable dans des conditions idéales.
 *
 * Le choix entre le Robin Hood HashTable et le Quadratic Probing HashTable
 * dépend des besoins spécifiques de l'application. Le Robin Hood HashTable peut exceller dans
 * des conditions idéales avec une faible charge, alors que le Quadratic Probing HashTable offre
 * une performance plus stable et prévisible, ce qui peut être essentiel dans des environnements
 * critiques en termes de temps d'exécution.
 *
 * */