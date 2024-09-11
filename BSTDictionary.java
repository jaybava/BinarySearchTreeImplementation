public class BSTDictionary implements BSTDictionaryADT {
    // initialize parameters for dictionary
    private final BinarySearchTree tree;

    // construct dictionary
    public BSTDictionary() {
        this.tree = new BinarySearchTree();
    }

    // method to get a record
    @Override
    public Record get(Key k) {
        BSTNode node = tree.get(tree.getRoot(), k);
        return node != null ? node.getRecord() : null;
    }

    // method to put a record
    @Override
    public void put(Record d) throws DictionaryException {
        if (d == null) {
            throw new IllegalArgumentException("Record cannot be null");
        }
        tree.insert(tree.getRoot(), d);
    }

    // method to remove a record
    @Override
    public void remove(Key k) throws DictionaryException {
        tree.remove(tree.getRoot(), k);
    }

    // method to find successor
    @Override
    public Record successor(Key k) {
        BSTNode node = tree.successor(tree.getRoot(), k);
        return node != null ? node.getRecord() : null;
    }

    // method to find predecessor
    @Override
    public Record predecessor(Key k) {
        BSTNode node = tree.predecessor(tree.getRoot(), k);
        return node != null ? node.getRecord() : null;
    }

    // method to find smallest
    @Override
    public Record smallest() {
        BSTNode node = tree.smallest(tree.getRoot());
        return node != null ? node.getRecord() : null;
    }

    // method to find largest
    @Override
    public Record largest() {
        BSTNode node = tree.largest(tree.getRoot());
        return node != null ? node.getRecord() : null;
    }
}
