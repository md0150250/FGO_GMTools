package moudle;

import java.io.File;
import java.io.IOException;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SFTPv3Client;  
  
/** 
 * ganymed的版本选择build210  
 * <dependency>  
 * <groupId>ch.ethz.ganymed</groupId>  
 * <artifactId>ganymed-ssh2</artifactId> 
 * <version>build210</version>  
 * </dependency> 
 */  
public class TestScp {  
    private static String IP = "192.168.0.200";  
    private static int PORT = 22;  
    private static String USER = "johnny";//登录用户名  
    private static String PASSWORD = "a53313158";//生成私钥的密码和登录密码，这两个共用这个密码  
    private static Connection connection = new Connection(IP, PORT);  
    private static String PRIVATEKEY = "C:\\Users\\ubuntu\\.ssh\\id_rsa";// 本机的私钥文件  
    private static boolean usePassword = true;// 使用用户名和密码来进行登录验证。如果为true则通过用户名和密码登录，false则使用rsa免密码登录  
    
    
    /** 
     * ssh用户登录验证，使用用户名和密码来认证 
     *  
     * @param user 
     * @param password 
     * @return 
     */  
    public static boolean isAuthedWithPassword(String user, String password) {  
        try {  
            return connection.authenticateWithPassword(user, password);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return false;  
    }  
  
    /** 
     * ssh用户登录验证，使用用户名、私钥、密码来认证 其中密码如果没有可以为null，生成私钥的时候如果没有输入密码，则密码参数为null 
     *  
     * @param user 
     * @param privateKey 
     * @param password 
     * @return 
     */  
    public static boolean isAuthedWithPublicKey(String user, File privateKey, String password) {  
        try {  
            return connection.authenticateWithPublicKey(user, privateKey, password);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return false;  
    }  
  
    public static boolean isAuth() {  
        if (usePassword) {  
            return isAuthedWithPassword(USER, PASSWORD);  
        } else {  
            return isAuthedWithPublicKey(USER, new File(PRIVATEKEY), PASSWORD);  
        }  
    }  
  
    public static void getFile(String remoteFile, String path) {  
        try {  
            connection.connect();  
            boolean isAuthed = isAuth();  
            if (isAuthed) {  
                System.out.println("认证成功!");  
                SCPClient scpClient = connection.createSCPClient();  
                scpClient.get(remoteFile, path); 
               
            } else {  
                System.out.println("认证失败!");  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            connection.close();  
        }  
    }  
  
    public static void putFile(String localFile, String remoteTargetDirectory) throws IOException {  
        try {  
            connection.connect();  
            boolean isAuthed = isAuth();  
            if (isAuthed) {  
                SCPClient scpClient = connection.createSCPClient();  
                scpClient.put(localFile, remoteTargetDirectory);  
            } else {  
                System.out.println("认证失败!");  
            }  
        }  finally {  
            connection.close();  
        }  
    }  
  
    
    /** 
     * 在远端linux上创建文件夹 
     * @param dirName 文件夹名称 
     * @param posixPermissions 目录或者文件夹的权限 
     */  
    public static void mkDir(String dirName,int posixPermissions)  
    {  
    	try {  
            connection.connect();  
            boolean isAuthed = isAuth();  
            if (isAuthed) {  
            	System.out.println("************开始创建目录:" + dirName + "************"); 
            	 SFTPv3Client sftpClient = new SFTPv3Client(connection);  
               sftpClient.mkdir(dirName, posixPermissions);   
            } else {  
                System.out.println("认证失败66666!");  
            }  
        } catch (Exception ex) { 
        	//System.out.println("************目录:" + dirName + "已经存在!************"); 
            ex.printStackTrace();  
        } finally {  
            connection.close();  
        }  

    }  
    
    /** 
     * 删除远端Linux服务器上的文件 
     * @param filePath 
     */  
    public static void rmFile(String filePath){
    	try {  
            connection.connect();  
            boolean isAuthed = isAuth();  
            if (isAuthed) {  
            	System.out.println("************开始删除文件:" + filePath + "************");   
            	SFTPv3Client sftpClient = new SFTPv3Client(connection);  
                sftpClient.rm(filePath);  
            } else {  
                System.out.println("认证失败66666!");  
            }  
        } catch (Exception ex) { 
        	System.out.println("************文件:" + filePath + "不存在!************");  
            ex.printStackTrace();  
        } finally {  
            connection.close();  
        }  
    }
    
} 