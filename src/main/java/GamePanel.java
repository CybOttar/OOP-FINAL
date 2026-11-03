package candycatcher;

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
