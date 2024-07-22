package com.libgdx.example.config;

public class GameConfig {

    //public static final int WIDOW_HEIGHT = 800; //ORIGNAL VALUE
    public static final int WINDOW_WIDTH = 800;
    public static final int WIDOW_HEIGHT = 720;//800;
    public static final float UNIT_SCALE = 2.5f;

    public static final short GROUND_CATEGORY = 0;
    public static final short WALL_CATEGORY = 1;
    public static final short NONE_CATEGORY = 1 << 1;
    public static final short PLAYER_CATEGORY = 1 << 2;
    public static final short NPC_CATEGORY = 1 << 3;
    public static final short INTERACTIBLE_CATEGORY = 1 << 4;
}
