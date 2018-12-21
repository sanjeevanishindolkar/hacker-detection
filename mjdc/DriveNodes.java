

package mjdc;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.tree.DefaultMutableTreeNode;

// Referenced classes of package mjdc:
//            MyDefaultMutableTreeNode

public class DriveNodes
{

    public DriveNodes(File file)
    {
        driverNode = null;
        driverNode = new DefaultMutableTreeNode(file);
        Object obj = null;
        doDirectory(driverNode, file);
    }

    public void doDirectory(DefaultMutableTreeNode defaultmutabletreenode, File file)
    {
        java.io.FileFilter filefilter = null;
        File afile[] = file.listFiles(filefilter);
        if(afile == null || afile.length < 1)
            return;
        Arrays.sort(afile);
        ArrayList arraylist = new ArrayList();
        for(int i = 0; i < afile.length; i++)
            if(!afile[i].isFile())
            {
                MyDefaultMutableTreeNode mydefaultmutabletreenode = new MyDefaultMutableTreeNode(afile[i]);
                mydefaultmutabletreenode.setPopulated(true);
                defaultmutabletreenode.add(mydefaultmutabletreenode);
                doSubDirectory(mydefaultmutabletreenode, afile[i]);
            }

    }

    public void doSubDirectory(DefaultMutableTreeNode defaultmutabletreenode, File file)
    {
        java.io.FileFilter filefilter = null;
        File afile[] = file.listFiles(filefilter);
        if(afile == null || afile.length < 1)
            return;
        Arrays.sort(afile);
        ArrayList arraylist = new ArrayList();
        for(int i = 0; i < afile.length; i++)
            if(!afile[i].isFile())
            {
                MyDefaultMutableTreeNode mydefaultmutabletreenode = new MyDefaultMutableTreeNode(afile[i]);
                defaultmutabletreenode.add(mydefaultmutabletreenode);
            }

    }

    public DefaultMutableTreeNode getNodes()
    {
        return driverNode;
    }

    public byte getDirectory()
    {
        return directory;
    }

    public void setDirectory(byte byte0)
    {
        directory = byte0;
    }

    public byte[] getSubDirectory()
    {
        return subDirectory;
    }

    public void setSubDirectory(byte abyte0[])
    {
        subDirectory = abyte0;
    }

    DefaultMutableTreeNode driverNode;
    private byte directory;
    private byte subDirectory[];
}