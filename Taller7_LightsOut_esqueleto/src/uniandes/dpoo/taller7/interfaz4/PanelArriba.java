package uniandes.dpoo.taller7.interfaz4;

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
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelArriba extends JPanel implements ChangeListener, ItemListener
{
	private JComboBox<String> dimensiones;
	
	private JLabel tamanio;
	
	private JLabel dificultad;
	
	private JRadioButton facil;
	
	private JRadioButton medio;
	
	private JRadioButton dificil;
	
	private ButtonGroup opciones;
		
	private int casillas;
	
	private int nDificultad;
	
	public PanelArriba()
	{
		super();
		
		casillas = 3;
		nDificultad = 5;
		
		setLayout(new GridLayout(1,6));
		setVisible(true);
		
		tamanio = new JLabel("Tamaño");
		add(tamanio);
		
		dimensiones = new JComboBox<String>();
		dimensiones.addItem("3x3");
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
		
		opciones = new ButtonGroup();
		
		facil = new JRadioButton();
		facil.setText("Fácil");
		facil.setSelected(true);
		facil.addChangeListener(this);
		opciones.add(facil);
		
		medio = new JRadioButton();
		medio.setText("Medio");
		medio.addChangeListener(this);
		opciones.add(medio);
		
		dificil = new JRadioButton();
		dificil.setText("Dificil");
		dificil.addChangeListener(this);
		opciones.add(dificil);
		
		add(facil);
		add(medio);
		add(dificil);
		
		repaint();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getItem().toString().equals("4x4"))
		{
			casillas = 4;
		}
		else if (e.getItem().toString().equals("5x5"))
		{
			casillas = 5;
		}
		else if (e.getItem().toString().equals("6x6"))
		{
			casillas = 6;
		}
		else if (e.getItem().toString().equals("7x7"))
		{
			casillas = 7;
		}
		else if (e.getItem().toString().equals("8x8"))
		{
			casillas = 8;
		}
		else if (e.getItem().toString().equals("9x9"))
		{
			casillas = 9;
		}
		else if (e.getItem().toString().equals("10x10"))
		{
			casillas = 10;
		}
		else if (e.getItem().toString().equals("3x3"))
		{
			casillas = 3;
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) 
	{
		// TODO Auto-generated method stub
		if (facil.isSelected())
		{
			nDificultad = 3;
		}
		else if (medio.isSelected())
		{
			nDificultad = 6;
		}
		else if (dificil.isSelected())
		{
			nDificultad = 9;
		}
	}
	
	public int[] darInfo()
	{
		return new int[] {casillas,nDificultad};
	}
}
