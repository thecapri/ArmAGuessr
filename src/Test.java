import java.util.Random;

public class Test {
    Random zufall = new Random();

    public Test(){
        SelectRandomLocation(10);
    }
    public void SelectRandomLocation(int pAnzRunden){
    int zufalls[] = new int[pAnzRunden];
        for(int i = 0; i<pAnzRunden; i++) {
            zufalls[i] = zufall.nextInt(33);
            while(zufalls[i]==0){
                zufalls[i] = zufall.nextInt(33);
            }
            if(i>0) {
                System.out.println("asd: "+zufalls[i]);
                for (int p = i; i >= 0; i--) {
                    System.out.println("3: "+p);
                    while (zufalls[i] == 0 || zufalls[i] == zufalls[p]) {
                        System.out.println("While");
                        System.out.println("i: "+zufalls[i]);
                        System.out.println("p: "+zufalls[p]);
                        zufalls[i] = zufall.nextInt(33);

                    }
                }
            }
            System.out.println("X"+i+": "+zufalls[i]);
        }
    }
}
