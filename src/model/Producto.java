package model;

public class Producto {
	
	private int codigo;
	private String articulo;
	private int precio;
	private int codFabricante;
	
	
	
	
	public Producto(String articulo, int precio, int codFabricante) {
		super();
		this.articulo = articulo;
		this.precio = precio;
		this.codFabricante = codFabricante;
	}




	public Producto(int codigo, String articulo, int precio) {
		this.codigo = codigo;
		this.articulo = articulo;
		this.precio = precio;
	}
	
	


	public Producto(int codigo, String articulo, int precio, int codFabricante) {
		super();
		this.codigo = codigo;
		this.articulo = articulo;
		this.precio = precio;
		this.codFabricante = codFabricante;
	}




	public int getCodFabricante() {
		return codFabricante;
	}



	public void setCodFabricante(int codFabricante) {
		this.codFabricante = codFabricante;
	}




	public int getCodigo() {
		return codigo;
	}


	public String getArticulo() {
		return articulo;
	}


	public int getPrecio() {
		return precio;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}


	public void setPrecio(int precio) {
		this.precio = precio;
	}


	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", articulo=" + articulo + ", precio=" + precio + "]";
	}
	
	
	

}
