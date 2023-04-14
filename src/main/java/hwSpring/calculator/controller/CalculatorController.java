package hwSpring.calculator.controller;

import hwSpring.calculator.service.CalculatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping(path = "/calculator")
    public String hello() {
        return "<b>Добро пожаловать в калькулятор</b>";
    }

    @GetMapping(path = "/calculator/plus")
    public String plus (@RequestParam(value = "num1", required = false) Integer a,
                       @RequestParam(value = "num2", required = false) Integer b){
        return typeOperation(a, b, calculatorService.plus(a, b), "+");
    }

    @GetMapping(path = "/calculator/minus")
    public String minus (@RequestParam(value = "num1", required = false) Integer a,
                        @RequestParam(value = "num2", required = false) Integer b){
        return typeOperation(a, b, calculatorService.minus(a, b), "-");
    }

    @GetMapping(path = "/calculator/multiply")
    public String multiply (@RequestParam(value = "num1", required = false) Integer a,
                         @RequestParam(value = "num2", required = false) Integer b){
        return typeOperation(a, b, calculatorService.multiply(a, b), "*");
    }

    @GetMapping(path = "/calculator/divide")
    public String divide (@RequestParam(value = "num1", required = false) Integer a,
                            @RequestParam(value = "num2", required = false) Integer b){
        return typeOperation(a, b, calculatorService.divide(a, b), "/");
    }

    private String typeOperation(Integer a, Integer b, Number result, String operation) {
        if (a == null || b == null) {
            return "Не передан один из параметров";
        }
        if ("/".equals(operation) && b == 0) {
            return "На 0 делить нельзя";
        }
        return a + " " + operation + " " + b + " = " + result;
    }

}
