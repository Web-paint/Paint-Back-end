package com.eg.edu.alexu.csd.oop.draw.cs.drawing.model;

import com.eg.edu.alexu.csd.oop.draw.cs.drawing.database.LoadingData;
import com.eg.edu.alexu.csd.oop.draw.cs.drawing.database.RunningData;
import org.json.JSONObject;

public class Square extends Shape {
	private double startPointX, startPointY, length;
	 private String color,id;
	private static Square instance;

	public Square() {
	}

	public Square(double startPointX, double startPointY, double length, String color,String id) {
		this.startPointX = startPointX;
		this.startPointY = startPointY;
		this.length = length;
		this.color = color;
		this.id = id;
	}

	public double getStartPointX() {
		return startPointX;
	}

	public double getStartPointY() {
		return startPointY;
	}

	public double getLength() {
		return length;
	}

	public void setStartPointX(double startPointX) {
		this.startPointX = startPointX;
	}

	public void setStartPointY(double startPointY) {
		this.startPointY = startPointY;
	}

	public void setLength(double length) {
		this.length = length;
	}

	@Override
	public void copy(double selectedX, double selectedY, double pointX, double pointY, int index, RunningData data) {
		/*
		 * Square s = new Square(data.squares.get(index).getStartPointX(),
		 * data.squares.get(index).getStartPointY(),
		 * data.squares.get(index).getLength());
		 */
		Square s = new Square();
		s = (Square) data.getSquares().get(index).clone();
		double differenceX = pointX - selectedX;
		double differenceY = pointY - selectedY;
		s.startPointX = s.startPointX + differenceX;
		s.startPointY = s.startPointY + differenceY;
		s.setId(Integer.parseInt(data.getShapesStack().peek())+1+"");
		data.getSquares().add(s);
	}

	@Override
	public void move(double selectedX, double selectedY, double pointX, double pointY, int index, RunningData data) {
		Square s = data.getSquares().get(index);
        double differenceX = pointX-selectedX;
        double differenceY = pointY-selectedY;
        s.startPointX = s.startPointX+differenceX;
        s.startPointY = s.startPointY+differenceY;
        s.setId(Integer.parseInt(data.getShapesStack().peek())+1+"");
	}

	@Override
	public void addNewShape(String jsonString, RunningData data) {
		JSONObject jsonObject = new JSONObject(jsonString);
		Square square = new Square(jsonObject.getDouble("startPointX"), jsonObject.getDouble("startPointY"),
				jsonObject.getDouble("length"),jsonObject.getString("color"),jsonObject.getString("id"));
		data.getSquares().add(square);
	}

	@Override
	public void delete(int index, RunningData data) {
		data.getSquares().remove(index);
	}

	@Override
	public void resize(int index, RunningData data, double scalar) {
		Square s = new Square();
        s =	(Square) data.getSquares().get(index).clone();
        double difference = s.length  * (scalar/2);
        double old = s.length;
        s.length =s.length * scalar ;
        if(scalar>1) {
            s.startPointX = s.startPointX-difference+(old/2);
            s.startPointY = s.startPointY-difference+(old/2);
        }else if (scalar<1) {
            s.startPointX = s.startPointX-difference+(old/2);
            s.startPointY = s.startPointY-difference+(old/2);
        }
        s.setId(Integer.parseInt(data.getShapesStack().peek())+1+"");
        data.getSquares().add(index,s);
        data.getSquares().remove(index+1);
	}

	@Override
	public void print(RunningData data) {
		for (int i = 0; i < data.getSquares().size(); i++) {
			System.out.print(data.getSquares().get(i).getStartPointX() + "\t");
			System.out.print(data.getSquares().get(i).getStartPointY() + "\t");
			System.out.print(data.getSquares().get(i).getLength() + "\n");
		}
	}

	public static Square getInstance() {
		if (instance == null) {
			synchronized (Square.class) {
				if (instance == null)
					instance = new Square();
			}
		}
		return instance;
	}

	@Override
	public boolean CheckIn(double pointx, double pointy, Object shape) {
		Square squ = (Square) shape;
		double startX = squ.startPointX - 1.5;
		double startY = squ.getStartPointY() - 1.5;
		double endX = squ.getStartPointX() + squ.getLength() + 1.5;
		double endY = squ.getStartPointY() + squ.getLength() + 1.5;
		return pointx > startX && pointx < endX && pointy > startY && pointy < endY;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@SuppressWarnings("unchecked")
	@Override
	public org.json.simple.JSONObject dataToString(LoadingData data, int index) {
		org.json.simple.JSONObject jsonObject=new org.json.simple.JSONObject();
		jsonObject.put("name","Square");
        jsonObject.put("startPointX",data.getSquares().get(index).getStartPointX());
        jsonObject.put("startPointY",data.getSquares().get(index).getStartPointY());
        jsonObject.put("length",data.getSquares().get(index).getLength());
        jsonObject.put("color",data.getSquares().get(index).getColor());
        jsonObject.put("id", data.getSquares().get(index).getId());
		return jsonObject;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
