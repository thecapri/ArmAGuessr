import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class StartGUI {
    JFrame titleScreen;
    JPanel mainTitle, settings, afterPlay, joinSession, hostSession;
    JLabel title1, title2, settingsTitle, changeControls, changeUsername, afterPlayLabel, joinSessionLabel, hostSessionLabel;
    Font titleFont1, titleFont2, normalFont;
    JButton playButton, settingsButton, backButton, saveControl, hostButton, joinButton, sendIPAdresse, sendRoundNumber;
    JTextField setControl, setUserName, setIPAdresse, setRoundNumber;
    JComboBox Controls;
    String IPAdresse;
    int RoundNumber;
    GameControl GameControl;

    public StartGUI(){
        try{
            titleFont1 = Font.createFont(Font.TRUETYPE_FONT, new File("ShortbreadCookies.ttf")).deriveFont(60f);
            titleFont2 = Font.createFont(Font.TRUETYPE_FONT, new File("ShortbreadCookies.ttf")).deriveFont(40f);
            normalFont = Font.createFont(Font.TRUETYPE_FONT, new File("ShortbreadCookies.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("ShortbreadCookies.ttf")));
        }catch(IOException | FontFormatException e){
            e.printStackTrace();
        }
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
        titleScreen.setSize(500,300);
        titleScreen.setResizable(false);
        titleScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        titleScreen.setLayout(null);
        titleScreen.setVisible(true);
        titleScreen.setLocationRelativeTo(null);


        mainTitle = new JPanel();
        mainTitle.setSize(500,500);
        mainTitle.setLayout(null);
        mainTitle.setBackground(Color.WHITE);
        titleScreen.add(mainTitle);

        title1 = new JLabel("ArmAGuessr");
        title1.setBounds(100,25,350,40);
        title1.setFont(titleFont1);
        mainTitle.add(title1);

        title2 = new JLabel("Arma X GeoGuessr");
        title2.setBounds(100,65,700,30);
        title2.setFont(titleFont2);
        mainTitle.add(title2);

        playButton = new JButton("Play");
        playButton.setBounds(170,120,150,40);
        playButton.setFont(normalFont);
        playButton.setBackground(Color.WHITE);
        playButton.addActionListener(plistener);
        mainTitle.add(playButton);

        settingsButton = new JButton("Settings");
        settingsButton.setBounds(170,165,150,40);
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
        afterPlay.setSize(500,500);
        afterPlay.setLayout(null);
        afterPlay.setBackground(Color.WHITE);
        afterPlay.setVisible(false);
        titleScreen.add(afterPlay);

        afterPlayLabel = new JLabel("Select Session");
        afterPlayLabel.setBounds(85,25,500,40);
        afterPlayLabel.setFont(titleFont1);
        afterPlay.add(afterPlayLabel);

        hostButton = new JButton("Host");
        hostButton.setBounds(170,120,150,40);
        hostButton.setFont(normalFont);
        hostButton.setBackground(Color.WHITE);
        hostButton.addActionListener(plistener);
        afterPlay.add(hostButton);

        joinButton = new JButton("Join");
        joinButton.setBounds(170,165,150,40);
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
        hostSession.setSize(500,500);
        hostSession.setLayout(null);
        hostSession.setBackground(Color.WHITE);
        hostSession.setVisible(false);
        titleScreen.add(hostSession);

        hostSessionLabel = new JLabel("How many Rounds?");
        hostSessionLabel.setBounds(15,35,500,40);
        hostSessionLabel.setFont(titleFont1);
        hostSession.add(hostSessionLabel);

        setRoundNumber = new JTextField("Enter Round Number");
        setRoundNumber.setBounds(55,120,260,40);
        setRoundNumber.setBackground(Color.WHITE);
        hostSession.add(setRoundNumber);

        sendRoundNumber = new JButton("Enter");
        sendRoundNumber.setBounds(320,120,125,40);
        sendRoundNumber.setBackground(Color.WHITE);
        sendRoundNumber.setFont(normalFont);
        sendRoundNumber.addActionListener(plistener);
        hostSession.add(sendRoundNumber);

        /**
         * JOIN SESSION
         */

        joinSession = new JPanel();
        joinSession.setSize(500,500);
        joinSession.setLayout(null);
        joinSession.setBackground(Color.WHITE);
        joinSession.setVisible(false);
        titleScreen.add(joinSession);

        joinSessionLabel = new JLabel("Enter IP-Adresse");
        joinSessionLabel.setBounds(45,25,500,40);
        joinSessionLabel.setFont(titleFont1);
        joinSession.add(joinSessionLabel);

        setIPAdresse = new JTextField("Enter IP-Adresse");
        setIPAdresse.setBounds(55,120,260,40);
        setIPAdresse.setBackground(Color.WHITE);
        joinSession.add(setIPAdresse);

        sendIPAdresse = new JButton("Enter");
        sendIPAdresse.setBounds(320,120,125,40);
        sendIPAdresse.setBackground(Color.WHITE);
        sendIPAdresse.setFont(normalFont);
        sendIPAdresse.addActionListener(plistener);
        joinSession.add(sendIPAdresse);


        /**
         * OPTIONS
         */

        settings = new JPanel();
        settings.setSize(500,500);
        settings.setLayout(null);
        settings.setBackground(Color.WHITE);
        settings.setVisible(false);
        titleScreen.add(settings);

        settingsTitle = new JLabel("Settings");
        settingsTitle.setBounds(130,25,300,40);
        settingsTitle.setFont(titleFont1);
        settings.add(settingsTitle);

        backButton = new JButton("Back");
        backButton.setBounds(370,25,93,40);
        backButton.setBackground(Color.WHITE);
        backButton.addActionListener(plistener);
        backButton.setFont(normalFont);
        settings.add(backButton);

        changeControls = new JLabel("Change your Key binds:");
        changeControls.setBounds(25,60,400,40);
        changeControls.setBackground(Color.WHITE);
        changeControls.setFont(normalFont);
        settings.add(changeControls);

        Controls = new JComboBox();
        Controls.setBounds(25,100,120,40);
        Controls.setFont(normalFont);
        Controls.setBackground(Color.WHITE);
        Controls.setMaximumRowCount(6);
        Controls.addItem("Right");
        Controls.addItem("Left");
        Controls.addItem("Jump");
        Controls.addItem("Crouch");
        Controls.addItem("Attack");
        Controls.addItem("Block");
        //Controls.addItemListener(iListener);
        settings.add(Controls);

        setControl = new JTextField("D");
        setControl.setBounds(155,105,30,30);
        setControl.setBackground(Color.WHITE);
        setControl.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (setControl.getText().length() >= 1 ) { // limit textfield to 3 characters
                    e.consume();
                }
            }
        });
        settings.add(setControl);

        changeUsername = new JLabel("Change your UserName");
        changeUsername.setBounds(25,135,400,40);
        changeUsername.setFont(normalFont);
        settings.add(changeUsername);

        setUserName = new JTextField("One");
        setUserName.setBounds(25,170,150,40);
        setUserName.setFont(normalFont);
        setUserName.setBackground(Color.WHITE);
        setUserName.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (setUserName.getText().length() >= 4 ) { // limit Text Field to 3 characters
                    e.consume();
                }
            }
        });
        settings.add(setUserName);

        saveControl = new JButton("Save");
        saveControl.setBackground(Color.WHITE);
        saveControl.setFont(normalFont);
        saveControl.setBounds(370,100,93,40);
        saveControl.addActionListener(plistener);
        settings.add(saveControl);
    }

    //ActionListener

    ActionListener plistener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == playButton) {
                mainTitle.setVisible(false);
                afterPlay.setVisible(true);
            }else if(e.getSource() == settingsButton){
                mainTitle.setVisible(false);
                settings.setVisible(true);
            }else if(e.getSource() == backButton){
                mainTitle.setVisible(true);
                settings.setVisible(false);
            }else if(e.getSource() == joinButton){
                afterPlay.setVisible(false);
                joinSession.setVisible(true);
            }else if(e.getSource() == hostButton){
                afterPlay.setVisible(false);
                hostSession.setVisible(true);
            }else if(e.getSource() == sendIPAdresse){
                IPAdresse = setIPAdresse.getText();
                System.out.println("IPAdresse Entered: "+IPAdresse);
            }else if(e.getSource() == sendRoundNumber){
                Boolean isNumber = false;
                try
                {
                    RoundNumber = Integer.parseInt(setRoundNumber.getText());
                    isNumber = true;
                }
                catch(NumberFormatException v)
                {
                    isNumber = false;
                }
                if(isNumber==true) {
                    GameControl = new GameControl(RoundNumber);
                }else new GameControl(3);
                titleScreen.dispose();
            }
        }
    };
}