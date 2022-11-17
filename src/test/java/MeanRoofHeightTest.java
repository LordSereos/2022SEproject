import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeanRoofHeightTest {
    @BeforeAll
    public static void initializeDataFromJson() {
        var data = new ReadFromJSON();
        //specify data source
        data.readPoints("./JSON_files/Data.json");
    }

    @Test
    void meanHeightResultIsNotNegative() {
        var meanRoofHeight = new MeanRoofHeight();
        assertTrue(meanRoofHeight.getMeanHeight() >= 0);
    }

    @Test
    void meanHeightResultIsCorrectlyCalculated() {
        var meanRoofHeight = new MeanRoofHeight();
        assertEquals(19.688975720000002, meanRoofHeight.getMeanHeight());
    }

    @Test
    void shouldReturnHighestCoordinate() {
        var meanRoofHeight = new MeanRoofHeight();
        assertEquals(29.53795144, meanRoofHeight.getHeighestZCoordinate());

    }

    @Test
    void shouldReturnLowestCoordinate() {
        var meanRoofHeight = new MeanRoofHeight();
        assertEquals(9.84, meanRoofHeight.getLowestZCoordinate());
    }

    @Test
    void windZoneWidthShouldBeHigherOrEqualTo3() {
        var meanRoofHeight = new MeanRoofHeight();
        assertTrue(meanRoofHeight.getWidthOfWindZone() >= 3.0);
    }
}