/**
 * @author naina
 * description:The Parent frame class. The execution of the 
 * program begins here 
 */
package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame {

	static JFrame frame;
	static JPanel mainpanel;
	public static void main(String args[]){
		if(frame == null){
			frame = new JFrame();
		}
		if(mainpanel == null){
			mainpanel=new JPanel();
		}
		frame.getContentPane().add(mainpanel);
		Login loginPanel = new Login();
		loginPanel.login();
	}
	
	public JFrame getJframeInstance(){
		return MainFrame.frame;
	}
	public JPanel getJPanelInstance(){
		return MainFrame.mainpanel;
	}
}
