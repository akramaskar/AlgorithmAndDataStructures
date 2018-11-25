package ad.praktikum2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BST {

    private Node root;

    public boolean insert(Stadt stadt, Parameter parameter) {
        if (root == null) {
            root = new Node(stadt, parameter, null);
            return true;
        }

        int key;

        if(parameter == Parameter.PLZ) key = stadt.getPostleitzahl();
        else if(parameter == Parameter.FLAECHE) key = (int) (stadt.getFlaeche() * 1000); // Fleache wird 2 Nachkommastellen nach hinten gerückt. Km > m
        else if(parameter == Parameter.BEVGESAMT) key = stadt.getBevoelkerungGesamt();
        else if(parameter == Parameter.BEVMAENNLICH) key = stadt.getBevoelkerungMaennlich();
        else if(parameter == Parameter.BEVWEIBLICH) key = stadt.getBevoelkerungWeiblich();
        else key = 0;

        Node n = root;
        while (true) {
            if (n.key == key)
                return false;

            Node parent = n;

            boolean goLeft = n.key > key;
            n = goLeft ? n.left : n.right;

            if (n == null) {
                if (goLeft) {
                    parent.left = new Node(stadt, parameter, parent);
                } else {
                    parent.right = new Node(stadt, parameter, parent);
                }
                rebalance(parent);
                break;
            }
        }
        return true;
    }

    private void rebalance(Node n) {
        setBalance(n);

        if (n.balance == -2) {
            if (height(n.left.left) >= height(n.left.right))
                n = rotateRight(n);
            else
                n = rotateLeftThenRight(n);

        } else if (n.balance == 2) {
            if (height(n.right.right) >= height(n.right.left))
                n = rotateLeft(n);
            else
                n = rotateRightThenLeft(n);
        }

        if (n.parent != null) {
            rebalance(n.parent);
        } else {
            root = n;
        }
    }

    private Node rotateLeft(Node a) {

        Node b = a.right;
        b.parent = a.parent;

        a.right = b.left;

        if (a.right != null)
            a.right.parent = a;

        b.left = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }

        setBalance(a, b);

        return b;
    }

    private Node rotateRight(Node a) {

        Node b = a.left;
        b.parent = a.parent;

        a.left = b.right;

        if (a.left != null)
            a.left.parent = a;

        b.right = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }

        setBalance(a, b);

        return b;
    }

    private Node rotateLeftThenRight(Node n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }

    private Node rotateRightThenLeft(Node n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }

    private int height(Node n) {
        if (n == null)
            return -1;
        return n.height;
    }

    private void setBalance(Node... nodes) {
        for (Node n : nodes) {
            reheight(n);
            n.balance = height(n.right) - height(n.left);
        }
    }

    public void printBalance() {
        printBalance(root);
    }

    private void printBalance(Node n) {
        if (n != null) {
            printBalance(n.left);
            System.out.printf("%s ", n.balance);
            printBalance(n.right);
        }
    }

    public void printTree(){
        printTree(root);
    }

    private void printTree(Node n){
        if(n != null) {
            printTree(n.left);
            System.out.println(n.toString() + n.balance);
            printTree(n.right);
        }
    }

    private void reheight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    public void findFromTo(Node n, int von, int bis, LinkedList<Stadt> resultingList){
        // Node key ist kleiner als Suchbereich | Rechts ist aber ein Subtree -> Such rechts!
        if(n.key < von && n.rightIsNotNull()) findFromTo(n.right, von, bis, resultingList);

        // Node key ist größer als Suchbereich | Links ist aber ein Subtree -> Such links!
        else if(n.key > bis && n.leftIsNotNull()) findFromTo(n.left, von, bis, resultingList);

        // Node key liegt genau im Suchbereich & rechts sowie links sind Subtrees. Such rechts und links!
        else if(n.key >= von && n.key <= bis && n.leftIsNotNull() && n.rightIsNotNull()) {
            resultingList.addLast(n.stadt);

            findFromTo(n.left, von, bis, resultingList);
            findFromTo(n.right, von, bis, resultingList);
        }

        // Node ist im Suchbereich jedoch ist nur links ein Subtree -> Such links!
        else if(n.key >= von && n.key <= bis && n.leftIsNotNull()) {
            resultingList.addLast(n.stadt);

            findFromTo(n.left, von, bis, resultingList);
        }
        else if(n.key >= von && n.key <= bis && n.rightIsNotNull()) {
            resultingList.addLast(n.stadt);

            findFromTo(n.right, von, bis, resultingList);
        }
        else if(n.isLeaf() && n.key >= von && n.key <= bis) {
            resultingList.addLast(n.stadt);
        }

    }

    public Node getRoot() {
        return root;
    }

    public void reset(){
        root = null;
    }
}