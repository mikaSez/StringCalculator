package info.mikaSez.katas.Calculator;

import info.mikaSez.katas.Calculator.StringCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by MikaSez on 12/03/2016.
 */
public class StringCalculatorTest {


    private StringCalculator tested;

    @Before
    public void setUp(){
        tested = new StringCalculator();
    }
    @Test
    public void methodAddWithEmptyString(){
        int sum = tested.add("");
        Assert.assertEquals(0, sum);

    }

    @Test
    public void methodAddWithOneNumber(){
        int sum = tested.add("5");
        Assert.assertEquals(5, sum);
    }

    @Test
    public void methodAddWithTwoNumbers(){
        int sum = tested.add("5,3");
        Assert.assertEquals(8, sum);
    }

    @Test
    public void methodAddWithManyNumbers(){
        int sum = tested.add("5,4,3");
        Assert.assertEquals(12,sum);
    }

    @Test
    public void methodNewLineDelimiter(){
        int sum = tested.add("5\n4,3");
        Assert.assertEquals(12, sum);
    }

    @Test
    public void customDelimiter(){
        int sum = tested.add("\\\\;\n4;3");
        Assert.assertEquals(7,sum);
    }


    @Test(expected = IllegalArgumentException.class)
    public void negativeNumbersNotAllowed(){
        int sum = tested.add("-4,3");
    }

    @Test
    public void negativesNumberExceptionMessageShouldContainAllWrongValues(){
        try{
            int sum = tested.add("-4,3,-5,5");
        } catch(IllegalArgumentException e){
            Assert.assertEquals("Negatives not allowed : [-4, -5]", e.getMessage());
        }


    }

}
