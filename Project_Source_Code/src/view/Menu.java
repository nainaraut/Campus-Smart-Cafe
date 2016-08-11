/**
 * @author naina
 * description:To display the Menu Panel
 */
package view;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import dao.ServiceDao;
import entity.CampusCafes;
import entity.Food;

public class Menu {

	List<String> selectedCheckbox = new ArrayList<String>();
	String cafeId;
	
	public MenuPanel displayMenu(final List<Food> foodList,String cafeId){

		this.cafeId = cafeId;
		CampusCafes campusCafe = null;
		try {
			campusCafe = ServiceDao.getCampusCafeDAO().getCampusCafeDetails(cafeId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		JButton checkout;
		MenuPanel panel = new MenuPanel(new ImageIcon("images/Menu_screen.png").getImage());
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(15,15,15,1);
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel venueName = new JLabel(campusCafe.getName());
		c.gridx = 1;
		c.gridy = 0;
		panel.add(venueName,c);

		int y1=3;
		int y2=3;
		int count = 0;
		int check = foodList.size()/2;

		for(Food food: foodList){
			count++;
			final JCheckBox foodName = new JCheckBox(food.getName());
			foodName.setName(food.getFoodId());
			foodName.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(foodName.isSelected()){
						selectedCheckbox.add(foodName.getName());
					}
				}
			});

			if(count <= check){
				c.gridx = 0;
				c.gridy = y1;
				panel.add(foodName,c);
				y1++;
			}
			else{
				c.gridx = 2;
				c.gridy = y2;
				panel.add(foodName,c);
				y2++;
			}
		}

		c.gridx = 1;
		c.gridy = y2;
		checkout=new JButton("Checkout");
		checkout.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				checkoutActionListener(evt,foodList);
			}
		});

		panel.add(checkout,c);
		return panel;
	}

	public void checkoutActionListener(ActionEvent evt,List<Food> foodList){
		List<Food> filteredFoodItems = new ArrayList<Food>();
		for(String foodId:selectedCheckbox){
			for(Food food:foodList){
				if(food.getFoodId().equals(foodId)){
					filteredFoodItems.add(food);
				}
			}
		}
		
		JFrame frame = new MainFrame().getJframeInstance();
		JPanel mainpanel = new MainFrame().getJPanelInstance();
		mainpanel.removeAll();
		Cart cartPanel = new Cart();
		CartPanel cartPanel1 = cartPanel.displayCart(filteredFoodItems,cafeId);
		mainpanel.add(cartPanel1);
		JMenuBar bar = new Menubar().getMenuBar();
		frame.setJMenuBar(bar);
		frame.validate();
		frame.repaint();
	}
}

class MenuPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;

	public MenuPanel(Image img) {
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