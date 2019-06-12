package ftploader.ftpclient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ftploader.ftpclient.FtpClient;

public class FtpLoader {
	private Map<String, String> chosenFiles;
	
	public FtpLoader () {
		chosenFiles = new HashMap<String, String>();
	}
	
    public void addFile (String filePath, String fileName) {
    	chosenFiles.put(filePath, fileName);
	}
    
    public boolean containsFile (String filePath) {
    	return chosenFiles.containsKey(filePath);
	}
    
    public void loadFiles (String host, int port, String user, String password, String account) throws IOException {
    	FtpClient ftpClient = new FtpClient(host, port, user, password, account);
        try {
			ftpClient.open();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        for (Entry<String, String> file: chosenFiles.entrySet()) {
        	ftpClient.putFileToPath(new File(file.getKey()), file.getValue());
        }
        chosenFiles.clear();
        ftpClient.close();
	}
    
    
}
