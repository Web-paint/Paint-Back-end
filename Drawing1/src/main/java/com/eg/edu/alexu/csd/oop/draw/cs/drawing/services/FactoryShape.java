package com.eg.edu.alexu.csd.oop.draw.cs.drawing.services;

import com.eg.edu.alexu.csd.oop.draw.cs.drawing.model.*;

public class FactoryShape {
    public Shape getShape(String shapeName){
        if(shapeName==null) return null;
        if(shapeName.equalsIgnoreCase("LineSegment"))
            return LineSegment.getInstance();
        else if(shapeName.equalsIgnoreCase("Circle"))
            return Circle.getInstance();
        else if(shapeName.equalsIgnoreCase("Ellipse"))
            return Ellipse.getInstance();
        else if(shapeName.equalsIgnoreCase("Triangle"))
            return Triangle.getInstance();
        else if(shapeName.equalsIgnoreCase("Rectangle"))
            return Rectangle.getInstance();
        else if(shapeName.equalsIgnoreCase("Square"))
            return Square.getInstance();
        else return null;
    }
}
