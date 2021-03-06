package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import diesisteinprojekt.DBHandler;
import diesisteinprojekt.Group;
import diesisteinprojekt.User;


public class EditGroupsTreePopupMenu extends JPopupMenu{
	//PopupMenu for EditUserTree
	JMenuItem deleteGroup;
	JMenuItem deleteUserFromGroup;
	JMenuItem addUserToGroup;
	String elementLabel;
	String groupLabel;
	public Font font = new Font("Source Sans Pro", Font.PLAIN, 12);
	DBHandler dbhandler = new DBHandler();
	ArrayList<String> groups = new ArrayList<String>();
	
    public void setPopupMenu(JTree tree){
    	groups = dbhandler.getGroupNameList();
    
    	DefaultMutableTreeNode selectedElement 
    	=(DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent();
    	elementLabel = selectedElement.getUserObject().toString();
    	groupLabel = selectedElement.getParent().toString();
        deleteGroup = new JMenuItem(elementLabel + " l�schen");
        deleteGroup.setFont(font);
        deleteUserFromGroup = new JMenuItem(elementLabel + " aus " + groupLabel +" entfernen");
        deleteUserFromGroup.setFont(font);
        addUserToGroup = new JMenuItem("Teilnehmer zu " + elementLabel + " hinzuf�gen");
        addUserToGroup.setFont(font);
    	if (groups.contains(elementLabel)) {
    	//adds menu items to popupmenu -> depends on selection(group or user selected?)
    		add(deleteGroup);
    		add(addUserToGroup);
    	}
    	else{
    		add(deleteUserFromGroup);
    	}
    	
    	popupListener(tree, selectedElement);
    }
   
    public void popupListener(final JTree tree, final DefaultMutableTreeNode selectedElement){
    	//expels the selected user from group and removes user from tree
		deleteUserFromGroup.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				 int dialogButton = JOptionPane.YES_NO_OPTION;
	                JOptionPane.showConfirmDialog (null, elementLabel + " unwiderruflich l�schen?","Warning",dialogButton);

	                if(dialogButton == JOptionPane.YES_OPTION){ 

	                	dbhandler.deleteUserFromGroup(elementLabel, groupLabel);
	                	selectedElement.removeFromParent();
	                	DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
	                	model.reload();
	                
	               }
			}
		});
		
		deleteGroup.addActionListener(new ActionListener() {
			//deletes selected group from db and removes group from tree 
			public void actionPerformed(ActionEvent arg0) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				JOptionPane.showConfirmDialog (null, elementLabel + " unwiderruflich l�schen?","Warning",dialogButton);

                if(dialogButton == JOptionPane.YES_OPTION){ 
                	dbhandler.deleteGroup(elementLabel);
                	selectedElement.removeFromParent();
                	DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                	model.reload();
                	
                }
			}
		});
		addUserToGroup.addActionListener(new ActionListener() {
			//gets all users without group from db and creates a dialog pane to select a user
			//adds selected user to tree
			public void actionPerformed(ActionEvent arg0) {
					ArrayList<String> users = dbhandler.getUserNameList();
					Object[] options = users.toArray();
					Object addUserDialog = JOptionPane.showInputDialog(null, "Teilnehmer ausw�hlen", "Teilnehmer zur Gruppe hinzuf�gen", JOptionPane.QUESTION_MESSAGE, null, options, users);
					String addedUser = addUserDialog.toString();
					dbhandler.addUserToGroup(addedUser, elementLabel);
					selectedElement.add(new DefaultMutableTreeNode(addedUser));
                	DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                	model.reload();
                	
                }	
		});
    }
   }
    
			
		
