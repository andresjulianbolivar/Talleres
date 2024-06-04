package uniandes.dpoo.taller7.interfaz1;

import java.awt.Color;

import javax.swing.JFrame;

public class InterfazJuego extends JFrame
{
	public InterfazJuego()
	{
		setTitle("Juego LightsOut");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,600);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(255,248,231));
		setVisible(true);
		setResizable(false);
	}
}
