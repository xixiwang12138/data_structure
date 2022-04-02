package data_structure.lineList;

public interface Llist {
    void clear();
    boolean isEmpty();
    int length();
    Object get(int i) throws Exception;
    void insert(int i ,Object x) throws Exception;
    void remove(int i) throws Exception;
    int indexOf(Object x);
    void display();
}
