package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import core.TerrorZoneHandler;
import gui.DesecratorGUI;

public class ChaosButtonAction extends AbstractAction {

	private static final long serialVersionUID = -2853653915662613129L;
	DesecratorGUI gui;

	public ChaosButtonAction(DesecratorGUI gui) {
		putValue(Action.NAME, "TOTAL CHAOS");
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gui.getStatusPanel().declareWorking();
		TerrorZoneHandler handler = TerrorZoneHandler.getInstance();
		TerrorZoneHandler.modPath = gui.getModPath();
		handler.applyChaos(gui.getCharLevel());
		int errorLevel = handler.writeChanges();
		gui.getStatusPanel().setStatusColor(errorLevel);
		gui.getComboBoxTerrorZone().setSelectedIndex(-1);
		gui.repaintComboBox();
	}
}
