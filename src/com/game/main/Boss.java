package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Boss extends GameObject
{

    private int width = 128;
    private int length = 128;

    private int tickSwitch = 0;

    private int red = 0;
    public static int bossHealth = -1;
    private int phase = 0;
    private float rotation = 0f;

    private boolean bossReady = false;
    private boolean switchDir = false;
    private boolean phase2Stop = false;
    private boolean downStop = false;

    private float enemyVel = 2.25f;

    Handler handler;

    public Boss(float x, float y, ID id, Handler handler)
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

        if (red < 255)
        {
            red += 1;
        }
        if (red == 254)
        {
            bossHealth = 50;
        }
        if (red == 255)
        {
            bossReady = true;
        }
        if (bossHealth > 25 && bossReady)
        {
            phase = 1;
        }
        else if (bossHealth <= 25 && bossReady)
        {
            phase = 2;
        }

        if (bossHealth <= 0 && bossReady)
        {
            handler.removeObject(this);
        }

        movement();
        collision();
    }

    private void collision()
    {
        for (int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Bullet && bossReady)
            {
                if (getBounds().intersects(tempObject.getBounds()))
                {
                    bossHealth -= 1;
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    private void movement()
    {
        if (phase == 1)
        {
            rotation += 5;
            if (rotation >= 360)
            {
                rotation -= 360;
            }
            if (tickSwitch == 0)
            {
                handler.addObject(new BossBullet(x + 64, y + 64, rotation, ID.EnemyBullet, handler));
                handler.addObject(new BossBullet(x + 64, y + 64, rotation + 90, ID.EnemyBullet, handler));
                handler.addObject(new BossBullet(x + 64, y + 64, rotation + 180, ID.EnemyBullet, handler));
                handler.addObject(new BossBullet(x + 64, y + 64, rotation + 270, ID.EnemyBullet, handler));
                tickSwitch += 1;
            }
            else if (tickSwitch >= 10)
            {
                tickSwitch = 0;
            }
            else
            {
                tickSwitch += 1;
            }
        }
        else if (phase == 2)
        {
            if (velY == 0)
            {
                velY = 2;
            }
            else if (y < 75)
            {
                velY *= -1;
            }
            else if (y > 524)
            {
                velY *= -1;
            }
            if (tickSwitch <= 30)
            {
                handler.addObject(new BossBullet(x + 64, y + 64, 45, ID.EnemyBullet, handler));
                handler.addObject(new BossBullet(x + 64, y + 64, 315, ID.EnemyBullet, handler));
                handler.addObject(new BossBullet(x + 64, y + 64, 90, ID.EnemyBullet, handler));
                handler.addObject(new BossBullet(x + 64, y + 64, 270, ID.EnemyBullet, handler));
                handler.addObject(new BossBullet(x + 64, y + 64, 135, ID.EnemyBullet, handler));
                handler.addObject(new BossBullet(x + 64, y + 64, 225, ID.EnemyBullet, handler));
                tickSwitch += 1;
            }
            else if (tickSwitch >= 60)
            {
                tickSwitch = 0;
            }
            else
            {
                tickSwitch += 1;
            }
        }
    }

    public void render(Graphics g)
    {
        Color fadeRed = new Color(red, 0, 0);
        g.setColor(fadeRed);
        g.fillRect((int)x, (int)y, width, length);
    }
}