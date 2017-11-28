//ACSL
//Sophia Shen

import java.util.*;
import javax.swing.JOptionPane;

public class Game
{
  String replaced;
  String result = "";
  int sign1=0;
  int sign2=0;
  int sign3=0;
  int sign4=0;
  int posnum;
  String[] filling = new String[16];
  int[] position = new int[16];
  int[] srow = new int[4];
  int[] scol = new int[4];
  int[] filled = new int[16];
  int[] inipos = new int[16];
  int[] testpos = new int[16];
  int[] abcrow = new int[16];
  int[] abccol = new int[16];
  int[][] board = {{0,0,0,0},
                   {0,0,0,0},
                   {0,0,0,0},
                   {0,0,0,0}};
                   
  
  public void separate(String unsep)
  {
    replaced = unsep.replaceAll(",", "  ");
    Scanner scan = new Scanner(replaced); 
    sign1 = scan.nextInt();
    sign2 = scan.nextInt();
    sign3 = scan.nextInt();
    sign4 = scan.nextInt();  
    posnum = scan.nextInt();
    for(int i=0; i<posnum; i++)
    {  
      filling[i] = scan.next();
      position[i] = scan.nextInt(); 
    }
  }
  
  public void signRowCol()
  {
    srow[0] = changeRow(sign1);
    srow[1] = changeRow(sign2);
    srow[2] = changeRow(sign3);
    srow[3] = changeRow(sign4);
    scol[0] = changeCol(sign1);
    scol[1] = changeCol(sign2);
    scol[2] = changeCol(sign3);
    scol[3] = changeCol(sign4);
  }
  public void fillSign()
  {
    signRowCol();
    for(int i=0; i<4; i++)
    {
      board[(srow[i])][(scol[i])] = 4;
    }
  } 
  
  public void ingToed()
  {
    for(int i=0; i<posnum; i++)
    {
      filled[i] = change(filling[i]);
    }
  }
  public int change(String abc)
  {
    if(abc.equals("A"))
      return 1;
    else if(abc.equals("B"))
      return 2;
    else
      return 3; 
  }
  public void transfer()
  {
    for(int i=0; i<posnum; i++)
    {
      inipos[i] = position[i];
      position[i] = changePosition(position[i]);
      testpos[i] = position[i];
    }
  }
  public void testPosition()
  {
    transfer();
    for(int i=0; i<posnum; i++)
    {
      if(testpos[i]==sign1||testpos[i]==sign2||testpos[i]==sign3||testpos[i]==sign4)
      position[i] =  changeSpecialPos(inipos[i]);
    }
  }
  public void abcRowCol()
  { 
    testPosition();
    for(int i=0; i<posnum; i++)
    {
      abcrow[i] = changeRow(position[i]);
      abccol[i] = changeCol(position[i]);
    }
  } 
  public void fillabc()
  {
    ingToed();
    abcRowCol();
    for(int i=0; i<posnum; i++)
    {
      board[(abcrow[i])][(abccol[i])] = filled[i];
    }
  }
  public int changePosition(int unchan)
  {
    if (unchan == 2||unchan == 7)
    return 8;
    else if (unchan == 5||unchan == 12)
    return 11;
    else if (unchan == 25||unchan == 32)
    return 26;
    else if (unchan == 35||unchan == 30)
    return 29;
    else if (unchan == 3)
    return 9;
    else if (unchan == 4)
    return 10;
    else if (unchan == 13)
    return 14;
    else if (unchan == 19)
    return 20;
    else if (unchan == 18)
    return 17;
    else if (unchan == 24)
    return 23;
    else if (unchan == 33)
    return 27;
    else
    return 28;
  }
  public int changeSpecialPos(int midun)
  {
    if (midun == 3||midun == 13)
    return 15;
    else if (midun == 4||midun == 18)
    return 16;
    else if (midun == 19||midun == 33)
    return 21;
    else if (midun == 24||midun == 34)
    return 22;
    else if (midun == 2)
    return 14;
    else if (midun == 5)
    return 17;
    else if (midun == 7)
    return 9;
    else if (midun == 12)
    return 10;
    else if (midun == 25)
    return 27;
    else if (midun == 30)
    return 28;
    else if (midun == 32)
    return 20;
    else
    return 23;  
  }
  
  public int changeRow(int s)
  {
    if(s==8||s==9||s==10||s==11)
    return 0;
    else if(s==14||s==15||s==16||s==17)
    return 1;
    else if(s==20||s==21||s==22||s==23)
    return 2;
    else
    return 3;  
  }
  public int changeCol(int s)
  {
    if(s==8||s==14||s==20||s==26)
    return 0;
    else if(s==9||s==15||s==21||s==27)
    return 1;
    else if(s==10||s==16||s==22||s==28)
    return 2;
    else
    return 3;
  }
  
  public boolean solve(int row, int col)
  {
    if(row==4)
      return true;
    if(board[row][col]!=0)
    {
      if(solve(col==3?(row+1):row, (col+1)%4))
        return true;
    }
    else 
    {
      Integer[] randoms = generateRandomNumbers();
      for(int i=0; i<4; i++)
      {
        if(!containedInRowCol(row, col, randoms[i]))
        {
          board[row][col] = randoms[i];
          if(solve(col==3?(row+1):row, (col+1)%4))
            return true;
          else
            board[row][col]=0;
        }
      }
    }
    return false;
  }
  public boolean containedInRowCol(int row, int col, int value)
  {
    for(int i=0; i<4; i++)
    {
      if(i!=col)
        if(board[row][i]==value)
         return true;
      if(i!=row)
        if(board[i][col]==value)
         return true;
    }
    return false;
  }
  public Integer[] generateRandomNumbers()
  {
    ArrayList<Integer> randoms = new ArrayList<Integer>();
    for(int i=0; i<4; i++)
      randoms.add(i+1);
    Collections.shuffle(randoms);
    return randoms.toArray(new Integer[4]);
  }

  
  public String conclude()
  {
    fillSign();
    fillabc();
    for(int a=0; a<4; a++)
    {
      for(int b=0; b<4; b++)
      {
        solve(a, b);
      }
    }
    for(int i=0; i<4; i++)
    { 
      for(int j=0; j<4; j++)
      result += board[i][j];
    }
    result = result.replaceAll("1", "A");
    result = result.replaceAll("2", "B");
    result = result.replaceAll("3", "C");
    result = result.replaceAll("4", "");
    return result;
  }
}