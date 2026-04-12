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
        if(!isInternal(p))  return;

        while(isInternal(p)) {
            Position<Entry<K, V>> leftC = left(p);
            Position<Entry<K, V>> rightC = right(p);

            boolean hasLeft = isInternal(leftC);
            boolean hasRight = isInternal(rightC);

            if(!hasLeft && !hasRight) {
                break;
            }

            Position<Entry<K, V>> highChild;
            if(hasLeft && hasRight) {
                if(priority(leftC) > priority(rightC)) {
                    highChild = leftC;
                } else {
                    highChild = rightC;
                }
            } else if(hasLeft) {
                highChild = leftC;
            } else {
                highChild = rightC;
            }

            if(priority(highChild) > priority(p)) {
                rotate(highChild);
            } else {
                break;
            }

        }
    }

    public int height() {
        return height(root());
    }

    private int height(Position<Entry<K, V>> p) {
        if(isExternal(p)) return 0;
        return 1 + Math.max(height(left(p)), height(right(p)));
    }

    public boolean isValidTreap() {
        return isHeap(root());
    }

    private boolean isHeap(Position<Entry<K, V>> p) {
        if(isExternal(p)) return true;

        Position<Entry<K, V>> leftChild = left(p);
        Position<Entry<K, V>> rightChild = right(p);

        if(isInternal(leftChild) && priority(leftChild) > priority(p)) return false;
        if(isInternal(rightChild) && priority(rightChild) > priority(p)) return false;

        return isHeap(leftChild) && isHeap(rightChild);
    }

}
