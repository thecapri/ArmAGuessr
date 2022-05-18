package game;

import netz.Client;
import netz.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class StartGUI {
    JFrame titleScreen;
    JPanel mainTitle, settings, afterPlay, joinSession, hostSession;
    JLabel title1, title2, settingsTitle, afterPlayLabel, joinSessionLabel, hostSessionLabel;
    Font titleFont1, titleFont2, normalFont;
    JButton playButton, settingsButton, backButton, hostButton, joinButton, sendIPAdresse, sendRoundNumber;
    JTextField setIPAdresse, setRoundNumber;
    JTextArea seeHowToPlay;
    FocusListener fListener;
    String IPAdresse;
    int RoundNumber;
    GameControl gameControl;
    StartGUI startGUI = this;
    Server pServer;

    public StartGUI() {
        try {
            titleFont1 = Font.createFont(Font.TRUETYPE_FONT, new File("ShortbreadCookies.ttf")).deriveFont(60f);
            titleFont2 = Font.createFont(Font.TRUETYPE_FONT, new File("ShortbreadCookies.ttf")).deriveFont(40f);
            normalFont = Font.createFont(Font.TRUETYPE_FONT, new File("ShortbreadCookies.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("ShortbreadCookies.ttf")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        /**
         * ActionListener
         */

        ActionListener plistener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == playButton) {
                    mainTitle.setVisible(false);
                    afterPlay.setVisible(true);
                } else if (e.getSource() == settingsButton) {
                    mainTitle.setVisible(false);
                    settings.setVisible(true);
                } else if (e.getSource() == backButton) {
                    mainTitle.setVisible(true);
                    settings.setVisible(false);
                } else if (e.getSource() == joinButton) {
                    afterPlay.setVisible(false);
                    joinSession.setVisible(true);
                } else if (e.getSource() == hostButton) {
                    afterPlay.setVisible(false);
                    hostSession.setVisible(true);
                } else if (e.getSource() == sendIPAdresse) {
                    if(setIPAdresse.getText() == "Enter IP-Adresse"){
                        IPAdresse = "localhost";
                    }else IPAdresse = setIPAdresse.getText();
                    new Client(IPAdresse, startGUI);
                    titleScreen.dispose();
                } else if (e.getSource() == sendRoundNumber) {
                    titleScreen.dispose();
                    Boolean isNumber = false;
                    try {
                        RoundNumber = Integer.parseInt(setRoundNumber.getText());
                        isNumber = true;
                    } catch (NumberFormatException v) {
                        isNumber = false;
                    }
                    if (!isNumber) {
                        RoundNumber = 3;
                    }
                    pServer = new Server(RoundNumber);
                }
            }
        };

        /**
         * FocusListener
         */

        fListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (e.getSource() == setRoundNumber) {
                    setRoundNumber.setText("");
                } else if (e.getSource() == setIPAdresse) {
                    setIPAdresse.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        };

        /**
         * Title Screen
         *
         * titleScreen (JFrame) = Main Frame before Game starts
         * mainTitle (JPanel) = Panel for Main Screen
         * title1 (JLabel) = Head 1
         * title2 (JLabel) = Head 2
         * playButton (JButton) = start Play button
         * settingsButton (JButton) = Settings Button
         */

        titleScreen = new JFrame("ArmAGuessr");
        titleScreen.setSize(500, 300);
        titleScreen.setResizable(false);
        titleScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        titleScreen.setLayout(null);
        titleScreen.setVisible(true);
        titleScreen.setLocationRelativeTo(null);


        mainTitle = new JPanel();
        mainTitle.setSize(500, 500);
        mainTitle.setLayout(null);
        mainTitle.setBackground(Color.WHITE);
        titleScreen.add(mainTitle);

        title1 = new JLabel("ArmAGuessr");
        title1.setBounds(100, 25, 350, 40);
        title1.setFont(titleFont1);
        mainTitle.add(title1);

        title2 = new JLabel("Arma X GeoGuessr");
        title2.setBounds(100, 65, 700, 30);
        title2.setFont(titleFont2);
        mainTitle.add(title2);

        playButton = new JButton("Play");
        playButton.setBounds(170, 120, 150, 40);
        playButton.setFont(normalFont);
        playButton.setBackground(Color.WHITE);
        playButton.addActionListener(plistener);
        mainTitle.add(playButton);

        settingsButton = new JButton("What is");
        settingsButton.setBounds(170, 165, 150, 40);
        settingsButton.setFont(normalFont);
        settingsButton.setBackground(Color.WHITE);
        settingsButton.addActionListener(plistener);
        mainTitle.add(settingsButton);


        /**
         * AFTER PLAY BUTTON PRESSED (SELECT SESSION)
         *
         * afterPlay (JPanel) = Panel for Selecting Session
         * afterPlayLabel (JLabel) = Head 1
         * hostButton (JButton) = Button for hosting session
         * joinButton (JButton) = Button for joining session
         */

        afterPlay = new JPanel();
        afterPlay.setSize(500, 500);
        afterPlay.setLayout(null);
        afterPlay.setBackground(Color.WHITE);
        afterPlay.setVisible(false);
        titleScreen.add(afterPlay);

        afterPlayLabel = new JLabel("Select Session");
        afterPlayLabel.setBounds(85, 25, 500, 40);
        afterPlayLabel.setFont(titleFont1);
        afterPlay.add(afterPlayLabel);

        hostButton = new JButton("Host");
        hostButton.setBounds(170, 120, 150, 40);
        hostButton.setFont(normalFont);
        hostButton.setBackground(Color.WHITE);
        hostButton.addActionListener(plistener);
        afterPlay.add(hostButton);

        joinButton = new JButton("Join");
        joinButton.setBounds(170, 165, 150, 40);
        joinButton.setFont(normalFont);
        joinButton.setBackground(Color.WHITE);
        joinButton.addActionListener(plistener);
        afterPlay.add(joinButton);


        /**
         * HOST SESSION
         *
         * hostSession (JPanel) = Panel for Hosting a Session
         * hosstSessionLabel (JLabel) = Head 1
         */

        hostSession = new JPanel();
        hostSession.setSize(500, 500);
        hostSession.setLayout(null);
        hostSession.setBackground(Color.WHITE);
        hostSession.setVisible(false);
        titleScreen.add(hostSession);

        hostSessionLabel = new JLabel("How many Rounds?");
        hostSessionLabel.setBounds(15, 35, 500, 40);
        hostSessionLabel.setFont(titleFont1);
        hostSession.add(hostSessionLabel);

        setRoundNumber = new JTextField("Enter Round Number");
        setRoundNumber.setBounds(55, 120, 260, 40);
        setRoundNumber.setBackground(Color.WHITE);
        setRoundNumber.setForeground(Color.GRAY);
        setRoundNumber.addFocusListener(fListener);
        hostSession.add(setRoundNumber);

        sendRoundNumber = new JButton("Enter");
        sendRoundNumber.setBounds(320, 120, 125, 40);
        sendRoundNumber.setBackground(Color.WHITE);
        sendRoundNumber.setFont(normalFont);
        sendRoundNumber.addActionListener(plistener);
        hostSession.add(sendRoundNumber);


        /**
         * JOIN SESSION
         */

        joinSession = new JPanel();
        joinSession.setSize(500, 500);
        joinSession.setLayout(null);
        joinSession.setBackground(Color.WHITE);
        joinSession.setVisible(false);
        titleScreen.add(joinSession);

        joinSessionLabel = new JLabel("Enter IP-Adresse");
        joinSessionLabel.setBounds(45, 25, 500, 40);
        joinSessionLabel.setFont(titleFont1);
        joinSession.add(joinSessionLabel);

        setIPAdresse = new JTextField("Enter IP-Adresse");
        setIPAdresse.setBounds(55, 120, 260, 40);
        setIPAdresse.addFocusListener(fListener);
        setIPAdresse.setBackground(Color.WHITE);
        joinSession.add(setIPAdresse);

        sendIPAdresse = new JButton("Enter");
        sendIPAdresse.setBounds(320, 120, 125, 40);
        sendIPAdresse.setBackground(Color.WHITE);
        sendIPAdresse.setFont(normalFont);
        sendIPAdresse.addActionListener(plistener);
        joinSession.add(sendIPAdresse);


        /**
         * OPTIONS
         */

        settings = new JPanel();
        settings.setSize(500, 500);
        settings.setLayout(null);
        settings.setBackground(Color.WHITE);
        settings.setVisible(false);
        titleScreen.add(settings);

        settingsTitle = new JLabel("What is it?");
        settingsTitle.setBounds(80, 23, 300, 40);
        settingsTitle.setFont(titleFont1);
        settings.add(settingsTitle);

        backButton = new JButton("Back");
        backButton.setBounds(370, 25, 93, 40);
        backButton.setBackground(Color.WHITE);
        backButton.addActionListener(plistener);
        backButton.setFont(normalFont);
        settings.add(backButton);

        seeHowToPlay = new JTextArea();
        seeHowToPlay.setBounds(70,80,500,300);
        seeHowToPlay.setEditable(false);
        seeHowToPlay.setLineWrap(true);
        seeHowToPlay.setWrapStyleWord(true);
        seeHowToPlay.setBackground(Color.WHITE);
        settings.add(seeHowToPlay);
        seeHowToPlay.append("Im Spiel bekommt der Spieler ein zufälliges Panorama\n" +
                "aus dem Computerspiel Arma 3 angezeigt\n " +
                "und muss anhand der Hinweise, die das Bild ihm gibt, herausfinden,\n " +
                "wo dieses Foto aufgenommen wurde.");

    }
    public void setGameControl(GameControl pGameControl){
        gameControl = pGameControl;
    }
}