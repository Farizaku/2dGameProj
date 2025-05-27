package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum [][];

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.getMaxScreenCol()][gp.getMaxScreenRow()];
        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass00.png"))));
            tile[1] = new Tile();
            tile[1].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png"))));
            tile[2] = new Tile();
            tile[2].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water00.png"))));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()){
                String line = br.readLine();
                while(col < gp.getMaxScreenCol()){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.getMaxScreenCol()){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics g2){
        for (int i = 0; i < gp.getMaxScreenCol(); i++){
            for (int j = 0; j < gp.getMaxScreenRow(); j++){
                int tileNum = mapTileNum[i][j];
                g2.drawImage(tile[tileNum].getImage(), i*gp.getTileSize(), j* gp.getTileSize(), gp.getTileSize(), gp.getTileSize(), null);
            }
        }

    }
}
