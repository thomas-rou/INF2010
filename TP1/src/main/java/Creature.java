import java.util.Objects;

public class Creature {
    public static final int MAX_VIE = 100;
    private String nom;
    private int attaque;
    private int defense;
    private int pointDeVie;

    // constructeur par défaut
    public Creature(){
        this.setNom("");
        this.setAttaque(0);
        this.setDefense(0);
        this.setPointDeVie(MAX_VIE);
    }

    // Constructeur avec params spécifiques
    public Creature(String nom, int attaque, int defense, int pointDeVie){
        this.setNom(nom);
        this.setAttaque(attaque);
        this.setDefense(defense);
        this.setPointDeVie(pointDeVie);
    }

    // Constructeur de copie
    public Creature copy(){
        return new Creature(this.getNom(), this.getAttaque(), this.getDefense(), this.getPointDeVie());
    }

    // getters and setters
    public String getNom(){
        return this.nom;
    }
    public int getAttaque(){
        return this.attaque;
    }
    public int getDefense(){
        return this.defense;
    }
    public int getPointDeVie(){
        return this.pointDeVie;
    }

    public void setNom(String nouveauNom){
        this.nom = nouveauNom;
    }
    public void setAttaque(int attaque){
        this.attaque = attaque;
    }
    public void setDefense(int defense){
        this.defense = defense;
    }
    public void setPointDeVie(int pointDeVie){
        this.pointDeVie = pointDeVie;
    }

    /** Modifie les points de vie
     * Si estPositive est true, la valeur sera ajoutée
     * Si estPositive est false, la valeur sera soustraite
     */
    public void setPointDeVie(int pointDeVie, boolean estPositive){
        if(estPositive){
            this.setPointDeVie(this.getPointDeVie() + pointDeVie);
        }
        else {
            this.setPointDeVie(this.getPointDeVie() - pointDeVie);
        }
    }

    /** Attaque une autre créature
     * 1. Calculez les dommages comme étant la différence entre l'attaque de cette créature et la défense de la créature cible
     * 2. Si les dommages sont positifs, réduisez les points de vie de la créature cible du montant des dommages
     */
    public void attaquer(Creature creature){
        int dommage = this.getAttaque() - creature.getDefense();
        if(dommage > 0) {
            creature.setPointDeVie(dommage, false);
        }
    }

    // Ajoutez le bonus d'attaque à l'attaque de cette créature
    public void attaquer(Creature creature, int attaqueBonus){
        creature.setAttaque(creature.attaque + attaqueBonus);
    }

    /** Se défend contre l'attaque d'une autre créature
     * 1. Calculez les dommages comme étant la différence entre l'attaque de la créature attaquante et la défense de cette créature
     * 2. Si les dommages sont positifs, réduisez les points de vie de cette créature du montant des dommages
     */
    public void defendre(Creature creature){
        int dommage = creature.getAttaque() - this.getDefense();
        if(dommage > 0){
            this.setPointDeVie(dommage, false);
        }
    }

    // Ajoutez le bonus de défense à la défense de cette créature
    public void defendre(Creature creature, int defenseBonus){
        creature.setDefense(creature.defense + defenseBonus);
    }

    // Vérifie si la créature est affaiblie (point de vie égale à 0)
    public boolean estAffaibli() {
        return this.getPointDeVie() == 0;
    }

    public String toString(){
        return "Nom: " + this.getNom() + ", Attaque: " + this.getAttaque() + ", Défense: " + this.getDefense() + ", Points de Vie: " + this.getPointDeVie();
    }

    public boolean equals(Object objet){
        if(this == objet){
            return true; //même référence
        }
        else if(objet == null || getClass() != objet.getClass()){
            return false; //Classes différentes ou nulle
        }
        Creature autreCreature = (Creature) objet;

        // Comparaison des attributs pour l'égalité
        return this.attaque == autreCreature.attaque
                && this.defense == autreCreature.defense
                && this.pointDeVie == autreCreature.pointDeVie
                && Objects.equals(this.nom, autreCreature.nom);
    }
}