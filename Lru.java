package PageReplacement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Lru implements PageStrategy {
    @Override
    public ArrayList<PageResult> getPageFault(Page page) {
        int[] referenceStrings = page.getReferenceString();
        int refSize = referenceStrings.length;
        int frameSize = page.getFrameSize();
        ArrayList<PageResult> results = new ArrayList<>();
        int currentPageSize = 0;

        for (int i = 0; i < refSize; ++i) {

            int refString = referenceStrings[i];
            if (currentPageSize < frameSize) {
                ArrayList<Integer> frame = results.size() == 0 ? new ArrayList<>()
                        : new ArrayList<>(results.get(results.size() - 1).getCurrentFrame());
                if (!frame.contains(refString)) {
                    frame.add(refString);
                    ++currentPageSize;
                }
                results.add(new PageResult(frame, false));
            } else {
                ArrayList<Integer> frame = new ArrayList<>(results.get(results.size() - 1).getCurrentFrame());
                if (!frame.contains(refString)) {
                    //find the previous
                    HashMap<Integer, Integer> nextRefs = new HashMap<>();
                    for (int j = 0; j < frame.size(); ++j) {
                        int nextRef = Integer.MAX_VALUE;
                        for (int k = i; k > -1; --k) {
                            if (frame.get(j) == referenceStrings[k]) {
                                nextRef = i - k;
                                break;
                            }
                        }
                        nextRefs.put(j, nextRef);
                    }
                    //find the process that is the last one that was referenced in past time
                    int replaceIndex = Collections.max(nextRefs.entrySet(),
                            Comparator.comparingInt(Map.Entry::getValue)).getKey();
                    frame.set(replaceIndex, refString);
                    results.add(new PageResult(frame, true));
                } else {
                    results.add(new PageResult(frame, false));
                }
            }
        }
        return results;
    }
}