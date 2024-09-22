package protectora;// HECHO POR ALEJANDRO ARANDA GARCIA Y JUAN RAFAEL ESCOBAR DIAZ

import java.io.*;
import java.util.*;

public class B1_ArandaEscobar {
    final static Scanner lector = new Scanner(System.in);
    final static Scanner lectorOpcion = new Scanner(System.in);
    public static void main(String[] args) {
        boolean seguir = true;
        Protectora protectoraLibertad = new Protectora();
        String fichero = "animales.txt";
        try {
            leerAnimales(fichero, protectoraLibertad);
        } catch (FileNotFoundException e) { // Controlamos la excepcion en caso de que no se encuentra el fichero
            System.out.println("|------------------------------------------|");
            System.out.println("ERROR AL LEER, EL FICHERO " + fichero + " NO EXISTE");
            System.out.println("|------------------------------------------|");
            seguir=false; // Si el fichero no existe, no continuamos con la ejecución
        }
        if (seguir){ // En caso de encontrar un fichero correcto, continuamos con la ejecucion
            ClinicaVeterniaria clinicaEstrella =
                    new ClinicaVeterniaria(14.00, "Clinica Estrella", "6666");
            mostrarMenu();
            gestionarProtectora(protectoraLibertad, clinicaEstrella);
        }

    }
    public static void gestionarProtectora(Protectora protectoraLibertad, ClinicaVeterniaria clinicaEstrella){
        int opcion = 2;
        String mensaje = "";
        String nombre;
        String correo;
        char tipo = 2; // Lo inicializamos en un valor diferente a 1 y 0 para que el while se ejecute

        boolean seguir = true;
        while(opcion>0){
            // CONTROLAR EL INPUT CON UNA EXCEPCION
            seguir = true;
            while (seguir) {
                try {
                    opcion = askOpcion();
                    seguir = false; // Si no se lanza ninguna excepción, salimos del bucle
                } catch (InputMismatchException e) {
                    System.out.println("Error: Por favor, introduce un número entero.");
                    lectorOpcion.nextLine(); // Limpiamos el buffer del scanner
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: Por favor, introduce un número entre 0 y 8");
                }
            }

            switch (opcion){ // Una vez capturada la opcion, ejecutamos el / los metodos correspondientes
                case 0:
                    mensaje = "Saliendo del menu..";
                    break;
                case 1:
                    mensaje = protectoraLibertad.mostrarAnimales();
                    break;
                case 2:
                    seguir = true;
                    nombre = askNombre();
                    System.out.println("Porfavor introduce un tipo solicitud: acogida (0) / adopcion (1): ");
                    while (seguir){
                        try {
                            tipo = askTipo();
                            seguir = false;
                        } catch (IllegalArgumentException e){ // Si el usuario introducen un tipo no valido
                            System.out.println(e.getMessage());
                        }
                    }
                    correo = askCorreo();
                    try{
                        mensaje = protectoraLibertad.altaSolicitud(nombre, tipo, correo);
                    } catch (Protectora.LimiteSolicitudes e) {
                        // Si el animal en cuestion ha alcanzado el limite de solicitudes
                        System.out.println("Error: " + e.getMessage());
                        mensaje = "";
                    }
                    break;
                case 3:
                    nombre = askNombre(); // Pedimos el nombre del animal al usuario
                    mensaje = protectoraLibertad.listadoSolicitudes(nombre);
                    break;
                case 4:
                    mensaje = "Gastos previstos anuales: " + protectoraLibertad.gastosVetTotal() + "€";
                    break;
                case 5:
                    mensaje = "Gastos por esterilización gatas: " + protectoraLibertad.costeEsterilizacionGatas(clinicaEstrella) + "€";
                    break;
                case 6:
                    mensaje = "Gasto de pienso semanal: " + protectoraLibertad.estimacionPiensoSemanal() + "kg";
                    break;
                case 7:
                    mensaje = protectoraLibertad.listadoInvisibles();
                    break;
            }

            System.out.println(mensaje);
            System.out.println();
            mostrarMenu();
        }
    }
    // Metodo utilizado para leer los animales del fichero de texto
    public static void leerAnimales(String cadena, Protectora protectoraLibertad) throws FileNotFoundException {

        File f = new File(cadena);
        Scanner nombre_f = new Scanner(f);

        char tipoAnimal, sexo;
        String nombre, raza;
        int edad, peso;
        boolean sociable, apadrinado, ppp, leishmania, esterilizado;

        while (nombre_f.hasNext()) { // Mientras haya contenido en el fichero..
            tipoAnimal = nombre_f.next().charAt(0);
            nombre = nombre_f.next();
            sexo = nombre_f.next().charAt(0);
            edad = nombre_f.nextInt();
            sociable = nombre_f.nextBoolean();
            apadrinado = nombre_f.nextBoolean();

            if (tipoAnimal == 'p'){
                raza = nombre_f.next();
                peso = nombre_f.nextInt();
                ppp = nombre_f.nextBoolean();
                leishmania = nombre_f.nextBoolean();

                Perro animal1 = new Perro(nombre, edad, sociable, apadrinado, sexo, raza, peso, ppp, leishmania);

                protectoraLibertad.addAnimal(animal1);
            } else {
                esterilizado = nombre_f.nextBoolean();
                protectoraLibertad.addAnimal(new Gato(nombre, edad, sociable, apadrinado, sexo, esterilizado));

            }
        }
        nombre_f.close();
    }
    public static void mostrarASCII(){
        System.out.println("          __  __ _____ _   _ _   _         ");
        System.out.println("         |  \\/  | ____| \\ | | | | |        ");
        System.out.println("  _____  | |\\/| |  _| |  \\| | | | |  _____ ");
        System.out.println(" |_____| | |  | | |___| |\\  | |_| | |_____|");
        System.out.println("         |_|  |_|_____|_| \\_|\\___/         ");

        System.out.println("                                  .-.");
        System.out.println("     (___________________________()6 `-,");
        System.out.println("    (   ______________________   /''\"`");
        System.out.println("     //\\\\                      //\\\\");
        System.out.println("     \"\" \"\"                     \"\" \"\"");
    }
    public static void mostrarMenu(){ // Mostramos el menu al usuario

        mostrarASCII();

        System.out.println("0. Salir del menu");
        System.out.println("1. Mostrar toda la información de los animales de la protectora");
        System.out.println("2. Solicitar adopción o acogida de un animal");
        System.out.println("3. Consultar listado de sol. de adopcion de un animal");
        System.out.println("4. Calcular coste gastos veterinarios anuales");
        System.out.println("5. Calcular coste gastos por esterilización de las gatas");
        System.out.println("6. Calcular estimación de pienso para perros semanal (en kg)");
        System.out.println("7. Listado de animales invisibles");
    }
    public static String askNombre(){ // Solicitamos el nombre del animal al usuario
        System.out.println("Introduce el nombre del animal: ");
        return lector.nextLine().toLowerCase();
    }
    public static char askTipo() throws IllegalArgumentException{ // Pedir el tipo de solicitud al usuario
        char tipo = 2;
        tipo = lector.nextLine().charAt(0);
        if (tipo != '0' && tipo != '1') { // Controlamos que el usuario introduzca 0 o 1
            throw new IllegalArgumentException("Debes introducir un tipo valido O / 1");
        }
        return tipo;
    }
    public static String askCorreo(){ // Pedimos el correo al solicitante
        System.out.println("Introduce el correo del solicitante: ");
        return lector.nextLine().toLowerCase();
    }
    // Solicitamos al usuario la opcion del menu
    public static int askOpcion() throws InputMismatchException, IllegalArgumentException{
        // Controlamos ambas excepciones fuera del metodo
        // 1. En caso de que se introduzca un caracter no valido
        // 2. En caso de que se introduzca una opcion que no existe
        int opcion = -1;
        opcion = lectorOpcion.nextInt();
        if (opcion <0 || opcion > 8) {
            throw new IllegalArgumentException("La opcion debe estar entre 0 y 7");
        }
        return opcion;
    }
}

