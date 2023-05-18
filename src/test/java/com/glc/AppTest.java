package com.glc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void totalWorldPop() throws IOException {
    String testFilePath = "test_population_data.csv";
    List<String> testDataLines = Arrays.asList(
                "City,State,Country,Continent,Population",
                "New York,New York,USA,North America,8623000",
                "Los Angeles,California,USA,North America,3994000",
                "Chicago,Illinois,USA,North America,2719000");
        Files.write(Paths.get(testFilePath), testDataLines, StandardCharsets.ISO_8859_1);

        
        List<String> testPopData = Files.readAllLines(Paths.get(testFilePath), StandardCharsets.ISO_8859_1);
        ParseData parser = new ParseData(testPopData);
        int totalPop = parser.sum(parser.getPopData());

        assertEquals(49997236, totalPop);

        Files.deleteIfExists(Paths.get(testFilePath));
    }

    /**
     * 
     */
    @Test
    public void parsingLines() {
        String line = "New York,United States,North America,8398748";
        PopData expected = new PopData("New York", "United States", "North America",
            8398748L);
    
        PopData actual = PopData.parseFromLine(line);
    
        assertEquals(expected.getPopData(), actual.getPopData());
    }

    @Test
    public void checkCsv(){
        String fileNotExists = "notExistant_population_data.csv";
        assertThrows(IOException.class, () -> {
            List<String> testPopData = Files.readAllLines(Paths.get(fileNotExists), StandardCharsets.ISO_8859_1);

        });


    }

}
