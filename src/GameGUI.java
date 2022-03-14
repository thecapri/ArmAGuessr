import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameGUI extends Panel {
    Image Map;
    Graphics gBuff;
    GameControl GameControl;
    public GameGUI(GameControl pGameControl){
        GameControl = pGameControl;
        this.setSize(1500,1000);
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
        try {
            File pathToFile = new File("Arma3atlis.jpg");
            Map = ImageIO.read(pathToFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        repaint();
    }
    public void paint(Graphics g){
        //super.paint(g); // paint Background
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(Map, 0, 0, 500, 300, null);
    }
}
