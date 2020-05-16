package PageReplacement;

import java.util.ArrayList;

public class Page {
    private int frameSize;
    private int[] referenceString;
    private PageStrategy strategy;

    public Page(int frameSize, int[] referenceString) {
        this.frameSize = frameSize;
        this.referenceString = referenceString;
    }

    public int getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(int frameSize) {
        this.frameSize = frameSize;
    }

    public int[] getReferenceString() {
        return referenceString;
    }

    public void setReferenceString(int[] referenceString) {
        this.referenceString = referenceString;
    }

    public PageStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(PageStrategy strategy) {
        this.strategy = strategy;
    }

    public ArrayList<PageResult> getPageFault() {
        var pageFault = strategy.getPageFault(this);
        String algoName = strategy.getClass().toString();
        System.out.println(algoName.substring(algoName.lastIndexOf('.')+1));
        System.out.println("Total Page faults are " + pageFault.stream().filter(PageResult::isFault).count());
        return pageFault;
    }

    public ArrayList<ArrayList<PageResult>> getPageFaultAllAlgo(ArrayList<PageStrategy> strategies) {
        ArrayList<ArrayList<PageResult>> results = new ArrayList<>();
        for (PageStrategy strategy : strategies) {
            this.strategy = strategy;
            var pageFault = strategy.getPageFault(this);
            results.add(pageFault);
        }
        return results;
    }
}