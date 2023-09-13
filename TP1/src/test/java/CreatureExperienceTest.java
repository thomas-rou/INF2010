import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreatureExperienceTest {

    private CreatureExperience creatureExp;
    private Creature creatureCible;

    @BeforeEach
    public void initialiser() {
        creatureExp = new CreatureExperience("Osselait", 50, 20, 100, 10);
        creatureCible = new Creature("Pikachu", 30, 20, 70);
    }

    @Test
    public void testConstructeurParDefaut() {
        CreatureExperience creature = new CreatureExperience();
        assertEquals(0, creature.getExperience());
    }

    @Test
    public void testConstructeurAvecParametres() {
        assertEquals("Osselait", creatureExp.getNom());
        assertEquals(50, creatureExp.getAttaque());
        assertEquals(20, creatureExp.getDefense());
        assertEquals(100, creatureExp.getPointDeVie());
        assertEquals(10, creatureExp.getExperience());
    }

    @Test
    public void testConstructeurAvecCreatureEtExperience() {
        Creature creature = new Creature("Charizard", 60, 25, 90);
        CreatureExperience creatureExpAvecCreature = new CreatureExperience(creature, 15);

        assertEquals(creature.getNom(), creatureExpAvecCreature.getNom());
        assertEquals(creature.getAttaque(), creatureExpAvecCreature.getAttaque());
        assertEquals(creature.getDefense(), creatureExpAvecCreature.getDefense());
        assertEquals(creature.getPointDeVie(), creatureExpAvecCreature.getPointDeVie());
        assertEquals(15, creatureExpAvecCreature.getExperience());
    }

    @Test
    public void testConstructeurCopie() {
        CreatureExperience creatureExpCopie = new CreatureExperience(creatureExp);
        assertEquals(creatureExp.getNom(), creatureExpCopie.getNom());
        assertEquals(creatureExp.getAttaque(), creatureExpCopie.getAttaque());
        assertEquals(creatureExp.getDefense(), creatureExpCopie.getDefense());
        assertEquals(creatureExp.getPointDeVie(), creatureExpCopie.getPointDeVie());
        assertEquals(creatureExp.getExperience(), creatureExpCopie.getExperience());
    }
    @Test
    public void testSetExperienceSup() {
        creatureExp.setExperience(CreatureExperience.MAX_VAL + 10);
        assertEquals(CreatureExperience.MAX_VAL, creatureExp.getExperience());
    }

    @Test
    public void testSetExperienceNegative() {
        creatureExp.setExperience(-5);
        assertEquals(0, creatureExp.getExperience());
    }

    @Test
    public void testSetExperience() {
        creatureExp.setExperience(5);
        assertEquals(5, creatureExp.getExperience());
    }

    @Test
    public void testSetExperienceIncrement() {
        creatureExp.setExperience(10, true);
        assertEquals(20, creatureExp.getExperience()); // Assuming starting exp is 10
    }

    @Test
    public void testSetExperienceDecrement() {
        creatureExp.setExperience(5, false);
        assertEquals(5, creatureExp.getExperience()); // Assuming starting exp is 10
    }

    @Test
    public void testAttaquer() {
        creatureExp.attaquer(creatureCible);
        assertTrue(creatureCible.getPointDeVie() < 70);
    }

    @Test
    public void testAttaquerAvecExpBonus() {
        int vieAvantAttaque = creatureCible.getPointDeVie();
        creatureExp.attaquer(creatureCible, 10);
        assertTrue(creatureCible.getPointDeVie() < vieAvantAttaque);
    }

    @Test
    public void testDefendre() {
        int pointDeVieAvantDefense = creatureExp.getPointDeVie();
        int defenseBonus = (int) (creatureExp.getDefense() * (creatureExp.getExperience() / 100.0));
        int dommage = ( creatureCible.getAttaque() - (creatureExp.getDefense() + defenseBonus) );
        creatureExp.defendre(creatureCible);
        assertEquals(pointDeVieAvantDefense - dommage, creatureExp.getPointDeVie());
    }

    @Test
    public void testDefendreAvecExpBonus() {
        int expBonus = 10;
        int vieAvantDefense = creatureExp.getPointDeVie();
        int experienceAvantDefense = creatureExp.getExperience();

        int defenseBonus = (int) (creatureExp.getDefense() * (creatureExp.getExperience() + expBonus) / 100.0);
        int dommage = creatureCible.getAttaque() - ( creatureExp.getDefense() + defenseBonus );

        creatureExp.defendre(creatureCible, expBonus);

        assertEquals(vieAvantDefense - dommage, creatureExp.getPointDeVie());
        assertEquals(experienceAvantDefense - expBonus, creatureExp.getExperience());
    }

    @Test
    public void testAfficher() {
        String resultatAttendu = "[Creature Experience] -> Nom: Osselait, Attaque: 50, Défense: 20, Points de Vie: 100, Force Experience: 10";
        assertEquals(resultatAttendu, creatureExp.toString());
    }

    @Test
    public void testObtenirExperience() {
        assertEquals(10, creatureExp.getExperience());
    }
}