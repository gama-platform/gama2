/*******************************************************************************************************
 *
 * CreateFromSavedSimulationDelegate.java, in gaml.extension.serialization, is part of the source code of the GAMA
 * modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 *
 ********************************************************************************************************/

package gaml.extension.serialization.gaml;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;

import gama.core.common.interfaces.ICreateDelegate;
import gama.core.kernel.simulation.SimulationPopulation;
import gama.core.metamodel.agent.SavedAgent;
import gama.core.runtime.IScope;
import gama.core.util.GamaListFactory;
import gaml.core.statements.Arguments;
import gaml.core.statements.CreateStatement;
import gaml.core.types.IType;
import gaml.core.types.Types;
import gaml.extension.serialization.factory.StreamConverter;
import gaml.extension.serialization.type.converters.ConverterScope;

/**
 * Class CreateFromSavecSimulationDelegate.
 *
 * @author bgaudou
 * @since 18 July 2018
 *
 */

@SuppressWarnings ({ "unchecked", "rawtypes" })
public class CreateFromSavedSimulationDelegate implements ICreateDelegate {

	@Override
	public boolean acceptSource(final IScope scope, final Object source) {
		return source instanceof GamaSavedSimulationFile;
	}

	@Override
	public boolean createFrom(final IScope scope, final List<Map<String, Object>> inits, final Integer max,
			final Object source, final Arguments init, final CreateStatement statement) {
		final GamaSavedSimulationFile file = (GamaSavedSimulationFile) source;

		SimulationPopulation pop = scope.getExperiment().getSimulationPopulation();

		if (pop == null) { pop = (SimulationPopulation) GamaListFactory.EMPTY_LIST; }
		// final boolean hasSequence = sequence != null && !sequence.isEmpty();
		boolean shouldBeScheduled = false;

		// Create an empty new simulation, that is necesssary to load the SavedAgents.
		List<Map<String, Object>> mock_inits = GamaListFactory.create(Types.MAP, 1);
		mock_inits.add(Collections.EMPTY_MAP);
		pop.createAgents(scope, 1, mock_inits, false, shouldBeScheduled, null);

		final XStream xstream = StreamConverter.loadAndBuild(scope, ConverterScope.class);

		String stringFile = file.getBuffer().get(0);
		final SavedAgent saveAgt = (SavedAgent) xstream.fromXML(stringFile);

		HashMap mapSavedAgt = new HashMap<String, Object>();
		mapSavedAgt.put("SavedAgent", saveAgt);

		// Dispose the empty simulation, created only to read/load the saved agent
		scope.getSimulation().dispose();

		inits.add(mapSavedAgt);

		// scope.getSimulation().dispose();

		return true;
	}

	@Override
	public IType fromFacetType() {
		return Types.FILE;
	}
}
