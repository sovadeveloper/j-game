package com.sovadeveloper.jgame;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Chunk extends Pane {
    public static final int TILE_SIZE = 20; // Размер каждой клетки
    private final int chunkSize;
    private final TileType[][] tiles;
    private final Random random = new Random();

    public Chunk(int chunkSize, double offsetX, double offsetY) {
        this.chunkSize = chunkSize;
        this.tiles = new TileType[chunkSize][chunkSize];
        generateTiles(offsetX, offsetY); // Передаем offsetX и offsetY при вызове
        renderChunk();
    }

    private void generateTiles(double offsetX, double offsetY) {
        double scale = 0.05;  // Чем меньше значение, тем крупнее и плавнее переходы
        for (int y = 0; y < chunkSize; y++) {
            for (int x = 0; x < chunkSize; x++) {
                double nx = (x + offsetX) * scale;
                double ny = (y + offsetY) * scale;
                double noiseValue = SimplePerlinNoise.noise(nx, ny);

                // Определение типа клетки на основе значения шума
                if (noiseValue < -0.9) {
                    tiles[y][x] = TileType.WATER;
                } else if (noiseValue < -0.7) {
                    tiles[y][x] = TileType.SAND;
                } else if (noiseValue < 0.7) {
                    tiles[y][x] = TileType.GRASS;
                    // Разброс деревьев с использованием шума с высокой частотой
                    double treeNoise = SimplePerlinNoise.noise(nx * 19, ny * 19); // Увеличиваем частоту шума
                    if (treeNoise > 0.8) { // Вероятность появления дерева
                        tiles[y][x] = TileType.TREE;
                    }
                } else {
                    tiles[y][x] = TileType.STONE;
                }
            }
        }
    }

    private void renderChunk() {
        for (int y = 0; y < chunkSize; y++) {
            for (int x = 0; x < chunkSize; x++) {
                Rectangle tile = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                tile.setFill(getColorForTileType(tiles[y][x]));
                this.getChildren().add(tile);
            }
        }
    }

    private Color getColorForTileType(TileType tileType) {
        return switch (tileType) {
            case WATER -> Color.BLUE;
            case SAND -> Color.YELLOW;
            case GRASS -> Color.GREEN;
            case STONE -> Color.GRAY;
            case TREE -> Color.DARKGREEN;
        };
    }
}
