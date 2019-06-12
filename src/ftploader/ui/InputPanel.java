package ftploader.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int textFieldWidth = 120;
	private static final int textFieldHeight = 20;
	
	private JTextField field;
    private JLabel fieldLabel;
    
    private String label;
    
    public InputPanel(String label) {
    	super(new GridLayout(1, 2));
    	this.label = label;
    	init();
    }
    
    public void init() {
    	field = new JTextField();
    	field.setPreferredSize(new Dimension(textFieldWidth, textFieldHeight));
    	fieldLabel = new JLabel(this.label);
    	add(fieldLabel);
    	add(Box.createHorizontalStrut(10));
    	add(field);
    }
    
    public String getText() {
    	return this.field.getText();
    }
    
}
