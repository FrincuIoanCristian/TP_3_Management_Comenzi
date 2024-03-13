package dao;

import java.awt.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import start.ReflectionExample;
import Exception.ExistElementException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Superclasa care implementeza toate operatiile pe baza de date
 * @Author: Frincu Ioan-Cristian
 * @Since: Mai 2023
 */
public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}

	/**
	 * Creaza query-ul pentru selectare dupa id
	 * @param field field-ul dupa care se cauta
	 * @return query-ul rezultat
	 */
	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		return sb.toString();
	}

	/**
	 * Creaza query-ul pentry findAll
	 * @return	query-ul rezultat
	 */
	private String createFindAllQuery(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(type.getSimpleName());
		return sb.toString();
	}

	/**
	 * Creaza query-ul pentru insert
	 * @param t	obiectul din care luam campurile
	 * @return query-ul rezultat
	 */
	private String createInsertQuery(T t){
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(t.getClass().getSimpleName());
		sb.append(" (");
		Field[] fields = type.getDeclaredFields();
		for (int i = 0; i < fields.length-1; i++) {
			sb.append(fields[i].getName() + ", ");
		}
		sb.append(fields[fields.length-1].getName() + ") VALUES (");
		for(int i=1;i<t.getClass().getDeclaredFields().length;i++) {
			sb.append("?,");
		}
		sb.append("?)");
		return sb.toString();
	}

	/**
	 * Creaza query-ul pentru update
	 * @param field	lista de field-uri ce vrem sa le modificam
	 * @return	query-ul rezultat
	 */
	private String createUpdateQuery(ArrayList<String> field) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ").append(type.getSimpleName()).append("\nSET");
		for (int i=0;i<field.size();i++) {
			sb.append(" " + field.get(i) + "=?,");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("\nWHERE id =?");
		return sb.toString();
	}

	/**
	 * Creaza query-ul pentru delete
	 * @return query-ul rezultat
	 */
	private String createDeleteQuery(){
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE id= ?");

		return sb.toString();
	}

	/**
	 * Extrage toate obiectele dintr-o tabele din bb
	 * @return	returneaza lista de obiecte din tabele respectiva
	 */
	public List<T> findAll() {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createFindAllQuery();
		try{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			return createObjects(resultSet);
		}catch (SQLException e){
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		}finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * Metoda prin care extrage obiectul din tabele din bd cu un anumit id
	 * @param id id-ul dupa care cautam obiectul
	 * @return obiectul gasit
	 */
	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
		Constructor[] ctors = type.getDeclaredConstructors();
		Constructor ctor = null;
		for (int i = 0; i < ctors.length; i++) {
			ctor = ctors[i];
			if (ctor.getGenericParameterTypes().length == 0)
				break;
		}
		try {
			while (resultSet.next()) {
				ctor.setAccessible(true);
				T instance = (T) ctor.newInstance();
				for (Field field : type.getDeclaredFields()) {
					String fieldName = field.getName();
					Object value = resultSet.getObject(fieldName);
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Metoda prin care se insereza intr-o tabele in bd
	 * @param t	obiecul ce il inseram
	 * @return	returneaza obiectul
	 * @throws ExistElementException exceptie in cazul in care exista deja obiectul cu id-ul respectiv
	 */
	public T insert(T t) throws ExistElementException {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createInsertQuery(t);

		try{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);

			Field[] fields = t.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				Object value = fields[i].get(t);
				statement.setObject(i + 1, value);
			}
			statement.executeUpdate();

		}catch (SQLException e){
			throw new ExistElementException();
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return t;
	}

	/**
	 * Metoda care modifica campurile unui obiect din tabele
	 * @param t	obiectul pe care se face update
	 * @param field	field-urile ce vrem sa le modificam
	 * @param value	noile valori
	 * @return	noul obiect
	 */
	public T update(T t, ArrayList<String> field, ArrayList<Object> value) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createUpdateQuery(field);
		try{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			for (int i = 0; i < value.size(); i++) {
				statement.setObject(i + 1, value.get(i));
			}
			statement.executeUpdate();

		}catch (SQLException e){
			LOGGER.log(Level.WARNING, t.getClass().getName() + "DAO:update " + e.getMessage());
		}finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return t;
	}

	/**
	 * Metoda prin care stergem un obiect dintr-o tabele din bd
	 * @param id id-ul obiectului ce vrem sa il stergem
	 */
	public void delete(int id){
		Connection connection = null;
		PreparedStatement statement = null;
		String deleteQuery = createDeleteQuery();

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(deleteQuery);
			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "DAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

	/**
	 * Metoda ce creaza tabelul cu valorile din baza de date
	 * @param t	tipul obiectelor
	 * @throws IllegalAccessException
	 */
	public void createTable(T t) throws IllegalAccessException {
		List<T> dateTabel= findAll();
		String[] coloane = new String[t.getClass().getDeclaredFields().length];
		String[][] linii;
		int i = 0;
		for (Field field : t.getClass().getDeclaredFields()) {
			coloane[i++] = field.getName();
		}
		linii = ReflectionExample.dateTabel(dateTabel);
		JFrame frame = new JFrame();
		if(linii == null){
			JOptionPane.showMessageDialog(frame, "Tabela goala!");
		}else {
			DefaultTableModel tb = new DefaultTableModel(linii,coloane);
			JTable jTable = new JTable(tb);
			jTable.setBounds(30, 40, 200, 300);
			JScrollPane scrollPane = new JScrollPane(jTable);
			scrollPane.setBorder(BorderFactory.createTitledBorder(t.getClass().getName()));
			JLabel titlu = new JLabel(t.getClass().getName());
			titlu.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
			frame.add(titlu);
			frame.add(scrollPane);
			frame.setSize(700, 400);
			frame.setVisible(true);
		}
	}
}