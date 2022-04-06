import java.util.Random;

public class Test {
    int[] zufalls;
    Random zufall = new Random();

    public Test(){
        zufalls = SelectRandomLocation(33);
        for(int i = 0; i<zufalls.length; i++) {
        System.out.println(getRandomLocation(i));
        }
    }

    public int[] SelectRandomLocation(int pAnzRunden){
        int z =0;
        int pzufalls[] = new int[pAnzRunden];
        for(int i = 0; i<pAnzRunden; i++) {
            pzufalls[i] = zufall.nextInt(33);
            while(pzufalls[i]==0){
                pzufalls[i] = zufall.nextInt(33);
            }
        }
        while(z<=pAnzRunden/5) {
            for (int i = 0; i < pAnzRunden; ++i) {
                for (int j = i + 1; j < pAnzRunden; ++j) {
                    if (pzufalls[i] == pzufalls[j]) {
                        pzufalls[i] = zufall.nextInt(33);
                        while (pzufalls[i] == 0) {
                            pzufalls[i] = zufall.nextInt(33);
                        }
                    }
                }
            }
            z++;
        }
        return pzufalls;
    }
    public int getRandomLocation(int pRoundNumber){
        return zufalls[pRoundNumber];
    }
}

