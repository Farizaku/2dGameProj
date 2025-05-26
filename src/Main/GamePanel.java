package Main;

import javax.swing.*;

public class GamePanel extends JPanel {
    //SCREEN SETTINGS

    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48px
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int maxScreenWidth = tileSize * maxScreenCol; // 768px
    final int maxScreenHeight = tileSize * maxScreenRow; //576px
}
