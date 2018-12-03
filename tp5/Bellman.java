import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;



public class Bellman {
	private Graph graph;
	private Node sourceNode;
	private List<Vector<Double>> piTable =  new ArrayList<Vector<Double>>();
	private List<Vector<Integer>> rTable =  new ArrayList<Vector<Integer>>();
	
	static final Integer inf = 99999;
	
	public Bellman (Graph g) {
		this.graph = g;
	}
	
	public void setSourceNode(Node source) {
		this.sourceNode = source;
	}
	
	public void shortestPath() {
		
		//On ajoute le premier vecteur a notre piTable pour k = 0
		//Meme chose pour la rTable
		piTable.add( new Vector<Double>());
		rTable.add( new Vector<Integer>());
		Integer k = 0;

		//Initialiser les distances dans piTable a Infini pour tous les noeuds, sauf src = 0
		//Mettre Infini dans la rTable pour tout les noeuds
		for (Node n : this.graph.getNodes()){
			piTable.get(k).add( n.equals(this.sourceNode) ? 0.0 : (double)inf );
			rTable.get(k).add(inf);
		}
		
		boolean hasNegativePath = false;

		for(;;){
			
			// On incremente k et on insere un nouveau vecteur dans notre piTable et rTable
			k++;
			piTable.add(new Vector<Double>());
			rTable.add( new Vector<Integer>());
			
			for(Node n : this.graph.getNodes()){

				// PI^k-1(n) : temps Minimal jusqu'a present pour se rendre au Node n
				Double currentMinimalTimeToNode = piTable.get(k - 1).get(n.hashCode());
				
				// On trouve tous les arcs rentrants dans le Node n
				List<Edge> inEdges =  this.graph.getInEdges(n);
				
				// On cherche le temps minimum pour se rendre a n a partir d'un de ses predecesseurs
				// On prend le minimum entre le PI^k-1(n) et le plus rapide a partir de ses predecesseurs

				// On met minValue a la valeur precedente
				Double minValue = piTable.get(k-1).get(n.hashCode());

				//Integer previousNodeToNID = rTable.get(k - 1).get(n.hashCode()); //is 99999 by default
				//Node previousNodeToN = this.graph.getNodeById(previousNodeToNID);
				Node minNode = null;

				//Va trouver le temps minimum pour se rendre a n et de quel noeud on vient
				for ( Edge e : inEdges){
					// On sait que la destination est n
					Node sourceNode = e.getSource();
					
					//Le temps minimal jusqua present pour se rendre a la source 
					Double currentWeightToSource = piTable.get(k-1).get(sourceNode.hashCode());
					Double edgeWeight = e.getDistance();
					
					if((currentWeightToSource + edgeWeight) < minValue){
						minValue = currentWeightToSource + edgeWeight;
						minNode = e.getSource(); 
					}
					
				}
				
				piTable.get(k).add(minValue);

				// Si le weight a partir d'un predecesseur est plus rapide que le temps courant, on change le temps courant
				if(minValue < currentMinimalTimeToNode){
					//On met a jour la rTable
					rTable.get(k).add(minNode.hashCode());
				} else {
					//Sinon, on remet la meme valeur qu'en PI^k-1
					rTable.get(k).add(rTable.get(k-1).get(n.hashCode()));
				}
			}

			// Si pi^k == pi^k-1 on arrete, il n'y a eu aucune modification
			Boolean same = true;
			for(Integer n = 0; n < piTable.get(k).size(); n++){
				if (piTable.get(k-1).get(n) != piTable.get(k).get(n)){
					same = false;
					break;
				}
			}
			if(same){
				break;
			}
			//verifier si il contient un chemin negatif
			if (k == this.graph.getNodes().size()){
				hasNegativePath = true;
				break;
			}

			
		}
	}
	
	public void  diplayShortestPaths() {
		List<Node> nodeBell = this.graph.getNodes(); //list contenant tous les node de la class Bellman
		List<Node> nodeCopy = new ArrayList<Node>(); // initilisation d'une list de node 
		for(Node node: nodeBell) { // Copy de nouveau Node afin d'éviter l'erreur java.lang.NullPointerException
			nodeCopy.add(node.copy()); 
		}
		nodeCopy.remove(nodeCopy.get(this.sourceNode.getId())); // retirer le node S
		int lastIndex = this.rTable.size()-1; // index definit au dernier élé de la rTable

		for(Node nodeSuivant: nodeBell) {
			String chemin= "[S - " + nodeSuivant.getName() + "]"+ this.piTable.get(lastIndex).get(nodeSuivant.getId()).toString() + " : "; // definition des chemin Node - Node
			String route = "";
			while( nodeSuivant.getId() != this.sourceNode.getId()) {
				route = " -> " +  nodeSuivant.getName() + route; // Afficher le prochain chemin tant qu'il ne revient pas tu ne revinet pas au nodeSource
				int indexSuivant = this.rTable.get(lastIndex).get( nodeSuivant.getId()); //trouver le
				nodeSuivant = this.graph.getNodeById(indexSuivant); //Valeur de l'index qui sépare le dernier node du nodeSource
			}

			route = sourceNode.getName() + route; // mettre le node S au debut de l'affichge de la route
			System.out.println(chemin + route); // afficher sur le terminal
		}
	}
	

	public void displayTables() {
		System.out.println("Display piTable");
		Integer i = 0;
		for(Vector<Double> a : piTable) {
			System.out.print("[" + i++ + "] - ");
			for(Double d : a){
				System.out.print("("+d+")");
				System.out.print(" - ");
				
			}	
			System.out.println(" ");
			System.out.println("-------------------");
		}

		System.out.println("Display rTables");
		for(Vector<Integer> a : rTable) {
			for(Integer d : a){
				String nodeName = "-";
				if( d < 90){
					nodeName = this.graph.getNodeById(d).getName();
				}
				System.out.print(nodeName);
				System.out.print(" - ");
				
			}	
			System.out.println(" ");
			System.out.println("-------------------");
		}
	}
}
