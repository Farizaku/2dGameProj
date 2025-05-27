package main;

import entity.Player;
import tile.TileManager;

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

    public int getTileSize() {
        return tileSize;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getMaxScreenWidth() {
        return maxScreenWidth;
    }

    public int getMaxScreenHeight() {
        return maxScreenHeight;
    }

    //FPS
    int FPS = 60;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    GamePanel(){
        this.setPreferredSize(new Dimension(maxScreenWidth, maxScreenHeight));
        this.setBackground(Color.black);
        this.addKeyListener(keyH);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.requestFocusInWindow();
        startGameThread();
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
                deltaTime--;
            }
        }
    }

    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
