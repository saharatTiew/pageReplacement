package PageReplacement;

import java.util.ArrayList;

public class PageResult {
    private ArrayList<Integer> currentFrame;
    private boolean isFault;

    public PageResult(ArrayList<Integer> currentFrame, boolean isFault) {
        this.currentFrame = currentFrame;
        this.isFault = isFault;
    }

    public ArrayList<Integer> getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(ArrayList<Integer> currentFrame) {
        this.currentFrame = currentFrame;
    }

    public boolean isFault() {
        return isFault;
    }

    public void setFault(boolean fault) {
        isFault = fault;
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        for (int i : currentFrame) {
            output.append(i).append(" ");
        }
        output.append(isFault ? "               F\n" : "\n");
        return output.toString();
    }
}