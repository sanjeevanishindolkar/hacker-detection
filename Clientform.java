import java.sql.*;
import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;  
import java.rmi.*;
import java.io.*;
import java.util.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.*;

  public class Clientform extends JFrame implements ActionListener
    {
	Container cp;
	JLabel title, lbllist, lblimg1, lblpass;	
	ImageIcon ic1, ic2;
	JList machine_list;
	DefaultListModel listModel;
	String []machinelist = null;
	JButton btnconnect, btnexit;
	int i=0;
                  JPasswordField txtpass;
	JButton btnok, btncancle;
	 getpass gp;

                 

       public Clientform()
      {
	setSize(662,550);
	setTitle("CLIENTFORM");	
	
  	cp=getContentPane();
	cp.setLayout(null);
	cp.setBackground(Color.white);

	btnconnect = new JButton("Connect to Network Compuer");
	btnexit = new JButton("Logout");

                 gp = new getpass();

	
	ic1=new ImageIcon("images/title.jpg");  
	ic2=new ImageIcon("images/Icon_networks.jpg");  
	lblimg1 = new JLabel(ic2);
	title=new JLabel(ic1);
	lbllist = new JLabel("Computers in the Network");
	lbllist.setOpaque(true);
	lbllist.setBackground(Color.black);
	lbllist.setForeground(Color.white);
	lbllist.setHorizontalTextPosition(JLabel.CENTER);


	try {

//==============Read Server Address from server_address.txt===================

         String retval="";
         String fname = "server_address.txt"; 
          FileReader fr = new FileReader(fname);
         BufferedReader br = new BufferedReader(fr);

  
  char[] buff = new char[1000];
  int nch;
  while ((nch = br.read(buff, 0, buff.length)) != -1) {
  
     retval = new String(buff, 0, nch);
   }

//==============End of Reading Server Address from server_address.txt===================

		
                String addr="rmi://"+ retval +"/bgserver"; 
                btinterface mi=(btinterface)Naming.lookup(addr);
		machinelist = mi.Getlist();
		//JOptionPane.showMessageDialog(null,"Result : "+result)                            
	

	} catch(Exception e)
	  {
	    System.out.println(e);
	 }
	

	listModel = new DefaultListModel();

	for(i=0;i<machinelist.length;i++)
	{
	
	listModel.addElement(machinelist[i]);
	
	}


	
	machine_list = new JList(listModel);
	Color cl = new Color(192,192,192);
	machine_list.setBackground(cl);
	JScrollPane listScrollPane = new JScrollPane(machine_list);									             
       
	Insets ins=getInsets();
	
        title.setBounds(0,0,662,140);
        lbllist.setBounds(0,140,300,20);

        listScrollPane.setBounds(0,160,300,360);
        btnconnect.setBounds(320,380,300,50);
        btnexit.setBounds(320,450,300,50);
        lblimg1.setBounds(300,140,362,208);

       
	cp.add(title);
	cp.add(lbllist);
	cp.add(listScrollPane);
	cp.add(btnconnect);
	cp.add(btnexit);
	cp.add(lblimg1);

  btnconnect.addActionListener(this);

btnok.addActionListener(this);
btncancle.addActionListener(this);
btnexit.addActionListener(this);


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



      }




public void checkvalid(String ip, String pass)
{
   try
        {

//==============Read Server Address from server_address.txt===================

         String retval="";
         String fname = "server_address.txt"; 
          FileReader fr = new FileReader(fname);
         BufferedReader br = new BufferedReader(fr);

  
  char[] buff = new char[1000];
  int nch;
  while ((nch = br.read(buff, 0, buff.length)) != -1) {
  
     retval = new String(buff, 0, nch);
   }

//==============End of Reading Server Address from server_address.txt===================


                String addr="rmi://"+ retval +"/bgserver"; 
                btinterface mi=(btinterface)Naming.lookup(addr);
		String str = mi.checkpass(ip, pass);

	if(str.equals("valid"))
                 {

	gp.setVisible(false);
                  setVisible(false);

	browseapp bapp = new browseapp(ip);
	bapp.show();
	}
                   else
                        {
                          JOptionPane.showMessageDialog(null,"Invalid Password");

                        }


       }
         catch(Exception e)
       {
          System.out.println(e);
      }
}



public void actionPerformed(ActionEvent ae)
{
   if(ae.getSource()==btnconnect)
  {
      if(machine_list.isSelectionEmpty()==true)
      {
        JOptionPane.showMessageDialog(null,"Select the network computer you want to connect");
     }
      else
      {        
      gp.show();
      }
 }

  if(ae.getSource() == btnok)
 {
    String ipaddr = ""+machine_list.getSelectedValue();    

  ipaddr = ipaddr.substring(0,ipaddr.indexOf(','));   
  
checkvalid(ipaddr,txtpass.getText());

}

if(ae.getSource() == btncancle)
{
   gp.setVisible(false);
}

if(ae.getSource() == btnexit)
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
				setVisible(false);
				Loginform lof = new Loginform();
   				lof.show();
			


   
}

}	


public static void main(String args[])
      {
	Clientform cf=new Clientform();
	cf.show();
      }


class getpass extends JDialog
{
  
  public getpass()
 {
   setTitle("Enter Password");
   setSize(250,150);
   
   txtpass = new JPasswordField(20);
   lblpass = new JLabel("Password:");
   btnok = new JButton("OK");
   btncancle = new JButton("CANCLE");

   Container cp1 = getContentPane();
   cp1.setLayout(null);
   
   cp1.add(lblpass);
   cp1.add(txtpass);
   cp1.add(btnok);
   cp1.add(btncancle);

   lblpass.setBounds(10,10,80,30);
   txtpass.setBounds(80,10,150,30);
   btnok.setBounds(20,60,90,30);
   btncancle.setBounds(120, 60,90,30);
   


 }  


}



  }
	 


