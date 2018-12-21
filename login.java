import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.*;
import java.util.prefs.Preferences;


public class login extends JFrame implements ActionListener
{
	Container cp;
	JLabel l1,l2,l3, lblbg;
	JTextField t1;
	JButton b1,b2,b3,b4;
	JPasswordField p1;
	Insets ins;
	ImageIcon ic;
	public static final String PREF_KEY = "softmusk";

	
	public login()
	{
		setSize(1024,786);
		setTitle("Login");

		cp=getContentPane();
		cp.setLayout(null);
		ins=getInsets();

		b1=new JButton("Login");
		b2=new JButton("Forgot Password");
		b3=new JButton("New user register here");
		b4=new JButton("Exit");
		
	
		l1=new JLabel("User ID");
		l2=new JLabel("Password");
		l3=new JLabel("Forgot Password");
		
		t1=new JTextField(20);
		p1=new JPasswordField(20);

 		ic = new ImageIcon("Copy of 837941001.JPEG");
		lblbg = new JLabel(ic);
		
		
		cp.add(l1);
		cp.add(t1);
		cp.add(l2);
		cp.add(p1);
		cp.add(b1);
		cp.add(b2);
		cp.add(b3);
		cp.add(b4);
		cp.add(lblbg);
                             
		
		
		l1.setBounds(100,100,150,25);
		t1.setBounds(350,100,150,25);
		l2.setBounds(100,160,150,25);
		p1.setBounds(350,160,150,25);
		b1.setBounds(130,220,135,35);
		b2.setBounds(330,220,135,35);
		b3.setBounds(130,285,325,45);
		b4.setBounds(250,360,100,35);
		lblbg.setBounds(0,0,1024,786);
		
	
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);

	}
	
	public void actionPerformed(ActionEvent ae)
	{
              boolean valid=false;
	
		if(ae.getSource()==b1)
		{

                 Preferences systemPref = Preferences.systemRoot();

		 String result = ""+ systemPref.get(PREF_KEY, "key not found.");

		System.out.println(result);

		 if(result.equals(t1.getText()))
                 {
                    valid=true;
                   
		 } else
                     {

			JOptionPane.showMessageDialog(null,"This Machine is not Authorised, you could be a hacker. Access Denied");
			valid=false;
		     }
		

			
			try
			{

  if(valid)
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


                String serveraddress="rmi://"+ retval +"/bgserver"; 



				String s1,s2;
				
				s1=t1.getText();

				s2=p1.getText();

				btinterface ma = (btinterface)Naming.lookup(serveraddress);
				
				String str = ma.loclog(s1,s2);

				JOptionPane.showMessageDialog(null,str);
				
				if(str.toString().equals("Login Successful"))
				{
				
					Loginform lf = new Loginform();
                                        lf.show();
					setVisible(false);
				}


                       }

//===========================================================================	

			}
			catch(Exception e)
			{
				System.out.println("Exception"+e);

			}
			
			
			
		}
		else if(ae.getSource()==b2)
		{
			getpass p=new getpass();
			p.show();
			setVisible(false);			
		}
		
		
		else if(ae.getSource()==b3)
		{
			register r=new register();
			r.show();
			setVisible(false);	
		}
		
		else 
		{
			System.exit(0);
			
		}
	}
	public static void main(String args[])
	{
		login l=new login();
		l.show();
	}

}
	
		
		
	
	
		
		
		

 

	
	