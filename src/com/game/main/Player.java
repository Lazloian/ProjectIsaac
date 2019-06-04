package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends GameObject
{

    private int width = 32;
    private int length = 32;
    private int fireRate = 50; //One second is 100
    private int iTime = 300; //""

    private boolean shootStop = false;
    private boolean iStop = true;

    Timer timer;
    Timer iframes;

    /*public static int red = 0;
    public static int green = 0;
    public static int blue = 0;

    public static boolean redstop = false;
    public static boolean greenstop = false;
    public static boolean bluestop = false;*/

    private float playerVel = 5f;
    private float playerAccel = 0.5f;

    Handler handler;

    public Player(float x, float y, ID id, Handler handler)
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
        shoot();
        collision();
    }

    private void collision()
    {
        for (int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.EnemyBullet)
            {
                if (getBounds().intersects(tempObject.getBounds()) && iStop)
                {
                    HUD.health -= 1;
                    iStop = false;
                    iframes = new Timer();
                    iframes.schedule(new iframesReset(), iTime * 10);
                    if (tempObject.getID() == ID.EnemyBullet)
                    {
                        handler.removeObject(tempObject);
                    }
                }
            }
        }
    }

    private void movement()
    {
        if (KeyInput.keyDown[0])
        {
            if (velY > -playerVel)
            {
                velY -= playerAccel;
            }
            else
            {
                velY = -playerVel;
            }
        }
        else if (KeyInput.keyDown[1])
        {
            if (velY < playerVel)
            {
                velY += playerAccel;
            }
            else
            {
                velY = playerVel;
            }
        }
        else
        {
            if (velY > 0)
            {
                velY -= playerAccel;
            }
            else if (velY < 0)
            {
                velY += playerAccel;
            }
            else
            {
                velY = 0;
            }
        }
        if (KeyInput.keyDown[2])
        {
            if (velX > -playerVel)
            {
                velX -= playerAccel;
            }
            else
            {
                velX = -playerVel;
            }
        }
        else if (KeyInput.keyDown[3])
        {
            if (velX < playerVel)
            {
                velX += playerAccel;
            }
            else
            {
                velX = playerVel;
            }
        }
        else
        {
            if (velX > 0)
            {
                velX -= playerAccel;
            }
            else if (velX < 0)
            {
                velX += playerAccel;
            }
            else
            {
                velX = 0;
            }
        }
    }

    private void shoot()
    {
        if ((KeyInput.keyDown[4] || KeyInput.keyDown[5] || KeyInput.keyDown[6] || KeyInput.keyDown[7]) && !shootStop)
        {
            shootStop = true;
            if (KeyInput.keyDown[4])
            {
                handler.addObject(new Bullet(x + 8, y - 6, "up", ID.Bullet, handler));
            }
            else if (KeyInput.keyDown[5])
            {
                handler.addObject(new Bullet(x + 8, y + 22, "down", ID.Bullet, handler));
            }
            else if (KeyInput.keyDown[6])
            {
                handler.addObject(new Bullet(x - 6, y + 8, "left", ID.Bullet, handler));
            }
            else if (KeyInput.keyDown[7])
            {
                handler.addObject(new Bullet(x + 22, y + 8, "right", ID.Bullet, handler));
            }

            timer = new Timer();
            timer.schedule(new timerReset(), fireRate * 10);
        }
    }

    class timerReset extends TimerTask
    {
        public void run()
        {
            shootStop = false;
            timer.cancel();
        }
    }

    class iframesReset extends TimerTask
    {
        public void run()
        {
            iStop = true;
            iframes.cancel();
        }
    }

    public void render(Graphics g)
    {
        if (!iStop)
        {
            g.setColor(Color.pink);
        }
        else
        {
            g.setColor(Color.white);
        }

        g.fillRect((int)x, (int)y, width, length);
    }
}