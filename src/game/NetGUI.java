package game;

import javax.swing.*;
import java.awt.*;

public class NetGUI extends JFrame {
    JTextArea dialog;

    public NetGUI(){

        this.setSize(500, 300);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);

        dialog = new JTextArea();
        dialog.setBounds(0,0,500,300);
        dialog.setEditable(false);
        dialog.setLineWrap(true);
        dialog.setWrapStyleWord(true);
        dialog.setBackground(Color.WHITE);
        this.add(dialog);
        repaint();
    }
    public void addToScrollpane(String pText){
        dialog.append(pText+"\n");
        System.out.println(pText);
        repaint();

    }

    public void setNewTitle(String pTitle){
        this.setTitle("Network: "+pTitle);
        repaint();
    }

    @Override
    public void repaint() {
        super.repaint();
    }
}
