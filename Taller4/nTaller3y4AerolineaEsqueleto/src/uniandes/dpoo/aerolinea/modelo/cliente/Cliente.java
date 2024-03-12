package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente 
{
	private List<Tiquete> tiquetesSinUsar;
	
	private List<Tiquete> tiquetesUsados;
	
	public Cliente()
	{
		tiquetesSinUsar = new ArrayList<Tiquete>();
		tiquetesUsados = new ArrayList<Tiquete>();
	}
	
	public abstract String getTipoCliente();
	
	public abstract String getIdentificador();
	
	public void agregarTiquete(Tiquete tiquete)
	{
		tiquetesSinUsar.add(tiquete);
	}
	
	public int calcularValorTotalTiquetes()
	{
		int suma = 0;
		
		for (int i=0;i<tiquetesSinUsar.size();i++)
		{
			Tiquete actual = tiquetesSinUsar.get(i);
			suma += actual.getTarifa();
		}
		
		for (int j = 0; j<tiquetesUsados.size();j++)
		{
			Tiquete actual = tiquetesUsados.get(j);
			suma += actual.getTarifa();
		}
		
		return suma;
	}
	
	public void usarTiquetes(Vuelo vuelo)
	{
		for (int i=0; i<tiquetesSinUsar.size();i++)
		{
			Tiquete actual = tiquetesSinUsar.get(i);
			
			if (actual.getVuelo().equals(vuelo))
			{
				Tiquete cambio = tiquetesSinUsar.remove(i);
				cambio.marcarComoUsado();
				tiquetesUsados.add(cambio);
			}
		}
	}
}
