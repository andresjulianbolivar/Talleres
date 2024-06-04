package uniandes.dpoo.taller7.interfaz3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class PanelCentro extends JPanel
{	
	private int maxX;
	
	private int maxY;
	
	private int[] lineasX;
	
	private int[] lineasY;
	
	private int casillasN;
	
	public PanelCentro(int casillas)
	{
		setSize(400,400);
		setBorder(new TitledBorder("Tablero"));
		getCoordenadas(casillas);
		
		lineasX = obtenerLineasX(casillas);
		
		lineasY = obtenerLineasY(casillas);
		
		repaint();
	}
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.yellow);
		g2d.fillRect(0, 0, maxX, maxY);
		g2d.setColor(Color.black);
		
		for (int linea:lineasX)
		{
			g2d.fillRect(linea-1,0,1,maxY);
		}
		
		for (int linea:lineasY)
		{
			g2d.fillRect(0, linea-1, maxX, 1);
		}
		repaint();
	}
	
	public int[] getCoordenadas(int casillas)
	{
		int alto = 500;
		int ancho = 500;
		
		int altoY = alto - casillas -1;
		int anchoX = ancho - casillas -1;
		
		int altoCasilla = altoY / casillas;
		int anchoCasilla = anchoX / casillas;
		
		maxX = anchoCasilla*casillas+casillas+1;
		maxY = altoCasilla*casillas+casillas+1;
		
		return new int[]{maxX,maxY};
	}
	
	public int[] obtenerLineasX(int casillas)
	{
		int [] r = new int[casillas+1];
		int anchoCasilla = (maxX - (casillas+1))/casillas;
		
		for (int i = 1; i< casillas; i++)
		{
			r[i] = i*anchoCasilla + i +1;
		}
		
		r[0] = 1;
		r[casillas] = maxX;
		
		lineasX = r;
		
		return r;
	}
	
	public int[] obtenerLineasY(int casillas)
	{
		int [] r = new int[casillas+1];
		int altoCasilla = (maxY - (casillas+1))/casillas;
		
		for (int i = 1; i< casillas; i++)
		{
			r[i] = i*altoCasilla + i +1;
		}
		
		r[0] = 1;
		r[casillas] = maxY;
		
		lineasY = r;
		
		return r;
	}
	
	public void setCasillasN(int nCasillas)
	{
		casillasN = nCasillas;
		getCoordenadas(casillasN);
		obtenerLineasX(casillasN);
		obtenerLineasY(casillasN);
		repaint();
	}
}
