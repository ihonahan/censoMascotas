package UI;
import java.awt.*;
import javax.swing.*;

import Model.BeingType;
import Model.LivingBeing;

public class AddLivingBeingView extends JFrame {
	private static final long serialVersionUID = 5L;
	
	private JButton acceptButton;
	private JButton cancelButton;
	private JTextField beingNameText;
	private JLabel beingNameLabel;
	private JTextField beingAddressText;
	private JLabel beingAddressLabel;
	private JLabel beingTypeLabel;
	private JList<String> types;
	
	private String[] humansLabels = {"Adulto", "Infante"};
	private BeingType[] humans = {BeingType.ADULTHUMAN, BeingType.CHILDHUMAN};

	private String[] petsLabels = {"Perro", "Gato", "Pez", "Canario", "Loro", "Perico"};
	private BeingType[] pets = {BeingType.DOG, BeingType.CAT, BeingType.FISH, BeingType.CANARY, BeingType.PARROT, BeingType.PARAKEET};
	
	private boolean asHuman;

	public AddLivingBeingView(boolean asHuman) {
		this.asHuman = asHuman;

		this.setLayout(new GridLayout(5, 1));
		
		this.beingNameLabel = new JLabel("Nombres: ");
		this.beingNameText = new JTextField();

		this.beingAddressLabel = new JLabel("Dirección: ");
		this.beingAddressText = new JTextField();

		this.beingTypeLabel = new JLabel("Tipo: ");
		this.types = new JList<String>();
		
		if (this.asHuman) {
			this.setTitle("Nuevo Humano");
			this.types.setListData(humansLabels);
		} else {
			this.setTitle("Nueva Mascota");
			this.types.setListData(petsLabels);
		}

		this.acceptButton = new JButton("Guardar");
		this.cancelButton = new JButton("Cancelar");

		addAcceptListener();
		addCancelListener();

		JPanel beingNamePanel = new JPanel(new GridLayout(1, 2));
		beingNamePanel.add(this.beingNameLabel);
		beingNamePanel.add(this.beingNameText);
		this.add(beingNamePanel);
		
		JPanel beingAddressPanel = new JPanel(new GridLayout(1, 2));
		beingAddressPanel.add(this.beingAddressLabel);
		beingAddressPanel.add(this.beingAddressText);
		this.add(beingAddressPanel);
		
		JPanel beingTypeLabelPanel = new JPanel(new GridLayout(1, 1));
		beingTypeLabelPanel.add(this.beingTypeLabel);
		this.add(beingTypeLabelPanel);
		
		JPanel beingTypeListPanel = new JPanel(new GridLayout(1, 1));
		beingTypeListPanel.add(this.types);
		this.add(beingTypeListPanel);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
		buttonsPanel.add(this.acceptButton);
		buttonsPanel.add(this.cancelButton);
		this.add(buttonsPanel);
		
		this.setSize(300, 360);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	private void addAcceptListener() {
		this.acceptButton.addActionListener((event) -> {
			if (this.asHuman) {
				this.addHuman();
			} else {
				this.addPet();
			}
		});
	}
	
	private void addHuman() {
		String names = beingNameText.getText().trim();
		String address = beingAddressText.getText().trim();
					
		if(!names.equals("") && !address.equals("") && types.getSelectedIndex() >= 0) {
			BeingType type = humans[types.getSelectedIndex()];
			LivingBeing being = new LivingBeing(names, address, type);
			CensusSingleton.shared.addHuman(being);
				
			this.dispose();					
		}
	}
	
	private void addPet() {
		String names = beingNameText.getText().trim();
		String address = beingAddressText.getText().trim();
					
		if(!names.equals("") && !address.equals("") && types.getSelectedIndex() >= 0) {
			BeingType type = humans[types.getSelectedIndex()];
			LivingBeing being = new LivingBeing(names, address, type);
			CensusSingleton.shared.addPet(being);
				
			this.dispose();					
		}		
	}

	private void addCancelListener() {
		this.cancelButton.addActionListener((event) -> {
			this.dispose();
		});
	}

}
