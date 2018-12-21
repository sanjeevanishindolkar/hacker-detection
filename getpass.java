import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.*;


public class getpass extends JFrame implements ActionListener
{
	Container cp;
	JLabel l1,lblbg,l2,l3;
	JTextField t1,t2;
	JButton b1,b2,b3;
	Insets ins;
	ImageIcon ic;
	String squest,pass,ans;
	
	
	

	public getpass()
	{
		setSize(1024,786);
		setTitle("Get password");

		cp=getContentPane();
		cp.setLayout(null);
		ins=getInsets();

		b1=new JButton("Submit");
		b2=new JButton("<< Back");
		l1=new JLabel("User ID");
		t1=new JTextField(20);
		ic = new ImageIcon("other.jpg");
		lblbg = new JLabel(ic);
		l2=new JLabel("");
		l3=new JLabel("Answer");
		t2=new JTextField(20);
		b3=new JButton("Get password");
		
		cp.add(l1);
		cp.add(t1);
		cp.add(b1);
		cp.add(b2);
		cp.add(l2);
		cp.add(l3);
		cp.add(t2);
		cp.add(b3);
		
		cp.add(lblbg);
                             	
		l1.setBounds(100,100,150,25);
		t1.setBounds(350,100,150,25);
		b1.setBounds(130,220,135,35);
		b2.setBounds(330,220,135,35);
		l2.setBounds(130,250,300,40);
		l3.setBounds(130,350,135,35);
		t2.setBounds(330,350,135,35);
		b3.setBounds(215,450,135,35);
		lblbg.setBounds(0,0,1024,786);
		
	
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
	
		if(ae.getSource()==b1)
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


                String serveraddress="rmi://"+ retval +"/bgserver"; 

				String s1,s2;
				
				s1=t1.getText();

				btinterface ma = (btinterface)Naming.lookup(serveraddress);
				String result = ma.getpass1(s1);

				squest = result.substring(0,result.indexOf(','));

				String tmpresult = result.substring(result.indexOf(',')+1,result.length());

				ans = tmpresult.substring(0,tmpresult.indexOf(','));
				pass = tmpresult.substring(tmpresult.indexOf(',')+1,tmpresult.length());
				
				System.out.println(squest);
				System.out.println(ans);
				System.out.println(pass);
				
				l3.setText(squest);
				
				
				
						
			}
			catch(Exception e)
			{
				System.out.println("Exception"+e);

			}
		}
		else if(ae.getSource()==b2)
		{
			try
			{
			
				login a = new login();
				a.show();
				setVisible(false);
			}
			
			catch(Exception e)
			{
				System.out.println("Exception"+e);

			}
		}
		else if(ae.getSource()==b3)
		{
			try
			{
				
			
			if(t2.getText().equals(ans))
			{
				JOptionPane.showMessageDialog(null,pass);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Enter the correct answer");
			}
			}
			
			catch(Exception e)
			{
				System.out.println("Exception"+e);

			}
		}
		
				
		
	}

	public static void main(String args[])
	{
		getpass l=new getpass();
		l.show();
	}
}
	
		
		
	
	
		
		
		

 

	
	