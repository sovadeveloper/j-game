package com.sovadeveloper.jgame;

import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class World extends Pane {
    public static final int CHUNK_SIZE = 20; // Размер чанка в тайлах
    private static final int VIEW_DISTANCE = 4; // Радиус видимости игрока в чанках
    private final Map<String, Chunk> loadedChunks = new HashMap<>();

    public World() {}

    // Обновление чанков в зависимости от положения игрока
    public void updateChunks(double playerX, double playerY) {
        // Определяем, в каком чанке находится игрок
        int playerChunkX = (int) (playerX / (CHUNK_SIZE * Chunk.TILE_SIZE));
        int playerChunkY = (int) (playerY / (CHUNK_SIZE * Chunk.TILE_SIZE));

        // Отображаем только чанки в области видимости
        Map<String, Boolean> visibilityMap = new HashMap<>();

        for (int y = playerChunkY - VIEW_DISTANCE; y <= playerChunkY + VIEW_DISTANCE; y++) {
            for (int x = playerChunkX - VIEW_DISTANCE; x <= playerChunkX + VIEW_DISTANCE; x++) {
                String chunkKey = x + "," + y;
                visibilityMap.put(chunkKey, true);

                if (!loadedChunks.containsKey(chunkKey)) {
                    // Вычисляем смещение для генерации шума Перлина
                    double offsetX = x * CHUNK_SIZE;
                    double offsetY = y * CHUNK_SIZE;

                    // Создание нового чанка с учетом смещения
                    Chunk chunk = new Chunk(CHUNK_SIZE, offsetX, offsetY);

                    // Установка позиции чанка на экране
                    chunk.setLayoutX(x * CHUNK_SIZE * Chunk.TILE_SIZE);
                    chunk.setLayoutY(y * CHUNK_SIZE * Chunk.TILE_SIZE);

                    // Сохранение чанка
                    loadedChunks.put(chunkKey, chunk);
                }

                // Добавляем чанк в отображение, если он не добавлен
                Chunk chunk = loadedChunks.get(chunkKey);
                if (!this.getChildren().contains(chunk)) {
                    this.getChildren().add(chunk);
                }
            }
        }

        // Убираем из отображения чанки за пределами видимости
        loadedChunks.forEach((key, chunk) -> {
            if (!visibilityMap.containsKey(key)) {
                this.getChildren().remove(chunk); // Удаляем только из отображения
            }
        });
    }
}
