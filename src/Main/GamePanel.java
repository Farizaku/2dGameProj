package Main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS

    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48px
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int maxScreenWidth = tileSize * maxScreenCol; // 768px
    final int maxScreenHeight = tileSize * maxScreenRow; //576px

    Thread gameThread;

    GamePanel(){
        this.setPreferredSize(new Dimension(maxScreenWidth, maxScreenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        startGameThread();
    }

    private void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null){
            System.out.println("The game is running! ");
        }
    }

    public void update(){

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
