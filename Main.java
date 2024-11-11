import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;



public class Main {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame(" Tic-Tac-Toe");
        frame.getContentPane();
        
        tictac ttt = new  tictac();
        frame.add(ttt);
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        
        System.out.println("Frame and panel setup complete.");
        
    }
    
}
