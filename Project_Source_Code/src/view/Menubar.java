/**
 * @author naina
 * description:To display the MenuBar
 */
package view;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import dao.ServiceDao;
import entity.CampusCardUser;
import entity.NoUserLoggedinException;
import entity.UserSession;

public class Menubar {

	public static String FUNDS = "funds";
	public static String CALORIES = "calories";
	
	public JMenuBar getMenuBar(){
		JMenuBar bar=new JMenuBar();
		JMenu home,profile,diet_stats = null,funds_stats,logout;
		JMenuItem dietStatDaily,dietStatYearly,fundsStatDaily,fundsStatYearly;
		CampusCardUser user = UserSession.getInstance().getCurrentUser();
		final int user_id = user.getStudentId();
		final float availableFunds = (float) user.getExpenseProfile().getAvailableFunds();
		final int reqCalories = user.getDietaryProfile().getReqCalories();
		
		//a group of submenu for dietary statistics
		dietStatDaily = new JMenuItem("Daily Dietary Statistics",
		                         KeyEvent.VK_T);
		dietStatDaily.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JFrame frame = new MainFrame().getJframeInstance();
				JPanel mainpanel = new MainFrame().getJPanelInstance();
				int caloriesConsumed = 0;
				mainpanel.removeAll();
				try {
					String[] data = ServiceDao.getOrderDAO().getPerDayDietaryConsumption(user_id);
					if(data[0] != null){
						caloriesConsumed = Integer.parseInt(data[0]);
					}
					else{
						caloriesConsumed = 0;
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new PieChart(mainpanel,caloriesConsumed,reqCalories,CALORIES);
				JMenuBar bar = new Menubar().getMenuBar();
				frame.setJMenuBar(bar);
				frame.validate();
				frame.repaint();
			}
		});
		
		dietStatYearly = new JMenuItem("Yearly Dietary Statistics",
                new ImageIcon("images/middle.gif"));
		dietStatYearly.setMnemonic(KeyEvent.VK_B);
		dietStatYearly.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JFrame frame = new MainFrame().getJframeInstance();
				JPanel mainpanel = new MainFrame().getJPanelInstance();
				mainpanel.removeAll();
				java.util.Map<String, Float> list = null;
				try {
					list = ServiceDao.getOrderDAO().getYearlyCaloryConsumption(user_id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				new BarGraph(mainpanel,list,CALORIES);
				JMenuBar bar = new Menubar().getMenuBar();
				frame.setJMenuBar(bar);
				frame.validate();
				frame.repaint();
			}
		});
		
		//a group of submenu for funds statistics
		fundsStatDaily = new JMenuItem("Daily Funds Statistics",
		                         KeyEvent.VK_T);
		fundsStatDaily.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JFrame frame = new MainFrame().getJframeInstance();
				JPanel mainpanel = new MainFrame().getJPanelInstance();
				float fundsUsed = 0;
				mainpanel.removeAll();
				try {
					fundsUsed = ServiceDao.getOrderDAO().getPerDayFundsConsumption(user_id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new PieChart(mainpanel,fundsUsed,availableFunds,FUNDS);
				JMenuBar bar = new Menubar().getMenuBar();
				frame.setJMenuBar(bar);
				frame.validate();
				frame.repaint();
			}
		});
		
		
		fundsStatYearly = new JMenuItem("Yearly Funds Statistics",
                new ImageIcon("images/middle.gif"));
		fundsStatYearly.setMnemonic(KeyEvent.VK_B);
		fundsStatYearly.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JFrame frame = new MainFrame().getJframeInstance();
				JPanel mainpanel = new MainFrame().getJPanelInstance();
				mainpanel.removeAll();
				java.util.Map<String, Float> list = null;
				try {
					list = ServiceDao.getOrderDAO().getYearlyFundsConsumption(user_id);
				} catch (SQLException e) {				
					e.printStackTrace();
				}
				new BarGraph(mainpanel,list,FUNDS);
				JMenuBar bar = new Menubar().getMenuBar();
				frame.setJMenuBar(bar);
				frame.validate();
				frame.repaint();
			}
		});
		
		
		home=new JMenu("Home");
		home.setMnemonic(KeyEvent.VK_F);
		home.addMenuListener(new MenuListener() {
			@Override
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuSelected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("in home");
				JFrame frame = new MainFrame().getJframeInstance();
				JPanel mainpanel = new MainFrame().getJPanelInstance();
				mainpanel.removeAll();
				Map mapPanel = new Map();
				MapPanel mapPanel1 = mapPanel.displayMap();
				mainpanel.add(mapPanel1);
				JMenuBar bar = new Menubar().getMenuBar();
				frame.setJMenuBar(bar);
				frame.validate();
				frame.repaint();
			}
	    });
		
		profile=new JMenu("Profile");
		profile.addMenuListener(new MenuListener() {
			@Override
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuSelected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				JFrame frame = new MainFrame().getJframeInstance();
				JPanel mainpanel = new MainFrame().getJPanelInstance();
				mainpanel.removeAll();
				Profile profilePanel = new Profile();
				ProfilePanel profilePanel1 = profilePanel.displayProfile();
				mainpanel.add(profilePanel1);
				JMenuBar bar = new Menubar().getMenuBar();
				frame.setJMenuBar(bar);
				frame.validate();
				frame.repaint();
			}
	    });
		
		diet_stats=new JMenu("Dietary Statistics");
		
		funds_stats=new JMenu("Expense Statistics");
		
		logout=new JMenu("Logout");
		logout.addMenuListener(new MenuListener() {
			@Override
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuSelected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				JFrame frame = new MainFrame().getJframeInstance();
				JPanel mainpanel = new MainFrame().getJPanelInstance();
				mainpanel.removeAll();
				try {
					UserSession.getInstance().userLogout();
				} catch (NoUserLoggedinException e) {
					e.printStackTrace();
				}
				Login loginPanel = new Login();
				loginPanel.login();
				frame.validate();
				frame.repaint();
			}
	    });
		
		bar.add(home);
		bar.add(profile);
		bar.add(diet_stats);
		bar.add(funds_stats);
		bar.add(logout);
		diet_stats.add(dietStatDaily);
		diet_stats.add(dietStatYearly);
		funds_stats.add(fundsStatDaily);
		funds_stats.add(fundsStatYearly);
		return bar;
	}
}
