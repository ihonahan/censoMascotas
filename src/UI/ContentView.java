package UI;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import Model.*;


public class ContentView extends JSplitPane {
	private static final long serialVersionUID = 4L;
	
	private JPanel regionsPanel;
	private JPanel regionsBar;
	private JLabel searchRegionLabel;
	private JTextField searchRegionText;
	private JButton searchRegionButton;
	private JList<Region> regionsList;
	
	private JPanel beingsPanel;
	private JPanel beingsBar;
	private JLabel searchBeingsLabel;
	private JTextField searchBeingsText;
	private JButton searchBeingsButton;
	private JList<LivingBeing> beingsList;

	public ContentView() {
		this.orientation = JSplitPane.HORIZONTAL_SPLIT;
		this.setupRegionsPane();
		this.setupBeingsPane();
		
		this.setSize(920, 650);
				
		this.setDividerLocation(0.5);
		
		this.setTopComponent(regionsPanel);
		this.setBottomComponent(beingsPanel);
		
		this.addSelectionListenerToRegionList();
		this.addSelectionListenerToBeingsList();
	}
	
	private void setupRegionsPane() {
		this.searchRegionLabel = new JLabel("Región: ");
		this.searchRegionText = new JTextField("", 10);
		this.searchRegionText.setMinimumSize(new Dimension(200, 50));
		this.searchRegionButton = new JButton("Buscar");
		
		this.regionsBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.regionsBar.setMinimumSize(new Dimension(400, 50));
		this.regionsBar.add(this.searchRegionLabel);
		this.regionsBar.add(this.searchRegionText);
		this.regionsBar.add(this.searchRegionButton);
		
		this.regionsList = new JList<Region>();
		
		this.regionsPanel = new JPanel(new BorderLayout());
		this.regionsPanel.add(this.regionsBar, BorderLayout.NORTH);
		this.regionsPanel.add(this.regionsList, BorderLayout.CENTER);
		
		this.addSearchRegionButtonListener();
	}
	
	private void setupBeingsPane() {
		this.searchBeingsLabel = new JLabel("Ser censado: ");
		this.searchBeingsText = new JTextField("", 10);
		this.searchBeingsText.setMinimumSize(new Dimension(200, 50));
		this.searchBeingsButton = new JButton("Buscar");
		
		this.beingsBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.beingsBar.setMinimumSize(new Dimension(400, 50));
		this.beingsBar.add(this.searchBeingsLabel);
		this.beingsBar.add(this.searchBeingsText);
		this.beingsBar.add(this.searchBeingsButton);
		
		this.beingsList = new JList<LivingBeing>();
		
		this.beingsPanel = new JPanel(new BorderLayout());
		this.beingsPanel.add(this.beingsBar, BorderLayout.NORTH);
		this.beingsPanel.add(this.beingsList, BorderLayout.CENTER);
		
		this.addSearchBeingButtonListener();
	}
	
	public void reloadRegions(String criteria) {
		boolean usingCriteria = !criteria.trim().equals("");
		
		Region[] result = usingCriteria ? getRegions(criteria.trim()) : getAllRegions();
		
		this.regionsList.setListData(result);
	}
	
	private Region[] getAllRegions() {
		ArrayList<Region> regions = CensusSingleton.shared.getAllRegions();
		
		return regions.toArray(new Region[regions.size()]);
	}
	
	private Region[] getRegions(String byName) {
		ArrayList<Region> regions = CensusSingleton.shared.getRegions(byName);
		
		return regions.toArray(new Region[regions.size()]);
	}
	
	
	public void reloadAllHumansInRegion() {
		ArrayList<LivingBeing> beings = CensusSingleton.shared.getAllHumasFromSelectedRegion();
		
		LivingBeing[] result = beings.toArray(new LivingBeing[beings.size()]);
		
		this.beingsList.setListData(result);
	}
	
	public void reloadAllHumansInRegion(String withName) {
		ArrayList<LivingBeing> beings = CensusSingleton.shared.getBeingsByNameInSelectedRegion(withName);
		
		LivingBeing[] result = beings.toArray(new LivingBeing[beings.size()]);
		
		this.beingsList.setListData(result);
	}
	
	private void addSelectionListenerToRegionList() {
		this.regionsList.addListSelectionListener((event) -> {
			Region region = this.regionsList.getSelectedValue();
			CensusSingleton.shared.assignSelectedRegion(region);
		});
	}
	
	private void addSelectionListenerToBeingsList() {
		this.beingsList.addListSelectionListener((event) -> {
			LivingBeing being = this.beingsList.getSelectedValue();
			CensusSingleton.shared.assignSelectedHuman(being);
		});
	}
	
	private void addSearchRegionButtonListener() {
		this.searchRegionButton.addActionListener((event) -> {
			this.reloadRegions(this.searchRegionText.getText().trim());
		});
	}
	
	private void addSearchBeingButtonListener() {
		this.searchBeingsButton.addActionListener((event) -> {
			this.reloadAllHumansInRegion(this.searchBeingsText.getText().trim());
		});
	}

}
