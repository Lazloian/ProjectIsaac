package com.game.main;

import java.util.Random;

public class Map
{
    Random r = new Random();

    private static int mapY = 16;
    private static int mapX = 16;

    public static int[][] map = new int[mapY][mapX];

    public static int playerX = 0;
    public static int playerY = 0;

    private int enterX = 7;
    private int enterY = 8;

    private int level = 0;

    private int roomCount = 0;
    private int randomDir = 0;
    private int roomSurround = 0;

    private boolean roomCreated = false;

    private int lastX = enterX;
    private int lastY = enterY;

    public Map(int level)
    {
        this.level = level;

        for (int y = 0; y < mapY; y++)
        {
            for (int x = 0; x < mapX; x++)
            {
                if (x == 0 || x == mapX - 1 || y == 0 || y == mapY - 1)
                {
                    map[y][x] = 0;
                }
                else
                {
                    map[y][x] = 1;
                }
            }
        }

        map[enterY][enterX] = 2;

        playerX = enterX;
        playerY = enterY;

        for (int i = 0; i < 6; i++)
        {
            newRoom(lastX, lastY);
            showMap();
        }
    }

    public void showMap()
    {
        for (int y = 0; y < mapY; y++)
        {
            for (int x = 0; x < mapX; x++)
            {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void newRoom(int x, int y)
    {
        roomCreated = false;

        while (!roomCreated)
        {
            randomDir = r.nextInt(4);

            if (map[y - 1][x] == 1 && randomDir == 0 && checkSurround(x, y - 1)) //north
            {
                if (roomCount == 5)
                {
                    map[y - 1][x] = 3;
                }
                else
                {
                    map[y - 1][x] = r.nextInt(3) + 4;
                }
                lastX = x;
                lastY = y - 1;
                roomCount += 1;
                roomCreated = true;
            }
            else if (map[y + 1][x] == 1 && randomDir == 1 && checkSurround(x, y + 1)) //south
            {
                if (roomCount == 5)
                {
                    map[y + 1][x] = 3;
                }
                else
                {
                    map[y + 1][x] = r.nextInt(3) + 4;
                }
                lastX = x;
                lastY = y + 1;
                roomCount += 1;
                roomCreated = true;
            }
            else if (map[y][x - 1] == 1 && randomDir == 2 && checkSurround(x - 1 , y)) //west
            {
                if (roomCount == 5)
                {
                    map[y][x - 1] = 3;
                }
                else
                {
                    map[y][x - 1] = r.nextInt(3) + 4;
                }
                lastX = x - 1;
                lastY = y;
                roomCount += 1;
                roomCreated = true;
            }
            else if (map[y][x + 1] == 1 && randomDir == 3 && checkSurround(x + 1, y)) //east
            {
                if (roomCount == 5)
                {
                    map[y][x + 1] = 3;
                }
                else
                {
                    map[y][x + 1] = r.nextInt(3) + 4;
                }
                lastX = x + 1;
                lastY = y;
                roomCount += 1;
                roomCreated = true;
            }
            else
            {
                roomCreated = false;
                randomDir = r.nextInt(4);
            }
        }
    }

    private boolean checkSurround(int x, int y)
    {
        roomSurround = 0;

        if (map[y - 1][x] != 1 && map[y - 1][x] != 0)
        {
            roomSurround += 1;
        }
        if (map[y + 1][x] != 1 && map[y + 1][x] != 0)
        {
            roomSurround += 1;
        }
        if (map[y][x + 1] != 1 && map[y][x + 1] != 0)
        {
            roomSurround += 1;
        }
        if (map[y][x - 1] != 1 && map[y][x - 1] != 0)
        {
            roomSurround += 1;
        }
        if (roomSurround >= 2)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public int getEnterY()
    {
        return enterY;
    }

    public int getEnterX()
    {
        return enterX;
    }

    public int getPlayerX()
    {
        return playerX;
    }

    public int getPlayerY()
    {
        return playerY;
    }

    public void setPlayerX(int x)
    {
        playerX = x;
    }

    public void setPlayerY(int y)
    {
        playerY = y;
    }
}