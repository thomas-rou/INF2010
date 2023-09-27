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


