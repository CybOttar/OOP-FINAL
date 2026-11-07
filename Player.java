package candycatcher;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player {

    private int x = 350;
    private final int y = 490;
    private final int width = 100;
    private final int height = 35;
    private final int speed = 25;

    public void moveLeft() {
        x = Math.max(0, x - speed);
    }

    public void moveRight() {
        x = Math.min(800 - width, x + speed);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics2D g) {
        g.setColor(new Color(255, 120, 180));
