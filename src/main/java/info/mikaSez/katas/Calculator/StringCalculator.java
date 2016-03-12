package info.mikaSez.katas.Calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Ab simple string calculator made for tdd kata:  http://osherove.com/tdd-kata-1/
 */
class StringCalculator {



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
            delimiter = s.substring(index-1, index);
            s = s.substring(index+1);
        }
        return calculate(s, delimiter);

    }

    private int calculate(String s, String delimiter){
        List<String> l = new ArrayList();
        boolean illegal = false;
        int sum = 0;
        String[] numbers = s.split(delimiter);
        for(String number : numbers){
            int n = Integer.parseInt(number);
            if(n < 0){
                illegal = true;
                l.add(number);
            } else {
                sum += n;
            }
        }

        if(illegal){
            throw new IllegalArgumentException("Negatives not allowed : " + l.toString());
        }
        return sum;
    }
}
