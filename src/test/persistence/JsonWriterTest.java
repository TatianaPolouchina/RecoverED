package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Tests the methods in the JsonWriter Class
// Portions of code received from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterAllEmpty() {
        try {
            WeightLog wl = new WeightLog();
            MealLog ml = new MealLog();
            CheckInLog cil = new CheckInLog();

            JsonWriter writer = new JsonWriter("./data/testWriterAllEmpty.json");
            writer.open();
            writer.write(wl, ml, cil);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterAllEmpty.json");
            ml = reader.readMealLog();
            assertEquals(0, ml.getMealEntries().size());
            cil = reader.readCheckInLog();
            assertEquals(0, cil.getCheckInEntries().size());
            wl = reader.readWeightLog();
            assertEquals("lbs", wl.getWeightSystem());
            assertEquals(0, wl.getWeightEntries().size());
            assertEquals(0, wl.getMinHealthyWeight());
            assertEquals(0, wl.getMaxHealthyWeight());
            assertFalse(wl.rangeExists());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
    @Test
    void testWriterEmptyMealLog() {
        try {
            MealLog ml = new MealLog();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMealLog.json");
            writer.open();
            writer.writeMealLog(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMealLog.json");
            ml = reader.readMealLog();
            assertEquals(0, ml.getMealEntries().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyCheckInLog() {
        try {
            CheckInLog cil = new CheckInLog();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCheckInLog.json");
            writer.open();
            writer.writeCheckInLog(cil);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCheckInLog.json");
            cil = reader.readCheckInLog();
            assertEquals(0, cil.getCheckInEntries().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyWeightLog() {
        try {
            WeightLog wl = new WeightLog();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWeightLog.json");
            writer.open();
            writer.writeWeightLog(wl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWeightLog.json");
            wl = reader.readWeightLog();

            assertEquals("lbs", wl.getWeightSystem());
            assertEquals(0, wl.getWeightEntries().size());
            assertEquals(0, wl.getMinHealthyWeight());
            assertEquals(0, wl.getMaxHealthyWeight());
            assertFalse(wl.rangeExists());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWeightLog() {
        try {
            WeightLog wl = new WeightLog();
            wl.setHealthyRange(150, 165);
            WeightEntry we1 = new WeightEntry(149, "lbs");
            WeightEntry we2 = new WeightEntry(155, "lbs");
            wl.addEntry(we1);
            wl.addEntry(we2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWeightLog.json");
            writer.open();
            writer.writeWeightLog(wl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWeightLog.json");
            wl = reader.readWeightLog();

            assertEquals(150, wl.getMinHealthyWeight());
            assertEquals(165, wl.getMaxHealthyWeight());
            assertEquals(2, wl.getWeightEntries().size());

            WeightEntry entry1 = wl.getWeightEntries().get(0);
            WeightEntry entry2 = wl.getWeightEntries().get(1);
            checkWeightEntry(entry1, 149, we1.getWeight("kg"),
                    we1.getWeight("stone"));
            checkWeightEntry(entry2, 155, we2.getWeight("kg"),
                    we2.getWeight("stone"));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMealLog() {
        try {
            MealLog ml = setupMealLog();

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMealLog.json");
            writer.open();
            writer.writeMealLog(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMealLog.json");
            ml = reader.readMealLog();

            assertEquals(2, ml.getMealEntries().size());
            MealEntry me1 = ml.getMealEntries().get(0);
            MealEntry me2 = ml.getMealEntries().get(1);
            checkMealEntry(me1, "Banana", "Home", "Morning Snack", "Low",
                    "Worried about xyz", "I should remember xyz", "Thinking about xyz");
            checkMealEntry(me2, "Cookies", "School", "Afternoon Snack", "High",
                    "", "", "");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    MealLog setupMealLog() {
        MealLog mealLog = new MealLog();
        MealEntry entry1 = setupMealEntry1();
        MealEntry entry2 = setupMealEntry2();
        mealLog.addEntry(entry1);
        mealLog.addEntry(entry2);
        return mealLog;
    }

    public MealEntry setupMealEntry1() {
        MealEntry entry = new MealEntry();
        entry.setFoods("Banana");
        entry.setLocation("Home");
        entry.setMealType("Morning Snack");
        entry.setStressLevel("Low");
        entry.setWorry("Worried about xyz");
        entry.setWorryResponse("I should remember xyz");
        entry.setOtherThoughts("Thinking about xyz");
        return entry;
    }

    public MealEntry setupMealEntry2() {
        MealEntry entry2 = new MealEntry();
        entry2.setFoods("Cookies");
        entry2.setLocation("School");
        entry2.setMealType("Afternoon Snack");
        entry2.setStressLevel("High");
        return entry2;
    }

    @Test
    void testWriterGeneralCheckInLog() {
        try {
            CheckInLog cil = setupCheckInLog();

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCheckInLog.json");
            writer.open();
            writer.writeCheckInLog(cil);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCheckInLog.json");
            cil = reader.readCheckInLog();

            assertEquals(2, cil.getCheckInEntries().size());

            CheckInEntry entry1 = cil.getCheckInEntries().get(0);
            CheckInEntry entry2 = cil.getCheckInEntries().get(1);
            checkCheckInEntry(entry1, 100, 105, "Thoughts", "Avoid social media",
                    true, "Practice positive self-talk", true);
            checkCheckList(entry1.getCheckList(), true, true, true, true, true, true,
                    true, true, true, true);
            checkCheckInEntry(entry2, 200, 185, "Many thoughts", "Do this",
                    true, "Do that", false);
            checkCheckList(entry2.getCheckList(), false, false, false, false, false, true,
                    true, true, true, true);


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    CheckInLog setupCheckInLog() {
        CheckInEntry entry1 = setupEntry1();
        CheckInEntry entry2 = setupEntry2();
        CheckInLog log = new CheckInLog();
        log.addEntry(entry1);
        log.addEntry(entry2);
        return log;
    }

    public CheckInEntry setupEntry1() {
        CheckInEntry entry1 = new CheckInEntry();
        entry1.setWeightEstimate(100);
        entry1.setWeight(105);
        entry1.setThoughts("Thoughts");
        CheckList checkList = entry1.getCheckList();

        checkList.setNormalizedEatingPattern(true);
        checkList.setFoodVariety(true);
        checkList.setNoOverExercising(true);
        checkList.setNoBinging(true);
        checkList.setNoPurging(true);
        checkList.setNoBodyChecking(true);
        checkList.setNoUntimelyWeighIns(true);
        checkList.setMaintainWeight(true);
        checkList.setNoShapeAvoidance(true);
        checkList.setNoUnhelpfulShapeContributions(true);

        entry1.createGoal1("Avoid social media");
        entry1.createGoal2("Practice positive self-talk");
        entry1.getGoal1().setChecked(true);
        entry1.getGoal2().setChecked(true);
        return entry1;
    }

    public CheckInEntry setupEntry2() {
        CheckInEntry entry2 = new CheckInEntry();
        entry2.setWeightEstimate(200);
        entry2.setWeight(185);
        entry2.setThoughts("Many thoughts");
        CheckList checkList = entry2.getCheckList();

        checkList.setNormalizedEatingPattern(false);
        checkList.setFoodVariety(false);
        checkList.setNoOverExercising(false);
        checkList.setNoBinging(false);
        checkList.setNoPurging(false);
        checkList.setNoBodyChecking(true);
        checkList.setNoUntimelyWeighIns(true);
        checkList.setMaintainWeight(true);
        checkList.setNoShapeAvoidance(true);
        checkList.setNoUnhelpfulShapeContributions(true);

        entry2.createGoal1("Do this");
        entry2.createGoal2("Do that");
        entry2.getGoal1().setChecked(true);
        return entry2;
    }
}
