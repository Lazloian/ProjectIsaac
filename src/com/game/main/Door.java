package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Door extends GameObject
{
    Handler handler;
    private String direction = "";

    private int width = 100;
    private int length = 32;

    private boolean hitCheck = true;
    private boolean locked = true;
    private boolean enemy = false;

    public Door(float x, float y, String direction, ID id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;
        this.direction = direction;

        if (direction.equals("east") || direction.equals("west"))
        {
            width = 32;
            length = 100;
        }
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, width, length);
    }

    public void tick()
    {
        collision();
        lock();
    }

    private void collision()
    {
        for (int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Player)
            {
                if (tempObject.getBounds().intersects(getBounds()) && hitCheck && !locked)
                {
                    hitCheck = false;
                    new LevelLoader(direction, handler);
                }
            }
        }
    }

    private void lock()
    {
        enemy = false;

        for (int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.BasicEnemy)
            {
                enemy = true;
            }
        }
        if (enemy)
        {
            locked = true;
        }
        else
        {
            locked = false;
        }
    }

    public void render(Graphics g)
    {
        if (locked)
        {
            g.setColor(Color.red);
        }
        else
        {
            g.setColor(Color.green);
        }
        g.fillRect((int)x, (int)y, width, length);
    }


}