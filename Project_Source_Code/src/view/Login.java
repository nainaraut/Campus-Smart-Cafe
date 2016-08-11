/**
 * @author naina
 * description:To display the Login panel
 */
package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.*;

import entity.UserSession;
import entity.UserSessionException;

public class Login {
	static JFrame frame = null;
	static JPanel mainpanel=null;
	
	public void login() {
		
		JButton login;
		JLabel label3,label4;
		final JTextField id;
		final JTextField password;
		final LoginController panel = new LoginController(new ImageIcon("images/Login_screen.png").getImage());

		panel.setLayout(new GridBagLayout());

		panel.setSize(new Dimension(450,450));
		GridBagConstraints c = new GridBagConstraints();
		c.insets=new Insets(5,5,5,25);
		c.fill = GridBagConstraints.HORIZONTAL;

		id=new JTextField(10);
		password=new JPasswordField(10);

		label3=new JLabel("Please Enter User ID ");
		label4=new JLabel("Please Enter the Password ");

		login=new JButton("Login");
		login.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				int userId = Integer.parseInt(id.getText());
				String pass = password.getText();
				boolean validUser = onLoginUser(evt,userId,pass);
				if(!validUser){
					JOptionPane.showMessageDialog(panel, "Incorrect login details", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		c.gridx = 0;
		c.gridy = 0;
		panel.add(label3,c);
		c.gridx = 1;
		c.gridy = 0;
		panel.add(id,c);
		c.gridx = 0;
		c.gridy = 2;
		panel.add(label4,c);
		c.gridx = 1;
		c.gridy = 2;
		panel.add(password,c);

		c.insets=new Insets(5,5,5,5);

		c.gridx = 1;
		c.gridy = 6;
		panel.add(login,c);
		
		frame = new MainFrame().getJframeInstance();
		mainpanel=new MainFrame().getJPanelInstance();
		mainpanel.add(panel);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private static boolean onLoginUser(ActionEvent evt, int userId,String password) {
		try {
			boolean validUser = UserSession.getInstance().userLogin(userId, password);
			if(validUser)
			{
				mainpanel.removeAll();
				Map mapPanel = new Map();
				MapPanel mapPanel1 = mapPanel.displayMap();
				mainpanel.add(mapPanel1);
				JMenuBar bar = new Menubar().getMenuBar();
				frame.setJMenuBar(bar);
				frame.validate();
				frame.repaint();
				return validUser;
			}
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserSessionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}

class LoginController extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image img;

	public LoginController(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}