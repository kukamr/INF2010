import java.util.List;

public class CompanyNode implements Comparable<CompanyNode> {
    private Integer money;
    private BinarySearchTree<CompanyNode> childs;
    public CompanyNode worstChild;

    // TODO: initialisation
    // O(1)
    public CompanyNode(Integer data) {
        money = data;
    }

    // TODO: la compagnie courante achete une autre compagnie
    // O(log(n))
    public void buy(CompanyNode item) {
        this.money += item.getMoney();

        if(this.worstChild == null){
            if(item.worstChild == null){
                this.worstChild = item;     // Si la compagnie actuelle et celle achete n'ont pas de worst child, worstChilcActuel = new item
            }
            else{ //Si la nouvelle compagnie a deja un worst child,
                this.worstChild = item.worstChild;
            }
        }
        else{
            if(item.worstChild == null){    //Si la compagnie actuelle a deja un worst child, mais la compagnie achete n'en a pas
                int compareWorstChild = this.worstChild.compareTo(item);
                if(compareWorstChild <= 0){
                    this.worstChild = item;
                }
            } else { //La compagnie actuelle a des enfants, et celle achete aussi une compagnie avec des enfants
                int compareWorstsChilds = this.worstChild.compareTo(item.worstChild);
                if (compareWorstsChilds <= 0){
                    this.worstChild = item.worstChild;
                }
            }
        }
        
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
        builder.append(this.getMoney() + "\n");
    	
        List<BinaryNode<CompanyNode>> listOfCompany = this.childs.getItemsInOrder();
    	for (int i = listOfCompany.size(); i > 0 ; i--) {
    		builder.append(prefix);
    		listOfCompany.get(i).getData().fillStringBuilderInOrder(builder, "> ");
    		builder.append("\n");
    	}
    }
    

    // TODO: on override le comparateur pour defenir l'ordre
    @Override
    public int compareTo(CompanyNode item) {
        return this.getMoney() - item.getMoney();
    }
}
