import java.util.List;

public class BinaryNode<T extends Comparable<? super T> > {
    private T data;
    private BinaryNode<T> right;
    private BinaryNode<T> left;

    // TODO: initialisation
    // O(1)
    public BinaryNode(T data) {
        this.data= data;
        right= null;
        left= null;
    }

    // TODO: on retourne la donnee voulue
    // O(1)
    public T getData() {
        return data;
    }

    // TODO: on ajoute une nouvelle donnee au bon endroit
    // O(log(n))
    public void insert(T item) {

        int compareResult = item.compareTo(this.data);

        if(compareResult <= 0){
            if(left==null)
                left = new BinaryNode(item);
            else 
                left.insert(item);
        }
        else{
            if (right==null)
                right = new BinaryNode(item);
            else 
                right.insert(item);
        }
    }

    // TODO: est-ce que l'item fais partie du noeuds courant
    // O(log(n))
    public boolean contains(T item) {
        int compareResult = item.compareTo(data);

        if(compareResult < 0){
            if (left!=null)
                return left.contains(item);
            else return false;
        }

        else if(compareResult > 0){
            if (right!=null)
                return right.contains(item);
            else return false;
        }
        else return true;
    }

    // TODO: on retourne la maximale de l'arbre
    // O(n)
    public int getHeight() {

        if(left==null&&right==null){
            return 0;
        }
        else {
            int hL = ((left != null) ? left.getHeight() : 0);
            int hR = ((right != null) ? right.getHeight() : 0);
            return 1 + Math.max(hL, hR);
        }

    }

    // TODO: l'ordre d'insertion dans la liste est l'ordre logique
    // de manière que le plus petit item sera le premier inseré
    // O(n)
    public void fillListInOrder(List<BinaryNode<T>> result) {

        if(left==null)
            result.add(this);
        else{
            left.fillListInOrder(result);
            result.add(this);
        }


        if(right != null)
            right.fillListInOrder(result);

    }
}
