package command;

import geometry.Shape;

public class SelectedShapeCmd implements Command {
	
	private Shape shape;
	private boolean selectedState;
	
	public SelectedShapeCmd(Shape shape, boolean selectedState) {
		this.shape = shape;
		this.selectedState = selectedState;
	}

	@Override
	public void execute() {
		shape.setSelected(selectedState);	
	}

	@Override
	public void unexecute() {
		if (shape.isSelected()) 
			shape.setSelected(false);
		else shape.setSelected(true);
	}

}
