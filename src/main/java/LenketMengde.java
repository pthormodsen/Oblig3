public class LenketMengde<T> implements MengdeADT<T> {

    public class Node {
        private T data;
        private Node neste;

        private Node(T data){
            this.data = data;
            this.neste = null;
        }
    }

    private Node forste;
    private int antall;

    public LenketMengde() {
        forste = null;
        antall = 0;
    }


    @Override
    public boolean erTom() {
        return antall == 0;
    }

    @Override
    public boolean inneholder(T element) {
        Node temp = forste;
        while(temp != null){
            if(temp.data.equals(element)){
                return true;
            }
            temp = temp.neste;
        }
        return false;
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        Node temp = forste;

        while(temp != null){
            if(!annenMengde.inneholder(temp.data)){
                return false;
            }
            temp = temp.neste;
        }
        return true;
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        Node temp = forste;

        if (antall != annenMengde.antallElementer()) {
            return false;
        }

        while(temp != null){
            if(!annenMengde.inneholder(temp.data)){
                return false;
            }
            temp = temp.neste;
        }
        return true;
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        Node temp = forste;

        while(temp != null){
            if(annenMengde.inneholder(temp.data)){
                return false;
            }
            temp = temp.neste;
        }
        return true;
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        LenketMengde<T> nyMengde = new LenketMengde<>();
        Node temp = forste;
        while(temp != null){
            if(annenMengde.inneholder(temp.data)) {
                nyMengde.leggTil(temp.data);
            }
            temp = temp.neste;
        }
        return nyMengde;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        LenketMengde<T> nyMengde = new LenketMengde<>();
        Node temp = forste;

        while(temp != null){
            nyMengde.leggTil(temp.data);
            temp = temp.neste;
        }

        Node tempAnnenMengde = forste(annenMengde);

        while(tempAnnenMengde != null){
            if(!nyMengde.inneholder(tempAnnenMengde.data)){
                nyMengde.leggTil(tempAnnenMengde.data);
            }
            tempAnnenMengde = tempAnnenMengde.neste;
        }

        return nyMengde;
    }


    private Node forste(MengdeADT<T> mengde){
        if(mengde instanceof LenketMengde){
            return ((LenketMengde<T>)mengde).forste;
        }
        throw new IllegalArgumentException("Mengde er ikke av typen LenketMengde");
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        LenketMengde<T> nyMengde = new LenketMengde<>();
        Node temp = forste;

        while(temp != null){
            if(!annenMengde.inneholder(temp.data)){
                nyMengde.leggTil(temp.data);
            }
            temp = temp.neste;
        }
        return nyMengde;
    }

    @Override
    public void leggTil(T element) {
        if (inneholder(element)) {
            return;
        }
        Node nyNode = new Node(element);
        nyNode.neste = forste;
        forste = nyNode;
        antall++;
    }

    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
        Node temp = ((LenketMengde<T>) annenMengde).forste;

        while (temp != null) {
            if (!inneholder(temp.data)) {
                leggTil(temp.data);
            }
            temp = temp.neste;
        }
    }

    @Override
    public T fjern(T element) {

        T elementTilbake = null;
        Node temp = forste;
        Node forrige = null;

        while(temp != null){
            if(element.equals(temp.data)){
                elementTilbake = temp.data;
                if(forrige == null){
                    forste = forste.neste;
                }else{
                    forrige.neste = temp.neste;
                }
                antall--;
                break;
            }
            forrige = temp;
            temp = temp.neste;
        }
        return elementTilbake;
    }

    @Override
    public T[] tilTabell() {
        T[] tabell = (T[]) new Object[antall];
        Node temp = forste;
        int i = 0;
        while(temp != null){
            tabell[i++] = temp.data;
            temp = temp.neste;
        }
        return tabell;
    }

    @Override
    public int antallElementer() {
        return antall;
    }
}
