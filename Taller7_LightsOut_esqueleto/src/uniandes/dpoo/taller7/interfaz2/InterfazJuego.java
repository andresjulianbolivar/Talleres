package uniandes.dpoo.taller7.interfaz2;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class InterfazJuego extends JFrame
{
	private PanelArriba superior;
	
	private PanelCentro centro;
	
	private PanelDerecha derecha;
	
	private PanelBajo inferior;
	
	public InterfazJuego()
	{
		setTitle("Juego LightsOut");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900,600);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
		superior = new PanelArriba();
		
		derecha = new PanelDerecha();
		
		inferior = new PanelBajo();
		
		centro = new PanelCentro();
		
		setLayout(new BorderLayout());
		add(superior,BorderLayout.NORTH);
		add(inferior, BorderLayout.SOUTH);
		add(centro, BorderLayout.CENTER);
		add(derecha, BorderLayout.EAST);
		repaint();
	}
}
