package sample.manager;

import sample.Objec.Objc;
import sample.Objec.Stepan;

import java.util.ArrayList;



/**
 * Created by tttt on 21.03.2017.
 */
public class  GameManager
{
   static   ArrayList<Objc> entities;
    static Stepan stepan;
    static ArrayList<Objc> myObj;
    static ArrayList<Objc> laterKill;
    static ArrayList<Objc> enemy;
    static void setup()
    {
        entities=null;
        stepan = new Stepan();
        myObj=null;
        laterKill=null;
        enemy=null;
    }
    static void upDate()
    {
        if (GameManager.stepan == null)
            return;
        GameManager.stepan.move_x=0;
        GameManager.stepan.move_y = 0;
        if (EventsManager.action.get("up")) stepan.move_y = -1;
        if (EventsManager.action.get("tab")) stepan.move_y = 1;
        if (EventsManager.action.get("left")) stepan.move_x = -1;
        if (EventsManager.action.get("right")) stepan.move_x = 1;
        for (int e = 0; e < entities.size(); e++) {
            entities.get(e).upDate();
        }
    }
    static void draw()
    {
        for (int e = 0; e < entities.size(); e++) {
            entities.get(e).draw();
        }
    }
    static void loadAll()
    {

    }
}
