import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This JUnit class represents a very partial test class for Ex1.
 * Make sure you complete all the needed JUnits
 */
public class Ex1Test {
    @Test
    void computeNumberTest() {
        String s2 = "1011b2";
        int v = Ex1.number2Int(s2);
        assertEquals(v,11);
        String s10 = "1011bA";
        v = Ex1.number2Int(s10);
        s2 = Ex1.int2Number(v,2);
        int v2 = Ex1.number2Int(s2);
        assertEquals(v,v2);
        assertTrue(Ex1.equals(s10,s2));
    }

    @Test
    void isBasisNumberTest() {
        String[] good = {"1", "1b2", "01b2", "123bA", "ABbG", "0bA"};
        for(int i=0;i<good.length;i=i+1) {
            boolean ok = Ex1.isNumber(good[i]);
            assertTrue(ok);
        }
        String[] not_good = {"b2", "2b2", "1G3bG", " BbG", "0bbA", "abB", "!@b2", "A", "1bb2"};
        for(int i=0;i<not_good.length;i=i+1) {
            boolean not_ok = Ex1.isNumber(not_good[i]);
            assertFalse(not_ok);
        }
    }
    @Test
    void int2NumberTest() {
        String[] numbers = {"1001b2","11b3","1DbG","AAbB","1b","1","0b2","10011b2"};
        int[] decimalValue = {9,4,29,120,-1,1,0,19};
        for(int i=0;i<numbers.length;i=i+1) {
            assertEquals(Ex1.number2Int(numbers[i]),decimalValue[i]);
        }
    }
    @Test
    void maxIndexTest() {
        String[][] arrays=  {{"1001b2","11b3","13","36"} ,{"1DbG","AAbB","10010101b2","110110011000b2"},{"1","0b2","1b2","0b2"},{"1","0","1","0"},{"2","10011b2","1FbG","E4bG"}};
        int[] indexs = {3,3,0,0,3};
        for(int i=0;i<arrays.length;i=i+1) {
            assertEquals(Ex1.maxIndex(arrays[i]),indexs[i]);
        }
    }

    @Test
    void number2IntTest() {
        String[] numbers = {"1001b2","11b3","1DbG","AAbB","","1","0b2","10011b2"};
        int[] base = {2,3,16,11,17,10,2,2};
        int[] decimalValue = {9,4,29,120,-1,1,0,19};
        for(int i=0;i<decimalValue.length;i=i+1) {
            assertEquals(Ex1.int2Number(decimalValue[i],base[i]),numbers[i]);
        }
    }

    @Test
    void charToDecimalTest() {
        char[] baseInChar = {'2','3','4','5','6','7','8','9','A','B','C','D','E','F','G'};
        int[] base = {2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        for(int i=0;i<baseInChar.length;i=i+1) {
            boolean ok = Ex1.charToDecimal(baseInChar[i]) == base[i];
            assertTrue(ok);
        }
    }

    @Test
    void decimalToCharTest() {
        String[] baseInChar = {"2","3","4","5","6","7","8","9","A","B","C","D","E","F","G"};
        int[] base = {2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        for(int i=0;i<baseInChar.length;i=i+1) {
            boolean ok = Ex1.decimalToChar(base[i]).equals(baseInChar[i]);
            assertTrue(ok);
        }
    }

    @Test
    void splitByBTest() {
        String[] beforeSplit = {"1b2", "01b2", "123bA", "ABbG"};
        String[][] afterSplit = {{"1","2"},{"01","2"},{"123","10"},{"AB","16"}};
        for(int i=0;i<beforeSplit.length;i=i+1) {
            assertEquals(Ex1.splitByB(beforeSplit[i])[0],afterSplit[i][0]);
            assertEquals(Ex1.splitByB(beforeSplit[i])[1],afterSplit[i][1]);
        }
        assertEquals(Ex1.splitByB("0bA")[0],"0");
    }

    @Test
    void isDigitTest() {
        String[] good = {"1", "12", "13", "9", "0"};
        for(int i=0;i<good.length;i=i+1) {
            boolean ok = Ex1.isDigit(good[i]);
            assertTrue(ok);
        }
        String[] not_good = {"1a", "b2b", "31c", "", " "};
        for(int i=0;i<not_good.length;i=i+1) {
            boolean not_ok = Ex1.isDigit(not_good[i]);
            assertFalse(not_ok);
        }
    }

    @Test
    void equalsTest() {
        assertTrue(Ex1.equals("444b7","E4bG"));

    }

    @Test
    void calculateNumbersTest() {
        assertTrue(Ex1.equals(Ex1.calculateNumbers("12","10011b2",16, '+'),"1FbG"));
        assertTrue(Ex1.equals(Ex1.calculateNumbers("12","10011b2",16, '*'),"E4bG"));
        assertTrue(Ex1.equals(Ex1.calculateNumbers("1DbG","AAbB",2, '+'),"10010101b2"));
        assertTrue(Ex1.equals(Ex1.calculateNumbers("1DbG","AAbB",2, '*'),"110110011000b2"));

    }

}