
package uniandes.dpoo.taller7.interfaz4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.dpoo.taller7.modelo.Tablero;
import uniandes.dpoo.taller7.modelo.Top10;

public class InterfazJuego extends JFrame
{
	private PanelArriba superior;
	
	private PanelCentro centro;
	
	private PanelDerecha derecha;
	
	private PanelBajo inferior;
	
	private Tablero tablero;
	
	private Top10 top10;
	
	private PanelTop10 mostrarTop10;
	
	public InterfazJuego()
	{
		tablero = new Tablero(3);
		top10 = new Top10();
		top10.cargarRecords(new File("./data/top10.csv"));
		setTitle("Juego LightsOut");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(670,583);
		getContentPane().setBackground(new Color(255,248,231));
		setLocationRelativeTo(null);
		setResizable(false);
		
		superior = new PanelArriba();
		
		derecha = new PanelDerecha(this);
		
		inferior = new PanelBajo();
		
		centro = new PanelCentro(this);
		
		mostrarTop10 = new PanelTop10(this);
		mostrarTop10.actualizarTop10(top10);
		
		setLayout(new BorderLayout());
		add(superior,BorderLayout.NORTH);
		add(inferior, BorderLayout.SOUTH);
		add(centro, BorderLayout.CENTER);
		add(derecha, BorderLayout.EAST);
		setVisible(true);
		tablero = null;
	}
	
	public static void main(String[] args) 
	{
		InterfazJuego x = new InterfazJuego();
	}
	
	public void nuevo()
	{
		int[] r = superior.darInfo();
		tablero = new Tablero(r[0]);
		tablero.desordenar(r[1]);
		centro.actualizarTablero();
		inferior.actualizarJugadas(tablero.darJugadas());
	}
	
	public boolean [][] darTablero()
	{
		return tablero.darTablero();
	}
	
	public void reiniciar()
	{
		tablero.reiniciar();
		centro.actualizarTablero();
		inferior.actualizarJugadas(tablero.darJugadas());
	}
	
	public void cambiarJugador()
	{
		String nuevoJugador = JOptionPane.showInputDialog(this, "Ingrese el nuevo jugador:", inferior.darJugador());
		if (nuevoJugador != null)
		{
			inferior.actualizarJugador(nuevoJugador);
		}
	}
	
	public void mostrarTop10()
	{
		mostrarTop10.actualizarTop10(top10);
		mostrarTop10.setVisible(true);
	}
	
	public void guardar()
	{
		try
		{
			top10.salvarRecords(new File("./data/top10.csv"));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void jugar(int fila, int columna)
	{
		if (inferior.darJugador().equals(""))
		{
			JOptionPane.showMessageDialog(this, "Se debe indicar el jugador primero.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (tablero == null)
		{
			JOptionPane.showMessageDialog(this, "Se debe iniciar un nuevo juego primero.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			tablero.jugar(fila, columna);
			centro.actualizarTablero();
			inferior.actualizarJugadas(tablero.darJugadas());
			if (tablero.tableroIluminado())
			{
				JOptionPane.showMessageDialog(this, "Ganaste con "+tablero.calcularPuntaje(),
						"Ganaste!", JOptionPane.INFORMATION_MESSAGE);
				top10.agregarRegistro(inferior.darJugador(), tablero.calcularPuntaje());
				guardar();
			}
		}
	}
}
