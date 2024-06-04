package uniandes.dpoo.taller7.interfaz4;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelBajo extends JPanel
{
	private JLabel jugadas;
	
	private JTextField puntaje;
	
	private JLabel jugador;
	
	private JTextField nombre;
	
	public PanelBajo()
	{
		super();
		
		setLayout(new GridLayout(1,4));
		setVisible(true);
		
		jugadas = new JLabel("Jugadas:");
		add(jugadas);
		
		puntaje = new JTextField("0");
		puntaje.setEditable(false);
		add(puntaje);
		
		jugador = new JLabel("Jugador:");
		add(jugador);
		
		nombre = new JTextField();
		add(nombre);
		repaint();
	}
	
	public String darJugador()
	{
		return nombre.getText();
	}
	
	public void actualizarJugadas(int jugadas)
	{
		puntaje.setText(""+jugadas);
		repaint();
	}
	
	public void actualizarJugador(String nuevo)
	{
		nombre.setText(nuevo);
		repaint();
	}
}
