package com.model;

import java.math.BigDecimal;
import java.util.Map;

public class Result {

    private Shape shape;

    private Map<Dimension, Float> inputSides;

    private BigDecimal area;

    public void setInputSides(Map<Dimension, Float> inputSides) {
        this.inputSides = inputSides;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Shape getShape() {
        return shape;
    }

    public Map<Dimension, Float> getInputSides() {
        return inputSides;
    }

    public BigDecimal getArea() {
        return area;
    }

}
