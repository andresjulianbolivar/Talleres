package uniandes.dpoo.taller7.interfaz4;

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
	
	private JPanel minipanel;
	
	private InterfazJuego papi;
	
	public static final String NUEVO = "nuevo";
	
	public static final String REINICIAR = "reiniciar";
	
	public static final String TOP10 = "top10";
	
	public static final String CAMBIAR_JUGADOR = "cambiar jugador";
	
	public PanelDerecha(InterfazJuego papi)
	{
		super();
		
		this.papi = papi;
		
		setLayout(new GridLayout(3,1));
		setBackground(new Color(255,248,231));
		setVisible(true);
		
		minipanel = new JPanel();
		minipanel.setLayout(new GridLayout(4,1));
		
		nuevo = new JButton("NUEVO");
		nuevo.setActionCommand(NUEVO);
		nuevo.addActionListener(this);
		minipanel.add(nuevo);
		
		reiniciar = new JButton("REINICIAR");
		reiniciar.setActionCommand(REINICIAR);
		reiniciar.addActionListener(this);
		minipanel.add(reiniciar);
		
		top10 = new JButton("TOP-10");
		top10.setActionCommand(TOP10);
		top10.addActionListener(this);
		minipanel.add(top10);
		
		cambiarJugador = new JButton("CAMBIAR JUGADOR");
		cambiarJugador.setActionCommand(CAMBIAR_JUGADOR);
		cambiarJugador.addActionListener(this);
		minipanel.add(cambiarJugador);
		
		add(new JPanel());
		add(minipanel);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(NUEVO))
		{
			papi.nuevo();
		}
		else if (e.getActionCommand().equals(REINICIAR))
		{
			papi.reiniciar();
		}
		else if (e.getActionCommand().equals(TOP10))
		{
			papi.mostrarTop10();
		}
		else if (e.getActionCommand().equals(CAMBIAR_JUGADOR))
		{
			papi.cambiarJugador();
		}
	}
}
