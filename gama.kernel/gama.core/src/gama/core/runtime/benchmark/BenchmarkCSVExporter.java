/*******************************************************************************************************
 *
 * BenchmarkCSVExporter.java, in gama.core, is part of the source code of the GAMA modeling and simulation platform
 * (v.1.9.2).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama for license information and contacts.
 *
 ********************************************************************************************************/
package gama.core.runtime.benchmark;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import gama.annotations.common.interfaces.IBenchmarkable;
import gama.core.common.util.FileUtils;
import gama.core.kernel.experiment.IExperimentPlan;
import gama.core.runtime.GAMA;
import gama.core.runtime.IScope;
import gama.core.runtime.exceptions.GamaRuntimeException;
import gama.core.util.GamaMapFactory;
import gama.core.util.IMap;
import gama.core.util.file.csv.CsvWriter;
import gama.core.util.tree.GamaTree.Order;
import gaml.core.operators.Files;
import gaml.core.types.Types;

/**
 * The Class BenchmarkCSVExporter.
 */
public class BenchmarkCSVExporter {

	/** The Constant exportFolder. */
	private static final String exportFolder = "benchmarks";

	/**
	 * Save.
	 *
	 * @param experiment
	 *            the experiment
	 * @param records
	 *            the records
	 */
	public void save(final IExperimentPlan experiment, final Benchmark records) {
		final IScope scope = experiment.getExperimentScope();
		try {
			Files.newFolder(scope, exportFolder);
		} catch (final GamaRuntimeException e1) {
			e1.addContext("Impossible to create folder " + exportFolder);
			GAMA.reportError(scope, e1, false);
			e1.printStackTrace();
			return;
		}
		final IMap<IScope, Benchmark.ScopeRecord> scopes = GamaMapFactory.wrap(Types.NO_TYPE, Types.NO_TYPE, records);
		final String exportFileName =
				FileUtils.constructAbsoluteFilePath(scope, exportFolder + "/" + experiment.getModel().getName()
						+ "_benchmark_" + Instant.now().toString().replace(':', '_') + ".csv", false);

		final List<String> headers = new ArrayList<>();
		final List<List<String>> contents = new ArrayList<>();
		headers.add("Execution");
		scopes.forEach((scopeRecord, record) -> {
			headers.add("Time in ms in " + scopeRecord);
			headers.add("Invocations in " + scopeRecord);
		});
		contents.add(headers);
		records.tree.visit(Order.PRE_ORDER, n -> {
			final IBenchmarkable r = n.getData();
			final List<String> line = new ArrayList<>();
			contents.add(line);
			line.add(r.getNameForBenchmarks());
			scopes.forEach((scope1, scopeRecord) -> {
				final BenchmarkRecord record1 = scopeRecord.find(r);
				line.add(record1.isUnrecorded() ? "" : String.valueOf(record1.milliseconds));
				line.add(record1.isUnrecorded() ? "" : String.valueOf(record1.times));
			});
		});

		try (final CsvWriter writer = new CsvWriter(exportFileName)) {
			writer.setDelimiter(';');
			for (final List<String> ss : contents) { writer.writeRecord(ss.toArray(new String[ss.size()])); }
		} catch (final IOException e) {
			throw GamaRuntimeException.create(e, scope);
		}
	}

}
