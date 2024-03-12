package uniandes.dpoo.aerolinea.modelo;

import java.util.ArrayList;
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
		this.ruta = ruta;
		this.fecha = fecha;
		this.avion = avion;
	}
	
	public Ruta getRuta()
	{
		return ruta;
	}
	
	public String getFecha() 
	{
		return fecha;
	}
	
	public Avion getAvion()
	{
		return avion;
	}
	
	public Collection<Tiquete> getTiquetes()
	{
		Collection<Tiquete> respuesta = new ArrayList<Tiquete>();
		
		for(Map.Entry<String, Tiquete> pareja: tiquetes.entrySet())
		{
			Tiquete actual = pareja.getValue();
			respuesta.add(actual);
		}
		
		return respuesta;
	}
	
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) throws VueloSobrevendidoException
	{
		if (tiquetes.size()+cantidad <= avion.getCapacidad())
		{
			int costo = 0;
			
			int tarifa = calculadora.calcularTarifa(this, cliente);
			
			for (int i=1;i<=cantidad;i++)
	    	{
	    		Tiquete nuevo = GeneradorTiquetes.generarTiquete(this, cliente, tarifa );
	    		GeneradorTiquetes.registrarTiquete(nuevo);
	    		cliente.agregarTiquete(nuevo);
	    		this.tiquetes.put(nuevo.getCodigo(), nuevo);
	    	}
			
			costo = cantidad * tarifa;
			
			return costo;
		}
		else
		{
			throw new VueloSobrevendidoException(this);
		}
	}
	
	public boolean equals(Object obj)
	{
		try
		{
			String fechaO = ((Vuelo) obj).getFecha();
			Ruta rutaO = ((Vuelo) obj).getRuta();
			
			if (!fecha.equals(fechaO)) 
			{
				return false;
			}
			else if (!ruta.equals(rutaO))
			{
				return false;
			}
			else
			{
				return true;
			}
			
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
