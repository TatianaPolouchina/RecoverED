package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests the methods in the WeightEntry Class
public class WeightEntryTest {

    private WeightEntry entry1;

    @BeforeEach
    public void setup() {
        entry1 = new WeightEntry(100, "lbs");
    }

    @Test
    public void testSetWeightLbs() {
        entry1.setWeight(100, "lbs");
        assertEquals(100, entry1.getWeight("lbs"), 0.01);
        assertEquals(45.3592, entry1.getWeight("kg"), 0.01);
        assertEquals(7.14286, entry1.getWeight("stone"), 0.01);
    }

    @Test
    public void testSetWeightKg() {
        entry1.setWeight(45.3592, "kg");
        assertEquals(100, entry1.getWeight("lbs"), 0.01);
        assertEquals(45.3592, entry1.getWeight("kg"), 0.01);
        assertEquals(7.14286, entry1.getWeight("stone"), 0.01);
    }

    @Test
    public void testSetWeightStone() {
        entry1.setWeight(7.14286, "stone");
        assertEquals(100, entry1.getWeight("lbs"), 0.01);
        assertEquals(45.3592, entry1.getWeight("kg"), 0.01);
        assertEquals(7.14286, entry1.getWeight("stone"), 0.01);    }

    @Test
    public void testGetWeightLbs() {
        assertEquals(100, entry1.getWeight("lbs"));
    }

    @Test
    public void testGetWeightKg() {
        assertEquals(45.359, entry1.getWeight("kg"), 0.01);
    }

    @Test
    public void testGetWeightStone() {
        assertEquals(7.143, entry1.getWeight("stone"), 0.01);
    }

    @Test
    public void testConvertToLbsFromLbs() {
        assertEquals(100, entry1.convertTolbs(100, "lbs"), 0.01);
    }

    @Test
    public void testConvertToLbsFromKg() {
        assertEquals(66.139, entry1.convertTolbs(30, "kg"), 0.01);
    }

    @Test
    public void testConvertToLbsFromStone() {
        assertEquals(140, entry1.convertTolbs(10, "stone"), 0.01);
    }

    @Test
    public void testConvertToKgFromLbs() {
        assertEquals(45.359, entry1.convertToKg(100, "lbs"), 0.01);
    }

    @Test
    public void testConvertToKgFromKg() {
        assertEquals(100, entry1.convertToKg(100, "kg"), 0.01);
    }

    @Test
    public void testConvertToKgFromStone() {
        assertEquals(63.503, entry1.convertToKg(10, "stone"), 0.01);
    }

    @Test
    public void testConvertToStoneFromLbs() {
        assertEquals(14.286, entry1.convertToStone(200, "lbs"), 0.01);
    }

    @Test
    public void testConvertToStoneFromKg() {
        assertEquals(9.448, entry1.convertToStone(60, "kg"), 0.01);
    }

    @Test
    public void testConvertToStoneFromStone() {
        assertEquals(60, entry1.convertToStone(60, "stone"), 0.01);
    }
}
