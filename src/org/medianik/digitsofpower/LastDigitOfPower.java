package org.medianik.digitsofpower;


import org.medianik.digitsofpower.util.Printer;

import java.util.HashMap;

import static org.medianik.digitsofpower.util.SimpleUtil.getLastDigitsIncNumber;
import static org.medianik.digitsofpower.util.SimpleUtil.getLastDigitsIncPower;

public class LastDigitOfPower {

    private final String stringRepresentation;

    public LastDigitOfPower(long number, long power, int module, long step, long max, int limitOfDigits, int width, int blocks, boolean isStepPowerElseNumber){
        HashMap<Long, String> result;
        if(isStepPowerElseNumber)
            result = getLastDigitsIncPower(number, power, max, step, module, limitOfDigits);
        else result = getLastDigitsIncNumber(power, number, max, step, module, limitOfDigits);

        stringRepresentation = new Printer(width, blocks, result).toString();
    }

    public String toString(){
        return stringRepresentation;
    }

}
