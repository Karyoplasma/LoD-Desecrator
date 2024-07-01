package actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Path;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import core.IniHandler;
import gui.DesecratorGUI;

public class SelectButtonAction extends AbstractAction {
	private static final long serialVersionUID = 8821818526461559027L;
	private DesecratorGUI gui;

	public SelectButtonAction(DesecratorGUI desecratorGUI) {
		putValue(Action.NAME, "Select");
		this.gui = desecratorGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setDialogTitle("Select the 'data/global/excel' Directory");

		while (true) {
			int returnValue = chooser.showOpenDialog(this.gui.getFrame());
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = chooser.getSelectedFile();
				if (isValidDirectory(selectedFile)) {
					Path modPath = selectedFile.toPath();
					this.gui.setModPath(modPath);
					IniHandler.saveModPath(modPath);
					break;
				} else {
					JOptionPane.showMessageDialog(this.gui.getFrame(),
							"Please select the 'data/global/excel' directory of the mod.", "Invalid Directory",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				break;
			}
		}
	}

	private boolean isValidDirectory(File directory) {
		String pathString = directory.getAbsolutePath().replace("\\", "/").toLowerCase();
		return pathString.endsWith("data/global/excel");
	}
}
