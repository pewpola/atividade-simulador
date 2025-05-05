package controller;

import model.MemorySimulation;
import model.algorithms.*;
import model.MemorySimulation.SimulationResult;
import java.util.Arrays;

public class SimulationController {
    public SimulationResult[] simulate(int[] referenceString, int frameCount) {
        PageReplacement[] algorithms = {
            new FIFO(),
            new LRU(),
            new Clock(),
            new Aging()
        };
        
        MemorySimulation simulation = new MemorySimulation(referenceString, frameCount, algorithms);
        return simulation.runSimulations();
    }
    
    public static int[] parseReferenceString(String input) {
        return Arrays.stream(input.split("[,\\s]+"))
                   .map(String::trim)
                   .filter(s -> !s.isEmpty())
                   .mapToInt(Integer::parseInt)
                   .toArray();
    }
}