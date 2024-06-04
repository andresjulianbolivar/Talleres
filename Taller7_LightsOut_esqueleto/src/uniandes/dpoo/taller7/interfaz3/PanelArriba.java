package uniandes.dpoo.taller7.interfaz3;

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
	
	private ButtonGroup opciones;
		
	private int casillas;
	
	private InterfazJuego papi;
	
	public PanelArriba(InterfazJuego papi)
	{
		super();
		
		setLayout(new GridLayout(1,6));
		setVisible(true);
		
		this.papi = papi;
		
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
		
		opciones = new ButtonGroup();
		
		facil = new JRadioButton();
		facil.setText("Fácil");
		opciones.add(facil);
		
		medio = new JRadioButton();
		medio.setText("Medio");
		opciones.add(medio);
		
		dificil = new JRadioButton();
		dificil.setText("Dificil");
		opciones.add(dificil);
		
		add(facil);
		add(medio);
		add(dificil);
		
		repaint();
		
		casillas = 5;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getItem().toString().equals("4x4"))
		{
			casillas = 4;
			papi.cambiarTablero(casillas);
		}
		else if (e.getItem().toString().equals("5x5"))
		{
			casillas = 5;
			papi.cambiarTablero(casillas);
		}
		else if (e.getItem().toString().equals("6x6"))
		{
			casillas = 6;
			papi.cambiarTablero(casillas);
		}
		else if (e.getItem().toString().equals("7x7"))
		{
			casillas = 7;
			papi.cambiarTablero(casillas);
		}
		else if (e.getItem().toString().equals("8x8"))
		{
			casillas = 8;
			papi.cambiarTablero(casillas);
		}
		else if (e.getItem().toString().equals("9x9"))
		{
			casillas = 9;
			papi.cambiarTablero(casillas);
		}
		else
		{
			casillas = 10;
			papi.cambiarTablero(casillas);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
