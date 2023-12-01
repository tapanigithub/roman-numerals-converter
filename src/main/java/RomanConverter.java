import java.util.*;

public class RomanConverter {

    private String errorMsg;
    private final Map<Character,Integer> romansToIntegers = new HashMap<>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};
    private final Map<Character,Integer> maxRepetitions = new HashMap<>() {{
        put('I', 3);
        put('V', 1);
        put('X', 3);
        put('L', 1);
        put('C', 3);
        put('D', 1);
        put('M', 3);
    }};

    public final int convert(String romanNumeral) {

        if (!isValidRoman(romanNumeral)) throw new IllegalArgumentException(getErrorMsg());

        // make the counting simpler by replacing values in the string.
        romanNumeral = romanNumeral.toUpperCase();
        romanNumeral = romanNumeral.replace("IV", "IIII");
        romanNumeral = romanNumeral.replace("IX", "VIIII");
        romanNumeral = romanNumeral.replace("XL", "XXXX");
        romanNumeral = romanNumeral.replace("XC", "LXXXX");
        romanNumeral = romanNumeral.replace("CD", "CCCC");
        romanNumeral = romanNumeral.replace("CM", "DCCCC");

        int result = 0;
        for (int i = 0; i < romanNumeral.length(); i++) {
            result += romansToIntegers.get(romanNumeral.charAt(i));
        }
        return result;
    }

    public final boolean isValidRoman(String romanString) {

        romanString = romanString.toUpperCase();

        if (romanString.isEmpty()) {
            setErrorMsg("You provided an empty string.");
            return false;
        }

        else if (checkInvalidLetters(romanString)) {
            setErrorMsg("Your input contained other than roman numerals.");
            return false;
        }
        else if (checkInvalidCombinations(romanString)) {
            setErrorMsg("Your input contained invalid combination of roman numerals.");
            return false;
        }
        else {
            setErrorMsg("");
            return true;
        }

    }


    private boolean checkInvalidCombinations(String romanString) {

        for (int i = 0; i < romanString.length(); i++) {
            char c = romanString.charAt(i);
            int count = 1;
            int max = maxRepetitions.get(c);

            while (i + 1 < romanString.length() && romanString.charAt(i + 1) == c) {
                i++;
                count++;
                if (count > max) return true;
            }

            if (i + 1 < romanString.length() &&
                    romansToIntegers.get(romanString.charAt(i+1)) > romansToIntegers.get(c)) {
                char next = romanString.charAt(++i);
                if (max != 3) return true; // only subtract I, X, C
                if (count > 1) return true; // only subtract once
                if (romansToIntegers.get(next) > 10 * romansToIntegers.get(c)) return true;
            }
        }
        return false;
    }

    private boolean checkInvalidLetters(String romanString) {

        for (char c : romanString.toCharArray()) {
            if (!romansToIntegers.containsKey(c)) {
                return true;
            }
        }
        return false;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    private void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


}

