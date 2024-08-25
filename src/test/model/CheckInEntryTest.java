package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests the methods in the CheckInEntry Class
public class CheckInEntryTest {

    private CheckInEntry emptyEntry;
    private CheckInEntry entry;

    @BeforeEach
    public void setup() {
        emptyEntry = new CheckInEntry();
        entry = new CheckInEntry();
        entry.createGoal1("This");
        entry.createGoal2("and that");
    }

    @Test
    public void testCreateGoal1() {
        assertNull(emptyEntry.getGoal1());
        emptyEntry.createGoal1("Do this");
        assertEquals("Do this", emptyEntry.getGoal1().getDescription());
        assertFalse(emptyEntry.getGoal1().getChecked());
    }

    @Test
    public void testCreateGoal2() {
        assertNull(emptyEntry.getGoal2());
        emptyEntry.createGoal2("Do that");
        assertEquals("Do that", emptyEntry.getGoal2().getDescription());
        assertFalse(emptyEntry.getGoal2().getChecked());
    }

    @Test
    public void testCreateGoalGoalExists() {
        emptyEntry.createGoal2("Do this");
        assertEquals("Do this", emptyEntry.getGoal2().getDescription());
        emptyEntry.createGoal2("Do that");
        assertEquals("Do that", emptyEntry.getGoal2().getDescription());
    }

    @Test
    public void testNumGoalsCompletedNoneCompleted() {
        assertEquals(entry.numGoalsCompleted(),0);
    }

    @Test
    public void testNumGoalsCompletedOneCompleted() {
        entry.getGoal1().setChecked(true);
        assertEquals(entry.numGoalsCompleted(),1);
    }

    @Test
    public void testNumGoalsCompletedAllCompleted() {
        entry.getGoal1().setChecked(true);
        entry.getGoal2().setChecked(true);
        assertEquals(entry.numGoalsCompleted(),2);
    }

    @Test
    public void testGetThoughts() {
        entry.setThoughts("Thinking...");
        assertEquals("Thinking...", entry.getThoughts());
    }
}
