public class MultiPair {
    private Matrix firstMultiplier;
    private Matrix secondMultiplier;
    volatile private int index;
    volatile private int threadNumber;

    /***
     * The MulitPair class constructor
     * 
     * @param matrix1 the first matrix of pair
     * @param matrix2 the second matrix of pair
     */
    public MultiPair(Matrix matrix1, Matrix matrix2) {
        firstMultiplier = matrix1;
        secondMultiplier = matrix2;
        index = 0;
        threadNumber = 0;
    }

    /***
     * Getter of threadNumber
     * 
     * @return field threadNumber
     */
    synchronized public int getThreadNumber() {
        return threadNumber;
    }

    /***
     * Setter of threadNumber
     * 
     * @param value to be setted
     */
    public void setThreadNumber(int value) {
        threadNumber = value;
    }

    /***
     * Getter of firstMultiplier
     * 
     * @return field firstMultiplier
     */
    public Matrix getFirstMultiplier() {
        return firstMultiplier;
    }

    /***
     * Getter of second Multiplier
     * 
     * @return field secondMultiplier
     */
    public Matrix getSecondMultiplier() {
        return secondMultiplier;
    }

    /***
     * Getter of index
     * 
     * @return field index
     */
    synchronized public int getIndex() {
        return index;
    }

    /***
     * Setter of index
     * 
     * @param indx to be setted
     * @return previous index
     */
    synchronized public int setIndex(int indx) {
        int prevIndex = index;
        index = indx;
        return prevIndex;
    }
}
