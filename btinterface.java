import java.rmi.*;
import java.io.*;
import java.util.*;

public interface btinterface extends Remote
{

public String []getDrives() throws RemoteException;
public Vector getfolders(String path) throws RemoteException;
public Vector getfiles(String path) throws RemoteException;
public byte []getFiledata(String flname) throws RemoteException, IOException;
public String pasteFile(byte buf[], String flname) throws RemoteException, IOException;
public void Addclient(String ssname,String sspass) throws RemoteException;        
public String[] Getlist()throws RemoteException;
public String GetIP(String comboname) throws RemoteException;
public String checkpass(String ip, String pass)  throws RemoteException; 
public String del_ip(String ipaddr) throws RemoteException;
public String loclog(String a,String b)throws RemoteException;
public String reguser(String s1,String s2,String s3,String s4,String s5)throws RemoteException;
public String getpass1(String a)throws RemoteException;
//public String ip_addr(String d1)throws RemoteException;

}