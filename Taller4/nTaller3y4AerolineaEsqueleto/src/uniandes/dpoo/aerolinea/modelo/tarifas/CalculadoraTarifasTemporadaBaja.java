package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas
{
	protected final int COSTO_POR_KM_NATURAL = 600;
	
	protected final int COSTO_POR_KM_CORPORATIVO = 900;
	
	protected final double DESCUENTO_PEQ = 0.02;
	
	protected final double DESCUENTO_MEDIANAS = 0.1;
	
	protected final double DESCUENTO_GRANDES = 0.2;
	
	public CalculadoraTarifasTemporadaBaja()
	{
		
	}
	
	public int calcularCostoBase(Vuelo vuelo, Cliente cliente)
	{
		String tipoCliente = cliente.getTipoCliente();
		
		int costo = COSTO_POR_KM_NATURAL;
		if (tipoCliente.equals(ClienteCorporativo.CORPORATIVO))
		{
			costo = COSTO_POR_KM_CORPORATIVO;
		}
		
		int distancia = calcularDistanciaVuelo(vuelo.getRuta());
		
		return distancia*costo;
	}
	
	public double calcularPorcentajeDescuento(Cliente cliente)
	{
		double descuento = 0;
		
		String tipoCliente = cliente.getTipoCliente();
		
		if (tipoCliente.equals(ClienteCorporativo.CORPORATIVO))
		{
			ClienteCorporativo clienteC = (ClienteCorporativo) cliente;
			
			if (clienteC.getTamanoEmpresa() == ClienteCorporativo.GRANDE)
			{
				descuento = DESCUENTO_GRANDES;
			}
			else if (clienteC.getTamanoEmpresa() == ClienteCorporativo.MEDIANA)
			{
				descuento = DESCUENTO_MEDIANAS;
			}
			else
			{
				descuento = DESCUENTO_PEQ;
			}
		}
		
		return descuento;
	}
}
