package protectora;

public class Gato extends Animal{
    private boolean esterilizado;
    public Gato(String nombre, int edad, boolean sociable, boolean apadrinado, char sexo, boolean esterilizado){
        super(nombre, edad, sociable, apadrinado, sexo);
        this.esterilizado = esterilizado;
    }
    public boolean getEsterilizado(){
        return esterilizado;
    }

    public void setEsterilizado(boolean esterilizado) {
        this.esterilizado = esterilizado;
    }

    public double getGastosVet(){ // Calcular los gastos veterinarios de los gatos
        double total= 0;
        if (!isApadrinado()) { // Si no está apadrinado..
            if (!getEsterilizado() &&  getSexo()=='h') { // Si no está esterilizado, hay que pagar el control de celo
                total = COSTE_CONTROL_CELO + total;
            }
        }
        return total*12; // Convertimos los gastos anuales a gastos mensuales
    }

    @Override
    public String toString(){
        return super.toString() + " | Esterilizado: " + esterilizado;
    }
}
