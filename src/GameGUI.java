import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class GameGUI extends JPanel {
    int x;
    int y;
    int LocPosX;
    int LocPosY;
    int RoundNumber;
    Image Map;
    GameControl GameControl;
    JButton setLocation, nextLocation;
    JLabel LPoints;
    Boolean playing = true;

    MouseListener mListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(playing==true) {
                x = e.getX();
                y = e.getY();
                //System.out.println("Gedrückte Position: "+x+", "+ y);
                repaint();
            }
        }
        @Override
        public void mousePressed(MouseEvent e) {

        }
        @Override
        public void mouseReleased(MouseEvent e) {

        }
        @Override
        public void mouseEntered(MouseEvent e) {

        }
        @Override
        public void mouseExited(MouseEvent e) {

        }
    };
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==setLocation){
                setLocation.setVisible(false);
                nextLocation.setVisible(true);
                System.out.println("Eingeloggte Position: "+x+", "+ y);
                playing = false;
                repaint();
                GameControl.berechneEntfernung();
            }else if(e.getSource()==nextLocation){
                setLocation.setVisible(true);
                nextLocation.setVisible(false);
                playing = true;
                if(RoundNumber==1){
                    GameControl.getRound2();
                }else if(RoundNumber==2){
                    GameControl.getRound3();
                }
                repaint();
            }
        }
    };

    public GameGUI(GameControl pGameControl){
        GameControl = pGameControl;
        setSize(1350,850);
        setVisible(true);
        setFocusable(true);
        setLayout(null);
        requestFocus();
        setOpaque(true);
        addMouseListener(mListener);
        createUI();
        try {
            File pathToFile = new File("Arma3atlis.jpg");
            Map = ImageIO.read(pathToFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        setBackground(Color.WHITE);
        repaint();
    }
    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(Map, 175, 5, 1200, 805, null);
        if(x>=175 && y>=5) {
            g2D.fill(new Rectangle(x, y, 5, 5));
        }
        g2D.drawLine(485,100,525,100);
        if(playing==false){
            g2D.setColor(Color.RED);
            g2D.fill(new Rectangle(LocPosX,LocPosY,5,5));
            g2D.drawLine(x+2, y+5, LocPosX+2, LocPosY);
        }
        add(setLocation);
    }
    private void createUI(){
        setLocation = new JButton("set Location");
        setLocation.setBounds(20,300,100,20);
        setLocation.setBackground(Color.BLACK);
        setLocation.addActionListener(listener);
        add(setLocation);
        nextLocation = new JButton("Next Location");
        nextLocation.setBounds(20,350,100,20);
        nextLocation.setBackground(Color.BLACK);
        nextLocation.addActionListener(listener);
        nextLocation.setVisible(false);
        add(nextLocation);
        LPoints = new JLabel("Your Points: "+ GameControl.Points);
        LPoints.setForeground(Color.BLACK);
        LPoints.setBounds(0,0,10,10);
        LPoints.setVisible(true);
        add(LPoints);
        LPoints = new JLabel("Your Points: "+ GameControl.Points);
        LPoints.setForeground(Color.BLACK);
        LPoints.setBounds(0,0,100,20);
        LPoints.setVisible(true);
        add(LPoints);
    }
}
