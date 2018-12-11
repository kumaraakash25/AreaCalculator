package com.service;

import com.model.Dimension;
import com.model.Result;
import com.model.Shape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static com.model.Dimension.*;

@Service
public class CalculateArea {

    private static final Logger LOG = LoggerFactory.getLogger(CalculateArea.class);

    /**
     * Validates the input and throws an Exception if any Validation fails. Otherwise returns a {@code Result}
     * object containing description of shape and calculated area.
     *
     * @param inputShape inputShape
     * @param side1 side1 in case of all figures
     * @param side2 side 2 only in case of Rectangle
     * @return
     * @throws Exception
     */
    public Result getAreaByShape(final Shape inputShape, final float side1, final float side2) throws Exception {
        validateInput(inputShape, side1, side2);
        LOG.info("Input data successfully validated....");
        final Result result = new Result();
        final Map<Dimension, Float> inputMap = new HashMap<>();
        BigDecimal calculatedShapeArea;
        switch (inputShape) {
            case SQUARE:
                calculatedShapeArea = BigDecimal.valueOf(side1 * side1);
                inputMap.put(Side, side1);
                break;
            case CIRCLE:
                calculatedShapeArea = BigDecimal.valueOf(Math.PI * side1 * side1);
                inputMap.put(Radius, side1);
                break;
            case RECTANGLE:
                calculatedShapeArea = BigDecimal.valueOf(side1 * side2);
                inputMap.put(Length, side1);
                inputMap.put(Breadth, side2);
                break;
            default:
                LOG.info("No suitable Shape implementation found. Returning null. ");
                return null;
        }
        result.setShape(inputShape);
        result.setArea(calculatedShapeArea.setScale(2, RoundingMode.DOWN));
        result.setInputSides(inputMap);
        LOG.info("Returning result {} ", result);
        return result;
    }

    private void validateInput(final Shape inputShape, final float side1, final float side2) throws Exception {
        validateSide(side1);
        // Only in case of rectangle we need to validate the second input side
        if (inputShape.equals(Shape.RECTANGLE)) {
            validateSide(side2);
        }
        validateShape(inputShape);
    }

    // Validates that the input input sides should not be negative
    private void validateSide(final float side) throws Exception {
        if (side < 0) {
            LOG.error("Input side {} is negative ", side);
            throw new Exception("Invalid input. Sides cannot be negative");
        }
    }

    // Validates that the input shape is in the list of allowed shapes
    private void validateShape(final Shape inputShape) throws Exception {
        for (Shape shape : Shape.values()) {
            if (inputShape.equals(shape)) {
                return;
            }
        }
        LOG.error("Input shape {} is invalid" , inputShape );
        throw new Exception("Input Shape is not valid.");
    }
}

