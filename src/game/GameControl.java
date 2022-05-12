package game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameControl {
    GUIFrame GUIFrame;
    GameGUI GameGUI;
    DataBase db;
    Random zufall = new Random();
    int Points = 0, anzRunden, pixel = 25;
    int[] zufalls;
    EndGameGUI EndGameGUI;
    public GameControl(int pAnzahlRounds){
        anzRunden = pAnzahlRounds;
        db = new DataBase();
        GameGUI = new GameGUI(this);
        GUIFrame = new GUIFrame(this);
        zufalls = selectRandomLocation(anzRunden);
        getRound();
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
    public JPanel getEndGamePanel(){
        return GameGUI.EndGamePanel;
    }

    public void initEndGame(){
        GUIFrame.GUIFramedispose();
        EndGameGUI = new EndGameGUI(Points, anzRunden);
    }
    private void setLocPos(int pLocationNumber){
        Point pLocPos = db.readXandY(pLocationNumber);
        GameGUI.LocPosX = (int)pLocPos.getX();
        GameGUI.LocPosY = (int)pLocPos.getY();
    }
    private int[] selectRandomLocation(int pAnzRunden){
        if(pAnzRunden>db.returnAnzlocations()){
            pAnzRunden = db.returnAnzlocations();
        }
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
        GameGUI.RoundNumber = 1;
        return pzufalls;
    }
    private int getRandomLocation(int pRoundNumber){
        return zufalls[pRoundNumber];
    }
    public void getRound(){
        System.out.println("Runde "+GameGUI.RoundNumber+":");
        int round1 = getRandomLocation(GameGUI.RoundNumber-1);
        setLocPos(round1);
        db.readName(round1);
        GameGUI.selectPicture(db.readSaveLocation(round1));
        GameGUI.LRound.setText("Runde "+GameGUI.RoundNumber+"/"+zufalls.length);
        GameGUI.RoundNumber = GameGUI.RoundNumber+1;
    }
    public int berechneEntfernung(){
        // 1 Pixel = 25m
        int pMeterEntfernt;
        int x1 = GameGUI.x- GameGUI.LocPosX;
        int x2 = GameGUI.y- GameGUI.LocPosY;
        pMeterEntfernt = (int)Math.sqrt((x1*x1)+(x2*x2))*pixel;
        calcPointsperRound(pMeterEntfernt);
        GameGUI.LMetersAway.setVisible(true);
        GameGUI.LMetersAway.setText(pMeterEntfernt+"m entfernt");
        return pMeterEntfernt;
    }
    private void calcPointsperRound(int pPoints){
        int pZwischenPoint = 1000-pPoints;
        if(pZwischenPoint>=0){
            Points = Points + pZwischenPoint;
        }else pZwischenPoint=0;
        GameGUI.LPoints.setText("Points: "+Points+"/3000");
        GameGUI.LZwischenPoints.setVisible(true);
        GameGUI.LZwischenPoints.setText("Got "+Points+"/1000 Points");
    }
    private void selectDifferentGame(int pPixel, String pMapPath){
        GameGUI.MapPath = pMapPath;
        pixel = pPixel;
        db.deleteDB();
    }
    private void reSafeDB(){
        db.saveNewLocation("Bank auf Zero One", "src/Locations/BankZero.jpg", 807, 264);
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
