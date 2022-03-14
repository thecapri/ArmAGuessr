public class GameControl {
    GUIFrame GUIFrame = new GUIFrame(this);
    GameGUI GameGUI = new GameGUI(this);
    public GameControl(){

    }
    public GUIFrame getGUIFrame(){
        return GUIFrame;
    }
    public GameGUI getGameGUI(){
        return GameGUI;
    }
}
