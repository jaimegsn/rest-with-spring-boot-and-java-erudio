package br.com.erudio;
import br.com.erudio.services.MathServices;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathController {
    private final MathServices mathServices;

    public MathController() {
        mathServices = new MathServices();
    }

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum (@PathVariable String numberOne,
                       @PathVariable String numberTwo){
        return mathServices.sum(numberOne, numberTwo);
    }

    @RequestMapping(value = "/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtraction (@PathVariable String numberOne,
                               @PathVariable String numberTwo){
        return mathServices.subtraction(numberOne, numberTwo);
    }

    @RequestMapping(value = "/multi/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiplication (@PathVariable String numberOne,
                                  @PathVariable String numberTwo){
        return mathServices.multiplication(numberOne, numberTwo);
    }

    @RequestMapping(value = "/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double division (@PathVariable String numberOne,
                            @PathVariable String numberTwo){
        return mathServices.division(numberOne, numberTwo);
    }

    @RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double mean (@PathVariable String numberOne,
                        @PathVariable String numberTwo){
        return mathServices.mean(numberOne, numberTwo);
    }

    @RequestMapping(value = "/square/{number}", method = RequestMethod.GET)
    public Double squareRoot (@PathVariable String number){
        return mathServices.squareRoot(number);
    }
}
