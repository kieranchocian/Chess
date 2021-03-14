package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Chess.Game;

public class BoardGUI extends JPanel {

	private JButton btn;
	private Map<Point, JButton> mapButtons;

	/**
	 * Create the panel.
	 */

	public BoardGUI() {
		JFrame frame = new JFrame("Chess test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ChessWindow window = new ChessWindow();
		frame.add(window);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public void changeCellPiece(int x, int y, String pieceLocation) {
		y = convertYToGBC(y);
		JButton test = mapButtons.get(new Point(x, y));

		if (pieceLocation.equals("null")) {
			test.setIcon(null);

		} else {

			try {
				Image img = ImageIO.read(getClass().getResource(pieceLocation));

				test.setIcon(new ImageIcon(img));
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}

	public void changeCellColour(int x, int y, Color colour) {
		y = convertYToGBC(y);
		JButton test = mapButtons.get(new Point(x, y));
		test.setBackground(colour);

	}

	public void removeRedCell(int x, int y) {
		if ((x + y + 2) % 2 == 0) {
			changeCellColour(x, y, Color.BLACK);
		} else {
			changeCellColour(x, y, Color.WHITE);
		}
	}

	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	// Y is inverted in the layout so this inverts normal coordinate
	public static int convertYToGBC(int y) {

		int newY = 7 - y;

		return newY;
	}

	// Y is inverted in the layout so this converts it back to normal
	public static int convertYToNormal(int y) {

		int newY = Math.abs(y - 7);

		return newY;
	}

	// This allows me to have a different layout for the grid and the overall frame
	public class ChessWindow extends JPanel implements ActionListener {

		private GridBagConstraints gbc = new GridBagConstraints();

		public ChessWindow() {
			setLayout(new GridBagLayout());

			mapButtons = new LinkedHashMap<Point, JButton>();

			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					gbc.gridx = x;
					gbc.gridy = y;
					gbc.weightx = 1;
					gbc.weighty = 1;
					gbc.fill = GridBagConstraints.BOTH;

					btn = new JButton();
					btn.setPreferredSize(new Dimension(100, 100));
					btn.setActionCommand(x + " " + convertYToNormal(y));
					if ((x + y + 2) % 2 == 0) {
						btn.setBackground(Color.WHITE);
						btn.setForeground(Color.BLACK);
					} else {
						btn.setBackground(Color.BLACK);
						btn.setForeground(Color.WHITE);
					}

					add(btn, gbc);
					btn.addActionListener(this);

					mapButtons.put(new Point(x, y), btn);
				}
			}

			System.out.println(mapButtons);
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();
			JButton button = (JButton) source;

			System.out.println("************************ NEW SELECTION ON GUI ************************");

			int x = Integer.parseInt(String.valueOf(button.getActionCommand().charAt(0)));
			int y = Integer.parseInt(String.valueOf(button.getActionCommand().charAt(2)));
			System.out.println("Coords selected on GUI: " + x + ", " + y);
			System.out.println("Dangerous cells at (" + x + ", " + y + "): " + Game.getBoard().getDangerousCells(x, y));
			Game.actionPerformed(x, y);
		}
	}
}
