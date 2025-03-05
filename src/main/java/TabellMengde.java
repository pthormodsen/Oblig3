import java.lang.reflect.Array;
import java.util.Arrays;

public class TabellMengde<T> implements MengdeADT<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] tab;
    private int antall;

    public TabellMengde() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public TabellMengde(int capacity) {
        tab = (T[]) new Object[capacity];
        antall = 0;
    }

    @Override
    public boolean erTom() {
        return antall == 0;
    }

    @Override
    public boolean inneholder(T element) {
        for (int i = 0; i < antall; i++) {
            if (element.equals(tab[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        for (int i = 0; i < antall; i++) {
            if (!annenMengde.inneholder(tab[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        if (this.antall != annenMengde.antallElementer()) {
            return false;
        }
        for (int i = 0; i < antall; i++) {
            if (!annenMengde.inneholder(tab[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        for( int i = 0; i < antall; i++ ) {
            if(annenMengde.inneholder(tab[i]) ) {
                return false;
            }
        }
        return true;
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        MengdeADT<T> snitt = new TabellMengde<>();
        for(int i = 0; i < antall; i++){
            if(annenMengde.inneholder(tab[i])){
                snitt.leggTil(tab[i]);
            }
        }
        return snitt;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {

        MengdeADT<T> union = new TabellMengde<>(antall + annenMengde.antallElementer());
        for(int i = 0; i < antall; i++){
            union.leggTil(tab[i]);
        }
        for(int i = 0; i < annenMengde.antallElementer(); i++){
            union.leggTil(annenMengde.tilTabell()[i]);
        }
        return union;
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        MengdeADT<T> minus = new TabellMengde<>();
        for(int i = 0; i < antall; i++){
            if(!annenMengde.inneholder(tab[i])){
                minus.leggTil(tab[i]);
            }
        }
        return minus;
    }

    @Override
    public void leggTil(T element) {
        if (antall < tab.length) {
            tab[antall] = element;
            antall++;
        }
    }

    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
        for(int i = 0; i < annenMengde.antallElementer(); i++){
            if(!inneholder(annenMengde.tilTabell()[i])){
                leggTil(annenMengde.tilTabell()[i]);
            }
        }
    }

    @Override
    public T fjern(T element) {
        T elementTilbake = null;
        for(int i = 0; i < antall; i++){
            if(element.equals(tab[i])){
                elementTilbake = tab[i];
                tab[i] = null;
                for(int j = i; j < antall - 1; j++){
                    tab[j] = tab[j + 1];
                }
                tab[antall - 1] = null;
                antall--;
                break;
            }
        }
        return elementTilbake;
    }

    @Override
    public T[] tilTabell() {

        T[] tabell = (T[]) new Object[antall];


        for (int i = 0; i < antall; i++) {
            tabell[i] = tab[i];
        }

        return tabell;
    }


    @Override
    public int antallElementer() {
        return antall;
    }
}
