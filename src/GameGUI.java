import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameGUI extends JPanel {
    Image Map;
    Graphics gBuff;
    GameControl GameControl;
    JButton setLocation;
    JLabel LPoints, LMetersAway;
    int Points = 0;
    public GameGUI(){
        //GameControl = pGameControl;
        setSize(1350,850);
        setVisible(true);
        setFocusable(true);
        setLayout(null);
        requestFocus();
        setOpaque(true);
        try {
            File pathToFile = new File("Arma3atlis.jpg");
            Map = ImageIO.read(pathToFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        setBackground(Color.WHITE);
        LPoints = new JLabel("Your Points: "+ Points);
        LPoints.setForeground(Color.BLACK);
        LPoints.setBounds(0,0,100,20);
        LPoints.setVisible(true);
        add(LPoints);
        //createUI();
        repaint();
    }
    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(Map, 175, 5, 1200, 805, null);
    }
    private void createUI(){
        setLocation = new JButton("set Location");
        setLocation.setBounds(25,400,100,20);
        setLocation.setBackground(Color.WHITE);
        add(setLocation);
        LPoints = new JLabel("Your Points: "+ Points);
        LPoints.setForeground(Color.BLACK);
        LPoints.setBounds(0,0,100,20);
        LPoints.setVisible(true);
        add(LPoints);
    }
}
