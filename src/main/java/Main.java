import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        // Start the game on the Swing event thread
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            game.start();
        });
    }
}
