import javax.swing.JOptionPane;

public class PuzzleRunner {
	public static void main(String[] args) {
		Puzzle tester = new Puzzle();
		String input = JOptionPane.showInputDialog("Please enter your ABC puzzle hints.");
		JOptionPane.showMessageDialog(null, "Your ABC puzzle solution is: \n\n" + tester.conclude(input));
		System.exit(0);
	}
}
