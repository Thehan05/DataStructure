package project20280.tree;

import project20280.interfaces.Entry;
import project20280.interfaces.Position;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class TreapMap<K, V> extends TreeMap<K, V> {
    public TreapMap() {
        super();
    }

    public TreapMap(Comparator<K> comp) {
        super(comp);
    }

    protected int priority(Position<Entry<K, V>> p) {
        return tree.getAux(p);
    }

    protected void setPriority(Position<Entry<K, V>> p, int priority) {
        tree.setAux(p, priority);
    }

    private final Random random = new Random();

    @Override
    protected void rebalanceInsert(Position<Entry<K, V>> p) {
        setPriority(p, random.nextInt());

        while(!isRoot(p)) {
            Position<Entry<K, V>> par = parent(p);
            if(priority(p) > priority(par)) {
                rotate(p);
            } else {
                break;
            }
        }
    }

    @Override
    protected void rebalanceDelete(Position<Entry<K, V>> p) {

    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K, V>> p = treeSearch(root(), key);

        if(isExternal(p)) {
            rebalanceAccess(p);
            return null;
        }

        V old = p.getElement().getValue();

        while(isInternal(left(p)) || isInternal(right(p))) {
            Position<Entry<K, V>> leftC = left(p);
            Position<Entry<K, V>> rightC = right(p);

            boolean hasLeft = isInternal(leftC);
            boolean hasRight = isInternal(rightC);

            Position<Entry<K,V>> highChild;
            if(hasLeft && hasRight) {
                highChild = (priority(leftC) > priority(rightC)) ? leftC : rightC;
            } else {
                highChild = hasLeft ? leftC : rightC;
            }

            rotate(highChild);
        }

        Position<Entry<K, V>> leaf = isExternal(left(p)) ? left(p) : right(p);
        remove(leaf);
        remove(p);
        return old;
    }

    public int height() {
        return height(root());
    }

    private int height(Position<Entry<K, V>> p) {
        if(isExternal(p)) return 0;
        return 1 + Math.max(height(left(p)), height(right(p)));
    }

    public boolean isValidTreap() {
        return isHeap(root()) && isBST(root(), null, null);
    }

    private boolean isHeap(Position<Entry<K, V>> p) {
        if(isExternal(p)) return true;

        Position<Entry<K, V>> leftChild = left(p);
        Position<Entry<K, V>> rightChild = right(p);

        if(isInternal(leftChild) && priority(leftChild) > priority(p)) return false;
        if(isInternal(rightChild) && priority(rightChild) > priority(p)) return false;

        return isHeap(leftChild) && isHeap(rightChild);
    }

    private boolean isBST(Position<Entry<K, V>> p, K lower, K upper) {
        if (isExternal(p)) return true;

        K key = p.getElement().getKey();

        if(lower != null && compare(key, lower) < 0) return false;
        if(upper != null && compare(key, upper) > 0) return false;

        return isBST(left(p), lower, key) && isBST(right(p), key, upper);
    }

    // making an empty list and filling it with Treap entries in sorted order
    public ArrayList<Entry<K, V>> inOrderList() {
        // in order traversal from the root
        ArrayList<Entry<K, V>> list = new ArrayList<>();
        inOrderHelper(root(), list);
        return list;
    }

    // taking the current node and the new list to actually do the tree traversal
    private void inOrderHelper(Position<Entry<K, V>> p, ArrayList<Entry<K, V>> list) {
        // error check if empty and goes back to previous node
        if (isExternal(p)) {
            return;
        }
        // in the tree the recursion make us go deep left which contains the smaller keys
        inOrderHelper(left(p), list);
        // add the deepest all the way up
        list.add(p.getElement());
        // helps us go right when given the option returning back up from the left
        inOrderHelper(right(p), list);
    }
}
