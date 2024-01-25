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
		TerrorZoneHandler handler = TerrorZoneHandler.getInstance();
		handler.applyChaos(gui.getCharLevel());
		handler.writeChanges();
		gui.getComboBoxTerrorZone().setSelectedIndex(-1);
		gui.repaintGUI();
	}
}
