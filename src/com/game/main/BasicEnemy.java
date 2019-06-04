package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject
{

    private int width = 32;
    private int length = 32;

    private float enemyVel = 2.25f;

    Handler handler;

    public BasicEnemy(float x, float y, ID id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, width, length);
    }

    public void tick()
    {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 34);
        y = Game.clamp(y, 0, Game.HEIGHT - 55);

        movement();
        collision();
    }

    private void collision()
    {
        for (int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Bullet)
            {
                if (getBounds().intersects(tempObject.getBounds()))
                {
                    handler.removeObject(this);
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    private void movement()
    {
        for (int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Player)
            {
                if (tempObject.getX() - 5 > x)
                {
                    velX = enemyVel;
                }
                else if (tempObject.getX() + 5 < x)
                {
                    velX = -enemyVel;
                }
                else
                {
                    velX = 0;
                    if (tempObject.getY() > y)
                    {
                        //handler.addObject(new Bullet(x, y, "down", ID.Bullet, handler));
                    }
                    else if (tempObject.getY() < y)
                    {
                        //handler.addObject(new Bullet(x, y, "up", ID.Bullet, handler));
                    }
                }
                if (tempObject.getY() - 5 > y)
                {
                    velY = enemyVel;
                }
                else if (tempObject.getY() + 5 < y)
                {
                    velY = -enemyVel;
                }
                else
                {
                    velY = 0;
                    if (tempObject.getX() > x)
                    {
                        //handler.addObject(new Bullet(x, y, "right", ID.Bullet, handler));
                    }
                    else if (tempObject.getX() < x)
                    {
                        //handler.addObject(new Bullet(x, y, "left", ID.Bullet, handler));
                    }
                }
            }
        }
    }

    public void render(Graphics g)
    {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, width, length);
    }
}