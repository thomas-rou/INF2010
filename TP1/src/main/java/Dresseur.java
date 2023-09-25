import java.util.ArrayList;
import java.util.Objects;

public class Dresseur {
    protected ArrayList<Creature> creatures_;
    protected String nom_;

    /** Constructeur avec nom en paramètre */
    public Dresseur(String nom) {
        this.setNom(nom);
        this.creatures_ = new ArrayList<>();
    }

    /** Constructeur par copie */
    public Dresseur(Dresseur dresseur) {
        this(dresseur.getNom());
        for (int i = 0; i < dresseur.getCreatures().size(); i++) {
            this.creatures_.add(new Creature());
        }
    }

    /** Méthodes d'accès et de modification du nom du dresseur */
    public String getNom(){
        return this.nom_;
    }

    public void setNom(String nouveauNom) {
        this.nom_ = nouveauNom;
    }

    /** Modifie la liste des créatures */
    public void setCreatures(ArrayList<Creature> creatures){
            this.creatures_.addAll(creatures);
    }

    /** Obtient le nombre de créatures possédées par le dresseur */
    public int getNombreCreatures(){
        return this.creatures_.size();
    }

    /** Obtient une liste des créatures du dresseur */
    public ArrayList<Creature> getCreatures(){
        return this.creatures_;
    }

    /** Si la créature est affaiblie, elle est ajoutée à la liste des créatures du dresseur */
   public boolean attraperCreature(Creature creature){
        if (creature.estAffaibli()){
            return this.ajouterCreature(creature);
        }
        return false;
   }

    /** Ajoute une créature à la liste */
    public boolean ajouterCreature(Creature creature) {
        if(!creatures_.contains(creature)){
            return this.creatures_.add(creature);
        }
        return false;
    }

    /** Retire une créature de la liste par son nom */
    public boolean supprimerCreature(String nom) {
        return creatures_.removeIf(creature -> creature.getNom().equals(nom));
    }

    /** Recherche une créature par son nom et la renvoie */
    public Creature getCreatureParNom(String nom) {
        for (Creature creature : creatures_) {
            if (creature.getNom().equals(nom)) {
                return creature;
            }
        }
        return null;
    }

    /** Surcharge des opérateurs de comparaison */
    public boolean equals(Object objet) {
        if(this == objet){
            return true; //même référence
        }
        else if(objet == null || getClass() != objet.getClass()){
            return false; //Classes différentes ou nulle
        }
        Dresseur autreDresseur = (Dresseur) objet;

        return Objects.equals(this.nom_, autreDresseur.nom_)
                && Objects.equals(this.creatures_, autreDresseur.creatures_);
    }

    public boolean equals(String nom) {
        return Objects.equals(this.nom_, nom);
    }

    public boolean equals(String nom, Object objet){
        if(objet == null || getClass() != objet.getClass()){
            return false;
        }
        Dresseur autreDresseur = (Dresseur) objet;

        return Objects.equals(this.nom_, autreDresseur.nom_)
                && Objects.equals(this.nom_, nom)
                && Objects.equals(this.creatures_, autreDresseur.creatures_);

    }

    public int hashCode(){
        return Objects.hash(nom_);
    }

    /** Surcharge de l'opérateur << pour afficher les informations d'un dresseur */
    public String toString() {
        StringBuilder infoDressseur = new StringBuilder();
        infoDressseur.append('[' + this.getNom() + "]\n");
        for (Creature creature : this.creatures_) {
            infoDressseur.append("\t").append(creature).append("\n");
        }

        return infoDressseur.toString();
    }
}




