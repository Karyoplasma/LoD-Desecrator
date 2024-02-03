package component;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.JPanel;

public class StatusPanel extends JPanel {

	private static final long serialVersionUID = -4849270990684851849L;
	private Color statusColor = Color.GRAY;

	public StatusPanel() {
		super();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					try {
						Desktop.getDesktop().browse(new URI("https://www.d2emu.com/tz-sp"));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
	}

	public void declareWorking() {
		this.statusColor = Color.GRAY;
		this.repaint();
	}

	public void setStatusColor(int errorLevel) {
		Color color = Color.GREEN;
		if (errorLevel != 0) {
			color = Color.RED;
		}
		this.statusColor = color;
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int diameter = 2 * (Math.min(getWidth(), getHeight())) / 3;
		int x = getWidth() - diameter;
		int y = (getHeight() - diameter) / 2;
		g.setColor(statusColor);
		g.fillOval(x, y, diameter, diameter);
	}
}
