package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command{
	
	private DrawingModel model;
	private Shape shape;
	private int index;
	
	public BringToBackCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		index =  model.getIndexOf(shape);
		model.removeAtIndex(index);
		model.addToIndex(0, shape);	
	}

	@Override
	public void unexecute() {
		model.removeAtIndex(0);
		model.addToIndex(index, shape);	
	}

}
