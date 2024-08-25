package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Tests the methods in the WeightLog Class
public class WeightLogTest {

    private WeightLog weightLog;
    private WeightLog noRangeLog;
    private WeightEntry minEntry;
    private WeightEntry maxEntry;
    private WeightEntry overweightEntry;
    private WeightEntry underweightEntry;
    private WeightEntry healthyEntry;

    @BeforeEach
    public void setup() {
        weightLog = new WeightLog();
        noRangeLog = new WeightLog();
        weightLog.setHealthyRange(150, 165);
        minEntry = new WeightEntry(150, "lbs");
        maxEntry = new WeightEntry(165, "lbs");
        overweightEntry = new WeightEntry(166, "lbs");
        underweightEntry = new WeightEntry(149, "lbs");
        healthyEntry = new WeightEntry(155, "lbs");
    }

    @Test
    public void testSetRange() {
        WeightLog wl = new WeightLog();
        wl.setSetRange(true);
        assertTrue(weightLog.rangeExists());
    }

    @Test
    public void testSetHealthyRange() {
        WeightLog wl = new WeightLog();
        wl.setHealthyRange(minEntry, maxEntry);
        assertTrue(weightLog.rangeExists());
        assertEquals(minEntry.getWeight("lbs"), weightLog.getMinHealthyWeight());
        assertEquals(maxEntry.getWeight("lbs"), weightLog.getMaxHealthyWeight());
    }

    @Test
    public void testConstructor() {
        ArrayList<WeightEntry> entries = new ArrayList<>();
        entries.add(overweightEntry);
        WeightEntry min = new WeightEntry(50, "kg");
        WeightEntry max = new WeightEntry(55, "kg");

        WeightLog weightLog = new WeightLog("kg", entries, min, max, true);
        assertEquals("kg", weightLog.getWeightSystem());
        assertEquals(1, weightLog.getWeightEntries().size());
        assertEquals(min.getWeight("kg"), weightLog.getMinHealthyWeight());
        assertEquals(max.getWeight("kg"), weightLog.getMaxHealthyWeight());
        assertTrue(weightLog.rangeExists());
    }

    @Test
    public void testAddEntryOneEntry() {
        assertEquals(0, weightLog.getWeightEntries().size());
        weightLog.addEntry(minEntry);
        assertEquals(1, weightLog.getWeightEntries().size());
        assertEquals(minEntry, weightLog.getWeightEntries().get(0));
    }

    @Test
    public void testAddEntryMultipleEntries() {
        assertEquals(0, weightLog.getWeightEntries().size());
        weightLog.addEntry(minEntry);
        weightLog.addEntry(overweightEntry);
        assertEquals(2, weightLog.getWeightEntries().size());
        assertEquals(minEntry, weightLog.getWeightEntries().get(0));
        assertEquals(overweightEntry, weightLog.getWeightEntries().get(1));
    }

    @Test
    public void testIsHealthyMinWeight() {
        assertTrue(weightLog.isHealthy(minEntry));
    }

    @Test
    public void testIsHealthyMaxWeight() {
        assertTrue(weightLog.isHealthy(maxEntry));
    }

    @Test
    public void testIsHealthyHealthyWeight() {
        assertTrue(weightLog.isHealthy(healthyEntry));
    }

    @Test
    public void testIsHealthyUnhealthyWeights() {
        assertFalse(weightLog.isHealthy(overweightEntry));
        assertFalse(weightLog.isHealthy(underweightEntry));
    }

    @Test
    public void testRangeExistsFalse() {
        assertFalse(noRangeLog.rangeExists());
    }

    @Test
    public void testRangeExistsTrue() {
        assertTrue(weightLog.rangeExists());
    }

    @Test
    public void testSetHealthyRangeDifferentValues() {
        assertFalse(noRangeLog.rangeExists());
        noRangeLog.setHealthyRange(100, 120);
        assertTrue(weightLog.rangeExists());
        assertEquals(100, noRangeLog.getMinHealthyWeight());
        assertEquals(120, noRangeLog.getMaxHealthyWeight());
    }

    @Test
    public void testSetHealthyRangeSameValue() {
        assertFalse(noRangeLog.rangeExists());
        noRangeLog.setHealthyRange(100, 100);
        assertTrue(weightLog.rangeExists());
        assertEquals(100, noRangeLog.getMinHealthyWeight());
        assertEquals(100, noRangeLog.getMaxHealthyWeight());
    }

    @Test
    public void testSetHealthyRangeRangeExists() {
        assertTrue(weightLog.rangeExists());
        weightLog.setHealthyRange(190, 200);
        assertTrue(weightLog.rangeExists());
        assertEquals(weightLog.getMinHealthyWeight(), 190);
        assertEquals(weightLog.getMaxHealthyWeight(), 200);
    }

    @Test
    public void testGetWeightSystem() {
        assertEquals("lbs", weightLog.getWeightSystem());
    }

    @Test
    public void testSetWeightSystem() {
        weightLog.setWeightSystem("kg");
        assertEquals("kg", weightLog.getWeightSystem());
    }
}

