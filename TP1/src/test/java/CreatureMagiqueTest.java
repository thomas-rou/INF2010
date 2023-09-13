import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreatureMagiqueTest {
    private CreatureMagique creatureMag_1;
    private CreatureMagique creatureMag_2;
    private CreatureMagique creatureMag_3;
    private Creature creatureCible;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @BeforeEach
    public void initialiser() {
        creatureMag_1 = new CreatureMagique();
        creatureMag_2 = new CreatureMagique("Arbok", 30, 20, 80, 15, true, true);
        creatureMag_3 = new CreatureMagique("Dedenne", 20, 10, 60, 10, false, false);
        creatureCible = new Creature("Cottonee", 15, 10, 70);
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testConstructeurParDefaut() {
        assertEquals("", creatureMag_1.getNom());
        assertEquals(0, creatureMag_1.getAttaque());
        assertEquals(0, creatureMag_1.getDefense());
        assertEquals(Creature.MAX_VAL, creatureMag_1.getPointDeVie());
        assertEquals(0, creatureMag_1.getForceMagique());
        assertEquals(false, creatureMag_1.getAPotionMagique());
        assertEquals(false, creatureMag_1.getEstMagique());
    }

    @Test
    public void testConstructeurPersonnalise() {
        assertEquals("Arbok", creatureMag_2.getNom());
        assertEquals(30, creatureMag_2.getAttaque());
        assertEquals(20, creatureMag_2.getDefense());
        assertEquals(80, creatureMag_2.getPointDeVie());
        assertEquals(15, creatureMag_2.getForceMagique());
        assertEquals(true, creatureMag_2.getAPotionMagique());
        assertEquals(true, creatureMag_2.getEstMagique());
    }

    @Test
    public void testConstructeurAvecCreature() {
        Creature creatureBase = new Creature("Togepi", 20, 10, 60);
        CreatureMagique creatureMagique = new CreatureMagique(creatureBase, 10, true, true);

        assertEquals("Togepi", creatureMagique.getNom());
        assertEquals(20, creatureMagique.getAttaque());
        assertEquals(10, creatureMagique.getDefense());
        assertEquals(60, creatureMagique.getPointDeVie());
        assertEquals(10, creatureMagique.getForceMagique());
        assertEquals(true, creatureMagique.getAPotionMagique());
        assertEquals(true, creatureMagique.getEstMagique());
    }

    @Test
    public void testConstructeurCopie() {
        CreatureMagique creatureMagiqueCopie = new CreatureMagique(creatureMag_2);
        assertEquals(creatureMag_2.getNom(), creatureMagiqueCopie.getNom());
        assertEquals(creatureMag_2.getAttaque(), creatureMagiqueCopie.getAttaque());
        assertEquals(creatureMag_2.getDefense(), creatureMagiqueCopie.getDefense());
        assertEquals(creatureMag_2.getPointDeVie(), creatureMagiqueCopie.getPointDeVie());
        assertEquals(creatureMag_2.getForceMagique(), creatureMagiqueCopie.getForceMagique());
        assertEquals(creatureMag_2.getAPotionMagique(), creatureMagiqueCopie.getAPotionMagique());
        assertEquals(creatureMag_2.getEstMagique(), creatureMagiqueCopie.getEstMagique());
    }

    @Test
    public void testSetPotionMagique() {
        creatureMag_2.setForceMagique(5);
        assertEquals(5, creatureMag_2.getForceMagique());
    }

    @Test
    public void testerSetPotionMagiqueIncrement() {
        creatureMag_2.setForceMagique(5, true);
        assertEquals(20, creatureMag_2.getForceMagique());
    }

    @Test
    public void testSetPotionMagiqueDecrement() {
        creatureMag_2.setForceMagique(5, false);
        assertEquals(10, creatureMag_2.getForceMagique());
    }

    @Test
    public void testAttaque() {
        int pointDeVieAvantAttaque = creatureCible.getPointDeVie();
        int dommage = creatureMag_3.getAttaque() - creatureCible.getDefense();
        creatureMag_3.attaquer(creatureCible);
        assertEquals(pointDeVieAvantAttaque - dommage, creatureCible.getPointDeVie());
    }

    @Test
    public void testAttaqueMagique() {
        int pointDeVieAvantAttaque = creatureCible.getPointDeVie();
        int forceMagiqueAvantAttaque = creatureMag_2.getForceMagique();
        int dommageBonus = (int) (creatureMag_2.getAttaque() * creatureMag_2.getForceMagique() / 100.0);
        int dommage = (creatureMag_2.getAttaque() + dommageBonus) - creatureCible.getDefense();
        creatureMag_2.attaquer(creatureCible);
        assertEquals(pointDeVieAvantAttaque - dommage, creatureCible.getPointDeVie());
        assertEquals(forceMagiqueAvantAttaque - CreatureMagique.PERTE_MAGIQUE, creatureMag_2.getForceMagique());
    }

    @Test
    public void testDefendre() {
        int pointDeVieAvantAttaque = creatureMag_3.getPointDeVie();
        int dommage = creatureCible.getAttaque() - creatureMag_3.getDefense();
        creatureMag_3.defendre(creatureCible);
        assertEquals(pointDeVieAvantAttaque - dommage, creatureMag_3.getPointDeVie());
    }

    @Test
    public void testDefendreMagique() {
        int pointDeVieAvantAttaqueCible = creatureCible.getPointDeVie();
        int pointDeVieAvantAttaque_2 = creatureMag_2.getPointDeVie();
        int dommage = creatureCible.getAttaque() - creatureCible.getDefense();
        creatureMag_2.defendre(creatureCible);
        assertEquals(pointDeVieAvantAttaqueCible - dommage, creatureCible.getPointDeVie());
        assertEquals(pointDeVieAvantAttaque_2, creatureMag_2.getPointDeVie());
    }

    @Test
    public void testUtiliserPotionMagique() {
        creatureMag_2.utiliserPotionMagique();
        assertEquals(creatureMag_2.getPointDeVie(), creatureMag_2.MAX_VAL);
        assertEquals(creatureMag_2.getEstMagique(), true);
        assertEquals(creatureMag_2.getAPotionMagique(), false);
    }

    @Test
    public void testerToString() {
        assertEquals("[Creature Magique] -> Nom: Arbok, Attaque: 30, Défense: 20, Points de Vie: 80, Force Magique: 15", creatureMag_2.toString());
    }

    @Test
    void testAfficher() {
        creatureMag_2.afficher();
        assertEquals("[Creature Magique] -> Nom: Arbok, Attaque: 30, Défense: 20, Points de Vie: 80, Force Magique: 15", outContent.toString());
    }

}