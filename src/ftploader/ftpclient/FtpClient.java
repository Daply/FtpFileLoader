package ftploader.ftpclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpClient {
	private String host;
    private int port;
    private String user;
    private String password;
    private String account;
    private FTPClient ftp;
    
    public FtpClient(String server, int port, String user, String password, String account) {
    	if (server == null) {
    		server = new String();
    	}
    	if (user == null) {
    		user = new String();
    	}
    	if (password == null) {
    		password = new String();
    	}
    	if (account == null) {
    		account = new String();
    	}
    	this.host = server;
    	this.port = port;
    	this.user = user;
    	this.password = password;
    	this.account = account;
    }
    
    public void open() throws IOException {
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        
        if (port == 0) {
        	ftp.connect(host);
        }
        else {
        	ftp.connect(host, port);
        }
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new IOException("Exception in connecting to FTP Server");
        }
        
        // if account is not specified
        if (account.length() <= 0) {
        	ftp.login(user, password);
        }
        else {
        	ftp.login(user, password, account);
        }
    }
 
    public void close() throws IOException {
        ftp.disconnect();
    }
    
    public void putFileToPath(File uploadingFile, String nameOfRemoteFile) throws IOException {
    	for (String s: ftp.listNames()) {
    		System.out.println(s);
    	}
        ftp.storeFile(nameOfRemoteFile, new FileInputStream(uploadingFile));
    }
    
}
