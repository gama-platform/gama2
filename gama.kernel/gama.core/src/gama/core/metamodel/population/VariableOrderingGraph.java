/*******************************************************************************************************
 *
 * VariableOrderingGraph.java, in gama.core, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.core.metamodel.population;

import java.util.Map;

import org.jgrapht.graph.DirectedAcyclicGraph;
import org.jgrapht.util.SupplierUtil;

import gama.core.util.GamaMapFactory;

public class VariableOrderingGraph extends DirectedAcyclicGraph<String, Object> {

	protected VariableOrderingGraph() {
		super(null, SupplierUtil.createSupplier(Object.class), new VisitedBitSetImpl(), new TopoMap<String>(), false,
				false);
	}

	protected static class TopoMap<V> implements TopoOrderMap<V> {

		private final Map<Integer, V> topoToVertex = GamaMapFactory.create();
		private final Map<V, Integer> vertexToTopo = GamaMapFactory.create();

		@Override
		public void putVertex(final Integer index, final V vertex) {
			topoToVertex.put(index, vertex);
			vertexToTopo.put(vertex, index);
		}

		@Override
		public V getVertex(final Integer index) {
			return topoToVertex.get(index);
		}

		@Override
		public Integer getTopologicalIndex(final V vertex) {
			return vertexToTopo.get(vertex);
		}

		@Override
		public Integer removeVertex(final V vertex) {
			Integer topoIndex = vertexToTopo.remove(vertex);
			if (topoIndex != null) { topoToVertex.remove(topoIndex); }
			return topoIndex;
		}

		@Override
		public void removeAllVertices() {
			vertexToTopo.clear();
			topoToVertex.clear();
		}

	}

}
