// the key class implements the Comparable interface
public class Key implements Comparable<Key> {
    // add parameter to the object Key
    private final String label;
    private final int type;

    // construct the object with the parameters
    public Key(String label, int type) {
        // filter if key is null (Helps with debugging)
        if (label == null) {
            throw new IllegalArgumentException("Label cannot be null");
        }
        this.label = label.toLowerCase();
        this.type = type;
    }

    // getter methods for each parameters
    public String getLabel() {
        return label;
    }

    public int getType() {
        return type;
    }

    // method to compare keys to each other
    @Override
    public int compareTo(Key other) {
        // compare label to the other label using compareTo function
        int labelComparison = this.label.compareTo(other.label);
        // if the labels are not equal
        if (labelComparison != 0) {
            // return the lexicographically greater label
            return labelComparison;
        }
        // if labels are the same compare the types of the key and return higher value
        return Integer.compare(this.type, other.type);
    }
}
