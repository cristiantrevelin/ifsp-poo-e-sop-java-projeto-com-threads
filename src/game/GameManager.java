package game;

import game.handlers.GameplayHandler;
import game.inputs.MouseInputs;
import game.models.Player;
import game.handlers.BallsHandler;
import game.models.Shot;
import game.window.GameFrame;
import game.window.GamePanel;
import java.awt.Graphics;

public class GameManager implements Runnable {
    
    private boolean gameRunning;
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private MouseInputs mouseInputs;
    private Player player;
    private Shot shot;
    private BallsHandler ballsHandler;
    private GameplayHandler gameplayHandler;
    private Thread gameThread;
    
    private final int FPS_SET = 120;
    
    public GameManager() {
        
        initComponents();
        startGameLoop();
       
    }
    
    private void initComponents () {
       
        player = new Player();
        shot = new Shot();
        
        mouseInputs = new MouseInputs(this);
        gamePanel = new GamePanel(this);
        gameFrame = new GameFrame(gamePanel);
        ballsHandler = new BallsHandler(this);
        gameplayHandler = new GameplayHandler(this);

    }
    
    public boolean gameRunning () {
        return gameRunning;
    }
    
    public Player getPlayer () {
        return player;
    }
    
    public Shot getShot () {
        return shot;
    }
    
    public MouseInputs getMouseInputs () {
        return mouseInputs;
    }
    
    public GamePanel getGamePanel () {
        return gamePanel;
    }
    
    public GameplayHandler getGameplayHandler () {
        return gameplayHandler;
    }
    
    public BallsHandler getBallsHandler () {
        return ballsHandler;
    }
    
    public void endGame () {
        System.exit(0);
    }
  
    private void startGameLoop () {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void updateGame () {
        ballsHandler.updateBalls();
        shot.update();
        gameplayHandler.update();
    }
    
    public void renderGame (Graphics g) {
        ballsHandler.renderBalls(g);
        shot.render(g);
        player.render(g);
        gameplayHandler.render(g);
    }

    @Override
    public void run() {
        
        gameRunning = true;
        
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrameTime = System.nanoTime();
        long currentFrameTime;
        
        long lastMilliSec = System.currentTimeMillis();
        long currentMilliSec;
        
        int fps = 0;
        
        // Loop Principal do Jogo:
        
        while (gameRunning) {
            
            // Garante que cada frame do jogo terá o tempo estipulado por 
            // <<timePerFrame>>, em nanossegundos, que equivale a <<FPS_SET>>
            // frames por segundo:
            
            currentFrameTime = System.nanoTime();
            if (currentFrameTime - lastFrameTime >= timePerFrame) {
                
                lastFrameTime = currentFrameTime;
                updateGame();
                gamePanel.repaint();
                
                fps++;
            }
            
            // Verifica quantos frames estão ocorrendo por segundo no jogo:
            
            currentMilliSec = System.currentTimeMillis();
            if (currentMilliSec - lastMilliSec >= 1000) {
                
                lastMilliSec = currentMilliSec;
                System.out.println("FPS: " + fps);
                fps = 0;
            }
        }
    }
}
