package game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUIFrame extends JFrame {
    GameControl GameControl;
    public GUIFrame(GameControl pGameControl){
        GameControl = pGameControl;
        this.setTitle("ArmA Guessr");
        this.add(pGameControl.getGameGUI());
        this.add(pGameControl.getUIPanel());
        this.setSize(1350,850);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void GUIFramedispose(){
        this.dispose();
    }

}
