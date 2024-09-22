package protectora;
import java.lang.Math;

public class Protectora implements Constantes { // IMPORTAMOS LA INTERFAZ CONSTANTES
    // Implementar relacion asociacion 1 a 0..20 --> array y contador
    private Animal[] animales;
    private int numAnimales;

    Protectora() {
        animales = new Animal[NUM_MAX_ANIMALES];
        numAnimales = 0;

    }

    public void addAnimal(Animal animal) {
            animales[numAnimales] = animal;
            numAnimales++;
    }


    public double costeEsterilizacionGatas(ClinicaVeterniaria clinica) { //
        double total = 0;
        for (int i = 0; i < animales.length; i++) {
            if (animales[i] instanceof Gato) {
               if (!(((Gato) animales[i]).getEsterilizado()) && ((Gato) animales[i]).getSexo()=='h') { // Si no está esterilizado, hay que pagar el control de celo
                        total = clinica.getPrecioEsterilizacion() + total;
                    }
                }
             }
        // Convertirmos los gastos de mensuales a anuales
        return total;
    }
    public double gastosVetTotal() { // Sumamos el total de los gastos de perros y gatos
        double total = 0;
        for (int i = 0; i < animales.length; i++) {
            if (animales[i]!=null) {
                total += animales[i].getGastosVet();   // Gastos individuales de cada perro / gato
            }
        }
        return total; // Convertimos los gastos a gastos anuales
    }


    public double estimacionPiensoSemanal(){
        double total_pienso_kg = 0;
        for (int i = 0; i < animales.length; i++) {
            if (animales[i] instanceof Perro) {
                total_pienso_kg = ((Perro) animales[i]).getPiensoPerro() + total_pienso_kg; // Gasto de pienso individual
            }
        }
       return total_pienso_kg*7; // Convertimos a gasto semanal
    }

    public String mostrarAnimales(){
        String cadena = "";
        for (int i=0;i<numAnimales;i++){ // Iteramos para cada animal mostrando sus datos
            cadena = cadena +  " "  + animales[i].toString() + "\n";
        }
        return cadena;
    }

   public int existeAnimal(String nombre){
        int existe = -1 ;  // Si no existe devuelve -1
       for (int i = 0; i < animales.length; i++) {
           if(animales[i]!=null){ // Siempre que no sea null (es decir, exista un animal)
               if(animales[i].getNombre().toLowerCase().equals(nombre)){ // Si existe devuelve la posición del animal en el array
                   existe = i;
               }
           }
       }
       return existe;
   }
   public String altaSolicitud(String nombre, char tipo_solicitud, String email) throws LimiteSolicitudes{
        String mensaje;
        int i_animal = existeAnimal(nombre.toLowerCase()); // Indice animal (-1 si no existe)

        if(i_animal<0){ // Si el animal no existe
            mensaje = "No existe ningun animal con el nombre " + nombre + " en la protectora";
        } else {
                // Comprobamos si se ha alcanzado el maximo de solicitudes
            if (animales[i_animal].getNum_solicitud() == NUM_MAX_SOLICITUDES) {
                throw new LimiteSolicitudes(
                        "Se ha alcanzado el numero maximo de solicitudes (" + NUM_MAX_SOLICITUDES + ")");
            } else { // Creamos la solicitud y añadimos un valor al mensaje para informar al usuario
                Solicitud soli = new Solicitud(tipo_solicitud, email);
                animales[i_animal].addSolicitud(soli);
                mensaje = "Solicitud realizada correctamente";
            }
        }
       return mensaje;
   }

   public String listadoInvisibles(){ // Metodo para mostrar a los animales cachorros e invisibles
        String listado = "";
       for (int i = 0; i < animales.length; i++) {
           if(animales[i]!=null){ // Siempre que no sea null (es decir, exista un animal)
               if((animales[i].getNum_solicitud()==0 && animales[i].getEdad()>5) || animales[i].getEdad()<2) { // Si existe devuelve la posición del animal en el array
                   listado = animales[i].getNombre() + " " + listado;
               }
           }
       }
       return listado; // Devolvemos un mensaje informando al usuario
   }
   public String listadoSolicitudes(String nombre){
        String listado = "";
        int i_animal = existeAnimal(nombre); // Si el animal existe i_animal = posicion del animal en el vector
        if(i_animal<0){ // Si el animal no existe, i_animal será <0
            listado = "El animal solicitado no existe";
        } else {
            listado = animales[i_animal].getInfoSolicitudes(); // Obtenemos la informacion de las solicitudes
        }
        return listado;
   }

    static class LimiteSolicitudes extends Exception { // Utilizada si el animal alcanza el limite de solicitudes
        public LimiteSolicitudes (String mensaje){
            super(mensaje);
        }
    }
}