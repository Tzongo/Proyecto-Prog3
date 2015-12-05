package utilidades;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class JButtonTransparente extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButtonTransparente() {
		// TODO Auto-generated constructor stub
	}

	public JButtonTransparente(Icon icon) {
		super(icon);
		// TODO Auto-generated constructor stub
	}

	public JButtonTransparente(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public JButtonTransparente(Action a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public JButtonTransparente(String text, Icon icon) {
		super(text, icon);
		// TODO Auto-generated constructor stub
	}
	public void setOpaque() {
		this.setOpaque(true);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
	}
}
