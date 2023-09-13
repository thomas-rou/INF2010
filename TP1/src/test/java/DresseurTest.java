import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DresseurTest {

    private Dresseur dresseur;
    private Creature creature1;
    private Creature creature2;

    private Creature creatureFaible;

    @BeforeEach
    public void initialiser() {
        dresseur = new Dresseur("Ash");
        creature1 = new Creature("Pikachu", 30, 20, 70);
        creature2 = new Creature("Bulbasaur", 20, 30, 80);
        creatureFaible = new Creature("Rattata", 10, 10, 0);
    }

    @Test
    public void testConstructeur() {
        assertEquals("Ash", dresseur.getNom());
        assertEquals(0, dresseur.getNombreCreatures());
    }

    @Test
    public void testConstructeurCopie() {
        dresseur.ajouterCreature(creature1);
        dresseur.ajouterCreature(creature2);

        Dresseur dresseurCopie = new Dresseur(dresseur);
        assertEquals(dresseur.getNom(), dresseurCopie.getNom());
        assertEquals(dresseur.getNombreCreatures(), dresseurCopie.getNombreCreatures());

        assertFalse(dresseur.getCreatures() == dresseurCopie.getCreatures());

        for (int i = 0; i < dresseur.getCreatures().size(); i++) {
            assertFalse(dresseur.getCreatures().get(i) == dresseurCopie.getCreatures().get(i));
        }
    }

    @Test void testGetNombreCreatures() {
        assertEquals(0, dresseur.getNombreCreatures());
        dresseur.ajouterCreature(creature1);
        assertEquals(1, dresseur.getNombreCreatures());
    }

    @Test
    public void testGetCreature() {
        dresseur.ajouterCreature(creature1);
        assertEquals(creature1, dresseur.getCreature("Pikachu"));
        assertNull(dresseur.getCreature("Charizard"));
    }

    @Test
    public void testSetNom() {
        dresseur.setNom("Misty");
        assertEquals("Misty", dresseur.getNom());
    }

    @Test
    public void testSetCreatures() {
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(creature1);
        creatures.add(creature2);
        dresseur.setCreatures(creatures);
        assertEquals(creatures, dresseur.getCreatures());
    }

    @Test
    public void testAttraperCreature() {
        assertTrue(dresseur.attraperCreature(creatureFaible));
        assertEquals(1, dresseur.getNombreCreatures());
        assertFalse(dresseur.attraperCreature(creature1));
        assertFalse(dresseur.attraperCreature(creatureFaible));
    }

    @Test
    public void testAjouterCreature() {
        assertTrue(dresseur.ajouterCreature(creature1));
        assertEquals(1, dresseur.getNombreCreatures());
        assertFalse(dresseur.ajouterCreature(creature1));
    }

    @Test
    public void testSupprimerCreature() {
        dresseur.ajouterCreature(creature1);
        assertTrue(dresseur.supprimerCreature("Pikachu"));
        assertEquals(0, dresseur.getNombreCreatures());
        assertFalse(dresseur.supprimerCreature("Charizard"));
    }

    @Test
    public void testEgaliteEtHashCode() {
        Dresseur dresseur2 = new Dresseur("Ash");
        assertEquals(dresseur, dresseur2);
        assertEquals(dresseur.hashCode(), dresseur2.hashCode());

        Dresseur dresseur3 = new Dresseur("Brock");
        assertNotEquals(dresseur, dresseur3);
        assertNotEquals(dresseur.hashCode(), dresseur3.hashCode());
    }

    @Test
    public void testToString() {
        dresseur.ajouterCreature(creature1);
        String chaineAttendue = "[Ash]\n\tNom: Pikachu, Attaque: 30, Défense: 20, Points de Vie: 70\n";
        assertEquals(chaineAttendue, dresseur.toString());
    }
}
