package sample.Objec;

import java.awt.*;

/**
 * Created by tttt on 21.03.2017.
 */
public class Sigi extends Objc  {
    public Sigi(int x, int y, int i)
    {
        super("Sigi",x,y);
        if(i>0)
        {
            image = getImage("s1.png");
        }
        else {
            image = getImage("s2.png");
        }
        move_x=i;
        speed=4;
        size_x=35;
        size_y=4;
    }

    @Override
    public void upDate()
    {
        this.pos_y=this.pos_y+2*move_y;
        this.pos_x=this.pos_x+speed*move_x;
//        move_y=0;

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,pos_x,pos_y,null);
    }


    @Override
    public void onTouchEntity(Objc e)
    {
        if(!e.type.equals("Stepan")) {
            delit = true;
        }
        if(e.type.equals("Roman"))
        {
            e.delit=true;
        }
//        move_x=0;
//        move_y=0;
    }


}
