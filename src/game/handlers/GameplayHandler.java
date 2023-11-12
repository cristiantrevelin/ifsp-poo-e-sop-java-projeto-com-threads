package game.handlers;

import game.GameManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameplayHandler {
    
    private int score;
    private int playerTries;
    private int time;
    private GameManager gameManager;
    private Thread gameplayThread;
    
    public GameplayHandler (GameManager gameManager) {
        
        this.gameManager = gameManager;
        score = 0;
        playerTries = 5;
        
    }
    
    public void addScore () {
        score++;
    }
    
    public void decreasePlayerTries () {
        playerTries--;
    }
    
    public void update () {
        
        if (playerTries <= 0) {
            gameManager.endGame();
        }

    }
    
    public void render (Graphics g) {

        Font font = new Font("SansSerif", Font.BOLD, 35);
        
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString("SCORE: " + String.valueOf(score), 800, 570);
        
        g.setColor(Color.BLUE);
        g.drawString("TRIES LEFT: " + String.valueOf(playerTries), 30, 570);
        
    }
}
