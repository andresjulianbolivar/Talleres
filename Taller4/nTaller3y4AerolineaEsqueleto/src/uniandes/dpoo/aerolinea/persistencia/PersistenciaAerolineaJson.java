package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.AeropuertoDuplicadoException;
import uniandes.dpoo.aerolinea.exceptions.ClienteRepetidoException;
import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteTiqueteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea
{
	public PersistenciaAerolineaJson()
	{
		
	}
	
	public void cargarAerolinea(String archivo, Aerolinea aerolinea) throws IOException, InformacionInconsistenteException
	{
		String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
        JSONObject raiz = new JSONObject( jsonCompleto );
        
        JSONArray jAviones = raiz.getJSONArray("aviones");
        
        int numAviones = jAviones.length();
        
        ArrayList<Avion> totalAviones = new ArrayList<Avion>();
        
        for (int i=0; i<numAviones;i++)
        {
        	JSONObject nAvion = jAviones.getJSONObject(i);
        	String nombre = nAvion.getString("nombre");
        	int capacidad = nAvion.getInt("capacidad");
        	
        	Avion nuevoAvion = new Avion(nombre,capacidad);
        	totalAviones.add(nuevoAvion);
        }
        
        JSONArray jAeropuertos = raiz.getJSONArray("aeropuertos");
        
        int numAeropuertos = jAeropuertos.length();
        
        ArrayList<Aeropuerto> totalAeropuertos = new ArrayList<Aeropuerto>();
        
        for (int i = 0; i<numAeropuertos;i++)
        {
        	try
        	{
        		JSONObject nAeropuerto = jAeropuertos.getJSONObject(i);
            	String nombre = nAeropuerto.getString("nombre");
            	String codigo = nAeropuerto.getString("codigo");
            	String nombreCiudad = nAeropuerto.getString("nombreCiudad");
            	double latitud = nAeropuerto.getDouble("latitud");
            	double longitud = nAeropuerto.getDouble("longitud");
            	
            	Aeropuerto nuevoAeropuerto = new Aeropuerto(nombre, codigo, nombreCiudad, latitud, longitud);
            	totalAeropuertos.add(nuevoAeropuerto);
        	}
        	catch (AeropuertoDuplicadoException e)
        	{
        		System.out.println(e.getMessage());
        	}
        }
        
        JSONArray jRutas = raiz.getJSONArray("rutas");
        
        int numRutas = jRutas.length();
        
        ArrayList<Ruta> totalRutas = new ArrayList<Ruta>();
        
        for (int i=0; i<numRutas;i++)
        {
        	try
        	{
        		JSONObject nRuta = jRutas.getJSONObject(i);
            	String horaSalida = nRuta.getString("horaSalida");
            	String horaLlegada = nRuta.getString("horaLlegada");
            	String codigoRuta = nRuta.getString("codigoRuta");
            	String aeropuertoDestino = nRuta.getString("aeropuertoDestino");
            	String aeropuertoOrigen = nRuta.getString("aeropuertoOrigen");
            	
            	Aeropuerto destino = null;
            	boolean encontrado = false;
            	int j = 0;
            	while (!encontrado && j<totalAeropuertos.size())
            	{
            		Aeropuerto nActual = totalAeropuertos.get(j);
            		if (nActual.getCodigo().equals(aeropuertoDestino))
            		{
            			encontrado = true;
            			destino = nActual;
            		}
            		
            		j++;
            	}
            	
            	if (!encontrado)
            	{
            		throw new InformacionInconsistenteException("La ruta " + codigoRuta + " no tiene un aeropuerto de destino.");
            	}
            	
            	Aeropuerto origen = null;
            	encontrado = false;
            	j = 0;
            	while (!encontrado && j<totalAeropuertos.size())
            	{
            		Aeropuerto nActual = totalAeropuertos.get(j);
            		if (nActual.getCodigo().equals(aeropuertoOrigen))
            		{
            			encontrado = true;
            			origen = nActual;
            		}
            		
            		j++;
            	}
            	
            	if (!encontrado)
            	{
            		throw new InformacionInconsistenteException("La ruta "+codigoRuta+" no tiene un aeropuerto de origen.");
            	}
            	
            	Ruta nuevaRuta = new Ruta(origen,destino,horaSalida,horaLlegada,codigoRuta);
            	totalRutas.add(nuevaRuta);
        	}
        	catch (InformacionInconsistenteException e)
        	{
        		System.out.println(e.getMessage());
        	}
        }
        
        JSONArray jVuelos = raiz.getJSONArray("vuelos");
        
        ArrayList<Vuelo> totalVuelos = new ArrayList<Vuelo>();
        
        int numVuelos = jVuelos.length();
        
        for (int i =0;i<numVuelos;i++)
        {
        	try
        	{
        		JSONObject nVuelo = jVuelos.getJSONObject(i);
            	String fecha = nVuelo.getString("fecha");
            	String nombreAvion = nVuelo.getString("avion");
            	String codigoRuta = nVuelo.getString("codigoRuta");
            	
            	Avion avion = null;
            	boolean encontrado = false;
            	int j = 0;
            	while(!encontrado && j<totalAviones.size())
            	{
            		Avion nActual = totalAviones.get(j);
            		if (nActual.getNombre().equals(nombreAvion))
            		{
            			encontrado = true;
            			avion = nActual;
            		}
            		j++;
            	}
            	
            	if(!encontrado)
            	{
            		throw new InformacionInconsistenteException("El vuelo programado para la fecha "+fecha+" no tiene un avion asignado.");
            	}
            	
            	Ruta ruta = null;
            	encontrado = false;
            	j=0;
            	while(!encontrado && j<totalRutas.size())
            	{
            		Ruta nActual = totalRutas.get(j);
            		if (nActual.getCodigoRuta().equals(codigoRuta))
            		{
            			encontrado = true;
            			ruta = nActual;
            		}
            		j++;
            	}
            	
            	if (!encontrado)
            	{
            		throw new InformacionInconsistenteException("El vuelo porgramado para la fecha "+fecha+" no tiene una ruta asignada.");
            	}
            	
            	Vuelo nuevoVuelo = new Vuelo(ruta,fecha,avion);
            	totalVuelos.add(nuevoVuelo);
        	}
        	catch(InformacionInconsistenteException e)
        	{
        		System.out.println(e.getMessage());
        	}
        }
        
        JSONObject nAerolinea = raiz.getJSONObject("aerolinea");
        
        JSONArray aviones = nAerolinea.getJSONArray("aviones");
        
        for (int i = 0; i<aviones.length();i++)
        {
        	try
        	{
        		String nombreAvion = (String) aviones.get(i);
            	int j = 0;
            	boolean encontrado = false;
            	while(!encontrado && j<totalAviones.size())
            	{
            		Avion nActual = totalAviones.get(j);
            		if (nActual.getNombre().equals(nombreAvion))
            		{
            			aerolinea.agregarAvion(nActual);
            			encontrado = true;
            		} 
            		j++;
            	}
            	
            	if (!encontrado)
            	{
            		throw new InformacionInconsistenteException("El avion "+nombreAvion+" no está cargado.");
            	}
        	}
        	catch(InformacionInconsistenteException e)
        	{
        		System.out.println(e.getMessage());
        	}
        }
        
        JSONArray rutas = nAerolinea.getJSONArray("rutas");
        
        for (int i = 0; i<rutas.length();i++)
        {
        	try
        	{
        		String codigoRuta = (String) rutas.get(i);
            	int j = 0;
            	boolean encontrado = false;
            	while(!encontrado && j<totalRutas.size())
            	{
            		Ruta nActual = totalRutas.get(j);
            		if(nActual.getCodigoRuta().equals(codigoRuta))
            		{
            			aerolinea.agregarRuta(nActual);
            			encontrado = true;
            		}
            		
            		j++;
            	}
            	
            	if (!encontrado)
            	{
            		throw new InformacionInconsistenteException("La ruta "+codigoRuta+" no está cargada.");
            	}
        	}
        	catch (InformacionInconsistenteException e)
        	{
        		System.out.println(e.getMessage());
        	}
        }
        
        JSONArray vuelos = nAerolinea.getJSONArray("vuelos");
        
        for(int i=0;i<vuelos.length();i++)
        {
        	try
        	{
        		JSONObject nVuelo = vuelos.getJSONObject(i);
            	String fecha = nVuelo.getString("fecha");
            	String avion = nVuelo.getString("avion");
            	String ruta = nVuelo.getString("codigoRuta");
            	
            	int j = 0;
            	boolean encontrado = false;
            	while (!encontrado && j<totalVuelos.size())
            	{
            		Vuelo nActual = totalVuelos.get(j);
            		if (nActual.getFecha().equals(fecha) && nActual.getAvion().getNombre().equals(avion) && nActual.getRuta().getCodigoRuta().equals(ruta))
            		{
            			aerolinea.programarVuelo(fecha, ruta, avion);
            			encontrado = true;
            		}
            		j++;
            	}
            	
            	if (!encontrado)
            	{
            		throw new InformacionInconsistenteException("El vuelo en la fecha "+fecha+", con la ruta "+ruta+" no está cargado.");
            	}
        	}
        	catch (Exception e)
        	{
        		System.out.println(e.getMessage());
        	}
        }
	}
	
	public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException
	{
		JSONObject jObject = new JSONObject();
		
		JSONObject jAerolinea = new JSONObject();
		
		JSONArray nombreAviones = new JSONArray();
		
		JSONArray aviones = new JSONArray();
		
		JSONArray codigoRutas = new JSONArray();
		
		JSONArray rutas = new JSONArray();
		
		JSONArray infoVuelos = new JSONArray();
		
		JSONArray vuelos = new JSONArray();
		
		JSONArray aeropuertos = new JSONArray();
		
		for (Avion avion:aerolinea.getAviones())
		{
			nombreAviones.put(avion.getNombre());
			
			JSONObject nAvion = new JSONObject();
			
			nAvion.put("nombre", avion.getNombre());
			nAvion.put("capacidad", avion.getCapacidad());
			
			aviones.put(nAvion);
		}
		
		ArrayList<Aeropuerto> agregados = new ArrayList<Aeropuerto>();
		
		for (Ruta ruta:aerolinea.getRutas())
		{
			codigoRutas.put(ruta.getCodigoRuta());
			
			JSONObject nRuta = new JSONObject();
			
			nRuta.put("horaSalida", ruta.getHoraSalida());
			nRuta.put("horaLlegada", ruta.getHoraLlegada());
			nRuta.put("codigoRuta", ruta.getCodigoRuta());
			nRuta.put("aeropuertoDestino", ruta.getDestino().getNombre());
			nRuta.put("aeropuertoOrigen", ruta.getOrigen().getNombre());
			
			if (!agregados.contains(ruta.getOrigen()))
			{
				agregados.add(ruta.getOrigen());
			}
			
			if (!agregados.contains(ruta.getDestino())) 
			{
				agregados.add(ruta.getDestino());
			}
			
			rutas.put(nRuta);
		}
		
		for (Vuelo vuelo:aerolinea.getVuelos())
		{
			JSONObject infoVuelo = new JSONObject();
			infoVuelo.put("fecha", vuelo.getFecha());
			infoVuelo.put("avion", vuelo.getAvion().getNombre());
			infoVuelo.put("codigoRuta", vuelo.getRuta().getCodigoRuta());
			
			infoVuelos.put(infoVuelo);
			vuelos.put(infoVuelo);
		}
		
		for (Aeropuerto aeropuerto:agregados)
		{
			JSONObject nAeropuerto = new JSONObject();
			nAeropuerto.put("nombre", aeropuerto.getNombre());
			nAeropuerto.put("codigo", aeropuerto.getCodigo());
			nAeropuerto.put("nombreCiudad", aeropuerto.getNombreCiudad());
			nAeropuerto.put("latitud", aeropuerto.getLatitud());
			nAeropuerto.put("longitud", aeropuerto.getLongitud());
			
			aeropuertos.put(nAeropuerto);
		}
		
		jAerolinea.put("aviones", nombreAviones);
		jAerolinea.put("rutas", codigoRutas);
		jAerolinea.put("vuelos", infoVuelos);
		
		jObject.put("aerolinea", jAerolinea);
		jObject.put("aviones", aviones);
		jObject.put("rutas", rutas);
		jObject.put("vuelos", vuelos);
		jObject.put("aeropuertos", aeropuertos);
		
		PrintWriter pw = new PrintWriter(archivo);
		jObject.write(pw, 2, 0);
		pw.close();
	}
}
