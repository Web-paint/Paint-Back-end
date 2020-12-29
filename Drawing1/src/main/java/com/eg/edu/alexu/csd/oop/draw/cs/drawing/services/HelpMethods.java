package com.eg.edu.alexu.csd.oop.draw.cs.drawing.services;

import com.eg.edu.alexu.csd.oop.draw.cs.drawing.database.LoadingData;
import com.eg.edu.alexu.csd.oop.draw.cs.drawing.database.RunningData;
import com.eg.edu.alexu.csd.oop.draw.cs.drawing.database.Selector;
import com.eg.edu.alexu.csd.oop.draw.cs.drawing.model.Shape;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Stack;

public class HelpMethods {
    FactoryShape factory = new FactoryShape();
    Shape shape;
    public double[]Points(String jsonString){
        JSONObject jsonObject=new JSONObject(jsonString);
        JSONObject pos1=jsonObject.getJSONObject("pos1");
        JSONObject pos2=jsonObject.getJSONObject("pos2");
        double selectedX=pos1.getDouble("x");
        double selectedY=pos1.getDouble("y");
        double pointX=pos2.getDouble("x");
        double pointY=pos2.getDouble("y");
        double[]points=new double[4];
        points[0]=selectedX;points[1]=selectedY;
        points[2]=pointX;points[3]=pointY;
        return points;
    }
    
    public void removeElementsfromStack(String element,RunningData data) {
    	Stack<String> temp = new Stack<String>();
    	while (!data.getShapesStack().isEmpty()) {
    		if (!data.getShapesStack().peek().equalsIgnoreCase(element)) {
    			temp.push(data.getShapesStack().pop());
    		}
    		else {
    			data.getShapesStack().pop();
    			break;
    		}
    	}
    	while(!temp.isEmpty()) {
    		data.getShapesStack().push(temp.pop());
    	}
    }
    
    
    public void removeandaddlasttoStack(String element,RunningData data) {
    	data.getShapesStack().add(Integer.parseInt(data.getShapesStack().peek())+1+"");
    	removeElementsfromStack(element, data);
    	
    }
    
    public void addlasttoStack(RunningData data) {
    	data.getShapesStack().push(Integer.parseInt(data.getShapesStack().peek())+1+"");
    }

    @SuppressWarnings("unchecked")
    public String sendToFront(LoadingData data){
        JSONArray jsonArray=new JSONArray();
        if(data.getRectangles()!=null){
            for(int i=0;i<data.getRectangles().size();i++){
                shape = factory.getShape("Rectangle");
                jsonArray.add(shape.dataToString(data, i));
            }
        }
        if(data.getSquares()!=null){
            for(int i=0;i<data.getSquares().size();i++){
                shape = factory.getShape("Square");
                jsonArray.add(shape.dataToString(data, i));
            }
        }
        if(data.getCircles()!=null){
            for(int i=0;i<data.getCircles().size();i++){
                shape = factory.getShape("Circle");
                jsonArray.add(shape.dataToString(data, i));
            }
        }
        if(data.getEllipses()!=null){
            for(int i=0;i<data.getEllipses().size();i++){
                shape = factory.getShape("Ellipse");
                jsonArray.add(shape.dataToString(data, i));
            }
        }
        if(data.getLines()!=null){
            for(int i=0;i<data.getLines().size();i++){
                shape = factory.getShape("LineSegment");
                jsonArray.add(shape.dataToString(data, i));
            }
        }
        if(data.getTriangles()!=null){
            for(int i=0;i<data.getTriangles().size();i++){
                shape = factory.getShape("Triangle");
                jsonArray.add(shape.dataToString(data, i));
            }
        }
        if(data.getShapesStack()!=null){
            org.json.simple.JSONObject jsonObject=new org.json.simple.JSONObject();
            jsonObject.put("name","Shapes");
            for(int i=0;i<data.getShapesStack().size();i++){
                jsonObject.put("Operation"+i,data.getShapesStack().get(i));
            }
            jsonArray.add(jsonObject);
            org.json.simple.JSONObject jsonObjectid=new org.json.simple.JSONObject();
            jsonObjectid.put("name","id");
            jsonObjectid.put("value",data.getShapesStack().peek());
            jsonArray.add(jsonObjectid);
        }

        return jsonArray.toJSONString();
    }
    @SuppressWarnings("unchecked")
    public Selector lastOne (ArrayList<Selector> back, Stack<String> shapes){
        Stack<String> copy = new Stack<String>();
        copy = (Stack<String>) shapes.clone();
        Selector  s = null;
        while (!copy.isEmpty() && s == null){
            String top = copy.pop();
            for (Selector selector : back) {
                if (top.equalsIgnoreCase(selector.getId())) {
                    s = selector;
                }
            }
        }
        return s;
    }
    public ArrayList<Selector>  select (double x,double y,RunningData data) {
        if (data.getTriangles() == null && data.getLines() == null && data.getRectangles() == null && data.getSquares() == null && data.getEllipses() == null && data.getCircles() == null) {
            System.out.println("That's fine");
            return null;
        } else {
            int index = -1;
            String id=null;
            ArrayList<Selector> selectedShapes = new ArrayList<Selector>();
            if (data.getCircles() != null) {
                for (int i = 0; i < data.getCircles().size(); i++) {
                    if (data.getCircles().get(i).CheckIn(x, y, data.getCircles().get(i))) {
                        index = i;
                        id = data.getCircles().get(i).getId();
                    }
                }
                if (index != -1) {
                    selectedShapes.add(new Selector("Circle",id, index));
                    index = -1;
                    id = null;
                }
            }
            if (data.getEllipses() != null) {
                System.out.println(x);
                System.out.println(y);
                for (int i = 0; i < data.getEllipses().size(); i++) {
                    if (data.getEllipses().get(i).CheckIn(x, y, data.getEllipses().get(i))) {
                        index = i;
                        id = data.getEllipses().get(i).getId();
                    }
                }
                if (index != -1) {
                    selectedShapes.add(new Selector("Ellipse",id,index));
                    index = -1;
                    id = null;
                }
            }
            if (data.getRectangles() != null) {
                for (int i = 0; i < data.getRectangles().size(); i++) {
                    if (data.getRectangles().get(i).CheckIn(x, y, data.getRectangles().get(i))) {
                        index = i;
                        id = data.getRectangles().get(i).getId();
                    }
                }
                if (index != -1) {
                    selectedShapes.add(new Selector("Rectangle",id, index));
                    index = -1;
                    id = null;
                }
            }
            if (data.getSquares() != null) {
                for (int i = 0; i < data.getSquares().size(); i++) {
                    if (data.getSquares().get(i).CheckIn(x, y, data.getSquares().get(i))) {
                        index = i;
                        id = data.getSquares().get(i).getId();
                    }
                }
                if (index != -1) {
                    selectedShapes.add(new Selector("Square",id, index));
                    index = -1;
                    id = null;
                }
            }
            if (data.getLines() != null) {
                for (int i = 0; i < data.getLines().size(); i++) {
                    if (data.getLines().get(i).CheckIn(x, y, data.getLines().get(i))) {
                        index = i;
                        id = data.getLines().get(i).getId();
                    }
                }
                if (index != -1) {
                    selectedShapes.add(new Selector("LineSegment",id, index));
                    index = -1;
                    id = null;
                }
            }
            if (data.getTriangles() != null) for (int i = 0; i < data.getTriangles().size(); i++) {
                if (data.getTriangles().get(i).CheckIn(x, y, data.getTriangles().get(i))) {
                    index = i;
                    id = data.getTriangles().get(i).getId();
                }
                if (index != -1) {
                    selectedShapes.add(new Selector("Triangle",id, index));
                    index = -1;
                    id = null;
                }
            }

            return selectedShapes;
        }
    }

}
