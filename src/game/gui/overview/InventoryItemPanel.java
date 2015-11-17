package game.gui.overview;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JPanel;

import game.Game;
import game.SoundHandler;
import game.graphics.Screen;
import items.Item;

public class InventoryItemPanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	private BufferedImage image;
	private String hoverText;
	private InventoryGUI panel;
	private Item item;

	public InventoryItemPanel(int width, int height, Item item, InventoryGUI panel) {

		this.panel = panel;
		panel.addMouseListener(this);
		this.item = item;

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		hoverText = item.name;

		Screen screen = new Screen(width, height);

		item.render(screen, 0, 0);

		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				pixels[x + y * width] = screen.pixels[x + y * screen.width];
			}

		}

		this.setPreferredSize(new Dimension(width, height));
		this.setOpaque(false);

		this.validate();
	}

	public String getText() {
		return hoverText;
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		SoundHandler.sound.play(SoundHandler.sound.click);
		Game.player.inventory.equip(item, Game.player);
		panel.update();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		panel.setToolTipText(hoverText);
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
