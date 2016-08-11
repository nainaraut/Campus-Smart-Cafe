/**
 * @author naina
 * description:To display the Map Panel
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
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.Icon;

import dao.ServiceDao;
import entity.CampusCafes;
import entity.Food;


public class Map {
	List<CampusCafes> campusCafesList;
	
	int[] dimX= {3,7,4,2,10,3,5,6,9,7,3};
	int[] dimY= {1,5,5,7,8,9,2,3,1,2,5};
	MapPanel panel;
	
	public MapPanel displayMap() {
	
		Icon VM_icon = new ImageIcon("images/VendingMachine_icon.png");
		Icon cafe_icon = new ImageIcon("images/Cafe_icon.jpg");
		panel = new MapPanel(new ImageIcon("images/Map_screen.png").getImage());
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		try {
			campusCafesList = ServiceDao.getCampusCafeDAO().getCampusCafeList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int i = 0;
		
		c.insets = new Insets(20,20,6,20);
		for(final CampusCafes item:campusCafesList){
			if(item.getType().equals("vending_machine")){
				JButton vm_button = new JButton(VM_icon);
				vm_button.setName(item.getCampusId());
				vm_button.setToolTipText(item.getName());
				vm_button.addActionListener(new java.awt.event.ActionListener() {
		            public void actionPerformed(java.awt.event.ActionEvent evt) {
		            	displayMenuScreen(evt,item.getCampusId());
		            }
		        });
				
				c.gridx = dimX[i];
				c.gridy = dimY[i];
				panel.add(vm_button,c);
				i++;
			}
			else{
				JButton cafe_button=new JButton(cafe_icon);
				cafe_button.setName(item.getCampusId());
				cafe_button.setToolTipText(item.getName());
				cafe_button.addActionListener(new java.awt.event.ActionListener() {
		            public void actionPerformed(java.awt.event.ActionEvent evt) {		        
		            	displayMenuScreen(evt,item.getCampusId());
		            }
		        });
				
				c.gridx = dimX[i];
				c.gridy = dimY[i];
				panel.add(cafe_button,c);
				i++;
			}
		}
		return panel;
	}
	
	public void displayMenuScreen(ActionEvent evt,String id){
		List<Food> foodList;
		try {
			foodList = ServiceDao.getFoodDAO().getFoodItems(id);
			JFrame frame = new MainFrame().getJframeInstance();
			JPanel mainpanel = new MainFrame().getJPanelInstance();
			mainpanel.removeAll();
			Menu menuPanel = new Menu();
			MenuPanel menuPanel1 = menuPanel.displayMenu(foodList,id);
			mainpanel.add(menuPanel1);
			JMenuBar bar = new Menubar().getMenuBar();
			frame.setJMenuBar(bar);
			frame.validate();
			frame.repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

class MapPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image img;

	public MapPanel(Image img) {
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