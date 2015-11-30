package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class showGroupPanel extends JPanel{
	
	private JPanel showGroupTitlePanel;
	private JPanel showGroupButtonPanel;
	private JPanel showGroupActionPanel;
	private GUIHelper guihelper = new GUIHelper();
	
	public showGroupPanel() {
		showGroup();
	}
	
	public void showGroup() {
		
		setLayout(new GridLayout(3, 1));
		setPreferredSize(new Dimension(600, 600));
		setVisible(true);
		
		showGroupTitlePanel = guihelper.setPanel();
		JLabel createGroupTitle = guihelper.setLabel("Gruppen anzeigen", 36);
		showGroupTitlePanel.add(createGroupTitle);
		
		showGroupButtonPanel = guihelper.setPanel();
		JButton save = guihelper.setButton("Speichern");
		JButton back = guihelper.setButton("Zur�ck ins Men�");
		showGroupButtonPanel.add(save);
		showGroupButtonPanel.add(back);
		
		showGroupActionPanel = guihelper.setPanel();
		// hier muss noch ne action rein
		
		add(showGroupTitlePanel);
		add(showGroupButtonPanel);
		add(showGroupActionPanel);
	}

}