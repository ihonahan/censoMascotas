package UI;
import java.awt.*;
import javax.swing.*;
import Model.*;

public class HomeView extends JFrame implements CensusListener {
	private static final long serialVersionUID = 1L;
	
	public JPanel menu;
	public JButton authenticateButton;
	public JButton addRegionButton;
	public JButton addHumanButton;
	public JButton addPetButton;
	public JButton reportPetsButton;
	
	public ContentView contentView;
	
	public HomeView() {
		this.setTitle("Censo de mascotas");
		this.setLayout(new BorderLayout());
		
		this.authenticateButton = new JButton("Login");
		this.addRegionButton = new JButton("Agregar Región");
		this.addHumanButton = new JButton("Agregar Persona");
		this.addPetButton = new JButton("Agregar Dependiente");
		this.reportPetsButton = new JButton("Desplegar Mascotas");
		
		this.addAuthenticateButtonListener();
		this.addRegionButtonListener();
		this.addHumanButtonListener();
		this.addPetButtonListener();
		this.addreportPetsButtonListener();
		
		this.menu = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.menu.add(this.authenticateButton);
		this.menu.add(this.addRegionButton);
		this.menu.add(this.addHumanButton);
		this.menu.add(this.addPetButton);
		this.menu.add(this.reportPetsButton);
		
		this.add(this.menu, BorderLayout.NORTH);
		
		this.contentView = new ContentView();
		
		this.add(this.contentView, BorderLayout.CENTER);
		
		this.setSize(920, 720);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		CensusSingleton.shared.addListener(this);
	}
	
	void addAuthenticateButtonListener() {
		this.authenticateButton.addActionListener((event) -> {
			new LoginView();
		});
	}

	void addRegionButtonListener() {
		this.addRegionButton.addActionListener((event) -> {
			if (CensusSingleton.shared.user != null) {
				new AddRegionView();
			}
		});
	}
	
	void addHumanButtonListener() {
		this.addHumanButton.addActionListener((event) -> {
			if (CensusSingleton.shared.userAuthenticated() && CensusSingleton.shared.isRegionSelected()) {
				new AddLivingBeingView(true);
			}
		});
	}

	void addPetButtonListener() {
		this.addPetButton.addActionListener((event) -> {
			if (CensusSingleton.shared.userAuthenticated() && CensusSingleton.shared.isRegionSelected() && CensusSingleton.shared.isHumanSelected()) {
				new AddLivingBeingView(false);
			}			
		});
	}

	void addreportPetsButtonListener() {
		this.reportPetsButton.addActionListener((event) -> {
			
		});
	}
	
	
	
	/// Census Listener methods
	
	@Override
	public void regionAdded() {
		this.contentView.reloadRegions("");
	}

	@Override
	public void regionSelected() {
		this.contentView.reloadAllHumansInRegion();
	}

	@Override
	public void humanAdded() {
		this.contentView.reloadAllHumansInRegion();
	}

	@Override
	public void humanSelected() {
	}

	@Override
	public void petAdded() {
	}
}
