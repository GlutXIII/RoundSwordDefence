package com.roundsworddefence.game.utils;

public class CollisionRect {

    float x, y;
    int width, height;

    /**
     * Default constructor for collisionRect
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public CollisionRect (float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Method move collision rect to x,y location
     * @param x
     * @param y
     */
    public void move (float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method checks if two objects collides with each other
     * @param rect CollisionRect
     * @return boolean
     */
    public boolean collidesWith (CollisionRect rect) {
        return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
    }

}