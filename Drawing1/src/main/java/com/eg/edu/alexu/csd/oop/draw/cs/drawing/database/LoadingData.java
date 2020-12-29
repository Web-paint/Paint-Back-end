package com.eg.edu.alexu.csd.oop.draw.cs.drawing.database;

import com.eg.edu.alexu.csd.oop.draw.cs.drawing.model.*;

import java.util.ArrayList;
import java.util.Stack;

public class LoadingData {
	private ArrayList<Rectangle> rectangles=new ArrayList<Rectangle>();
	private ArrayList<Square>squares=new ArrayList<Square>();
	private ArrayList<Circle>circles=new ArrayList<Circle>();
	private ArrayList<Ellipse>ellipses=new ArrayList<Ellipse>();
	private ArrayList<Triangle>triangles=new ArrayList<Triangle>();
	private ArrayList<LineSegment>lines=new ArrayList<LineSegment>();
	 private Stack<String> shapesStack = new Stack<String>();
    
    public LoadingData(){}
    
    public LoadingData(RunningData data){
        this.setRectangles(data.getRectangles().clone());
        this.setSquares(data.getSquares().clone());
        this.setCircles(data.getCircles().clone());
        this.setEllipses(data.getEllipses().clone());
        this.setTriangles(data.getTriangles().clone());
        this.setLines(data.getLines().clone());
        this.setShapesStack(data.getShapesStack().clone());
    }
    
   
    
	public ArrayList<Rectangle> getRectangles() {
		return rectangles;
	}
	@SuppressWarnings("unchecked")
	public void setRectangles(Object object) {
		this.rectangles = (ArrayList<Rectangle>) object;
	}
	public ArrayList<Square> getSquares() {
		return squares;
	}
	@SuppressWarnings("unchecked")
	public void setSquares(Object object) {
		this.squares = (ArrayList<Square>) object;
	}
	public ArrayList<Circle> getCircles() {
		return circles;
	}
	@SuppressWarnings("unchecked")
	public void setCircles(Object object) {
		this.circles = (ArrayList<Circle>) object;
	}
	public ArrayList<Ellipse> getEllipses() {
		return ellipses;
	}
	@SuppressWarnings("unchecked")
	public void setEllipses(Object object) {
		this.ellipses = (ArrayList<Ellipse>) object;
	}
	public ArrayList<Triangle> getTriangles() {
		return triangles;
	}
	@SuppressWarnings("unchecked")
	public void setTriangles(Object object) {
		this.triangles = (ArrayList<Triangle>) object;
	}
	public ArrayList<LineSegment> getLines() {
		return lines;
	}
	@SuppressWarnings("unchecked")
	public void setLines(Object object) {
		this.lines = (ArrayList<LineSegment>) object;
	}

	public Stack<String> getShapesStack() {
		return shapesStack;
	}
	
	@SuppressWarnings("unchecked")
	public void setShapesStack(Object object) {
		this.shapesStack = (Stack<String>) object;
	}
}
