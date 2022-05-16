package game;

import javax.swing.*;
import java.awt.*;

public class NetGUI extends JFrame {
    JPanel NetPanel;
    JTextArea dialog;

    public NetGUI(){

        this.setSize(500, 300);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);


        NetPanel = new JPanel();
        NetPanel.setSize(500, 300);
        NetPanel.setLayout(null);
        NetPanel.setBackground(Color.WHITE);
        this.add(NetPanel);

        dialog = new JTextArea();
        dialog.setBounds(0,0,500,300);
        dialog.setEditable(false);
        dialog.setLineWrap(true);
        dialog.setWrapStyleWord(true);
        dialog.setBackground(Color.WHITE);
        NetPanel.add(dialog);
        repaint();
    }
    public void addToScrollpane(String pText){
        dialog.append(pText+"\n");
        repaint();
    }

    public void setNewTitle(String pTitle){
        this.setTitle("Network: "+pTitle);
    }
}
