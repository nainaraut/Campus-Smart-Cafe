/**
 * @author naina
 * description:To display the Bar Graph
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

public class BarGraph{	
	public static String label;
	
	private static final long serialVersionUID = 1L;
	public BarGraph(JPanel mainPanel,Map<String,Float> dataList,String label){
		BarGraph.label = label;
		Bar1 chart = new Bar1();	

		for(Entry<String, Float> entry : dataList.entrySet()){
			chart.addBar(entry.getKey(), entry.getValue());
		}
		mainPanel.add(chart);
	}
}

class Bar1 extends JPanel
{
	private static final long serialVersionUID = 1L;
	public Bar1(){
		Dimension size = new Dimension(700, 700);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
	}
	
	private Map<String, Float> bars = new LinkedHashMap<String, Float>();
	
	public void addBar(String day, Float float1)
	{
		bars.put(day, float1);	
		repaint();
	}

	protected void paintComponent(Graphics g)
	{
		float max = Integer.MIN_VALUE;
		for (Float value : bars.values())
		{
			max = Math.max(max, value);
		}

		int width = bars.size() == 0 ? 0 : (getWidth() / bars.size()) -10;
		int x = 25;
		int y = 45;
		int count=1;
		for (String day : bars.keySet())
		{
			Float value = bars.get(day);
			int height = (int)((getHeight()-100) * ((double)value / (max+10)));
			g.setColor(Color.cyan);
			g.fillRect(x, getHeight() - height-y, width, height);
			g.setColor(Color.cyan);
			g.drawRect(x, getHeight() - height-y, width, height);
			count++;

			g.setColor(Color.black);
			g.drawString(day, x+5 , getHeight() - 30);
			String value1 = String.valueOf(value);
			g.drawString(value1, x+15 , getHeight() - height - 45);
			x += (width + 3);
		}
		g.setColor( Color.black );

		int height = getHeight();
		int width1 = getWidth();
		int start = 20;
		int base = height - start;

		g.drawLine( 0, base-20, width1-start, base-20 );//X Axis
		g.drawLine( start, base-20, start, start ); //Y axis
		
		g.setFont( new Font( "ARIAL", Font.BOLD, 14 ) );
		if(BarGraph.label.equals("funds")){
			g.drawString( "Funds Used Over the Year", getWidth()/2-50,10 );
		}
		else if(BarGraph.label.equals("calories")){
			g.drawString( "Calories Consumed Over the Year", getWidth()/2-50,10 );
		}
		
		g.setFont( new Font( "ARIAL", Font.PLAIN, 12 ) );
		g.drawString( "Y", 0,25 );
		g.drawString( "X",  width1-start, base-20 );
		g.drawString( "AXIS", 0, 0 );
	}

	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
}
