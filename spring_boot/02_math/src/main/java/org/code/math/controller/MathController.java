package org.code.math.controller;

import org.code.math.entity.Operation;
import org.code.math.entity.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequestMapping(path = "math")
public class MathController {

    @Autowired
    OperationRepository operationRepository;

    //SWAGGER
    //http://localhost:8080/swagger-ui/index.html

    @PostMapping(path = "/add")
    public @ResponseBody String add(@RequestParam float num1, @RequestParam float num2){
        float result = num1 + num2;
        Operation operation = new Operation(num1, num2, result, "addition");
        try{
            operationRepository.save(operation);
        }catch(Exception e) {
            return ("Error in saving: " + e);
        }
        return String.valueOf(result);
    }

    @PostMapping(path = "/somma/{num1}/{num2}")
    public @ResponseBody String somma(@PathVariable float num1, @PathVariable float num2){
        float result = num1 + num2;
        Operation operation = new Operation(num1, num2, result, "addition");
        try{
            operationRepository.save(operation);
        }catch(Exception e) {
            return ("Error in saving: " + e);
        }
        return String.valueOf(result);
    }


    @PostMapping(path = "/subtract")
    public @ResponseBody String  subtract(@RequestParam float num1, @RequestParam float num2){
        float result = num1 -num2;
        Operation operation = new Operation(num1, num2, result, "subtraction");
        try{
            operationRepository.save(operation);
        }catch(Exception e){
            return ("Error in saving: " +e);
        }

        return String.valueOf(result);
    }

    @PostMapping(path = "/mult")
    public @ResponseBody String mult(@RequestParam float num1, @RequestParam float num2){
        float result = num1 * num2;
        Operation operation = new Operation(num1, num2, result, "multiplication");
        try{
            operationRepository.save(operation);
        }catch(Exception e) {
            return ("Error in saving: " + e);
        }
        return String.valueOf(result);
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody String delete(@RequestParam int id){
        try{
            operationRepository.deleteById(id);
        }catch (Exception e){
            return ("Error in deletion: "+ e);
        }
        return "Operation with id = " +id + " has been deleted";
    }


}
