import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Candy extends GameObject implements Collectible {

    protected static final int SPRITE_SIZE = 46;

    private static final BufferedImage[] SPRITES = {
        SpriteLoader.load("blue_jelly.png"),
        SpriteLoader.load("grape.png"),
        SpriteLoader.load("green_gem.png"),
        SpriteLoader.load("heart.png"),
        SpriteLoader.load("lime.png"),
        SpriteLoader.load("peach.png"),
        SpriteLoader.load("pink_jelly.png"),
        SpriteLoader.load("strawberry.png")
    };

    private final BufferedImage sprite;

    public Candy(int x, int y) {
        this(x, y, ThreadLocalRandom.current().nextInt(SPRITES.length));
    }

    Candy(int x, int y, int spriteIndex) {
        this(x, y, SPRITES[spriteIndex]);
    }

    Candy(int x, int y, BufferedImage sprite) {
        super(x, y, 30, 3,
              new Color(255, 100, 160));
        this.sprite = sprite;
    }

    static int randomSpriteIndex(Random random) {
        return random.nextInt(SPRITES.length);
    }

    @Override
    public void draw(Graphics2D g) {
        int spriteOffset = (SPRITE_SIZE - size) / 2;
        g.drawImage(sprite, x - spriteOffset, y - spriteOffset,
                SPRITE_SIZE, SPRITE_SIZE, null);
    }

    @Override
    public void collect(ScoreManager score) {
        score.addScore(10);
    }
}
