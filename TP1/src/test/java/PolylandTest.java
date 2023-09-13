import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class PolylandTest {

    private Polyland polyland;
    private Dresseur dresseur1;
    private Dresseur dresseur2;
    private Creature creature1;
    private Creature creature2;
    private Creature creature3;

    @BeforeEach
    public void setUp() {
        polyland = new Polyland();
        dresseur1 = new Dresseur("Sacha");
        dresseur2 = new Dresseur("Flora");
        creature1 = new Creature("Pikachu", 30, 20, 70);
        creature2 = new Creature("Bulbizarre", 20, 30, 80);
        creature3 = new Creature("Dracaufeu", 50, 40, 90);
    }

    @Test
    public void testAjouterDresseur() {
        assertTrue(polyland.ajouterDresseur(dresseur1));
        assertFalse(polyland.ajouterDresseur(dresseur1));  // duplicate entry
    }

    @Test
    public void testAjouterCreature() {
        assertTrue(polyland.ajouterCreature(creature1));
        assertFalse(polyland.ajouterCreature(creature1));  // duplicate entry
    }

    @Test
    public void testRetirerDresseur() {
        polyland.ajouterDresseur(dresseur1);
        assertTrue(polyland.retirerDresseur("Sacha"));
        assertFalse(polyland.retirerDresseur("Sacha"));  // already removed
    }

    @Test
    public void testRetirerCreature() {
        polyland.ajouterCreature(creature1);
        assertTrue(polyland.retirerCreature("Pikachu"));
        assertFalse(polyland.retirerCreature("Pikachu"));  // already removed
    }

    @Test
    public void testChooseRandomCreature() {
        polyland.ajouterCreature(creature1);
        polyland.ajouterCreature(creature2);

        Set<Creature> uniqueChosen = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            uniqueChosen.add(polyland.choisirCreatureAleatoire());
        }

        assertTrue(uniqueChosen.contains(creature1));
        assertTrue(uniqueChosen.contains(creature2));
    }

    @Test
    public void testTrouverDresseur() {
        polyland.ajouterDresseur(dresseur1);
        polyland.ajouterDresseur(dresseur2);

        assertEquals(dresseur1, polyland.trouverDresseur("Sacha"));
        assertEquals(dresseur2, polyland.trouverDresseur("Flora"));
    }

    @Test
    public void testTrouverCreature() {
        polyland.ajouterCreature(creature1);
        polyland.ajouterCreature(creature2);
        polyland.ajouterCreature(creature3);

        assertEquals(creature1, polyland.trouverCreature("Pikachu"));
        assertEquals(creature2, polyland.trouverCreature("Bulbizarre"));
        assertEquals(creature3, polyland.trouverCreature("Dracaufeu"));
    }

    @Test
    public void testChoisirRandomCreature() {
        polyland.ajouterCreature(creature1);
        polyland.ajouterCreature(creature2);

        Set<Creature> uniqueChosen = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            uniqueChosen.add(polyland.choisirCreatureAleatoire());
        }

        assertTrue(uniqueChosen.contains(creature1));
        assertTrue(uniqueChosen.contains(creature2));
    }

    @Test
    public void testChoisirRandomDresseur() {
        polyland.ajouterDresseur(dresseur1);
        polyland.ajouterDresseur(dresseur2);

        Set<Dresseur> uniqueChosen = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            uniqueChosen.add(polyland.choisirDresseurAleatoire());
        }

        assertTrue(uniqueChosen.contains(dresseur1));
        assertTrue(uniqueChosen.contains(dresseur2));
    }

    @Test
    public void testToString() {
        polyland.ajouterDresseur(dresseur1);
        polyland.ajouterDresseur(dresseur2);

        polyland.ajouterCreature(creature1);
        dresseur1.ajouterCreature(creature1);
        dresseur2.ajouterCreature(creature2);

        polyland.ajouterCreature(creature3);

        String expected = "PolyLand:\n" +
                "Dresseurs:\n" +
                dresseur1.toString() + "\n" +
                dresseur2.toString() + "\n" +
                "Creatures sans dresseur:\n" +
                creature3.toString() + "\n";

        assertEquals(expected, polyland.toString());
    }
}