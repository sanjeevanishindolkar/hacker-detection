import java.sql.*;
import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;  
import java.rmi.*;
import java.io.*;
import java.util.*;
import mjdc.*; 
import java.io.*;
import javax.swing.event.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.*;


public class browseapp extends JFrame implements ActionListener, Selector, ItemListener, ListSelectionListener, Runnable
{
	Container cp;
	JLabel title, lbllocal, lblremote, lblfolder, lbldrives;
	ImageIcon ic1;	
	JTextField txtpath, txtpath1;
	JButton btnbrowse, btnexplore, btnupload, btndownload, btnup, btndown, btntransfer,btnviewstat ;
	JDialog jDialog;
	mjdc.JFolderChooser jFolderChooser= null;
	JList file_list, file_list1,file_list2;
	DefaultListModel listModel, listModel1, listModel2;
	JScrollPane listpane, listpane1,listpane2;	
	int i = 0;
	JComboBox jcb;
	String ipaddr = "";
	boolean complete = false;
	boolean exists = false; 
	

public browseapp(String ip)
{
   	setTitle("Connected to: "+ ip);
   	setSize(670,600);

	cp=getContentPane();
	cp.setLayout(null);
	cp.setBackground(Color.white);

	ipaddr = ip;

	ic1=new ImageIcon("images/title.jpg");  	
	title=new JLabel(ic1);
	lbllocal = new JLabel("Local Computer");
	lblremote = new JLabel("Network Computer");
	lblfolder = new JLabel("Folder:");
	lbldrives = new JLabel("Drives:");
	jcb = new JComboBox();
	

	txtpath= new JTextField(30);
	txtpath1= new JTextField(30);


	btnbrowse = new JButton("Browse");
	btnexplore = new JButton("Explore");

	btnupload = new JButton("--->");
	btndownload = new JButton("<---");

	btnup = new JButton("Upload");
	btndown = new JButton("Download");
	btntransfer = new JButton("Start Transfer");
	btnviewstat = new JButton("View Status");

	btnup.addActionListener(this);
	btndown.addActionListener(this);
	btntransfer.addActionListener(this);
	btnviewstat.addActionListener(this);	


	listModel = new DefaultListModel();

	file_list = new JList(listModel);	
	Color cl = new Color(192,192,192);
	file_list.setBackground(cl);
	listpane = new JScrollPane(file_list);


	listModel1 = new DefaultListModel();
	

	file_list1 = new JList(listModel1);		
	file_list1.setBackground(cl);
	listpane1 = new JScrollPane(file_list1);
	listpane1.setBounds(360,200,300,150);	

	file_list1.addListSelectionListener(this);

	listModel2 = new DefaultListModel();
	
	file_list2 = new JList(listModel2);		
	file_list2.setBackground(cl);
	listpane2 = new JScrollPane(file_list2);
	listpane2.setBounds(360,360,300,150);
	

	lbllocal.setOpaque(true);
	lbllocal.setBackground(Color.black);
	lbllocal.setForeground(Color.white);
	lbllocal.setHorizontalTextPosition(JLabel.CENTER);

	lblremote.setOpaque(true);
	lblremote.setBackground(Color.black);
	lblremote.setForeground(Color.white);
	lblremote.setHorizontalTextPosition(JLabel.CENTER);
	

	title.setBounds(0,0,662,140);
	lbllocal.setBounds(0,140,300,20);
	lblremote.setBounds(360,140,300,20);
	lblfolder.setBounds(5,170,40,20);
	txtpath.setBounds(50,170,150,20);
	btnbrowse.setBounds(210,170,90,20);
	listpane.setBounds(0,200,300,315);	
	lbldrives.setBounds(360,170,40,20);
	jcb.setBounds(405,170,120,20);
	//btnexplore.setBounds(550,170,90,20);
	txtpath1.setBounds(530,170,120,20);
	btnupload.setBounds(300,400,60,30);
	btndownload.setBounds(300,440,60,30);

	btnup.setBounds(10,520,100,30);
	btndown.setBounds(140,520,100,30);
	btnviewstat.setBounds(270,520,100,30);
	//btnviewstat.setBounds(400,520,100,30);

	cp.add(title);
	cp.add(lbllocal);
	cp.add(lblremote);
	cp.add(lblfolder);
	cp.add(txtpath);
	cp.add(btnbrowse);
	cp.add(listpane);
	cp.add(lbldrives);
	cp.add(jcb);
	//cp.add(btnexplore);
	cp.add(txtpath1);
	cp.add(btnupload);
	cp.add(btndownload);
	cp.add(btnup);
	cp.add(btndown);
	//cp.add(btntransfer);
	cp.add(btnviewstat);
	
	
	btnupload.addActionListener(this);
	btndownload.addActionListener(this);
	btnbrowse.addActionListener(this);
	jcb.addItemListener(this);

		System.out.println("this is ip: "+ ip); 

	try {

		
		String addr="rmi://"+ ip +"/bgserver"; 
               	btinterface mi=(btinterface)Naming.lookup(addr);
		String []drives = mi.getDrives();

		System.out.println(drives.length);

		for(i=0;i<drives.length;i++)
		{
		   complete = false;
                   System.out.println(drives[i] +" length "+ drives[i].length());
		   if(drives[i].length()>1)
	            {
		   jcb.addItem(drives[i]);
		    }

		}

		complete = true;		

	 }
                     catch(Exception e)
	{
	   System.out.println(e);
	}

cp.add(listpane1);
cp.add(listpane2);
	
//===================== Logout =================================		        
addWindowListener(new WindowAdapter() 
		{
	
public void windowClosing(WindowEvent we)     
		{
           		String ipaddr;						
			
		  	try 
			{
		 	ipaddr = (InetAddress.getLocalHost()).getHostAddress();


String retval="";
         String fname2 = "server_address.txt"; 
          FileReader fr = new FileReader(fname2);
         BufferedReader br = new BufferedReader(fr);

  
  char[] buff = new char[1000];
  int nch;
  while ((nch = br.read(buff, 0, buff.length)) != -1) {
  
     retval = new String(buff, 0, nch);

}


			String addr="rmi://"+ retval +"/bgserver"; 
                	btinterface mi=(btinterface)Naming.lookup(addr);
                        String result=mi.del_ip(ipaddr);
			JOptionPane.showMessageDialog(null, result);
			} 
			catch(Exception e) 
				{ 
				System.out.println(e);
				}	
				System.exit(0);
				}
      				});


//===================== End Logout =================================		        



Thread t = new Thread(this);
t.start();	
	

}


public void run()
{

String sourcepath = "";
   String destpath = "";
   String filename = "";
   String ttype="";
   int transid = 0;

System.out.println("Background thread started");
 

  try
     {
	while(true)
	{

	System.out.println("Thread Starting");
        Thread.sleep(20000);

	System.out.println("After Sleep");

	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");    
   	File f = new File("tempdb.mdb");
   	String fname= f.getAbsolutePath();
   	Connection con = DriverManager.getConnection("Jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+fname,"","");
   	Statement stmt = con.createStatement();

  	ResultSet rs = stmt.executeQuery("select * from file_list where status='p'");
	while(rs.next())
	{
	  transid = rs.getInt("trans_id");
	  sourcepath = rs.getString("sourcepath");
	  destpath = rs.getString("destpath");
	  filename = rs.getString("filename");
	  ttype= rs.getString("ttype");

	}
  	con.close();

        if(ttype.equals("download"))
	{



	
		String addr="rmi://"+ ipaddr +"/bgserver"; 
               		btinterface mi=(btinterface)Naming.lookup(addr);
		byte []buf = mi.getFiledata(sourcepath);

		File f1 = new File(destpath +"\\"+ filename);
		
		String fullname = f1.getAbsolutePath();

		FileOutputStream outStream = new FileOutputStream(fullname);

		outStream.write(buf);

		outStream.close();

		//JOptionPane.showMessageDialog(null,"File Transfer successful");
	   

		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");    
   		File f2 = new File("tempdb.mdb");
   		String fname2= f2.getAbsolutePath();
   		Connection con1 = DriverManager.getConnection("Jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+fname2,"","");
   		Statement stmt1 = con1.createStatement();

		stmt1.executeUpdate("update file_list set status='c' where trans_id="+ transid);
		con1.close();

             
	
	}
	 else if(ttype.equals("upload"))
	 {


          	File f1 = new File(sourcepath);

		String fullname = f1.getAbsolutePath();

		//File p2 = new File(fullname);

		FileInputStream inStream = new FileInputStream(fullname);

		int inBytes = inStream.available();

		byte inBuf[] = new byte[inBytes];

		int bytesread = inStream.read(inBuf,0,inBytes);

		inStream.close();


		String addr="rmi://"+ ipaddr +"/bgserver"; 
               	btinterface mi=(btinterface)Naming.lookup(addr);
		String result = mi.pasteFile(inBuf, destpath);
  		//JOptionPane.showMessageDialog(null,result);

		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");    
   		File f3 = new File("tempdb.mdb");
   		String fname3= f3.getAbsolutePath();
   		Connection con2 = DriverManager.getConnection("Jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+fname3,"","");
   		Statement stmt2 = con2.createStatement();

		stmt2.executeUpdate("update file_list set status='c' where trans_id="+ transid);
		con2.close();
	

	 }
		
	

	}

   }
     catch(Exception e)
     {
       System.out.println(e);
     }	



}





public void getfilelist()
{

File f = new File(txtpath.getText());
String []files = f.list();

int size = listModel.getSize();

System.out.println(size);

listModel.clear();


for(i=0;i<files.length;i++)
	{
	
	listModel.addElement(files[i]);
	
	}		

}


public void setFolder(java.io.File folder) 
	{
        		if(folder!=null)
		{
			String fullpath = folder.getAbsolutePath();
       			txtpath.setText(fullpath);
			getfilelist();
                 		}
        		jDialog.dispose();
           	}



public void getFolderList()
{

try {
		String addr="rmi://"+ ipaddr +"/bgserver"; 
               		btinterface mi=(btinterface)Naming.lookup(addr);
		Vector v = mi.getfolders(txtpath1.getText());

listModel1.clear();


for(i=0;i<v.size();i++)
	{
	//System.out.println(v.elementAt(i));
	listModel1.addElement(v.elementAt(i));
		
	}	

			

	 }
                     catch(Exception e)
	{
	   System.out.println(e);
	}

}



public void actionPerformed(ActionEvent ae)
{

if(ae.getSource() == btnbrowse)
            		{
               			
			jDialog = new javax.swing.JDialog(new javax.swing.JFrame(),true);
     
		        	jFolderChooser = new JFolderChooser(this);
		        	jDialog.getContentPane().add( jFolderChooser ,java.awt.BorderLayout.CENTER);
		      	jDialog.setSize(350,500);
		        	jDialog.show();
             		}


if(ae.getSource() == btndown)
{

   String sourcepath = txtpath1.getText() + listModel1.elementAt(file_list1.getSelectedIndex()) +"\\" + listModel2.elementAt(file_list2.getSelectedIndex());
   String destpath = txtpath.getText();
   String filename = ""+listModel2.elementAt(file_list2.getSelectedIndex());


try {
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");    
   File f = new File("tempdb.mdb");
   String fname= f.getAbsolutePath();
   Connection con = DriverManager.getConnection("Jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+fname,"","");
   Statement stmt = con.createStatement();

  stmt.executeUpdate("insert into file_list(sourcepath, destpath, filename, ttype, status) values('"+ sourcepath +"','"+ destpath +"','"+ filename +"','download','p')");
  con.close();
  
 JOptionPane.showMessageDialog(null,"File Added to Queue");

  }
    catch(Exception e)
    {
	System.out.println(e);
    }


}



if(ae.getSource() == btnup)
{
 
   String destpath = txtpath1.getText() + listModel1.elementAt(file_list1.getSelectedIndex()) + "\\" + listModel.elementAt(file_list.getSelectedIndex());
   String sourcepath = txtpath.getText() +"\\"+ listModel.elementAt(file_list.getSelectedIndex());
   String filename = ""+listModel.elementAt(file_list.getSelectedIndex());


try {
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");    
   File f = new File("tempdb.mdb");
   String fname= f.getAbsolutePath();
   Connection con = DriverManager.getConnection("Jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+fname,"","");
   Statement stmt = con.createStatement();

  stmt.executeUpdate("insert into file_list(sourcepath, destpath, filename, ttype, status) values('"+ sourcepath +"','"+ destpath +"','"+ filename +"','upload','p')");
  con.close();
  
 JOptionPane.showMessageDialog(null,"File Added to Queue");

  }
    catch(Exception e)
    {
	System.out.println(e);
    }


}


if(ae.getSource() == btndownload)
{
   String sourcepath = txtpath1.getText() + listModel1.elementAt(file_list1.getSelectedIndex()) +"\\" + listModel2.elementAt(file_list2.getSelectedIndex());
   String destpath = txtpath.getText();
   String filename = ""+listModel2.elementAt(file_list2.getSelectedIndex());

System.out.println(sourcepath);
 
	try {
		String addr="rmi://"+ ipaddr +"/bgserver"; 
               		btinterface mi=(btinterface)Naming.lookup(addr);
		byte []buf = mi.getFiledata(sourcepath);

		File f1 = new File(destpath +"\\"+ filename);
		
		String fullname = f1.getAbsolutePath();

		FileOutputStream outStream = new FileOutputStream(fullname);

		outStream.write(buf);

		outStream.close();

		JOptionPane.showMessageDialog(null,"File Transfer successful");
	    }
                        catch(Exception e)
		{
		   System.out.println(e);
		} 

	}

  if(ae.getSource()==btnupload)
 {

   String destpath = txtpath1.getText() + listModel1.elementAt(file_list1.getSelectedIndex()) + "\\" + listModel.elementAt(file_list.getSelectedIndex());
   String sourcepath = txtpath.getText() +"\\"+ listModel.elementAt(file_list.getSelectedIndex());
   String filename = ""+listModel.elementAt(file_list.getSelectedIndex());

    try {

   File f1 = new File(sourcepath);

String fullname = f1.getAbsolutePath();

//File p2 = new File(fullname);

FileInputStream inStream = new FileInputStream(fullname);

int inBytes = inStream.available();

byte inBuf[] = new byte[inBytes];

int bytesread = inStream.read(inBuf,0,inBytes);

inStream.close();


String addr="rmi://"+ ipaddr +"/bgserver"; 
               		btinterface mi=(btinterface)Naming.lookup(addr);
		String result = mi.pasteFile(inBuf, destpath);
  		JOptionPane.showMessageDialog(null,result);
        
         }
           catch(Exception e)
          {
              System.out.println(e);
            }		

 }

  if(ae.getSource()==btnviewstat)
  {
    transstat ts = new transstat();
    ts.show();
   
   }


}

public void itemStateChanged(ItemEvent ie)
{

  if(ie.getSource() == jcb)
{
    String drv = ""+ jcb.getSelectedItem();
 	
    drv = drv.substring(drv.indexOf('(')+1,drv.length()-1);

    txtpath1.setText(drv+"\\");

if(complete)
{
   getFolderList();
}
  

}

}


public void getfileslist(String fname)
{

try {
		String addr="rmi://"+ ipaddr +"/bgserver"; 
               		btinterface mi=(btinterface)Naming.lookup(addr);
		Vector v = mi.getfiles(txtpath1.getText() + fname);

listModel2.clear();


for(i=0;i<v.size();i++)
	{
	//System.out.println(v.elementAt(i));
	listModel2.addElement(v.elementAt(i));
		
	}	

			

	 }
                     catch(Exception e)
	{
	   System.out.println(e);
	}




}


 public void valueChanged(ListSelectionEvent e) 
{

if(e.getSource()==file_list1)
{
 
   getfileslist(""+listModel1.elementAt(file_list1.getSelectedIndex()));



}


}

public static void main(String args[])
{
   browseapp bapp = new browseapp("localhost");
   bapp.show();
}
}

