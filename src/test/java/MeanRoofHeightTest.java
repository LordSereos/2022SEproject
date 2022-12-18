import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MeanRoofHeightTest {
    @BeforeAll
    public static void initializeDataFromJson() {
        var data = new ReadFromJSON();
        //specify data source
        data.readPoints("./JSON_files/Data.json");
    }

    @Test
    void meanHeightResultIsNotNegative(ArrayList<Edge> roofs) {
        var meanRoofHeight = new MeanRoofHeight();
        assertTrue(meanRoofHeight.getMeanHeight(roofs) >= 0);
    }

    @Test
    void meanHeightResultIsCorrectlyCalculated(ArrayList<Edge> roof_edges) {
        var meanRoofHeight = new MeanRoofHeight();
        assertEquals(19.688975720000002, meanRoofHeight.getMeanHeight(roof_edges));
    }

    @Test
    void shouldReturnHighestCoordinate(ArrayList<Edge> roof_edges) {
        var meanRoofHeight = new MeanRoofHeight();
        assertEquals(29.53795144, meanRoofHeight.getHeighestZCoordinate(roof_edges));

    }

    @Test
    void shouldReturnLowestCoordinate(ArrayList<Edge> roof_edges) {
        var meanRoofHeight = new MeanRoofHeight();
        assertEquals(9.84, meanRoofHeight.getLowestZCoordinate(roof_edges));
    }

    @Test
    void windZoneWidthShouldBeHigherOrEqualTo3(ArrayList<Edge> roof_edges) {
        var meanRoofHeight = new MeanRoofHeight();
        assertTrue(meanRoofHeight.getWidthOfWindZone(roof_edges) >= 3.0);
    }
}