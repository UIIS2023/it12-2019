package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {
	
	private DrawingModel model;
	private ArrayList<Shape> selectedShapes;
	
	public RemoveShapeCmd(ArrayList<Shape> selectedShapes, DrawingModel model) {
		this.selectedShapes = new ArrayList<Shape>(selectedShapes);
		this.model = model;
	}

	@Override
	public void execute() {
		for(Shape shape : selectedShapes) {
			model.remove(shape);
			model.removeSelected(shape);
		}
		
	}

	@Override
	public void unexecute() {
		
		for(Shape shape : selectedShapes) {
			model.getShapes().add(shape);
			model.addSelected(shape);
		}
	}

}
