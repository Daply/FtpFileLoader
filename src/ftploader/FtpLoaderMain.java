package ftploader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ftploader.ftpclient.FtpClient;
import ftploader.ui.FtpLoaderInterface;

public class FtpLoaderMain {

	private static void createAndShowGUI() {
        JFrame frame = new JFrame("Ftp Loader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new FtpLoaderInterface());
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                createAndShowGUI();
            }
        });
    }
}
