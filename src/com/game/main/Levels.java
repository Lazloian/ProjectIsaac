package com.game.main;

public class Levels
{
    Handler handler;
    String levelType = "";
    String direction = "";

    public Levels(String levelType, String direction, Handler handler)
    {
        this.handler = handler;
        this.levelType = levelType;
        this.direction = direction;

        handler.object.clear();
        handler.addObject(new Wall(0, 0, 75, (int)Game.HEIGHT, ID.Wall, handler));
        handler.addObject(new Wall(Game.WIDTH - 75, 0, 75, (int)Game.HEIGHT, ID.Wall, handler));
        handler.addObject(new Wall(0, 0, (int)Game.WIDTH, 75, ID.Wall, handler));
        handler.addObject(new Wall(0, Game.HEIGHT - 95, (int)Game.WIDTH, 75, ID.Wall, handler));

        if (levelType.equals("N"))
        {
            loadN();
        }
        else if (levelType.equals("S"))
        {
            loadS();
        }
        else if (levelType.equals("E"))
        {
            loadE();
        }
        else if (levelType.equals("W"))
        {
            loadW();
        }
        else if (levelType.equals("NS"))
        {
            loadNS();
        }
        else if (levelType.equals("EW"))
        {
            loadEW();
        }
        else if (levelType.equals("NE"))
        {
            loadNE();
        }
        else if (levelType.equals("SE"))
        {
            loadSE();
        }
        else if (levelType.equals("SW"))
        {
            loadSW();
        }
        else if (levelType.equals("NW"))
        {
            loadNW();
        }

        loadLevelType();

        if (direction.equals("north"))
        {
            handler.addObject(new Player((Game.WIDTH / 2) - 16, Game.HEIGHT - 145, ID.Player, handler));
        }
        else if (direction.equals("south"))
        {
            handler.addObject(new Player((Game.WIDTH / 2) - 16, 90, ID.Player, handler));
        }
        else if (direction.equals("east"))
        {
            handler.addObject(new Player(90, (Game.HEIGHT / 2) - 16, ID.Player, handler));
        }
        else if (direction.equals("west"))
        {
            handler.addObject(new Player((Game.WIDTH - 125), (Game.HEIGHT / 2) - 16, ID.Player, handler));
        }
        else
        {
            handler.addObject(new Player((Game.WIDTH / 2) - 16, (Game.HEIGHT / 2) - 16, ID.Player, handler));
        }
    }

    private void loadLevelType()
    {
        if (Map.map[Map.playerY][Map.playerX] == 4)
        {
            handler.addObject(new Wall((Game.WIDTH / 2) - 75, (Game.HEIGHT / 2) - 75, 150, 150, ID.Wall, handler));
            handler.addObject(new BasicEnemy(200, 200, ID.BasicEnemy, handler));
            handler.addObject(new BasicEnemy(Game.WIDTH - 300, Game.HEIGHT - 300, ID.BasicEnemy, handler));
            handler.addObject(new ShootingEnemy((Game.WIDTH / 2) + 100, (Game.HEIGHT / 2) - 16, ID.BasicEnemy, handler));
            handler.addObject(new ShootingEnemy((Game.WIDTH / 2) - 100, (Game.HEIGHT / 2) - 16, ID.BasicEnemy, handler));
            handler.addObject(new ShootingEnemy((Game.WIDTH / 2), (Game.HEIGHT / 2) - 100, ID.BasicEnemy, handler));
            handler.addObject(new ShootingEnemy((Game.WIDTH / 2), (Game.HEIGHT / 2) + 100, ID.BasicEnemy, handler));
        }
        else if (Map.map[Map.playerY][Map.playerX] == 5)
        {
            handler.addObject(new Wall(200, 200, 100, 100, ID.Wall, handler));
            handler.addObject(new Wall(200, Game.HEIGHT - 300, 100, 100, ID.Wall, handler));
            handler.addObject(new Wall(Game.WIDTH - 300, 200, 100, 100, ID.Wall, handler));
            handler.addObject(new Wall(Game.WIDTH - 300, Game.HEIGHT - 300, 100, 100, ID.Wall, handler));
            handler.addObject(new Wall((Game.WIDTH / 2) - 75, (Game.HEIGHT / 2) - 75, 150, 150, ID.Wall, handler));
            handler.addObject(new BasicEnemy(150, 100, ID.BasicEnemy, handler));
            handler.addObject(new BasicEnemy(150, Game.HEIGHT - 150, ID.BasicEnemy, handler));
            handler.addObject(new BasicEnemy(Game.WIDTH - 150, 100, ID.BasicEnemy, handler));
            handler.addObject(new BasicEnemy(Game.WIDTH - 150, Game.HEIGHT - 150, ID.BasicEnemy, handler));
        }
        else if (Map.map[Map.playerY][Map.playerX] == 6)
        {
            handler.addObject(new Wall(200, 200, 100, 350, ID.Wall, handler));
            handler.addObject(new Wall(Game.WIDTH - 300, 200, 100, 350, ID.Wall, handler));
            handler.addObject(new Wall(400, 300, 350, 100, ID.Wall, handler));
            handler.addObject(new BasicEnemy(200, 100, ID.BasicEnemy, handler));
            handler.addObject(new BasicEnemy(200, Game.HEIGHT - 150, ID.BasicEnemy, handler));
            handler.addObject(new BasicEnemy(Game.WIDTH - 200, 100, ID.BasicEnemy, handler));
            handler.addObject(new BasicEnemy(Game.WIDTH - 200, Game.HEIGHT - 150, ID.BasicEnemy, handler));
        }
        else if (Map.map[Map.playerY][Map.playerX] == 3)
        {
            handler.addObject(new Boss((Game.WIDTH / 2) - 64, (Game.HEIGHT / 2) - 64, ID.BasicEnemy, handler));
        }
    }

    private void loadN()
    {
        handler.addObject(new Door((Game.WIDTH / 2) - 50, 50, "north", ID.Wall, handler));
    }
    private void loadS()
    {
        handler.addObject(new Door((Game.WIDTH / 2) - 50, 650, "south", ID.Wall, handler));
    }
    private void loadE()
    {
        handler.addObject(new Door(Game.WIDTH - 80, (Game.HEIGHT / 2) - 50, "east", ID.Wall, handler));
    }
    private void loadW()
    {
        handler.addObject(new Door(50, (Game.HEIGHT / 2) - 50, "west", ID.Wall, handler));
    }
    private void loadNS()
    {
        handler.addObject(new Door((Game.WIDTH / 2) - 50, 50, "north", ID.Wall, handler));
        handler.addObject(new Door((Game.WIDTH / 2) - 50, 650, "south", ID.Wall, handler));
    }
    private void loadEW()
    {
        handler.addObject(new Door(Game.WIDTH - 80, (Game.HEIGHT / 2) - 50, "east", ID.Wall, handler));
        handler.addObject(new Door(50, (Game.HEIGHT / 2) - 50, "west", ID.Wall, handler));
    }
    private void loadNE()
    {
        handler.addObject(new Door((Game.WIDTH / 2) - 50, 50, "north", ID.Wall, handler));
        handler.addObject(new Door(Game.WIDTH - 80, (Game.HEIGHT / 2) - 50, "east", ID.Wall, handler));
    }
    private void loadSE()
    {
        handler.addObject(new Door((Game.WIDTH / 2) - 50, 650, "south", ID.Wall, handler));
        handler.addObject(new Door(Game.WIDTH - 80, (Game.HEIGHT / 2) - 50, "east", ID.Wall, handler));
    }
    private void loadSW()
    {
        handler.addObject(new Door((Game.WIDTH / 2) - 50, 650, "south", ID.Wall, handler));
        handler.addObject(new Door(50, (Game.HEIGHT / 2) - 50, "west", ID.Wall, handler));
    }
    private void loadNW()
    {
        handler.addObject(new Door((Game.WIDTH / 2) - 50, 50, "north", ID.Wall, handler));
        handler.addObject(new Door(50, (Game.HEIGHT / 2) - 50, "west", ID.Wall, handler));
    }
}