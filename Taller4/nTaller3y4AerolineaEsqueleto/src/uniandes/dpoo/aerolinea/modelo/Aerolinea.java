package uniandes.dpoo.aerolinea.modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifasTemporadaAlta;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifasTemporadaBaja;
import uniandes.dpoo.aerolinea.persistencia.CentralPersistencia;
import uniandes.dpoo.aerolinea.persistencia.IPersistenciaAerolinea;
import uniandes.dpoo.aerolinea.persistencia.IPersistenciaTiquetes;
import uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

/**
 * En esta clase se organizan todos los aspectos relacionados con una Aerolínea.
 * 
 * Por un lado, esta clase cumple un rol central como estructurador para todo el resto de elementos: directa o indirectamente, todos están contenidos y se pueden acceder a
 * través de la clase Aerolínea.
 * 
 * Por otro lado, esta clase implementa algunas funcionalidades adicionales a su rol como estructurador, para lo cual se apoya en las otras clases que hacen parte del
 * proyecto.
 */
public class Aerolinea
{
    /**
     * Una lista con los aviones de los que dispone la aerolínea
     */
    private List<Avion> aviones;

    /**
     * Un mapa con las rutas que cubre la aerolínea.
     * 
     * Las llaves del mapa son el código de la ruta, mientras que los valores son las rutas
     */
    private Map<String, Ruta> rutas;

    /**
     * Una lista de los vuelos programados por la aerolínea
     */
    private List<Vuelo> vuelos;

    /**
     * Un mapa con los clientes de la aerolínea.
     * 
     * Las llaves del mapa son los identificadores de los clientes, mientras que los valores son los clientes
     */
    private Map<String, Cliente> clientes;

    /**
     * Construye una nueva aerolínea con un nombre e inicializa todas las contenedoras con estructuras vacías
     */
    public Aerolinea( )
    {
        aviones = new LinkedList<Avion>( );
        rutas = new HashMap<String, Ruta>( );
        vuelos = new LinkedList<Vuelo>( );
        clientes = new HashMap<String, Cliente>( );
    }

    // ************************************************************************************
    //
    // Estos son los métodos que están relacionados con la manipulación básica de los atributos
    // de la aerolínea (consultar, agregar)
    //
    // ************************************************************************************

    /**
     * Agrega una nueva ruta a la aerolínea
     * @param ruta
     */
    public void agregarRuta( Ruta ruta )
    {
        this.rutas.put( ruta.getCodigoRuta( ), ruta );
    }

    /**
     * Agrega un nuevo avión a la aerolínea
     * @param avion
     */
    public void agregarAvion( Avion avion )
    {
        this.aviones.add( avion );
    }

    /**
     * Agrega un nuevo cliente a la aerolínea
     * @param cliente
     */
    public void agregarCliente( Cliente cliente )
    {
        this.clientes.put( cliente.getIdentificador( ), cliente );
    }

    /**
     * Verifica si ya existe un cliente con el identificador dado
     * @param identificadorCliente
     * @return Retorna true si ya existía un cliente con el identificador, independientemente de su tipo
     */
    public boolean existeCliente( String identificadorCliente )
    {
        return this.clientes.containsKey( identificadorCliente );
    }

    /**
     * Busca el cliente con el identificador dado
     * @param identificadorCliente
     * @return Retorna el cliente con el identificador, o null si no existía
     */
    public Cliente getCliente( String identificadorCliente )
    {
        return this.clientes.get( identificadorCliente );
    }

    /**
     * Retorna todos los aviones de la aerolínea
     * @return
     */
    public Collection<Avion> getAviones( )
    {
        return aviones;
    }

    /**
     * Retorna todas las rutas disponibles para la aerolínea
     * @return
     */
    public Collection<Ruta> getRutas( )
    {
        return rutas.values( );
    }

    /**
     * Retorna la ruta de la aerolínea que tiene el código dado
     * @param codigoRuta El código de la ruta buscada
     * @return La ruta con el código, o null si no existe una ruta con ese código
     */
    public Ruta getRuta( String codigoRuta )
    {
        return rutas.get( codigoRuta );
    }

    /**
     * Retorna todos los vuelos de la aerolínea
     * @return
     */
    public Collection<Vuelo> getVuelos( )
    {
        return vuelos;
    }

    /**
     * Busca un vuelo dado el código de la ruta y la fecha del vuelo.
     * @param codigoRuta
     * @param fechaVuelo
     * @return Retorna el vuelo que coincide con los parámetros dados. Si no lo encuentra, retorna null.
     */
    public Vuelo getVuelo( String codigoRuta, String fechaVuelo )
    {
        // TODO implementar
        Vuelo respuesta = null;
        
        boolean encontro = false;
        
        int i = 0;
        
        while (!encontro && i<vuelos.size())
        {
        	Vuelo actual = vuelos.get(i);
        	
        	if (actual.getFecha().equals(fechaVuelo) && actual.getRuta().getCodigoRuta().equals(codigoRuta))
        	{
        		respuesta = actual;
        	}
        	
        	i++;
        }
        
        return respuesta;
    }

    /**
     * Retorna todos los clientes de la aerolínea
     * @return
     */
    public Collection<Cliente> getClientes( )
    {
        return clientes.values( );
    }

    /**
     * Retorna todos los tiquetes de la aerolínea, los cuales se recolectan vuelo por vuelo
     * @return
     */
    public Collection<Tiquete> getTiquetes( )
    {
        // TODO implementar
        Collection<Tiquete> respuesta = new ArrayList<Tiquete>();
        
        for (int i = 0; i< vuelos.size(); i++)
        {
        	for (Iterator<Tiquete> iterator = vuelos.get(i).getTiquetes().iterator();iterator.hasNext();)
        	{
        		Tiquete actual = iterator.next();
        		respuesta.add(actual);
        	}
        }
        
        return respuesta;
    }

    // ************************************************************************************
    //
    // Estos son los métodos que están relacionados con la persistencia de la aerolínea
    //
    // ************************************************************************************

    /**
     * Carga toda la información de la aerolínea a partir de un archivo
     * @param archivo El nombre del archivo.
     * @param tipoArchivo El tipo del archivo. Puede ser CentralPersistencia.JSON o CentralPersistencia.PLAIN.
     * @throws TipoInvalidoException Se lanza esta excepción si se indica un tipo de archivo inválido
     * @throws IOException Lanza esta excepción si hay problemas leyendo el archivo
     * @throws InformacionInconsistenteException Lanza esta excepción si durante la carga del archivo se encuentra información que no es consistente
     */
    public void cargarAerolinea( String archivo, String tipoArchivo ) throws TipoInvalidoException, IOException, InformacionInconsistenteException, Exception
    {
        // TODO implementar
    	IPersistenciaAerolinea cargador = CentralPersistencia.getPersistenciaAerolinea(tipoArchivo);
    	cargador.cargarAerolinea(archivo, this);
    }

    /**
     * Salva la información de la aerlínea en un archivo
     * @param archivo El nombre del archivo.
     * @param tipoArchivo El tipo del archivo. Puede ser CentralPersistencia.JSON o CentralPersistencia.PLAIN.
     * @throws TipoInvalidoException Se lanza esta excepción si se indica un tipo de archivo inválido
     * @throws IOException Lanza esta excepción si hay problemas escribiendo en el archivo
     */
    public void salvarAerolinea( String archivo, String tipoArchivo ) throws TipoInvalidoException, IOException
    {
        // TODO implementar
    	IPersistenciaAerolinea cargador = CentralPersistencia.getPersistenciaAerolinea(tipoArchivo);
    	cargador.salvarAerolinea(archivo, this);
    }

    /**
     * Carga toda la información de sobre los clientes y tiquetes de una aerolínea a partir de un archivo
     * @param archivo El nombre del archivo.
     * @param tipoArchivo El tipo del archivo. Puede ser CentralPersistencia.JSON o CentralPersistencia.PLAIN.
     * @throws TipoInvalidoException Se lanza esta excepción si se indica un tipo de archivo inválido
     * @throws IOException Lanza esta excepción si hay problemas leyendo el archivo
     * @throws InformacionInconsistenteException Lanza esta excepción si durante la carga del archivo se encuentra información que no es consistente con la información de la
     *         aerolínea
     */
    public void cargarTiquetes( String archivo, String tipoArchivo ) throws TipoInvalidoException, IOException, InformacionInconsistenteException
    {
        IPersistenciaTiquetes cargador = CentralPersistencia.getPersistenciaTiquetes( tipoArchivo );
        cargador.cargarTiquetes( archivo, this );
    }

    /**
     * Salva la información de la aerlínea en un archivo
     * @param archivo El nombre del archivo.
     * @param tipoArchivo El tipo del archivo. Puede ser CentralPersistencia.JSON o CentralPersistencia.PLAIN.
     * @throws TipoInvalidoException Se lanza esta excepción si se indica un tipo de archivo inválido
     * @throws IOException Lanza esta excepción si hay problemas escribiendo en el archivo
     */
    public void salvarTiquetes( String archivo, String tipoArchivo ) throws TipoInvalidoException, IOException
    {
        IPersistenciaTiquetes cargador = CentralPersistencia.getPersistenciaTiquetes( tipoArchivo );
        cargador.salvarTiquetes( archivo, this );
    }

    // ************************************************************************************
    //
    // Estos son los métodos que están relacionados con funcionalidades interesantes de la aerolínea
    //
    // ************************************************************************************

    /**
     * Agrega un nuevo vuelo a la aerolínea, para que se realice en una cierta fecha, en una cierta ruta y con un cierto avión.
     * 
     * Este método debe verificar que el avión seleccionado no esté ya ocupado para otro vuelo en el mismo intervalo de tiempo del nuevo vuelo. No es necesario verificar que
     * se encuentre en el lugar correcto (origen del vuelo).
     * 
     * @param fecha La fecha en la que se realizará el vuelo
     * @param codigoRuta La ruta que cubirá el vuelo
     * @param nombreAvion El nombre del avión que realizará el vuelo
     * @throws Exception Lanza esta excepción si hay algún problema con los datos suministrados
     */
    public void programarVuelo( String fecha, String codigoRuta, String nombreAvion ) throws Exception
    {
        // TODO Implementar el método
    	
    	Ruta ruta = getRuta(codigoRuta);
    	
    	Avion avion = null;
    	
    	int  i = 0;
    	boolean encontrado = false;
    	while (!encontrado && i<aviones.size())
    	{
    		Avion nAvion = aviones.get(i);
    		if (nAvion.getNombre().equals(nombreAvion))
    		{
    			avion = nAvion;
    			encontrado = true;
    		}
    		i ++;
    	}
    	
    	if (ruta.equals(null) || avion.equals(null))
    	{
    		throw new Exception("La aerolinea no cuenta con la ruta o con el avion para realizar el vuelo.");
    	}
    	
    	ArrayList<Vuelo> comunes = new ArrayList<Vuelo>();
    	for (Vuelo vuelo:vuelos)
    	{
    		if (vuelo.getAvion().equals(avion))
    		{
    			comunes.add(vuelo);
    		}
    	}
    	
    	encontrado = false;
    	ArrayList<String>fechas = fechas(fecha);
    	int j = 0;
    	while (!encontrado && j<comunes.size())
    	{
    		if (fechas.get(0).equals(comunes.get(j).getFecha()))
    		{
    			if (comunes.get(j).getRuta().getHoraLlegada().compareTo(comunes.get(j).getRuta().getHoraSalida())<0)
    			{
    				if(comunes.get(j).getRuta().getHoraLlegada().compareTo(ruta.getHoraSalida())>0)
    				{
    					encontrado = true;
    				}
    			}
    		}
    		else if (fechas.get(1).equals(comunes.get(j).getFecha()))
    		{
    			if(ruta.getHoraLlegada().compareTo(ruta.getHoraSalida())<0)
    			{
    				if (ruta.getHoraLlegada().compareTo(comunes.get(j).getRuta().getHoraSalida())>0)
    				{
    					encontrado = true;
    				}
    			}
    		}
    		else if (comunes.get(j).getFecha().equals(fecha))
    		{
    			if ((ruta.getHoraSalida().compareTo(comunes.get(j).getRuta().getHoraSalida())>0 && ruta.getHoraSalida().compareTo(comunes.get(j).getRuta().getHoraLlegada())<0)||(ruta.getHoraLlegada().compareTo(comunes.get(j).getRuta().getHoraSalida())>0 && ruta.getHoraLlegada().compareTo(comunes.get(j).getRuta().getHoraLlegada())<0))
    			{
    				encontrado = true;
    			}
    		}
    	}
    	
    	if (encontrado)
    	{
    		throw new Exception("El vuelo no se puede programar porque el avion no está disponible en dicha franja horaria.");
    	}
    	else
    	{
    		Vuelo nuevo = new Vuelo(ruta,fecha,avion);
    		vuelos.add(nuevo);
    	}
    	
    }

    private ArrayList<String> fechas(String fecha)
    {
    	String anio = fecha.substring(0,4);
    	String mes = fecha.substring(5,7);
    	String dia = fecha.substring(8);
    	
    	String fechaP;
    	String fechaA;
    	
    	int nDiaP = Integer.parseInt(dia)+1;
    	int nDiaA = Integer.parseInt(dia)-1;
    	if (nDiaP > 30)
    	{
    		int nMesP = Integer.parseInt(mes)+1;
    		if (nMesP>12)
    		{
    			int nAnioP = Integer.parseInt(anio)+1;
    			fechaP = nAnioP+"01"+"01";
    		}
    		else
    		{
    			fechaP = anio+nMesP+"01";
    		}
    	}
    	else
    	{
    		fechaP = anio + mes + nDiaP;
    	}
    	
    	if (nDiaA < 0)
    	{
    		int nMesA = Integer.parseInt(mes) -1;
    		if (nMesA < 0)
    		{
    			int nAnioA = Integer.parseInt(anio)-1;
    			fechaA = nAnioA+"30"+"12";
    		}
    		else
    		{
    			fechaA = anio+nMesA+"30";
    		}
    	}
    	else
    	{
    		fechaA = anio+mes+nDiaA;
    	}
    	
    	ArrayList<String> r = new ArrayList<String>();
    	
    	r.add(fechaA);
    	r.add(fechaP);
    	
    	return r;
    	
    }
    /**
     * Vende una cierta cantidad de tiquetes para un vuelo, verificando que la información sea correcta.
     * 
     * Los tiquetes deben quedar asociados al vuelo y al cliente.
     * 
     * Según la fecha del vuelo, se deben usar las tarifas de temporada baja (enero a mayo y septiembre a noviembre) o las de temporada alta (el resto del año).
     * 
     * @param identificadorCliente El identificador del cliente al cual se le venden los tiquetes
     * @param fecha La fecha en la que se realiza el vuelo para el que se van a vender los tiquetes
     * @param codigoRuta El código de la ruta para el que se van a vender los tiquetes
     * @param cantidad La cantidad de tiquetes que se quieren comprar
     * @return El valor total de los tiquetes vendidos
     * @throws VueloSobrevendidoException Se lanza esta excepción si no hay suficiente espacio en el vuelo para todos los pasajeros
     * @throws Exception Se lanza esta excepción para indicar que no se pudieron vender los tiquetes por algún otro motivo
     */
    public int venderTiquetes( String identificadorCliente, String fecha, String codigoRuta, int cantidad ) throws VueloSobrevendidoException, Exception
    {
        // TODO Implementar el método
        Vuelo vuelo = getVuelo(codigoRuta, fecha);
        
        if (vuelos != null)
        {
        	Cliente cliente = clientes.get(identificadorCliente);
        	
        	ArrayList<String> tempBaja = new ArrayList<String>();
        	tempBaja.add("01");
        	tempBaja.add("02");
        	tempBaja.add("03");
        	tempBaja.add("04");
        	tempBaja.add("05");
        	tempBaja.add("09");
        	tempBaja.add("10");
        	tempBaja.add("11");
        	
        	ArrayList<String> tempAlta = new ArrayList<String>();
        	tempAlta.add("06");
        	tempAlta.add("07");
        	tempAlta.add("08");
        	tempAlta.add("12");
        	
        	String mes = fecha.substring(5, 6);
        	
        	CalculadoraTarifas calculadora;
        	
        	if (tempAlta.contains(mes))
        	{
        		calculadora = new CalculadoraTarifasTemporadaAlta();
        	}
        	else
        	{
        		calculadora = new CalculadoraTarifasTemporadaBaja();
        	}

        	return vuelo.venderTiquetes(cliente, calculadora, cantidad);
        }
        else
        {
        	throw new Exception("No hay un vuelo disponible para las necesidades del cliente");
        }
    }

    /**
     * Registra que un cierto vuelo fue realizado
     * @param fecha La fecha del vuelo
     * @param codigoRuta El código de la ruta que recorrió el vuelo
     */
    public void registrarVueloRealizado( String fecha, String codigoRuta )
    {
        // TODO Implementar el método
    	Vuelo vuelo = getVuelo(codigoRuta, fecha);
    	
    	Collection<Tiquete> Tiquetes = vuelo.getTiquetes();
    	
    	ArrayList<Cliente> nClientes = new ArrayList<Cliente>();
    	
    	for (Iterator<Tiquete> iterator = Tiquetes.iterator();iterator.hasNext();)
    	{
    		Tiquete actual = iterator.next();
    		Cliente nCliente = actual.getCliente();
    		if (!nClientes.contains(nCliente))
    		{
    			nClientes.add(nCliente);
    		}
    		actual.marcarComoUsado();
    	}
    	
    	for (Cliente nCliente:nClientes)
    	{
    		nCliente.usarTiquetes(vuelo);
    	}
    	
    	vuelos.remove(vuelo);
    }

    /**
     * Calcula cuánto valen los tiquetes que ya compró un cliente dado y que todavía no ha utilizado
     * @param identificadorCliente El identificador del cliente
     * @return La suma de lo que pagó el cliente por los tiquetes sin usar
     */
    public String consultarSaldoPendienteCliente( String identificadorCliente )
    {
        // TODO Implementar el método
        Cliente cliente = clientes.get(identificadorCliente);
        
        int suma = 0;
        
        for (int i=0; i<vuelos.size();i++)
        {
        	Vuelo actual = vuelos.get(i);
        	
        	for(Iterator<Tiquete> iterator = actual.getTiquetes().iterator();iterator.hasNext();)
        	{
        		Tiquete tiquete = iterator.next();
        		if (tiquete.getCliente().equals(cliente) && !tiquete.esUsado())
        		{
        			suma += tiquete.getTarifa();
        		}
        	}
        }
        
        return ("$"+suma);
    }
    

}