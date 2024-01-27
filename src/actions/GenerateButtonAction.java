package actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import core.TerrorZone;
import core.TerrorZoneHandler;
import gui.DesecratorGUI;

public class GenerateButtonAction extends AbstractAction {

	private static final long serialVersionUID = -1194709445424621014L;
	DesecratorGUI gui;

	public GenerateButtonAction(DesecratorGUI plugYTerrorZoneGUI) {
		putValue(Action.NAME, "Generate");
		this.gui = plugYTerrorZoneGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gui.getStatusPanel().declareWorking();
		TerrorZone selection = (TerrorZone) gui.getComboBoxTerrorZone().getSelectedItem();
		if (selection == null) {
			gui.getStatusPanel().setStatusColor(2);
			return;
		}
		TerrorZoneHandler handler = TerrorZoneHandler.getInstance();
		if (selection == TerrorZone.RANDOM) {
			List<TerrorZone> possibleZones = new ArrayList<TerrorZone>();
			possibleZones.addAll(Arrays.asList(TerrorZone.values()));
			possibleZones.remove(TerrorZone.RANDOM);
			Collections.shuffle(possibleZones);
			selection = possibleZones.get(0);
		}

		handler.applyTerrorZone(selection, gui.getCharLevel());
		int errorLevel = handler.writeChanges();
		gui.getStatusPanel().setStatusColor(errorLevel);
		gui.getComboBoxTerrorZone().setSelectedIndex(selection.ordinal());
		gui.repaintComboBox();

	}

}
