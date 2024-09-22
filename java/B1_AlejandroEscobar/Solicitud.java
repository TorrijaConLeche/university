package protectora;

public class Solicitud {
    private char tipo;
    private String email_persona;

    Solicitud(char tipo, String email_persona){
        this.tipo = tipo;
        this.email_persona = email_persona;
    }

    public char getTipo() {
        return tipo;
    }

    public String getEmail_persona() {
        return email_persona;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public void setEmail_persona(String email_persona) {
        this.email_persona = email_persona;
    }

    @Override
    public String toString() {
        return "Solicitud{" + "tipo=" + tipo + ", email_persona='" + email_persona + '\'' + '}';
    }
}
