package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

public class GameGUI extends JPanel {
    private boolean init = true;
    private int zoomLevel = 0;
    private int minZoomLevel = -20;
    private int maxZoomLevel = 10;
    private double zoomMultiplicationFactor = 1.2;

    private Point dragStartScreen;
    private Point dragEndScreen;
    private AffineTransform coordTransform = new AffineTransform();

    int x, y, LocPosX, LocPosY, RoundNumber;
    GameControl GameControl;
    Image Picture;
    JPanel UIPannnel, EndGamePanel;
    JButton setLocation, nextLocation, seePicture, seeMap;
    JLabel LPoints, LHeadOne, LRound, LMetersAway, LZwischenPoints;
    Boolean playing = false, finRound = false;
    String Path, MapPath = "Arma3atlis.jpg";
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

        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.isControlDown()) {
                    zoom(e);
                }
            }
        });
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
                }
            }else if(e.getSource() == seePicture){
                seePicture.setVisible(false);
                seeMap.setVisible(true);
                setLocation.setVisible(false);
                playing = false;
                selectPicture(Path);
            }else if(e.getSource() == seeMap){
                seePicture.setVisible(true);
                seeMap.setVisible(false);
                setLocation.setVisible(true);
                playing = true;
                selectMap();
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
    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(Picture, 0, 0, 1200, 815, null);
        if(seeMap.isVisible()==true){
            g2D.fill(r = new Rectangle(0, 0, 5, 5));
        }else g2D.fill(r = new Rectangle(x, y, 5, 5));
        if(finRound == true){
            g2D.setColor(Color.RED);
            g2D.fill(new Rectangle(LocPosX,LocPosY,5,5));
            g2D.drawLine(x+2, y+5, LocPosX+2, LocPosY);
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
    /**
     * Test
     */

    private Point2D.Float transformPoint(Point p1) throws NoninvertibleTransformException {
        AffineTransform inverse = coordTransform.createInverse();
        Point2D.Float p2 = new Point2D.Float();
        inverse.transform(p1, p2);
        return p2;
    }

    private void zoom(MouseWheelEvent e) {
        try {
            int wheelRotation = e.getWheelRotation();
            Point p = e.getPoint();
            if (wheelRotation > 0) {
                if (zoomLevel < maxZoomLevel) {
                    zoomLevel++;
                    Point2D p1 = transformPoint(p);
                    coordTransform.scale(1 / zoomMultiplicationFactor, 1 / zoomMultiplicationFactor);
                    Point2D p2 = transformPoint(p);
                    coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
                    repaint();
                }
            } else {
                if (zoomLevel > minZoomLevel) {
                    zoomLevel--;
                    Point2D p1 = transformPoint(p);
                    coordTransform.scale(zoomMultiplicationFactor, zoomMultiplicationFactor);
                    Point2D p2 = transformPoint(p);
                    coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
                    repaint();
                }
            }
        } catch (NoninvertibleTransformException ex) {
            ex.printStackTrace();
        }
    }
}
