package com.eg.edu.alexu.csd.oop.draw.cs.drawing.model;

import com.eg.edu.alexu.csd.oop.draw.cs.drawing.database.LoadingData;
import com.eg.edu.alexu.csd.oop.draw.cs.drawing.database.RunningData;

import org.json.JSONObject;

public class Triangle extends Shape {
	public double point1X, point1Y, point2X, point2Y, point3X, point3Y;
	private String color,id;
	private static Triangle instance;

	public Triangle() {
	}

	public Triangle(double point1X, double point1Y, double point2X, double point2Y, double point3X, double point3Y, String color,String id) {
		this.point1X = point1X;
		this.point1Y = point1Y;
		this.point2X = point2X;
		this.point2Y = point2Y;
		this.point3X = point3X;
		this.point3Y = point3Y;
		this.color = color;
		this.id = id;
	}

	public double getPoint1X() {
		return point1X;
	}

	public double getPoint1Y() {
		return point1Y;
	}

	public double getPoint2X() {
		return point2X;
	}

	public double getPoint2Y() {
		return point2Y;
	}

	public double getPoint3X() {
		return point3X;
	}

	public double getPoint3Y() {
		return point3Y;
	}

	public void setPoint1X(double point1X) {
		this.point1X = point1X;
	}

	public void setPoint1Y(double point1Y) {
		this.point1Y = point1Y;
	}

	public void setPoint2X(double point2X) {
		this.point2X = point2X;
	}

	public void setPoint2Y(double point2Y) {
		this.point2Y = point2Y;
	}

	public void setPoint3X(double point3X) {
		this.point3X = point3X;
	}

	public void setPoint3Y(double point3Y) {
		this.point3Y = point3Y;
	}

	public static Triangle getInstance() {
		if (instance == null) {
			synchronized (Triangle.class) {
				if (instance == null)
					instance = new Triangle();
			}
		}
		return instance;
	}

	private double tArea(double x1, double y1, double x2, double y2, double x3, double y3) {
		return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
	}

	@Override
	public void copy(double selectedX, double selectedY, double pointX, double pointY, int index, RunningData data) {
		Triangle t = new Triangle();
		t = (Triangle) data.getTriangles().get(index).clone();
		double differenceX = pointX - selectedX;
		double differenceY = pointY - selectedY;
		t.point1X = t.point1X + differenceX;
		t.point1Y = t.point1Y + differenceY;
		t.point2X = t.point2X + differenceX;
		t.point2Y = t.point2Y + differenceY;
		t.point3X = t.point3X + differenceX;
		t.point3Y = t.point3Y + differenceY;
		t.setId(Integer.parseInt(data.getShapesStack().peek())+1+"");
		data.getTriangles().add(t);
	}

	@Override
	public void move(double selectedX, double selectedY, double pointX, double pointY, int index, RunningData data) {
		Triangle t = data.getTriangles().get(index);
		double differenceX = pointX - selectedX;
		double differenceY = pointY - selectedY;
		t.point1X = t.point1X + differenceX;
		t.point1Y = t.point1Y + differenceY;
		t.point2X = t.point2X + differenceX;
		t.point2Y = t.point2Y + differenceY;
		t.point3X = t.point3X + differenceX;
		t.point3Y = t.point3Y + differenceY;
		t.setId(Integer.parseInt(data.getShapesStack().peek())+1+"");
	}

	@Override
	public void addNewShape(String jsonString, RunningData data) {
		JSONObject jsonObject = new JSONObject(jsonString);
		Triangle triangle = new Triangle(jsonObject.getDouble("point1X"), jsonObject.getDouble("point1Y"),
				jsonObject.getDouble("point2X"), jsonObject.getDouble("point2Y"), jsonObject.getDouble("point3X"),
				jsonObject.getDouble("point3Y"),jsonObject.getString("color"),jsonObject.getString("id"));
		data.getTriangles().add(triangle);
	}

	@Override
	public void delete(int index, RunningData data) {
		data.getTriangles().remove(index);
	}

	@Override
	public void resize(int index, RunningData data, double scalar) {
		Triangle t = data.getTriangles().get(index);
		double differenceX = (t.point1X + t.point2X + t.point3X) * (1-Math.sqrt(scalar)) / 3;
		double differenceY = (t.point1Y + t.point2Y + t.point3Y) * (1-Math.sqrt(scalar)) / 3;
		t.point1X = t.point1X * Math.sqrt(scalar)+differenceX;
		t.point1Y = t.point1Y * Math.sqrt(scalar)+differenceY;
		t.point2X = t.point2X * Math.sqrt(scalar)+differenceX;
		t.point2Y = t.point2Y * Math.sqrt(scalar)+differenceY;
		t.point3X = t.point3X * Math.sqrt(scalar)+differenceX;
		t.point3Y = t.point3Y * Math.sqrt(scalar)+differenceY;
		t.setId(Integer.parseInt(data.getShapesStack().peek())+1+"");
	}

	@Override
	public void print(RunningData data) {
		for (int i = 0; i < data.getTriangles().size(); i++) {
			System.out.print(data.getTriangles().get(i).getPoint1X() + "\t");
			System.out.print(data.getTriangles().get(i).getPoint1Y() + "\t");
			System.out.print(data.getTriangles().get(i).getPoint2X() + "\t");
			System.out.print(data.getTriangles().get(i).getPoint2Y() + "\t");
			System.out.print(data.getTriangles().get(i).getPoint2Y() + "\t");
			System.out.print(data.getTriangles().get(i).getPoint3X() + "\t");
			System.out.print(data.getTriangles().get(i).getPoint3Y() + "\n");
		}
	}

	@Override
	public boolean CheckIn(double pointx, double pointy, Object shape) {
		Triangle tr = (Triangle) shape;
		return (tArea(pointx, pointy, tr.point2X, tr.point2Y, tr.point3X, tr.point3Y)
				+ tArea(pointx, pointy, tr.point1X, tr.point1Y, tr.point3X, tr.point3Y)
				+ tArea(pointx, pointy, tr.point2X, tr.point2Y, tr.point1X, tr.point1Y)) == (tArea(tr.point1X,
						tr.point1Y, tr.point2X, tr.point2Y, tr.point3X, tr.point3Y));
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
        jsonObject.put("name","Triangle");
        jsonObject.put("point1X",data.getTriangles().get(index).getPoint1X());
        jsonObject.put("point1Y",data.getTriangles().get(index).getPoint1Y());
        jsonObject.put("point2X",data.getTriangles().get(index).getPoint2X());
        jsonObject.put("point2Y",data.getTriangles().get(index).getPoint2Y());
        jsonObject.put("point3X",data.getTriangles().get(index).getPoint3X());
        jsonObject.put("point3Y",data.getTriangles().get(index).getPoint3Y());
        jsonObject.put("color",data.getTriangles().get(index).getColor());
        jsonObject.put("id", data.getTriangles().get(index).getId());
		return jsonObject;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
