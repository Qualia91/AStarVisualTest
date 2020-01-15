package com.nick.wood.rts.utils;

public class Vector2I {

    private final int x;
    private final int y;

    public Vector2I() {
        x = 0;
        y = 0;
    }

    public Vector2I(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2I add(Vector2I vec2I) {
        int a = this.x + vec2I.x;
        int b = this.y + vec2I.y;
        return new Vector2I(a, b);
    }
    public Vector2I add(int number) {
        int a = this.x + number;
        int b = this.y + number;
        return new Vector2I(a, b);
    }
    public Vector2I subtract(Vector2I vec2d) {
        int a = this.x - vec2d.x;
        int b = this.y - vec2d.y;
        return new Vector2I(a, b);
    }
    public Vector2I subtract(int number) {
        int a = this.x + number;
        int b = this.y + number;
        return new Vector2I(a, b);
    }
    public Vector2I multiply(int number) {
        int a = this.x * number;
        int b = this.y * number;
        return new Vector2I(a, b);
    }
    public Vector2I divide(int number) {
        if (number == 0) {
            return new Vector2I(0, 0);
        }

        int a = this.x / number;
        int b = this.y / number;
        return new Vector2I(a, b);
    }
    public int dot(Vector2I vec2d) {
        return (this.x * vec2d.x) + (this.y * vec2d.y);
    }
    public int cross(Vector2I vec2d) {
        return (this.x * vec2d.y) - (vec2d.x * this.y);
    }
    public double length() {
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }
    public Vector2I midpoint(Vector2I Vector2I) {
        int x = (Vector2I.x + this.x)/2;
        int y = (Vector2I.y + this.y)/2;
        return new Vector2I();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
