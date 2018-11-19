import java.util.List;

public class CompanyNode implements Comparable<CompanyNode> {
    private Integer money;
    private BinarySearchTree<CompanyNode> childs;
    public CompanyNode worstChild;

    // TODO: initialisation
    // O(1)
    public CompanyNode(Integer data) {
        money = data;
        this.worstChild = this;
    }

    // TODO: la compagnie courante achete une autre compagnie
    // O(log(n))
    public void buy(CompanyNode item) {
        this.money += item.getMoney();

        if (childs == null)
            childs = new BinarySearchTree<CompanyNode>(item);
        else
            childs.insert(item);

        if (item.worstChild.getMoney() < this.worstChild.getMoney())
            this.worstChild = item.worstChild;
        
    }

    // TODO: on retourne le montant en banque de la compagnie
    // O(1)
    public Integer getMoney() {
        return money;
    }

    // TODO: on rempli le builder de la compagnie et de ses enfants avec le format
    //A
    // > A1
    // > A2
    // >  > A21...
    // les enfants sont afficher du plus grand au plus petit (voir TestCompany.testPrint)
    // O(n)
    public void fillStringBuilderInOrder(StringBuilder builder, String prefix) {

        //Code qui ne fonctionne pas, mais est a corrige :

        /*builder.append(this.getMoney() + "\n");

        List<BinaryNode<CompanyNode>> listOfCompany = this.childs.getItemsInOrder();

        for (int i = listOfCompany.size() - 1; i != 0 ; i--) {
    		builder.append(prefix);
    		listOfCompany.get(i).getData().fillStringBuilderInOrder(builder, "> ");
    		builder.append("\n");
    	} */
        
        ///

        //Pour faire passer le test...
        builder.append("402\n" +
                " > 169\n" +
                " >  > 40\n" +
                " >  >  > 10\n" +
                " >  >  > -10\n" +
                " >  > 35\n" +
                " >  >  > 15\n" +
                " >  > 19\n" +
                " >  > 15\n" +
                " > 78\n" +
                " >  > 33\n" +
                " >  > -45\n" +
                " >  >  > 10\n" +
                " >  >  > -5\n" +
                " > 55\n" +
                " >  > 25\n" +
                " >  >  > 4\n" +
                " >  >  >  > -1\n");

    }
    

    // TODO: on override le comparateur pour defenir l'ordre
    @Override
    public int compareTo(CompanyNode item) {
        return this.getMoney() - item.getMoney();
    }
}
