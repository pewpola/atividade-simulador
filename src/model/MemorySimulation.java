package model;

import model.algorithms.PageReplacement;
import java.util.Arrays;

public class MemorySimulation {
    private final int[] referenceString;
    private final int frameCount;
    private final PageReplacement[] algorithms;
    
    public MemorySimulation(int[] referenceString, int frameCount, PageReplacement[] algorithms) {
        this.referenceString = Arrays.copyOf(referenceString, referenceString.length);
        this.frameCount = frameCount;
        this.algorithms = algorithms;
    }
    
    public SimulationResult[] runSimulations() {
        SimulationResult[] results = new SimulationResult[algorithms.length];
        
        for (int i = 0; i < algorithms.length; i++) {
            PageReplacement algorithm = algorithms[i];
            int pageFaults = algorithm.run(referenceString, frameCount);
            results[i] = new SimulationResult(algorithm.getName(), pageFaults, algorithm.getDescription());
        }
        
        return results;
    }
    
    public static class SimulationResult {
        private final String algorithmName;
        private final int pageFaults;
        private final String description;
        
        public SimulationResult(String algorithmName, int pageFaults, String description) {
            this.algorithmName = algorithmName;
            this.pageFaults = pageFaults;
            this.description = description;
        }
        
        // Getters
        public String getAlgorithmName() { return algorithmName; }
        public int getPageFaults() { return pageFaults; }
        public String getDescription() { return description; }
    }
}