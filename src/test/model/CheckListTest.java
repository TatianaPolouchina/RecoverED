package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests the methods in the CheckList Class
public class CheckListTest {

    private CheckList checkList;
    private ArrayList<CheckInTask> taskList;

    @BeforeEach
    public void setup() {
        checkList = new CheckList();
        taskList = new ArrayList<>();
        CheckInTask normalizedEatingPattern = new CheckInTask();
        normalizedEatingPattern.setDescription("Maintaining a normalized eating pattern");
        taskList.add(normalizedEatingPattern);
        checkList.setTaskList(taskList);
    }

    @Test
    public void testGetTaskList() {
        assertEquals(taskList, checkList.getTaskList());
    }
}
