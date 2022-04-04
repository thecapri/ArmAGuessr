import java.awt.*;
import java.util.Random;

public class GameControl {
    GUIFrame GUIFrame;
    GameGUI GameGUI;
    DataBase db;
    Random zufall = new Random();
    int zufalls;
    public GameControl(){
        db = new DataBase();
        GameGUI = new GameGUI(this);
        GUIFrame = new GUIFrame(this);
        //db.saveNewLocation("AeroClub Airfield(KerosinDestille)","Locations/ACAirfield.png",50,50);
        //db.saveNewLocation("Kavala Burg","Locations/KavalaBurg.png",235,498);
        //db.saveNewLocation("Selekano Airfield","Locations/SelekanoAirfield.png",988,724);
        //db.saveNewLocation("Ghost Hotel","Locations/GhostHotel.png",1010,198);
        selectRandomLocation();
        setLocPos(zufalls);
        db.readName(zufalls);
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
        zufalls = zufall.nextInt(5);
        while(zufalls==0){
            zufalls=zufall.nextInt(5);
        }
        GameGUI.LocationNumber = zufalls;
    }
    public int berechneEntfernung(){
        // 1 Pixel = 25m
        int x1 = GameGUI.x- GameGUI.LocPosX;
        int x2 = GameGUI.y- GameGUI.LocPosY;
        System.out.println((int)Math.sqrt((x1*x1)+(x2*x2))*25 + " Meter entfernt");
        return (int)Math.sqrt((x1*x1)+(x2*x2))*25;


    }
}
