public class Record {
    // initialize the parameters for Record object
    private final Key key;
    private final String data;

    // construct the Record object
    public Record(Key key, String data) {
        // filter if key or data is null
        if (key == null || data == null) {
            throw new IllegalArgumentException("Key and data cannot be null");
        }
        this.key = key;
        this.data = data;
    }

    // getter methods for the Record object
    public Key getKey() {
        return key;
    }

    public String getDataItem() {
        return data;
    }
}