package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Tests the methods in the JsonReader Class
// Portions of code received from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            WeightLog weightLog = reader.readWeightLog();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMealLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMealLog.json");
        try {
            MealLog ml = reader.readMealLog();
            assertEquals(0, ml.getMealEntries().size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyCheckInLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCheckInLog.json");
        try {
            CheckInLog cil = reader.readCheckInLog();
            assertEquals(0, cil.getCheckInEntries().size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyWeightLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWeightLog.json");
        try {
            WeightLog wl = reader.readWeightLog();
            assertEquals("lbs", wl.getWeightSystem());
            assertEquals(0, wl.getWeightEntries().size());
            assertEquals(0, wl.getMinHealthyWeight());
            assertEquals(0, wl.getMaxHealthyWeight());
            assertFalse(wl.rangeExists());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMealLog() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMealLog.json");
        try {
            MealLog ml = reader.readMealLog();

            assertEquals(2, ml.getMealEntries().size());
            MealEntry me1 = ml.getMealEntries().get(0);
            MealEntry me2 = ml.getMealEntries().get(1);
            checkMealEntry(me1, "Apple", "Home", "Afternoon Snack", "Low",
                    "Worried about abc", "I should remember that...", "Thinking about xyz");
            checkMealEntry(me2, "Ice cream", "School", "Afternoon Snack", "High",
                    "", "", "");
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCheckInLog() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCheckInLog.json");
        try {
            CheckInLog cil = reader.readCheckInLog();

            assertEquals(2, cil.getCheckInEntries().size());

            CheckInEntry entry1 = cil.getCheckInEntries().get(0);
            CheckInEntry entry2 = cil.getCheckInEntries().get(1);
            checkCheckInEntry(entry1, 100, 107, "Thoughts...", "Stop...",
                    true, "Start...", false);
            checkCheckList(entry1.getCheckList(), true, true, true, true, true, true,
                    true, true, true, true);
            checkCheckInEntry(entry2, 205, 199, "Many thoughts", "Do this",
                    true, "Do that", false);
            checkCheckList(entry2.getCheckList(), false, false, false, false, false, true,
                    true, true, true, true);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWeightLog() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWeightLog.json");
        try {
            WeightLog wl = reader.readWeightLog();

            assertEquals(150, wl.getMinHealthyWeight());
            assertEquals(165, wl.getMaxHealthyWeight());
            assertEquals(2, wl.getWeightEntries().size());

            WeightEntry entry1 = wl.getWeightEntries().get(0);
            WeightEntry entry2 = wl.getWeightEntries().get(1);
            checkWeightEntry(entry1, 149, 68, 11);
            checkWeightEntry(entry2, 200, 90.7, 14.3);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
