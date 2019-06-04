package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject
{

    private int height = 16;
    private int width = 16;
    private int bulletVel = 7;

    private float bulletAccel = 0.05f;

    private boolean velXStop = false;
    private boolean velYStop = false;

    private float playerVelX = 0;
    private float playerVelY = 0;

    private String direction = "";
    private ID id;

    Handler handler;

    public Bullet(float x, float y, String direction, ID id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;
        this.direction = direction;
        this.id = id;

        for (int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Player)
            {
                playerVelX = tempObject.getVelX() / 1.75f;
                playerVelY = tempObject.getVelY() / 2.75f;
            }
        }

        if (direction == "up")
        {
            velX = playerVelX;
            velY = -bulletVel + playerVelY;
        }
        else if (direction == "down")
        {
            velX = playerVelX;
            velY = bulletVel + playerVelY;
        }
        else if (direction == "left")
        {
            velX = -bulletVel + playerVelX;
            velY = playerVelY;
        }
        else if (direction == "right")
        {
            velX = bulletVel + playerVelX;
            velY = playerVelY;
        }
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, width, height);
    }

    public void tick()
    {
        x += velX;
        y += velY;

        if (x < 0 || x > Game.WIDTH - 15)
        {
            velX *= -1;
        }
        else if (y < 0 || y > Game.HEIGHT - 39)
        {
            velY *= -1;
        }

        x = Game.clamp(x, 0, Game.WIDTH - 5);
        y = Game.clamp(y, 0, Game.HEIGHT - 5);

        movement();
        //collision();

        //rainbow();
    }

    private void collision()
    {
        for (int i = 0; i < handler.object.size(); i ++)
        {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.EnemyBullet)
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
        if (velY > 0.1)
        {
            velY -= bulletAccel;
        }
        else if (velY < -0.1)
        {
            velY += bulletAccel;
        }
        else
        {
            velY = 0;
            velYStop = true;
        }
        if (velX > 0.1)
        {
            velX -= bulletAccel;
        }
        else if (velX < -0.1)
        {
            velX += bulletAccel;
        }
        else
        {
            velX = 0;
            velXStop = true;
        }
        if (velXStop && velYStop)
        {
            handler.removeObject(this);
        }
    }

    /*public void rainbow()
    {
        if (Player.red < 255 && !Player.redstop)
        {
            Player.red += 1;
        }
        else if (Player.red == 255 && !Player.redstop)
        {
            Player.redstop = true;
            Player.red = 0;
        }
        if (Player.green < 255 && Player.redstop && !Player.greenstop)
        {
            Player.green += 1;
        }
        else if (Player.green == 255 && !Player.greenstop)
        {
            Player.greenstop = true;
            Player.green = 0;
        }
        if (Player.blue < 255 && Player.redstop && Player.greenstop && !Player.bluestop)
        {
            Player.blue += 1;
        }
        else if (Player.blue == 255 && !Player.bluestop)
        {
            Player.red = 0;
            Player.green = 0;
            Player.blue = 0;
            Player.redstop = false;
            Player.greenstop = false;
            Player.bluestop = false;
        }
    }*/

    public void render(Graphics g)
    {
        //Color rainbow = new Color(Player.red, Player.green, Player.blue);
        if (id == ID.Bullet)
        {
            g.setColor(Color.blue);
        }
        else if (id == ID.EnemyBullet)
        {
            g.setColor(Color.red);
        }
        g.fillRect((int)x, (int)y, width, height);
    }
}