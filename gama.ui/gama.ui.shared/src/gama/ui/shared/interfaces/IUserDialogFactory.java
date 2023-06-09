/*******************************************************************************************************
 *
 * IUserDialogFactory.java, in gama.ui.shared, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.ui.shared.interfaces;

import gama.core.runtime.IScope;
import gaml.core.architecture.user.UserPanelStatement;

/**
 * A factory for creating IUserDialog objects.
 */
public interface IUserDialogFactory {

	/**
	 * Open user dialog.
	 *
	 * @param scope the scope
	 * @param panel the panel
	 */
	void openUserDialog(IScope scope, UserPanelStatement panel);

	/**
	 * Close user dialog.
	 */
	void closeUserDialog();
}