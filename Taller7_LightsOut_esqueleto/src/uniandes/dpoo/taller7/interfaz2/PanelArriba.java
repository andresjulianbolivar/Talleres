package uniandes.dpoo.taller7.interfaz2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelArriba extends JPanel implements ActionListener, ItemListener
{
	private JComboBox<String> dimensiones;
	
	private JLabel tamanio;
	
	private JLabel dificultad;
	
	private JRadioButton facil;
	
	private JRadioButton medio;
	
	private JRadioButton dificil;
		
	public PanelArriba()
	{
		super();
		
		setLayout(new GridLayout(1,6));
		setVisible(true);
		
		tamanio = new JLabel("Tamaño");
		add(tamanio);
		
		
		dimensiones = new JComboBox<String>();
		dimensiones.addItem("4x4");
		dimensiones.addItem("5x5");
		dimensiones.addItem("6x6");
		dimensiones.addItem("7x7");
		dimensiones.addItem("8x8");
		dimensiones.addItem("9x9");
		dimensiones.addItem("10x10");
		dimensiones.addItemListener(this);
		add(dimensiones);
		
		dificultad = new JLabel("Dificultad");
		add(dificultad);
		
		facil = new JRadioButton();
		facil.setText("Fácil");
		add(facil);
		
		medio = new JRadioButton();
		medio.setText("Medio");
		add(medio);
		
		dificil = new JRadioButton();
		dificil.setText("Dificil");
		add(dificil);
		repaint();
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
