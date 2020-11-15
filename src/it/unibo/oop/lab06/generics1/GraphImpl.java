package it.unibo.oop.lab06.generics1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphImpl<N> implements Graph<N> {
	
	private Map<N, Set<N>> graph;
	
	public GraphImpl() {
		this.graph = new LinkedHashMap<>();
	}
	
	public void addNode(final N node) {
		this.graph.putIfAbsent(node, new HashSet<>());
	}
	
	public void addEdge(final N source, final N target) {
		if (this.graph.containsKey(source) && this.graph.containsKey(target)) {
			this.graph.get(source).add(target);
		}
	}
	
	public Set<N> nodeSet() {
		return new HashSet<>(this.graph.keySet());
	}

	public Set<N> linkedNodes(final N node) {
		return new HashSet<>(this.graph.get(node));
	}

	public List<N> getPath(final N source, final N target) {
		List<N> path = new ArrayList<>();
		Set<N> linkedNodes = this.linkedNodes(source);
		
		path.add(source);
		if (linkedNodes.contains(target)) {
			path.add(target);
			return path;
		} else {
			for (int i = 1; i < this.graph.size(); i++) {
				for (N elem : linkedNodes) {
					if (elem.equals(target)) {
						path.add(elem);
						return path;
					}
					if (!path.contains(elem)) {
						if (!this.linkedNodes(elem).isEmpty()) {
							linkedNodes = this.linkedNodes(elem);
							path.add(elem);
							break;
						}
					}
				}
			}
		}
		return null;
	}

}
