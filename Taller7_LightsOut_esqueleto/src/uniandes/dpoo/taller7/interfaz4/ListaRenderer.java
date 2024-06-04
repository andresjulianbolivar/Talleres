package uniandes.dpoo.taller7.interfaz4;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ListaRenderer extends DefaultListCellRenderer
{

	@Override
	public Component getListCellRendererComponent(JList arg0, Object value, int index, boolean arg3, boolean arg4) {
		// TODO Auto-generated method stub
		super.getListCellRendererComponent(arg0, value, index, arg3, arg4);
		setFont(new Font("Arial",Font.BOLD,15));
		if (index<3)
		{
			setForeground(Color.GREEN);
		}
		else if (index<5)
		{
			setForeground(Color.BLUE);
		}
		return this;
	}

}
