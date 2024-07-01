package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import java.nio.file.Path;
import javax.swing.JButton;
import actions.ResetButtonAction;
import component.StatusPanel;
import core.IniHandler;
import core.TerrorZone;
import actions.ChaosButtonAction;
import actions.GenerateButtonAction;
import actions.SelectButtonAction;
import model.ComboBoxTerrorZoneModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class DesecratorGUI {

	private JFrame frmDesecrator;
	private JButton btnGenerate;
	private JLabel lblCharacterLevel;
	private JButton btnReset;
	private JComboBox<TerrorZone> comboBoxTerrorZone;
	private JSpinner spinnerCharLevel;
	private JButton btnChaos;
	private StatusPanel statusPanel;
	private Path modPath;
	private JButton btnSelectModFolder;
	private JLabel lblNewLabel;
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DesecratorGUI window = new DesecratorGUI();
					window.frmDesecrator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DesecratorGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDesecrator = new JFrame();
		frmDesecrator.setTitle("Terror Zones 2.6");
		frmDesecrator.setBounds(100, 100, 450, 204);
		frmDesecrator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDesecrator.getContentPane().setLayout(new MigLayout("", "[grow][grow][]", "[grow][grow][][]"));
		
		lblNewLabel = new JLabel();
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmDesecrator.getContentPane().add(lblNewLabel, "cell 0 0 2 1,alignx center,aligny center");
		
		btnSelectModFolder = new JButton(new SelectButtonAction(this));
		btnSelectModFolder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmDesecrator.getContentPane().add(btnSelectModFolder, "cell 2 0,growx,aligny center");
		
		comboBoxTerrorZone = new JComboBox<TerrorZone>(new ComboBoxTerrorZoneModel());
		comboBoxTerrorZone.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmDesecrator.getContentPane().add(comboBoxTerrorZone, "cell 0 1 3 1,growx");
		
		lblCharacterLevel = new JLabel("Character level:");
		lblCharacterLevel.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmDesecrator.getContentPane().add(lblCharacterLevel, "cell 0 2,alignx trailing,growy");
		
		spinnerCharLevel = new JSpinner();
		spinnerCharLevel.setModel(new SpinnerNumberModel(1, 1, 99, 1));
		frmDesecrator.getContentPane().add(spinnerCharLevel, "cell 1 2,grow");
		
		btnGenerate = new JButton(new GenerateButtonAction(this));
		btnGenerate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmDesecrator.getContentPane().add(btnGenerate, "cell 2 2,alignx center,growy");
		
		btnChaos = new JButton(new ChaosButtonAction(this));
		btnChaos.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmDesecrator.getContentPane().add(btnChaos, "cell 0 3");
		
		statusPanel = new StatusPanel();
		frmDesecrator.getContentPane().add(statusPanel, "cell 1 3,grow");
		
		btnReset = new JButton(new ResetButtonAction(this));
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmDesecrator.getContentPane().add(btnReset, "cell 2 3,growx,aligny center");
		
		comboBoxTerrorZone.setSelectedIndex(0);
		
		this.modPath = IniHandler.readModPath();
		if (this.modPath == null) {
			disableButtons();
			this.lblNewLabel.setText("Please select your Diablo 2 mod data/global folder");
		} else {
			this.lblNewLabel.setText(this.modPath.toString());
		}
		
	}
	
	public int getCharLevel() {
		return (int) this.spinnerCharLevel.getValue();
	}

	public JComboBox<TerrorZone> getComboBoxTerrorZone() {
		return this.comboBoxTerrorZone;
	}
	
	public void repaintComboBox() {
		this.comboBoxTerrorZone.repaint();
	}
	
	public StatusPanel getStatusPanel() {
		return this.statusPanel;
	}
	
	public Path getModPath() {
		return this.modPath;
	}
	
	public JFrame getFrame() {
		return this.frmDesecrator;
	}
	
	public void setModPath(Path modPath) {
		this.modPath = modPath;
		this.lblNewLabel.setText(modPath.toString());
		this.enableButtons();
	}
	
	public void disableButtons(){
		this.btnChaos.setEnabled(false);
		this.btnGenerate.setEnabled(false);
		this.btnReset.setEnabled(false);
	}
	
	public void enableButtons(){
		this.btnChaos.setEnabled(true);
		this.btnGenerate.setEnabled(true);
		this.btnReset.setEnabled(true);
	}
}
