import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Graph {

	private List<Node> nodes = new ArrayList<Node>(); // Noeuds
	private List<Edge> edges = new ArrayList<Edge>();; // Les arcs
	static final double inf = 99999;
	public Graph() {
		
	}

	public void readFromFile(String filePath,String separtor){
		
		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(filePath);
			br = new BufferedReader(fr);

			String sCurrentLine;

			sCurrentLine = br.readLine();
			String[] noeuds = sCurrentLine.split(separtor);

			Integer id = 0;

			//Creation des noeuds
			for(String node : noeuds){
				nodes.add(new Node(id++, node));
			}

			id = 0;

			while ((sCurrentLine = br.readLine()) != null) {
				//Creer les arcs
				String[] distances = sCurrentLine.split(separtor);

				for(int i = 0; i < distances.length; i++){
					String dist = distances[i];
				
					if(!dist.equals("inf"))
						edges.add(new Edge(nodes.get(id), nodes.get(i), Double.parseDouble(dist)));
				}

				id++;
				
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
	}
	
	public List<Edge> getOutEdges(Node source) {
		List<Edge> outEdges = new ArrayList<Edge>();
		
		for (Edge e : edges) {
			if(e.getSource() == source)
				outEdges.add(e);
		}
		
		return outEdges;	
	}
	
	public List<Edge> getInEdges(Node dest) {
		List<Edge> inEdges = new ArrayList<Edge>(); 

		for (Edge e : edges) {
			if(e.getDestination() == dest && e.getSource() != dest)
				inEdges.add(e);
		}
		return inEdges;		
	}

	// Accesseurs 
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public List<Edge> getEdges() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	public Node getNodeByName(String name){
		for (Node node : nodes) {
			if(node.getName().equals(name)){
				return node;
			}
		}
		return null;
	}
	
	public Node getNodeById(int id){
		for (Node node : nodes) {
			if(node.getId()==id){
				return node;
			}

		}
		return null;
	}
}
