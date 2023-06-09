/*******************************************************************************************************
 *
 * GamaRegressionType.java, in gaml.extension.stats, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gaml.extension.stats;

import gama.annotations.precompiler.IConcept;
import gama.annotations.precompiler.ISymbolKind;
import gama.annotations.precompiler.GamlAnnotations.doc;
import gama.annotations.precompiler.GamlAnnotations.type;
import gama.core.runtime.IScope;
import gama.core.runtime.exceptions.GamaRuntimeException;
import gaml.core.types.GamaType;
import gaml.core.types.IType;

/**
 * The Class GamaRegressionType.
 */
@type (
		name = "regression",
		id = IType.REGRESSION,
		wraps = { GamaRegression.class },
		kind = ISymbolKind.Variable.REGULAR,
		concept = { IConcept.TYPE },
		doc = { @doc (
				value = "Type of variables that enables to learn a regression function and to use it to predict new values") })
public class GamaRegressionType extends GamaType<GamaRegression> {

	@Override
	public boolean canCastToConst() {
		return true;
	}

	@Override
	@doc ("Returns the argument if it is a regression, otherwise nil")
	public GamaRegression cast(final IScope scope, final Object obj, final Object param, final boolean copy)
			throws GamaRuntimeException {
		if (obj instanceof GamaRegression) return (GamaRegression) obj;
		return null;
	}

	@Override
	public GamaRegression getDefault() { return null; }

}
