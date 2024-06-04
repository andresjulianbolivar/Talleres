package uniandes.dpoo.taller7.interfaz2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelDerecha extends JPanel implements ActionListener
{
	private JButton nuevo;
	
	private JButton reiniciar;
	
	private JButton top10;
	
	private JButton cambiarJugador;
	
	public static final String NUEVO = "nuevo";
	
	public static final String REINICIAR = "reiniciar";
	
	public static final String TOP10 = "top10";
	
	public static final String CAMBIAR_JUGADOR = "cambiar jugador";
	
	public PanelDerecha()
	{
		super();
		
		setLayout(new GridLayout(4,1));
		setVisible(true);
		
		nuevo = new JButton("NUEVO");
		nuevo.setActionCommand(NUEVO);
		nuevo.addActionListener(this);
		add(nuevo);
		
		reiniciar = new JButton("REINICIAR");
		reiniciar.setActionCommand(REINICIAR);
		reiniciar.addActionListener(this);
		add(reiniciar);
		
		top10 = new JButton("TOP-10");
		top10.setActionCommand(TOP10);
		top10.addActionListener(this);
		add(top10);
		
		cambiarJugador = new JButton("CAMBIAR JUGADOR");
		cambiarJugador.setActionCommand(CAMBIAR_JUGADOR);
		cambiarJugador.addActionListener(this);
		add(cambiarJugador);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
}
