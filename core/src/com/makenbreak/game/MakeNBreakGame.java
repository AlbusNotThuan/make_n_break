package com.makenbreak.game;

import com.badlogic.gdx.Game;

public class MakeNBreakGame extends Game {
    @Override
    public void create() {
        setScreen(new MainMenuScreen(this));
    }
}
