/*******************************************************************************************************
 *
 * WebHelperFactory.java, in gama.ui.shared, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.ui.shared.factories;

import org.eclipse.ui.services.AbstractServiceFactory;
import org.eclipse.ui.services.IServiceLocator;

import gama.ui.application.workbench.IWebHelper;
import gama.ui.shared.utils.WebHelper;

/**
 * A factory for creating WebHelper objects.
 */
public class WebHelperFactory extends AbstractServiceFactory {

	@Override
	public IWebHelper create(final Class serviceInterface, final IServiceLocator parentLocator,
			final IServiceLocator locator) {
		return WebHelper.getInstance();
	}

}
