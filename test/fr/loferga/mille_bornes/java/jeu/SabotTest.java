package fr.loferga.mille_bornes.java.jeu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import fr.loferga.mille_bornes.java.core.carte.Attaque;
import fr.loferga.mille_bornes.java.core.carte.Botte;
import fr.loferga.mille_bornes.java.core.carte.Carte;
import fr.loferga.mille_bornes.java.core.carte.Parade;
import fr.loferga.mille_bornes.java.core.carte.Probleme.Type;
import fr.loferga.mille_bornes.java.core.jeu.OldSabot;

class SabotTest {
	
	private static OldSabot sabot;
	private static final Carte ATK_ACCIDENT = new Attaque(3, Type.ACCIDENT);
	private static final Carte PARRY_ACCIDENT = new Parade(3, Type.ACCIDENT);
	private static final Carte BTE_ACCIDENT = new Botte(1, Type.ACCIDENT);
	
	@BeforeEach
	void setUpBeforeEach() throws Exception {
		sabot = new OldSabot(110);
		sabot.ajouterFamilleCarte(ATK_ACCIDENT, PARRY_ACCIDENT, BTE_ACCIDENT);
	}

	@Test
	void test_piocher() {
		assertEquals(sabot.piocher(), ATK_ACCIDENT);
		assertEquals(sabot.piocher(), ATK_ACCIDENT);
		assertEquals(sabot.piocher(), ATK_ACCIDENT);
		assertEquals(sabot.piocher(), PARRY_ACCIDENT);
		assertEquals(sabot.piocher(), PARRY_ACCIDENT);
		assertEquals(sabot.piocher(), PARRY_ACCIDENT);
		assertEquals(sabot.piocher(), BTE_ACCIDENT);
		assertThrows(NoSuchElementException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				sabot.piocher();
			}
		});
	}
	
	@Test
	void test_unsupported_operation() {
		Iterator<Carte> it = sabot.iterator();
		assertThrows(UnsupportedOperationException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				it.remove();
			}
		});
	}
	
	@Test
	void test_concurrent_modification() {
		Iterator<Carte> it1 = sabot.iterator();
		Iterator<Carte> it2 = sabot.iterator();
		it1.next();
		it2.next();
		it2.remove();
		assertThrows(ConcurrentModificationException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				it1.remove();
			}
		});
		assertThrows(ConcurrentModificationException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				it1.next();
			}
		});
	}

}
