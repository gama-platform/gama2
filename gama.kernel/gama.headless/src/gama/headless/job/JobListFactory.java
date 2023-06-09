/*******************************************************************************************************
 *
 * JobListFactory.java, in gama.headless, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.headless.job;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import gama.annotations.common.interfaces.IKeyword;
import gama.core.kernel.experiment.IExperimentPlan;
import gama.core.kernel.model.IModel;
import gama.headless.core.GamaHeadlessException;
import gama.headless.core.HeadlessSimulationLoader;
import gaml.core.compilation.GAML;
import gaml.core.descriptions.ExperimentDescription;
import gaml.core.expressions.IExpression;
import gaml.core.types.Types;

/**
 * The Class JobPlan.
 */
public class JobListFactory {

	/**
	 * The JobPlanExperimentID.
	 */
	public record JobPlanExperimentID(String modelName, String experimentName) {}

	/**
	 * Construct all jobs.
	 *
	 * @param seeds
	 *            the seeds
	 * @param finalStep
	 *            the final step
	 * @return the list
	 * @throws GamaHeadlessException
	 * @throws IOException
	 */
	public static List<IExperimentJob> constructAllJobs(final String modelPath, final long[] seeds,
			final long finalStep, final Integer numberOfCores) throws IOException, GamaHeadlessException {
		IModel model = HeadlessSimulationLoader.loadModel(new File(modelPath), null);
		Map<JobPlanExperimentID, IExperimentJob> originalJobs = new LinkedHashMap<>();
		if (numberOfCores != null && numberOfCores > 0) {
			IExpression expr = GAML.getExpressionFactory().createConst(numberOfCores, Types.INT);
			for (IExperimentPlan plan : model.getExperiments()) { plan.setConcurrency(expr); }
		}
		for (final ExperimentDescription expD : model.getDescription().getExperiments()) {
			if (!IKeyword.BATCH.equals(expD.getLitteral(IKeyword.TYPE))) {
				final IExperimentJob tj = ExperimentJob.loadAndBuildJob(expD, model.getFilePath(), model);
				// TODO AD Why 12 ??
				tj.setSeed(12);
				originalJobs.put(new JobPlanExperimentID(tj.getModelName(), tj.getExperimentName()), tj);
			}
		}
		final List<IExperimentJob> jobs = new ArrayList<>();
		for (final IExperimentJob locJob : originalJobs.values()) {
			jobs.addAll(constructJobWithName(locJob, seeds, finalStep, null, null));
		}
		return jobs;
	}

	/**
	 * Construct job with name.
	 *
	 * @param originalExperiment
	 *            the original experiment
	 * @param seeds
	 *            the seeds
	 * @param finalStep
	 *            the final step
	 * @param in
	 *            the in
	 * @param out
	 *            the out
	 * @return the list
	 */
	private static List<IExperimentJob> constructJobWithName(final IExperimentJob originalExperiment,
			final long[] seeds, final long finalStep, final List<Parameter> in, final List<Output> out) {
		final List<IExperimentJob> res = new ArrayList<>();
		for (final long sd : seeds) {
			final IExperimentJob job = new ExperimentJob((ExperimentJob) originalExperiment);
			job.setSeed(sd);
			job.setFinalStep(finalStep);
			if (in != null) { for (final Parameter p : in) { job.setParameterValueOf(p.getName(), p.getValue()); } }
			if (out != null) {
				final List<String> availableOutputs = job.getOutputNames();
				for (final Output o : out) {
					job.setOutputFrameRate(o.getName(), o.getFrameRate());
					availableOutputs.remove(o.getName());
				}
				for (final String s : availableOutputs) { job.removeOutputWithName(s); }
			}

			res.add(job);
		}
		return res;
	}

}
