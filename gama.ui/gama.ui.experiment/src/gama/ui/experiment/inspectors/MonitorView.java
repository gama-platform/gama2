/*******************************************************************************************************
 *
 * MonitorView.java, in gama.ui.experiment, is part of the source code of the GAMA modeling and simulation platform
 * (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 *
 ********************************************************************************************************/
package gama.ui.experiment.inspectors;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import gama.core.common.interfaces.IValue;
import gama.core.common.interfaces.ItemList;
import gama.core.metamodel.agent.IAgent;
import gama.core.outputs.IDisplayOutput;
import gama.core.outputs.MonitorOutput;
import gama.core.outputs.ValuedDisplayOutputFactory;
import gama.core.runtime.IScope;
import gama.core.runtime.exceptions.GamaRuntimeException;
import gama.core.util.GamaColor;
import gama.dev.COUNTER;
import gama.ui.shared.parameters.EditorFactory;
import gama.ui.shared.parameters.EditorsGroup;
import gama.ui.shared.resources.GamaColors;
import gama.ui.shared.resources.IGamaIcons;
import gama.ui.shared.utils.WorkbenchHelper;
import gama.ui.shared.views.ExpandableItemsView;
import gama.ui.shared.views.toolbar.GamaToolbar2;
import gama.ui.shared.views.toolbar.GamaToolbarFactory;
import gama.ui.shared.views.toolbar.IToolbarDecoratedView;
import gaml.core.compilation.GAML;
import gaml.core.expressions.IExpression;
import gaml.core.expressions.IExpressionFactory;
import gaml.core.types.IType;
import gaml.core.types.Types;

/**
 * @author Alexis Drogoul
 */
@SuppressWarnings ({ "rawtypes", "unchecked" })
public class MonitorView extends ExpandableItemsView<MonitorOutput> implements IToolbarDecoratedView.Pausable {

	/**
	 * Own create part control.
	 *
	 * @param parent
	 *            the parent
	 */
	@Override
	public void ownCreatePartControl(final Composite parent) {
		displayItems();
	}

	/**
	 * Needs output.
	 *
	 * @return true, if successful
	 */
	@Override
	protected boolean needsOutput() {
		return false;
	}

	/**
	 * Adds the output.
	 *
	 * @param output
	 *            the output
	 */
	@Override
	public void addOutput(final IDisplayOutput output) {
		super.addOutput(output);
		addItem((MonitorOutput) output);
	}

	/**
	 * Adds the item.
	 *
	 * @param output
	 *            the output
	 * @return true, if successful
	 */
	@Override
	public boolean addItem(final MonitorOutput output) {
		if (output != null) {
			createItem(getParentComposite(), output, output.getValue() == null,
					output.getColor(null) == null ? null : GamaColors.get(output.getColor(null)));
			return true;
		}
		return false;

	}

	/**
	 * Creates the item contents for.
	 *
	 * @param output
	 *            the output
	 * @return the composite
	 */
	@Override
	protected Composite createItemContentsFor(final MonitorOutput output) {
		final EditorsGroup compo = new EditorsGroup(getViewer(), SWT.NONE);
		final Text titleEditor =
				(Text) EditorFactory.create(output.getScope(), compo, "Title:", output.getName(), true, newValue -> {
					output.setName(newValue);
					update(output);
				}).getEditor();

		IExpression expr;
		try {
			expr = GAML.compileExpression(output.getExpressionText(), output.getScope().getSimulation(), true);
		} catch (GamaRuntimeException e1) {
			// The expression is maybe dedicated to experiments (and not simulations) ?
			expr = GAML.compileExpression(output.getExpressionText(), output.getScope().getExperiment(), true);
		}

		final Text c = (Text) EditorFactory.createExpression(output.getScope(), compo, "Expression:",
				output.getValue() == null ? IExpressionFactory.NIL_EXPR : expr, newValue -> {
					output.setNewExpression((IExpression) newValue);
					update(output);
				}, Types.NO_TYPE).getEditor();

		c.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(final SelectionEvent e) {}

			@Override
			public void widgetDefaultSelected(final SelectionEvent e) {
				getViewer().collapseItemWithData(output);
			}

		});
		titleEditor.addModifyListener(evt -> {
			output.setName(titleEditor.getText());
			update(output);
		});
		// outputs.add(output);
		// update(output);
		return compo;
	}

	/**
	 * Removes the item.
	 *
	 * @param o
	 *            the o
	 */
	@Override
	public void removeItem(final MonitorOutput o) {
		o.close();
		removeOutput(o);
	}

	/**
	 * Resume item.
	 *
	 * @param o
	 *            the o
	 */
	@Override
	public void resumeItem(final MonitorOutput o) {
		if (o.isPaused()) { o.setPaused(false); }
		update(o);
	}

	/**
	 * Pause item.
	 *
	 * @param o
	 *            the o
	 */
	@Override
	public void pauseItem(final MonitorOutput o) {
		o.setPaused(true);
		update(o);
	}

	/**
	 * Gets the item display name.
	 *
	 * @param o
	 *            the o
	 * @param previousName
	 *            the previous name
	 * @return the item display name
	 */
	@Override
	public String getItemDisplayName(final MonitorOutput o, final String previousName) {
		final StringBuilder sb = new StringBuilder(100);
		sb.setLength(0);
		sb.append(o.getName()).append(ItemList.SEPARATION_CODE).append(getValueAsString(o));
		if (o.isPaused()) { sb.append(" (paused)"); }
		return sb.toString();

	}

	/**
	 * Gets the value as string.
	 *
	 * @param o
	 *            the o
	 * @return the value as string
	 */
	public String getValueAsString(final MonitorOutput o) {
		final Object v = o.getLastValue();
		return v == null ? "nil" : v instanceof IValue ? ((IValue) v).serialize(true) : v.toString();
	}

	/**
	 * Gets the item display color.
	 *
	 * @param o
	 *            the o
	 * @return the item display color
	 */
	@Override
	public GamaColor getItemDisplayColor(final MonitorOutput o) {
		return o.getColor(null);
	}

	/**
	 * Creates the new monitor.
	 *
	 * @param scope
	 *            the scope
	 */
	@SuppressWarnings ("unused")
	public static void createNewMonitor(final IScope scope) {
		// TODO ADD the possibility to do it in several simulations
		new MonitorOutput(scope, "monitor" + COUNTER.COUNT(), "");
	}

	/**
	 * Reset.
	 */
	@Override
	public void reset() {
		disposeViewer();
		outputs.clear();
	}

	/**
	 * Focus item.
	 *
	 * @param data
	 *            the data
	 */
	@Override
	public void focusItem(final MonitorOutput data) {
		outputs.remove(data);
		outputs.add(0, data);
	}

	/**
	 * Are items closable.
	 *
	 * @return true, if successful
	 */
	@Override
	protected boolean areItemsClosable() {
		return true;
	}

	/**
	 * Are items pausable.
	 *
	 * @return true, if successful
	 */
	@Override
	protected boolean areItemsPausable() {
		return true;
	}

	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	@Override
	public List getItems() { return outputs; }

	/**
	 * Update item values.
	 *
	 * @param synchronously
	 *            the synchronously
	 */
	@Override
	public void updateItemValues(final boolean synchronously) {}

	/**
	 * Creates the tool items.
	 *
	 * @param tb
	 *            the tb
	 */
	@Override
	public void createToolItems(final GamaToolbar2 tb) {
		super.createToolItems(tb);
		tb.sep(GamaToolbarFactory.TOOLBAR_SEP, SWT.RIGHT);
		tb.button(IGamaIcons.MENU_ADD_MONITOR, "Add new monitor", "Add new monitor",
				e -> createNewMonitor(getOutput().getScope()), SWT.RIGHT);
	}

	@Override
	public void pauseChanged() {}

	/**
	 * Method handleMenu()
	 *
	 * @see gama.core.common.interfaces.ItemList#handleMenu(java.lang.Object, int, int)
	 */
	@Override
	public Map<String, Runnable> handleMenu(final MonitorOutput data, final int x, final int y) {
		final Map<String, Runnable> menu = new HashMap();
		final IExpression exp = data.getValue();
		if (exp == null) return null;
		final IType<?> type = exp.getGamlType();
		menu.put("Copy to clipboard", () -> { WorkbenchHelper.copy(getValueAsString(data)); });
		if (type.isNumber() || type.isContainer() && type.getContentType().isNumber()) {
			// menu.put("Open chart", () -> {});
			menu.put("Save as CSV", () -> { data.saveHistory(); });
		} else if (type.isAgentType()) {
			menu.put("Inspect", () -> { data.getScope().getGui().setSelectedAgent((IAgent) data.getLastValue()); });
		} else if (type.isContainer() && type.getContentType().isAgentType()) {
			menu.put("Browse",
					() -> { ValuedDisplayOutputFactory.browse((Collection<? extends IAgent>) data.getLastValue()); });
		}
		return menu;
	}

}
