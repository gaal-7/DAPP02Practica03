package org.uv.dapp02practica03;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmpleadoDAO dao = new EmpleadoDAO();
        int opcion;
        
        do {
            System.out.println("\n--- Menu de Empleados ---");
            System.out.println("1. Agregar empleado");
            System.out.println("2. Modificar empleado");
            System.out.println("3. Eliminar empleado");
            System.out.println("4. Buscar empleado por ID");
            System.out.println("5. Mostrar todos los empleados");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    EmpleadoPojo nuevoEmpleado = new EmpleadoPojo();
                    System.out.print("Ingrese nombre: ");
                    nuevoEmpleado.setNombre(scanner.nextLine());
                    System.out.print("Ingrese direccion: ");
                    nuevoEmpleado.setDireccion(scanner.nextLine());
                    System.out.print("Ingrese telefono: ");
                    nuevoEmpleado.setTelefono(scanner.nextLine());
                    dao.guardar(nuevoEmpleado);
                    System.out.println("Empleado agregado correctamente.");
                    break;
                case 2:
                    System.out.print("Ingrese el ID del empleado a modificar: ");
                    Long idModificar = scanner.nextLong();
                    scanner.nextLine();
                    EmpleadoPojo empleadoMod = new EmpleadoPojo();
                    System.out.print("Nuevo nombre: ");
                    empleadoMod.setNombre(scanner.nextLine());
                    System.out.print("Nueva direccion: ");
                    empleadoMod.setDireccion(scanner.nextLine());
                    System.out.print("Nuevo telefono: ");
                    empleadoMod.setTelefono(scanner.nextLine());
                    dao.modificar(empleadoMod, idModificar);
                    System.out.println("Empleado modificado correctamente.");
                    break;
                case 3:
                    System.out.print("Ingrese el ID del empleado a eliminar: ");
                    Long idEliminar = scanner.nextLong();
                    if (dao.eliminar(idEliminar)) {
                        System.out.println("Empleado eliminado correctamente.");
                    } else {
                        System.out.println("No se encontro el empleado.");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el ID del empleado a buscar: ");
                    Long idBuscar = scanner.nextLong();
                    EmpleadoPojo empleado = dao.buscarById(idBuscar);
                    if (empleado != null) {
                        System.out.println("ID: " + empleado.getClave());
                        System.out.println("Nombre: " + empleado.getNombre());
                        System.out.println("Direccion: " + empleado.getDireccion());
                        System.out.println("Teléfono: " + empleado.getTelefono());
                    } else {
                        System.out.println("Empleado no encontrado.");
                    }
                    break;
                case 5:
                    List<EmpleadoPojo> empleados = dao.buscarAll();
                    if (!empleados.isEmpty()) {
                        for (EmpleadoPojo emp : empleados) {
                            System.out.println("ID: " + emp.getClave() + ", Nombre: " + emp.getNombre() + ", Direccion: " + emp.getDireccion() + ", Telefono: " + emp.getTelefono());
                        }
                    } else {
                        System.out.println("No hay empleados registrados.");
                    }
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 6);
        
        scanner.close();
    }
}
