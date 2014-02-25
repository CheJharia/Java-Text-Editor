/**
 * @author Afiq Johari
 *
 * This class defines a subclass of TextArea(java.awt.TextArea)
 * and is used with EditorFrame.java
 * 
 * It contains the following methods:
 * void copyFile(String inputFile, String outputFile);
 * void readFile(String inputFile);
 * void writeFile(String outputFile);
 * void clear();
 */
import java.awt.Color;
import java.awt.Frame;
import java.awt.TextArea;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class EditorSpace extends TextArea {
	public EditorSpace() {
		// number of rows and number of columns
		super(40, 58);
		setForeground(Color.WHITE);
		setBackground(new Color(39, 40, 34));
	}

	public static void main(String[] args) throws IOException {
		EditorSpace editorSpace = new EditorSpace();
		Frame f = new Frame("EditorSpace test");
		f.add(editorSpace);
		f.pack();
		String inputFile = "myServer.java";
		String outputFile = "output.txt";

		editorSpace.readFile(inputFile);
		editorSpace.writeFile(outputFile);
		editorSpace.copyFile(inputFile, outputFile);
		editorSpace.clear();
		// 26, 188, 156 is turquoise
		editorSpace.changeColor(26, 188, 156);
		f.setVisible(true);

	}

	void changeColor(int r, int g, int b) {

		this.setBackground(new Color(r, g, b));

	}

	/**
	 * Make a copy of inputFile to outputFile
	 * 
	 * @param inputFile
	 * @param outputFile
	 */
	void copyFile(String inputFile, String outputFile) {
		this.readFile(inputFile);
		this.writeFile(outputFile);

	}

	/**
	 * clear TextArea content
	 */
	void clear() {
		this.setText("");
	}

	/**
	 * Write content from TextArea to outputFile
	 * 
	 * @param outputFile
	 */
	void writeFile(String outputFile) {

		try {
			PrintStream ps = new PrintStream(new BufferedOutputStream(
					new FileOutputStream(outputFile)));
			// write to buffer
			ps.println(this.getText());
			// flush out the buffer to outputFile
			ps.flush();
			ps.close();

		} catch (FileNotFoundException e) {
			System.out.println(outputFile + " not found.");
		}

	}

	/**
	 * Read content from inputFile to TextArea
	 * 
	 * @param inputFile
	 */
	void readFile(String inputFile) {
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					inputFile)));
			String line = br.readLine();
			this.setText("");
			while (line != null) {
				this.append(line + "\n");
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(inputFile + " not found.");
		} catch (IOException e) {
			System.out.println("Can't read " + inputFile);
		}

	}
}
