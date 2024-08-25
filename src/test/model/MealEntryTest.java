package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests the methods in the MealEntry Class
public class MealEntryTest {

    private MealEntry entry;

    @BeforeEach
    public void setup() {
        entry = new MealEntry();
        entry.setFoods("Oranges, Bananas");
        entry.setMealType("Morning Snack");
        entry.setLocation("Bedroom");
        entry.setStressLevel("Low");
        entry.setWorry("Worrying about...");
        entry.setWorryResponse("Don't worry");
        entry.setOtherThoughts("thoughts thoughts");
    }

    @Test
    public void testGetFoods() {
        assertEquals("Oranges, Bananas", entry.getFoods());
    }

    @Test
    public void testGetMealType() {
        assertEquals("Morning Snack", entry.getMealType());
    }

    @Test
    public void testGetLocation() {
        assertEquals("Bedroom", entry.getLocation());
    }

    @Test
    public void testGetStressLevel() {
        assertEquals("Low", entry.getStressLevel());
    }

    @Test
    public void testGetWorry() {
        assertEquals("Worrying about...", entry.getWorry());
    }

    @Test
    public void testGetWorryResponse() {
        assertEquals("Don't worry", entry.getWorryResponse());
    }

    @Test
    public void testGetOtherThoughts() {
        assertEquals("thoughts thoughts", entry.getOtherThoughts());
    }
}
