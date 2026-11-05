
import java.awt.Color;
import java.awt.Graphics2D;

public class Bomb extends GameObject {

    public Bomb(int x, int y) {
        super(x, y, 30, 4, Color.DARK_GRAY);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillOval(x, y, size, size);

        g.setColor(Color.ORANGE);
        g.fillRect(x + 12, y - 5, 6, 8);

        g.setColor(Color.WHITE);
        g.fillOval(x + 8, y + 8, 5, 5);
    }
}
