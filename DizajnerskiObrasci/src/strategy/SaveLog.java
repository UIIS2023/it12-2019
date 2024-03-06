package strategy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import mvc.DrawingFrame;

public class SaveLog implements Save{
	
	private DrawingFrame frame;
	
	public SaveLog(DrawingFrame frame) {
		this.frame = frame;
	}

	@Override
	public void save(String path) {
		
		File file = new File(path);

		try {

			FileWriter fw = new FileWriter(file+".txt");
			fw.write(frame.getTextArea().getText());
			fw.close();
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "Something went wrong!", "Message", JOptionPane.WARNING_MESSAGE);

		}
		
	}

	

}
