package com.eg.edu.alexu.csd.oop.draw.cs.drawing.database;
import com.eg.edu.alexu.csd.oop.draw.cs.drawing.model.*;
import java.util.ArrayList;
import java.util.Stack;

public class RunningData {
    private ArrayList<Rectangle>rectangles=new ArrayList<Rectangle>();
    private ArrayList<Square>squares= new ArrayList<Square>();
    private ArrayList<Circle>circles=new ArrayList<Circle>();
    private ArrayList<Ellipse>ellipses=new ArrayList<Ellipse>();
    private ArrayList<Triangle>triangles=new ArrayList<Triangle>();
    private ArrayList<LineSegment>lines=new ArrayList<LineSegment>();
    private Stack<String> shapesStack = new Stack<String>();
    private Stack<LoadingData> undoStack = new Stack<LoadingData>();
    private Stack<LoadingData> redoStack = new Stack<LoadingData>();
    public RunningData () {}
    public RunningData (RunningData data) {
    	this.setRectangles(data.getRectangles());
        this.setSquares(data.getSquares());
        this.setCircles(data.getCircles());
        this.setEllipses(data.getEllipses());
        this.setTriangles(data.getTriangles());
        this.setLines(data.getLines());
        this.setShapesStack(data.getShapesStack());
        this.setUndoStack(data.getUndoStack());
        this.setRedoStack(data.getRedoStack());
    }
    
    public void renewData (LoadingData data){
        this.setRectangles(data.getRectangles());
        this.setSquares(data.getSquares());
        this.setCircles(data.getCircles());
        this.setEllipses(data.getEllipses());
        this.setTriangles(data.getTriangles());
        this.setLines(data.getLines());
        this.setShapesStack(data.getShapesStack());
    }
	public ArrayList<Rectangle> getRectangles() {
		return rectangles;
	}
	public void setRectangles(ArrayList<Rectangle> rectangles) {
		this.rectangles = rectangles;
	}
	public ArrayList<Square> getSquares() {
		return squares;
	}
	public void setSquares(ArrayList<Square> squares) {
		this.squares = squares;
	}
	public ArrayList<Triangle> getTriangles() {
		return triangles;
	}
	public void setTriangles(ArrayList<Triangle> triangles) {
		this.triangles = triangles;
	}
	public ArrayList<LineSegment> getLines() {
		return lines;
	}
	public void setLines(ArrayList<LineSegment> lines) {
		this.lines = lines;
	}
	public ArrayList<Circle> getCircles() {
		return circles;
	}
	public void setCircles(ArrayList<Circle> circles) {
		this.circles = circles;
	}
	public ArrayList<Ellipse> getEllipses() {
		return ellipses;
	}
	public void setEllipses(ArrayList<Ellipse> ellipses) {
		this.ellipses = ellipses;
	}
	public Stack<String> getShapesStack() {
		return shapesStack;
	}
	public void setShapesStack(Stack<String> shapesStack) {
		this.shapesStack = shapesStack;
	}
	public Stack<LoadingData> getUndoStack() {
		return undoStack;
	}
	public void setUndoStack(Stack<LoadingData> undoStack) {
		this.undoStack = undoStack;
	}
	public Stack<LoadingData> getRedoStack() {
		return redoStack;
	}
	public void setRedoStack(Stack<LoadingData> redoStack) {
		this.redoStack = redoStack;
	}
}
