package sample.Objec;

/**
 * Created by tttt on 21.03.2017.
 */
public  interface Obj
{

    int pos_y = 0;
    int pos_x = 0;
    int size_x = 0;
    int size_y = 0;
    int speed = 0;
    int move_x = 0;
    int move_y = 0;
    int direction = 0;
    void upDate();
    void draw();
    void onTouchEntity();


}
