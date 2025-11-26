
package main.java.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void isIntegerCorrect() {
        String testString = "1";
        assertTrue(Validator.isInteger(testString));
    }

    @Test
    void isIntegerFalse() {
        String testString = "1 ";
        assertFalse(Validator.isInteger(testString));
    }

    @Test
    void containsIntegerCorrect() {
        String testString = "s 1 d";
        assertTrue(Validator.containsInteger(testString));
    }

    @Test
    void containsIntegerFalse() {
        String testString = "s s sdgf:!) d";
        assertFalse(Validator.containsInteger(testString));
    }

    @Test
    void lossyConvertStrinToInt() {
        String testString = "sd 2 sldkf";
        int result = 2;
        assertEquals(result, Validator.lossyConvertStringToInt(testString));
    }



    @Test
    void testCorrectDateInput() {
        String userDateString = "18/08/1975";
        LocalDate userDate = LocalDate.of(1975,8,18);

        assertEquals(Validator.dateValidator(userDateString), userDate);
    }

    @Test
    void testIncorrectStringDateInput() {
        String userDateString = ".";
        //LocalDate userDate = LocalDate.of(1975,8,18);

        assertThrows(SmashException.class, () -> Validator.dateValidator(userDateString));
    }

    @Test
    void testDateErrorInput() {
        String userDateString = "32/08/1975";
        //LocalDate userDate = LocalDate.of(1975,8,18);

        assertThrows(SmashException.class, () -> Validator.dateValidator(userDateString));
    }

    @Test
    void testMonthErrorInput() {
        String userDateString = "10/13/1975";
        //LocalDate userDate = LocalDate.of(1975,8,18);

        assertThrows(SmashException.class, () -> Validator.dateValidator(userDateString));
    }

    @Test
    void testYearErrorInput() {
        String userDateString = "10/10/197";
        //LocalDate userDate = LocalDate.of(1975,8,18);

        assertThrows(SmashException.class, () -> Validator.dateValidator(userDateString));
    }

    @Test
    void testBirthdayMinAgeError() {
        LocalDate birthday = LocalDate.now().minusYears(2);

        assertThrows(SmashException.class, () -> Validator.birthdayValidator(birthday));
    }

    @Test
    void testBirthdayAgeCorrectMin() {
        LocalDate birthday = LocalDate.now().minusYears(3);

        assertDoesNotThrow(() -> Validator.birthdayValidator(birthday));

    }

    @Test
    void testBirthdayMaxAgeError() {
        LocalDate birthday = LocalDate.now().minusYears(121);

        assertThrows(SmashException.class, () -> Validator.birthdayValidator(birthday));
    }

    @Test
    void testBirthdayAgeCorrectMax() {
        LocalDate birthday = LocalDate.now().minusYears(119);

        assertDoesNotThrow(() -> Validator.birthdayValidator(birthday));

    }

    @Test
    void testBirthdayFuture() {
        LocalDate birthday = LocalDate.now().plusDays(1);

        assertThrows(SmashException.class, () -> Validator.birthdayValidator(birthday));
    }





}