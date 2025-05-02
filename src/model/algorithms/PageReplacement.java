package model.algorithms;


public interface PageReplacement {
    int run(int[] referenceString, int frameCount);
    String getName();
    String getDescription();
}