/*******************************************************************************************************
 *
 * GamaGraphReducer.java, in gaml.extension.serialization, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gaml.extension.serialization.type.reduced;

import gama.core.kernel.simulation.SimulationAgent;
import gama.core.metamodel.topology.graph.GamaSpatialGraph;
import gama.core.runtime.IScope;
import gama.core.util.GamaListFactory;
import gama.core.util.GamaMapFactory;
import gama.core.util.IList;
import gama.core.util.IMap;
import gama.core.util.IReference;
import gama.core.util.graph.GamaGraph;
import gama.core.util.graph.IGraph;
import gaml.core.types.GamaGraphType;
import gaml.core.types.IType;
import gaml.core.types.Types;
import gaml.extension.serialization.type.reference.ReferenceGraph;

/**
 * The Class GamaGraphReducer.
 */
@SuppressWarnings ({ "rawtypes" })
public class GamaGraphReducer {

	/** The node type graph reducer. */
	private final IType nodeTypeGraphReducer;

	/** The edge type graph reducer. */
	private final IType edgeTypeGraphReducer;

	/** The edges graph reducer. */
	// private GamaMap valuesGraphReducer;
	private IList edgesGraphReducer;

	/** The edges weights graph reducer. */
	private IMap edgesWeightsGraphReducer;

	/** The spatial. */
	private final boolean spatial;

	/** The directed. */
	private final boolean directed;

	/**
	 * Instantiates a new gama graph reducer.
	 *
	 * @param scope
	 *            the scope
	 * @param g
	 *            the g
	 */
	@SuppressWarnings ("unchecked")
	public GamaGraphReducer(final IScope scope, final IGraph<?, ?> g) {
		spatial = g instanceof GamaSpatialGraph;
		directed = g.isDirected();

		nodeTypeGraphReducer = g.getGamlType().getKeyType();
		edgeTypeGraphReducer = g.getGamlType().getContentType();

		// Map of keys = pair(source,target), values = edge
		// valuesGraphReducer = g.mapValue(scope, nodeTypeGraphReducer, edgeTypeGraphReducer, false);
		edgesGraphReducer = GamaListFactory.create(scope, edgeTypeGraphReducer, g.edgeSet());

		// edgesWeightsGraphReducer = new GamaMap<>(valuesGraphReducer.capacity(), edgeTypeGraphReducer, new
		// GamaPairType());
		edgesWeightsGraphReducer = GamaMapFactory.create(edgeTypeGraphReducer, Types.PAIR, edgesGraphReducer.size());

		// for (final Object edge : valuesGraphReducer.values()) {
		for (final Object edge : edgesGraphReducer) {
			// edgesWeightsGraphReducer.put(k.getKey(), new EdgeReducer(k.getValue(), g.getWeightOf(k.getValue())));
			edgesWeightsGraphReducer.put(edge, g.getWeightOf(edge));
		}

	}

	/**
	 * Gets the edges graph reducer.
	 *
	 * @return the edges graph reducer
	 */
	// public GamaMap getValuesGraphReducer() {return valuesGraphReducer; }
	public IList getEdgesGraphReducer() { return edgesGraphReducer; }

	/**
	 * Gets the weights graph reducer.
	 *
	 * @return the weights graph reducer
	 */
	public IMap getWeightsGraphReducer() { return edgesWeightsGraphReducer; }

	/**
	 * Sets the edges graph reducer.
	 *
	 * @param m
	 *            the new edges graph reducer
	 */
	// public void setValuesGraphReducer(GamaMap m) { valuesGraphReducer = m; }
	public void setEdgesGraphReducer(final IList m) { edgesGraphReducer = m; }

	/**
	 * Sets the edges weights graph reducer.
	 *
	 * @param w
	 *            the w
	 */
	public void setEdgesWeightsGraphReducer(final IMap<Object, Object> w) { edgesWeightsGraphReducer = w; }

	/**
	 * Construct object.
	 *
	 * @param scope
	 *            the scope
	 * @return the gama graph
	 */
	public IGraph constructObject(final IScope scope) {
		// GamaGraph graph = (GamaGraph) GamaGraphType.from(scope, valuesGraphReducer, spatial);
		GamaGraph graph;
		// if(IReference.isReference(valuesGraphReducer) || IReference.isReference(edgesWeightsGraphReducer)) {
		if (IReference.isReference(edgesGraphReducer) || IReference.isReference(edgesWeightsGraphReducer)) {
			graph = new ReferenceGraph(this);
		} else {
			// graph = (GamaGraph) GamaGraphType.from(scope, valuesGraphReducer.getValues(),
			graph = (GamaGraph) GamaGraphType.from(scope, edgesGraphReducer, true, directed, spatial,
					nodeTypeGraphReducer, edgeTypeGraphReducer);

			graph.setWeights(edgesWeightsGraphReducer);
			// for (final Object el : edgesWeightsGraphReducer.entrySet()) {
			// Map.Entry entry = (Map.Entry) el;
			// graph.setEdgeWeight(e, weight);
			// }
		}
		return graph;
	}

	/**
	 * Unreference reducer.
	 *
	 * @param sim
	 *            the sim
	 */
	@SuppressWarnings ("unchecked")
	public void unreferenceReducer(final SimulationAgent sim) {
		// valuesGraphReducer = (GamaMap)IReference.getObjectWithoutReference(valuesGraphReducer,sim);
		edgesGraphReducer = (IList) IReference.getObjectWithoutReference(edgesGraphReducer, sim);
		edgesWeightsGraphReducer = (IMap) IReference.getObjectWithoutReference(edgesWeightsGraphReducer, sim);
	}
}

/*
 * class EdgeReducer { private Object edge; private double weight;
 *
 * public EdgeReducer(Object _o, double _w) { edge = _o; weight = _w; } }
 */
