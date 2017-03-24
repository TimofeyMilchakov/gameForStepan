package sample.Objec;

import java.awt.*;

/**
 * Created by tttt on 21.03.2017.
 */
public class Roman extends Objc  {

    public Roman(int x, int y)
    {
        super("Roman",x,y);
        image=getImage("r.png");
        speed=1;
        size_y=40;
        size_x=22;


    }



    @Override
    public void draw(Graphics g) {
        g.drawImage(image,pos_x,pos_y,null);
    }



    @Override
    public void onTouchEntity(Objc e)
    {
        move_y=0;
        move_x=0;
        if(e.type.equals("Sigi"))
        {
            delit=true;
        }

    }


}
