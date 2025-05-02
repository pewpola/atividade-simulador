package model.algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class FIFO implements PageReplacement {
    
    @Override
    public int run(int[] referenceString, int frameCount) {
        Queue<Integer> frames = new LinkedList<>();
        int pageFaults = 0;
        
        for (int page : referenceString) {
            if (!frames.contains(page)) {
                pageFaults++;
                if (frames.size() == frameCount) {
                    frames.poll();
                }
                frames.add(page);
            }
        }
        return pageFaults;
    }

    @Override
    public String getName() {
        return "FIFO (First-In, First-Out)";
    }

    @Override
    public String getDescription() {
        return "Substitui a página que está na memória há mais tempo. Simples mas pode não ser eficiente em cenários com padrões de acesso repetitivos.";
    }
}