/**
 * @author Afiq Johari
 *
 * This class defines a subclass of Frame.
 * It serves as the main GUI of our text editor.
 */

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EditorFrame extends Frame implements ActionListener {
	MenuBar menuBar = new MenuBar();
	Menu file = new Menu("File");
	MenuItem open = new MenuItem("Open\tCtrl+O",
			new MenuShortcut(KeyEvent.VK_O));
	MenuItem save = new MenuItem("Save\tCtrl+S",
			new MenuShortcut(KeyEvent.VK_S));
	MenuItem save_as = new MenuItem("Save as");
	MenuItem copy = new MenuItem("Copy");
	MenuItem clearScreen = new MenuItem("Clear screen\tCtrl+L",
			new MenuShortcut(KeyEvent.VK_L));
	EditorSpace editorSpace = new EditorSpace();

	FileDialog openDialog = new FileDialog(this, "Open file", 0); // 0 for load
	FileDialog saveDialog = new FileDialog(this, "Save file as", 1); // 1 for
																		// save
	FileDialog copyDialog = new FileDialog(this, "Copy to", 1);
	String currentFile = null;

	public EditorFrame() {
		super("Untitled");
		add(editorSpace, BorderLayout.NORTH);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("The window will be closed");
				System.exit(0);
			}
		});
		open.addActionListener(this);
		save.addActionListener(this);
		save_as.addActionListener(this);
		copy.addActionListener(this);
		clearScreen.addActionListener(this);
		file.add(open);
		file.add(save);
		file.add(save_as);
		file.add(copy);
		file.add(clearScreen);
		menuBar.add(file);
		setMenuBar(menuBar);
	}

	public static void main(String[] args) {
		EditorFrame f = new EditorFrame();
		f.setSize(600, 580);
		;
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(open)) {
			openDialog.setVisible(true);
			currentFile = openDialog.getFile();
			String filePath = openDialog.getDirectory();
			if (currentFile != null && filePath != null) {
				this.editorSpace.readFile(filePath + currentFile);
				this.setTitle(filePath + currentFile);
				System.out.println("Opening " + filePath + currentFile);
			}

		}
		if (e.getSource().equals(save)) {
			String filepath = this.getTitle();
			this.editorSpace.writeFile(filepath);
			System.out.println("Saved " + filepath);
		}
		if (e.getSource().equals(save_as)) {
			saveDialog.setVisible(true);
			String filepath = saveDialog.getDirectory();
			String newfilename = saveDialog.getFile();
			this.editorSpace.writeFile(filepath + newfilename);
			System.out.println("Saved " + filepath + newfilename);
		}
		if (e.getSource().equals(copy)) {
			copyDialog.setVisible(true);
			String filepath = copyDialog.getDirectory();
			String newfilename = copyDialog.getFile();
			System.out.println("Copying " + filepath + newfilename);
			this.editorSpace.copyFile(this.getTitle(), filepath + newfilename);
			System.out.println(filepath + newfilename);
		}
		if (e.getSource().equals(clearScreen)) {
			this.editorSpace.clear();
		}

	}
}
