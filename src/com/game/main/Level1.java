package com.game.main;

public class Level1
{

    Handler handler;

    public Level1(Handler handler)
    {
        handler.addObject(new Wall(0, 0, 75, (int)Game.HEIGHT, ID.Wall, handler));
        handler.addObject(new Wall(Game.WIDTH - 75, 0, 75, (int)Game.HEIGHT, ID.Wall, handler));
        handler.addObject(new Wall(0, 0, (int)Game.WIDTH, 75, ID.Wall, handler));
        handler.addObject(new Wall(0, Game.HEIGHT - 95, (int)Game.WIDTH, 75, ID.Wall, handler));
        handler.addObject(new Door((Game.WIDTH / 2) - 50, 50, "north", ID.Wall, handler));
        handler.addObject(new Door((Game.WIDTH / 2) - 50, 650, "south", ID.Wall, handler));
        handler.addObject(new Door(Game.WIDTH - 80, (Game.HEIGHT / 2) - 50, "east", ID.Wall, handler));
        handler.addObject(new Door(50, (Game.HEIGHT / 2) - 50, "west", ID.Wall, handler));
        handler.addObject(new Player((Game.WIDTH - 125), (Game.HEIGHT / 2) - 16, ID.Player, handler));
    }
}