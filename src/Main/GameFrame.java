package Main;

import javax.swing.*;

public class GameFrame extends JFrame {
    GameFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("2D adventure game");

        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(new GamePanel());
        this.pack();
    }
}
