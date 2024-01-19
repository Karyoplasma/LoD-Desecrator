package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.JButton;
import actions.ResetButtonAction;
import core.TerrorZone;
import actions.ChaosButtonAction;
import actions.GenerateButtonAction;
import model.ComboBoxTerrorZoneModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class DesecratorGUI {

	private JFrame frmDesecrator;
	private JButton btnGenerate;
	private JLabel lblCharacterLevel;
	private JButton btnReset;
	private JComboBox<TerrorZone> comboBoxTerrorZone;
	private JSpinner spinnerCharLevel;
	private JButton btnChaos;
 
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
		frmDesecrator.setBounds(100, 100, 450, 144);
		frmDesecrator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDesecrator.getContentPane().setLayout(new MigLayout("", "[][grow][]", "[grow][][]"));
		
		comboBoxTerrorZone = new JComboBox<TerrorZone>(new ComboBoxTerrorZoneModel());
		comboBoxTerrorZone.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmDesecrator.getContentPane().add(comboBoxTerrorZone, "cell 0 0 3 1,growx");
		
		lblCharacterLevel = new JLabel("Character level:");
		lblCharacterLevel.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmDesecrator.getContentPane().add(lblCharacterLevel, "cell 0 1,alignx trailing,aligny bottom");
		
		spinnerCharLevel = new JSpinner();
		spinnerCharLevel.setModel(new SpinnerNumberModel(1, 1, 99, 1));
		frmDesecrator.getContentPane().add(spinnerCharLevel, "cell 1 1,growx,aligny bottom");
		
		btnGenerate = new JButton(new GenerateButtonAction(this));
		btnGenerate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmDesecrator.getContentPane().add(btnGenerate, "cell 2 1,alignx center,growy");
		
		btnChaos = new JButton(new ChaosButtonAction(this));
		btnChaos.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmDesecrator.getContentPane().add(btnChaos, "cell 0 2");
		
		btnReset = new JButton(new ResetButtonAction(this));
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmDesecrator.getContentPane().add(btnReset, "cell 2 2,growx,aligny center");
		
		comboBoxTerrorZone.setSelectedIndex(0);
	}
	
	public int getCharLevel() {
		return (int) this.spinnerCharLevel.getValue();
	}

	public JComboBox<TerrorZone> getComboBoxTerrorZone() {
		return this.comboBoxTerrorZone;
	}
	
	public void repaintGUI() {
		this.frmDesecrator.repaint();
	}
}
