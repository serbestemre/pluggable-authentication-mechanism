import java.util.ArrayList;
//Iterator pattern is used to access the elements of a collection.
// In our case, we have a general collection which contains users defined in OS.
// In order to find a user from the collection, users are iterated one by one in findUser() and getUid() methods.
//
// PARTICIPANTS
// Iterator:
//  AbstractIterator
// Concrete Iterator:
//  Iterator
// Aggregate:
//  AbstractCollection
// Concrete  Aggregate:
//  Collection



interface AbstractIterator {
    void First();

    void Next();

    Boolean IsDone();

    User CurrentUser();
}

interface AbstractAggregate {
    AbstractIterator CreateIterator();

    void add(User us);

    boolean contains(User user);

    int getCount();

    User get(int idx);
}

class CollectionIterator implements AbstractIterator {
    private Collection _collection;
    private int _current;

    public CollectionIterator(Collection collection) {
        _collection = collection;
        _current = 0;
    }

    public void First() {
        _current = 0;
    }

    public void Next() {
        _current++;
    }

    public User CurrentUser() {
        return (IsDone() ? null : _collection.get(_current));
    }

    public Boolean IsDone() {
        return _current >= _collection.getCount();
    }
}

class Collection implements AbstractAggregate {
    private ArrayList<User> _items = new ArrayList<User>();

    public CollectionIterator CreateIterator() {
        return new CollectionIterator(this);
    }

    public int getCount() {
        return _items.size();
    }

    public void add(User user) {
        _items.add(user);
    }

    public User get(int index) {
        return _items.get(index);
    }

    public boolean contains(User user) {
        if (_items.contains(user))
            return true;
        else
            return false;

    }

}
