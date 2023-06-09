/*******************************************************************************************************
 *
 * IExperimentAgent.java, in gama.core, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.core.kernel.experiment;

import java.util.List;

import gama.core.kernel.simulation.SimulationAgent;
import gama.core.kernel.simulation.SimulationPopulation;
import gama.core.metamodel.population.IPopulationFactory;

/**
 * The Interface IExperimentAgent.
 */
public interface IExperimentAgent extends ITopLevelAgent {

	/**
	 * Sets the maximum duration.
	 *
	 * @param d
	 *            the new maximum duration
	 */
	void setMaximumDuration(Double d);

	/**
	 * Gets the maximum duration.
	 *
	 * @return the maximum duration
	 */
	Double getMaximumDuration();

	/**
	 * Gets the species.
	 *
	 * @return the species
	 */
	@Override
	IExperimentPlan getSpecies();

	/**
	 * Gets the working path.
	 *
	 * @return the working path
	 */
	String getWorkingPath();

	/**
	 * Gets the working paths.
	 *
	 * @return the working paths
	 */
	List<String> getWorkingPaths();
	//
	// /**
	// * Gets the warnings as errors. Deprecated
	// *
	// * @return the warnings as errors
	// */
	// Boolean getWarningsAsErrors();

	/**
	 * Gets the minimum duration.
	 *
	 * @return the minimum duration
	 */
	Double getMinimumDuration();

	/**
	 * Sets the minimum duration.
	 *
	 * @param d
	 *            the new minimum duration
	 */
	void setMinimumDuration(Double d);

	/**
	 * Close simulations.
	 */
	void closeSimulations();

	/**
	 * Close simulation.
	 *
	 * @param simulationAgent
	 *            the simulation agent
	 */
	void closeSimulation(SimulationAgent simulationAgent);

	/**
	 * Gets the simulation population.
	 *
	 * @return the simulation population
	 */
	SimulationPopulation getSimulationPopulation();

	/**
	 * Checks if is memorize.
	 *
	 * @return true, if is memorize
	 */
	boolean isMemorize();

	/**
	 * Can step back.
	 *
	 * @return true, if successful
	 */
	boolean canStepBack();

	/**
	 * Inform status.
	 */
	void informStatus();

	/**
	 * Checks if is headless.
	 *
	 * @return true, if is headless
	 */
	boolean isHeadless();

	/**
	 * Returns the population factory of this type of experiment -- default is a DefaultPopulationFactory
	 *
	 * @return the population factory
	 */
	IPopulationFactory getPopulationFactory();

}
