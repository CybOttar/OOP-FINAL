import java.awt.Color;
import java.awt.Graphics2D;

public class Candy extends GameObject implements Collectible {

    public Candy(int x, int y) {
        super(x, y, 30, 3,
              new Color(255, 100, 160));
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval(x, y, size, size);

        g.setColor(Color.WHITE);
        g.fillOval(x + 8, y + 7, 7, 7);
    }

    @Override
    public void collect(ScoreManager score) {
        score.addScore(10);
    }
}
