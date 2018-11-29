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
		
		//1. On ajoute le premier vecteur a notre piTable pour k = 0
		//   Meme chose pour la rTable
		piTable.add( new Vector<Double>());
		rTable.add( new Vector<Integer>());
		Integer k = 0;

		//2. Initialiser les distances dans piTable a Infini pour tous les noeuds, sauf src = 0
		//   Mettre Infini dans la rTable pour tout les noeuds
		for (Node n : this.graph.getNodes()){
			piTable.get(k).add( n.equals(this.sourceNode) ? 0.0 : (double)inf );
			rTable.get(k).add(inf);
		}
		

		for(;;){
			
			// On incremente k et on insere un nouveau vecteur dans notre piTable et rTable
			k++;
			piTable.add(new Vector<Double>());
			rTable.add( new Vector<Integer>());

			for(Node n : this.graph.getNodes()){

				//On copie le piTable -1 a k et le rTable
				piTable.set(k, piTable.get(k-1));
				rTable.set(k, rTable.get(k-1));

				// PI^k-1(n) : temps Minimal jusqu'a present pour se rendre au Node n
				Double currentMinimalTimeToNode = piTable.get(k - 1).get(n.hashCode());
				
				// On trouve tous les arcs rentrants dans le Node n
				List<Edge> inEdges =  this.graph.getInEdges(n);
				
				// On cherche le temps minimum pour se rendre a n a partir d'un de ses predecesseurs
				// On prend le minimum entre le PI^k-1(n) et le plus rapide a partir de ses predecesseurs

				// On met minWeightToN a inf
				Double minValue = (double)inf;
				Node previousNodeToN = rTable.get(k - 1).get(n.hashCode);
				
				for ( Edge e : inEdges){
					// On sait que la destination est n
					Node previousNode = e.getSource();
					
					//Le temps minimal jusqua present pour se rendre a son predecesseur
					Double currentWeightToPredecessor = piTable.get(k-1).get(previousNode.hashCode());
					Double weightFromPredecessorToN = e.getDistance();
					
					if((currentWeightToPredecessor + weightFromPredecessorToN) < minValue){
						minValue = currentMinimalTimeToNode + weightFromPredecessorToN;
						previousNodeToN = previousNode;
					}
					
				}
				
				// Si le weight a partir d'un predecesseur est plus rapide que le temps courant, on change le temps courant
				if(minValue < currentMinimalTimeToNode){
					piTable.get(k).set(n.hashCode(), minValue);
					//Et on met a jour la rTable
					rTable.get(k).set(n.hashCode(), previousNodeToN.hashCode());
				}
			}

			// Si pi^k == pi^k-1 on arrete, il n'y a eu aucune modification
			if(piTable.get(k-1) == piTable.get(k)){
				break;
			}

			//verifier si negatif
		}
	}
	
	public void  diplayShortestPaths() {
		//Completer
	}

	public void displayTables() {
	 //Completer
	}
}
