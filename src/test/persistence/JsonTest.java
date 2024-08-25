package persistence;

import model.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests the methods in the JsonTest Class
// Portions of code received from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {

    protected void checkWeightEntry(WeightEntry entry1, double lbs, double kg, double stone) {
        assertEquals(lbs, entry1.getWeight("lbs"));
        assertEquals(kg, entry1.getWeight("kg"));
        assertEquals(stone, entry1.getWeight("stone"));
    }

    protected void checkMealEntry (MealEntry me, String foods, String location, String mealType, String stressLevel,
                                   String worry, String worryResponse, String otherThoughts) {
        assertEquals(foods, me.getFoods());
        assertEquals(location, me.getLocation());
        assertEquals(mealType, me.getMealType());
        assertEquals(stressLevel, me.getStressLevel());
        assertEquals(worry, me.getWorry());
        assertEquals(worryResponse, me.getWorryResponse());
        assertEquals(otherThoughts, me.getOtherThoughts());
    }

    //EFFECTS: Checks the values in the checkInEntry except for the checklist
    protected void checkCheckInEntry(CheckInEntry entry, double weightEstimate, double weight, String thoughts,
                                     String goal1, boolean goal1Complete, String goal2, boolean goal2Complete) {
        assertEquals(weightEstimate, entry.getWeightEstimate());
        assertEquals(weight, entry.getWeight());
        assertEquals(thoughts, entry.getThoughts());
        assertEquals(goal1, entry.getGoal1().getDescription());
        assertEquals(goal1Complete, entry.getGoal1().getChecked());
        assertEquals(goal2, entry.getGoal2().getDescription());
        assertEquals(goal2Complete, entry.getGoal2().getChecked());
    }

    protected void checkCheckList(CheckList checkList, boolean nep, boolean fv, boolean noe, boolean nb, boolean np,
                                  boolean nbc, boolean nuwi, boolean mw, boolean nsa, boolean nusc) {
        ArrayList<CheckInTask> tasks = checkList.getTaskList();
        assertEquals(nep, tasks.get(0).getChecked());
        assertEquals(fv, tasks.get(1).getChecked());
        assertEquals(noe, tasks.get(2).getChecked());
        assertEquals(nb, tasks.get(3).getChecked());
        assertEquals(np, tasks.get(4).getChecked());
        assertEquals(nbc, tasks.get(5).getChecked());
        assertEquals(nuwi, tasks.get(6).getChecked());
        assertEquals(mw, tasks.get(7).getChecked());
        assertEquals(nsa, tasks.get(8).getChecked());
        assertEquals(nusc, tasks.get(9).getChecked());
    }
}
