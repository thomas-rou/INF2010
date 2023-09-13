import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreatureTest {

    private Creature creatureParDefaut;
    private Creature creature_1;
    private Creature creature_2;

    @BeforeEach
    public void initialiser() {
        creatureParDefaut = new Creature();
        creature_1 = new Creature("Rattata", 30, 20, 80);
        creature_2 = new Creature("Miaouss", 15, 10, 70);
    }

    @Test
    public void testConstructeurParDefaut() {
        assertEquals("", creatureParDefaut.getNom());
        assertEquals(0, creatureParDefaut.getAttaque());
        assertEquals(0, creatureParDefaut.getDefense());
        assertEquals(Creature.MAX_VAL, creatureParDefaut.getPointDeVie());
    }

    @Test
    public void testConstructeurAvecDeuxParametres() {
        Creature creature = new Creature("Magicarpe", 25, 10);
        assertEquals("Magicarpe", creature.getNom());
        assertEquals(25, creature.getAttaque());
        assertEquals(10, creature.getDefense());
        assertEquals(Creature.MAX_VAL, creature.getPointDeVie());
    }

    @Test
    public void testConstructeurComplet() {
        assertEquals("Rattata", creature_1.getNom());
        assertEquals(30, creature_1.getAttaque());
        assertEquals(20, creature_1.getDefense());
        assertEquals(80, creature_1.getPointDeVie());
    }

    @Test
    public void testConstructeurCopie() {
        Creature creatureCopiee = new Creature(creature_1);
        assertEquals(creature_1.getNom(), creatureCopiee.getNom());
        assertEquals(creature_1.getAttaque(), creatureCopiee.getAttaque());
        assertEquals(creature_1.getDefense(), creatureCopiee.getDefense());
        assertEquals(creature_1.getPointDeVie(), creatureCopiee.getPointDeVie());
    }

    @Test
    public void testMethodesModification() {
        creatureParDefaut.setNom("Smogo");
        creatureParDefaut.setAttaque(20);
        creatureParDefaut.setDefense(15);
        creatureParDefaut.setPointDeVie(70);

        assertEquals("Smogo", creatureParDefaut.getNom());
        assertEquals(20, creatureParDefaut.getAttaque());
        assertEquals(15, creatureParDefaut.getDefense());
        assertEquals(70, creatureParDefaut.getPointDeVie());
    }

    @Test
    public void testAttaquer() {
        int pointDeVie = creature_2.getPointDeVie();
        int dommages = creature_1.getAttaque() - creature_2.getDefense();
        creature_1.attaquer(creature_2);
        assertEquals(pointDeVie - dommages, creature_2.getPointDeVie());
    }

    @Test
    public void testAttaquerWithBonus() {
        int bonus = 10;
        int pointDeVie = creature_2.getPointDeVie();
        int dommages = creature_1.getAttaque() - creature_2.getDefense() + bonus;
        creature_1.attaquer(creature_2, bonus);
        assertEquals(pointDeVie - dommages, creature_2.getPointDeVie());
    }

    @Test
    public void testDefendre() {
        creature_2.defendre(creature_1);
        assertEquals(50, creature_2.getPointDeVie());
    }

    @Test
    public void testPointDeVieSup() {
        creature_2.setPointDeVie(110);
        assertEquals(Creature.MAX_VAL, creature_2.getPointDeVie());
    }

    @Test
    public void testPointDeVieInf() {
        creature_2.setPointDeVie(-10);
        assertEquals(0, creature_2.getPointDeVie());
    }

    @Test
    public void testIncrementerVie() {
        creature_2.setPointDeVie(50, true);
        assertEquals(Creature.MAX_VAL, creature_2.getPointDeVie());
    }

    @Test
    public void testDecrementerVie() {
        creature_2.setPointDeVie(40, false);
        assertEquals(30, creature_2.getPointDeVie());
    }

    @Test
    public void estAffaibli() {
        creature_2.setPointDeVie(0);
        assertTrue(creature_2.estAffaibli());
        creature_2.setPointDeVie(50);
        assertFalse(creature_2.estAffaibli());
    }

    @Test
    public void testEgalite() {
        Creature creatureIdentique = new Creature("Rattata", 50, 25);
        assertTrue(creature_1.equals(creatureIdentique));
        assertFalse(creature_1.equals(creature_2));
    }

    @Test
    public void testToString() {
        assertEquals("Nom: Rattata, Attaque: 30, Défense: 20, Points de Vie: 80", creature_1.toString());
    }
}