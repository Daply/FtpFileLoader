package ftploader.ui;

import java.io.*;
import java.util.Collections;
import java.util.Map.Entry;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ftploader.ftpclient.FtpClient;
import ftploader.ftpclient.FtpLoader;

public class FtpLoaderInterface extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final int width = 400;
	private static final int height = 400;
	
	private Box mainBox; 
	
	private JButton chooseFilesButton;
	
	private InputPanel hostPanel;
	private InputPanel portPanel;
	private InputPanel userPanel;
	private InputPanel passwordPanel;
	private InputPanel accountPanel;
	
    private JButton loadFilesButton;
    private JTextArea chosenFiles;
    private JFileChooser fileChooser;
    
    private JButton clearButton;
    
    private FtpLoader ftpLoader;
 
    private boolean needToClear = false;
    
    public FtpLoaderInterface() {
        super(new GridBagLayout());
        //super();
        init();
    }
    
    public void init() {
    	ftpLoader = new FtpLoader();
    	setPreferredSize(new Dimension(width, height));
        mainBox = Box.createVerticalBox();
        
        initButtons();
        mainBox.add(Box.createVerticalStrut(20));
        initInputs();
        mainBox.add(Box.createVerticalStrut(20));
        initChosenFilesPane();
        
        add(mainBox);
    }
    
    public void initButtons() {
    	
    	// file chooser
        fileChooser = new JFileChooser();
    	
    	// choose files button
        chooseFilesButton = new JButton("Choose files");
        chooseFilesButton.addActionListener(this);

        // load files via ftp
        loadFilesButton = new JButton("Load");
        loadFilesButton.addActionListener(this);
        
        // clear text area
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        
    	JPanel buttonPanel = new JPanel();
        buttonPanel.add(chooseFilesButton);
        buttonPanel.add(loadFilesButton);
        buttonPanel.add(clearButton);
        
        mainBox.add(buttonPanel);
    }
 
    public void initInputs() {
    	// input panels
        hostPanel = new InputPanel("host");
        portPanel = new InputPanel("port");
        userPanel = new InputPanel("user");
        passwordPanel = new InputPanel("password");
        accountPanel = new InputPanel("account");
    	Box inputsBox = Box.createVerticalBox();
        inputsBox.add(hostPanel);
        inputsBox.add(Box.createVerticalStrut(20));
        inputsBox.add(Box.createVerticalGlue());
        inputsBox.add(portPanel);
        inputsBox.add(Box.createVerticalStrut(20));
        inputsBox.add(Box.createVerticalGlue());
        inputsBox.add(userPanel);
        inputsBox.add(Box.createVerticalStrut(20));
        inputsBox.add(Box.createVerticalGlue());
        inputsBox.add(passwordPanel);
        inputsBox.add(Box.createVerticalStrut(20));
        inputsBox.add(Box.createVerticalGlue());
        inputsBox.add(accountPanel);
        
        mainBox.add(inputsBox);
    }
    
    public void initChosenFilesPane() {
    	// text area for chosen files
    	int textAreaWidth = 200;
    	int textAreaHeight = 200;
        chosenFiles = new JTextArea(5,20);
        chosenFiles.setMargin(new Insets(5,5,5,5));
        chosenFiles.setEditable(false);
        JScrollPane filesScrollPane = new JScrollPane(chosenFiles);
        chosenFiles.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
        
        mainBox.add(filesScrollPane);
    }
    
    public void actionPerformed(ActionEvent e) {
 
        if (e.getSource() == chooseFilesButton) {
        	if (needToClear) {
        		chosenFiles.setText("");
        		needToClear = false;
        	}
            int returnVal = fileChooser.showOpenDialog(FtpLoaderInterface.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (!ftpLoader.containsFile(file.getPath())) {
	                chosenFiles.append(file.getName() + "\n");
	                ftpLoader.addFile(file.getPath(), file.getName());
                }
            }
            chosenFiles.setCaretPosition(chosenFiles.getDocument().getLength());
        } else if (e.getSource() == loadFilesButton) {
            String host = hostPanel.getText();
            String portString = portPanel.getText();
            int port = 0;
            String user = userPanel.getText();
            String password = passwordPanel.getText();
            String account = accountPanel.getText();
            if (host.length() <= 0) {
            	host = new String();
            }
            if (portString.length() <= 0) {
            	port = 0;
            }
            else {
            	port = Integer.parseInt(portString);
            }
            if (user.length() <= 0) {
            	user = new String();
            }
            if (password.length() <= 0) {
            	password = new String();
            }
            if (account.length() <= 0) {
            	account = new String();
            }
            try {
				ftpLoader.loadFiles(host, port, user, password, account);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            chosenFiles.setText("Transfer complete");
            needToClear = true;
        } else if (e.getSource() == clearButton) {
        	chosenFiles.setText("");
        	needToClear = false;
        }
    	
    }

}