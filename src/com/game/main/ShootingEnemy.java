package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

public class ShootingEnemy extends GameObject
{

    private int width = 32;
    private int length = 32;
    private int fireRate = 300;

    private float enemyVel = 0.5f;
    private boolean stopShoot = false;

    Handler handler;
    Timer timer;

    public ShootingEnemy(float x, float y, ID id, Handler handler)
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
                    if (tempObject.getY() > y && !stopShoot)
                    {
                        handler.addObject(new Bullet(x + 8, y + 16, "down", ID.EnemyBullet, handler));
                        stopShoot = true;
                        timer = new Timer();
                        timer.schedule(new timerReset(), fireRate * 10);
                    }
                    else if (tempObject.getY() < y &&!stopShoot)
                    {
                        handler.addObject(new Bullet(x + 8, y, "up", ID.EnemyBullet, handler));
                        stopShoot = true;
                        timer = new Timer();
                        timer.schedule(new timerReset(), fireRate * 10);
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
                    if (tempObject.getX() > x && !stopShoot)
                    {
                        handler.addObject(new Bullet(x + 32, y + 8, "right", ID.EnemyBullet, handler));
                        stopShoot = true;
                        timer = new Timer();
                        timer.schedule(new timerReset(), fireRate * 10);
                    }
                    else if (tempObject.getX() < x && !stopShoot)
                    {
                        handler.addObject(new Bullet(x, y + 8, "left", ID.EnemyBullet, handler));
                        stopShoot = true;
                        timer = new Timer();
                        timer.schedule(new timerReset(), fireRate * 10);
                    }
                }
            }
        }
    }

    class timerReset extends TimerTask
    {
        public void run()
        {
            stopShoot = false;
            timer.cancel();
        }
    }

    public void render(Graphics g)
    {
        g.setColor(Color.orange);
        g.fillRect((int)x, (int)y, width, length);
    }
}