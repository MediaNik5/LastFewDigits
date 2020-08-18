package org.medianik.digitsofpower.util;

import java.util.HashMap;
import java.util.Iterator;

public class Printer {

    long firstKey, step, lastKeyInRow, lastKey;

    int width, high;
    int widthSeparating;
    HashMap<Long, String> data;

    String separator;

    public Printer(int width, int widthSeparating, HashMap<Long, String> map){
        this.width = width;
        this.widthSeparating = widthSeparating;
        this.data = map;
        initialize();
    }

    private void initialize(){
        Iterator<Long> iterator = data.keySet().iterator();

        firstKey = iterator.next();
        step = iterator.next()-firstKey;
        lastKeyInRow = (width-1)*step + firstKey;
        lastKey = lastKeyInRow;
        while (iterator.hasNext())
            lastKey = iterator.next();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(data.size()*10);

        high = (int) Math.ceil(lastKey/(double)(lastKeyInRow + step));

        feelRows(sb);

        return sb.toString();
    }

    private void feelRows(StringBuilder sb){
        setUpSeparator();

        sb.append(separator);
        feelFirstRow(sb);
        sb.append('\n').append('\n');

        feelRowsExceptFirst(sb);
    }

    private void setUpSeparator(){
        separator = SimpleUtil.repeat(
                " ",
                SimpleUtil.countNumberOfTargets(
                        data.get(firstKey),
                        " ") +
                        SimpleUtil.numberOfDigits(Integer.parseInt(data.get(firstKey).trim()))
        );
    }

    private void feelRowsExceptFirst(StringBuilder sb) {
        feelLeadingKeyAndSpaces(sb, 0);

        for(int i = 0; i < high-1; i++) {
            feelSingleRow(sb, i * width, (i + 1) * width);
            sb.append('\n');

            if(isSeparationNeeded(i))
                sb.append('\n');

            feelLeadingKeyAndSpaces(
                    sb,
                    data.keySet().toArray(
                            new Long[0])
                            [(i+1)*width] - step
            );
        }

        feelSingleRow(sb, (high - 1)*width, data.size());
    }


    private boolean isSeparationNeeded(int row) {
        return row % widthSeparating == widthSeparating - 1 && row > 0;
    }

    private void feelSingleRow(StringBuilder sb, int firstIncluding, int lastExclusive) {
        SimpleUtil.setStringsInRowWithSeparation(
                sb,
                data.values().toArray(new String[0]),
                firstIncluding,
                lastExclusive,
                " "
        );
    }

    private void feelLeadingKeyAndSpaces(StringBuilder sb, long keyToAppend) {
        int substring = SimpleUtil.numberOfDigits(keyToAppend);
        if(substring >= separator.length())
            substring = separator.length()-1;

       sb.append(separator.substring(substring))
               .append(keyToAppend)
               .append(" ");
    }

    private void feelFirstRow(StringBuilder sb){
        SimpleUtil.setNumbersInRowWIthSeparation(
                sb,
                firstKey,
                step,
                width,
                separator
        );
    }
}
