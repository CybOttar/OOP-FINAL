import javax.swing.JFrame;

public class CandyCatcher {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Candy Catcher");

        GamePanel gamePanel = new GamePanel();

        frame.add(gamePanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        gamePanel.requestFocusInWindow();
    }
}
