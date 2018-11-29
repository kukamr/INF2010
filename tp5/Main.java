import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Main {
	public static void main(String[] args) {
		// Exerice 1: creation de graphe a partir du fichier 
		Graph g = new Graph();
		g.readFromFile("./graphe.txt",",");

		// Exerice 2: calcul du plus court chemin de source vers tous les sommet
		Bellman bl = new Bellman(g);
		Node source=g.getNodeByName("S");
		bl.setSourceNode(source);
		bl.shortestPath();
		bl.displayTables();
		// Exerice 3: affichage des plus courts chemins
		bl.diplayShortestPaths();
	}
}
