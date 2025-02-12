import org.w3c.dom.ls.LSOutput;

import java.util.LinkedList;

class Counter {

    public static void main(String[] args) {
        LinkedList<String> lst = new LinkedList<String>();

        lst.push("A");
        lst.push("B");
        lst.add("C");
        System.out.println(lst);
    }

}