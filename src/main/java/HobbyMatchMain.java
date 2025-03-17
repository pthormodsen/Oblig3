import java.util.HashSet;
import java.util.Set;

public class HobbyMatchMain {

    public static void main(String[] args) {
        Person adam = new Person("Adam", "løping", "venner");
        Person trym = new Person("Trym", "drikking", "spill");
        Person stian = new Person("Stian", "mat", "venner");

        double matchTrymAdam = match(trym, adam);
        double matchStianAdam = match(stian, adam);

        System.out.println("Match mellom Trym og Adam: " + matchTrymAdam);
        System.out.println("Match mellom Stian og Adam: " + matchStianAdam);

        System.out.println("Match mellom Adam og seg selv: " + match(adam, adam));

    }

    public static double match(Person a, Person b) {
        Set<String> hobbyerA = a.getHobbyer();
        Set<String> hobbyerB = b.getHobbyer();
        int antallFelles = 0;
        int kunHosA = 0;
        int kunHosB = 0;
        int antallTotalt = 0;

        Set<String> fellesHobbyer = new HashSet<>(hobbyerA);
        fellesHobbyer.retainAll(hobbyerB);
        antallFelles = fellesHobbyer.size();

        Set<String> kunA = new HashSet<>(hobbyerA);
        kunA.removeAll(hobbyerB);
        kunHosA = kunA.size();

        Set<String> kunB = new HashSet<>(hobbyerB);
        kunB.removeAll(hobbyerA);
        kunHosB = kunB.size();

        antallTotalt = hobbyerA.size() + hobbyerB.size() - antallFelles;

        double matchScore = antallFelles - ((double) (kunHosA + kunHosB) / antallTotalt);
        return matchScore;
    }
}
