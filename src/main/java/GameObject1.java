import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class GameObject {

    protected int x;
    protected int y;
    protected int size;
    protected int speed;
    protected Color color;

    protected GameObject(int x, int y, int size, int speed, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
        this.color = color;
    }

    public void move() {
        y += speed;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public abstract void draw(Graphics2D g);
}
