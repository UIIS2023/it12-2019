package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import mvc.DrawingModel;

public class SaveDraw implements Save{
	
	private DrawingModel model;
	private FileOutputStream fileOutputStream;
	private FileInputStream fileInputStream;
	
	public SaveDraw(DrawingModel model) {
		this.model = model;
	}

	@Override
	public void save(String path) {
		
		File file = new File(path);
		
		try {
			fileOutputStream = new FileOutputStream(file + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
			out.writeObject(model.getShapes());
			out.flush();
			out.close();
			fileOutputStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
