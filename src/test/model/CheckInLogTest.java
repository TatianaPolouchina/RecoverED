package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests the methods in the CheckInLog Class
public class CheckInLogTest {

    private CheckInEntry entry1;
    private CheckInEntry entry2;
    private CheckInEntry entry3;
    private CheckInLog log;
    private CheckInLog oneEntryLog;
    private CheckInLog emptyLog;

    @BeforeEach
    public void setup() {
        setupEntry1();
        setupEntry2();
        setupEntry3();
        log = new CheckInLog();
        oneEntryLog = new CheckInLog();
        emptyLog = new CheckInLog();

        oneEntryLog.addEntry(entry1);

        log.addEntry(entry1);
        log.addEntry(entry2);
        log.addEntry(entry3);
    }

    public void setupEntry1() {
        entry1 = new CheckInEntry();
        entry1.setWeightEstimate(100);
        entry1.setWeight(105);
        entry1.setThoughts("Thoughts thoughts");
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
    }

    public void setupEntry2() {
        entry2 = new CheckInEntry();
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
    }

    public void setupEntry3() {
        entry3 = new CheckInEntry();
        entry3.setWeightEstimate(130);
        entry3.setWeight(120);
        entry3.setThoughts("thoughts thoughts thoughts");
        CheckList checkList = entry3.getCheckList();

        checkList.setNormalizedEatingPattern(false);
        checkList.setFoodVariety(true);
        checkList.setNoOverExercising(true);
        checkList.setNoBinging(false);
        checkList.setNoPurging(false);
        checkList.setNoBodyChecking(true);
        checkList.setNoUntimelyWeighIns(false);
        checkList.setMaintainWeight(true);
        checkList.setNoShapeAvoidance(true);
        checkList.setNoUnhelpfulShapeContributions(true);

        entry3.createGoal1("xyz");
        entry3.createGoal2("lmnop");
    }

    @Test
    public void testAddEntryEmpty() {
        assertEquals(0, emptyLog.getCheckInEntries().size());
        emptyLog.addEntry(entry1);
        assertEquals(1, emptyLog.getCheckInEntries().size());
        assertEquals(entry1, emptyLog.getCheckInEntries().get(0));
    }

    @Test
    public void testAddEntryMultipleEntries() {
        assertEquals(0, emptyLog.getCheckInEntries().size());
        emptyLog.addEntry(entry1);
        emptyLog.addEntry(entry2);
        assertEquals(2, emptyLog.getCheckInEntries().size());
        assertEquals(entry1, emptyLog.getCheckInEntries().get(0));
        assertEquals(entry2, emptyLog.getCheckInEntries().get(1));
    }

    @Test
    public void testAverageWeightDifferenceOneEntry() {
        double weightDiff = oneEntryLog.averageWeightDifference(1,1);
        assertEquals(5, weightDiff);
    }

    @Test
    public void testAverageWeightDifferenceMultipleEntries() {
        double weightDiff = log.averageWeightDifference(1,2);
        assertEquals(10, weightDiff);
    }

    @Test
    public void testAverageGoalsCompletedOneEntry() {
        double avgGoalsCompleted = log.averageGoalsCompleted(1,1);
        assertEquals(2, avgGoalsCompleted);
    }

    @Test
    public void testAverageGoalsCompletedMultipleEntries() {
        double avgGoalsCompleted = log.averageGoalsCompleted(1,2);
        assertEquals(1.5, avgGoalsCompleted);
    }

    @Test
    public void testTotalGoalsCompletedOneEntry() {
        double totalGoalsCompleted = log.totalGoalsCompleted(1,1);
        assertEquals(2, totalGoalsCompleted);
    }

    @Test
    public void testTotalGoalsCompletedMultipleEntries() {
        double totalGoalsCompleted = log.totalGoalsCompleted(1,2);
        assertEquals(3, totalGoalsCompleted);
    }

    @Test
    public void testAverageGoalsCompletedWithoutRecentOnlyRecent() {
        double avg = log.averageGoalsCompletedWithoutRecent(3,3, 3);
        assertEquals(avg, -1);
    }

    @Test
    public void testAverageGoalsCompletedWithoutRecentNoRecentGoal() {
        double avg = log.averageGoalsCompletedWithoutRecent(1,2, 3);
        assertEquals(avg, 1.5);
    }

    @Test
    public void testAverageGoalsCompletedWithoutRecentMixed() {
        double avg = log.averageGoalsCompletedWithoutRecent(1,3, 3);
        assertEquals(avg, 1.5);
    }

    @Test
    public void testTotalGoalsCompletedMultipleEntriesAllComplete() {
        entry2.getGoal2().setChecked(true);
        double totalGoalsCompleted = log.totalGoalsCompleted(1,2);
        assertEquals(4, totalGoalsCompleted);
    }

}
