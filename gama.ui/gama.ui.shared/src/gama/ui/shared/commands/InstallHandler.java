/*******************************************************************************************************
 *
 * InstallHandler.java, in gama.ui.shared, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.ui.shared.commands;

import static gama.ui.shared.utils.WorkbenchHelper.getCommand;
import static gama.ui.shared.utils.WorkbenchHelper.runCommand;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.internal.AbstractEnabledHandler;

import gama.core.runtime.GAMA;
import gama.ui.application.workspace.WorkspacePreferences;

/**
 * The Class InstallHandler.
 */
public class InstallHandler extends AbstractEnabledHandler implements IHandler {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		runCommand(getCommand("org.eclipse.equinox.p2.ui.sdk.install"), event);
		WorkspacePreferences.forceWorkspaceRebuild();
		GAMA.getGui().refreshNavigator();
		return this;
	}

}
