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
            zufalls = zufall.nextInt(29);
            while (zufalls == 0 || zufalls==round1 || zufalls==round2) {
                zufalls = zufall.nextInt(29);
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
    private void reSafeDB(){
        db.saveNewLocation("AeroClub Airfield(KerosinDestille)","Locations/ACAirfield.png",50,50);
        db.saveNewLocation("Kavala Burg","Locations/KavalaBurg.png",235,498);
        db.saveNewLocation("Selekano Airfield","Locations/SelekanoAirfield.png",988,724);
        db.saveNewLocation("Ghost Hotel","Locations/GhostHotel.png",1010,198);
        db.saveNewLocation("Bank","src/Locations/Bank.jpg",773, 348);
        db.saveNewLocation("Bomos Insel (Oben Links)","src/Locations/BomosInsel.jpg",198, 158);
        db.saveNewLocation("Burg Checkpoint","src/Locations/BurgCheckpoint.jpg",577, 662);
        db.saveNewLocation("Frachtumschlagsstelle","src/Locations/Frachti.jpg",756, 390);
        db.saveNewLocation("Goldankäufer","src/Locations/GoldBuyer.jpg",589, 726);
        db.saveNewLocation("Kavala Garage", "src/Locations/KavalaGarage.jpg", 270, 491);
        db.saveNewLocation("Kavala Polizei HQ","src/Locations/KavalaHQ.jpg",248, 522);
        db.saveNewLocation("Kokainverarbeiter Sofia","src/Locations/KokainverarbeiterSofia.jpg",1235, 175);
        db.saveNewLocation("Kommunikationszentrum","src/Locations/Kommi.jpg",704, 502);
        db.saveNewLocation("Kore Tower","src/Locations/KoreTower.jpg",397, 394);
        db.saveNewLocation("Kupfermine Kavala", "src/Locations/KupfermineKavala.jpg",292, 516);
        db.saveNewLocation("Neochori Garage", "src/Locations/NeochoriGarage.jpg",640, 456);
        db.saveNewLocation("Propagandazentrum", "src/Locations/Propaganda.jpg",300, 412);
        db.saveNewLocation("Pyrgos","src/Locations/Pyrgos.jpg",803, 515);
        db.saveNewLocation("Rebel Nord", "src/Locations/RebelNord.jpg",496, 181);
        db.saveNewLocation("Rebel West", "src/Locations/RebelWest.jpg",458, 612);
        db.saveNewLocation("RedWater Tower", "src/Locations/RedWaterTower.jpg", 799, 272);
        db.saveNewLocation("Rodopoli", "src/Locations/Rodopoli.jpg",892, 365);
        db.saveNewLocation("Salzsee Airfield", "src/Locations/Salzsee.jpg",1065, 280);
        db.saveNewLocation("Rodopoli Mine", "src/Locations/SelekanoMine.jpg",936, 315);
        db.saveNewLocation("Sofia", "src/Locations/Sofia.jpg",1177, 186);
        db.saveNewLocation("Syrta Tower", "src/Locations/SyrtaTower.jpg", 462, 303);
        db.saveNewLocation("Todeskurve", "src/Locations/Todeskurve.jpg", 469, 392);
        db.saveNewLocation("DB", "src/Locations/DP.jpg",355, 233);
    }
}
