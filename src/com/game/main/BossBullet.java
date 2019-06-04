package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BossBullet extends GameObject
{

    private int height = 20;
    private int width = 20;
    private int bulletVel = 4;
    private float rotation = 0f;

    private float bulletAccel = 0f;

    private int bounceLimit = 1;
    private int bounceCount = 0;

    private float lastVelX;
    private float lastVelY;

    private boolean velXStop = false;
    private boolean velYStop = false;

    private ID id;

    Handler handler;

    public BossBullet(float x, float y, float rotation, ID id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;
        this.id = id;
        this.rotation = rotation;

        bounceCount = 0;

        if (rotation >= 360)
        {
            rotation -= 360;
        }
        
        if (rotation >= 0 && rotation < 90)
        {
            velX = bulletVel * (float)Math.sin(Math.toRadians(rotation));
            velY = -bulletVel * (float)Math.cos(Math.toRadians(rotation));
        }
        else if (rotation >= 90 && rotation < 180)
        {
            velX = bulletVel * (float)Math.cos(Math.toRadians(rotation - 90));
            velY = bulletVel * (float)Math.sin(Math.toRadians(rotation - 90));
        }
        else if (rotation >= 180 && rotation < 270)
        {
            velX = -bulletVel * (float)Math.sin(Math.toRadians(rotation - 180));
            velY = bulletVel * (float)Math.cos(Math.toRadians(rotation - 180));
        }
        else if (rotation >= 270 && rotation < 360)
        {
            velX = -bulletVel * (float)Math.cos(Math.toRadians(rotation - 270));
            velY = -bulletVel * (float)Math.sin(Math.toRadians(rotation - 270));
        }

        lastVelX = velX;
        lastVelY = velY;
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

        if (bounceCount >= bounceLimit)
        {
            handler.removeObject(this);
        }

        movement();
        checkVel();
    }

    private void checkVel()
    {
        if (velX != lastVelX || velY != lastVelY)
        {
            bounceCount += 1;
            lastVelX = velX;
            lastVelY = velY;
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

    public void render(Graphics g)
    {
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