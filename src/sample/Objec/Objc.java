package sample.Objec;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by tttt on 21.03.2017.
 */
public abstract class Objc
{
    public int pos_y = 0;
    public int pos_x = 0;
    public int size_x = 40;
    public int size_y = 40;
    public int speed = 0;
    public int move_x = 0;
    public  int move_y = 0;
    public int enegy=-1;
    public String type;
    public int direction = 0;
    public Image image;
    public boolean delit = false;
    Objc(String i,int x,int y)
    {
        type=i;
        pos_x=x;
        pos_y=y;
    }

    public void upDate()
    {
        this.pos_y=this.pos_y+speed*move_y;
        this.pos_x=this.pos_x+speed*move_x;
        move_y=0;
        move_x=0;
    }
    public abstract void draw(Graphics g);
    public abstract void onTouchEntity(Objc e);
    Image getImage(String path) {
        BufferedImage sourceImage = null;
        try {
            URL url = this.getClass().getClassLoader().getResource(path);
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image image = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
        return image;


    }


}
