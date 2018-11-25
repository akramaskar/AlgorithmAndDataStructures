package ad.praktikum2;

public class Node {
    Node left, right, parent;
    int key, height, balance;

    Stadt stadt;

    public Node(Stadt stadt, Parameter parameter,Node parent) {
        this.stadt = stadt;
        this.parent = parent;

        switch (parameter) {
            case PLZ: this.key = stadt.getPostleitzahl(); break;
            case FLAECHE: this.key = (int) stadt.getFlaeche(); break;
            case BEVGESAMT: this.key = stadt.getBevoelkerungGesamt(); break;
            case BEVMAENNLICH: this.key = stadt.getBevoelkerungMaennlich(); break;
            case BEVWEIBLICH: this.key = stadt.getBevoelkerungWeiblich(); break;
        }
    }

    public boolean isLeaf() {
        return this.right == null && this.left == null;
    }

    public boolean rightIsNotNull(){
        return this.right != null;
    }

    public boolean leftIsNotNull(){
        return this.left != null;
    }

    @Override
    public String toString() {
        return stadt.toString();
    }
}