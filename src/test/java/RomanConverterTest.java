import org.junit.Test;
import static org.junit.Assert.*;

public class RomanConverterTest {

    RomanConverter roman = new RomanConverter();

    @Test
    public void testValidRomanNumerals() {
        String[] validNumerals = {
                "I", "II", "IiI", "IV", "V", "VI", "VII", "vIII", "IX", "X",
                "XI", "XII", "xiII", "XIV", "XV", "XVI", "Xvii", "XVIII", "XIx", "XX",
                "XL", "L", "XC", "C", "cd", "D", "CM", "M", "MI", "mm", "MMM", "MCmxCIV"
        };

        for (String numeral : validNumerals) {
            assertTrue("Roman numeral " + numeral + " is not valid", roman.isValidRoman(numeral));
        }
    }

    @Test
    public void testInvalidRomanNumerals() {
        String[] invalidNumerals = {
                "IIII", "VV", "XXXX", "LL", "CCCC", "DD", "MMMM", // Repeating more than allowed
                "IIIV", "IIX", "IIL", "IIC", "IID", "IIM",         // Invalid subtractive combinations for I
                "VVX", "VVL", "VVC", "VVD", "VVM",                 // Invalid subtractive combinations for V
                "XXXXL", "XXXXC", "XXXXD", "XXXXM",               // Invalid subtractive combinations for X
                "LLC", "LLD", "LLM",                               // Invalid subtractive combinations for L
                "CCD", "CCM",                                      // Invalid subtractive combinations for C
                "DDM"                                              // Invalid subtractive combinations for D
        };

        for (String numeral : invalidNumerals) {
            assertFalse("Roman numeral " + numeral + " is valid", roman.isValidRoman(numeral));
        }
    }

    @Test
    public void testEmptyString() {
        String emptyString = "";
        assertFalse(roman.isValidRoman(emptyString));
    }

    @Test
    public void testRandomInvalidStrings() {
        String[] invalidStrings = {
                "XXVA", "15sgf", "MMPII", "MMM I", " XXV", "&¤%DGvd",
                "ROMANNUMERAL", "RIV"
        };
        for (String randomString : invalidStrings) {
            assertFalse(roman.isValidRoman(randomString));
        }
    }
}
