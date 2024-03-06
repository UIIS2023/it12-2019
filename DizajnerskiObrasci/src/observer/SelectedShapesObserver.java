package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class SelectedShapesObserver implements PropertyChangeListener {
	
	private DrawingFrame frame;
	private int size;
	private int sizeOfShapes;
	private int index;
	
	public SelectedShapesObserver(DrawingFrame frame) {
		this.frame = frame;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("Size")) {
			this.size = (int) evt.getNewValue();

		}
		if (evt.getPropertyName().equals("Index")) {
			this.index = (int) evt.getNewValue();
			this.sizeOfShapes = (int) evt.getOldValue();

		}
		
		handleButtons();
	}

	private void handleButtons() {
		
		if (size == 0) {
			frame.getBtnModify().setEnabled(false);
			frame.getBtnDelete().setEnabled(false);
			frame.getBtnBTF().setEnabled(false);
			frame.getBtnBTB().setEnabled(false);
			frame.getBtnTF().setEnabled(false);
			frame.getBtnTB().setEnabled(false);

		} else if (size == 1) {

			frame.getBtnModify().setEnabled(true);
			frame.getBtnDelete().setEnabled(true);
			
			frame.getBtnBTF().setEnabled(true);
			frame.getBtnBTB().setEnabled(true);
			frame.getBtnTF().setEnabled(true);
			frame.getBtnTB().setEnabled(true);

		} else if (size > 1) {
			frame.getBtnModify().setEnabled(false);
			frame.getBtnDelete().setEnabled(true);
			frame.getBtnBTF().setEnabled(false);
			frame.getBtnBTB().setEnabled(false);
			frame.getBtnTF().setEnabled(false);
			frame.getBtnTB().setEnabled(false);

		}
		
	}

}
