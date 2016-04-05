package net.ukr.ruslana;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		Class<?> cls = Sum.class;
		Method[] methods = cls.getDeclaredMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(Test.class)) {
				Test test = method.getAnnotation(Test.class);
				int result = (Integer)method.invoke(null, test.a(), test.b());
				System.out.println(result);
			}
		}
	}

}
