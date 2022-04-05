import java.awt.*;
import java.util.Random;

public class GameControl {
    GUIFrame GUIFrame;
    GameGUI GameGUI;
    DataBase db;
    Random zufall = new Random();
    int round1;
    int round2;
    int round3;
    int Points = 0;
    public GameControl(){
        db = new DataBase();
        GameGUI = new GameGUI(this);
        GUIFrame = new GUIFrame(this);
        //db.saveNewLocation("AeroClub Airfield(KerosinDestille)","Locations/ACAirfield.png",50,50);
        //db.saveNewLocation("Kavala Burg","Locations/KavalaBurg.png",235,498);
        //db.saveNewLocation("Selekano Airfield","Locations/SelekanoAirfield.png",988,724);
        //db.saveNewLocation("Ghost Hotel","Locations/GhostHotel.png",1010,198);
        selectRandomLocation();
    }
    public DataBase getDB(){return db;}
    public GUIFrame getGUIFrame(){
        return GUIFrame;
    }
    public GameGUI getGameGUI(){
        return GameGUI;
    }

    public void setLocPos(int pLocationNumber){
        Point pLocPos = db.readXandY(pLocationNumber);
        GameGUI.LocPosX = (int)pLocPos.getX();
        GameGUI.LocPosY = (int)pLocPos.getY();
    }
    public void selectRandomLocation(){
        int zufalls;
        for(int i = 1; i<=3; i++) {
            zufalls = zufall.nextInt(5);
            while (zufalls == 0 || zufalls==round1 || zufalls==round2) {
                zufalls = zufall.nextInt(5);
            }
            if(i == 1){
                round1 = zufalls;
            }else if(i==2){
                round2 = zufalls;
            }else if (i==3){
                round3 = zufalls;
            }
        }
        System.out.println("1:"+round1+" 2:"+round2+" 3:"+round3);
        getRound1();
    }
    public void getRound1(){
        System.out.println("Runde 1:");
        GameGUI.RoundNumber = 1;
        setLocPos(round1);
        db.readName(round1);
        GameGUI.selectPicture(db.readSaveLocation(round1));
    }
    public void getRound2(){
        System.out.println("\nRunde 2:");
        GameGUI.RoundNumber = 2;
        setLocPos(round2);
        db.readName(round2);
        GameGUI.selectPicture(db.readSaveLocation(round2));
    }
    public void getRound3(){
        System.out.println("\nRunde 3:");
        GameGUI.RoundNumber = 3;
        setLocPos(round3);
        db.readName(round3);
        GameGUI.selectPicture(db.readSaveLocation(round3));
    }
    public int berechneEntfernung(){
        // 1 Pixel = 25m
        int pMeterEntfernt;
        int x1 = GameGUI.x- GameGUI.LocPosX;
        int x2 = GameGUI.y- GameGUI.LocPosY;
        pMeterEntfernt = (int)Math.sqrt((x1*x1)+(x2*x2))*25;
        System.out.println(pMeterEntfernt + " Meter entfernt");
        calcPointsperRound(pMeterEntfernt);
        return pMeterEntfernt;
    }
    public void GameEnd(){
        System.out.println("\nGame Ended \nFinal Points: "+Points+"/3000");
    }
    public void calcPointsperRound(int pPoints){
        int pZwischenPoint = 1000-pPoints;
        if(pZwischenPoint>=0){
            Points = Points + pZwischenPoint;
        }else pZwischenPoint=0;
        System.out.println(pZwischenPoint+"/1000 Punkten erreicht");
    }
}
