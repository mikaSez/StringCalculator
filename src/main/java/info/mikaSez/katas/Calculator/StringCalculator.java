package info.mikaSez.katas.Calculator;

import sun.misc.Regexp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple string calculator made for tdd kata:  http://osherove.com/tdd-kata-1/
 */
public class StringCalculator {
    List<String> errors = new ArrayList<String>();


    /**
     * <pre>
     * This method will return the sum of passed elements
     *Example :
     *  "" : 0
     * "5" : 5
     * "4,5" : 9
     * "4,5,1": 10
     *"4,5\n1" : 10
     *
     * You can define custom delimiters by specifying them on the first line :
     * Example :
     * "\\;\\n4;6" : 10
     *
     *
     * Big numbers are ignored :
     * "4, 1000" : 4
     *</pre>
     * @throws IllegalArgumentException : negatives are not allowed
     * */
    public int add(String s) {
        String delimiter = ",|\n";
        if(s.isEmpty()){
            return 0;
        }
        if(s.startsWith("\\")){
            int index = s.indexOf("\n");
            if(s.contains("[")){
                delimiter = getBigDelimiter(s);
            }else {
                delimiter = s.substring(index - 1, index);
            }
            s = s.substring(index+1);
        }
        return calculate(s, delimiter);

    }

    private String getBigDelimiter(String s) {
        String result = "";
        Pattern pattern = Pattern.compile("(\\[)(.*)(\\])");
        Matcher matcher = pattern.matcher(s);
        while(matcher.find()){
            result += matcher.group(2);
        }
        System.out.println("result : " + result);
        return Pattern.quote(result);

    }

    private int calculate(String s, String delimiter){
        errors.clear();
        boolean illegal = false;

        int sum = 0;
        String[] numbers = s.split(delimiter);
        for(String number : numbers){
            int n = Integer.parseInt(number);
            if(n < 0){
                illegal = true;
                errors.add(number);
            } else if(n < 1000){
                sum += n;
            } else {
                System.out.println("A too big number has been ignored : " + n);
            }


        }

        if(illegal){
            throw new IllegalArgumentException("Negatives not allowed : " + errors.toString());
        }
        return sum;
    }
}
