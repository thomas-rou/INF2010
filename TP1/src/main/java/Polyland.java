import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class Polyland {
    // Liste des dresseurs présents dans PolyLand
    private ArrayList<Dresseur> dresseurs_;
    // Liste des créatures présentes dans PolyLand
    private ArrayList<Creature> creatures_;

    public Polyland() {
        dresseurs_ = new ArrayList<>();
        creatures_ = new ArrayList<>();
    }

    // Ajoute un dresseur à PolyLand s'il n'y est pas déjà
    // Retourne true si l'ajout a été effectué, sinon false
    public boolean ajouterDresseur(Dresseur dresseur){
        if (!dresseurs_.contains(dresseur)){
            return this.dresseurs_.add(dresseur);
        }
        return false;
    }
    // Ajoute une créature à PolyLand si elle n'y est pas déjà
    // Retourne true si l'ajout a été effectué, sinon false
    public boolean ajouterCreature(Creature creature){
        if(!creatures_.contains(creature)){
            return this.creatures_.add(creature);
        }
        return false;
    }

    // Retire un dresseur de PolyLand en utilisant son nom
    // Retourne true si le dresseur a été retiré, sinon false
    public boolean retirerDresseur(String nom){
        for(Dresseur dresseur : dresseurs_){
            if (dresseur.getNom().equals(nom)){
                return this.dresseurs_.remove(dresseur);
            }
        }
        return false;
    }
    // Retire une créature de PolyLand en utilisant son nom
    // Retourne true si la créature a été retirée, sinon false
    public boolean retirerCreature(String nom){
        for(Creature creature : creatures_){
            if (creature.getNom().equals(nom)){
                return this.creatures_.remove(creature);
            }
        }
        return false;
    }

    // Retourne un dresseur spécifique à partir de son nom
    public Dresseur trouverDresseur(String nom){
        for(Dresseur dresseur : dresseurs_){
            if(dresseur.getNom().equals(nom)){
                return dresseur;
            }
        }
        return null;
    }
    // Retourne une créature spécifique à partir de son nom
    public Creature trouverCreature(String nom){
        for(Creature creature : creatures_){
            if(creature.getNom().equals(nom)){
                return creature;
            }
        }
        return null;
    }

    // Choisit et retourne une créature aléatoirement
    public Creature choisirCreatureAleatoire(){
        if(creatures_.isEmpty()){
            return null;
        }
        Random random = new Random();
        int indexRandom = random.nextInt(creatures_.size());
        return creatures_.get(indexRandom);
    }

    // Choisit et retourne un dresseur aléatoirement
    public Dresseur choisirDresseurAleatoire(){
        if(dresseurs_.isEmpty()){
            return null;
        }
        Random random = new Random();
        int indexRandom = random.nextInt(dresseurs_.size());
        return dresseurs_.get(indexRandom);
    }

    // Opérateur de flux surchargé pour afficher PolyLand
    public String toString(){
        StringBuilder infoPolyland = new StringBuilder();

        infoPolyland.append("PolyLand:\n");
        infoPolyland.append("Dresseurs:\n");

        for (Dresseur dresseur : dresseurs_){
            infoPolyland.append(dresseur.toString()).append("\n");
        }

        infoPolyland.append("Creatures sans dresseur:\n");

        for (Creature creature : creatures_){
            infoPolyland.append(creature.toString()).append("\n");
        }

        return infoPolyland.toString();
    }
}
