package net.ukr.ruslana;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		TextContainer textCont = new TextContainer("Hello World!");
		Class<?> cls = textCont.getClass();
		if (cls.isAnnotationPresent(SaveTo.class)) {
			SaveTo saveTo = cls.getAnnotation(SaveTo.class);
			String path = saveTo.path();
			Method[] methods = cls.getDeclaredMethods();

			for (Method method : methods) {
				if (method.isAnnotationPresent(Saver.class)) {
					method.invoke(textCont, path);
					System.out.println("File saved!");
				}
			}
		}
	}

}
