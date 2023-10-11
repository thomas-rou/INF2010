public class RobinHoodHashTable<AnyType> extends QuadraticProbingHashTable<AnyType> {
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

/*Mettre ici votre réponse pour executionTimeTest
*
*
*
*
*
*
* */