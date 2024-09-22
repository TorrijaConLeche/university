package protectora;
public abstract class Animal implements Constantes {
    protected String nombre;
    protected int edad;
    protected boolean sociable;
    protected boolean apadrinado;
    protected char sexo;
    protected int num_solicitud;
    protected Solicitud[] solicitudes; // Atibuto para almacenar las solicitudes de cada animal
    public Animal(String nombre, int edad, boolean sociable, boolean apadrinado, char sexo){
        this.nombre = nombre;
        this.edad = edad;
        this.sociable=sociable;
        this.apadrinado=apadrinado;
        this.sexo=sexo;

        solicitudes = new Solicitud[NUM_MAX_SOLICITUDES]; // Tamaño en funcion de una constante
        num_solicitud = 0;

    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public boolean isSociable() {
        return sociable;
    }

    public boolean isApadrinado() {
        return apadrinado;
    }

    public char getSexo() {
        return sexo;
    }

    public int getNum_solicitud() {
        return num_solicitud;
    }

    public void addSolicitud(Solicitud solicitud){ // Añadimos una solicitud al animal en cuestion
        solicitudes[num_solicitud] = solicitud;
        num_solicitud+=1;
    }

    public String getInfoSolicitudes(){
        String mensaje = ""; // Mensaje que mostrara si el animal no tiene solicitudes
        for(int i = 0; i < solicitudes.length;i++){
            if(solicitudes[i]!=null){
                // Mensaje que se mostrará si el animal tiene alguna solicitud
                if (solicitudes[i].getTipo() == '1') {
                    mensaje = "Email solicitante: " + solicitudes[i].getEmail_persona() +
                            " | Tipo: " + solicitudes[i].getTipo() + " (adopcion)\n" + mensaje;
                }
            }
        }
        // Si el mensaje esta vacio (por que no se han encontrado solicitudes), se informara al usuario
        if (mensaje.isEmpty()) {
            mensaje = nombre + " no tiene solicitudes";
        }
        return mensaje;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSociable(boolean sociable) {
        this.sociable = sociable;
    }

    public void setApadrinado(boolean apadrinado) {
        this.apadrinado = apadrinado;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setNum_solicitud(int num_solicitud) {
        this.num_solicitud = num_solicitud;
    }

    public abstract double getGastosVet();
    @Override
    public String toString() {
        return  "Nombre: " + nombre +
                " | Edad: " + edad +
                " | Sociable: " + sociable +
                " | Apadrinado: " + apadrinado +
                " | Sexo: " + sexo +
                " | Num solicitudes: " + num_solicitud;
    }

}
