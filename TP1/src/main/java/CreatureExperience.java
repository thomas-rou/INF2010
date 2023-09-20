public class CreatureExperience extends Creature implements Experience{
    private int forceExperience;
    // Constructeurs
    public CreatureExperience(){
        this("",0,0,MAX_VAL,0);
    }
    public CreatureExperience(String nom, int attaque, int defense, int pointDeVie, int forceExp){
        this.setNom(nom);
        this.setAttaque(attaque);
        this.setDefense(defense);
        this.setPointDeVie(pointDeVie);
        this.setExperience(forceExp);
    }
    public CreatureExperience(Creature creature, int forceExp){
        this(creature.getNom(),
                creature.getAttaque(),
                creature.getDefense(),
                creature.getPointDeVie(),
                forceExp);
    }
    public CreatureExperience(CreatureExperience cExp){
        this(cExp.getNom(),
                cExp.getAttaque(),
                cExp.getDefense(),
                cExp.getPointDeVie(),
                cExp.getExperience());
    }

    //Accesseurs et modificateurs
    public int getExperience(){
        return this.forceExperience;
    }

    public void setExperience(int exp){
        if(exp > MAX_VAL) {
            this.forceExperience = MAX_VAL;
        } else if (exp < 0) {
            this.forceExperience = 0;
        } else {
            this.forceExperience = exp;
        }
    }
    public void setExperience(int exp, boolean estPositive){
        if(estPositive){
            this.setExperience(this.getExperience() + exp);
        }
        else {
            this.setExperience(this.getExperience() - exp);
        }
    }

    /* Attaque une autre créature
     * 1. Calculez un bonus : getAttaque() * (forceExperience / 100)
     * 2. Attaquer avec ce bonus
     */
    public void attaquer(Creature creature){
        int dommage = this.getAttaque() + (this.getAttaque() * (this.getExperience() / 100)) - creature.getDefense();
        if(dommage > 0) {
            creature.setPointDeVie(dommage, false);
        }
    }
    /*
     * 1. Calculez un bonus : getAttaque() * ((forceExperience + expBonus) / 100)
     * 2. Attaquer avec ce bonus
     */
    public void attaquer(Creature creature, int expBonus){
        int dommage = this.getAttaque() + (this.getAttaque() + ((this.getExperience() + expBonus) / 100)) - creature.getDefense();
        if(dommage > 0) {
            creature.setPointDeVie(dommage, false);
        }
    }

    /* Se défend contre l'attaque d'une autre créature
     * 1. getDefense() * (forceExperience / 100)
     * 2. Se deffendre avec ce bonus
     */
    public void defendre(Creature creature){
        int defBonus = (int) (this.getDefense() * (this.getExperience() / 100.0));
        int dommage = creature.getAttaque() - (this.getDefense() + defBonus);
        if(dommage > 0){
            this.setPointDeVie(dommage, false);
        }
    }
    /*
     * 1. getDefense() * ((forceExperience + expBonus) / 100)
     * 2. Se deffendre avec ce bonus
     */
    public void defendre(Creature creature, int expBonus){
        int defBonus = (int) (this.getDefense() * ((this.getExperience() + expBonus) / 100.0));
        int dommage = creature.getAttaque() - (this.getDefense() + defBonus);
        if(dommage > 0){
            this.setPointDeVie(dommage, false);
        }
        this.setExperience(expBonus, false);
    }

    public void afficher(){}

    public String toString(){
        return "[Creature Experience] -> Nom: " + this.getNom() + ", Attaque: " + this.getAttaque() + ", Défense: " + this.getDefense() + ", Points de Vie: " + this.getPointDeVie() + ", Force Experience: " + this.getExperience();
    }
}