import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player {

    private static final BufferedImage SPRITE = loadSprite();
    private static final int WIDTH = 90;
    private static final int IMAGE_SIZE = 108;
    private static final int IMAGE_Y = 432;
    private static final int CATCH_Y = IMAGE_Y + 40;
    private static final int CATCH_HEIGHT = 20;
    private static final int SPEED = 25;

    private int x = 355;

    public void moveLeft() {
        x = Math.max(0, x - SPEED);
    }

    public void moveRight() {
        x = Math.min(800 - WIDTH, x + SPEED);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, CATCH_Y, WIDTH, CATCH_HEIGHT);
    }

    public void draw(Graphics2D g) {
        int imageX = x - (IMAGE_SIZE - WIDTH) / 2;
        g.drawImage(SPRITE, imageX, IMAGE_Y, IMAGE_SIZE, IMAGE_SIZE, null);
    }

    private static BufferedImage loadSprite() {
        BufferedImage source = SpriteLoader.load("Basket - Copy.jpg");
        BufferedImage sprite = new BufferedImage(
                source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                int pixel = source.getRGB(x, y);
                int red = (pixel >>> 16) & 0xFF;
                int green = (pixel >>> 8) & 0xFF;
                int blue = pixel & 0xFF;
                int brightest = Math.max(red, Math.max(green, blue));
                int darkest = Math.min(red, Math.min(green, blue));

                if (darkest >= 190 && brightest - darkest <= 12) {
                    pixel &= 0x00FFFFFF;
                } else {
                    pixel |= 0xFF000000;
                }
                sprite.setRGB(x, y, pixel);
            }
        }

        return sprite;
    }
}
