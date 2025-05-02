package model.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Clock implements PageReplacement {
    
    @Override
    public int run(int[] referenceString, int frameCount) {
        List<Integer> frames = new ArrayList<>(frameCount);
        List<Boolean> referenceBits = new ArrayList<>(frameCount);
        int pointer = 0;
        int pageFaults = 0;
        
        for (int page : referenceString) {
            int index = frames.indexOf(page);
            
            if (index != -1) {
                referenceBits.set(index, true);
                continue;
            }
            
            pageFaults++;
            
            if (frames.size() < frameCount) {
                frames.add(page);
                referenceBits.add(true);
            } else {
                while (true) {
                    if (!referenceBits.get(pointer)) {
                        frames.set(pointer, page);
                        referenceBits.set(pointer, true);
                        pointer = (pointer + 1) % frameCount;
                        break;
                    }
                    referenceBits.set(pointer, false);
                    pointer = (pointer + 1) % frameCount;
                }
            }
        }
        return pageFaults;
    }

    @Override
    public String getName() {
        return "Clock (Second Chance)";
    }

    @Override
    public String getDescription() {
        return "Implementação aproximada do LRU usando um bit de referência. Bom compromisso entre desempenho e complexidade de implementação.";
    }
}