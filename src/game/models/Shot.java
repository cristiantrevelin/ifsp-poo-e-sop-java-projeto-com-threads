package game.models;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Shot extends ScreenObject {
    
    private BufferedImage sprites;
    private BufferedImage[] animation;
    private int animationTick;
    private int animationIndex;
    private int animationSpeed;
    private boolean playing;
    
    public Shot () {
        
        animation = new BufferedImage[4];
        animationSpeed = 12;
        setWidth(77);
        setHeight(77);
        
        try {
            loadAnimation();
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public BufferedImage[] getAnimation () {
        return animation;
    }

    private void loadAnimation() throws FileNotFoundException {

        FileInputStream fis = new FileInputStream("res/shot_animation.png");
        
        try {
            sprites = ImageIO.read(fis);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        for (int i = 0; i < animation.length; i++) {
            animation[i] = sprites.getSubimage(i*31, 0, 31, 31);
        }
        
    }
    
    public void play () {
        
        if (!playing) {
            animationTick = 0;
            animationIndex = 0;
            playing = true;            
        }

    }
    
    public void update () {
        
        if (playing) {
            animationTick++;

            if (animationTick >= animationSpeed) {
                animationTick = 0;
                animationIndex++;

                if (animationIndex >= animation.length)
                    playing = false;
            }            
        }
        
    }

    @Override
    public void render(Graphics g) {
        
        if (playing) {
            g.drawImage(animation[animationIndex], getX(), getY(), 
                   getWidth(), getHeight(), null);
        }
        
    }
}
