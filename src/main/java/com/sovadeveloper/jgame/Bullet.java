package com.sovadeveloper.jgame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet extends Circle {

    private static final int SPEED = 20;

    private final double directionX;
    private final double directionY;

    public Bullet (double startX, double startY, double directionAngle) {
        super(startX, startY, 3);
        this.setFill(Color.BLACK);
        this.directionX = Math.cos(Math.toRadians(directionAngle)) * SPEED;
        this.directionY = Math.sin(Math.toRadians(directionAngle)) * SPEED;
    }

    public void move() {
        this.setCenterX(this.getCenterX() + this.directionX);
        this.setCenterY(this.getCenterY() + this.directionY);
    }
}
