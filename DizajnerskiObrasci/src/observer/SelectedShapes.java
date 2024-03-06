package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SelectedShapes {
	
	private int size;
	private PropertyChangeSupport propertyChangeSupport;
	
	public SelectedShapes() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void addListener(PropertyChangeListener add) {
		propertyChangeSupport.addPropertyChangeListener(add);
	}
	
	public void removeListener(PropertyChangeListener remove) {
		propertyChangeSupport.removePropertyChangeListener(remove);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		propertyChangeSupport.firePropertyChange("Size", this.size, size);
		this.size = size;
	}

	public PropertyChangeSupport getPropertyChangeSupport() {
		return propertyChangeSupport;
	}

	public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
		this.propertyChangeSupport = propertyChangeSupport;
	}

}
