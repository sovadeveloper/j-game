package com.sovadeveloper.jgame;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;

import java.util.*;

public class GameField extends Pane {
    private final Player player;
    private final World world;
    private final Set<KeyCode> activeKeys = new HashSet<>();
    private final Translate cameraTranslate = new Translate();

    public GameField() {
        world = new World();
        player = new Player(300, 200);

        this.getChildren().addAll(world, player);
        this.getTransforms().add(cameraTranslate);

        this.setOnKeyPressed(e -> activeKeys.add(e.getCode()));
        this.setOnKeyReleased(e -> activeKeys.remove(e.getCode()));
        this.setOnMouseMoved(this::handleMouseMoved);

        this.setFocusTraversable(true);

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                player.move(activeKeys);
                updateCamera();
                world.updateChunks(player.getX(), player.getY());
            }
        };
        gameLoop.start();
    }

    private void handleMouseMoved(MouseEvent event) {
        player.rotateTowards(event.getX(), event.getY());
    }

    private void updateCamera() {
        double centerX = player.getX() + player.getWidth() / 2;
        double centerY = player.getY() + player.getHeight() / 2;
        cameraTranslate.setX(-centerX + getWidth() / 2);
        cameraTranslate.setY(-centerY + getHeight() / 2);
    }
}