package com.game.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD
{
    public static float health = 3;
    private boolean bossExists = false;

    public void tick()
    {
        if (health < 0)
        {
            health = 0;
        }

        if (Boss.bossHealth >= 0)
        {
            bossExists = true;
        }
    }

    public void render(Graphics g)
    {
        if (health == 3)
        {
            g.setColor(Color.green);
            g.fillRect(15, 15, 50, 50);
            g.fillRect(80, 15, 50, 50);
            g.fillRect(145, 15, 50, 50);
        }
        else if (health == 2)
        {
            g.setColor(Color.green);
            g.fillRect(15, 15, 50, 50);
            g.fillRect(80, 15, 50, 50);
            g.setColor(Color.red);
            g.fillRect(145, 15, 50, 50);
        }
        else if (health == 1)
        {
            g.setColor(Color.green);
            g.fillRect(15, 15, 50, 50);
            g.setColor(Color.red);
            g.fillRect(80, 15, 50, 50);
            g.fillRect(145, 15, 50, 50);
        }
        else if (health == 0)
        {
            g.setColor(Color.red);
            g.fillRect(15, 15, 50, 50);
            g.fillRect(80, 15, 50, 50);
            g.fillRect(145, 15, 50, 50);
        }

        if (bossExists)
        {
            g.setColor(Color.red);
            g.fillRect(300, 15, 500, 50);
            g.setColor(Color.green);
            g.fillRect(300, 15, Boss.bossHealth * 10, 50);
        }

    }
}