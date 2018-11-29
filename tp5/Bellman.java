import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import javax.lang.model.util.ElementScanner6;

import javafx.scene.Node;


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
		
		for(Node n : graph.getNodes()){	

			piTable.add(new Vector<Double>());		//Ajoute un vecteur pour chaque noeud
			
			piTable.get(n.hashCode()).add( n.equals(sourceNode) ? 0.0 : (double)inf ); //Ajoute 0 au vecteur du sourceNode, et infini sinon

			rTable.add(new Vector<Integer>());		// Ajoute un vecteur pour chaque noeud

			rTable.get(n.hashCode()).add( inf );	// Ajoute infini au vecteur de chaque noeud
		}

		Integer k = 0;

		for(;;){
			
			k += 1;

			List<Vector<Double>> piKMinusOne = piTable[k - 1];

			for (Node node : graph.getNodes()) { 
				Double kMinusOneValue = 
			}
				
		}

		
	}
	
	public void  diplayShortestPaths() {
		//Completer
	}

	public void displayTables() {
	 //Completer
	}
}
