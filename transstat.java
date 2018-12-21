import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;


class transstat extends JFrame
{

  String[] columnNames = {"File Name","Status"};

Connection con = null;
   Statement stmt = null;

 JTable table;
 Object data[][] = null;

Container cp;


public transstat()
{
   setSize(600,500);
   setTitle("Status");

cp = getContentPane();

 cp.setLayout(null);


int count = 0, i=0;

   try {
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");    
   File f = new File("tempdb.mdb");
   String fname= f.getAbsolutePath();
   con = DriverManager.getConnection("Jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+fname,"","");
   stmt = con.createStatement();
  
   ResultSet rs = stmt.executeQuery("select * from file_list where status='p'");
  while(rs.next())
 {
   count++;
}


data = new String[count][2];


rs = stmt.executeQuery("select * from file_list where status='p'");
  while(rs.next())
 {
    data[i][0] = rs.getString("filename");
    data[i][1] = "Waiting in Que";
    i++;
 }

con.close();
} catch(Exception e)
  {  System.out.println(e);
     }

table = new JTable(data, columnNames);

table.getTableHeader().setBounds(10,40,570,20);
 table.setBounds(10,60,570,350);
 
cp.add(table.getTableHeader());
cp.add(table);

repaint(); 

}

}

  