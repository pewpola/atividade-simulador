package model.algorithms;

import java.util.HashMap;
import java.util.Map;

public class Aging implements PageReplacement {
    
    @Override
    public int run(int[] referenceString, int frameCount) {
        Map<Integer, Integer> agingCounters = new HashMap<>();
        int pageFaults = 0;
        
        for (int i = 0; i < referenceString.length; i++) {
            int page = referenceString[i];
            
            if (agingCounters.containsKey(page)) {
                agingCounters.put(page, agingCounters.get(page) | 0x80);
                continue;
            }
            
            pageFaults++;
            
            if (agingCounters.size() >= frameCount) {
                int pageToRemove = findPageToRemove(agingCounters);
                agingCounters.remove(pageToRemove);
            }
            
            agingCounters.put(page, 0x80);
            
            // Simula o envelhecimento periódico (a cada 8 referências)
            if (i % 8 == 0) {
                ageCounters(agingCounters);
            }
        }
        return pageFaults;
    }
    
    private void ageCounters(Map<Integer, Integer> counters) {
        counters.replaceAll((k, v) -> v >> 1);
    }
    
    private int findPageToRemove(Map<Integer, Integer> counters) {
        return counters.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow();
    }

    @Override
    public String getName() {
        return "Aging";
    }

    @Override
    public String getDescription() {
        return "Implementação em software do LRU usando contadores de envelhecimento. Excelente para sistemas sem suporte hardware para LRU.";
    }
}