package ar.com.sicos;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ar.com.sicos.config.AppConfig;
import ar.com.sicos.dao.DataDAO;

public class App {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(AppConfig.class);
		ac.refresh();

		DataDAO dao = ac.getBean(DataDAO.class);
		
		dao.setInfo("Hola Mundo!!!");
		
		String info = dao.getInfo();
		
		System.out.println("Informacion obtenida: " + info);
		
		try {
			dao.getInfoConError();
		} catch (Throwable t) {}
		
		ac.close();
	}

}
