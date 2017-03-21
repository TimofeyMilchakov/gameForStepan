package sample.manager;

import java.awt.Event;

import java.util.Map;

/**
 * Created by tttt on 21.03.2017.
 */
public class EventsManager
{
     static  Map<String,Boolean> action;
     static  Map<Integer,String> bind;
     static void setup()
     {
          bind.put(java.awt.Event.UP,"up");
          bind.put(java.awt.Event.LEFT,"left");
          bind.put(java.awt.Event.TAB,"tab");
          bind.put(java.awt.Event.RIGHT,"right");
          action.put("up",false);
          action.put("left",false);
          action.put("tab",false);
          action.put("right",false);
     }
     static  void onKeyUp(Event event)
     {
          String action = EventsManager.bind.get(event.key);
          if(action.equals("up")||action.equals("left")||action.equals("tab")||action.equals("right"))
          {
               EventsManager.action.remove(action,true);
          }
     }

     static  void onKeyDown(Event event)
     {
          String action = EventsManager.bind.get(event.key);
          if(!action.isEmpty())
          {
               EventsManager.action.remove(action,false);
          }
     }
}
