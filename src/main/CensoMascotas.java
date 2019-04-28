package main;
import javax.swing.*;
import UI.*;

public class CensoMascotas {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(() -> {
			new HomeView();
		});
	}
}
