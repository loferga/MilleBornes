package fr.loferga.carte;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import fr.loferga.carte.Probleme.Type;

class EqualsTest {
	
	private static final Attaque ATK_ACC_1 = new Attaque(3, Type.ACCIDENT);
	private static final Attaque ATK_ACC_2 = new Attaque(2, Type.ACCIDENT);
	private static final Attaque ATK_CRV = new Attaque(3, Type.CREVAISON);
	
	private static final Borne BRN_50_1 = new Borne(4, 50);
	private static final Borne BRN_50_2 = new Borne(2, 50);
	private static final Borne BRN_75 = new Borne(4, 75);
	
	private static final Botte BTE_ACC_1 = new Botte(4, Type.ACCIDENT);
	private static final Botte BTE_ACC_2 = new Botte(2, Type.ACCIDENT);
	private static final Botte BTE_CRV = new Botte(4, Type.CREVAISON);
	
	private static final DebutLimite DL1 = new DebutLimite(4);
	private static final DebutLimite DL2 = new DebutLimite(2);
	
	private static final FinLimite FL1 = new FinLimite(4);
	private static final FinLimite FL2 = new FinLimite(2);
	
	private static final Parade PRD_ACC_1 = new Parade(3, Type.ACCIDENT);
	private static final Parade PRD_ACC_2 = new Parade(2, Type.ACCIDENT);
	private static final Parade PRD_CRV = new Parade(3, Type.CREVAISON);

	@Test
	void test_atk_equals() {
		assertEquals(ATK_ACC_1, ATK_ACC_1);
		assertEquals(ATK_ACC_1, ATK_ACC_2);
		assertNotEquals(ATK_ACC_1, ATK_CRV);
	}
	
	@Test
	void test_brn_equals() {
		assertEquals(BRN_50_1, BRN_50_1);
		assertEquals(BRN_50_1, BRN_50_2);
		assertNotEquals(BRN_50_1, BRN_75);
	}
	
	@Test
	void test_bte_equals() {
		assertEquals(BTE_ACC_1, BTE_ACC_1);
		assertEquals(BTE_ACC_1, BTE_ACC_2);
		assertNotEquals(BTE_ACC_1, BTE_CRV);
	}
	
	@Test
	void test_dl_equals() {
		assertEquals(DL1, DL1);
		assertEquals(DL1, DL2);
	}
	
	@Test
	void test_fl_equals() {
		assertEquals(FL1, FL1);
		assertEquals(FL1, FL2);
	}
	
	@Test
	void test_pard_equals() {
		assertEquals(PRD_ACC_1, PRD_ACC_1);
		assertEquals(PRD_ACC_1, PRD_ACC_2);
		assertNotEquals(PRD_ACC_1, PRD_CRV);
	}

}
