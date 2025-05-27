package entity;

import java.awt.image.BufferedImage;

public class Entity {
    //protected static final String SPRITE_PATH = "res/";
    protected enum Direction {UP, DOWN, LEFT, RIGHT}
    protected int x,y, speed;

    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    protected Direction direction;

    protected int spriteCounter = 0;
    protected int spriteNumber = 1;
}
