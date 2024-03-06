package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command{
	
	private DrawingModel model;
	private Shape shape;
	private int size;
	private int index;
	
	public BringToFrontCmd(DrawingModel model, Shape shape, int size) {
		this.model = model;
		this.shape = shape;
		this.size = size;
	}

	@Override
	public void execute() {
		index =  model.getIndexOf(shape);
		model.removeAtIndex(index);
		model.addToIndex(size, shape);
		
	}

	@Override
	public void unexecute() {
		model.removeAtIndex(size);
		model.addToIndex(index, shape);	
	}

}
