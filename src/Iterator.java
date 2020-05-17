import java.util.ArrayList;

interface  AbstractIterator {
    void First();
    void Next();
    Boolean IsDone () ;
    User CurrentUser() ;
}

class CollectionIterator implements AbstractIterator {
    public void First() {_current = 0;}
    public void Next()  {_current++; }
    public User CurrentUser() { return (IsDone()?null:_collection.get(_current)); }
    public Boolean IsDone() {	return _current >= _collection.getCount(); }
    public CollectionIterator(Collection collection) {
        _collection = collection;
        _current = 0;
    }
    private Collection _collection;
    private int _current;
};

interface AbstractAggregate {
    public AbstractIterator CreateIterator();
    public void add(User us);
    public int getCount ();
    public User get(int idx);
};


class Collection implements AbstractAggregate {
    private	 ArrayList<User> _items = new ArrayList<User>();
    public	CollectionIterator CreateIterator() {
        return new CollectionIterator(this);
    }
    public int getCount () {return _items.size(); }
    public void add(User user) {_items.add(user);};
    public User get(int index) { return _items.get(index);};
};
