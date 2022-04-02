package data_structure.hashTable;

public class HashTableDome {
}

//即结点，有需要存储的数据以及指针next
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList {  //表示链表
    //头指针
    private Emp head;

    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (curEmp != null) {
            curEmp = curEmp.next;
        }

        curEmp = emp;


    }
}