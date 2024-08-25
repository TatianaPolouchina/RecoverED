package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests the methods in the MealLog Class
public class MealLogTest {

    private MealLog emptyLog;
    private MealEntry entry1;
    private MealEntry entry2;

    @BeforeEach
    public void setup() {
        emptyLog = new MealLog();
        setupMealEntry1();
        setupMealEntry2();
    }

    public void setupMealEntry1() {
        entry1 = new MealEntry();
        entry1.setFoods("Banana");
        entry1.setLocation("Home");
        entry1.setMealType("Morning Snack");
        entry1.setStressLevel("Low");
        entry1.setWorry("Worried about xyz");
        entry1.setWorryResponse("I should remember xyz");
        entry1.setOtherThoughts("Thinking about xyz");
    }

    public void setupMealEntry2() {
        entry2 = new MealEntry();
        entry2.setFoods("Cookies");
        entry2.setLocation("School");
        entry2.setMealType("Afternoon snack");
        entry2.setStressLevel("High");
    }

    @Test
    public void testAddEntryEmpty() {
        assertEquals(0, emptyLog.getMealEntries().size());
        emptyLog.addEntry(entry1);
        assertEquals(1, emptyLog.getMealEntries().size());
        assertEquals(entry1, emptyLog.getMealEntries().get(0));
    }

    @Test
    public void testAddEntryMultipleEntries() {
        assertEquals(0, emptyLog.getMealEntries().size());
        emptyLog.addEntry(entry1);
        emptyLog.addEntry(entry2);
        assertEquals(2, emptyLog.getMealEntries().size());
        assertEquals(entry1, emptyLog.getMealEntries().get(0));
        assertEquals(entry2, emptyLog.getMealEntries().get(1));
    }
}
