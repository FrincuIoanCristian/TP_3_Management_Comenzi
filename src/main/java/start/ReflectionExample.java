package start;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Clasa unde folosim reflection
 * @Author: Frincu Ioan-Cristian
 * @Since: Apr 03, 2017
 */
public class ReflectionExample {

	public static void retrieveProperties(Object object) {

		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true); 
			Object value;
			try {
				value = field.get(object);
				System.out.println(field.getName() + "=" + value);

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Metoda ce converteste datele dintr-o lista si le pune intr-o matrice de string-uri
	 * @param lista lista de obiecte
	 * @return o matrice de string-uri
	 * @param <T> pentru a face metoda generica
	 * @throws IllegalAccessException
	 */
	public static <T> String[][] dateTabel(List<T> lista) throws IllegalAccessException {
		int linii = lista.size();
		if(linii != 0) {
			Field[] field = lista.get(0).getClass().getDeclaredFields();
			int coloane = field.length;

			String[][] dateTabel = new String[linii][coloane];

			for (int i = 0; i < linii; i++) {
				T data = lista.get(i);
				for (int j = 0; j < coloane; j++) {
					field[j].setAccessible(true);
					dateTabel[i][j] = (String) field[j].get(data).toString();
				}
			}
			return dateTabel;
		}else{
			return null;
		}
	}
}



