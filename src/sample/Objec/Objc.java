package sample.Objec;

import java.awt.*;

/**
 * Created by tttt on 21.03.2017.
 */
public abstract class Objc
{
    public int pos_y = 0;
    public int pos_x = 0;
    public int size_x = 0;
    public int size_y = 0;
    public int speed = 0;
    public int move_x = 0;
    public  int move_y = 0;
    public String type;
    public int direction = 0;
    public Image image;
    public boolean delit = false;
    Objc(String i,Image image,int x,int y)
    {
        type=i;
        this.image=image;
        pos_x=x;
        pos_y=y;
    }

    public abstract void upDate();
    public abstract void draw(Graphics g);
    public abstract void onTouchEntity(Objc e);

}
