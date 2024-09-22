package protectora;

public class Perro extends Animal {
    private String raza;
    private int peso;
    private boolean ppp;
    private boolean leishmania;
    public Perro(String nombre, int edad, boolean sociable, boolean apadrinado, char sexo, String raza, int peso, boolean ppp, boolean leishmania){
        super(nombre, edad,sociable,apadrinado, sexo);
        this.raza=raza;
        this.peso=peso;
        this.ppp=ppp;
        this.leishmania=leishmania;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setPpp(boolean ppp) {
        this.ppp = ppp;
    }

    public void setLeishmania(boolean leishmania) {
        this.leishmania = leishmania;
    }

    public String getRaza() {
        return raza;
    }

    public int getPeso() {
        return peso;
    }

    public boolean getPpp() {
        return ppp;
    }

    public boolean getLeishmania() {
        return leishmania;
    }

    public double getPiensoPerro(){ // Calculo del pienso de acada perro

        double pienso_kg = 0;
        if (edad>2) { // Cuando los animales no son cachorros calculamos el pienso

            if (peso<=15) { // Peso menor o igual a 15kg
                pienso_kg=PIENSO_PERROS_PEQUENOS;
            } else if (peso>25) { // Peso mayor a 25kg
                pienso_kg= peso*PIENSO_PERROS_GRANDES;
            }else { // Peso entre 15 y 25 kg
                pienso_kg=PIENSO_PERROS_MEDIANOS;
            }
        }
        return pienso_kg;
    }

    public double getGastosVet(){ // Calculo de los gastos veterinarios de los gatos
        double total = 0;
        if (!isApadrinado()) { // Si no está apadrinado..
            if (getLeishmania()) {
                total = PRECIO_LEISHMANIA*12 + total;
            }
            if (getPpp() && !isSociable()) {
                total = VACUNA_RABIA + PRECIO_SEDACION + total; // Coste vacuna rabia (anual)
            } else {
                total +=VACUNA_RABIA; // Coste anual rabia
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return super.toString() + " | Raza: " + raza + " | Peso " + peso + "(kg) | PPP: " + ppp + " | Leishmania: " + leishmania;
    }
}
