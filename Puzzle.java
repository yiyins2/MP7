import java.util.*;

public class Puzzle {
	static int[][] board = {{0,0,0,0},
            				   {0,0,0,0},
            				   {0,0,0,0},
            				   {0,0,0,0}};
	
	public static void seperate(String hint) {
		String replaced = hint.replaceAll(","," ");
		int[] blockPos = new int[4];
		int[] abc = new int[4];
		int[] abcPos = new int[4];
		Scanner scan = new Scanner(replaced);
		for (int i = 0; i < 4; i++) {
			blockPos[i] = scan.nextInt();
		}
		int abcHintSum = scan.nextInt();
		for (int j = 0; j < abcHintSum; j++) {
			abc[j] = changeABC(scan.next()); 
			abcPos[j] = scan.nextInt();
		}
		int[] abcPosChanged = new int[4];
		//Change ABC position
		for (int b = 0; b < abcHintSum; b++) {
			abcPosChanged[b] = change(abcPos[b]);
		}
		//Change ABC position special cases 
		for (int c = 0; c < abcHintSum; c++) {
			if (abcPosChanged[c] == blockPos[0] || abcPosChanged[c] == blockPos[1] || abcPosChanged[c] == blockPos[2] || abcPosChanged[c] == blockPos[3])
				abcPosChanged[c] = changeSpecial(abcPos[c]);
		}
		//Fill the board with changed ABC
		for (int d = 0; d < abcHintSum; d++) {
			board[changeRow(abcPosChanged[d])][changeCol(abcPosChanged[d])] = abc[d];
		}
		//Fill the board with changed blocks 
		for (int a = 0; a < 4; a++) {
			board[changeRow(blockPos[a])][changeCol(blockPos[a])] = 4; 
		}
	}
	
	public static int changeABC(String abc) {
		if (abc.equals("A"))
			return 1; 
		else if(abc.equals("B")) 
			return 2; 
		else 
			return 3; 
	}
	public static int change(int abcPos) {
		if (abcPos == 2||abcPos == 7)
		    return 8;
		else if (abcPos == 5||abcPos == 12)
		    return 11;
		else if (abcPos == 25||abcPos == 32)
		    return 26;
		else if (abcPos == 35||abcPos == 30)
		    return 29;
		else if (abcPos == 3)
		    return 9;
		else if (abcPos == 4)
		    return 10;
		else if (abcPos == 13)
		    return 14;
		else if (abcPos == 19)
		    return 20;
		else if (abcPos == 18)
		    return 17;
		else if (abcPos == 24)
		    return 23;
		else if (abcPos == 33)
		    return 27;
		else
		    return 28;
	}
	public static int changeSpecial(int abcPosChanged) {
		if (abcPosChanged == 3||abcPosChanged == 13)
			return 15;
	    else if (abcPosChanged == 4||abcPosChanged == 18)
	        return 16;
	    else if (abcPosChanged == 19||abcPosChanged == 33)
	        return 21;
	    else if (abcPosChanged == 24||abcPosChanged == 34)
	        return 22;
	    else if (abcPosChanged == 2)
	        return 14;
	    else if (abcPosChanged == 5)
	        return 17;
	    else if (abcPosChanged == 7)
	        return 9;
	    else if (abcPosChanged == 12)
	        return 10;
	    else if (abcPosChanged == 25)
	        return 27;
	    else if (abcPosChanged == 30)
	        return 28;
	    else if (abcPosChanged == 32)
	        return 20;
	    else
	        return 23;  
	  }
	
	public static int changeRow(int blockPos) {
		if (blockPos == 8 || blockPos == 9 || blockPos == 10 || blockPos == 11) 
			return 0;
		else if (blockPos == 14 || blockPos == 15 || blockPos == 16 || blockPos == 17)
			return 1; 
		else if (blockPos == 20 || blockPos == 21 || blockPos == 22 || blockPos == 23) 
			return 2; 
		else 
			return 3;
	}
	public static int changeCol(int blockPos) {
		if (blockPos == 8 || blockPos == 14 || blockPos == 20 || blockPos == 26) 
			return 0;
		else if (blockPos == 9 || blockPos == 15 || blockPos == 21 || blockPos == 27)
			return 1; 
		else if (blockPos == 10 || blockPos == 16 || blockPos == 22 || blockPos == 28) 
			return 2; 
		else 
			return 3;
	}
	
	public static boolean solve(int row, int col) {
		if (row == 4) 
			return true; 
		if (board[row][col] != 0) {
			if(solve(col == 3 ? (row + 1) : row, (col + 1) % 4))
				return true; 
		}
		else {
			Integer[] random = generateRandom(); 
			for (int i = 0; i < 4; i++) {
				if (!containedInRowCol(row, col, random[i])) {
					board[row][col] = random[i];
					if(solve(col == 3 ? (row + 1) : row, (col + 1) % 4))
						return true;
					else 
						board[row][col] = 0; 
				}
			}
		}
		return false; 
	}
	public static Integer[] generateRandom() {
		ArrayList<Integer> random = new ArrayList<Integer>(); 
		for (int i = 0; i < 4; i++) {
			random.add(i + 1);
		}
		Collections.shuffle(random);
		return random.toArray(new Integer[4]);
	}
	public static boolean containedInRowCol(int row, int col, int test) {
		for (int i = 0; i < 4; i++) {
			if (i != col) {
				if (board[row][i] == test)
					return true;
			}
			if (i != row) {
				if (board[i][col] == test) 
					return true; 
			}
		}
		return false; 
	}
	
	public static String conclude(String input) {
		seperate(input);
		String result = "";
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				solve(a, b);
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				result = result + board[i][j];
			}
			result += "\n";
		}
		result = result.replaceAll("1", "A");
	    result = result.replaceAll("2", "B");
	    result = result.replaceAll("3", "C");
	    result = result.replaceAll("4", "@");
		return result; 
	}
}