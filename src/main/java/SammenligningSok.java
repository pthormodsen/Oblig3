import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class SammenligningSok {
    public static void main(String[] args) {

        int antallElementer = 100000;
        int antallSok = 10000;

        HashSet<Integer> hashSet = new HashSet<>();
        int[] tabell = new int[antallElementer];

        int tall = 376;

        for (int i = 0; i < antallElementer; i++) {
            hashSet.add(tall);
            tabell[i] = tall;
            tall = (tall + 45713) % 1000000;
        }

        Arrays.sort(tabell);

        Random random = new Random();
        int[] sokTall = new int[antallSok];
        for(int i = 0; i < antallSok; i++){
            sokTall[i] = random.nextInt(antallElementer);
        }

        long startTimeHash = System.nanoTime();
        int antallFunnet = 0;

        for (int i = 0; i < antallSok; i++) {
            if(hashSet.contains(sokTall[i])){
                antallFunnet++;
            }
        }

        long endTimeHash = System.nanoTime();
        long elapsedTimeHash = endTimeHash - startTimeHash;

        System.out.println("Antall funnet hash sok: " + antallFunnet + "   Tid brukt: " + elapsedTimeHash / 100000 + "ms");

        long startTimeBinary = System.nanoTime();
        int antallBinary = 0;
        for (int i = 0; i < antallSok; i++) {
            if(Arrays.binarySearch(tabell, sokTall[i]) >= 0){
                antallBinary++;
            }
        }

        long endTimeBinary = System.nanoTime();
        long elapsedTimeBinary = endTimeBinary - startTimeBinary;

        System.out.println("Antall funnet binært sok: " + antallBinary + "   Tid brukt: " + elapsedTimeBinary / 100000 + "ms");

    }
}
