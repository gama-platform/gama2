/*******************************************************************************************************
 *
 * RuntimeExceptionHandlerFactory.java, in gama.ui.experiment, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.ui.experiment.factories;

import org.eclipse.ui.services.AbstractServiceFactory;
import org.eclipse.ui.services.IServiceLocator;

import gama.ui.experiment.commands.RuntimeExceptionHandler;
import gama.core.common.interfaces.IRuntimeExceptionHandler;

/**
 * A factory for creating RuntimeExceptionHandler objects.
 */
public class RuntimeExceptionHandlerFactory extends AbstractServiceFactory {

	/** The handler. */
	IRuntimeExceptionHandler handler;

	/**
	 * Gets the handler.
	 *
	 * @return the handler
	 */
	public IRuntimeExceptionHandler getHandler() {
		if (handler == null) {
			handler = new RuntimeExceptionHandler();
		}
		return handler;
	}

	@Override
	public Object create(final Class serviceInterface, final IServiceLocator parentLocator,
			final IServiceLocator locator) {
		return getHandler();
	}

}
