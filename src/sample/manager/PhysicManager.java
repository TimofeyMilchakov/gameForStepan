package sample.manager;

import sample.Objec.Obj;

/**
 * Created by tttt on 21.03.2017.
 */
public class PhysicManager
{
    void upDate(Obj obj)
    {
        if (obj.move_x == 0 && obj.move_y == 0)
        {
            return;
        }
        int newX = obj.pos_x+obj.move_x*obj.speed;
        int newY = obj.pos_y+obj.move_y*obj.speed;

    }
    Obj entityAtXY(Obj obj,int x,int y)
    { // поиск объекта по координатам
    int k=0;

    for (int i = 0; i < GameManager.entities.size(); i++) {
        Obj e = GameManager.entities.get(i); // рассматриваем все объекты на карте
        if (e.pos_y != obj.pos_y||e.pos_x!= obj.pos_x) { // не совпадает
            if (x + obj.size_x + k< e.pos_x || // не пересекаются
                    y + obj.size_y + k< e.pos_y ||
                    x-k > e.pos_x + e.size_x ||
                    y-k> e.pos_y + e.size_y)
                continue;
            return e; // найден объект
        }
    }
    return null; // объект не найден
}
}
