import javax.swing.*;

public class GUIFrame extends JFrame {
    GameControl GameControl;
    public GUIFrame(GameControl pGameControl){
        GameControl = pGameControl;
        setTitle("ArmA Guessr");
        add(pGameControl.getGameGUI());
        setSize(1350,850);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
