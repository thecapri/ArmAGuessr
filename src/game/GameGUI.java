package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class GameGUI extends JPanel {
    int x, y, LocPosX, LocPosY, RoundNumber;
    GameControl GameControl;
    Image Picture;
    JPanel UIPannnel, EndGamePanel;
    JButton setLocation, nextLocation, seePicture, seeMap;
    JLabel LPoints, LHeadOne, LRound, LMetersAway, LZwischenPoints;
    Boolean playing = false, finRound = false;
    String Path, MapPath = "Arma3atlisResized.jpg";
    Font titleFont = new Font("Verdana", Font.BOLD, 20);
    Font normalFont = new Font("Verdana", Font.BOLD, 12);
    Rectangle r;

    public GameGUI(GameControl pGameControl){
        GameControl = pGameControl;
        this.setBounds(150, 0,1350,850);
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.requestFocus();
        this.setOpaque(true);
        this.addMouseListener(mListener);
        this.createUI();
        this.setBackground(Color.WHITE);
        this.repaint();
        this.requestFocus();
    }



    MouseListener mListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(playing==true) {
                x = e.getX();
                y = e.getY();
                System.out.println("Gedrückte Position: "+x+", "+ y);
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
                if(RoundNumber > GameControl.anzRunden){
                    nextLocation.setText("Go to End");
                }
                setLocation.setVisible(false);
                nextLocation.setVisible(true);
                seePicture.setVisible(false);
                System.out.println("Eingeloggte Position: "+x+", "+ y);
                playing = false;
                finRound = true;
                repaint();
                GameControl.berechneEntfernung();
            }else if(e.getSource()==nextLocation){
                if(RoundNumber > GameControl.anzRunden){
                    GameControl.initEndGame();
                }else {
                    setLocation.setVisible(false);
                    nextLocation.setVisible(false);
                    seePicture.setVisible(false);
                    seeMap.setVisible(true);
                    LZwischenPoints.setVisible(false);
                    LMetersAway.setVisible(false);
                    playing = true;
                    finRound = false;
                    GameControl.getRound();
                    repaint();
                    requestFocus();
                }
            }else if(e.getSource() == seePicture){
                seePicture.setVisible(false);
                seeMap.setVisible(true);
                setLocation.setVisible(false);
                playing = false;
                selectPicture(Path);
                requestFocus();
            }else if(e.getSource() == seeMap){
                seePicture.setVisible(true);
                seeMap.setVisible(false);
                setLocation.setVisible(true);
                playing = true;
                selectMap();
                requestFocus();
            }
        }
    };

    public void selectPicture(String pPath){
        Path = pPath;
        try {
            File pathToFile = new File(pPath);
            Picture = ImageIO.read(pathToFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        repaint();
    }
    private void selectMap(){
        try {
            File pathToFile = new File(MapPath);
            Picture = ImageIO.read(pathToFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        repaint();
    }
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(Picture, 0, 0,null);
        //1200, 815
        if (seeMap.isVisible() == true) {
            g2D.fill(r = new Rectangle(0, 0, 5, 5));
        } else g2D.fill(r = new Rectangle(x, y, 5, 5));
        if (finRound == true) {
            g2D.setColor(Color.RED);
            g2D.fill(new Rectangle(LocPosX, LocPosY, 5, 5));
            g2D.drawLine(x + 2, y + 5, LocPosX + 2, LocPosY);
        }
    }
    private void createUI(){
        UIPannnel = new JPanel();
        UIPannnel.setBounds(0,0,150,850);
        UIPannnel.setBackground(Color.WHITE);
        UIPannnel.setLayout(null);
        setLocation = new JButton("set Location");
        setLocation.setBounds(5,155,142,20);
        setLocation.setVisible(false);
        setLocation.setFont(normalFont);
        setLocation.setBackground(Color.WHITE);
        setLocation.addActionListener(listener);
        UIPannnel.add(setLocation);
        nextLocation = new JButton("Next Location");
        nextLocation.setBounds(5,155,142,20);
        nextLocation.setBackground(Color.WHITE);
        nextLocation.addActionListener(listener);
        nextLocation.setFont(normalFont);
        nextLocation.setVisible(false);
        UIPannnel.add(nextLocation);
        seePicture = new JButton("See Picture");
        seePicture.setBounds(5,125,142,20);
        seePicture.setBackground(Color.WHITE);
        seePicture.addActionListener(listener);
        seePicture.setFont(normalFont);
        seePicture.setVisible(false);
        UIPannnel.add(seePicture);
        seeMap = new JButton("See Map");
        seeMap.setBounds(5,125,142,20);
        seeMap.setBackground(Color.WHITE);
        seeMap.addActionListener(listener);
        seeMap.setFont(normalFont);
        seeMap.setVisible(true);
        UIPannnel.add(seeMap);
        LPoints = new JLabel("Points: "+ GameControl.Points+"/"+GameControl.anzRunden*1000);
        LPoints.setFont(normalFont);
        LPoints.setBounds(5,60,175,20);
        UIPannnel.add(LPoints);
        LHeadOne = new JLabel("ArmAGuessr");
        LHeadOne.setBounds(5,10,175,20);
        LHeadOne.setFont(titleFont);
        UIPannnel.add(LHeadOne);
        LRound = new JLabel();
        LRound.setBounds(5,40,100,20);
        LRound.setFont(normalFont);
        UIPannnel.add(LRound);
        LMetersAway = new JLabel();
        LMetersAway.setBounds(5,80,175,20);
        LMetersAway.setFont(normalFont);
        LMetersAway.setVisible(false);
        UIPannnel.add(LMetersAway);
        LZwischenPoints = new JLabel();
        LZwischenPoints.setBounds(5,100,175,20);
        LZwischenPoints.setFont(normalFont);
        LZwischenPoints.setVisible(false);
        UIPannnel.add(LZwischenPoints);
    }
}
