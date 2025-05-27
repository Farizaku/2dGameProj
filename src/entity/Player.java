package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {

    protected static final String SPRITE_PATH = "/player/";
    GamePanel gamePanel;
    KeyHandler keyH;
    private BufferedImage[][] sprites;

    public Player(GamePanel gp, KeyHandler kh) {
        this.gamePanel = gp;
        this.keyH = kh;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
        direction = Direction.DOWN;
    }

    Map<Direction, BufferedImage[]> spriteMap = new EnumMap<>(Direction.class);
    public void getPlayerImage(){
        try{
            spriteMap.put(Direction.UP, new BufferedImage[]{
                loadImage("Walking_sprites/boy_up_1.png"),
                loadImage("Walking_sprites/boy_up_2.png")
            });
            spriteMap.put(Direction.DOWN, new BufferedImage[]{
                loadImage("Walking_sprites/boy_down_1.png"),
                loadImage("Walking_sprites/boy_down_2.png")
            });
            spriteMap.put(Direction.LEFT, new BufferedImage[]{
                loadImage("Walking_sprites/boy_left_1.png"),
                loadImage("Walking_sprites/boy_left_2.png")
            });
            spriteMap.put(Direction.RIGHT, new BufferedImage[]{
                loadImage("Walking_sprites/boy_right_1.png"),
                loadImage("Walking_sprites/boy_right_2.png")
            });

            initializeSpriteArray();

        } catch(IOException e){
            System.err.println("Error loading player image");
            e.printStackTrace();

            initializeSpriteArray();
        }
    }
    private BufferedImage loadImage(String path) throws IOException {
        InputStream is = getClass().getResourceAsStream(SPRITE_PATH + path);
        if (is == null) {
            throw new IOException("Could not load image: " + SPRITE_PATH + path);
        }
        return ImageIO.read(is);
    }

    private void initializeSpriteArray() {
        int directionCount = Direction.values().length;
        int framesPerDirection = 2;
        this.sprites = new BufferedImage[directionCount][framesPerDirection];

        for (Direction dir : Direction.values()) {
            sprites[dir.ordinal()] = spriteMap.get(dir);
        }
    }

    private static final int ANIMATION_SPEED =  10;
    private static final float DIAGONAL_SPEED_FACTOR = 0.707f;
    public void update() {
        int moveX = 0;
        int moveY = 0;

        if (keyH.upPressed) moveY = -speed;
        if (keyH.downPressed) moveY = speed;
        if (keyH.leftPressed) moveX = -speed;
        if (keyH.rightPressed) moveX = speed;

        if (moveX != 0 && moveY != 0) {
            moveX *= DIAGONAL_SPEED_FACTOR;
            moveY *= DIAGONAL_SPEED_FACTOR;
        }

        x += moveX;
        y += moveY;

        if (moveX != 0 || moveY != 0) {
            if (keyH.upPressed) direction = Direction.UP;
            if (keyH.downPressed) direction = Direction.DOWN;
            if (keyH.leftPressed) direction = Direction.LEFT;
            if (keyH.rightPressed) direction = Direction.RIGHT;

            spriteCounter++;
            if (spriteCounter > ANIMATION_SPEED) {
                spriteNumber = (spriteNumber == 1) ? 2 : 1;
                spriteCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2) {
        try{
            BufferedImage image = sprites[direction.ordinal()][spriteNumber-1];
            g2.drawImage(image, x, y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        } catch(Exception e) {
            // Fallback rendering
            g2.setColor(Color.RED);
            g2.fillRect(x, y, gamePanel.getTileSize(), gamePanel.getTileSize());
        }
    }
}
