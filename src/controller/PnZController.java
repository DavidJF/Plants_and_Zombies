package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import model.PnZModel;

import view.PnZView;

/**
 * 
 * The controller to interpret and apply user input
 * @author Murdock Walsh
 * @author David Falardeau
 * @version ver 1.4.1
 * 
 */


public class PnZController implements ActionListener {
	
	PnZModel pnzm;
	PnZView pnzv;
	
	/**
	 * Create new controller
	 * 
	 * @param pnzm the model
	 * @param pnzv the view
	 */
	public PnZController(PnZModel pnzm, PnZView pnzv){
		this.pnzm = pnzm;
		this.pnzv = pnzv;
	}

	@Override
	public void actionPerformed(ActionEvent o) {
		Object o2 = o.getSource();
		//PnZModel pnzm = ((PnZView)o).getPnzm();
		JButton b;
		b = (JButton) o2;
		String name = b.getName();
		if (name.equalsIgnoreCase("start")){
			pnzm.startWave();
		}else{
			String[] things = name.split(",");
			//System.out.println(things[1]+" "+things[2]);
			int row = Integer.parseInt(things[1]);
			int col = Integer.parseInt(things[2]);
			//System.out.println(row+" "+col);
			Object[] possibilities = {"Sunflower", "Pea Shooter"};
			String s = (String)JOptionPane.showInputDialog(new JFrame(),"What type of plant would you like to add?","Customized Dialog",
			                    JOptionPane.QUESTION_MESSAGE, null, possibilities, "");
			if (pnzm.placePlant(row, col, s)){
				b.setText(s);
				b.setEnabled(false);
				JTextArea ja = (JTextArea) pnzv.getContentPane().getComponent(26);
				ja.setText("Sun Points: "+pnzm.getSunPoints());
			}else{
				JOptionPane.showMessageDialog(new JFrame(), "Placement Failed: out of money");
			}
		}
	}

}
