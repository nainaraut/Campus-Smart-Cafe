/**
 * @author naina
 * description:To display the Pie Chart
 */
package view;
import java.awt.*;
import javax.swing.JPanel;

public class PieChart{

	public static String label;
	private static final long serialVersionUID = 1L;
	public PieChart(JPanel mainPanel,float consumed,float available,String label){	
		PieChart.label = label;
		PiePanel chart = new PiePanel(consumed,available);
		mainPanel.add(chart);
	}
}

class PiePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	float consumed;
	float available;
	
	PiePanel(float consumed,float available){
		this.consumed =consumed;
		this.available = available;
		Dimension size = new Dimension(700, 700);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
	}

	public void addPie()
	{
		repaint();
	}

	public void paintComponent( Graphics g )
	{
		drawPieChart( g );
		drawLegend( g );
	}

	private void drawPieChart( Graphics g )
	{
		System.out.println("first:"+available+" "+consumed);
		if(available < consumed ){
			float temp = consumed;
			consumed = available;
			available = temp;
		}
		System.out.println("second:"+available+" "+consumed);
		double totalBalance = available;
		int startAngle = 0;
		int arcAngle = 0;
		double remaining = (double)((totalBalance-consumed)/ totalBalance);//manipulate
		if(consumed != 0)
		{
			consumed = (float) (consumed/ totalBalance);//manipulate
		}
		
		arcAngle = ( int ) Math.round( remaining * 360 );

		System.out.println("third:"+remaining+" "+consumed);
		g.setColor(Color.red);

		g.fillArc( getWidth()/2-100, getHeight()/2-100, getWidth()/2 - 50, getHeight()/2 - 50, startAngle, arcAngle );

		startAngle += arcAngle;
		arcAngle = ( int ) Math.round( consumed * 360 );

		// set drawing Color for Account pie wedge
		g.setColor(Color.blue);

		// draw Account pie wedge
		g.fillArc( getWidth()/2-100, getHeight()/2-100, getWidth()/2 - 50, getHeight()/2 - 50, startAngle, arcAngle );
	} 

	private void drawLegend( Graphics g )
	{
		Font font = new Font( "SansSerif", Font.BOLD, 12 );
		g.setFont( font );

		FontMetrics metrics = getFontMetrics( font );
		int ascent = metrics.getMaxAscent();
		int offsetY = ascent + 5;

		g.setColor( Color.blue );
		g.fillRect( 125, offsetY * 1, ascent, ascent );

		g.setColor( Color.black );
		g.drawString( "Consumed", 140,
				offsetY * 1 + ascent );

		g.setColor( Color.red );
		g.fillRect( 125, offsetY * 2, ascent, ascent );

		g.setColor( Color.black );
		g.drawString( "Available", 140,
				offsetY * 2 + ascent );
		
		String label = PieChart.label;
		g.setFont( new Font( "SansSerif", Font.BOLD, 14 ) );
		if(label.equals("funds")){
			g.drawString( "Funds Used Over the Day", getWidth()/2-50,10 );
		}
		else if(label.equals("calories")){
			g.drawString( "Calories Consumed Over the Day", getWidth()/2-50,10 );
		}
	}

}
