package sample.Objec;

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
    public int direction = 0;


    public abstract void upDate();
    public abstract void draw();
    public abstract boolean onTouchEntity();
}
