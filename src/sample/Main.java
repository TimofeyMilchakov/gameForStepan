package sample;

import sample.Objec.Block;
import sample.Objec.Objc;
import sample.Objec.Stepan;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
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

    private boolean running;

    public static int WIDTH = 800;
    public static int HEIGHT = 600;

    void setupManager()
    {
        bind=new HashMap<>();
        bind.put(java.awt.Event.UP,"up");
        bind.put(java.awt.Event.LEFT,"left");
        bind.put(java.awt.Event.TAB,"tab");
        bind.put(java.awt.Event.RIGHT,"right");
        action=new HashMap<>();
        action.put("up",false);
        action.put("left",false);
        action.put("tab",false);
        action.put("right",false);
        entities= new ArrayList<>();
//        blocks=null;
        stepan = getStepan(400,300);
        laterKill=new ArrayList<>();
    }

    public void start() {
        running = true;
        new Thread(this).start();
    }
    public void initBlocks()
    {
        for(int i =0;i<801;i=i+40)
        {
//            Block b=getBlock(i,560);
            entities.add(getBlock(i,570));

        }
    }

    public Block getBlock(int x, int y) {
        BufferedImage sourceImage = null;
        String path="block.png";
        try {
            URL url = this.getClass().getClassLoader().getResource(path);
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Block sprite = new Block(Toolkit.getDefaultToolkit().createImage(sourceImage.getSource()),x,y);

        return sprite;
    }
    public Stepan getStepan(int x, int y) {
        BufferedImage sourceImage = null;
        String path="sl1.png";
        try {
            URL url = this.getClass().getClassLoader().getResource(path);
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stepan sprite = new Stepan(Toolkit.getDefaultToolkit().createImage(sourceImage.getSource()),x,y);

        return sprite;
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
        g.setColor(Color.black); //âûáðàòü öâåò
        g.fillRect(0, 0, getWidth(), getHeight()); //çàïîëíèòü ïðÿìîóãîëüíèê
        draw(g);
        g.dispose();
        bs.show(); //ïîêàçàòü
    }

    private void draw(Graphics g)
    {
        for (int e = 0; e < entities.size(); e++) {
            entities.get(e).draw(g);
        }
    }

    @Override
    public void run()
    {

        long lastTime = System.currentTimeMillis();
        long delta;

        init();

        while(running)
        {
            delta = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            render();
            update(delta);
        }
    }
    public void update(long delta) {

    }
    public void upDateGameManager()
    {
        stepan.move_x=0;
        stepan.move_y = 0;
        if (action.get("up")) stepan.move_y = -1;
        if (action.get("tab")) stepan.move_y = 1;
        if (action.get("left")) stepan.move_x = -1;
        if (action.get("right")) stepan.move_x = 1;
        for (int e = 0; e < entities.size(); e++) {
            entities.get(e).upDate();
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
            if(actio.equals("up")||actio.equals("left")||actio.equals("tab")||actio.equals("right"))
            {
                action.remove(action,true);
            }
        }

        public void keyReleased(KeyEvent e) {
            String actio = bind.get(e.getKeyCode());
            if(actio.equals("up")||actio.equals("left")||actio.equals("tab")||actio.equals("right"))
            {
                action.remove(action,false);
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

    String upDatePhisicManager(Objc obj)
    {
        if (obj.move_x == 0 && obj.move_y == 0)
        {
            return "stop";
        }
        int newX = obj.pos_x+obj.move_x*obj.speed;
        int newY = obj.pos_y+obj.move_y*obj.speed;

        Objc e = entityAtXY(obj,newX,newY);
        // объект на пути
        if (e != null) // если есть конфликт
        {
            obj.onTouchEntity(e);
        } // разбор конфликта внутри объекта




        switch (obj.move_x + 2 * obj.move_y) {
            case -1: // двигаемся влево
                return "left";
//            break;
            case 1: // двигаемся вправо
                return "right";
//            break;
            case -2: // двигаемся вверх
                return "up";
//            break;
            case 2: // стреляем
                return "tab";
//            break;
        }
        return  "as";
    }
}
