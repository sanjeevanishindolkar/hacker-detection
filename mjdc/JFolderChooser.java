

package mjdc;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

// Referenced classes of package mjdc:
//            JTreePanel, Selector

public class JFolderChooser extends JPanel
{

    public JFolderChooser(Selector selector)
    {
        jTreePanel = null;
        currentDrive = null;
        selectedDirectory = null;
        jButtonCanceleEntered = false;
        jButtonOkEntered = false;
        aSelector = null;
        jFolderChooserVersion = "    ";
        selectionModem = 1;
      
        aSelector = selector;
        File file = new File(".");
        for(String s = file.getAbsolutePath(); s != null; s = file.getParent())
            file = new File(s);

        currentDrive = file;
        secondaryComponents();
    }

    void setJlistModel()
    {
        JFolderChooser _tmp = this;
        File afile[] = File.listRoots();
        Arrays.sort(afile);
        String as[] = new String[afile.length];
        for(int i = 0; i < afile.length; i++)
            as[i] = afile[i].getName();

    }

    void setNorthPanele()
    {
        jPanelNoth = new JPanel();
        jPanelNoth.setLayout(new BorderLayout());
        jPanelNoth.setBorder(new TitledBorder(new BevelBorder(1)));
        jTextField = new JTextField(currentDrive.toString());
        jTextField.setEditable(false);
        jTextField.setBackground(new Color(255, 255, 255));
        jPanelNoth.add(jTextField, "South");
        jPanelFrameCenter.add(jPanelNoth, "North");
    }

    void setCenterPanele()
    {
        JPanelCenter = new JPanel();
        JPanelCenter.setLayout(new BorderLayout());
        JPanelCenter.setBorder(new TitledBorder(new BevelBorder(1), "Directories"));
        jPanelFrameCenter.add(JPanelCenter, "Center");
        jLabel1 = new JLabel();
        jLabel1.setText("Please select a directory");
        jLabel1.setFont(new Font("Dialog", 1, 14));
        JPanelCenter.add(jLabel1, "North");
        jTreePanel = new JTreePanel(this);
        JPanelCenter.add(jTreePanel, "Center");
    }

    void setSouthPanele()
    {
        jPanelSouth = new JPanel();
        jPanelSouth.setLayout(new BorderLayout());
        jPanelSouth.setBorder(new TitledBorder(new BevelBorder(1)));
        jPanelFrameCenter.add(jPanelSouth, "South");
        jPanelSouthCenter = new JPanel();
        jPanelSouthCenter.setLayout(new BorderLayout());
        jPanelSouth.add(jPanelSouthCenter, "Center");
      
        JPanel jpanel = new JPanel();
        jpanel.setBorder(new TitledBorder(new BevelBorder(1), "Drives"));
        jpanel.setLayout(new BorderLayout());
        jPanelSouthCenter.add(jpanel, "North");
        jComboBoxDir = new JComboBox();
        JFolderChooser _tmp = this;
        File afile[] = File.listRoots();
        Arrays.sort(afile);
        boolean flag = false;
        for(int i = 0; i < afile.length; i++)
            jComboBoxDir.addItem(afile[i]);

        jComboBoxDir.setSelectedItem(currentDrive);
        selectedDirectory = (File)jComboBoxDir.getSelectedItem();
        jComboBoxDir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                jComboBoxDirActionPerformed(actionevent);
            }

        });
        JLabel jlabel1 = new JLabel("Please select a Drive");
        jlabel1.setFont(new Font("Dialog", 1, 14));
        jpanel.add(jlabel1, "North");
        jpanel.add(jComboBoxDir, "South");
        jPaneSouthCenterSouth = new JPanel();
        jPaneSouthCenterSouth.setLayout(new GridLayout(1, 2));
        jPanelSouthCenter.add(jPaneSouthCenterSouth, "South");
        jButtonCancel = new JButton();
        jButtonCancel.setText("Cancel");
        jButtonCancel.addMouseListener(new MouseAdapter() {

            public void mouseReleased(MouseEvent mouseevent)
            {
                jButtonCancelMouseReleased(mouseevent);
            }

            public void mouseExited(MouseEvent mouseevent)
            {
                jButtonCancelMouseExited(mouseevent);
            }

            public void mouseEntered(MouseEvent mouseevent)
            {
                jButtonCancelMouseEntered(mouseevent);
            }

        });
        jPaneSouthCenterSouth.add(jButtonCancel);
        jButtonOk = new JButton();
        jButtonOk.setText("Ok");
        jButtonOk.addMouseListener(new MouseAdapter() {

            public void mouseReleased(MouseEvent mouseevent)
            {
                jButtonOkMouseReleased(mouseevent);
            }

            public void mouseExited(MouseEvent mouseevent)
            {
                jButtonOkMouseExited(mouseevent);
            }

            public void mouseEntered(MouseEvent mouseevent)
            {
                jButtonOkMouseEntered(mouseevent);
            }

        });
        jPaneSouthCenterSouth.add(jButtonOk);
    }

    private void secondaryComponents()
    {
        setLayout(new BorderLayout());
        jPanelFrameCenter = new JPanel();
        jPanelFrameCenter.setLayout(new BorderLayout());
        jPanelFrameCenter.setBorder(new TitledBorder(new BevelBorder(1), "Directory path"));
        setNorthPanele();
        setCenterPanele();
        setSouthPanele();
        add(jPanelFrameCenter, "Center");
    }

    private void jComboBoxDirActionPerformed(ActionEvent actionevent)
    {
        currentDrive = (File)jComboBoxDir.getSelectedItem();
        jTextField.setText(currentDrive.toString());
        jTreePanel.setDrive(currentDrive);
    }

    private void jButtonOkMouseReleased(MouseEvent mouseevent)
    {
        if(jButtonOkEntered)
        {
            aSelector.setFolder(selectedDirectory);
            finalize();
        }
    }

    private void jButtonOkMouseExited(MouseEvent mouseevent)
    {
        jButtonOkEntered = false;
    }

    private void jButtonOkMouseEntered(MouseEvent mouseevent)
    {
        jButtonOkEntered = true;
    }

    private void jButtonCancelMouseReleased(MouseEvent mouseevent)
    {
        if(jButtonCanceleEntered)
        {
            aSelector.setFolder(null);
            finalize();
        }
    }

    private void jButtonCancelMouseExited(MouseEvent mouseevent)
    {
        jButtonCanceleEntered = false;
    }

    private void jButtonCancelMouseEntered(MouseEvent mouseevent)
    {
        jButtonCanceleEntered = true;
    }

    private void exitForm(WindowEvent windowevent)
    {
    }

    public File getCurrentDrive()
    {
        return currentDrive;
    }

    public void setCurrentDrive(File file)
    {
        currentDrive = file;
    }

    public String getJFolderChooserVersion()
    {
        return jFolderChooserVersion;
    }

    public void finalize()
    {
        jTreePanel = null;
        currentDrive = null;
        aSelector = null;
    }

    public File getSelectedDirectory()
    {
        return selectedDirectory;
    }

    public void setSelectedDirectory(File file)
    {
        selectedDirectory = file;
        jTextField.setText(selectedDirectory.getAbsolutePath());
    }

    private JPanel JPanelCenter;
    private JButton jButtonCancel;
    private JButton jButtonOk;
    private JComboBox jComboBoxDir;
    private JLabel jLabel1;
    private JPanel jPaneSouthCenterSouth;
    private JPanel jPanelFrameCenter;
    private JPanel jPanelNoth;
    private JPanel jPanelSouth;
    private JPanel jPanelSouthCenter;
    private JScrollPane jScrollPane;
    private JTextField jTextField;
    private JTree jTree;
    JTreePanel jTreePanel;
    private File currentDrive;
    private File selectedDirectory;
    boolean jButtonCanceleEntered;
    boolean jButtonOkEntered;
    Selector aSelector;
    private String jFolderChooserVersion;
    public static final int SINGLE_TREE_SELECTION = 1;
    public static final int CONTIGUOUS_TREE_SELECTION = 2;
    public static final int DISCONTIGUOUS_TREE_SELECTION = 4;
    public int selectionModem;


}