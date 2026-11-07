
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Bomb extends GameObject {

    private static final BufferedImage SPRITE =
            SpriteLoader.load("candy_bomb.png");

    public Bomb(int x, int y) {
        super(x, y, 38, 4, Color.DARK_GRAY);
    }

    @Override
    public void draw(Graphics2D g) {
        int spriteSize = 56;
        int spriteOffset = (spriteSize - size) / 2;

        Graphics2D highlight = (Graphics2D) g.create();
        highlight.setColor(new Color(255, 190, 55, 85));
        highlight.fillOval(x - 10, y - 10, size + 20, size + 20);
        highlight.setColor(new Color(220, 105, 25, 190));
        highlight.drawOval(x - 10, y - 10, size + 20, size + 20);
        highlight.dispose();

        g.drawImage(SPRITE, x - spriteOffset, y - spriteOffset,
                spriteSize, spriteSize, null);
    }
}
