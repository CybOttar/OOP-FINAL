import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class SpriteLoader {

    private static final String SPRITE_DIRECTORY =
            "/candy_sprites/sprites/square_128/";
    private static final String SPRITE_ROOT =
            "/candy_sprites/sprites/";

    private SpriteLoader() {
    }

    public static BufferedImage load(String filename) {
        return loadResource(SPRITE_DIRECTORY + filename, filename);
    }

    public static BufferedImage loadFromSpriteRoot(String filename) {
        return loadResource(SPRITE_ROOT + filename, filename);
    }

    private static BufferedImage loadResource(String resourcePath, String filename) {
        try {
            var resource = SpriteLoader.class.getResource(resourcePath);
            if (resource == null) {
                throw new IllegalStateException("Sprite not found: " + filename);
            }

            BufferedImage image = ImageIO.read(resource);
            if (image == null) {
                throw new IllegalStateException("Unsupported sprite image: " + filename);
            }
            return image;
        } catch (IOException e) {
            throw new IllegalStateException("Could not load sprite: " + filename, e);
        }
    }
}
