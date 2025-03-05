import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JavaSetToMengdeTest {

    private MengdeADT<Integer> javaSetToMengde1;
    private MengdeADT<Integer> javaSetToMengde2;
    private MengdeADT<Integer> javaSetToMengde3;

    @BeforeEach
    void setUp() {
        javaSetToMengde1 = new JavaSetToMengde<>();
        javaSetToMengde2 = new JavaSetToMengde<>();
        javaSetToMengde3 = new JavaSetToMengde<>();
    }

    @Test
    void testErTom(){
        assertTrue(javaSetToMengde1.erTom());
    }

    @Test
    void testLeggTil(){
        javaSetToMengde1.leggTil(2);
        assertFalse(javaSetToMengde1.erTom());
    }

    @Test
    void testInneholder(){
        javaSetToMengde1.leggTil(2);
        assertFalse(javaSetToMengde1.inneholder(1));
        assertTrue(javaSetToMengde1.inneholder(2));
    }

    @Test
    void testContainsAll() {
        JavaSetToMengde<Integer> set1 = new JavaSetToMengde<>();
        JavaSetToMengde<Integer> set2 = new JavaSetToMengde<>();

        set1.leggTil(1);
        set1.leggTil(2);
        set1.leggTil(3);

        set2.leggTil(2);
        set2.leggTil(3);

        System.out.println(set1.getSet());
        System.out.println(set2.getSet());

        assertTrue(set1.getSet().containsAll(set2.getSet()));
    }


    @Test
    void testErDelmengdeAv() {
        JavaSetToMengde<Integer> set1 = new JavaSetToMengde<>();
        JavaSetToMengde<Integer> set2 = new JavaSetToMengde<>();
        JavaSetToMengde<Integer> set3 = new JavaSetToMengde<>();

        set1.leggTil(1);
        set1.leggTil(2);
        set1.leggTil(3);

        set2.leggTil(1);
        set2.leggTil(2);

        set3.leggTil(4);

        assertTrue(set2.erDelmengdeAv(set1));
    }


    @Test
    void testErLik(){
        javaSetToMengde1.leggTil(2);
        javaSetToMengde2.leggTil(2);
        javaSetToMengde3.leggTil(3);
        assertTrue(javaSetToMengde1.erLik(javaSetToMengde2));
        assertFalse(javaSetToMengde1.erLik(javaSetToMengde3));
    }

    @Test
    void testErDisjunkt(){
        javaSetToMengde1.leggTil(2);
        javaSetToMengde1.leggTil(4);

        javaSetToMengde2.leggTil(3);
        javaSetToMengde2.leggTil(5);

        javaSetToMengde3.leggTil(2);
        javaSetToMengde3.leggTil(5);

        assertTrue(javaSetToMengde1.erDisjunkt(javaSetToMengde2));
        assertFalse(javaSetToMengde1.erDisjunkt(javaSetToMengde3));
    }

    @Test
    void testSnitt(){
        javaSetToMengde1.leggTil(2);
        javaSetToMengde1.leggTil(4);

        javaSetToMengde2.leggTil(2);
        javaSetToMengde2.leggTil(5);

        MengdeADT<Integer> snitt = javaSetToMengde1.snitt(javaSetToMengde2);

        assertEquals(1, snitt.antallElementer());
        assertTrue(snitt.inneholder(2));
    }

    @Test
    void testUnion(){
        javaSetToMengde1.leggTil(2);
        javaSetToMengde1.leggTil(4);

        javaSetToMengde2.leggTil(2);
        javaSetToMengde2.leggTil(5);

        MengdeADT<Integer> union = javaSetToMengde1.union(javaSetToMengde2);

        assertEquals(3, union.antallElementer());
        assertTrue(union.inneholder(2));
        assertTrue(union.inneholder(4));
        assertTrue(union.inneholder(5));
    }

    @Test
    void testMinus() {
        javaSetToMengde1.leggTil(2);
        javaSetToMengde1.leggTil(4);
        javaSetToMengde1.leggTil(6);

        javaSetToMengde2.leggTil(2);
        javaSetToMengde2.leggTil(5);

        MengdeADT<Integer> minus = javaSetToMengde1.minus(javaSetToMengde2);

        assertEquals(2, minus.antallElementer());
        assertTrue(minus.inneholder(4));
        assertTrue(minus.inneholder(6));
        assertFalse(minus.inneholder(2));
    }

    @Test
    void testLeggTilAlleFra() {
        javaSetToMengde1.leggTil(1);
        javaSetToMengde1.leggTil(2);

        javaSetToMengde2.leggTil(2);
        javaSetToMengde2.leggTil(3);

        javaSetToMengde1.leggTilAlleFra(javaSetToMengde2);

        assertEquals(3, javaSetToMengde1.antallElementer());
        assertTrue(javaSetToMengde1.inneholder(1));
        assertTrue(javaSetToMengde1.inneholder(2));
        assertTrue(javaSetToMengde1.inneholder(3));
    }

    @Test
    void testFjern() {
        javaSetToMengde1.leggTil(1);
        javaSetToMengde1.leggTil(2);
        javaSetToMengde1.leggTil(3);

        Integer fjernetElement = javaSetToMengde1.fjern(2);

        assertEquals(2, fjernetElement);
        assertFalse(javaSetToMengde1.inneholder(2));

        assertTrue(javaSetToMengde1.inneholder(1));
        assertTrue(javaSetToMengde1.inneholder(3));
    }

    @Test
    void testTilTabell() {
        javaSetToMengde1.leggTil(1);
        javaSetToMengde1.leggTil(2);
        javaSetToMengde1.leggTil(3);

        Integer[] tabell = javaSetToMengde1.tilTabell();

        assertEquals(3, tabell.length);

        List<Integer> list = Arrays.asList(tabell);

        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
    }

    @Test
    void testAntallElementer() {
        javaSetToMengde1.leggTil(1);
        javaSetToMengde1.leggTil(2);
        javaSetToMengde1.leggTil(3);

        assertEquals(3, javaSetToMengde1.antallElementer());
    }
}
