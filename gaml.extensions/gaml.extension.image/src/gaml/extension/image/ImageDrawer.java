/*******************************************************************************************************
 *
 * ImageDrawer.java, in gaml.extension.image, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gaml.extension.image;

import java.awt.geom.Rectangle2D;

import gama.core.runtime.IScope.IGraphicsScope;
import gama.core.runtime.exceptions.GamaRuntimeException;
import gaml.core.expressions.IExpression;
import gaml.core.statements.draw.AssetDrawer;
import gaml.core.statements.draw.DrawingData;
import gaml.core.types.IType;
import gaml.core.types.Types;

/**
 * The Class ImageDrawer.
 */
public class ImageDrawer extends AssetDrawer {

	/**
	 * Execute on.
	 *
	 * @param scope
	 *            the scope
	 * @param data
	 *            the data
	 * @param items
	 *            the items
	 * @return the rectangle 2 D
	 * @throws GamaRuntimeException
	 *             the gama runtime exception
	 */
	@Override
	public Rectangle2D executeOn(final IGraphicsScope scope, final DrawingData data, final IExpression... items)
			throws GamaRuntimeException {
		return super.executeOn(scope, data, items);
	}

	/**
	 * Type drawn.
	 *
	 * @return the i type
	 */
	@Override
	public IType<?> typeDrawn() {
		return Types.get(GamaImageType.ID);
	}

}
