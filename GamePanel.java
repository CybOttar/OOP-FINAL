
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel
        implements ActionListener, KeyListener {

    private Player player;
    private ScoreManager scoreManager;
    private ArrayList<GameObject> objects;
    private Timer timer;
    private Random random;

    private String gameState = "MENU";

    private int timeLeft = 60;
    private int tickCount = 0;
    private int highScore = 0;

    public GamePanel() {
        setFocusable(true);
        addKeyListener(this);

        player = new Player();
        scoreManager = new ScoreManager();
        objects = new ArrayList<>();
        random = new Random();

        timer = new Timer(16, this);
        timer.start();
    }

    private void startGame() {
        player = new Player();
        scoreManager.reset();
        objects.clear();

        timeLeft = 60;
        tickCount = 0;

        gameState = "PLAYING";
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (gameState.equals("PLAYING")) {

            tickCount++;

            // Create falling objects
            if (tickCount % 35 == 0) {
                spawnObject();
            }

            // Move objects
            for (GameObject obj : objects) {
                obj.move();
            }

            // Check collisions
            checkCollisions();

            // Remove objects outside screen
            objects.removeIf(obj -> obj.y > 600);

            // Countdown timer
            if (tickCount % 63 == 0) {
                timeLeft--;
            }

            // Check game over
            if (timeLeft <= 0 ||
                scoreManager.getLives() <= 0) {

                gameState = "GAME_OVER";
                
               if (scoreManager.getScore() > highScore) {
                    highScore = scoreManager.getScore();
                }
            }
        }

        repaint();
    }

    private void spawnObject() {

        int x = random.nextInt(750);
        int type = random.nextInt(10);

        if (type < 2) {
            objects.add(new Bomb(x, -30));
        } else if (type < 4) {
            objects.add(new SpecialCANDY(x, -30));
        } else {
            objects.add(new Candy(x, -30));
        }
    }

    private void checkCollisions() {

        for (int i = objects.size() - 1; i >= 0; i--) {

            GameObject obj = objects.get(i);

            if (obj.getBounds().intersects(player.getBounds())) {

                if (obj instanceof Bomb) {
                    scoreManager.loseLife();
                }

                else if (obj instanceof Collectible) {
                    ((Collectible) obj).collect(scoreManager);
                }

                objects.remove(i);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        if (gameState.equals("MENU")) {
            drawMenu(g2);
        }

        else if (gameState.equals("PLAYING")) {
            drawGame(g2);
        }

        else if (gameState.equals("GAME_OVER")) {
            drawGameOver(g2);

 }
    }

    private void drawMenu(Graphics2D g) {

        g.setColor(new Color(255, 225, 240));
        g.fillRect(0, 0, 800, 600);

        g.setColor(new Color(220, 70, 140));
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("CANDY CATCHER", 205, 190);

        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.setColor(Color.WHITE);
        g.fillRoundRect(290, 270, 220, 60, 20, 20);

        g.setColor(new Color(220, 70, 140));
        g.drawString("START GAME", 320, 310);

        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("Press ENTER to Start", 305, 380);
        g.drawString("Use LEFT and RIGHT arrows to move", 240, 415);
        g.drawString("Catch candies and avoid bombs!", 260, 450);
        g.drawString("Close the window to exit.", 315, 490);
    }

    private void drawGame(Graphics2D g) {

        g.setColor(new Color(255, 240, 248));
        g.fillRect(0, 0, 800, 600);

        // Score and lives
        g.setColor(new Color(210, 60, 130));
        g.setFont(new Font("Arial", Font.BOLD, 22));

        g.drawString("Score: " +
            scoreManager.getScore(), 25, 35);

        g.drawString("Lives: " +
            scoreManager.getLives(), 330, 35);

        g.drawString("Time: " +
            timeLeft, 650, 35);

        // Draw falling objects
        for (GameObject obj : objects) {
            obj.draw(g);
        }

        // Draw basket
        player.draw(g);
    }

    private void drawGameOver(Graphics2D g) {

        g.setColor(new Color(255, 225, 240));
        g.fillRect(0, 0, 800, 600);

        g.setColor(new Color(220, 70, 140));
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("GAME OVER!", 270, 200);

        g.setFont(new Font("Arial", Font.BOLD, 26));
        g.drawString("Final Score: " +
            scoreManager.getScore(), 290, 280);
     g.drawString("High Score: " +
            highScore, 290, 330);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Press R to Play Again", 300, 400);
        g.drawString("Press ESC for Main Menu", 290, 440);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (gameState.equals("MENU")) {

            if (key == KeyEvent.VK_ENTER) {
                startGame();
            }
        }

        else if (gameState.equals("PLAYING")) {

            if (key == KeyEvent.VK_LEFT) {
                player.moveLeft();
            }

            if (key == KeyEvent.VK_RIGHT) {
                player.moveRight();
            }

            if (key == KeyEvent.VK_ESCAPE) {
                gameState = "MENU";
            }
        }

        else if (gameState.equals("GAME_OVER")) {

            if (key == KeyEvent.VK_R) {
                startGame();
            }

            if (key == KeyEvent.VK_ESCAPE) {
                gameState = "MENU";
            }
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
