import java.io.PrintStream;

public class CreatureMagique extends Creature{
    public static final int PERTE_MAGIQUE = 5;
    private int forceMagique;
    private boolean aPotionMagique;
    private boolean estMagique;

    /** Constructeurs */
    public CreatureMagique(){
        this("",0,0,MAX_VAL,0,false,false);
    }

    public CreatureMagique(String nom, int attaque, int defense, int pointDeVie, int forceMagique, boolean aPotionMagique, boolean estMagique){
        this.setNom(nom);
        this.setAttaque(attaque);
        this.setDefense(defense);
        this.setPointDeVie(pointDeVie);
        this.setForceMagique(forceMagique);
        this.aPotionMagique = aPotionMagique;
        this.estMagique = estMagique;
    }

    public CreatureMagique(Creature creature, int forceMagique, boolean aPotionMagique, boolean estMagique){
        this(creature.getNom(),
                creature.getAttaque(),
                creature.getDefense(),
                creature.getPointDeVie(),
                forceMagique,
                aPotionMagique,
                estMagique);
    }

    public CreatureMagique(CreatureMagique cMagique){
        this(cMagique.getNom(),
                cMagique.getAttaque(),
                cMagique.getDefense(),
                cMagique.getPointDeVie(),
                cMagique.getForceMagique(),
                cMagique.getAPotionMagique(),
                cMagique.getEstMagique());
    }

    /** Accesseurs et modificateurs */
    public int getForceMagique(){return this.forceMagique;}

    public boolean getAPotionMagique(){return this.aPotionMagique;}

    public boolean getEstMagique(){return this.estMagique;}

    public void setForceMagique(int forceMagique){
        this.forceMagique = forceMagique;
    }

    public void setForceMagique(int forceMagique, boolean estPositive){
        if (estPositive){
            setForceMagique(this.getForceMagique() + forceMagique);
        }
        else {
            setForceMagique(this.getForceMagique() - forceMagique);
        }
    }

    /** Attaque une autre créature
     * Si la créature est magique, elle attaque en utilisant sa force magique
     * Sinon, elle utilise la méthode d'attaque de base de la classe parent
     */
    public void attaquer(Creature creature){
        if(this.getEstMagique()){
            this.attaquer(creature, this.getForceMagique());
        }
        else {
            super.attaquer(creature);
        }
    }

    /**
     * 1. Calculez un bonus de dommage en utilisant la force magique donnée : getAttaque() * (forceMagique / 100)
     * 2. Attaquez avec ce bonus
     * 3. Réduisez la force magique de la créature par PERTE_MAGIQUE
     */
    public void attaquer(Creature creature, int forceMagique){
        int magieBonus = (int) (this.getAttaque() * (forceMagique / 100.0));
        int dommageMagique = this.getAttaque() + magieBonus - creature.getDefense();
        if(dommageMagique > 0) {
            creature.setPointDeVie(dommageMagique, false);
        }
        this.setForceMagique(PERTE_MAGIQUE, false);
    }

    /** Se défend contre l'attaque d'une autre créature
     * Si la créature est magique, elle utilise son pouvoir magique pour contre-attaquer son adversaire
     * 	- L'autre creature s'attaque elle-meme
     * Sinon, elle utilise la méthode de défense de base de la classe parent
     */
    public void defendre(Creature creature){
        if(this.getEstMagique()){
            creature.attaquer(creature);
        }
        else {
            super.defendre(creature);
        }
    }

    /**
     * Si la créature a une potion magique, utilisez-la pour restaurer tous ses points de vie
     * et la rendre magique
     */
    public void utiliserPotionMagique(){
        if(this.getAPotionMagique()){
            this.setPointDeVie(MAX_VAL);
            this.estMagique = true;
            this.aPotionMagique = false;
        }
    }

    public void afficher(){
        System.out.print(this.toString());
    }

    public String toString(){
        return "[Creature Magique] -> Nom: " + this.getNom()
                + ", Attaque: " + this.getAttaque() + ", Défense: "
                + this.getDefense() + ", Points de Vie: " + this.getPointDeVie()
                + ", Force Magique: " + this.getForceMagique();
    }
}