/*******************************************************************************************************
 *
 * ShowHideParametersViewHandler.java, in gama.ui.experiment, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.ui.experiment.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import gama.core.common.interfaces.IGui;
import gama.core.runtime.GAMA;
import gama.ui.shared.utils.ViewsHelper;

/**
 * The Class ShowHideParametersViewHandler.
 */
public class ShowHideParametersViewHandler extends AbstractHandler {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		if (ViewsHelper.findView(IGui.PARAMETER_VIEW_ID, null, false) == null) {
			GAMA.getGui().showAndUpdateParameterView(null, GAMA.getExperiment());
		} else {
			GAMA.getGui().hideParameters();
		}
		return null;
	}
}
