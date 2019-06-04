package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class Wall extends GameObject
{
    Handler handler;

    private int width;
    private int length;
    private int playerWidth = 32;
    private int playerLength = 32;

    public Wall(float x, float y, int width, int length, ID id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;
        this.length = length;
        this.width = width;
    }

    private Line2D checkBounds(String dir)
    {
        Line2D north = new Line2D.Float(x + 10, y, x + width - 10, y);
        Line2D south = new Line2D.Float(x + 10, y + length, x + width - 10, y + length);
        Line2D east = new Line2D.Float(x + width, y + 10, x + width, y + length - 10);
        Line2D west = new Line2D.Float(x, y + 10, x, y + length - 10);

        if (dir == "north")
        {
            return north;
        }
        else if (dir == "south")
        {
            return south;
        }
        else if (dir == "east")
        {
            return east;
        }
        else
        {
            return west;
        }
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, width, length);
    }

    public void tick()
    {
        collision();
    }

    private void collision()
    {
        for (int i = 0; i < handler.object.size(); i ++)
        {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Bullet || tempObject.getID() == ID.EnemyBullet)
            {
                if (tempObject.getBounds().intersectsLine(checkBounds("north")) || tempObject.getBounds().intersectsLine(checkBounds("south")))
                {
                    tempObject.setVelY(tempObject.getVelY() * -1);
                }
                else if (tempObject.getBounds().intersectsLine(checkBounds("east")) || tempObject.getBounds().intersectsLine(checkBounds("west")))
                {
                    tempObject.setVelX((tempObject.getVelX() * -1));
                }
            }
            if (tempObject.getID() == ID.Player || tempObject.getID() == ID.BasicEnemy)
            {
                if (tempObject.getBounds().intersectsLine(checkBounds("west")))
                {
                    tempObject.setX(x - playerWidth);
                }
                else if (tempObject.getBounds().intersectsLine(checkBounds("north")))
                {
                    tempObject.setY(y - playerLength);
                }
                else if (tempObject.getBounds().intersectsLine(checkBounds("south")))
                {
                    tempObject.setY(y + length);
                }
                else if (tempObject.getBounds().intersectsLine(checkBounds("east")))
                {
                    tempObject.setX(x + width);
                }
            }
        }
    }

    public void render(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect((int)x, (int)y, width, length);
    }
}