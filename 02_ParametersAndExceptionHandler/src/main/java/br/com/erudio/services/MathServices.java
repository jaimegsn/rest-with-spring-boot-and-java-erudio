package br.com.erudio.services;

import br.com.erudio.exceptions.UnsupportedMathOperationException;
import org.springframework.stereotype.Service;

@Service
public class MathServices {

    public Double sum (String numberOne, String numberTwo){
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    public Double subtraction (String numberOne, String numberTwo){
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }
    public Double multiplication (String numberOne, String numberTwo){
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }
    public Double division (String numberOne, String numberTwo){
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }
    public Double mean (String numberOne, String numberTwo){
        return (convertToDouble(numberOne) + convertToDouble(numberTwo))/2;
    }
    public Double squareRoot (String number){
        return Math.sqrt(convertToDouble(number));
    }
    private Double convertToDouble(String strNumber){
        if(strNumber.isBlank())
            return 0D;
        strNumber = strNumber.replaceAll(",",".");
        if(isANumber(strNumber))
            return Double.parseDouble(strNumber);
        throw new UnsupportedMathOperationException("url parameter is not a number");
    }

    private boolean isANumber(String strNumber){
        if(strNumber.isBlank())
            return false;
        strNumber = strNumber.replaceAll(",",".");
        return strNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
