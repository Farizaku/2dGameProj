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

    int FPS = 60;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    GamePanel(){
        this.setPreferredSize(new Dimension(maxScreenWidth, maxScreenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        startGameThread();
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    private void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double deltaTime = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null){
            currentTime = System.nanoTime();

            deltaTime += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;
            if(deltaTime >= 1){
                update();
                repaint();
            }
        }
    }

    public void update(){
        if(keyH.upPressed){
            playerY -= playerSpeed;
            System.out.println(playerY);
        }
        if(keyH.downPressed){
            playerY += playerSpeed;
        }
        if(keyH.leftPressed){
            playerX -= playerSpeed;
        }
        if(keyH.rightPressed){
            playerX += playerSpeed;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);

        g2.fillRect(playerX,playerY, tileSize, tileSize);
        g2.dispose();
    }
}
