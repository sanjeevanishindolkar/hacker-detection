
package mjdc;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

// Referenced classes of package mjdc:
//            DriveNodes, MyDefaultMutableTreeNode, JFolderChooser

public class JTreePanel extends JPanel
    implements TreeWillExpandListener
{
    class MyRenderer extends DefaultTreeCellRenderer
    {

        public Component getTreeCellRendererComponent(JTree jtree, Object obj, boolean flag, boolean flag1, boolean flag2, int i, boolean flag3)
        {
            super.getTreeCellRendererComponent(jtree, obj, flag, flag1, flag2, i, flag3);
            if(flag2)
                setIcon(getClosedIcon());
            return this;
        }

        public MyRenderer()
        {
        }
    }


    public JTreePanel(File file)
    {
        selectedFile = null;
        aJFolderChooser = null;
        iniComponents(file);
    }

    public JTreePanel(JFolderChooser jfolderchooser)
    {
        selectedFile = null;
        aJFolderChooser = null;
        aJFolderChooser = jfolderchooser;
        iniComponents(aJFolderChooser.getCurrentDrive());
    }

    void setDrive(File file)
    {
        DriveNodes drivenodes = new DriveNodes(file);
        DefaultTreeModel defaulttreemodel = new DefaultTreeModel(drivenodes.getNodes());
        tree.setModel(defaulttreemodel);
        tree.setSelectionPath(tree.getPathForLocation(0, 0));
    }

    private void iniComponents(File file)
    {
        setLayout(new BorderLayout());
        jScrollPane1 = new JScrollPane();
        add(jScrollPane1, "Center");
        DriveNodes drivenodes = new DriveNodes(file);
        DefaultTreeModel defaulttreemodel = new DefaultTreeModel(drivenodes.getNodes());
        tree = new JTree(defaulttreemodel);
        tree.setCellRenderer(new MyRenderer());
        tree.setEditable(false);
        tree.getSelectionModel().setSelectionMode(aJFolderChooser.selectionModem);
        tree.setShowsRootHandles(false);
        jScrollPane1.setViewportView(tree);
        tree.setSelectionPath(tree.getPathForLocation(0, 0));
        tree.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent treeselectionevent)
            {
                DefaultMutableTreeNode defaultmutabletreenode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
                if(defaultmutabletreenode == null)
                {
                    return;
                } else
                {
                    aJFolderChooser.setSelectedDirectory((File)defaultmutabletreenode.getUserObject());
                    return;
                }
            }

        });
        tree.addTreeWillExpandListener(this);
    }

    public void treeWillCollapse(TreeExpansionEvent treeexpansionevent)
        throws ExpandVetoException
    {
    }

    public void treeWillExpand(TreeExpansionEvent treeexpansionevent)
        throws ExpandVetoException
    {
        TreePath treepath = treeexpansionevent.getPath();
        MyDefaultMutableTreeNode mydefaultmutabletreenode = (MyDefaultMutableTreeNode)treepath.getLastPathComponent();
        if(mydefaultmutabletreenode.isPopulated())
        {
            int i = mydefaultmutabletreenode.getChildCount();
            if(i < 1)
                return;
            for(int j = 0; j < i; j++)
            {
                MyDefaultMutableTreeNode mydefaultmutabletreenode1 = (MyDefaultMutableTreeNode)mydefaultmutabletreenode.getChildAt(j);
                populateMyDefaultMutableTreeNode(mydefaultmutabletreenode1);
            }

        } else
        {
            populateMyDefaultMutableTreeNode(mydefaultmutabletreenode);
        }
    }

    void populateMyDefaultMutableTreeNode(MyDefaultMutableTreeNode mydefaultmutabletreenode)
    {
        mydefaultmutabletreenode.setPopulated(true);
        File file = (File)mydefaultmutabletreenode.getUserObject();
        if(file.isFile())
            return;
        java.io.FileFilter filefilter = null;
        File afile[] = file.listFiles(filefilter);
        if(afile == null || afile.length < 1)
            return;
        Arrays.sort(afile);
        ArrayList arraylist = new ArrayList();
        for(int i = 0; i < afile.length; i++)
            if(!afile[i].isFile())
            {
                MyDefaultMutableTreeNode mydefaultmutabletreenode1 = new MyDefaultMutableTreeNode(afile[i]);
                mydefaultmutabletreenode.add(mydefaultmutabletreenode1);
            }

    }

    public byte[] getFileSys()
    {
        return fileSys;
    }

    public void setFileSys(byte abyte0[])
    {
        fileSys = abyte0;
    }

    public byte[] getDirectories()
    {
        return directories;
    }

    public void setDirectories(byte abyte0[])
    {
        directories = abyte0;
    }

    public char[] getDrives()
    {
        return drives;
    }

    public void setDrives(char ac[])
    {
        drives = ac;
    }

    private JScrollPane jScrollPane1;
    private JTree tree;
    File selectedFile;
    JFolderChooser aJFolderChooser;
    private byte fileSys[];
    private byte directories[];
    private char drives[];

}