import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            Luchador t = new Luchador();
            t.setName("Luchador " + i);
            t.start();
        }
    }
}


public class Luchador extends Thread {


    @Override
    public void run() {
        try {

            Cuadrilatero.AddParticipante(this);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Cuadrilatero {
    static Semaphore semaphore =new Semaphore(2,true);
    static Luchador luchador1 = null;
    static Luchador luchador2 = null;
    static void AddParticipante(Luchador Luchador){
        try {
            semaphore.acquire(2);

            if(luchador1==null){
                luchador1=Luchador;
            }else {
                luchador2 = Luchador;
            }
            
            lucha();

        }catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    static void lucha(){

        Random r= new Random();

        if(r.nextBoolean()){
            luchador2=null;

            //Jugador 1 ganador
        } else {
            //Jugador 2 ganador
            luchador1=null;

        }

        semaphore.release();


    }


}