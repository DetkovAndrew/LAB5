package Reading.Iterator;

import java.util.Iterator;
import java.util.Vector;

import SpaceMarine.SpaceMarine;

public class SpaceMarineIterator implements Iterator {
    private final Vector<SpaceMarine> marines;
    private int index = 0;

    public SpaceMarineIterator(Vector<SpaceMarine> marines) {
        this.marines = marines;
    }

    @Override
    public boolean hasNext() {
        return index < marines.size();
    }

    @Override
    public SpaceMarine next() {
        return marines.get(index++);
    }
}
