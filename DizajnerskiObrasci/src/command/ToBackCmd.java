package command;

import geometry.Shape;
import mvc.DrawingModel;

public class ToBackCmd implements Command{
	
	private DrawingModel model;
	private Shape shape;
	private int index;
	
	public ToBackCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}


	@Override
	public void execute() {
		index =  model.getIndexOf(shape);
		model.removeAtIndex(index);
		model.addToIndex(index - 1, shape);	
	}

	@Override
	public void unexecute() {
		model.removeAtIndex(index - 1);
		model.addToIndex(index, shape);	
	}

}
