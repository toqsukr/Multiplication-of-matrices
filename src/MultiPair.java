public class MultiPair {
    private Matrix firstMultiplier;
    private Matrix secondMultiplier;
    volatile private int index;
    volatile private int threadNumber;

    public MultiPair(Matrix matrix1, Matrix matrix2) {
        firstMultiplier = matrix1;
        secondMultiplier = matrix2;
        index = 0;
        threadNumber = 0;
    }

    synchronized public int getThreadNumber() {
        return threadNumber;
    }

    public void setThreadNumber(int value) {
        threadNumber = value;
    }

    public Matrix getFirstMultiplier() {
        return firstMultiplier;
    }

    public Matrix getSecondMultiplier() {
        return secondMultiplier;
    }

    synchronized public int getIndex() {
        return index;
    }

    synchronized public int setIndex(int indx) {
        int prevIndex = index;
        index = indx;
        return prevIndex;
    }
}
