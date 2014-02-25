
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
		f.setVisible(true);

	}

	void copyFile(String inputFile, String outputFile) {
		this.readFile(inputFile);
		this.writeFile(outputFile);
		
	}

	void clear() {
		this.setText("");
	}

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
