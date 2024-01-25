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
		TerrorZone selection = (TerrorZone) gui.getComboBoxTerrorZone().getSelectedItem();
		TerrorZoneHandler handler = TerrorZoneHandler.getInstance();
		if (selection == TerrorZone.RANDOM) {
			List<TerrorZone> possibleZones = new ArrayList<TerrorZone>();
			possibleZones.addAll(Arrays.asList(TerrorZone.values()));
			possibleZones.remove(TerrorZone.RANDOM);
			Collections.shuffle(possibleZones);
			selection = possibleZones.get(0);
		}
		System.out.println(selection);
		handler.applyTerrorZone(selection, gui.getCharLevel());
		handler.writeChanges();
		gui.getComboBoxTerrorZone().setSelectedIndex(selection.ordinal());
		gui.repaintGUI();

	}

}
