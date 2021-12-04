package com.epam.esm.verifier;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerifierTest {

    Verifier verifier = new Verifier();

    @Test
    public void validateIdTestTrue() {
        String id = "123";
        boolean actual = verifier.isValidId(id);
        assertTrue(actual);
    }

    @Test
    public void validateIdTestFalse1() {
        String id = "-1";
        boolean actual = verifier.isValidId(id);
        assertFalse(actual);
    }

    @Test
    public void validateIdTestFalse2() {
        String id = "9323372036854775807";
        boolean actual = verifier.isValidId(id);
        assertFalse(actual);
    }

    @Test
    public void validateIdTestFalse3() {
        String id = "";
        boolean actual = verifier.isValidId(id);
        assertFalse(actual);
    }

    @Test
    public void validateIdTestFalse4() {
        String id = "   ";
        boolean actual = verifier.isValidId(id);
        assertFalse(actual);
    }

    @Test
    public void validateIdTestFalse5() {
        String id = "<script>";
        boolean actual = verifier.isValidId(id);
        assertFalse(actual);
    }

    @Test
    public void validateNameTestTrue1() {
        String name = "Vlad";
        boolean actual = verifier.isValidName(name);
        assertTrue(actual);
    }

    @Test
    public void validateNameTestTrue2() {
        String name = "VladVladVladVladVladVladVladVladVladVladVladV";
        boolean actual = verifier.isValidName(name);
        assertTrue(actual);
    }

    @Test
    public void validateNameTestFalse1() {
        String name = "владислав";
        boolean actual = verifier.isValidName(name);
        assertFalse(actual);
    }

    @Test
    public void validateNameTestFalse2() {
        String name = "";
        boolean actual = verifier.isValidName(name);
        assertFalse(actual);
    }

    @Test
    public void validateNameTestFalse3() {
        String name = "  ";
        boolean actual = verifier.isValidName(name);
        assertFalse(actual);
    }

    @Test
    public void validateNameTestFalse4() {
        String name = "<script>";
        boolean actual = verifier.isValidName(name);
        assertFalse(actual);
    }

    @Test
    public void validateNameTestFalse5() {
        String name = "VladVladVladVladVladVladVladVladVladVladVladVlad";
        boolean actual = verifier.isValidName(name);
        assertFalse(actual);
    }

    @Test
    public void validateDescriptionTestTrue1() {
        String description= "Hello";
        boolean actual = verifier.isValidDescription(description);
        assertTrue(actual);
    }

    @Test
    public void validateDescriptionTestTrue2() {
        String description= "something new";
        boolean actual = verifier.isValidDescription(description);
        assertTrue(actual);
    }

    @Test
    public void validateDescriptionTestTrue3() {
        boolean actual = verifier.isValidDescription(null);
        assertTrue(actual);
    }

    @Test
    public void validateDescriptionTestFalse1() {
        String description= "";
        boolean actual = verifier.isValidDescription(description);
        assertFalse(actual);
    }

    @Test
    public void validateDescriptionTestFalse2() {
        String description= "  ";
        boolean actual = verifier.isValidDescription(description);
        assertFalse(actual);
    }

    @Test
    public void validateDescriptionTestFalse3() {
        String description= "D  <script>";
        boolean actual = verifier.isValidDescription(description);
        assertFalse(actual);
    }

    @Test
    public void validatePriceTestTrue1() {
        BigDecimal price = new BigDecimal("1000000");
        boolean actual = verifier.isValidPrice(price);
        assertTrue(actual);
    }

    @Test
    public void validatePriceTestTrue2() {
        BigDecimal price = new BigDecimal("0");
        boolean actual = verifier.isValidPrice(price);
        assertTrue(actual);
    }

    @Test
    public void validatePriceTestFalse1() {
        BigDecimal price = new BigDecimal("-1000000");
        boolean actual = verifier.isValidPrice(price);
        assertFalse(actual);
    }

    @Test
    public void validateDurationTestTrue1() {
        int duration = 50;
        boolean actual = verifier.isValidDuration(duration);
        assertTrue(actual);
    }

    @Test
    public void validateDurationTestTrue2() {
        int duration = 2147483647;
        boolean actual = verifier.isValidDuration(duration);
        assertTrue(actual);
    }

    @Test
    public void validateDurationTestTrue3() {
        int duration = 0;
        boolean actual = verifier.isValidDuration(duration);
        assertTrue(actual);
    }

    @Test
    public void validateDurationTestFalse1() {
        int duration = -1;
        boolean actual = verifier.isValidDuration(duration);
        assertFalse(actual);
    }
}
