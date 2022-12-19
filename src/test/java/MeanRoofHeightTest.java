import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MeanRoofHeightTest {
    @Test
    void meanHeightResultIsNotNegative() {
        var meanRoofHeight = new MeanRoofHeight();
        var data = new ReadFromJSON();
        ArrayList<Edge> roof_edges = data.readPoints("./JSON_files/data2.json");
        assertTrue(meanRoofHeight.getMeanHeight(roof_edges) >= 0);
    }

    @Test
    void windZoneWidthShouldBeHigherOrEqualTo3() {
        var meanRoofHeight = new MeanRoofHeight();
        var data = new ReadFromJSON();
        ArrayList<Edge> roof_edges = data.readPoints("./JSON_files/data2.json");
        assertTrue(meanRoofHeight.getWidthOfWindZone(roof_edges) >= 3.0);
    }

    @Test
    void meanHeightResultIsCorrectlyCalculated() {
        var meanRoofHeight = new MeanRoofHeight();
        var data = new ReadFromJSON();
        ArrayList<Edge> roof_edges = data.readPoints("./JSON_files/data2.json");
        assertEquals(19.689, meanRoofHeight.getMeanHeight(roof_edges));
    }

    @Test
    void shouldReturnHighestCoordinate() {
        var meanRoofHeight = new MeanRoofHeight();
        var data = new ReadFromJSON();
        ArrayList<Edge> roof_edges = data.readPoints("./JSON_files/data2.json");
        assertEquals(29.538, meanRoofHeight.getHeighestZCoordinate(roof_edges));

    }

    @Test
    void shouldReturnLowestCoordinate() {
        var meanRoofHeight = new MeanRoofHeight();
        var data = new ReadFromJSON();
        ArrayList<Edge> roof_edges = data.readPoints("./JSON_files/data2.json");
        assertEquals(9.84, meanRoofHeight.getLowestZCoordinate(roof_edges));
    }


}