package game.window;

import javax.swing.JFrame;

public class GameFrame {
    
    private JFrame jframe;
    
    public GameFrame(GamePanel gamePanel) {
        
        jframe = new JFrame();
        
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.pack();
        jframe.setResizable(false);
        
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        
    }
    
    public JFrame getjFrame() {
        return jframe;
    }
    
}
