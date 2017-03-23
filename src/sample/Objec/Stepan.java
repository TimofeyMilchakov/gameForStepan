package sample.Objec;

import java.awt.*;

/**
 * Created by tttt on 21.03.2017.
 */
public class Stepan  extends Objc {

    public boolean piy = false;
    public Stepan(Image image, int x, int y)
    {
        super("Stepan",image,x,y);
        direction=1;
//        move_y=1;
        speed=5;

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,pos_x,pos_y,null);
    }



    @Override
    public void onTouchEntity(Objc e)
    {


    }



}
