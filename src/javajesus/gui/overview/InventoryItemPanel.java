package javajesus.gui.overview;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JPanel;

import javajesus.SoundHandler;
import javajesus.entities.Player;
import javajesus.graphics.Screen;
import javajesus.items.Item;

public class InventoryItemPanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	private BufferedImage image;
	private String hoverText;
	private InventoryGUI panel;
	private Item item;

	private static final int Y_OFFSET = 30;

	private static final int SIZE = 16;
	
	private static Player player;

	public InventoryItemPanel(Item item, InventoryGUI panel, Player player) {

		this.panel = panel;
		InventoryItemPanel.player = player;
		this.addMouseListener(this);
		this.item = item;
		
		image = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
		int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		hoverText = item.getName();

		Screen screen = new Screen(SIZE, SIZE);

		item.render(screen, 0, 0);
		for (int y = 0; y < screen.getHeight(); y++) {
			for (int x = 0; x < screen.getWidth(); x++) {
				pixels[x + y * SIZE] = screen.getPixels()[x + y * screen.getWidth()];
			}

		}

		this.validate();
	}

	public String getText() {
		return hoverText;
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, SIZE, Y_OFFSET, this.getWidth(), this.getHeight(), null);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		panel.updateText(hoverText);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		panel.updateText("Inventory");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		SoundHandler.play(SoundHandler.click);
		player.getInventory().select(item);
		InventoryGUI.update();
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
