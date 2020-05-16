package PageReplacement;

import java.util.ArrayList;

public class Fifo implements PageStrategy {
    @Override
    public ArrayList<PageResult> getPageFault(Page page) {
        int[] referenceStrings = page.getReferenceString();
        int refSize = referenceStrings.length;
        int frameSize = page.getFrameSize();

        ArrayList<PageResult> results = new ArrayList<>();
        int oldestIndex = -1;
        int currentPageSize = 0;

        for (int i = 0; i < refSize; ++i) {
            int refString = referenceStrings[i];

            if (currentPageSize < frameSize) {
                ArrayList<Integer> frame = results.size() == 0 ? new ArrayList<>() :
                        new ArrayList<>(results.get(results.size() - 1).getCurrentFrame());
                if (!frame.contains(refString)) {
                    frame.add(refString);
                    ++currentPageSize;
                }
                results.add(new PageResult(frame, false));

            } else {
                ArrayList<Integer> frame = new ArrayList<>(results.get(results.size() - 1).getCurrentFrame());
                if (!frame.contains(refString)) {
                    oldestIndex = (oldestIndex + 1) % frameSize;
                    frame.set(oldestIndex, refString);
                    results.add(new PageResult(frame, true));
                } else {
                    results.add(new PageResult(frame, false));
                }
            }
        }
        return results;
    }
}