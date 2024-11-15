package com.sovadeveloper.jgame;

public class SimplePerlinNoise {

    // Генерация случайного градиента на основе координат
    private static double randomGradient(int x, int y) {
        int hash = x * 374761393 + y * 668265263; // Случайные "магические" числа
        hash = (hash ^ (hash >> 13)) * 1274126177;
        return (hash & 0x7fffffff) / (double) 0x7fffffff * 2 - 1;
    }

    // Линейная интерполяция
    private static double lerp(double a, double b, double t) {
        return a + t * (b - a);
    }

    // Функция сглаживания для плавных переходов
    private static double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    // Функция для вычисления 2D Перлин-шума
    public static double noise(double x, double y) {
        int x0 = (int) Math.floor(x);
        int y0 = (int) Math.floor(y);
        int x1 = x0 + 1;
        int y1 = y0 + 1;

        // Локальные координаты в пределах ячейки
        double dx = x - x0;
        double dy = y - y0;

        // Градиенты для четырёх углов
        double grad00 = randomGradient(x0, y0);
        double grad01 = randomGradient(x0, y1);
        double grad10 = randomGradient(x1, y0);
        double grad11 = randomGradient(x1, y1);

        // Интерполяция вдоль x для обоих направлений y
        double lerpX0 = lerp(grad00, grad10, fade(dx));
        double lerpX1 = lerp(grad01, grad11, fade(dx));

        // Интерполяция вдоль y для окончательного значения
        return lerp(lerpX0, lerpX1, fade(dy));
    }
}