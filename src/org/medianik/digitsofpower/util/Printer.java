package org.medianik.digitsofpower.util;

import java.util.HashMap;
import java.util.Iterator;

public class Printer {

    int width, high;
    int widthSeparating;
    HashMap<Long, String> data;

    String separator;

    public Printer(int width, int widthSeparating, HashMap<Long, String> map){
        this.width = width;
        this.widthSeparating = widthSeparating;
        this.data = map;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(data.size()*10);

        Iterator<Long> iterator = data.keySet().iterator();
        long firstKey = iterator.next();
        long step = iterator.next()-firstKey;
        long lastKeyInRow = (width-1)*step + firstKey;
        long lastKey = lastKeyInRow;
        while (iterator.hasNext())
            lastKey = iterator.next();

        high = (int) Math.ceil(lastKey/(double)(lastKeyInRow+step));

        feelRows(sb, firstKey, step, lastKey);

        return sb.toString();
    }

    private void feelRows(StringBuilder sb, long firstKey, long step, long lastKey){
        setSeparator(firstKey);

        sb.append(separator);
        sb.append(feelFirstRow(firstKey, step));
        sb.append('\n').append('\n');

        sb.append(feelRowsExceptFirst(step, lastKey));
    }

    private void setSeparator(long anyKey){
        separator = SimpleUtil.repeat(
                " ",
                SimpleUtil.countNumberOfTargets(
                        data.get(anyKey),
                        " ") +
                        SimpleUtil.numberOfDigits(Integer.parseInt(data.get(anyKey).trim()))
        );
    }

    private StringBuilder feelRowsExceptFirst(long step, long lastKey) {
        StringBuilder sb = new StringBuilder((int) (lastKey/step));

        sb.append(feelLeadingKeyAndSpaces(0));

        for(int i = 0; i < high-1; i++) {
            sb.append(feelSingleRow(i * width, (i + 1) * width));
            sb.append('\n');
            if(i % widthSeparating == widthSeparating - 1 && i > 0)
                sb.append('\n');

            sb.append(
                    feelLeadingKeyAndSpaces(
                            data.keySet().toArray(
                                    new Long[0])[
                                    (i+1)*width
                                    ] - step
                    )
            );
        }

        sb.append(feelSingleRow((high - 1)*width, data.size()));

        return sb;
    }

    private StringBuilder feelSingleRow(int firstIncluding, int lastExclusive) {
        return SimpleUtil.setStringsInRowWithSeparation(
                data.values().toArray(new String[0]),
                firstIncluding,
                lastExclusive,
                " "
        );
    }

    private StringBuilder feelLeadingKeyAndSpaces(long toAppend) {
        int substring = SimpleUtil.numberOfDigits(toAppend);
        if(substring >= separator.length())
            substring = separator.length()-1;

        return new StringBuilder()
                .append(separator.substring(substring))
                .append(toAppend)
                .append(" ");
    }

    private StringBuilder feelFirstRow(long first, long step){
        return SimpleUtil.setNumbersInRowWIthSeparation(
                first,
                step,
                width,
                separator
        );
    }
}
