public class BSTNode {
    // parameters for BSTNode object
    private Record record;
    private BSTNode leftChild, rightChild, parent;

    // constructor for BSTNode object
    public BSTNode(Record record) {
        // filter if record is null
        if (record == null) {
            throw new IllegalArgumentException("Record cannot be null");
        }
        this.record = record;
        // initialize the children and parent to null
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
    }

    // getter methods for BSTNode object
    public Record getRecord() {
        return record;
    }

    public BSTNode getLeftChild() {
        return leftChild;
    }

    public BSTNode getRightChild() {
        return rightChild;
    }

    public BSTNode getParent() {
        return parent;
    }


    // setter methods for BSTNode object
    public void setRecord(Record record) {
        this.record = record;
    }

    public void setLeftChild(BSTNode leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(BSTNode rightChild) {
        this.rightChild = rightChild;
    }

    public void setParent(BSTNode parent) {
        this.parent = parent;
    }

    // method to check if a Node is a leaf
    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }
}
