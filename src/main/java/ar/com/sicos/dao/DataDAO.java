package ar.com.sicos.dao;

import org.springframework.stereotype.Repository;

@Repository
public class DataDAO {
	
	private String info;
	
	public void setInfo(String inf) {
		System.out.println("Seteando informacion [" + inf + "]");
		this.info = inf;
	}

	public String getInfo() {
		System.out.println("Retornando informacion [" + info + "]");
		return info;
	}
	
	public String getInfoConError() {
		throw new RuntimeException("Ocurrio un error inesperado");
	}
	
}
