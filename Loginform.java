import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;


  public class Loginform extends JFrame implements ActionListener
    {
	Container cp;
        JTextField sname;
       	JPasswordField spass;
        JButton loginbtn, btnexit;
        JLabel smname,smpass,backpic;
        ImageIcon ic1;
 
        public Loginform()
      {

	setSize(662,500);
	setTitle("LOGIN FORM");
	
	cp=getContentPane();
	cp.setLayout(null);
        
        	ic1=new ImageIcon("images/logo.jpg");	
	backpic=new JLabel(ic1); 
    
        	smname=new JLabel("System Name:");
	smpass=new JLabel("Password:");
	
	sname=new JTextField(20);
	spass=new JPasswordField(20);
	
	loginbtn=new JButton("LOGIN");
	btnexit = new JButton("EXIT");

	loginbtn.addActionListener(this);
	btnexit.addActionListener(this);
       
        Insets ins=getInsets();
        
        backpic.setBounds(0,0,662,360); 

        smname.setBounds(120,370,90,20);
        smpass.setBounds(120,400,90,20);

        sname.setBounds(210,370,150,20);
        spass.setBounds(210,400,150,20);

        loginbtn.setBounds(370,370,80,50);
        btnexit.setBounds(470,370,80,50);
	
	cp.add(sname);
	cp.add(spass);
      	cp.add(loginbtn);
        	cp.add(btnexit);
  	cp.add(smname);
	cp.add(smpass);	
        	cp.add(backpic);       
   
     }

	
	public void actionPerformed(ActionEvent ae)
     {
	try
	   {  

if(ae.getSource()==loginbtn)
	  {     
                int num;
                String dbsname,dbpass,ssname,sspass;
		dbsname=sname.getText();
		dbpass=spass.getText();
            
              
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
		mi.Addclient(dbsname,dbpass);
		//JOptionPane.showMessageDialog(null,"Result : "+result)
            }
           catch(Exception e)
             {
                System.out.println(e);
             }


                 
                        this.setVisible(false);
			Clientform cf=new Clientform();
			cf.show();
                    
	}
                  else if(ae.getSource() == btnexit)
	{
                    System.exit(0);
                 }

          }
        catch(Exception e)
          {
                  System.out.println(e); 
          }
			
		
	  	 
     }		

	

	public static void main(String args[])
     {
	Loginform lf=new Loginform();
	lf.show();
     }
  }

