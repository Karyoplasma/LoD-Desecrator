package actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import core.TerrorZoneHandler;
import gui.DesecratorGUI;

public class ResetButtonAction extends AbstractAction {

	private static final long serialVersionUID = -6941648632110794855L;
	DesecratorGUI gui;
	
	public ResetButtonAction(DesecratorGUI plugYTerrorZoneGUI) {
		putValue(Action.NAME, "Reset");
		this.gui = plugYTerrorZoneGUI;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		TerrorZoneHandler.resetTerrorZones();
		gui.getComboBoxTerrorZone().setSelectedIndex(-1);
		gui.repaintGUI();
	}

}
