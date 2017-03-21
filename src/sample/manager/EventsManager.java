package sample.manager;

import java.awt.Event;

import java.util.Map;

/**
 * Created by tttt on 21.03.2017.
 */
public class EventsManager
{
     Map<String,Boolean> action;
     Map<Integer,String> bind;
     EventsManager()
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
     void onKeyUp(Event event)
     {
          String action = this.bind.get(event.key);
          if(action.equals("up")||action.equals("left")||action.equals("tab")||action.equals("right"))
          {
               this.action.remove(action,true);
          }
     }

     void onKeyDown(Event event)
     {
          String action = this.bind.get(event.key);
          if(!action.isEmpty())
          {
               this.action.remove(action,false);
          }
     }
}
