package utilidades;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class JPanelRelleno extends JPanel {

	public JPanelRelleno() {
		// TODO Auto-generated constructor stub
		this.setOpaque(false);
		this.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
	}

	public JPanelRelleno(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public JPanelRelleno(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public JPanelRelleno(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

}
