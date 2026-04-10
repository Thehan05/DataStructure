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
}
