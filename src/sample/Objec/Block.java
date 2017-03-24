package sample.Objec;

import java.awt.*;

/**
 * Created by tttt on 22.03.2017.
 */
public class Block extends Objc {
    public Block( int x, int y)
    {
        super("Block",x,y);
        image=getImage("block.png");


    }

    @Override
    public void upDate() {

    }

    @Override
    public void draw(Graphics g)
    {
        g.drawImage(image,pos_x,pos_y,null);
    }

    @Override
    public void onTouchEntity(Objc e)
    {
        if(e.move_y!=0)
        {
            e.move_y=0;
        }
        if(e.move_x==1)
        {
            e.move_x=0;
        }
        if(e.move_x==-1)
        {
            e.move_x=1;
        }
        e.enegy=-1;
        if(e.type.equals("Sigi"))
        {
//            e.delit=true;
        }
    }
}
