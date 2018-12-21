

package mjdc;

import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;

public class MyDefaultMutableTreeNode extends DefaultMutableTreeNode
{

    public MyDefaultMutableTreeNode(File file)
    {
        super(file);
        populated = false;
    }

    public MyDefaultMutableTreeNode(Object obj)
    {
        super(obj);
        populated = false;
    }

    public String toString()
    {
        File file = (File)getUserObject();
        return file.getName();
    }

    public boolean isPopulated()
    {
        return populated;
    }

    public void setPopulated(boolean flag)
    {
        populated = flag;
    }

    private boolean populated;
}