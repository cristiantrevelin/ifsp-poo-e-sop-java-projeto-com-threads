package game.handlers;

import game.models.Ball;
import game.GameManager;
import java.awt.Graphics;
import java.util.Random;

public class BallsHandler {
    
    private Ball[] balls;
    private int ballsNumber;
    private GameManager gameManager;
    private Random random;
    
    public BallsHandler (GameManager gameManager) {
        
        ballsNumber = 15;
        balls = new Ball[ballsNumber];
        
        random = new Random();
        
        this.gameManager = gameManager;
        generateBalls();
        
    }

    public Ball[] getBalls () {
        return balls;
    }

    private void generateBalls () {
        
        for (int i = 0; i < ballsNumber; i++)
            balls[i] = new Ball();
        
    }
    
    public void checkCollision (int x, int y) {
        
        boolean scoreAdded = false;
        
        for (Ball ball : balls) {
            if (ball.collided(x, y)) {
                
                if (ball.isTarget()) {
                    gameManager.getGameplayHandler().addScore();
                    scoreAdded = true;
                }
                else
                    gameManager.getGameplayHandler().decreasePlayerTries();
                
            }
        }
        
        if (!scoreAdded)
            gameManager.getGameplayHandler().decreasePlayerTries();
        
    }
    
    public void updateBalls () {
        
        for (Ball ball : balls) {
            ball.update();
        }
        
    }
    
    public void renderBalls (Graphics g) {
        
        for (Ball ball : balls) {
            ball.render(g);
        }
    }
}
