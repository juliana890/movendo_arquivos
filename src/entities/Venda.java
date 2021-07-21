package entities;

import java.util.Date;

public class Venda {
	
	private Integer numero;
	private String nomeCliente;
	private Date data;
	private Double valor;
	
	public Venda() {}

	public Venda(Integer numero, String nomeCliente, Date data, Double valor) {
		super();
		this.numero = numero;
		this.nomeCliente = nomeCliente;
		this.data = data;
		this.valor = valor;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Venda [numero=" + numero + ", nomeCliente=" + nomeCliente + ", data=" + data + ", valor=" + valor + "]";
	}

}
