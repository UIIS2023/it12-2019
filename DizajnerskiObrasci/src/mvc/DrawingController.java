package mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import adapter.HexagonAdapter;
import command.AddShapeCmd;
import command.BringToBackCmd;
import command.BringToFrontCmd;
import command.Command;
import command.RemoveShapeCmd;
import command.SelectedShapeCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgRectangle;
import drawing.DlgPoint;
import drawing.DlgLine;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Shape;
import observer.*;
import strategy.SaveDraw;
import strategy.SaveLog;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	
	private Shape selectedShape;
	private Point startPoint;
	
	private Stack<Command> listActions;
	private Stack<Command> undoActions;
	
	private Color outlineColor = Color.BLACK;
	private Color innerColor = Color.WHITE;
	private Color choosenOutlineColor;
	private Color choosenInnerColor;
	
	private SelectedShapesObserver observer;
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		listActions = new Stack<Command>();
		undoActions = new Stack<Command>();
		observer = new SelectedShapesObserver(frame);
		model.addListener(observer);
	}

	public void mouseClicked(MouseEvent mouse) {
		//Kopirana metoda thisMouseClicked iz PnlDrawing klase
		
		Shape newShape = null;
		Point click = new Point(mouse.getX(), mouse.getY());
		
		if (frame.getTglbtnSelect().isSelected()) {
			selectedShape = null;
			Iterator<Shape> iterator = model.getShapes().iterator();
			
			while (iterator.hasNext()) {
				Shape shape = iterator.next();
				if (shape.contains(click.getX(), click.getY()))
					selectedShape = shape;
			}
			if (selectedShape != null) {
				
				if(!selectedShape.isSelected()) {
					selectedShape.setSelected(true);
					model.addSelected(selectedShape);
					frame.getTextArea().append("Select: " +selectedShape.toString() +" \n");
					Command command = new SelectedShapeCmd(selectedShape, true);
					command.execute();
					listActions.push(command);
					
				} else {
					selectedShape.setSelected(false);
					model.removeSelected(selectedShape);
					frame.getTextArea().append("Deselect: " +selectedShape.toString() + " \n");
					Command command = new SelectedShapeCmd(selectedShape, false);
					command.execute();
					listActions.push(command);
					frame.getTglbtnSelect().setSelected(false);	
				}
				
			}
			
		} else if (frame.getTglbtnPoint().isSelected()) {
			newShape = new Point(click.getX(), click.getY(), false, outlineColor);
		} else if (frame.getTglbtnLine().isSelected()) {
			if (startPoint == null)
				startPoint = click;
			else {
				newShape = new Line(startPoint, new Point(mouse.getX(), mouse.getY(), false, outlineColor));
				startPoint = null;
			}
		} else if (frame.getTglbtnRectangle().isSelected()) {
			DlgRectangle dlg = new DlgRectangle();
			dlg.setModal(true);
			dlg.getTxtX().setText("" + Integer.toString(mouse.getX()));
			dlg.getTxtX().setEditable(false);
			dlg.getTxtY().setText("" + Integer.toString(mouse.getY()));
			dlg.getTxtY().setEditable(false);
			
			if (innerColor != null)
				dlg.getBtnInnerColor().setBackground(innerColor);
			if (outlineColor != null)
				dlg.getBtnOutLineColor().setBackground(outlineColor);
			dlg.setVisible(true);
			if (!dlg.isOK())
				return;
			try {
				newShape = dlg.getRectangle();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Wrong data type", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else if (frame.getTglbtnCircle().isSelected()) {
			DlgCircle dlg = new DlgCircle();
			dlg.getTxtX().setText("" + Integer.toString(click.getX()));
			dlg.getTxtX().setEditable(false);
			dlg.getTxtY().setText("" + Integer.toString(click.getY()));
			dlg.getTxtY().setEditable(false);
			if (innerColor != null)
				dlg.getBtnInnerColor().setBackground(innerColor);
			if (outlineColor != null)
				dlg.getBtnOutLineColor().setBackground(outlineColor);
			dlg.setVisible(true);
			if (dlg.isOK())
				newShape = dlg.getCircle();
		} else if (frame.getTglbtnDonut().isSelected()) {
			DlgDonut dlg = new DlgDonut();
			dlg.setModal(true);
			dlg.getTxtX().setText("" + Integer.toString(click.getX()));
			dlg.getTxtX().setEditable(false);
			dlg.getTxtY().setText("" + Integer.toString(click.getY()));
			dlg.getTxtY().setEditable(false);
			if (innerColor != null)
				dlg.getBtnInnerColor().setBackground(innerColor);
			if (outlineColor != null)
				dlg.getBtnOutLineColor().setBackground(outlineColor);
			dlg.setVisible(true);
			if (dlg.isOK())
				newShape = dlg.getDonut();
		} else if (frame.getTglbtnHexagon().isSelected()) {
			DlgHexagon dlg = new DlgHexagon();
			dlg.setModal(true);
			dlg.getTxtX().setText("" + Integer.toString(click.getX()));
			dlg.getTxtX().setEditable(false);
			dlg.getTxtY().setText("" + Integer.toString(click.getY()));
			dlg.getTxtY().setEditable(false);
			if (innerColor != null)
				dlg.getBtnInnerColor().setBackground(innerColor);
			if (outlineColor != null)
				dlg.getBtnOutLineColor().setBackground(outlineColor);
			dlg.setVisible(true);
			if (dlg.isOK())
				newShape = dlg.getHexagon();
		}

		if (newShape != null) {
			frame.getTextArea().append("Add: " + (newShape.toString() + "\n"));
			Command command = new AddShapeCmd((Shape) newShape, model);
			command.execute();
			listActions.push(command);
		}
		
		if(!getListActions().isEmpty()) {
			frame.getBtnUndo().setEnabled(true);
		}
			
		clearRedo();
		frame.getView().repaint();
		
	}

	//Kopirano iz metode modify FrmDrawing klase
	public void modify() {
		Shape selectedShape = this.getSelectedShape();
		Command command;

		if (selectedShape != null) {
			if (selectedShape instanceof Point) {
				Point p = (Point) selectedShape;
				DlgPoint dlg = new DlgPoint();

				dlg.getTxtX().setText("" + Integer.toString(p.getX()));
				dlg.getTxtY().setText("" + Integer.toString(p.getY()));
				dlg.getBtnColor().setBackground(p.getColor());
				dlg.setVisible(true);

				if (dlg.isOK()) {
					frame.getTextArea().append("Modify: " + ((Point) dlg.getP()).toString() + "\n");
					command = new UpdatePointCmd((Point) selectedShape, dlg.getP());
					command.execute();
					listActions.push(command);
				}
			} else if (selectedShape instanceof Line) {
				Line line = (Line) selectedShape;
				DlgLine dlg = new DlgLine();

				dlg.getTxtStartPointX().setText("" + Integer.toString(line.getStartPoint().getX()));
				dlg.getTxtStartPointY().setText("" + Integer.toString(line.getStartPoint().getY()));
				dlg.getTxtEndPointX().setText("" + Integer.toString(line.getEndPoint().getX()));
				dlg.getTxtEndPointY().setText("" + Integer.toString(line.getEndPoint().getY()));
				dlg.getBtnOutLineColor().setBackground(line.getColor());
				dlg.setVisible(true);

				if (dlg.isOK()) {
					frame.getTextArea().append("Modify: " + ((Line) dlg.getLine()).toString() + "\n");
					command = new UpdateLineCmd((Line) selectedShape, dlg.getLine());
					command.execute();
					listActions.push(command);
				}
				
			} else if (selectedShape instanceof Rectangle) {
				Rectangle rect = (Rectangle) selectedShape;
				DlgRectangle dlg = new DlgRectangle();

				dlg.getTxtX().setText("" + Integer.toString(rect.getUpperLeftPoint().getX()));
				dlg.getTxtY().setText("" + Integer.toString(rect.getUpperLeftPoint().getY()));
				dlg.getTxtHeight().setText("" + Integer.toString(rect.getHeight()));
				dlg.getTxtWidth().setText("" + Integer.toString(rect.getWidth()));
				dlg.getBtnInnerColor().setBackground(rect.getInnerColor());
				dlg.getBtnOutLineColor().setBackground(rect.getColor());
				dlg.setModal(true);
				dlg.setVisible(true);

				if (dlg.isOK()) {
					frame.getTextArea().append("Modify: " + ((Rectangle) dlg.getRectangle()).toString() + "\n");
					command = new UpdateRectangleCmd((Rectangle) selectedShape, dlg.getRectangle());
					command.execute();
					listActions.push(command);
				}

			} else if (selectedShape instanceof Donut) {
				Donut donut = (Donut) selectedShape;
				DlgDonut dlg = new DlgDonut();

				dlg.getTxtX().setText("" + Integer.toString(donut.getCenter().getX()));
				dlg.getTxtY().setText("" + Integer.toString(donut.getCenter().getY()));
				dlg.getTxtRadius().setText("" + Integer.toString(donut.getRadius()));
				dlg.getTxtInnerRadius().setText("" + Integer.toString(donut.getInnerRadius()));
				dlg.getBtnInnerColor().setBackground(donut.getInnerColor());
				dlg.getBtnOutLineColor().setBackground(donut.getColor());
				dlg.setModal(true);
				dlg.setVisible(true);

				if (dlg.isOK()) {
					frame.getTextArea().append("Modify: " + ((Donut) dlg.getDonut()).toString() + "\n");
					command = new UpdateDonutCmd((Donut) selectedShape, dlg.getDonut());
					command.execute();
					listActions.push(command);
				}
			} else if (selectedShape instanceof Circle && (selectedShape instanceof Donut) == false) {
				Circle circle = (Circle) selectedShape;
				DlgCircle dlg = new DlgCircle();

				dlg.getTxtX().setText("" + Integer.toString(circle.getCenter().getX()));
				dlg.getTxtY().setText("" + Integer.toString(circle.getCenter().getY()));
				dlg.getTxtRadius().setText("" + Integer.toString(circle.getRadius()));
				dlg.getBtnInnerColor().setBackground(circle.getInnerColor());
				dlg.getBtnOutLineColor().setBackground(circle.getColor());
				dlg.setModal(true);
				dlg.setVisible(true);

				if (dlg.isOK()) {
					frame.getTextArea().append("Modify: " + ((Circle) dlg.getCircle()).toString() + "\n");
					command = new UpdateCircleCmd((Circle) selectedShape, dlg.getCircle());
					command.execute();
					listActions.push(command);
				}
			} else if (selectedShape instanceof HexagonAdapter) {
				HexagonAdapter hexagon = (HexagonAdapter) selectedShape;
				DlgHexagon dlg = new DlgHexagon();
				
				dlg.getTxtX().setText("" + Integer.toString(hexagon.getHexagon().getX()));
				dlg.getTxtY().setText("" + Integer.toString(hexagon.getHexagon().getY()));
				dlg.getTxtRadius().setText("" + Integer.toString(hexagon.getHexagon().getR()));
				dlg.getBtnInnerColor().setBackground(hexagon.getHexagon().getAreaColor());
				dlg.getBtnOutLineColor().setBackground(hexagon.getHexagon().getBorderColor());
				dlg.setModal(true);
				dlg.setVisible(true);

				if (dlg.isOK()) {
					frame.getTextArea().append("Modify: " + ((HexagonAdapter) dlg.getHexagon()).toString() + "\n");
					command = new UpdateHexagonCmd((HexagonAdapter) selectedShape, dlg.getHexagon());
					command.execute();
					listActions.push(command);
				}
			}

		}
		clearRedo();
		frame.getView().repaint();
		
	}

	public void delete() {

		if (model.getSelectedShapes() != null) {
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?",
					"Warning message", JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				frame.getTextArea().append("Delete: " + model.getSelectedShapes().toString() + "\n");
				Command command = new RemoveShapeCmd(model.getSelectedShapes(), model);
				command.execute();
				listActions.push(command);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You haven't selected any shape!", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		}
		this.setSelectedShape(null);
		clearRedo();
		frame.getView().repaint();
		frame.getTglbtnSelect().setSelected(false);
		
	}
	
	public void BTF() {
		Shape shape = getSelectedShapes();
		if (model.getIndexOf(shape) == model.getShapes().size() - 1) JOptionPane.showMessageDialog(null, "Shape is already on top!");
		else {
			frame.getTextArea().append("BringToFront: " + shape.toString() + "\n");
			Command command = new BringToFrontCmd(model, shape, model.getShapes().size() - 1);
			command.execute();
			listActions.push(command);
		}
		clearRedo();
		frame.getView().repaint();
	}
	
	public void BTB() {
		Shape shape = getSelectedShapes();
		if (model.getIndexOf(shape) == 0) JOptionPane.showMessageDialog(null, "Shape is already on bottom!");
		else {
			frame.getTextArea().append("BringToBack: " + shape.toString() + "\n");
			Command command = new BringToBackCmd(model, shape);
			command.execute();
			listActions.push(command);
		}
		clearRedo();
		frame.getView().repaint();
	}
	
	public void TF() {
		Shape shape = getSelectedShapes();
		if (model.getIndexOf(shape) == model.getShapes().size() - 1) JOptionPane.showMessageDialog(null, "Shape is already on top!");
		else {
			frame.getTextArea().append("ToFront: " + shape.toString() + "\n");
			Command command = new ToFrontCmd(model, shape);
			command.execute();
			listActions.push(command);
		}
		clearRedo();
		frame.getView().repaint();
	}
	
	public void TB() {
		Shape shape = getSelectedShapes();
		if (model.getIndexOf(shape) == 0) JOptionPane.showMessageDialog(null, "Shape is already on bottom!");
		else {
			frame.getTextArea().append("ToBack: " + shape.toString() + "\n");
			Command command = new ToBackCmd(model, shape);
			command.execute();
			listActions.push(command);
		}
		clearRedo();
		frame.getView().repaint();
	}
	
	public Shape getSelectedShapes() {
		Iterator<Shape> iterator = model.getShapes().iterator();
		while(iterator.hasNext()) {
			Shape shapeForModification = iterator.next();
			if(shapeForModification.isSelected()) return shapeForModification;
		}
		return null;
	}
	
	public void undo() {
		
		if(!listActions.isEmpty()) {
			Command command = listActions.pop();
			command.unexecute();
			undoActions.push(command);
			if(!undoActions.isEmpty()) {
				frame.getBtnRedo().setEnabled(true);
			}
			if(listActions.isEmpty()) {
				frame.getBtnUndo().setEnabled(false);
			}
			frame.getTextArea().append("Undo \n");
		}
		
		frame.getView().repaint(); 
		
	}
	
	public void redo() {
		if(!undoActions.isEmpty()) {
			Command command = undoActions.pop();
			command.execute();
			listActions.push(command);
			
			if(undoActions.isEmpty()) {
				frame.getBtnRedo().setEnabled(false);
			}
			frame.getTextArea().append("Redo \n");
		}
		if(undoActions.isEmpty()) {
			frame.getBtnRedo().setEnabled(false);
		}
		if(!listActions.isEmpty()) {
			frame.getBtnUndo().setEnabled(true);
		}
		
		frame.getView().repaint();
	}
	
	public void clearRedo() {
		getUndoActions().clear();
		frame.getBtnRedo().setEnabled(false);
	}
	
	public void saveLog() {
		
		SaveLog save = new SaveLog(frame);
		JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int dialog = jFileChooser.showSaveDialog(frame);
		
		if(dialog == JFileChooser.APPROVE_OPTION) {
			String path = jFileChooser.getSelectedFile().getPath();
			save.save(path);
			JOptionPane.showMessageDialog(frame, "File saved successfully.", "Message", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(frame, "Cancelled.", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void saveDraw() {
		
		SaveDraw save = new SaveDraw(model);
		JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int dialog = jFileChooser.showSaveDialog(frame);
		
		if(dialog == JFileChooser.APPROVE_OPTION) {
			String path = jFileChooser.getSelectedFile().getPath();
			save.save(path);
			JOptionPane.showMessageDialog(frame, "Drawing has been successfully saved.", "Message", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(frame, "Cancelled.", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void openDrawing() {
		JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int dialog = jFileChooser.showSaveDialog(frame);
		File file = jFileChooser.getSelectedFile();
		ArrayList<Shape> temp = new ArrayList<Shape>();
		
		if(dialog == JFileChooser.APPROVE_OPTION) {
			model.clearAll();
			frame.getTextArea().setText(" ");
			
			try {
				FileInputStream fin = new FileInputStream(file);
		        ObjectInputStream oin = new ObjectInputStream(fin);
		        
		        temp = (ArrayList<Shape>) oin.readObject();
		        model.getShapes().addAll(temp);
		        fin.close();
		        oin.close();
		        
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			frame.getBtnUndo().setEnabled(false);
			frame.getView().repaint();
			
		} else {
			JOptionPane.showMessageDialog(null, "Cancelled", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void openLog() {
		JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int dialog = jFileChooser.showSaveDialog(frame);
		File file = jFileChooser.getSelectedFile();
		
		if(dialog == JFileChooser.APPROVE_OPTION) {
			model.clearAll();
			frame.getTextArea().setText(" ");
			
			try {
				@SuppressWarnings("resource")
				Scanner scan = new Scanner(file);
				frame.getTextArea().append("Start loading commands \n");
				JOptionPane.showMessageDialog(null, "Click Next to load the next line.", "Message", JOptionPane.INFORMATION_MESSAGE);
				frame.getBtnNext().setVisible(true);
				frame.getBtnOpenLog().setVisible(false);
				
				frame.getBtnNext().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Scanner temp = scan;
						try
				        {
				            if(temp.hasNextLine()) {
				            	String t = temp.nextLine();
				            	writeLog(t);
				            } else {
				            	frame.getBtnNext().setVisible(false);
				            	frame.getBtnOpenLog().setVisible(true);
								JOptionPane.showMessageDialog(null, "There is no more shapes to load.", "Message", JOptionPane.INFORMATION_MESSAGE);
				            }
				        
				        }  
				        catch(Exception ex)
				        {
				        	ex.printStackTrace();
				        }
					}
					
				});
				
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "Cancelled.", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
		
		frame.getView().repaint();		
		
	}
	
	public void writeLog(String line) {
		String[] l = line.split(" ");
		Command command;
		
		if(l[0].equals("Add:")) {
			Shape addShape = returnShape(line);
			
			if (addShape != null) {
				frame.getTextArea().append("Add: " + (addShape.toString() + "\n"));
				command = new AddShapeCmd((Shape) addShape, model);
				command.execute();
				listActions.push(command);
			}
			
			if(!getListActions().isEmpty()) {
				frame.getBtnUndo().setEnabled(true);
			}
				
			clearRedo();
			frame.getView().repaint();
		} else if (l[0].equals("Select:")) {
			Shape shape = returnShape(line);
			
			for(int i=0; i < model.getShapes().size(); i++) {
				if (model.getShapes().get(i) instanceof HexagonAdapter && shape instanceof HexagonAdapter) {
					HexagonAdapter hex1 = (HexagonAdapter) model.getShapes().get(i);
					HexagonAdapter hex2 = (HexagonAdapter) shape;

					if (hex1.getHexagon().getX() == hex2.getHexagon().getX()
							&& hex1.getHexagon().getY() == hex2.getHexagon().getY()
							&& hex1.getHexagon().getR() == hex2.getHexagon().getR()
							&& hex1.getHexagon().getAreaColor().equals(hex2.getHexagon().getAreaColor())
							&& hex1.getHexagon().getBorderColor().equals(hex2.getHexagon().getBorderColor())) {
						shape = model.getShapes().get(i);
					}
				}
				else if ((model.getShapes().get(i)).equals(shape)) {
					shape = model.getShapes().get(i);
				}
			}
			shape.setSelected(true);
			model.addSelected(shape);
			frame.getTextArea().append("Select: " + shape.toString() + "\n");
			clearRedo();
			frame.getView().repaint();
		} else if (l[0].equals("Deselect:")) {
			Shape shape = returnShape(line);
			
			for(int i=0; i < model.getShapes().size(); i++) {
				if (model.getShapes().get(i) instanceof HexagonAdapter && shape instanceof HexagonAdapter) {
					HexagonAdapter hex1 = (HexagonAdapter) model.getShapes().get(i);
					HexagonAdapter hex2 = (HexagonAdapter) shape;

					if (hex1.getHexagon().getX() == hex2.getHexagon().getX()
							&& hex1.getHexagon().getY() == hex2.getHexagon().getY()
							&& hex1.getHexagon().getR() == hex2.getHexagon().getR()
							&& hex1.getHexagon().getAreaColor().equals(hex2.getHexagon().getAreaColor())
						&& hex1.getHexagon().getBorderColor().equals(hex2.getHexagon().getBorderColor()))
						{
						shape = model.getShapes().get(i);
					}

				}
				else if (shape.equals(model.getShapes().get(i))) {
					shape = model.getShapes().get(i);
				}
			}
			shape.setSelected(false);
			model.removeSelected(shape);
			frame.getTextArea().append("Deselect: " + shape.toString() + "\n");
			clearRedo();
			frame.getView().repaint();
		}else if (l[0].equals("Delete:")) {
			frame.getTextArea().append("Delete: " + model.getSelectedShapes().toString() + "\n");
			command = new RemoveShapeCmd(model.getSelectedShapes(), model);
			command.execute();
			listActions.push(command);
			
			this.setSelectedShape(null);
			clearRedo();
			frame.getView().repaint();
			frame.getTglbtnSelect().setSelected(false);	
		} else if (l[0].equals("Modify:")) {
			Shape shape = returnShape(line);
			Shape firstShape = model.getSelectedShapes().get(0);
			
			if(l[1].equals("Point:")) {
				frame.getTextArea().append("Modify: " + shape.toString() + "\n");
				command = new UpdatePointCmd((Point) firstShape, (Point) shape);
				command.execute();
				listActions.push(command);
			} else if (l[1].equals("Line:")) {
				frame.getTextArea().append("Modify: " + shape.toString() + "\n");
				command = new UpdateLineCmd((Line) firstShape, (Line) shape);
				command.execute();
				listActions.push(command);
			} else if (l[1].equals("Circle:")) {
				frame.getTextArea().append("Modify: " + shape.toString() + "\n");
				command = new UpdateCircleCmd((Circle) firstShape, (Circle) shape);
				command.execute();
				listActions.push(command);
			} else if (l[1].equals("Rectangle:")) {
				frame.getTextArea().append("Modify: " + shape.toString() + "\n");
				command = new UpdateRectangleCmd((Rectangle) firstShape, (Rectangle) shape);
				command.execute();
				listActions.push(command);
			} else if (l[1].equals("Donut:")) {
				frame.getTextArea().append("Modify: " + shape.toString() + "\n");
				command = new UpdateDonutCmd((Donut) firstShape, (Donut) shape);
				command.execute();
				listActions.push(command);
			} else if (l[1].equals("Hexagon:")) {
				frame.getTextArea().append("Modify: " + shape.toString() + "\n");
				command = new UpdateHexagonCmd((HexagonAdapter) firstShape, (HexagonAdapter) shape);
				command.execute();
				listActions.push(command);
			}
			
			clearRedo();
			firstShape.setSelected(true);
			frame.getView().repaint();
		} else if (l[0].equals("Undo")) {
			undo();
		} else if (l[0].equals("Redo")) {
			redo();
		} else if (l[0].equals("BringToFront:")) {
			BTF();
		} else if (l[0].equals("BringToBack:")) {
			BTB();
		} else if (l[0].equals("ToFront:")) {
			TF();
		} else if (l[0].equals("ToBack:")) {
			TB();
		}
	}
	
	public Shape returnShape(String line) {
		Shape shape = null;
		String[] s = line.split(" ");
		
		if (s[1].equals("Point:")) {
			Point p = new Point(Integer.parseInt(s[2]), Integer.parseInt(s[3]), false, Color.decode(s[5]));
			shape = p;
		} else if (s[1].equals("Line:")) {
			Line l = new Line(new Point(Integer.parseInt(s[3]), Integer.parseInt(s[4])), new Point(Integer.parseInt(s[6]), Integer.parseInt(s[7]), false, Color.decode(s[9])));
			shape = l;
		} else if (s[1].equals("Circle:")) {
			Circle c = new Circle(new Point(Integer.parseInt(s[3]), Integer.parseInt(s[4])), Integer.parseInt(s[6]), false, Color.decode(s[8]), Color.decode(s[10]));
			shape = c;
		} else if (s[1].equals("Rectangle:")) {
			Rectangle r = new Rectangle(new Point(Integer.parseInt(s[5]), Integer.parseInt(s[6])), Integer.parseInt(s[8]), Integer.parseInt(s[10]), false, Color.decode(s[12]), Color.decode(s[14]));
			shape = r;
		} else if (s[1].equals("Donut:")) {
			Donut d = new Donut(new Point(Integer.parseInt(s[4]), Integer.parseInt(s[5])), Integer.parseInt(s[7]), Integer.parseInt(s[13]), false, Color.decode(s[9]), Color.decode(s[11]));
			shape = d;
		} else if (s[1].equals("Hexagon:")) {
			HexagonAdapter hex = new HexagonAdapter(new Point(Integer.parseInt(s[3]), Integer.parseInt(s[4])), Integer.parseInt(s[6]), false, Color.decode(s[8]), Color.decode(s[10]));
			shape = hex;
		}
		
		return shape;
	}
	
	public Color btnOutlineColorClicked() {
		choosenOutlineColor = JColorChooser.showDialog(null, "Colors pallete", outlineColor);
		if (choosenOutlineColor != null) {
			outlineColor = choosenOutlineColor;
			return outlineColor;
		}
		return choosenOutlineColor;
	}
	
	public Color btnInnerColorClicked() {
		choosenInnerColor = JColorChooser.showDialog(null, "Colors pallete", innerColor);
		if (choosenInnerColor != null) {
			innerColor = choosenInnerColor;
			return innerColor;
		}
		return choosenOutlineColor;
	}


	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

	public DrawingFrame getFrame() {
		return frame;
	}

	public void setFrame(DrawingFrame frame) {
		this.frame = frame;
	}
	
	public Stack<Command> getListActions() {
		return listActions;
	}

	public void setListActions(Stack<Command> listActions) {
		this.listActions = listActions;
	}

	public Stack<Command> getUndoActions() {
		return undoActions;
	}

	public void setUndoActions(Stack<Command> undoActions) {
		this.undoActions = undoActions;
	}

}
