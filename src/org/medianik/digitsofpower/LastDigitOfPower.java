package org.medianik.digitsofpower;

import org.medianik.digitsofpower.util.Printer;
import org.medianik.digitsofpower.util.SimpleUtil;

import java.util.HashMap;


public class LastDigitOfPower {

    private final String stringRepresentation;

    public LastDigitOfPower(long number, long power, int module, long step, long max, int limitOfDigits, int width, int blocks, boolean isStepPowerElseNumber){
        HashMap<Long, String> result;
        if(isStepPowerElseNumber)
            result = SimpleUtil.getLastDigitsIncPower(number, power, max, step, module, limitOfDigits);
        else result = SimpleUtil.getLastDigitsIncNumber(power, number, max, step, module, limitOfDigits);

        stringRepresentation = new Printer(width, blocks, result).toString();
    }

    public String toString(){
        return stringRepresentation;
    }

}
