package com.game.main;

public class LevelLoader
{
    Handler handler;
    
    private String direction = "";
    private String levelType = "1";

    private boolean northCheck = false;
    private boolean southCheck = false;
    private boolean eastCheck = false;
    private boolean westCheck = false;

    public LevelLoader(String direction, Handler handler)
    {
        this.handler = handler;
        this.direction = direction;

        if (direction.equals("north"))
        {
            Map.playerY -= 1;
        }
        else if (direction.equals("south"))
        {
            Map.playerY += 1;
        }
        else if (direction.equals("east"))
        {
            Map.playerX += 1;
        }
        else if (direction.equals("west"))
        {
            Map.playerX -= 1;
        }

        System.out.println(Map.playerX + ", " + Map.playerY);

        checkSurrounding(Map.playerX, Map.playerY);
        findType();
        System.out.println(levelType);
        loadLevel();
    }

    private void checkSurrounding(int x, int y)
    {
        northCheck = false;
        southCheck = false;
        eastCheck = false;
        westCheck = false;

        if (Map.map[y - 1][x] != 1 && Map.map[y - 1][x] != 0)
        {
            northCheck = true;
        }
        if (Map.map[y + 1][x] != 1 && Map.map[y + 1][x] != 0)
        {
            southCheck = true;
        }
        if (Map.map[y][x + 1] != 1 && Map.map[y][x + 1] != 0)
        {
            eastCheck = true;
        }
        if (Map.map[y][x - 1] != 1 && Map.map[y][x - 1] != 0)
        {
            westCheck = true;
        }
        System.out.println("north " + northCheck);
        System.out.println("south " + southCheck);
        System.out.println("east " + eastCheck);
        System.out.println("west " + westCheck);
    }

    private void findType()
    {
        if (northCheck && !southCheck && !eastCheck && !westCheck)
        {
            levelType = "N";
        }
        else if (!northCheck && southCheck && !eastCheck && !westCheck)
        {
            levelType = "S";
        }
        else if (!northCheck && !southCheck && eastCheck && !westCheck)
        {
            levelType = "E";
        }
        else if (!northCheck && !southCheck && !eastCheck && westCheck)
        {
            levelType = "W";
        }
        else if (northCheck && southCheck && !eastCheck && !westCheck)
        {
            levelType = "NS";
        }
        else if (!northCheck && !southCheck && eastCheck && westCheck)
        {
            levelType = "EW";
        }
        else if (northCheck && !southCheck && eastCheck && !westCheck)
        {
            levelType = "NE";
        }
        else if (!northCheck && southCheck && eastCheck && !westCheck)
        {
            levelType = "SE";
        }
        else if (!northCheck && southCheck && !eastCheck && westCheck)
        {
            levelType = "SW";
        }
        else if (northCheck && !southCheck && !eastCheck && westCheck)
        {
            levelType = "NW";
        }
    }

    private void loadLevel()
    {
        new Levels(levelType, direction, handler);
    }
}