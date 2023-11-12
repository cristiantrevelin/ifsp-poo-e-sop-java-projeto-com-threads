package game.models;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball extends ScreenObject implements Runnable {
    
    private Color color; 
    private boolean isTarget;
    private boolean blinking;
    private int blinkTick;
    private int blinkSpeed;
    private int blinkTime;
    private int blinkTimeCounter;
    private Random random;
    private Thread ballThread;
    
    public Ball () {

        setWidth(64);
        setHeight(64);

        blinkSpeed = 120;
        blinkTime = 1;
        
        random = new Random();
        startAction();
        
    }
    
    public boolean isTarget () {
        return isTarget;
    }
    
    private void startAction () {
        
        ballThread = new Thread(this);
        ballThread.start();
        
    }
    
    public void startBlink () {
        
        if (!blinking) {
            setX(random.nextInt(0, 1000 - getWidth()));
            setY(random.nextInt(0, 600 - getHeight()));
            
            isTarget = random.nextInt(1, 100) <= 20;

            blinkTick = 0;
            blinkTimeCounter = 0;
            blinking = true;       
        }
    }
    
    private void blink () {

        blinkTick++;
        
        if (blinkTick >= blinkSpeed) {
            
            blinkTimeCounter++;
            blinkTick = 0;
            
            if (blinkTimeCounter >= blinkTime)
                blinking = false;
            
        }
        
    }
    
    public void randomizeBlink () {
        
        synchronized (this) {
            try {
                wait(random.nextLong(1000, 5000)); 

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }            
        }

        startBlink();
    }
    
    public void update () {
        
        if (blinking)
            blink();
        
    }
    
    public boolean collided (int x, int y) {
        
        return ((x >= getX() && x <= getX() + getWidth()) &&
               (y >= getY() && y <= getY() + getHeight()) &&
                blinking);
        
    }

    @Override
    public void render(Graphics g) {
        
        if (blinking) {
            
            if (isTarget)
                color = Color.RED;
            else
                color = Color.DARK_GRAY;
            
            g.setColor(color);
            g.fillOval(getX(), getY(), getWidth(), getHeight());
        }
    }

    @Override
    public void run() {
        
        while (true) {
            randomizeBlink();
        }
    }
}
