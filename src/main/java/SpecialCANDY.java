import java.awt.image.BufferedImage;

public class SpecialCANDY extends Candy {

    private static final BufferedImage WILD_SPRITE =
            SpriteLoader.load("wild_diamond.png");

    public SpecialCANDY(int x, int y) {
        super(x, y, WILD_SPRITE);
    }

    @Override
    public void collect(ScoreManager score) {
        score.addScore(30);
    }
}
