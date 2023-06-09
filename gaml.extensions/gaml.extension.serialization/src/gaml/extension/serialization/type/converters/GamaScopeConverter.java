/*******************************************************************************************************
 *
 * GamaScopeConverter.java, in gaml.extension.serialization, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gaml.extension.serialization.type.converters;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import gama.core.kernel.experiment.ExperimentAgent;
import gama.core.metamodel.agent.IAgent;
import gama.core.runtime.IScope;
import gama.dev.DEBUG;

/**
 * The Class GamaScopeConverter.
 */
public class GamaScopeConverter extends AbstractGamaConverter<IScope, String> {

	/**
	 * Instantiates a new gama scope converter.
	 *
	 * @param target
	 *            the target
	 */
	public GamaScopeConverter(final Class<IScope> target) {
		super(target);
	}

	@Override
	public void write(final IScope scope, final IScope scopeToSave, final HierarchicalStreamWriter writer,
			final MarshallingContext context) {
		writer.startNode("IScope");
		writer.setValue(scopeToSave.getName().toString());
		writer.endNode();
		// The experiment ???
		writer.startNode("Simulations");
		final ExperimentAgent expAgt = (ExperimentAgent) scopeToSave.getExperiment();
		for (final IAgent agt : expAgt.getSimulationPopulation()) {
			// Each simulation
			DEBUG.OUT("ConvertAnother : ScopeConverter " + agt.getClass());
			context.convertAnother(agt);
		}
		writer.endNode();
	}

	@Override
	public String read(final IScope scope, final HierarchicalStreamReader reader, final UnmarshallingContext arg1) {
		reader.moveDown();
		try {
			return reader.getValue();
		} finally {
			reader.moveUp();
		}
	}

}
