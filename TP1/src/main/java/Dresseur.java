import java.util.ArrayList;
import java.util.Objects;

public class Dresseur {

    protected ArrayList<Creature> creatures;
    protected String nom;

    // Constructeur avec nom en paramètre
    public Dresseur(String nom) {
        this.setNom(nom);
        this.creatures = new ArrayList<>();
    }

    // Constructeur par copie
    public Dresseur(Dresseur autre) {
        this(autre.getNom());
        this.setCreatures(autre.getCreatures());
    }

    // Méthodes d'accès et de modification du nom du dresseur

    public String getNom(){
        return this.nom;
    }
    public void setNom(String nouveauNom) {
        this.nom = nouveauNom;
    }

    // Modifie la liste des créatures

    public void setCreatures(ArrayList<Creature> creatures){
        for (int i = 0; i < creatures.size(); i++){
            this.creatures.set(i, creatures.get(i));

        }

    }

    // Obtient le nombre de créatures possédées par le dresseur
    public int getNombreCreatures(){

        return this.creatures.size();
    }

    // Obtient une liste des créatures du dresseur
    public ArrayList<Creature> getCreatures(){
        return this.creatures;
    }

    // Si la créature est affaiblie, elle est ajoutée à la liste des créatures du dresseur
   public boolean attraperCreature(Creature creature){
        if (creature.estAffaibli()){
            return this.ajouterCreature(creature);
        }
        else
            return false;
   }

    // Ajoute une créature à la liste
    public boolean ajouterCreature(Creature creature) {
        return this.creatures.add(creature);
    }

    // Retire une créature de la liste par son nom

    public boolean supprimerCreature(String nom) {
        return creatures.removeIf(creature -> creature.getNom().equals(nom));
    }



    // Recherche une créature par son nom et la renvoie
    public Creature getCreatureParNom(String nom) {
        for (Creature creature : creatures) {
            if (creature.getNom().equals(nom)) {
                return creature;
            }
        }
        return null;
    }

    // Surcharge des opérateurs de comparaison
    public boolean equals(Dresseur dresseur) {
        return this.nom.equals(dresseur.nom);
    }

    public boolean equals(String nom) {
        return this.nom.equals(nom);
    }

    // Surcharge de l'opérateur << pour afficher les informations d'un dresseur
    @Override
    public String toString() {
        return "Dresseur{" +
                "nom='" + nom + '\'' +
                ", creatures=" + creatures +
                '}';
    }

}




