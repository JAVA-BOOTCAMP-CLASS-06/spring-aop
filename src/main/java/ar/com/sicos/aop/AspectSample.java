package ar.com.sicos.aop;

import java.util.Random;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectSample {
		
	@Before("execution(* ar.com.sicos.dao.*.*(..))")
	public void beforeAll() {
		randomDelay();
	}
	
	@Before(value="execution(* ar.com.sicos.dao.*.set*(..)) && args(inf)", argNames="inf")
	public void beforeSetter(JoinPoint p, String inf) {
		System.out.println("[BEFORE] Se va a invocar el metodo [" + p.getSignature() + "] con el parametro [" + inf + "]");
	}
	
	@Before("execution(* ar.com.sicos.dao.*.get*(..))")
	public void beforeGetter(JoinPoint p) {
		System.out.println("[BEFORE] Se va a invocar el metodo [" + p.getSignature() + "]");
	}
	
	@AfterReturning("execution(* ar.com.sicos.dao.*.get*(..))")
	public void afterOkGetter(JoinPoint p) {
		System.out.println("[AFTER RETURN] Se ejecuto con exito el metodo [" + p.getSignature() + "]");
	}
	
	@After("execution(* ar.com.sicos.dao.*.get*(..))")
	public void afterGetter(JoinPoint p) {
		System.out.println("[AFTER FINALLY] Finalizo con exito o error el metodo [" + p.getSignature() + "]");
	}
	
	
	@AfterThrowing(value="execution(* ar.com.sicos.dao.*.get*(..))", throwing="exc")
	public void afterGetterError(JoinPoint p, Exception exc) {
		System.out.println("[AFTER THROW] Ocurrio una excepcion en el metodo [" + p.getSignature() + "] -> [" + exc.getMessage() + "]");
	}


	@Around("execution(* ar.com.sicos.dao.*.*(..))")
	public Object profileMethod(ProceedingJoinPoint jp) {
		System.out.println("[AROUND] Iniciando profile para el metodo [" + jp.getSignature() + "]");
		
		long init = System.currentTimeMillis();
		
		Object obj = null;
		try {
			obj = jp.proceed();
		} catch (Throwable e) {}

		long end = System.currentTimeMillis();
		
		System.out.println("[AROUND] Finalizando profile para el metodo [" + jp.getSignature() + "] -> Tiempo de ejecucion " + (end - init) + " mseg");
		return obj;		
	}

	private void randomDelay() {
		Random rand = new Random(500);
		try {
			Thread.sleep(rand.nextInt(1000));
		} catch (InterruptedException e) {}
		
	}
}
