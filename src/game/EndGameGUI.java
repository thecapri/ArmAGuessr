package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameGUI extends JFrame {
    int Points, AnzRunden;
    Font titleFont = new Font("Verdana", Font.BOLD, 35);
    Font normalFont = new Font("Verdana", Font.BOLD, 12);
    JLabel LEndPoints, LEndTitle, LEndRounds;
    JPanel endPanel;
    JButton EndButton;

    public EndGameGUI(int pPoints, int pAnzRunden){
        Points = pPoints;
        AnzRunden = pAnzRunden;
        init();
    }
    public void init(){
        this.setSize(500,300);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("ArmA Guessr");

        endPanel = new JPanel();
        endPanel.setSize(500,300);
        endPanel.setLayout(null);
        endPanel.setBackground(Color.WHITE);
        this.add(endPanel);

        LEndTitle = new JLabel("Game ended");
        LEndTitle.setBounds(115,50,250,50);
        LEndTitle.setFont(titleFont);
        endPanel.add(LEndTitle);

        LEndPoints = new JLabel("Points: "+Points+"/"+AnzRunden*1000);
        LEndPoints.setBounds(165,135,250,25);
        LEndPoints.setFont(normalFont);
        endPanel.add(LEndPoints);

        LEndRounds = new JLabel("Anzahl Runden: "+AnzRunden);
        LEndRounds.setBounds(165,115,250,25);
        LEndRounds.setFont(normalFont);
        endPanel.add(LEndRounds);

        EndButton = new JButton("Exit");
        EndButton.setBackground(Color.WHITE);
        EndButton.setBounds(180,190,100,40);
        EndButton.addActionListener(EndListener);
        endPanel.add(EndButton);

    }
    ActionListener EndListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == EndButton){
                System.exit(0);
            }
        }
    };
}
