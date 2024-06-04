package uniandes.dpoo.taller7.interfaz4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import uniandes.dpoo.taller7.modelo.RegistroTop10;
import uniandes.dpoo.taller7.modelo.Top10;

public class PanelTop10 extends JDialog
{	
	private JList top10;
	
	private DefaultListModel<String> lista;
	
	private ListaRenderer render;
	
	public PanelTop10(InterfazJuego papi)
	{
		super(papi,true);
		setTitle("Top-10");
		setSize(100,200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(1,1));
		setLocationRelativeTo(null);
		
		lista = new DefaultListModel<String>();
		
		top10 = new JList<>(lista);
		
		render = new ListaRenderer();
		
		top10.setCellRenderer(render);
		
		add(new JScrollPane(top10));
	}
	
	public void actualizarTop10(Top10 top10)
	{
		lista.clear();
		Collection<RegistroTop10> registros = top10.darRegistros();
		int i = 0;
		for (RegistroTop10 nRegistro: registros)
		{
			String registro = nRegistro.toString();
			JLabel parcial = new JLabel(registro);
			parcial.setForeground(Color.GREEN);
			registro = parcial.getText();
			this.lista.addElement(""+(i+1)+". "+registro);
			this.top10.repaint();
			i++;
		}
	}
}
