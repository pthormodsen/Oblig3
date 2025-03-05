import java.util.HashSet;
import java.util.Set;

public class JavaSetToMengde<T> implements MengdeADT<T> {

    private Set<T> set;

    public JavaSetToMengde(){
        set = new HashSet<>();
    }

    public Set<T> getSet() {
        return this.set;
    }


    @Override
    public boolean erTom() {
        return set.isEmpty();
    }

    @Override
    public boolean inneholder(T element) {
        return set.contains(element);
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> annenJavaSet = (JavaSetToMengde<T>) annenMengde;
        return set.containsAll(annenJavaSet.getSet());
    }


    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> annenJavaSet = (JavaSetToMengde<T>) annenMengde;
        return set.equals(annenJavaSet.set);
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> annenJavaSet = (JavaSetToMengde<T>) annenMengde;
        for (T element : set) {
            if (annenJavaSet.set.contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> snitt = new JavaSetToMengde<>();
        JavaSetToMengde<T> annenJavaSet = (JavaSetToMengde<T>) annenMengde;
        for(T element : set){
            if(annenJavaSet.set.contains(element)){
                snitt.leggTil(element);
            }
        }
        return snitt;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> union = new JavaSetToMengde<>();
        union.set.addAll(set);
        union.set.addAll(((JavaSetToMengde<T>) annenMengde).set);
        return union;
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> minus = new JavaSetToMengde<>();
        JavaSetToMengde<T> annenJavaSet = (JavaSetToMengde<T>) annenMengde;
        for(T element : set){
            if(!annenJavaSet.set.contains(element)){
                minus.leggTil(element);
            }
        }
        return minus;
    }

    @Override
    public void leggTil(T element) {
    set.add(element);
    }

    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
    JavaSetToMengde<T> annenJavaSet = (JavaSetToMengde<T>) annenMengde;
    set.addAll(annenJavaSet.set);
    }

    @Override
    public T fjern(T element) {
        if(set.contains(element)){
            set.remove(element);
            return element;
        }
        return null;
    }

    @Override
    public T[] tilTabell() {
        return (T[]) set.toArray();
    }

    @Override
    public int antallElementer() {
        return set.size();
    }
}
