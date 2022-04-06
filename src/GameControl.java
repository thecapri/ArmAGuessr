import javax.swing.*;
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
    public JPanel getUIPanel(){
        return GameGUI.UIPannnel;
    }

    public void setLocPos(int pLocationNumber){
        Point pLocPos = db.readXandY(pLocationNumber);
        GameGUI.LocPosX = (int)pLocPos.getX();
        GameGUI.LocPosY = (int)pLocPos.getY();
    }
    public void selectRandomLocation(){
        int zufalls;
        for(int i = 1; i<=3; i++) {
            zufalls = zufall.nextInt(db.returnAnzlocations()+1);
            while (zufalls == 0 || zufalls==round1 || zufalls==round2) {
                zufalls = zufall.nextInt(db.returnAnzlocations()+1);
            }
            if(i == 1){
                round1 = zufalls;
            }else if(i==2){
                round2 = zufalls;
            }else round3 = zufalls;

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
        GameGUI.LRound.setText("Runde "+GameGUI.RoundNumber+"/3");
    }
    public void getRound2(){
        System.out.println("\nRunde 2:");
        GameGUI.RoundNumber = 2;
        setLocPos(round2);
        db.readName(round2);
        GameGUI.selectPicture(db.readSaveLocation(round2));
        GameGUI.LRound.setText("Runde "+GameGUI.RoundNumber+"/3");
    }
    public void getRound3(){
        System.out.println("\nRunde 3:");
        GameGUI.RoundNumber = 3;
        setLocPos(round3);
        db.readName(round3);
        GameGUI.selectPicture(db.readSaveLocation(round3));
        GameGUI.LRound.setText("Runde "+GameGUI.RoundNumber+"/3");
    }
    public int berechneEntfernung(){
        // 1 Pixel = 25m
        int pMeterEntfernt;
        int x1 = GameGUI.x- GameGUI.LocPosX;
        int x2 = GameGUI.y- GameGUI.LocPosY;
        pMeterEntfernt = (int)Math.sqrt((x1*x1)+(x2*x2))*25;
        calcPointsperRound(pMeterEntfernt);
        GameGUI.LMetersAway.setVisible(true);
        GameGUI.LMetersAway.setText(pMeterEntfernt+"m entfernt");
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
        GameGUI.LPoints.setText("Points: "+Points+"/3000");
        GameGUI.LZwischenPoints.setVisible(true);
        GameGUI.LZwischenPoints.setText("Got "+Points+"/1000 Points");
    }
    private void reSafeDB(){
        db.saveNewLocation("AeroClub Airfield(KerosinDestille)","src/Locations/ACAirfield.png",418, 542);
        db.saveNewLocation("Kavala Burg","src/Locations/KavalaBurg.png",64, 496);
        db.saveNewLocation("Selekano Airfield","src/Locations/SelekanoAirfield.png",812, 725);
        db.saveNewLocation("Ghost Hotel","src/Locations/GhostHotel.png",837, 194);
        db.saveNewLocation("Bank","src/Locations/Bank.jpg",599, 347);
        db.saveNewLocation("Bomos Insel (Oben Links)","src/Locations/BomosInsel.jpg",23, 154);
        db.saveNewLocation("Burg Checkpoint","src/Locations/BurgCheckpoint.jpg",402, 666);
        db.saveNewLocation("Frachtumschlagsstelle","src/Locations/Frachti.jpg",578, 393);
        db.saveNewLocation("Goldankäufer","src/Locations/GoldBuyer.jpg",417, 729);
        db.saveNewLocation("Kavala Garage", "src/Locations/KavalaGarage.jpg", 92, 495);
        db.saveNewLocation("Kavala Polizei HQ","src/Locations/KavalaHQ.jpg",75, 520);
        db.saveNewLocation("Kokainverarbeiter Sofia","src/Locations/KokainverarbeiterSofia.jpg",1060, 174);
        db.saveNewLocation("Kommunikationszentrum","src/Locations/Kommi.jpg",529, 501);
        db.saveNewLocation("Kore Tower","src/Locations/KoreTower.jpg",223, 394);
        db.saveNewLocation("Kupfermine Kavala", "src/Locations/KupfermineKavala.jpg",117, 518);
        db.saveNewLocation("Neochori Garage", "src/Locations/NeochoriGarage.jpg",465, 456);
        db.saveNewLocation("Propagandazentrum", "src/Locations/Propaganda.jpg",125, 409);
        db.saveNewLocation("Pyrgos","src/Locations/Pyrgos.jpg",629, 518);
        db.saveNewLocation("Rebel Nord", "src/Locations/RebelNord.jpg",316, 176);
        db.saveNewLocation("Rebel West", "src/Locations/RebelWest.jpg",283, 612);
        db.saveNewLocation("RedWater Tower", "src/Locations/RedWaterTower.jpg", 624, 271);
        db.saveNewLocation("Rodopoli", "src/Locations/Rodopoli.jpg",717, 366);
        db.saveNewLocation("Salzsee Airfield", "src/Locations/Salzsee.jpg",891, 276);
        db.saveNewLocation("Rodopoli Mine", "src/Locations/SelekanoMine.jpg",759, 313);
        db.saveNewLocation("Sofia", "src/Locations/Sofia.jpg",1003, 183);
        db.saveNewLocation("Syrta Tower", "src/Locations/SyrtaTower.jpg", 288, 303);
        db.saveNewLocation("Todeskurve", "src/Locations/Todeskurve.jpg", 295, 392);
        db.saveNewLocation("DB", "src/Locations/DP.jpg",181, 229);
        db.saveNewLocation("Agios", "src/Locations/Agios.jpg",312, 394);
        db.saveNewLocation("Airfield", "src/Locations/Airfield.jpg",539, 354);
        db.saveNewLocation("Air HQ Airfield", "src/Locations/AirHQAirfield.jpg",540, 378);
        db.saveNewLocation("Athira Garage", "src/Locations/AthiraGarage.jpg",518, 286);
        db.saveNewLocation("Bandit Athira", "src/Locations/BanditAthira.jpg",467, 363);
        db.saveNewLocation("Ghost Hotel inside", "src/Locations/GhostHotelInside.jpg", 837, 194);
        db.saveNewLocation("Sumpf", "src/Locations/Sumpf.jpg", 803, 441);
    }
}
