package sample;

import sample.Objec.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main  extends Canvas implements Runnable
{
    public static String NAME = "Stepochka";
    Map<String,Boolean> action;
    Map<Integer,String> bind;
    ArrayList<Objc> entities;
    ArrayList<Block> blocks;
    Stepan stepan;
    ArrayList<Objc> laterKill;
    private static final long serialVersionUID = 1L;
    int time = 1000;
    int sum = 1000;

    private boolean running;

    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    private boolean peres=true;

    void setupManager()
    {
        bind=new HashMap<>();
        bind.put(KeyEvent.VK_UP,"up");
        bind.put(KeyEvent.VK_LEFT,"left");
        bind.put(KeyEvent.VK_SPACE,"space");
        bind.put(KeyEvent.VK_RIGHT,"right");
        action=new HashMap<>();
        action.put("up",false);
        action.put("left",false);
        action.put("space",false);
        action.put("right",false);
        action.put("down", false);
        entities= new ArrayList<>();

        stepan = new Stepan(400,350);
        laterKill=new ArrayList<>();
        entities.add(stepan);
    }

    public void start() {
        running = true;
        new Thread(this).start();
    }
    public void initBlocks()
    {
        for(int i =0;i<801;i=i+40)
        {
//
//            entities.add(getBlock(i,570));
            entities.add(new Block(i,570));
            if(i<200||i>600)
            {
                entities.add(new Block(i,400));
            }
            else
            {
                entities.add(new Block(i,230));
            }

        }
    }




    public static void main(String[] args)
    {
        Main game = new Main();
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JFrame frame = new JFrame(Main.NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(game, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        game.start();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            requestFocus();
            return;
        }

        Graphics g = bs.getDrawGraphics(); //ïîëó÷àåì Graphics èç ñîçäàííîé íàìè BufferStrategy
        g.setColor(Color.cyan); //âûáðàòü öâåò
        g.fillRect(0, 0, getWidth(), getHeight()); //çàïîëíèòü ïðÿìîóãîëüíèê
        draw(g);
        g.dispose();
        bs.show(); //ïîêàçàòü
    }

    private void draw(Graphics g)
    {
        stepan.draw(g);
        for (int e = 0; e < entities.size(); e++) {
            entities.get(e).draw(g);
        }
    }

    @Override
    public void run()
    {



        init();
        entities.add(new Roman(200,150));
        while(running)
        {
            try {
                Thread.sleep(10);
                if(time==0)
                {
                    entities.add(new Roman(400,350));
                    if(sum!=1) {
                        sum = sum - 1;
                        time=sum;
                    }
                }
                else
                {
                    time--;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            update();
            render();

        }
    }
    public void update()
    {
        upDateGameManager();
    }
    public void upDateGameManager()
    {
        stepan.move_x=0;
        stepan.move_y = 0;

        if (action.get("up")&& stepan.enegy==-1) stepan.enegy = 200;
        if (action.get("space"))
        {
            stepan.piy=true;
        }
        else
        {
            stepan.piy=false;
            peres=true;
        }
        if (action.get("left")) stepan.move_x = -1;
        if (action.get("right")) stepan.move_x = 1;
//        upDatePhisicManager(stepan);
//        stepan.upDate();
        for(int i=0;i<laterKill.size();i++)
        {
            entities.remove(laterKill.get(i));
        }
        laterKill.clear();
        for (int e = 0; e < entities.size(); e++)
        {

            upDatePhisicManager(entities.get(e));
            entities.get(e).upDate();
            if(entities.get(e).delit)
            {
                laterKill.add(entities.get(e));
            }
        }
    }

    public void init() {
        addKeyListener(new KeyInputHandler());

        setupManager();
        initBlocks();
    }

   private  class KeyInputHandler extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            String actio = bind.get(e.getKeyCode());
            if(actio.equals("up")||actio.equals("left")||actio.equals("space")||actio.equals("right"))
            {
                action.put(actio,true);
            }
        }

        public void keyReleased(KeyEvent e) {
            String actio = bind.get(e.getKeyCode());
            if(actio.equals("up")||actio.equals("left")||actio.equals("space")||actio.equals("right"))
            {
                action.put(actio, false);
            }
        }
    }

    Objc entityAtXY(Objc obj, int x, int y)
    { // поиск объекта по координатам
        int k=0;

        for (int i = 0; i < entities.size(); i++) {
            Objc e = entities.get(i); // рассматриваем все объекты на карте
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

    void logicsPoman(Objc obj)
    {
        if(stepan.pos_x>obj.pos_x)
        {
            obj.move_x=1;
        }
        else
        {
            obj.move_x=-1;
        }
        if(stepan.pos_y<obj.pos_y)
        {
            obj.enegy=200;
        }
    }

    void upDatePhisicManager(Objc obj)
    {
        if(obj.type.equals("Roman"))
        {
            logicsPoman(obj);
        }
        if(obj.enegy>0)
        {
            obj.move_y=-1;
            obj.enegy=obj.enegy-obj.speed;
        }
        if(obj.type.equals("Stepan"))
        {

            if(((Stepan) obj).piy)
            {
                if(obj.move_x!=0&&peres)
                {
                    Sigi sigi=new Sigi(obj.pos_x+36*obj.move_x,obj.pos_y+20,obj.move_x);
                    entities.add(sigi);
                }
                obj.direction=1;
                peres=false;
            }
            else
            {

                obj.direction=0;
            }
        }
        if(obj.type.equals("Roman"))
        {
            int rer = 123;
        }
        int newX = obj.pos_x + obj.move_x * obj.speed;
        int newY = obj.pos_y + obj.move_y * obj.speed;
        int downY = obj.pos_y + 1 * obj.speed;

        Objc downE=entityAtXY(obj, obj.pos_x, downY);
        Objc e = entityAtXY(obj, newX, newY);
        // объект на пути
        if (e != null) // если есть конфликт
        {
            obj.onTouchEntity(e);
        } // разбор конфликта внутри объекта
        if(downE==null )
        {
            if(obj.move_y==0) {
                obj.move_y = 1;
            }
        }else
        {
            if(downE.type.equals("Block"))
            {
                obj.enegy=-1;
            }
        }




    }
    }

