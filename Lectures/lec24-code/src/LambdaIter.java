import java.util.Iterator;

interface Tree<T> {
    Iterator<T> preorderIterator();
    Iterator<T> postorderIterator();
}


public class LambdaIter {
    public static void main(String[] args) {
        Tree<String> tree = null;
//        for (String s : tree) { }  // Doesn't work - Tree doesn't implement Iterable


        // Lambda expressions create objects!
        Iterable<String> postfixView = () -> tree.postorderIterator();
        for (String s : postfixView) {
            System.out.println(s);
        }

        // Equivalent to this ANONYMOUS CLASS:
//        Iterable<String> postfixView = new Iterable<>() {
//            @Override
//            public Iterator<String> iterator() {
//                return tree.postorderIterator();
//            }
//        };
    }
}