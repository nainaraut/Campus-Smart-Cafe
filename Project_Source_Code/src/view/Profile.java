/**
 * @author naina
 * description:To display the Profile Panel
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
import dao.ServiceDao;
import entity.CampusCardUser;
import entity.UserSession;
public class Profile {

	public ProfilePanel displayProfile(){
		final JButton button1;
		JLabel label1,label2,label3,label4,label5,label6,label7,label8,label9,label10,label11,label12,label13,label14,space1,space2;
		final JCheckBox rd6;
		final JCheckBox rd7;
		final JCheckBox rd8;
		final JTextField fundsVal;
		final JTextField caloriesVal;
		ProfilePanel panel = new ProfilePanel(new ImageIcon("images/Profile_screen.png").getImage());

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets=new Insets(10,10,10,10);
		c.fill = GridBagConstraints.HORIZONTAL;
		
		final CampusCardUser user = UserSession.getInstance().getCurrentUser();

		label8=new JLabel(" ");
		label9=new JLabel(" ");
		space1=new JLabel(" ");
		space2=new JLabel(" ");
		
		fundsVal=new JTextField(String.valueOf(user.getExpenseProfile().getAvailableFunds()));
		caloriesVal=new JTextField(String.valueOf(user.getDietaryProfile().getReqCalories()));
		label1=new JLabel("Name:");
		label2=new JLabel("User ID:");
		label6=new JLabel(user.getFirstName()+" "+user.getLastName());//user name
		label7=new JLabel(String.valueOf(user.getStudentId()));      //user id
		label9 = new JLabel("Funds Profile");
		label3=new JLabel("Funds: ");
		label11=new JLabel("Funds Spent: ");
		label12=new JLabel(String.valueOf(user.getExpenseProfile().getSpentFunds()));//spent funds
		label4=new JLabel("Dietary Profile ");
		label5=new JLabel("Calories: ");
		label13=new JLabel("Calories Consumed: ");//
		label14=new JLabel(String.valueOf(user.getDietaryProfile().getCaloriesConsumed()));//consumed calories
		label10=new JLabel("Allergies:");
		
		rd6=new JCheckBox("Peanut");
		if(user.getDietaryProfile().isPeanut_allergy()){
			rd6.setSelected(true);
		}
		rd6.setEnabled(false);
		rd7=new JCheckBox("Seafood");
		if(user.getDietaryProfile().isSeafood_allergy()){
			rd7.setSelected(true);
		}
		rd7.setEnabled(false);
		rd8=new JCheckBox("Lactose Intolerant");
		if(user.getDietaryProfile().isLactose_intolerant()){
			rd8.setSelected(true);
		}
		rd8.setEnabled(false);
		
		button1=new JButton("Edit");
		button1.setName("Edit");
		button1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String name = button1.getName();
				if(name.equals("Edit")){
					fundsVal.setEditable(true);
					caloriesVal.setEditable(true);
					rd6.setEnabled(true);
					rd7.setEnabled(true);
					rd8.setEnabled(true);
					button1.setName("Save");
					button1.setText("Save");
				}
				else if(name.equals("Save")){
					
					try {
				        Float.parseFloat(fundsVal.getText());
				        Float.parseFloat(caloriesVal.getText());
				        fundsVal.setEditable(false);
						caloriesVal.setEditable(false);
						rd6.setEnabled(false);
						rd7.setEnabled(false);
						rd8.setEnabled(false);
						button1.setName("Edit");	
						button1.setText("Edit");
						user.getExpenseProfile().setAvailableFunds(Double.parseDouble(fundsVal.getText()));
						user.getDietaryProfile().setReqCalories(Integer.parseInt(caloriesVal.getText()));
						if(rd6.isSelected()){
							user.getDietaryProfile().setPeanut_allergy(true);
						}
						if(rd7.isSelected()){
							user.getDietaryProfile().setSeafood_allergy(true);
						}
						if(rd8.isSelected()){
							user.getDietaryProfile().setLactose_intolerant(true);
						}
						updateProfile(evt,user);

				    } catch (NumberFormatException e) {
				        JOptionPane.showConfirmDialog(null, "Please enter numbers only", "Error", JOptionPane.CANCEL_OPTION);
				    }
				}
			}
		});
		
		//c.insets=new Insets(15,5,5,5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(label1,c);
		
		//c.insets=new Insets(5,5,5,5);
		c.gridx = 1;
		c.gridy = 0;
		panel.add(label6,c);
		
		c.gridx = 0;
		c.gridy = 1;
		panel.add(label2,c);
		
		//c.insets=new Insets(20,5,5,5);
		c.gridx = 1;
		c.gridy = 1;
		panel.add(label7,c);
		
		c.gridx = 1;
		c.gridy = 2;
		panel.add(space1,c);
		
		//c.insets=new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 2;
		panel.add(label9,c);//fund Profile
		
		c.gridx = 0;
		c.gridy = 3;
		panel.add(label3,c);
		
		c.gridx = 1;
		c.gridy = 3;
		panel.add(fundsVal,c);
		fundsVal.setEditable(false);//funds restriction
		
		c.gridx = 0;
		c.gridy = 4;
		panel.add(label11,c);//funds spent
		
		//c.insets=new Insets(25,5,5,5);
		c.gridx = 1;
		c.gridy = 4;
		panel.add(label12,c);//value
		
		c.gridx = 1;
		c.gridy = 1;
		panel.add(space2,c);
		
		//c.insets=new Insets(15,5,5,5);
		c.gridx = 0;
		c.gridy = 5;
		panel.add(label4,c);//Dietary Profile

		c.gridx = 0;
		c.gridy = 6;
		panel.add(label5,c);//calories
		
		c.gridx = 1;
		c.gridy = 6;
		panel.add(caloriesVal,c);
		
		caloriesVal.setEditable(false);
		c.gridx = 0;
		c.gridy = 7;
		panel.add(label13,c);//caloriesconsumed
		
		c.gridx = 1;
		c.gridy = 7;
		panel.add(label14,c);

		//c.insets=new Insets(5,5,5,5);
		panel.add(rd6,c);
		
		c.gridx = 0;
		c.gridy = 8;
		panel.add(label10,c);
		c.gridx = 1;
		c.gridy = 8;
		panel.add(rd6,c);
		c.gridx = 2;
		c.gridy = 8;
		panel.add(rd7,c);
		c.gridx = 3;
		c.gridy = 8;
		panel.add(rd8,c);
		c.gridx = 4;
		c.gridy = 9;
		panel.add(label8,c);

		c.gridx = 1;
		c.gridy = 11;
		panel.add(button1,c);
		
		return panel;
	}
	
	public static void updateProfile(ActionEvent e,CampusCardUser user){
		try {
			ServiceDao.getUserDAO().updateUserProfile(user);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}

class ProfilePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image img;

	public ProfilePanel(Image img) {
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