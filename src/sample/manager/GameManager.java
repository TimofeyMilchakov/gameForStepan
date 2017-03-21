package sample.manager;

import sample.Objec.Obj;
import sample.Objec.Stepan;

import java.util.ArrayList;

import static sample.Objec.Obj.move_x;

/**
 * Created by tttt on 21.03.2017.
 */
public class  GameManager
{
   static   ArrayList<Obj> entities;
    static Stepan stepan;
    static ArrayList<Obj> myObj;
    static ArrayList<Obj> laterKill;
    static ArrayList<Obj> enemy;
    static void setup()
    {
        entities=null;
        stepan=null;
        myObj=null;
        laterKill=null;
        enemy=null;
    }
    static void upDate()
    {
        if (GameManager.stepan == null)
            return;
        stepan.move_x=0;
        GameManager.stepan.move_y = 0;
        if (EventsManager.action.get("up")) stepan.move_y = -1;
        if (EventsManager.action.get("tab")) this.player.move_y = 1;
        if (EventsManager.action.get("left")) this.player.move_x = -1;
        if (EventsManager.action.get("right")) this.player.move_x = 1;
        for (int e = 0; e < entities.size(); e++) {
            entities.get(e).upDate();
        }
    }
    static void draw()
    {
        for (int e = 0; e < entities.size(); e++) {
            entities.get(e).draw(ctx);
        }
    }
    static void loadAll()
    {

    }
}
