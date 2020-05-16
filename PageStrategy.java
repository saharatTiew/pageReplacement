package PageReplacement;

import java.util.ArrayList;

public interface PageStrategy {
    public ArrayList<PageResult> getPageFault(Page page);
}