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

        while (true){
            if (array[currentPos] == null){
                array[currentPos] = new HashEntry<AnyType>(x.element, true);
                array[currentPos].probeDistance = currentProbeDistance;
                if (++currentSize > array.length * 0.5)
                    rehash();
                return;
            }
            else if (array[currentPos].element == x.element){
                return;
            }
            else if (currentProbeDistance > array[currentPos].probeDistance){
                HashEntry<AnyType> temp = array[currentPos];
                array[currentPos] = new HashEntry<AnyType>(x.element, true);
                array[currentPos].probeDistance = currentProbeDistance;
                //++collisionNbr;
                if (++currentSize > array.length * 0.5) {
                    rehash();
                }
                if (temp.isActive){
                    x = temp;
                    offset = baseOffset;
                    currentProbeDistance = x.probeDistance;
                    initialPos = myhash(x.element);
                }
                else{
                    --currentSize;
                    return;
                }
            }
            else {
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
*
* Nombre de collision avec le inputArrays.txt (le code utilisé pour cette
* étape à été mis en commentaire):
*   Robin Hood number of collision: 856076
*   Quadratic Probing number of collision: 801022
*
* Analyse :
 * Il semble que RobinHoodHashTable à un temps moyen d'exécution plus faible
 * que QuadraticProbingHashTable, cependant sa variabilité est plus grande.
 * RobinHoodHashTable semble parfois prendre significativement plus de temps
 * que QuadraticProbingHashTable. Le nombre de collision semble plus élevé
 * pour RobinHoodHashTable (856076) que pour QuadraticProbingHashTable (801022)
 * cela semble indiqué que RobinHoodHashTable est plus efficace à gérer des situations
 * où il y a un grand nombre de collision.
 * */