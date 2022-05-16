package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameGUI extends JFrame {
    int Points, AnzRunden, EnemyPoints;
    Boolean winner;
    Font titleFont = new Font("Verdana", Font.BOLD, 35);
    Font normalFont = new Font("Verdana", Font.BOLD, 12);
    JLabel LEndPoints, LEndTitle, LEndRounds, LEnemyEndPoints, LWinner;
    JPanel endPanel;
    JButton EndButton;

    public EndGameGUI(int pPoints, int pAnzRunden, int pEnemyPoints,Boolean pWinner){
        winner = pWinner;
        EnemyPoints = pEnemyPoints;
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
        LEndPoints.setBounds(55,135,250,25);
        LEndPoints.setFont(normalFont);
        endPanel.add(LEndPoints);

        LEnemyEndPoints = new JLabel("Enemy Points: "+EnemyPoints+"/"+AnzRunden*1000);
        LEnemyEndPoints.setBounds(250,135,250,25);
        LEnemyEndPoints.setFont(normalFont);
        endPanel.add(LEnemyEndPoints);

        LWinner = new JLabel();
        if(winner == true){
            LWinner.setText("Du hast Gewonnen");
        }else LWinner.setText("Du hast Verloren");
        LWinner.setBounds(173,158,250,25);
        LWinner.setFont(normalFont);
        endPanel.add(LWinner);

        LEndRounds = new JLabel("Anzahl Runden: "+AnzRunden);
        LEndRounds.setBounds(173,95,250,25);
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
