package daProject;



public class DaMain {

	public static void main(String[] args) {
javax.swing.SwingUtilities.invokeLater(new Runnable() {
	
			@Override
			public void run() {
				GameJFrame gui = new GameJFrame();
				gui.setSize(900, 500);
				gui.setResizable(false);
			}

	});

}
}