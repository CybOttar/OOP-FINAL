import javax.swing.JFrame;

public class Game {

    private static final String TITLE = "Candy Catcher";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private final JFrame frame;
    private final GamePanel gamePanel;

    public Game() {
        frame = new JFrame(TITLE);
        gamePanel = new GamePanel();
        setupWindow();
    }

    private void setupWindow() {
        frame.add(gamePanel);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public void start() {
        frame.setVisible(true);
        gamePanel.requestFocusInWindow();
    }
}
