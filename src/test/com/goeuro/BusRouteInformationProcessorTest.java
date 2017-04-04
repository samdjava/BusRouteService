package com.goeuro;

import com.goeuro.job.BusRouteInformationProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by root on 2/4/17.
 */
public class BusRouteInformationProcessorTest {

    BusRouteInformationProcessor busRouteInformationProcessor = new BusRouteInformationProcessor();
    final String tempFileName = "a.txt";
    final File tmpFile = new File("a.txt");

    @Before
    public void setup(){
        if(tmpFile.exists())
            tmpFile.delete();
        String fileName = "test";
        String content = "3\n1 2 5 6 7\n2 4 6";
        BufferedWriter bw = null;
        try {
            tmpFile.createNewFile();
            bw = new BufferedWriter(new FileWriter(tmpFile));
            // when(new File(fileName)).thenReturn(tmpFile);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            System.out.println("[BusRouteInformationProcessorTest] Testing issue for file creation");
        }
    }

    @Test
    public void processTest(){
        try {
            Map<Integer,Set<Integer>> busRouteMap = busRouteInformationProcessor.process(tempFileName);
            boolean result = busRouteMap.get(2).contains(7);
            assertEquals(true, result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @After
    public void cleanUp() {
        if(tmpFile.exists())
            tmpFile.delete();
    }
}
