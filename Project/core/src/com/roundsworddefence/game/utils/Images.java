package com.roundsworddefence.game.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class contains all paths to images in game
 */
public class Images {
    public static final String CASTLE_IMG_PATH = "Castle.png";
    public static final String PLAYER_SIMPLE_IMG_PATH = "Player.png";
    public static final String ENEMY_SIMPLE_IMG_PATH = "Enemy.png";
    public static final String BACKGROUND_IMG_PATH = "Background.png";
    public static final String END_GAME_IMG_PATH = "EndGame.png";
    public static final String FIRST_PLAYER_FRAME = "BohaterAtakObrot_Miejsce.png";
    public static final String SECOND_PLAYER_FRAME = "BohaterAtakObrot45.png";
    public static final String THIRD_PLAYER_FRAME = "BohaterAtakObrot90.png";
    public static final String FOURTH_PLAYER_FRAME = "BohaterAtakObrot135.png";
    public static final String FIFTH_PLAYER_FRAME = "BohaterAtakObrot180.png";
    public static final String SIXTH_PLAYER_FRAME = "BohaterAtakObrot225.png";
    public static final String SEVENTH_PLAYER_FRAME = "BohaterAtakObrot270.png";
    public static final String EIGHT_PLAYER_FRAME = "BohaterAtakObrot315.png";
    public static final String MAIN_MANU_IMG_PATH = "EndGame2/MainMenu2.png";
    public static final String PLAY_BUTTON_IMG_PATH = "png/png/PlayButton.png";


    public static final List<String> playerAnimationList= new ArrayList<String>();

    static{
        playerAnimationList.add(FIRST_PLAYER_FRAME);
        playerAnimationList.add(SECOND_PLAYER_FRAME);
        playerAnimationList.add(THIRD_PLAYER_FRAME);
        playerAnimationList.add(FOURTH_PLAYER_FRAME);
        playerAnimationList.add(FIFTH_PLAYER_FRAME);
        playerAnimationList.add(SIXTH_PLAYER_FRAME);
        playerAnimationList.add(SEVENTH_PLAYER_FRAME);
        playerAnimationList.add(EIGHT_PLAYER_FRAME);

    }
}
