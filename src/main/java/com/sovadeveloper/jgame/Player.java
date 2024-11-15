package com.sovadeveloper.jgame;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Set;

public class Player extends Rectangle {

    private static final int STEP = 5;

    public Player(int startX, int startY) {
        super(startX, startY, 20, 20);
        this.setFill(Color.PURPLE);
        this.setStroke(Color.RED);
        this.setStrokeWidth(2);
    }

    public void move(Set<KeyCode> activeKeys) {
        double deltaX = 0;
        double deltaY = 0;

        if (activeKeys.contains(KeyCode.W)) deltaY -= STEP;
        if (activeKeys.contains(KeyCode.S)) deltaY += STEP;
        if (activeKeys.contains(KeyCode.A)) deltaX -= STEP;
        if (activeKeys.contains(KeyCode.D)) deltaX += STEP;

        if (deltaX != 0 && deltaY != 0) {
            deltaX *= Math.sqrt(0.5);
            deltaY *= Math.sqrt(0.5);
        }

        this.setX(this.getX() + deltaX);
        this.setY(this.getY() + deltaY);
    }

    public void rotateTowards(double mouseX, double mouseY) {
        double deltaX = mouseX - (this.getX() + this.getWidth() / 2);
        double deltaY = mouseY - (this.getY() + this.getHeight() / 2);

        double angle = Math.toDegrees(Math.atan2(deltaY, deltaX));
        this.setRotate(angle);
    }
}
