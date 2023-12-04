# Kruskal-s-AlgorithmUnionFind

Kruskal’s Algorithm

UnionFindAlgo Class Documentation

Overview
The UnionFindAlgo class is designed to compute the Minimum Spanning Tree (MST) of a weighted graph using Kruskal's algorithm. The class includes nested classes for graph Edge and Subset, and implements the Union-Find algorithm to detect cycles while building the MST.

Class Structure:


# Inner Class: Edge

Description: Represents an edge in the graph.
Attributes:
int src: Source vertex of the edge.
int dest: Destination vertex of the edge.
int weight: Weight of the edge.
Method:
int compareTo(Edge): Compares this edge with another edge based on their weights for sorting.
# Inner Class: Subset

Description: Represents a subset for the Union-Find algorithm.
Attributes:
int parent: The parent of the subset.
int rank: The rank of the subset.
Attributes of UnionFindAlgo:

int vertices: The number of vertices in the graph.
int edges: The number of edges in the graph.
Edge[] edge: Array of edges in the graph.
Constructor:

UnionFindAlgo(int v, int e): Initializes a graph with v vertices and e edges.
Methods:

int find(Subset[], int): Finds the parent of a subset using path compression.
void union(Subset[], int, int): Performs a union of two subsets based on rank.
void kruskalMST(): Computes the MST using Kruskal's algorithm and prints the edges in the MST and its total weight.
Usage
Initialization:

Create an instance of UnionFindAlgo with the number of vertices and edges.
Initialize the graph edges with their respective source, destination, and weight.
Finding the MST:

Call the kruskalMST() method on the graph instance to compute and print the MST.
