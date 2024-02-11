package uniandes.dpoo.estructuras.logica;

import java.util.HashMap;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre arreglos de enteros y de cadenas.
 *
 * Todos los métodos deben operar sobre los atributos arregloEnteros y arregloCadenas.
 * 
 * No pueden agregarse nuevos atributos.
 * 
 * Implemente los métodos usando operaciones sobre arreglos (ie., no haga cosas como construir listas para evitar la manipulación de arreglos).
 */
public class SandboxArreglos
{
    /**
     * Un arreglo de enteros para realizar varias de las siguientes operaciones.
     * 
     * Ninguna posición del arreglo puede estar vacía en ningún momento.
     */
    private int[] arregloEnteros;

    /**
     * Un arreglo de cadenas para realizar varias de las siguientes operaciones
     * 
     * Ninguna posición del arreglo puede estar vacía en ningún momento.
     */
    private String[] arregloCadenas;

    /**
     * Crea una nueva instancia de la clase con los dos arreglos inicializados pero vacíos (tamaño 0)
     */
    public SandboxArreglos( )
    {
        arregloEnteros = new int[]{};
        arregloCadenas = new String[]{};
    }

    /**
     * Retorna una copia del arreglo de enteros, es decir un nuevo arreglo del mismo tamaño que contiene copias de los valores del arreglo original
     * @return Una copia del arreglo de enteros
     */
    public int[] getCopiaEnteros( )
    {
        int[] copia = new int[arregloEnteros.length];
        
        for (int i=0; i<arregloEnteros.length; i++)
        {
        	copia[i] = arregloEnteros[i];
        }
    	return copia;
    }

    /**
     * Retorna una copia del arreglo de cadenas, es decir un nuevo arreglo del mismo tamaño que contiene copias de los valores del arreglo original
     * @return Una copia del arreglo de cadenas
     */
    public String[] getCopiaCadenas( )
    {
    	String[] copia = new String[arregloCadenas.length];
        
        for (int i=0; i<arregloCadenas.length; i++)
        {
        	copia[i] = arregloCadenas[i];
        }
    	return copia;
    }

    /**
     * Retorna la cantidad de valores en el arreglo de enteros
     * @return
     */
    public int getCantidadEnteros( )
    {
        return arregloEnteros.length;
    }

    /**
     * Retorna la cantidad de valores en el arreglo de cadenas
     * @return
     */
    public int getCantidadCadenas( )
    {
        return arregloCadenas.length;
    }

    /**
     * Agrega un nuevo valor al final del arreglo. Es decir que este método siempre debería aumentar en 1 la capacidad del arreglo.
     * 
     * @param entero El valor que se va a agregar.
     */
    public void agregarEntero( int entero )
    {
    	int actual = getCantidadEnteros();
    	int[] copia = getCopiaEnteros();
    	int[] nuevo = new int[actual+1];
    	arregloEnteros = nuevo;
    	arregloEnteros[actual] = entero;
    	for (int i=0; i<actual; i++)
    	{
    		arregloEnteros[i] = copia[i];
    	}
    }

    /**
     * Agrega un nuevo valor al final del arreglo. Es decir que este método siempre debería aumentar en 1 la capacidad del arreglo.
     * 
     * @param cadena La cadena que se va a agregar.
     */
    public void agregarCadena( String cadena )
    {
    	int actual = getCantidadCadenas();
    	String[] copia = getCopiaCadenas();
    	String[] nuevo = new String[actual+1];
    	arregloCadenas = nuevo;
    	arregloCadenas[actual] = cadena;
    	for (int i=0; i<actual; i++)
    	{
    		arregloCadenas[i] = copia[i];
    	}
    }

    /**
     * Elimina todas las apariciones de un determinado valor dentro del arreglo de enteros
     * @param valor El valor que se va eliminar
     */
    public void eliminarEntero( int valor )
    {
    	int[] copia = getCopiaEnteros();
    	
    	int actual = getCantidadEnteros();
    	
    	int cont = 0;
    	for (int i=0; i<actual; i++)
    	{
    		if (copia[i] == valor)
    		{
    			cont++;
    		}
    	}
    	
    	int[] nuevo = new int[actual-cont];
    	arregloEnteros = nuevo;
    	
    	int agregados = 0;
    	
    	for(int i=0; i<actual; i++)
    	{
    		if (copia[i] != valor)
    		{
    			arregloEnteros[agregados] = copia[i];
    			agregados ++;
    		}
    	}
    }

    /**
     * Elimina todas las apariciones de un determinado valor dentro del arreglo de cadenas
     * @param cadena La cadena que se va eliminar
     */
    public void eliminarCadena( String cadena )
    {
    	String[] copia = getCopiaCadenas();
    	
    	int actual = getCantidadCadenas();
    	
    	int cont = 0;
    	for (int i=0; i<actual; i++)
    	{
    		if (cadena.equals(copia[i]))
    		{
    			cont++;
    		}
    	}
    	
    	String[] nuevo = new String[actual-cont];
    	arregloCadenas = nuevo;
    	
    	int agregados = 0;
    	
    	for(int i=0; i<actual; i++)
    	{
    		if (!(cadena.equals(copia[i])))
    		{
    			arregloCadenas[agregados] = copia[i];
    			agregados ++;
    		}
    	}
    }

    /**
     * Inserta un nuevo entero en el arreglo de enteros.
     * 
     * @param entero El nuevo valor que debe agregarse
     * @param posicion La posición donde debe quedar el nuevo valor en el arreglo aumentado. Si la posición es menor a 0, se inserta el valor en la primera posición. Si la
     *        posición es mayor que el tamaño del arreglo, se inserta el valor en la última posición.
     */
    public void insertarEntero( int entero, int posicion )
    {
    	int actual = getCantidadEnteros();
    	
    	int[] copia = getCopiaEnteros();
    	
    	agregarEntero(entero);
    	
    	
    	if (posicion < 0)
    	{
    		for(int i=0; i < actual; i++)
    		{
    			arregloEnteros[i+1] = copia[i];
    		}
    		
    		arregloEnteros[0] = entero;
    	}
    	else if (posicion < actual)
    	{
    		arregloEnteros[posicion] = entero;
    		
    		for (int i=posicion; i<actual; i++)
    		{
    			arregloEnteros[i+1] = copia[i];
    		}
    	}
    }

    /**
     * Elimina un valor del arreglo de enteros dada su posición.
     * @param posicion La posición donde está el elemento que debe ser eliminado. Si el parámetro posicion no corresponde a ninguna posición del arreglo de enteros, el método
     *        no debe hacer nada.
     */
    public void eliminarEnteroPorPosicion( int posicion )
    {
    	int actual = getCantidadEnteros();
    	
    	int[] copia = getCopiaEnteros();
    	
    	if (posicion == 0)
    	{
    		int[] nuevo = new int[actual-1];
    		
    		for (int i = 0; i<actual-1; i++)
    		{
    			nuevo[i] = copia[i+1];
    		}
    		
    		arregloEnteros = nuevo;
    	}
    	else if(posicion == actual-1)
    	{
    		int[] nuevo = new int[actual-1];
    		
    		for (int i = 0; i<actual-1; i++)
    		{
    			nuevo[i] = copia[i];
    		}
    		
    		arregloEnteros = nuevo;
    	}
    	else if(posicion > 0 && posicion < actual-1)
    	{
    		int[] nuevo = new int[actual-1];
    		
    		for(int i=0; i<posicion; i++)
    		{
    			nuevo[i] = copia[i];
    		}
    		
    		for (int i=posicion; i<actual-1;i++)
    		{
    			nuevo[i] = copia[i+1];
    		}
    		
    		arregloEnteros = nuevo;
    	}
    	
    }

    /**
     * Reinicia el arreglo de enteros con los valores contenidos en el arreglo del parámetro 'valores' truncados.
     * 
     * Es decir que si el valor fuera 3.67, en el nuevo arreglo de enteros debería quedar el entero 3.
     * @param valores Un arreglo de valores decimales.
     */
    public void reiniciarArregloEnteros( double[] valores )
    {
    	int[] nuevo = new int[valores.length];
    	
    	for (int i=0; i<valores.length; i++)
    	{
    		nuevo[i] = (int) valores[i];
    	}
    	arregloEnteros = nuevo;
    }

    /**
     * Reinicia el arreglo de cadenas con las representaciones como Strings de los objetos contenidos en el arreglo del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Un arreglo de objetos
     */
    public void reiniciarArregloCadenas( Object[] objetos )
    {
    	String[] nuevo = new String[objetos.length];
    	
    	for (int i=0; i<objetos.length; i++)
    	{
    		nuevo[i] = objetos[i].toString();
    	}
    	arregloCadenas = nuevo;
    }

    /**
     * Modifica el arreglo de enteros para que todos los valores sean positivos.
     * 
     * Es decir que si en una posición había un valor negativo, después de ejecutar el método debe quedar el mismo valor muliplicado por -1.
     */
    public void volverPositivos( )
    {
    	int actual = getCantidadEnteros();
    	
    	for (int i=0; i<actual; i++)
    	{
    		if(arregloEnteros[i] < 0)
    		{
    			arregloEnteros[i] = arregloEnteros[i] * -1;
    		}
    	}
    }

    /**
     * Modifica el arreglo de enteros para que todos los valores queden organizados de menor a mayor.
     */
    public void organizarEnteros( )
    {
    	int actual = getCantidadEnteros();
    	
    	int[] arreglo = getCopiaEnteros();
        
        for (int i=0; i<actual -1; i++)
        {
            for(int j=1; j < actual - i; j++)
            {
                if (arreglo[j] < arreglo[j-1])
                {
                    int temp = arreglo[j];
                    arreglo[j] = arreglo[j-1];
                    arreglo[j-1] = temp;
                }
            }
        }
        
        arregloEnteros = arreglo;
    }

    /**
     * Modifica el arreglo de cadenas para que todos los valores queden organizados lexicográficamente.
     */
    public void organizarCadenas( )
    {
    	int actual = getCantidadCadenas();
    	
    	String[] arreglo = getCopiaCadenas();
        
        for (int i=0; i<actual -1; i++)
        {
            for(int j=1; j < actual - i; j++)
            {
                if (arreglo[j-1].compareTo(arreglo[j]) > 0)
                {
                    String temp = arreglo[j];
                    arreglo[j] = arreglo[j-1];
                    arreglo[j-1] = temp;
                }
            }
        }
        
        arregloCadenas = arreglo;
    }

    /**
     * Cuenta cuántas veces aparece el valor recibido por parámetro en el arreglo de enteros
     * @param valor El valor buscado
     * @return La cantidad de veces que aparece el valor
     */
    public int contarApariciones( int valor )
    {
    	int cont = 0;
    	
    	int actual = getCantidadEnteros();
    	
    	for (int i = 0; i<actual; i++)
    	{
    		if(arregloEnteros[i] == valor)
    		{
    			cont ++;
    		}
    	}
        return cont;
    }

    /**
     * Cuenta cuántas veces aparece la cadena recibida por parámetro en el arreglo de cadenas.
     * 
     * La búsqueda no debe diferenciar entre mayúsculas y minúsculas.
     * @param cadena La cadena buscada
     * @return La cantidad de veces que aparece la cadena
     */
    public int contarApariciones( String cadena )
    {
    	int cont = 0;
    	
    	int actual = getCantidadCadenas();
    	
    	for (int i = 0; i<actual; i++)
    	{
    		if(arregloCadenas[i].equalsIgnoreCase(cadena))
    		{
    			cont ++;
    		}
    	}
        return cont;
    }

    /**
     * Busca en qué posiciones del arreglo de enteros se encuentra el valor que se recibe en el parámetro
     * @param valor El valor que se debe buscar
     * @return Un arreglo con los números de las posiciones del arreglo de enteros en las que se encuentra el valor buscado. Si el valor no se encuentra, el arreglo retornado
     *         es de tamaño 0.
     */
    public int[] buscarEntero( int valor )
    {
    	int[] pos = new int[contarApariciones(valor)];
    	
    	int actual = getCantidadEnteros();
    	
    	int cont = 0;
    	for (int i=0; i<actual; i++)
    	{
    		if (arregloEnteros[i] == valor)
    		{
    			pos[cont] = i;
    			cont ++;
    		}
    	}
    	
        return pos;
    }

    /**
     * Calcula cuál es el rango de los enteros (el valor mínimo y el máximo).
     * @return Un arreglo con dos posiciones: en la primera posición, debe estar el valor mínimo en el arreglo de enteros; en la segunda posición, debe estar el valor máximo
     *         en el arreglo de enteros. Si el arreglo está vacío, debe retornar un arreglo vacío.
     */
    public int[] calcularRangoEnteros( )
    {
    	int actual = getCantidadEnteros();
    	
    	if (actual == 0)
    	{
    		int[] rango = new int[0];
    		return rango;
    	}
    	
    	int min = arregloEnteros[0];
    	
    	int max = arregloEnteros[0];
    	
    	for (int i=0; i<actual; i++)
    	{
    		if (arregloEnteros[i] < min)
    		{
    			min = arregloEnteros[i];
    		}
    		if (arregloEnteros[i] > max)
    		{
    			max = arregloEnteros[i];
    		}
    	}
    	
    	int[] rango = {min,max};
    	
        return rango;
    }

    /**
     * Calcula un histograma de los valores del arreglo de enteros y lo devuelve como un mapa donde las llaves son los valores del arreglo y los valores son la cantidad de
     * veces que aparece cada uno en el arreglo de enteros.
     * @return Un mapa con el histograma de valores.
     */
    public HashMap<Integer, Integer> calcularHistograma( )
    {
    	HashMap<Integer, Integer> histo = new HashMap<Integer,Integer>();
    	
    	int actual = getCantidadEnteros();
    	
    	for (int i=0; i<actual; i++)
    	{
    		if (!histo.containsKey(arregloEnteros[i]))
    		{
    			int cantidad = contarApariciones(arregloEnteros[i]);
        		histo.put(arregloEnteros[i], cantidad);
    		}
    	}
    	
        return histo;
    }

    /**
     * Cuenta cuántos valores dentro del arreglo de enteros están repetidos.
     * @return La cantidad de enteos diferentes que aparecen más de una vez
     */
    public int contarEnterosRepetidos( )
    {
    	int actual = getCantidadEnteros();
    	
    	int cont = 0;
    	
    	HashMap<Integer, Integer> histo = new HashMap<Integer,Integer>();
    	
    	for (int i=0; i<actual; i++)
    	{
    		if (!histo.containsKey(arregloEnteros[i]))
    		{
    			int cantidad = contarApariciones(arregloEnteros[i]);
    			if (cantidad > 1)
    			{
    				histo.put(arregloEnteros[i], cantidad);
    				cont ++;
    			}
    		}
    	}
    	
        return cont;
    }

    /**
     * Compara el arreglo de enteros con otro arreglo de enteros y verifica si son iguales, es decir que contienen los mismos elementos exactamente en el mismo orden.
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si los arreglos son idénticos y false de lo contrario
     */
    public boolean compararArregloEnteros( int[] otroArreglo )
    {
    	int actual = getCantidadEnteros();
    	
    	if (actual > 0)
    	{
	    	for (int i=0; i<actual; i++)
	    	{
	    		if (arregloEnteros[i] != otroArreglo[i])
	    		{
	    			return false;
	    		}
	    	}
    	}
    	else if (otroArreglo.length > 0)
    	{
    		return false;
    	}
    	
        return true;
    }

    /**
     * Compara el arreglo de enteros con otro arreglo de enteros y verifica que tengan los mismos elementos, aunque podría ser en otro orden.
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si los elementos en los dos arreglos son los mismos
     */
    public boolean mismosEnteros( int[] otroArreglo )
    {
    	int actual = getCantidadEnteros();
    	
    	int cantidad = otroArreglo.length;
    	
    	if (actual >0)
    	{
    		for (int i=0; i<actual; i++)
	    	{
	    		int num = arregloEnteros[i];
	    		
	    		boolean esta = false;
	    		
	    		for (int j=0; j<cantidad && !esta; j++)
	    		{
	    			if (otroArreglo[j] == num)
	    			{
	    				esta = true;
	    			}
	    		}
	    		
	    		if (!esta)
	    		{
	    			return false;
	    		}
	    	}
    	}
    	else if (cantidad != 0)
    	{
    		return false;
    	}

        return true;
    }

    /**
     * Cambia los elementos del arreglo de enteros por una nueva serie de valores generada de forma aleatoria.
     * 
     * Para generar los valores se debe partir de una distribución uniforme usando Math.random().
     * 
     * Los números en el arreglo deben quedar entre el valor mínimo y el máximo.
     * @param cantidad La cantidad de elementos que debe haber en el arreglo
     * @param minimo El valor mínimo para los números generados
     * @param maximo El valor máximo para los números generados
     */
    public void generarEnteros( int cantidad, int minimo, int maximo )
    {
    	int[] nuevo = new int[cantidad];
    	
    	nuevo[0] = minimo;
    	
    	nuevo[cantidad-1] = maximo;
    	
    	for (int i=1; i<cantidad-1; i++)
    	{
    		 double num = (maximo - minimo + 1) * Math.random();
    		 
    		 int valor = ((int) num ) + minimo;
    		 
    		 nuevo[i] = valor;
    	}
    	
    	arregloEnteros = nuevo;
    }

}
