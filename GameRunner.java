//ACSL
//Sophia Shen

import java.util.*;
import javax.swing.JOptionPane;

public class GameRunner 
{

  public static void main(String [] args)
  { 
    Game put = new Game();
    String input;

      input = JOptionPane.showInputDialog("Enter the code");
      put.separate(input);
      
      System.out.println (put.conclude());
      System.out.println ("Github sucks");
      System.out.println ("Github sucks");
    
    System.exit(0);
  }
} 