import java.rmi.*;
import java.rmi.server.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.util.*;

public class btserver extends UnicastRemoteObject implements btinterface
{

  public btserver() throws RemoteException
 {
     	//Loginform lf=new Loginform();
	//lf.show();

	login lg = new login();
         lg.show();

    System.out.println("Background file transfer Service Started...");
 }


public String []getDrives() throws RemoteException
{
       String []drives = null;
      
      int i = 0;
  try
        {            

            FileSystemView fsv = FileSystemView.getFileSystemView();

            File[] roots = File.listRoots();
            int size = roots.length;
            drives = new String[size];

             System.out.println(size);

           for(i=0;i<size;i++)
           {
              	drives[i] = fsv.getSystemDisplayName(roots[i]);
	System.out.println(drives[i]);
           }

        }
         catch(Exception e)
          {
	System.out.println(e);
          }
 
 return drives;
}


public Vector getfolders(String path) throws RemoteException
{

String []app = null;
Vector v = new Vector();
int i=0;

 try {
           File f = new File(path);
           app = f.list();

           for(i=0;i<app.length;i++)
           {
	File f1 = new File(path + app[i]);

	//System.out.println(path + app[i]);

                 if(f1.isDirectory())
	{
	  //System.out.println(app[i]);
	   v.addElement(f1.getName());
                  }    
            }       

      }
        catch(Exception e)
        {
           System.out.println(e);
        }

return v;


}


public Vector getfiles(String path) throws RemoteException
{

String []app = null;
Vector v = new Vector();
int i=0;

 try {
           File f = new File(path);
           app = f.list();

           for(i=0;i<app.length;i++)
           {
	File f1 = new File(path + app[i]);

	//System.out.println(path + app[i]);

                 if(f1.isDirectory()==false)
	{
	  //System.out.println(app[i]);
	   v.addElement(app[i]);
                  }    
            }       

      }
        catch(Exception e)
        {
           System.out.println(e);
        }

return v;

}


public byte []getFiledata(String flname) throws RemoteException, IOException
{


  String fname = flname;

File f1 = new File(fname);

String fullname = f1.getAbsolutePath();

//File p2 = new File(fullname);

FileInputStream inStream = new FileInputStream(fullname);

int inBytes = inStream.available();

byte inBuf[] = new byte[inBytes];

int bytesread = inStream.read(inBuf,0,inBytes);

inStream.close();

return inBuf; 


}



public String pasteFile(byte buf[], String flname) throws RemoteException, IOException
{
  
File f1 = new File(flname);

String fullname = f1.getAbsolutePath();

FileOutputStream outStream = new FileOutputStream(fullname);

outStream.write(buf);

outStream.close();

return "File Transfered";


}


public void Addclient(String ssname,String sspass) throws RemoteException	
	   {
		
                   String sysip;
		
                 
             try
              {
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                		Connection con=DriverManager.getConnection("Jdbc:Odbc:bluelogin1");		
		Statement stmt=con.createStatement();
		

                   sysip=RemoteServer.getClientHost();
 
		
		
                
		stmt.executeUpdate("insert into bbtable values('"+ssname+"','"+sspass+"','"+sysip+"')");	
		con.close();
	       	
              }
            catch(Exception e)
              {
                System.out.println(e);
              }       
           } 		
	

public String checkpass(String ip, String pass)  throws RemoteException
{
	String result="";
try {
                 String dbpass="";
                 
        	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                	Connection con=DriverManager.getConnection("Jdbc:Odbc:bluelogin1");
	Statement stmt=con.createStatement();
	ResultSet rs=stmt.executeQuery("select * from bbtable where system_ip='"+ip+"'");
  	while(rs.next())
	{
                   dbpass = rs.getString("system_password");
                  }
	 con.close();

	if(pass.equals(dbpass))
	{
                      result="valid";
                  }
                    else
                      {
                         result="invalid";
                      }

	      
     }
       catch(Exception e)
       {
          System.out.println(e);
       }

  return result;

}



public String del_ip(String ipaddr) throws RemoteException
	{
	try
		{
		System.out.println(ipaddr);
		
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                	Connection con=DriverManager.getConnection("Jdbc:Odbc:bluelogin1");
	Statement stmt=con.createStatement();

		
		stmt.executeUpdate("Delete from bbtable where system_ip='"+ ipaddr +"'");
		
		
		con.close();
		
		}
		catch(Exception e)
		
		{
		System.out.println(e);
		System.exit(0);
		}
		
		return "Log out Successfull";		
	}	







          public String GetIP(String comboname) throws RemoteException
          {
		String systemip; 
                systemip=new String();
                try
               { System.out.println("Hello");       
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection con=DriverManager.getConnection("Jdbc:Odbc:bluelogin1");
		Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery("select * from bbtable where system_name='"+comboname+"'");
               
                while(rs.next())
                   {
                      systemip=rs.getString("system_ip");
                      System.out.println("HELLO");
                   } 
                  
              
                         


                 }
      
                catch(Exception e)
             {
                System.out.println(e);
             }
   
               return(systemip);                                  

          }      
           






	public String[] Getlist() throws RemoteException
	   {
		String []sysname = null;
		
		int count =0;
                String dbsname, ipaddr="";		 
                int i=0;
		
             try
               {
      
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                		Connection con=DriverManager.getConnection("Jdbc:Odbc:bluelogin1");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from bbtable");

	 while(rs.next())
                   {
	    count = count +1;
	 }

	sysname=new String[count];
                
	rs=stmt.executeQuery("select * from bbtable");
                while(rs.next())
                   {
	       
	       dbsname=rs.getString("system_name");
	       ipaddr = rs.getString("system_ip");
                              
	                sysname[i]= ipaddr+",               "+dbsname;			
                    
                      i=i+1;
                   }

                       
               }
             catch(Exception e)
               {
                   System.out.println(e);
               }
           
                  return(sysname);
         }



public String loclog(String s1,String s2)throws RemoteException
	{
		boolean exists = false;
		String retval = "";
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:bluelogin1");
			Statement s=con.createStatement();
			ResultSet rs=s.executeQuery("select * from log");
			while(rs.next())
			{	
				if(rs.getString("uid").equals(s1)&&rs.getString("pass").equals(s2))											
				{
					exists=true;
					break;
				} 
			}
			if(exists)
				
				retval = "Login Successful";
			else
				retval = "Failed";
				
		}	
		catch(Exception e)
		{
			System.out.println("Exception"+e);
		}

		return retval;
	}


public String reguser(String s1,String s2,String s3,String s4,String s5)throws RemoteException
	{
		boolean exists = false;
		String retval = "";
		
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:bluelogin1");
			Statement s=con.createStatement();
					
			s.executeUpdate("insert into log(uname,uid,pass,securityq,answer) values ('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"')");
			con.close();
                                                retval = "Register Successful";

		}
		catch(Exception e)
		{
			System.out.println("Exception"+e);
		}
		return retval;
	}


public String getpass1(String a)throws RemoteException
{

     String squest="", sans="", retval="", pass="";

try
   {
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Connection con=DriverManager.getConnection("jdbc:odbc:bluelogin1");
	Statement s=con.createStatement();
        ResultSet rs = s.executeQuery("select * from log where uid='"+ a +"'");
	while(rs.next())
	{
         squest = rs.getString("securityq");
         sans = rs.getString("answer");
	 pass = rs.getString("pass");
	}
	con.close();						

	retval=	squest +","+ sans +","+ pass;
    }
     catch(Exception e)
     {
       System.out.println(e);
     }

 return retval;

}



public static void main(String args[])
{
   try {
	btserver bt = new btserver();
	Naming.rebind("bgserver",bt);
        }
          catch(Exception e)
	{
	   System.out.println(e);
                  }
}
}     
               
   