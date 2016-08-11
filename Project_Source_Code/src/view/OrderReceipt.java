/**
 * @author naina
 * description:To display the OrderReceipt Panel
 */
package view;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import dao.ServiceDao;
import entity.CampusCafes;
import entity.Food;
import entity.OrderFood;

public class OrderReceipt {
	
	public ReceiptPanel displayOrderReceipt(List<OrderFood> orderedDetailsList,List<Food> foodDetailsList,String cafeId){
		
		ReceiptPanel panel = new ReceiptPanel(new ImageIcon("images/Receipt_screen.png").getImage());
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets=new Insets(15,15,25,15);
		c.fill = GridBagConstraints.HORIZONTAL;
		CampusCafes campusCafe = null;
		
		JLabel label1,label2,label3,label4,label5,label6;
		
		try {
			campusCafe = ServiceDao.getCampusCafeDAO().getCampusCafeDetails(cafeId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		label1=new JLabel("Item");
		label2=new JLabel("Quantity");
		label3=new JLabel("Total Price");
		label4=new JLabel("Venue Name");
		label5=new JLabel("location");
		
		if(campusCafe.getType().equals("cafe")){
			label6=new JLabel("Pickup Time");
			c.gridx = 5;
			c.gridy = 0;
			panel.add(label6,c);
		}
		
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
		
		int y=1;
		
		for(final Food food:foodDetailsList){
			
			for(OrderFood order:orderedDetailsList){
				
				if(food.getFoodId().equals(order.getFood_id())){
					
					JLabel selectFoodItem = new JLabel(food.getName());
					c.gridx = 0;
					c.gridy = y;
					panel.add(selectFoodItem,c);
					
					JLabel quantity = new JLabel(String.valueOf(order.getQuantity()));
					c.gridx = 1;
					c.gridy = y;
					panel.add(quantity,c);
					
					JLabel total_amount = new JLabel(String.valueOf(order.getTotal_amount()));
					c.gridx = 2;
					c.gridy = y;
					panel.add(total_amount,c);
					
					JLabel cafeName = new JLabel(campusCafe.getName());
					c.gridx = 3;
					c.gridy = y;
					panel.add(cafeName,c);
					
					JLabel location = new JLabel(campusCafe.getLocation());
					c.gridx = 4;
					c.gridy = y;
					panel.add(location,c);
					
					if(campusCafe.getType().equals("cafe")){
						JLabel prepTime = new JLabel(food.getPrepTime()+" minutes");
						c.gridx = 5;
						c.gridy = y;
						panel.add(prepTime,c);
					}
					y++;		
				}
			}
		}
		return panel;
	}
}

class ReceiptPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;

	public ReceiptPanel(Image img) {
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
