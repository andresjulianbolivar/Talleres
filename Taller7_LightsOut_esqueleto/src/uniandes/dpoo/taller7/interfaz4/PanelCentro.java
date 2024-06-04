package uniandes.dpoo.taller7.interfaz4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class PanelCentro extends JPanel implements MouseListener
{	
	private int maxX;
	
	private int maxY;
	
	private int[] lineasX;
	
	private int[] lineasY;
	
	private int casillasN;
	
	private boolean[][] tablero;
	
	private InterfazJuego papi;
	
	private BufferedImage imagen;
	
	public PanelCentro(InterfazJuego papi)
	{
		super();
		addMouseListener(this);
		this.papi = papi;
		tablero = papi.darTablero();
		setPreferredSize(new Dimension(500,500));
		getCoordenadas(tablero.length);
		
		lineasX = obtenerLineasX(tablero.length);
		
		lineasY = obtenerLineasY(tablero.length);
		
		setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		int casillasSize = darCasillasSize(tablero.length);
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.blue);
		cargarImagen();
		for (int linea:lineasX)
		{
			g2d.fillRect(linea-1,0,1,maxY);
		}
		
		for (int linea:lineasY)
		{
			g2d.fillRect(0, linea-1, maxX, 1);
		}
		
		for (int i = 0; i<tablero.length; i++)
		{
			for (int j = 0; j< tablero.length; j++)
			{
				if (!tablero[i][j])
				{
					g2d.setColor(new Color(255,248,231));
				}
				else
				{
					g2d.setColor(Color.yellow);
				}
				g2d.fillRect(i*casillasSize+i+1, j*casillasSize+j+1, casillasSize, casillasSize);
				g2d.drawImage(imagen, i*casillasSize+i+1, j*casillasSize+j+1, casillasSize, casillasSize, this);
			}
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
	
	public int darCasillasSize(int casillas)
	{
		return ((maxX -(casillas+1))/casillas);
	}

	public void actualizarTablero()
	{
		tablero = papi.darTablero();
		casillasN = tablero.length;
		getCoordenadas(casillasN);
		obtenerLineasX(casillasN);
		obtenerLineasY(casillasN);
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		
		int [] casillas = convertirCoordenadasACasillas(x,y);
		papi.jugar(casillas[0],casillas[1]);
	}
	
	public int[] convertirCoordenadasACasillas(int x, int  y)
	{
		int altoCasilla = darCasillasSize(tablero.length);
		
		return new int[] {x/altoCasilla,y/altoCasilla};
	}
	
	public void cargarImagen()
	{
		try
		{
			BufferedImage imagen = ImageIO.read(new File("./data/luz.png"));
			this.imagen = imagen;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
