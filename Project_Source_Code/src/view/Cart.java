/**
 * @author naina
 * description:To display the Order Cart Panel
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;

import dao.ServiceDao;
import entity.CampusCardUser;
import entity.Food;
import entity.OrderFood;
import entity.UserSession;

public class Cart {
	String cafeId;
	JTextField quantity[];
	CartPanel panel;
	public CartPanel displayCart(final List<Food> filteredFoodItems, final String cafeId) {

		this.cafeId = cafeId;
		JButton orderBtn;
		JLabel label1,label2,label3,label4,label5,label6,label7;
		panel = new CartPanel(new ImageIcon("images/Cart_screen.png").getImage());
		Icon remove_icon = new ImageIcon("images/remove.png");
		quantity = new JTextField[filteredFoodItems.size()];

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets=new Insets(15,15,25,15);
		c.fill = GridBagConstraints.HORIZONTAL;

		label1=new JLabel("Item");
		label2=new JLabel("Quantity");
		label3=new JLabel("Price");
		label4=new JLabel("Calories");
		label5=new JLabel("Fats");
		label6=new JLabel("Carbs");
		label7=new JLabel("Cholesterol");

		c.gridx = 0;
		c.gridy = 0;
		panel.add(label1,c);
		c.gridx = 1;
		c.gridy = 0;
		panel.add(label2,c);
		c.gridx = 2;
		c.gridy = 0;
		panel.add(label3,c);
		c.gridx = 3;
		c.gridy = 0;
		panel.add(label4,c);
		c.gridx = 4;
		c.gridy = 0;
		panel.add(label5,c);
		c.gridx = 5;
		c.gridy = 0;
		panel.add(label6,c);
		c.gridx = 6;
		c.gridy = 0;
		panel.add(label7,c);

		int y=1;

		for(final Food food:filteredFoodItems){

			JLabel selectFoodItem = new JLabel(food.getName());
			c.gridx = 0;
			c.gridy = y;
			panel.add(selectFoodItem,c);

			quantity[y-1] = new JTextField("1");
			c.gridx = 1;
			c.gridy = y;
			panel.add(quantity[y-1],c);

			JLabel foodPrice = new JLabel(String.valueOf(food.getPrice()));
			c.gridx = 2;
			c.gridy = y;
			panel.add(foodPrice,c);

			JLabel calories = new JLabel(String.valueOf(food.getCalories()));
			c.gridx = 3;
			c.gridy = y;
			panel.add(calories,c);

			JLabel fats = new JLabel(String.valueOf(food.getFats()));
			c.gridx = 4;
			c.gridy = y;
			panel.add(fats,c);

			JLabel carbs = new JLabel(String.valueOf(food.getCarbohydrates()));
			c.gridx = 5;
			c.gridy = y;
			panel.add(carbs,c);

			JLabel cholesterol = new JLabel(String.valueOf(food.getCholestrol()));
			c.gridx = 6;
			c.gridy = y;
			panel.add(cholesterol,c);

			final JButton remove_button = new JButton(remove_icon);
			remove_button.setName(food.getFoodId());
			remove_button.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					onRemove(evt,food,filteredFoodItems);
				}
			});
			c.gridx = 7;
			c.gridy = y;
			panel.add(remove_button,c);

			y++;		
		}

		orderBtn=new JButton("Place Order");
		orderBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				onOrder(evt,filteredFoodItems,cafeId);
			}
		});
		c.gridx = 2;
		c.gridy = y;
		panel.add(orderBtn,c);

		return panel;
	}

	public void onRemove(ActionEvent evt, Food food, List<Food> filteredFoodItems){
		filteredFoodItems.remove(food);
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

	public void onOrder(ActionEvent evt,List<Food> filteredFoodItems, String cafeId){
		UserSession session = new UserSession();
		CampusCardUser user = session.getCurrentUser();
		List<OrderFood> orders = new ArrayList<OrderFood>();
		int id = user.getStudentId();
		boolean lactose_intolerant = user.getDietaryProfile().isLactose_intolerant();
		boolean peanut_allergy = user.getDietaryProfile().isPeanut_allergy();
		boolean seaFood_allergy = user.getDietaryProfile().isSeafood_allergy();
		double fundsSpent = 0;
		int caloriesConsumed = 0;
		int i = 0;
		boolean sufficientFunds = true;

		String allergy_warning = null;
		boolean check = true;
		for(Food food:filteredFoodItems){

			boolean valid = true;
			String msg = "";
			msg = msg+food.getName()+ "contains";
			JTextField qty = quantity[i];
			int quantity = 0;

			try{
				quantity = Integer.parseInt(qty.getText());
			}
			catch(NumberFormatException e) {
				check = false;
				JOptionPane.showConfirmDialog(null, "Please enter numbers only", "Error", JOptionPane.CANCEL_OPTION);
			}

			if(!check){
				break;
			}

			if(food.isLactose_intolerant() && lactose_intolerant){
				msg = msg+" lactose";
				valid = false;
			}

			if(food.isPeanut_allergy() && peanut_allergy){
				msg = msg+" peanuts";
				valid = false;
			}

			if(food.isSeafood_allergy() && seaFood_allergy){
				msg = msg+" sea food";
				valid = false;
			}

			if(!valid){
				allergy_warning = allergy_warning+msg+"/n";
			}

			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			Date date = new Date();
			dateFormat.format(date);

			String foodId = food.getFoodId();
			double totalAmt = food.getPrice()*quantity;
			OrderFood order = new OrderFood(cafeId,id,foodId,quantity,totalAmt,date);
			orders.add(order);
			fundsSpent = fundsSpent + totalAmt;
			caloriesConsumed = caloriesConsumed + food.getCalories()*order.getQuantity();
			i++;
		}

		if(check){
			try {
				Double remainingFunds = user.getExpenseProfile().getAvailableFunds() - user.getExpenseProfile().getSpentFunds();
				int totConsumedCal = user.getDietaryProfile().getCaloriesConsumed();

				if(allergy_warning != null){
					JOptionPane.showMessageDialog(panel,
							allergy_warning,
							"Health warning",
							JOptionPane.WARNING_MESSAGE);
				}

				if(totConsumedCal+caloriesConsumed > user.getDietaryProfile().getReqCalories()){
					JOptionPane.showMessageDialog(panel,
							"Total colory count exeeds the calory restriction",
							"Health warning",
							JOptionPane.WARNING_MESSAGE);
				}

				if(remainingFunds-fundsSpent < 0){
					//custom title, error icon
					JOptionPane.showMessageDialog(panel,
							"Not enough funds available.Please add funds to your account!",
							"Insufficient funds",
							JOptionPane.ERROR_MESSAGE);
					sufficientFunds = false;
				}
				else{
					ServiceDao.getOrderDAO().placeOrder(orders);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			if(sufficientFunds){
				//update spent funds and calories consumed
				try {
					ServiceDao.getUserDAO().updateFundsAndCalories(user.getStudentId(),fundsSpent,caloriesConsumed);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				JFrame frame = new MainFrame().getJframeInstance();
				JPanel mainpanel = new MainFrame().getJPanelInstance();
				mainpanel.removeAll();
				OrderReceipt receiptPanel = new OrderReceipt();
				ReceiptPanel receiptPanel1 = receiptPanel.displayOrderReceipt(orders,filteredFoodItems,cafeId);
				mainpanel.add(receiptPanel1);
				JMenuBar bar = new Menubar().getMenuBar();
				frame.setJMenuBar(bar);
				frame.validate();
				frame.repaint();
			}
		}
	}
}

class CartPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;

	public CartPanel(Image img) {
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