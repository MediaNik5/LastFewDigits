package org.medianik.digitsofpower.util;

import java.util.HashMap;

public class SimpleUtil {


    public static HashMap<Long, String> getLastDigitsIncPower(long number, long beginningPower, long maxPower, long step, int module, int limitOfDigits) {
        return getLastDigitsInc(number, beginningPower, maxPower, step, module, limitOfDigits, true);
    }

    public static HashMap<Long, String> getLastDigitsIncNumber(long power, long beginningNumber, long maxNumber, long step, int module, int limitOfDigits){
        return getLastDigitsInc(power, beginningNumber, maxNumber, step, module, limitOfDigits, false);
    }

    public static HashMap<Long, String> getLastDigitsInc(long n, long beginning, long max, long step, int module, int limitOfDigits, boolean isPowerElseNumber){
        HashMap<Long, String> map = new HashMap<>();

        for(long i = beginning; i <= max; i+=step){
            long number
                  = isPowerElseNumber ?
                    optimizedPowWithLimitOfDigits(n, i, limitOfDigits, module) :
                    optimizedPowWithLimitOfDigits(i, n, limitOfDigits, module);

            String value = feelWithLeadingSpaces(String.valueOf(number), limitOfDigits);

            map.put(i, value);
        }
        return map;
    }

    public static String feelWithLeadingSpaces(String s, int limitOfDigits) {
        if(s.length()>=limitOfDigits)
            return s;

        StringBuilder sb = new StringBuilder(limitOfDigits);
        String spaces = repeat(" ", limitOfDigits - s.length());

        sb.append(spaces);
        sb.append(s);

        return sb.toString();
    }

    public static long optimizedPowWithLimitOfDigits(long n, long beginningP, int limitOfDigits, int module) {
        long output = 1;
        long limit = (long) Math.pow(10, limitOfDigits);
        for(int i = 1; i <= beginningP; i++){
            output *= n;
            output %= limit;
        }
        return output % module;
    }

    public static String repeat(String string, int times){
        assert times >= 0;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < times; i++)
            sb.append(string);
        return sb.toString();
    }

    public static long average(long[] values){
        return average(values, 0, values.length-1);
    }

    public static long average(long[] values, int beginning, int end){
        long sum = 0;
        for(int i = beginning; i <= end; i++)
            sum+=values[i];
        return sum/(end-beginning);
    }


    public static String lastDigits(String s, int limit) {
        if(limit > s.length())
            return s;

        return s.substring(s.length()-limit);
    }

    public static StringBuilder setStringsInRowWithSeparation(String[] data, int firstIncluding, int lastExcluding, String separator){
        StringBuilder sb = new StringBuilder(
                (lastExcluding-firstIncluding)*data[firstIncluding].length() +
                        (lastExcluding-firstIncluding-1)*separator.length()
        );

        for(int i = firstIncluding; i < lastExcluding; i++){
            sb.append(data[i]);
            if(i != lastExcluding-1)
                sb.append(separator);
        }
        return sb;
    }

    public static StringBuilder setNumbersInRowWIthSeparation(long firstNumber, long step, int amount, String separator) {

        StringBuilder sb = new StringBuilder(
                amount*numberOfDigits(firstNumber) +
                        separator.length()*(amount-1)
        );

        for(long i = 1; i <= amount; i++){
            sb.append(separator.substring(numberOfDigits(firstNumber)-1));
            sb.append(firstNumber);

            firstNumber += step;
        }
        return sb;
    }

    public static int countNumberOfTargets(String s, String target){
        return (s.length() - s.replace(target, "").length()) / target.length();
    }








    public static int numberOfDigits(long n) {
        // Guessing 4 digit numbers will be more probable.
        // They are set in the first branch.
        if (n < 10000L) { // from 1 to 4
            if (n < 100L) { // 1 or 2
                if (n < 10L)
                    return 1;
                else
                    return 2;

            } else { // 3 or 4
                if (n < 1000L)
                    return 3;
                else
                    return 4;

            }
        } else  { // from 5 a 20 (albeit longs can't have more than 18 or 19)
            if (n < 1000000000000L) { // from 5 to 12
                if (n < 100000000L) { // from 5 to 8
                    if (n < 1000000L) { // 5 or 6
                        if (n < 100000L)
                            return 5;
                        else
                            return 6;

                    } else { // 7 u 8
                        if (n < 10000000L)
                            return 7;
                        else
                            return 8;

                    }
                } else { // from 9 to 12
                    if (n < 10000000000L) { // 9 or 10
                        if (n < 1000000000L)
                            return 9;
                        else
                            return 10;

                    } else { // 11 or 12
                        if (n < 100000000000L)
                            return 11;
                        else
                            return 12;

                    }
                }
            } else { // from 13 to ... (18 or 20)
                if (n < 10000000000000000L) { // from 13 to 16
                    if (n < 100000000000000L) { // 13 or 14
                        if (n < 10000000000000L)
                            return 13;
                        else
                            return 14;

                    } else { // 15 or 16
                        if (n < 1000000000000000L)
                            return 15;
                        else
                            return 16;
                    }
                } else { // from 17 to 18
                    if (n < 100000000000000000L)
                        return 17;
                    else
                        return 18;
                }
            }
        }
    }
}
