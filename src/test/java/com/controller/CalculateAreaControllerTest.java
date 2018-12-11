package com.controller;

import com.model.Result;
import com.model.Shape;
import com.service.CalculateArea;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CalculateAreaControllerTest {

    private CalculateArea calculateAreaService;

    @Before
    public void setup() {
        calculateAreaService = new CalculateArea();
    }

    @Test
    public void getCircleArea() throws Exception {
        Result result = calculateAreaService.getAreaByShape(Shape.CIRCLE, 1, 0);
        assertNotNull(result);
        assertEquals(new BigDecimal("3.14"), result.getArea());
        assertEquals(1, result.getInputSides().size());
        assertEquals(Shape.CIRCLE, result.getShape());
    }

    @Test
    public void testCircleAreaUnaffectedWhenExtraParam() throws Exception {
        Result result = calculateAreaService.getAreaByShape(Shape.CIRCLE, 2, 3);
        assertNotNull(result);
        assertEquals(new BigDecimal("12.56"), result.getArea());
    }

    @Test(expected = Exception.class)
    public void getCircleAreaWhenInvalidInput() throws Exception {
        Result result = calculateAreaService.getAreaByShape(Shape.CIRCLE, -1, 0);
        assertNull(result);
    }

    @Test
    public void getSquareArea() throws Exception {
        Result result = calculateAreaService.getAreaByShape(Shape.SQUARE, 4, 0);
        assertNotNull(result);
        assertEquals(new BigDecimal("16.00"), result.getArea());
        assertEquals(1, result.getInputSides().size());
    }

    @Test(expected = Exception.class)
    public void getSquareAreaWhenInvalidInput() throws Exception {
        Result result = calculateAreaService.getAreaByShape(Shape.SQUARE, -1, 0);
        assertNull(result);
    }

    @Test
    public void testSquareAreaUnaffectedWhenExtraParam() throws Exception {
        Result result = calculateAreaService.getAreaByShape(Shape.SQUARE, 3.3f, 12);
        assertNotNull(result);
        assertEquals(new BigDecimal("10.88"), result.getArea());
    }

    @Test
    public void testSquareAreaUnaffectedWhenInvalidSecondParam() throws Exception {
        Result result = calculateAreaService.getAreaByShape(Shape.SQUARE, 3, -1.4f);
        assertNotNull(result);
        assertEquals(new BigDecimal("9.00"), result.getArea());
        assertEquals(Shape.SQUARE, result.getShape());

    }

    @Test
    public void getRectangleArea() throws Exception {
        Result result = calculateAreaService.getAreaByShape(Shape.RECTANGLE, 3, 12);
        assertNotNull(result);
        assertEquals(new BigDecimal("36.00"), result.getArea());
        assertEquals(2, result.getInputSides().size());
        assertEquals(Shape.RECTANGLE, result.getShape());

    }

    @Test(expected = Exception.class)
    public void getRectangleAreWhenInvalidInput() throws Exception {
        Result result = calculateAreaService.getAreaByShape(Shape.RECTANGLE, 3, -2);
        assertNull(result);
    }

    @Test(expected = Exception.class)
    public void testGetAreaWithInvalidShape() throws Exception {
        Result result = calculateAreaService.getAreaByShape(null, 3, -2);
        assertNull(result);
    }
}