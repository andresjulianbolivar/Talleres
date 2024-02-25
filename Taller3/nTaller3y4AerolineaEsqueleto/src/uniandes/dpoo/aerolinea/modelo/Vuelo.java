package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.*;

public class Vuelo 
{
	private String fecha;
	
	private Avion avion;
	
	private Ruta ruta;
	
	private Map<String,Tiquete> tiquetes;
	
	public Vuelo(Ruta ruta, String fecha, Avion avion)
	{

	}
	
	public Ruta getRuta()
	{
		return null;
	}
	
	public String getFecha() 
	{
		return null;
	}
	
	public Avion getAvion()
	{
		return null;
	}
	
	public Collection<Tiquete> getTiquetes()
	{
		return null;
	}
	
	public int veenderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) throws VueloSobrevendidoException
	{
		return -1;
	}
	
	public boolean equals(Object obj)
	{
		return false;
	}
}
