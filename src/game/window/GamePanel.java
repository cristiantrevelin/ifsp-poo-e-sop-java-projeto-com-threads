package game.window;

import game.GameManager;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    
    private GameManager gameManager;
    private int width;
    private int height;
    
    public GamePanel (GameManager gameManager) {
        
        this.gameManager = gameManager;
        width = 1000;
        height = 600;
        
        addMouseListener(gameManager.getMouseInputs());
        addMouseMotionListener(gameManager.getMouseInputs());

        setPanelSize(width, height);
        
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public GameManager getGameManager () {
        return gameManager;
    }
    
    private void setPanelSize (int width, int height) {
        
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        
    }
    
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        gameManager.renderGame(g);
    }
}
