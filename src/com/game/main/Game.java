package com.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable
{
    private static final long serialVersionUID = 2L;

    public static final float WIDTH = 1150, HEIGHT = 750;
    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private HUD hud;

    public Game()
    {
        handler = new Handler();
        hud = new HUD();
        new Window((int)WIDTH, (int)HEIGHT, "ProjectIsaac", this);
        new Map(1);

        this.addKeyListener(new KeyInput(handler));

        new LevelLoader("start", handler);
    }

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1)
            {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    
    private void tick()
    {
        handler.tick();
        hud.tick();
    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, (int)WIDTH, (int)HEIGHT);

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max)
    {
        if (var >= max)
        {
            return var = max;
        }
        if (var <= min)
        {
            return var = min;
        }
        else
        {
            return var;
        }
    }

    public static void main(String[] args)
    {
        System.setProperty("sun.java2d.opengl", "True");
        new Game();
    }
}