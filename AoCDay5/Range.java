/**
 * Represents a range, used in RangeMachine
 * For AoC Day 5 Part 2
 * 
 * @author Tony Li
 * Bugs: None
 */
public class Range {

    // Attributes of Range
    private long min;
    private long max;

    /**
     * Creates a Range object with bounds
     * @param min
     * @param max
     */
    public Range(long min, long max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Accessor for min bound
     * @return min
     */
    public long getMin() {
        return min;
    }

    /**
     * Accessor for max bound
     * @return max
     */
    public long getMax() {
        return max;
    }

    /**
     * Gets the size of the range
     * @return the size
     */
    public long getSize() {
        return max - min;
    }

    /**
     * Prints out the bounds of the range
     */
    @Override
    public String toString() {
        return min + " - " + max;
    }
}
