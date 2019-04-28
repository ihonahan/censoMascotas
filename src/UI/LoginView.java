package UI;
import java.awt.*;
import javax.swing.*;
import Model.*;

public class LoginView extends JFrame {
	private static final long serialVersionUID = 2L;
	
	private JButton loginButton;
	private JButton cancelButton;
	private JTextField usernameText;
	private JTextField passwordText;
	private JLabel usernameLabel;
	private JLabel passwordLabel;

	public LoginView() {
		this.setTitle("Login");
		this.setLayout(new GridLayout(3, 1));
		
		this.usernameLabel = new JLabel("Nombre de usuario: ");
		this.usernameText = new JTextField();

		this.passwordLabel = new JLabel("Contraseña: ");
		this.passwordText = new JPasswordField();
		
		this.loginButton = new JButton("Login");
		this.cancelButton = new JButton("Cancelar");

		addLoginListener();
		addCancelListener();

		JPanel usernamePanel = new JPanel(new GridLayout(1, 2));
		usernamePanel.add(this.usernameLabel);
		usernamePanel.add(this.usernameText);
		this.add(usernamePanel);
		
		JPanel passwordPanel = new JPanel(new GridLayout(1, 2));
		passwordPanel.add(this.passwordLabel);
		passwordPanel.add(this.passwordText);
		this.add(passwordPanel);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
		buttonsPanel.add(this.loginButton);
		buttonsPanel.add(this.cancelButton);
		this.add(buttonsPanel);
		
		this.setSize(300, 160);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	private void addLoginListener() {
		this.loginButton.addActionListener((event) -> {
			String username = usernameText.getText().trim();
			String password = passwordText.getText().trim();
			
			if(!username.equals("") && !password.equals("")) {
					User user = new User(username, password);
					CensusSingleton.shared.user = user;
					
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
