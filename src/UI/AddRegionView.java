package UI;
import java.awt.*;
import javax.swing.*;

public class AddRegionView extends JFrame {
	private static final long serialVersionUID = 3L;
	
	private JButton acceptButton;
	private JButton cancelButton;
	private JTextField regionNameText;
	private JLabel regionNameLabel;

	public AddRegionView() {
		this.setTitle("Nueva Región");
		this.setLayout(new GridLayout(3, 1));
		
		this.regionNameLabel = new JLabel("Nombre de región: ");
		this.regionNameText = new JTextField();

		this.acceptButton = new JButton("Guardar");
		this.cancelButton = new JButton("Cancelar");

		addAcceptListener();
		addCancelListener();

		JPanel regionNamePanel = new JPanel(new GridLayout(1, 2));
		regionNamePanel.add(this.regionNameLabel);
		regionNamePanel.add(this.regionNameText);
		this.add(regionNamePanel);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
		buttonsPanel.add(this.acceptButton);
		buttonsPanel.add(this.cancelButton);
		this.add(buttonsPanel);
		
		this.setSize(300, 160);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	private void addAcceptListener() {
		this.acceptButton.addActionListener((event) -> {
			String regionName = regionNameText.getText().trim();
			
			if(!regionName.equals("")) {
					CensusSingleton.shared.addRegion(regionName);
					
					this.dispose();					
			}
		});
	}

	private void addCancelListener() {
		this.cancelButton.addActionListener((event) -> {
			this.dispose();
		});
	}

}
