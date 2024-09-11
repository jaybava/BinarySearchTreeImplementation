public class BinarySearchTree {
    // initialize the parameters for the Binary Search Tree
    private BSTNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    public BSTNode getRoot() {
        return root;
    }

    // method to insert a node and record to binary search tree
    public void insert(BSTNode r, Record d) throws DictionaryException {
        // if record is null
        if (d == null) {
            // throw exception
            throw new IllegalArgumentException("Record cannot be null");
        }
        // if root node is null
        if (root == null) {
            // set the root to this new record
            root = new BSTNode(d);
        } else {
            // else call on recursive method to insert the node
            insertRec(root, d);
        }
    }

    private void insertRec(BSTNode r, Record d) throws DictionaryException {
        // compare current key of the record to current node r
        int cmp = d.getKey().compareTo(r.getRecord().getKey());
        // if the key is less than current node's key
        if (cmp < 0) {
            // check is left child is null
            if (r.getLeftChild() == null) {
                // insert the node
                BSTNode newNode = new BSTNode(d);
                // set parent of this new node to the current node
                newNode.setParent(r);
                // set the left child of current node to this new node
                r.setLeftChild(newNode);
            } else {
                // recursively call insertRec with left child of current node
                insertRec(r.getLeftChild(), d);
            }
        // if key is greater than current node's key
        } else if (cmp > 0) {
            // if the right child is null
            if (r.getRightChild() == null) {
                // insert the new node
                BSTNode newNode = new BSTNode(d);
                // set parent of this new node to current node
                newNode.setParent(r);
                // set current node's right child to the new node
                r.setRightChild(newNode);
            } else {
                // recursively call InsertRec with the right child of current node
                insertRec(r.getRightChild(), d);
            }
        } else {
            // throw exception if record already exists
            throw new DictionaryException("Record with the same key already exists");
        }
    }

    // method to get a node and return it
    public BSTNode get(BSTNode r, Key k) {
        // if given node is null
        if (r == null) {
            // return null
            return null;
        }
        int cmp = k.compareTo(r.getRecord().getKey());
        // if key is less than current node
        if (cmp < 0) {
            // recursively call the method on the left child of current node
            return get(r.getLeftChild(), k);
        // if key is greater current node
        } else if (cmp > 0) {
            // recursively call the method on the right child of current node
            return get(r.getRightChild(), k);
        } else {
            // if key is found return it
            return r;
        }
    }

    // method to remove a record from binary search tree
    public void remove(BSTNode r, Key k) throws DictionaryException {
        // call on removeRec method and pass the root and key
        root = removeRec(root, k);
    }

    private BSTNode removeRec(BSTNode r, Key k) throws DictionaryException {
        if (r == null) {
            throw new DictionaryException("Record with the given key does not exist");
        }
        int cmp = k.compareTo(r.getRecord().getKey());
        // if key is less than current node's key
        if (cmp < 0) {
            // recursively call the method on the left child of current node
            r.setLeftChild(removeRec(r.getLeftChild(), k));
        // if key is greater than current node's key
        } else if (cmp > 0) {
            // recursively call the method on the right child of current node
            r.setRightChild(removeRec(r.getRightChild(), k));
        } else {
            // if node with matching key is leaf
            if (r.isLeaf()){
                return null;
            }
            // check if left child of the rode is null
            if (r.getLeftChild() == null) {
                // if right child isn't null
                if (r.getRightChild() != null) {
                    // set the parent of right child to parent of current node
                    r.getRightChild().setParent(r.getParent());
                }
                // returns the right child of current node removing the node from the tree
                return r.getRightChild();
            // if right child is null
            } else if (r.getRightChild() == null) {
                // if left child isn't null
                if (r.getLeftChild() != null) {
                    // set parent of left child to parent of current node
                    r.getLeftChild().setParent(r.getParent());
                }
                // return left child of current node removing the node from the tree
                return r.getLeftChild();
            }
            // find the smallest node in the right subtree of the current node
            BSTNode temp = smallest(r.getRightChild());
            // replace the record of the current node with the smallest node of the right subtree
            r.setRecord(temp.getRecord());
            // recursively removes the smallest node from the right subtree as it has been moved
            r.setRightChild(removeRec(r.getRightChild(), temp.getRecord().getKey()));
        }
        // return current node
        return r;
    }

    // method to get the smallest node in the binary search tree
    public BSTNode smallest(BSTNode r) {
        // if the node is null
        if (r == null) {
            return null;
        }
        // traverse through tree until the current node's left child is null
        while (r.getLeftChild() != null) {
            r = r.getLeftChild();
        }
        // return the node
        return r;
    }

    public BSTNode largest(BSTNode r) {
        // if node is null
        if (r == null) {
            return null;
        }
        // traverse through tree until right child is null
        while (r.getRightChild() != null) {
            r = r.getRightChild();
        }
        // return the node
        return r;
    }

    // method to find a successor node of a given node in the binary search tree
    public BSTNode successor(BSTNode r, Key k) {
        // get node with correct key
        BSTNode current = get(r, k);
        // if the node found is null
        if (current == null) {
            return null;
        }
        // checks if right subtree exist
        if (current.getRightChild() != null) {
            // return the smallest node in the right subtree
            return smallest(current.getRightChild());
        }
        // initialize the successor as null
        BSTNode successor = null;
        // set the ancestor to root of binary search tree
        BSTNode ancestor = root;
        // while ancestor does not equal current
        while (ancestor != current) {
            // compare the keys and if less than current node
            if (current.getRecord().getKey().compareTo(ancestor.getRecord().getKey()) < 0) {
                // set successor as ancestor
                successor = ancestor;
                // ancestor is equal to left child of ancestor
                ancestor = ancestor.getLeftChild();
            // if greater than current node
            } else {
                // set ancestor to right child of ancestor
                ancestor = ancestor.getRightChild();
            }
        }
        // return successor
        return successor;
    }

    // method to find predecessor of a node
    public BSTNode predecessor(BSTNode r, Key k) {
        // get node with correct key
        BSTNode current = get(r, k);
        // if current node is null
        if (current == null) {
            return null;
        }
        // checks if left subtree exist
        if (current.getLeftChild() != null) {
            // return largest of left child
            return largest(current.getLeftChild());
        }
        // set predecessor to null
        BSTNode predecessor = null;
        // set ancestor to root of binary search tree
        BSTNode ancestor = root;
        // while ancestor does not equal current
        while (ancestor != current) {
            // compare current's key to ancestor's key and if current is greater
            if (current.getRecord().getKey().compareTo(ancestor.getRecord().getKey()) > 0) {
                // predecessor is equal to ancestor
                predecessor = ancestor;
                // ancestor is right child of ancestor
                ancestor = ancestor.getRightChild();
            // if current is less
            } else {
                // ancestor is equal to its left child
                ancestor = ancestor.getLeftChild();
            }
        }
        // return predecessor
        return predecessor;
    }
}
