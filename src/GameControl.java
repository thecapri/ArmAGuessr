public class GameControl {
    GUIFrame GUIFrame;
    GameGUI GameGUI;
    DataBase db;
    public GameControl(){
        //GUIFrame = new GUIFrame(this);
        db = new DataBase();
        //db.saveNewLocation("AeroClubAirfield(KerosinDestille)","Locations/ACAirfield.png",50,50);
        db.readName(1);
        db.readSaveLocation(1);
        db.readXandY(1);
        //GameGUI = new GameGUI(this);
    }
    public DataBase getDB(){return db;}
    public GUIFrame getGUIFrame(){
        return GUIFrame;
    }
    public GameGUI getGameGUI(){
        return GameGUI;
    }
}
