package sample.Objec;

import java.awt.*;

/**
 * Created by tttt on 21.03.2017.
 */
public class Roman extends Objc  {

    Roman(Image image, int x, int y)
    {
        super("Roman",image,x,y);

    }

    @Override
    public void upDate() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,pos_x,pos_y,null);
    }



    @Override
    public void onTouchEntity(Objc e) {

    }


}
