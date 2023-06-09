/*******************************************************************************************************
 *
 * GamaAgentConverterNetwork.java, in gaml.extension.serialization, is part of the source code of the
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

import gama.core.metamodel.agent.IAgent;
import gama.core.metamodel.agent.MutableSavedAgent;
import gama.core.metamodel.agent.SavedAgent;
import gama.core.runtime.IScope;
import gama.dev.DEBUG;

/**
 * The Class GamaAgentConverterNetwork.
 */
public class GamaAgentConverterNetwork extends AbstractGamaConverter<IAgent, SavedAgent> {

	/**
	 * Instantiates a new gama agent converter network.
	 *
	 * @param target
	 *            the target
	 */
	public GamaAgentConverterNetwork(final Class<IAgent> target) {
		super(target);
	}

	@Override
	public void write(final IScope scope, final IAgent agt, final HierarchicalStreamWriter writer,
			final MarshallingContext context) {
		context.convertAnother(new SavedAgent(scope, agt));
		DEBUG.OUT("===========END ConvertAnother : GamaAgent Network");
	}

	@Override
	public SavedAgent read(final IScope scope, final HierarchicalStreamReader reader,
			final UnmarshallingContext context) {
		MutableSavedAgent msa = new MutableSavedAgent();
		SavedAgentProvider.push(msa);
		context.convertAnother(msa, SavedAgent.class);
		SavedAgentProvider.pop();
		return msa;
	}

}
