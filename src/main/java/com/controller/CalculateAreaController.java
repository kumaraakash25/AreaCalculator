package com.controller;

import com.model.Result;
import com.model.Shape;
import com.service.CalculateArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.model.Dimension.Breadth;
import static com.model.Dimension.Length;

@RestController
@RequestMapping("/area")
public class CalculateAreaController {

    @Autowired
    private CalculateArea calculateAreaService;

    @RequestMapping(value = "/circle/{Radius}", method = RequestMethod.GET)
    public ResponseEntity<Result> getCircleArea(@PathVariable("Radius") float radius) {
        Result result = null;
        try {
            result = calculateAreaService.getAreaByShape(Shape.CIRCLE, radius, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Result(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/square", method = RequestMethod.GET)
    public ResponseEntity<Result> getSquareArea(@RequestParam("Side") float side) {
        Result result = null;
        try {
            result = calculateAreaService.getAreaByShape(Shape.SQUARE, side, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Result(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/rectangle/", method = RequestMethod.POST)
    public ResponseEntity<Result> getRectangleArea(@RequestBody Map<String, Float> inputDimensions) {
        Float length = inputDimensions.get(Length.toString());
        Float breadth = inputDimensions.get(Breadth.toString());
        Result result = null;
        try {
            result = calculateAreaService.getAreaByShape(Shape.RECTANGLE, length, breadth);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Result(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
