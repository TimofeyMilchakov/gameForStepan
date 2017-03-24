package sample.Objec;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by tttt on 21.03.2017.
 */
public class Stepan  extends Objc {

    public ArrayList<Image> images;
    public boolean piy = false;
    public Stepan(int x, int y)
    {
        super("Stepan",x,y);
        images=new ArrayList<>();
        images.add(getImage("st1.png"));
        images.add(getImage("st2.png"));
//        image=images.get(0);
        direction=0;
//        move_y=1;
        speed=2;
        enegy=-1;


    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(images.get(direction),pos_x,pos_y,null);
    }



    @Override
    public void onTouchEntity(Objc e)
    {
        move_y=0;
        move_x=0;
        if(e.type.equals("Block")) {
            enegy = -1;
        }
        if(e.type.equals("Roman"))
        {
            e.move_x=0;
            e.move_y=0;
        }

    }



}
