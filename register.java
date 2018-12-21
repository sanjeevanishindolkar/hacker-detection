import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.rmi.*;
import java.util.prefs.Preferences;


public class register extends JFrame implements ActionListener
{
	Container cp;
	JLabel l1,l2,l3,l4,l5,l6,lblbg;
	JTextField t1,t2,t3;
	JPasswordField p1,p2;
	JComboBox cmb;
	JButton b1,b2;
	Insets ins;
	ImageIcon ic;	
	public static final String PREF_KEY = "softmusk";

	public register()
	{
		setSize(1024,768);
		setTitle("Login");

		cp=getContentPane();
		cp.setLayout(null);
		ins=getInsets();

		b1=new JButton("Register");
		b2=new JButton("<< Back");
		
		l1=new JLabel("Name");
		l2=new JLabel("Uid");
		l3=new JLabel("Password");
		l4=new JLabel("Confirm Password");
		l5=new JLabel("Security Question");
		l6=new JLabel("Answer");
		cmb=new JComboBox();
		
		t1=new JTextField(20);
		t2=new JTextField(20);
		t3=new JTextField(20);
		
		p1=new JPasswordField(20);
		p2=new JPasswordField(20);
		ic=new ImageIcon("other.jpeg");
		lblbg = new JLabel(ic);
		
		cp.add(l1);
		cp.add(t1);
		cp.add(l2);
		cp.add(t2);
		cp.add(l3);
		cp.add(p1);
		cp.add(l4);
		cp.add(p2);
		cp.add(l5);
		cp.add(cmb);
		cmb.addItem("Enter your first phone no.");
		cmb.addItem("Enter your first teachers name");
		cp.add(l6);
		cp.add(t3);
		cp.add(b1);
		cp.add(b2);
		cp.add(lblbg);
		l1.setBounds(150,20,150,25);
		t1.setBounds(400,20,150,25);
		l2.setBounds(150,75,150,25);
		t2.setBounds(400,75,150,25);
		l3.setBounds(150,130,150,25);
		p1.setBounds(400,130,150,25);
		l4.setBounds(150,185,150,25);
		p2.setBounds(400,185,150,25);
		l5.setBounds(150,240,150,25);
		cmb.setBounds(400,240,200,25);
		l6.setBounds(150,295,150,25);
		t3.setBounds(400,295,150,25);
		b1.setBounds(200,350,100,35);
		b2.setBounds(400,350,100,35);
		lblbg.setBounds(0,0,1024,786);		
		b1.addActionListener(this);
		b2.addActionListener(this);

	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			boolean valid = true;

                        	if(t1.getText().equals(""))
                        	{
                        		JOptionPane.showMessageDialog(null,"Enter the user Name");
                                		valid = false;
                        	}
		if(t2.getText().equals(""))
                        	{
                        		JOptionPane.showMessageDialog(null,"Enter the user id");
                                		valid = false;
                        	}
		if(p1.getText().equals(""))
                        	{
                        		JOptionPane.showMessageDialog(null,"Enter the Password");
                                		valid = false;
                        	}	
		if(p2.getText().equals(""))
                        	{
                        		JOptionPane.showMessageDialog(null,"Confirm the Password");
                                		valid = false;
                        	}
		/*if(""+ cmb.getSelectedItem().equals(""))
                        	{
                        		JOptionPane.showMessageDialog(null,"Select a question");
                                		valid = false;
                        	}*/
		if(t3.getText().equals(""))
                        	{
                        		JOptionPane.showMessageDialog(null,"Enter the answer");
                                		valid = false;
                        	}
		/*if(p1.getText().notequals(p2.getText())
		{
			JOptionPane.showMessageDialog(null,"Password do not match");
			valid=false;
		}*/
	
		if(valid)
                        	{
			
			try
			{	
				String s1,s2,s3,s4,s5;
				
				s1=t1.getText();

				s2=t2.getText();
				
				s3=p1.getText();
				
				s4= ""+ cmb.getSelectedItem();
				
				s5=t3.getText();


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
				
				

				btinterface ma = (btinterface)Naming.lookup(serveraddress);
				
				String str = ma.reguser(s1,s2,s3,s4,s5);


				if(str.equals("Register Successful"))
				{
				  Preferences systemPref = Preferences.systemRoot();

				  systemPref.put(PREF_KEY, s2);

				}


				JOptionPane.showMessageDialog(null,str);

			}
			catch(Exception e)
			{
				System.out.println("Exception"+e);

			}
                        		}
			
		}
		else if(ae.getSource()==b2)
		{
			login l= new login();
			l.show();
			setVisible(false);
		}
		
	}
}
		
		
	
	
		
		
		

 

	
	