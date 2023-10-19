//Salvador Depons(303738) y Valentina Velazquez(305278)
package Interfaz;
import Dominio.*;
import java.util.*;
import java.io.*;


 class Prueba {
    public static void main(String args[]){
        
        // Comienzo del programa
        Scanner input= new Scanner(System.in);
        String ingreso="";
        boolean juega=true;
        System.out.println("Desea jugar una partida? SI/NO");
        String resp=input.nextLine();
        
        // Validacion de respuesta inicial
        while (!(resp.equals("SI") || resp.equals("NO"))){
            System.out.println("Ingrese respuesta valida SI/NO");
            resp=input.nextLine();
        }
        
        if(resp.equals("no")){
            juega=false;
        }
        
        // Bucle donde se ejecutara el juego
        while (juega){
            System.out.println("Elija tipo de tablero");
            System.out.println("a) Archivo txt");
            System.out.println("b) Tablero predefinido");
            System.out.println("c) Tablero al azar");
            String tipoTablero = input.nextLine();
            
            while (!(tipoTablero.equals("a") || tipoTablero.equals("b") || tipoTablero.equals("c"))){
                System.out.println("Ingrese dato valido a/b/c");
                tipoTablero = input.nextLine();
            }
            Tablero t1 = new Tablero();
            
            switch (tipoTablero){
                case "a":
                   // Lee el tablero desde un archivo de texto
                   try {
                        // Guarda ruta actual de el archivo Prueba
                        String directorioActual = System.getProperty("user.dir");
                        // Crea variable ruta la cual es la ruta hacia datos.txt
                        String ruta = directorioActual +"/test/datos.txt"; 
                        Scanner scanner = new Scanner(new File(ruta));
                        
                        // Lee filas y columnas 
                        int filastxt = scanner.nextInt();                 
                        int columnastxt = scanner.nextInt();
                        scanner.nextLine();
                        
                        // Define el largo y ancho del array segun los datos leidos  
                        String[][] formaTablero = new String[filastxt][columnastxt];
                        
                        // Lee cada uno de los datos de el tablero 
                        for (int i = 0; i < filastxt; i++) {
                            String fil = scanner.nextLine();
                            for (int j = 0; j < columnastxt; j++) {
                                 formaTablero[i][j] = String.valueOf(fil.charAt(j * 3)) + String.valueOf(fil.charAt(j * 3 + 1));
                            }
                        }
                        
                        // Creacion del tablero con los datos leidos 
                        int dificultad = scanner.nextInt();
                        t1.setFilas(filastxt); 
                        t1.setColumnas(columnastxt);
                        t1.setFormaTabl(formaTablero);
                        t1.setNivel(dificultad);
                        
                        // Lee la dificultad
                        for(int i=0; i<dificultad; i++){
                            int movfil = scanner.nextInt();
                            int movcol = scanner.nextInt();
                            t1.AgregarNuevoMovDificultad("("+movfil+","+movcol+")");
                        }

                        // Muestra el tablero leido y lo agrega al historial
                        Vista.mostrarTablero(t1.getFormaTabl());
                        t1.agregarTableroHistorial(t1.getFormaTabl());
                        scanner.close();
                    
                   }
                   catch (FileNotFoundException e) {
                    e.printStackTrace();
                    }
                   
                    System.out.println("Para salir del juego en cualquiero momento ingrese 'X'");
                    System.out.println("para ver el historial de jugadas Ingrese 'H'");
                    System.out.println("Para ver una posible solucion Ingrese 'S'");
                break;
                
                case "b":
                    // Crea y muestra un tablero predefinido
                    Vista.mostrarTablero(t1.getFormaTabl());
                    t1.agregarTableroHistorial(t1.getFormaTabl());
                    t1.AgregarNuevoMov("(4,4)");
                    t1.AgregarNuevoMov("(5,6)");
                    t1.AgregarNuevoMov("(5,4)");
                    
                    System.out.println("Para salir del juego en cualquiero momento ingrese 'X'");
                    System.out.println("para ver el historial de jugadas Ingrese 'H'");
                    System.out.println("Para ver una posible solucion Ingrese 'S'");
                break;
                
                case "c":
                    // Solicita datos para crear un tablero aleatorio
                    System.out.println("Ingrese cantidad de filas");
                    int filas = input.nextInt();
                    
                    // Valida respuesta
                    while (filas > 9 || filas < 3){
                        System.out.println("Ingrese cantidad valida (entre 3 y 9)");
                        filas = input.nextInt();
                    }
                    
                    System.out.println("Ingrese cantidad de columnas");
                    int colum = input.nextInt();
                   
                    // Valida respuesta
                    while (colum > 9 || colum < 3){
                        System.out.println("Ingrese cantidad valida (entre 3 y 9)");
                        colum = input.nextInt();
                    }
                    
                    System.out.println("Ingrese dificultad");
                    int dif = input.nextInt();
                    
                    // Valida respuesta
                    while (dif > 8 || dif < 1){
                        System.out.println("Ingrese dificultad valida (entre 1 y 8)");
                        dif = input.nextInt();
                    
                    }
                    
                    // Crea tablero aleatorio con los datos ingresados y lo muestra
                    t1.setFilas(filas);
                    t1.setColumnas(colum);
                    t1.setNivel(dif);
                    t1.setFormaTabl(t1.generarTablero(filas, colum));
                    
                    System.out.println("Para salir del juego en cualquiero momento ingrese 'X'");
                    System.out.println("para ver el historial de jugadas Ingrese 'H'");
                    System.out.println("Para ver una posible solucion Ingrese 'S'");
                    
                    t1.agregarDificultad(dif);
                    Vista.mostrarTablero(t1.getFormaTabl());
                    t1.agregarTableroHistorial(t1.getFormaTabl());  
                break;
            }
            
            // Bucle para solicitar filas y columnas a cambiar
            boolean noGano = true;
            boolean apretoX = false;
            
            // Defino el timpo con el que inicio la partida 
            long tiempoInicio = System.currentTimeMillis();
            while(noGano && !apretoX){
                
                int fila = 0;
                boolean entradaValida = false;
                
                boolean aux = false;
                
                // Bucle para leer la entrada de fila
                while (!entradaValida) {
                    System.out.println("Ingrese fila a jugar: ");
                    String entrada = input.next();

                    // Chequea si la entrada es 'X' y sale del bucle
                    if (entrada.equals("X")) {
                        apretoX = true; 
                        break; 
                    }
                    
                    // Chequea si es una 'H' y muestra el historial
                    if (entrada.equals("H")) {
                        System.out.print("HISTORIAL DE JUGADAS:");
                        System.out.println();
                        Vista.verHistorialMovs(t1);
                    }
                    
                    // Chequea si es un 'S' y muestra una posible solucion
                    if (entrada.equals("S")){
                        System.out.print("POSIBLE JUGADA GANADORA:");
                        System.out.println();
                        Vista.MostrarSolucion(t1);
                        System.out.println();
                    }
                    
                    // Si no se ingreso 'X', 'H' o 'S' verifica si el dato es un int
                    try {
                        fila = Integer.parseInt(entrada);
                        if (fila >= -1 && fila < t1.getFilas()+1) {
                            entradaValida = true;
                        } else {
                            System.out.println("Ingrese una fila valida.");
                        }
                    // Si no es un int y no es 'H' o 'S' pide un dato valido
                    } catch (NumberFormatException e) {
                        if(!entrada.equals("H") && !entrada.equals("S")){
                            System.out.println("Dato incorrecto.");
                            input.nextLine();
                        }
                    }
                }
                
                // Verifica si se apreto 'X' y sale de la partida
                if (apretoX) {
                    break; 
                }
                    
                int columna = 0;
                entradaValida = false;

                // Bucle para leer la entrada de columna
                while (!entradaValida) {
                    System.out.println("Ingrese columna a jugar: ");
                    String entrada = input.next();

                    // Chequea si la entrada es 'X' y sale del bucle
                    if (entrada.equals("X")) {
                        apretoX = true; 
                        break; 
                    }
                    
                    // Chequea si es una 'H' y muestra el historial
                    if (entrada.equals("H")) {
                        System.out.print("HISTORIAL DE JUGADAS:");
                        System.out.println();
                        Vista.verHistorialMovs(t1);
                    }
                    
                    // Chequea si es un 'S' y muestra una posible solucion
                    if (entrada.equals("S")){
                        System.out.print("POSIBLE JUGADA GANADORA:");
                        System.out.println();
                        Vista.MostrarSolucion(t1);
                        System.out.println();
                    }

                    // Si no se ingreso 'X', 'H' o 'S' verifica si el dato es un int
                    try {
                        columna = Integer.parseInt(entrada);
                        if (columna >= -1 && columna < t1.getColumnas()+1) {
                            entradaValida = true;
                            t1.AgregarNuevoMov("("+fila+","+columna+")");
                        } else {
                            System.out.println("Ingrese una columna valida.");
                        }
                    // Si no es un int y no es 'H' o 'S' pide un dato valido
                    } catch (NumberFormatException e) {
                        if(!entrada.equals("H") && !entrada.equals("S")){
                            System.out.println("Dato incorrecto.");
                            input.nextLine();
                        }
                    }
                }
                
                // Verifica si se apreto 'X' y sale de la partida
                if (apretoX) {
                    break; 
                }
                
                // Verifica si la fila y la columna es -1
                if (fila == -1 && columna == -1){
                    // Si no hay tablero previo lo informa
                    if(t1.getHistTableros().size() == 1){
                        System.out.println("NO EXISTE TABLERO PREVIO");
                    }else{
                        // Si existe tablero previo elimina el ultimo y muestra el anterior
                        System.out.println("TABLERO PREVIO");
                        t1.eliminarUltimoTablero();
                        Vista.mostrarTablero(t1.getFormaTabl()); 
                    }
                // Si la fila y la columna no es -1
                }else{ 
                    // Cambia el color de las respectivas posiciones 
                    t1.cambiarColorPorCoordenada(fila-1, columna-1); 
                    // Crea un ArrayList con el historial de tableros
                    ArrayList<String[][]> Tableros = t1.getHistTableros(); 
                    // Muestra el tablero anterior y el actualizado 
                    Vista.mostrarTableroConCambio(Tableros.get((Tableros.size())-1),t1.getFormaTabl()); 
                    // Agrego el tablero al historial
                    t1.agregarTableroHistorial(t1.getFormaTabl());
                }
                // Valida si el jugador gano
                noGano=!Vista.validarGano(t1.getFormaTabl());
            }
            
            // Defino el tiempo en el que termino la partida
            long tiempoFin = System.currentTimeMillis();
            
            // Valida si el jugador Gano o Perido
            if(noGano){
                System.out.println("PERDISTE :(");
            }else{
                System.out.println("GANASTE!!! :)");
            }
            
            // Muestro el tiempo total de la partida
            long tiempoTranscurrido = tiempoFin - tiempoInicio;
            long segundos = tiempoTranscurrido / 1000;
            long minutos = segundos / 60;
            segundos = segundos % 60;
            System.out.println("La partida duro: " + minutos + " minutos con " + segundos + " segundos");
            
            input.nextLine();
            System.out.println("Gracias por jugar");  
            System.out.println("Desea jugar otra partida? SI/NO");
            String resp2=input.nextLine();
            
            // Valida respuesta
            while (!(resp2.equals("SI") || resp2.equals("NO"))){
                System.out.println("Ingrese respuesta valida SI/NO");
                resp2=input.nextLine();
            }

            if(resp2.equals("NO")){
                juega=false;
            }
        }   

        System.out.println("Gracias por jugar");      
    }   
}
        

