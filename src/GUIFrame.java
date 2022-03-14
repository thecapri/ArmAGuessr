import javax.swing.*;
import java.awt.*;

public class GUIFrame extends Frame {
    GameControl GameControl;
    public GUIFrame(GameControl pGameControl){
        GameControl = pGameControl;
        this.setTitle("ArmA Guessr");
        this.add(GameControl.getGameGUI());
        this.setSize(1500,1000);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
