package com.ipartek.formacion.almacen.presentacion;

import static com.ipartek.formacion.bibliotecas.Consola.*;

import com.ipartek.formacion.almacen.accesodatos.Dao;
import com.ipartek.formacion.almacen.accesodatos.FabricaDao;
import com.ipartek.formacion.almacen.entidades.Producto;

public class AlmacenConsola {
	private static final int SALIR = 0;

	private static Dao<Producto> dao = FabricaDao.getDaoProducto();

	public static void main(String[] args) {
		int opcion;

		do {
			mostrarMenu();
			opcion = pedirOpcion();
			ejecutarOpcion(opcion);
		} while (opcion != SALIR);

	}

	private static void mostrarMenu() {
		pl();
		pl("1. Listado");
		pl("2. Buscar por id");
		pl("3. Insertar");
		pl("4. Modificar");
		pl("5. Borrar");
		pl();
		pl("0. Salir");
		pl();
	}

	private static int pedirOpcion() {
		return pedirEntero("Introduce la opción deseada");
	}

	private static void ejecutarOpcion(int opcion) {
		switch (opcion) {
		case 0:
			pl("Gracias por usar la aplicación");
			break;
		case 1:
			listado();
			break;
		case 2:
			buscarPorId();
			break;
		case 3:
			insertar();
			break;
		case 4:
			modificar();
			break;
		case 5:
			borrar();
			break;
		default:
			ple("No conozco esa opción");
		}
	}

	private static void listado() {
		boolean hayProductos = false;

		for (Producto producto : dao.obtenerTodos()) {
			mostrarProductoLinea(producto);
			hayProductos = true;
		}

		if (!hayProductos) {
			pl("No hay ningún producto");
		}
	}

	private static void mostrarProductoLinea(Producto producto) {
		pl(producto);
	}

	private static void buscarPorId() {
		pedirProductoPorId();
	}

	private static void mostrarFichaProducto(Producto producto) {
		pl(producto);
	}

	private static void insertar() {
		Producto producto = pedirDatosProducto();

		dao.insertar(producto);
	}

	private static Producto pedirDatosProducto() {
		Producto producto = new Producto();

		boolean esIncorrecto = true;

		do {
			try {
				producto.setNombre(pedirTexto("Nombre"));
				esIncorrecto = false;
			} catch (IllegalArgumentException e) {
				ple(e.getMessage());
			}
		} while (esIncorrecto);

		esIncorrecto = true;

		do {
			try {
				producto.setPrecio(pedirBigDecimal("Precio"));
				esIncorrecto = false;
			} catch (IllegalArgumentException e) {
				ple(e.getMessage());
			}
		} while (esIncorrecto);

		esIncorrecto = true;

		do {
			try {
				producto.setStock(pedirEntero("Stock", OPCIONAL));
				esIncorrecto = false;
			} catch (IllegalArgumentException e) {
				ple(e.getMessage());
			}
		} while (esIncorrecto);

		esIncorrecto = true;

		do {
			try {
				producto.setFechaCaducidad(pedirFecha("Fecha de caducidad", OPCIONAL));
				esIncorrecto = false;
			} catch (IllegalArgumentException e) {
				ple(e.getMessage());
			}
		} while (esIncorrecto);

		return producto;
	}

	private static void modificar() {
		Producto producto = pedirProductoPorId(); 

		if(producto == null) {
			return;
		}
		
		Long id = producto.getId();
		
		producto = pedirDatosProducto();

		producto.setId(id);

		dao.modificar(producto);
	}

	private static void borrar() {
		Producto producto = pedirProductoPorId();
		
		if(producto == null) {
			return;
		}
		
		dao.borrar(producto.getId());
	}

	private static Producto pedirProductoPorId() {
		Long id = pedirLong("Id");

		Producto producto = dao.obtenerPorId(id);

		if (producto == null) {
			ple("No se ha encontrado el producto");
		} else {
			mostrarFichaProducto(producto);
		}

		return producto;
	}
}
