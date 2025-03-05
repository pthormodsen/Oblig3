import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TabellMengdeTest {

    private MengdeADT<Integer> mengde1;
    private MengdeADT<Integer> mengde2;
    private MengdeADT<Integer> mengde3;

    @BeforeEach
    void setUp() {
        mengde1 = new TabellMengde<>(3);
        mengde2 = new TabellMengde<>(3);
        mengde3 = new TabellMengde<>(3);
    }

    @Test
    void testErTom(){
        assertTrue(mengde1.erTom());
    }

    @Test
    void testLeggTill(){
        mengde1.leggTil(2);
        assertFalse(mengde1.erTom());
    }

    @Test
    void testInneholder(){
        mengde1.leggTil(2);
        assertFalse(mengde1.inneholder(1));
        assertTrue(mengde1.inneholder(2));
    }

    @Test
    void testErDelmengdeAv(){
        mengde1.leggTil(2);
        mengde2.leggTil(2);
        mengde2.leggTil(3);
        assertTrue(mengde1.erDelmengdeAv(mengde2));
    }

    @Test
    void testErLik(){
        mengde1.leggTil(2);
        mengde2.leggTil(2);
        mengde3.leggTil(3);
        assertTrue(mengde1.erLik(mengde2));
        assertFalse(mengde1.erLik(mengde3));
    }

    @Test
    void testErDisjunkt(){
        mengde1.leggTil(2);
        mengde1.leggTil(4);

        mengde2.leggTil(3);
        mengde2.leggTil(5);

        mengde3.leggTil(2);
        mengde3.leggTil(5);

        assertTrue(mengde1.erDisjunkt(mengde2));
        assertFalse(mengde1.erDisjunkt(mengde3));
    }

    @Test
    void testSnitt(){
        mengde1.leggTil(2);
        mengde1.leggTil(4);

        mengde2.leggTil(2);
        mengde2.leggTil(5);

        MengdeADT<Integer> snitt = mengde1.snitt(mengde2);

        assertEquals(1, snitt.antallElementer());
        assertTrue(snitt.inneholder(2));
    }

    @Test
    void testUnion(){
        mengde1.leggTil(2);
        mengde1.leggTil(4);

        mengde2.leggTil(2);
        mengde2.leggTil(5);

        MengdeADT<Integer> union = mengde1.union(mengde2);

        assertEquals(4, union.antallElementer());
        assertTrue(union.inneholder(2));
        assertTrue(union.inneholder(4));
        assertTrue(union.inneholder(5));
    }

    @Test
    void testMinus() {
        mengde1.leggTil(2);
        mengde1.leggTil(4);
        mengde1.leggTil(6);

        mengde2.leggTil(2);
        mengde2.leggTil(5);

        MengdeADT<Integer> minus = mengde1.minus(mengde2);

        assertEquals(2, minus.antallElementer());
        assertTrue(minus.inneholder(4));
        assertTrue(minus.inneholder(6));
        assertFalse(minus.inneholder(2));
    }

    @Test
    void testLeggTilAlleFra() {
        mengde1.leggTil(1);
        mengde1.leggTil(2);

        mengde2.leggTil(2);
        mengde2.leggTil(3);

        mengde1.leggTilAlleFra(mengde2);


        assertEquals(3, mengde1.antallElementer());
        assertTrue(mengde1.inneholder(1));
        assertTrue(mengde1.inneholder(2));
        assertTrue(mengde1.inneholder(3));
    }

    @Test
    void testFjern() {
        // Legger til noen elementer
        mengde1.leggTil(1);
        mengde1.leggTil(2);
        mengde1.leggTil(3);

        Integer fjernetElement = mengde1.fjern(2);

        assertEquals(2, fjernetElement);
        assertFalse(mengde1.inneholder(2));

        assertTrue(mengde1.inneholder(1));
        assertTrue(mengde1.inneholder(3));
    }

    @Test
    void testTilTabell() { //Hater denne

        mengde1.leggTil(1);
        mengde1.leggTil(2);
        mengde1.leggTil(3);

        Integer[] tabell = mengde1.tilTabell();

        assertEquals(3, tabell.length);

        List<Integer> list = Arrays.asList(tabell);

        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
    }



    @Test
    void testAntallElementer() {
        mengde1.leggTil(1);
        mengde1.leggTil(2);
        mengde1.leggTil(3);

        assertEquals(3, mengde1.antallElementer());
    }

}