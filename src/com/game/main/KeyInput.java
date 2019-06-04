package com.game.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class KeyInput extends KeyAdapter
{
    private Handler handler;

    public static boolean[] keyDown = new boolean[8];

    public KeyInput(Handler handler)
    {
        this.handler = handler;

        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
        keyDown[4] = false;
        keyDown[5] = false;
        keyDown[6] = false;
        keyDown[7] = false;
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W)
        {
            keyDown[0] = true;
        }
        else if (key == KeyEvent.VK_S)
        {
            keyDown[1] = true;
        }
        else if (key == KeyEvent.VK_A)
        {
            keyDown[2] = true;
        }
        else if (key == KeyEvent.VK_D)
        {
            keyDown[3] = true;
        }
        else if (key == KeyEvent.VK_UP)
        {
            keyDown[4] = true;
        }
        else if (key == KeyEvent.VK_DOWN)
        {
            keyDown[5] = true;
        }
        else if (key == KeyEvent.VK_LEFT)
        {
            keyDown[6] = true;
        }
        else if (key == KeyEvent.VK_RIGHT)
        {
            keyDown[7] = true;
        }
        else if (key == KeyEvent.VK_ESCAPE)
        {
            System.exit(1);
        }
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W)
        {
            keyDown[0] = false;
        }
        else if (key == KeyEvent.VK_S)
        {
            keyDown[1] = false;
        }
        else if (key == KeyEvent.VK_A)
        {
            keyDown[2] = false;
        }
        else if (key == KeyEvent.VK_D)
        {
            keyDown[3] = false;
        }
        else if (key == KeyEvent.VK_UP)
        {
            keyDown[4] = false;
        }
        else if (key == KeyEvent.VK_DOWN)
        {
            keyDown[5] = false;
        }
        else if (key == KeyEvent.VK_LEFT)
        {
            keyDown[6] = false;
        }
        else if (key == KeyEvent.VK_RIGHT)
        {
            keyDown[7] = false;
        }

    }
}