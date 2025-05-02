package model.algorithms;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU implements PageReplacement {
    
    @Override
    public int run(int[] referenceString, int frameCount) {
        LinkedHashMap<Integer, Boolean> frames = new LinkedHashMap<>(frameCount, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Boolean> eldest) {
                return size() > frameCount;
            }
        };
        
        int pageFaults = 0;
        
        for (int page : referenceString) {
            if (!frames.containsKey(page)) {
                pageFaults++;
            }
            frames.put(page, true);
        }
        return pageFaults;
    }

    @Override
    public String getName() {
        return "LRU (Least Recently Used)";
    }

    @Override
    public String getDescription() {
        return "Substitui a página que não foi usada há mais tempo. Excelente desempenho mas requer hardware especializado ou overhead de software.";
    }
}