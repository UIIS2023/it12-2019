package mvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import geometry.Shape;

public class DrawingModel {
	
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	private PropertyChangeSupport propertyChangeSupport;
	
	public DrawingModel() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void addListener(PropertyChangeListener add) {
		propertyChangeSupport.addPropertyChangeListener(add);
	}
	
	public void removeListener(PropertyChangeListener remove) {
		propertyChangeSupport.removePropertyChangeListener(remove);
	}
	
	public void add(Shape s) {
		shapes.add(s);
		propertyChangeSupport.firePropertyChange("Index", getShapes().size(), getShapes().indexOf(s));
	}
	
	public void addSelected(Shape s) {
		propertyChangeSupport.firePropertyChange("Size", this.selectedShapes.size(), this.selectedShapes.size() + 1);
		selectedShapes.add(s);
		propertyChangeSupport.firePropertyChange("Index", getShapes().size(), getShapes().indexOf(s));
		
	}
	
	public void addToIndex(int index, Shape shape) {
		shapes.add(index, shape);
		propertyChangeSupport.firePropertyChange("Index", getShapes().size(), index);
	}

	public void remove(Shape s) {
		propertyChangeSupport.firePropertyChange("Index", getShapes().size() - 1, getShapes().indexOf(s));
		shapes.remove(s);
	}
	
	public void removeSelected(Shape s) {
		propertyChangeSupport.firePropertyChange("Size", this.selectedShapes.size(), this.selectedShapes.size() - 1);
		propertyChangeSupport.firePropertyChange("Index", getShapes().size() - 1, getShapes().indexOf(s));
		selectedShapes.remove(s);
	}
	
	public void removeAtIndex(int index) {
		propertyChangeSupport.firePropertyChange("Index", getShapes().size() - 1, index);
		shapes.remove(index);
	}
	
	public Shape getByIndex(int index) {
		return shapes.get(index);
	}
	
	public int getIndexOf(Shape shape) {
		return shapes.indexOf(shape);
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	
	public void clearAll() {
		shapes.clear();
	}
	
	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapes;
	}

	public void setSelectedShapes(ArrayList<Shape> selectedShapes) {
		this.selectedShapes = selectedShapes;
	}
	
}
