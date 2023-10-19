//Salvador Depons(303738) y Valentina Velazquez(305278)
package Dominio;
import java.util.*;

public class Tablero {
    
    //Define los atributos de Tablero
    private String[][] formaTabl;
    private int filas;
    private int columnas;
    private int nivel;
    private ArrayList<String[][]> histTableros;
    private ArrayList<String> histMovimientos;
    private ArrayList<String> movsDificultad; 
    public Tablero() {
        String[][] tab = {
            {"|A", "|A", "-R", "/A", "|R", "-R"},
            {"-R", "/A", "/A", "|A", "-R", "-R"},
            {"-R", "-R", "|A", "-R", "/R", "-R"},
            {"\\R", "-R", "|R", "\\R", "|A", "|R"},
            {"\\R", "/R", "/R", "|A", "/A", "\\A"}
        };
        this.setFormaTabl(tab);
        this.setFilas(5);
        this.setColumnas(6);
        this.setNivel(3);
        this.setHistTableros(new ArrayList<>());
        this.setHistMovimientos(new ArrayList<>());
        this.setMovsDificultad(new ArrayList<>());
    }
    
    // Metodos Set y Get de la forma del tablero
    public void setFormaTabl(String[][] unaForma) {
        formaTabl = unaForma;
    }

    public String[][] getFormaTabl() {
        return this.formaTabl;
    }
    
    // Metodos Set y Get de las filas
    public void setFilas(int cant){
        filas= cant;
    }
    public int getFilas(){
        return this.filas;
    }

    // Metodos Set y Get de las columnas
    public void setColumnas(int cant){
        columnas= cant;
    }
    public int getColumnas(){
        return this.columnas;
    }
   
    // Metodos Set y Get de la dificultad
    public void setNivel(int cant){
        nivel= cant;
    }
    
    public int getNivel(){
        return this.nivel;
    }
    
    // Metodos Set y Get del hitorial del tablero
    public void setHistTableros(ArrayList<String[][]> nuevoHist){
        this.histTableros = nuevoHist;
    }
    
    public ArrayList<String[][]> getHistTableros(){
        return this.histTableros;
    }
    
    // Metodos Set y Get de el historial de movimientos
    public void setHistMovimientos(ArrayList<String> nuevoMov){
        this.histMovimientos = nuevoMov;
    }
    
    public ArrayList<String> getHistMovimientos(){
        return this.histMovimientos;
    }
    
    // Metodos Set y Get de los movimientos aplicados para la dificultad
    public void setMovsDificultad(ArrayList<String> nuevoMov){
       this.movsDificultad = nuevoMov;
    } 
    
    public ArrayList<String> getMovsDificultad(){
        return this.movsDificultad;
    }
    
    // Metodo para agregar un nuevo movimiento de dificultad
    public void AgregarNuevoMovDificultad(String mov){
        this.movsDificultad.add(mov);
    }
    
    // Metodo para agregar un nuevo movimiento al tablero
    public void AgregarNuevoMov(String mov){
        this.histMovimientos.add(mov);
    }
    
    // Metodo para agregar un tablero al historial
    public void agregarTableroHistorial(String[][] formatab){
        
       // Defino una matriz del largo y ancho del tablero
       String[][] agregado= new String[this.getFormaTabl().length][this.getFormaTabl()[0].length];
       
       // Le asigno cada elemento al array que agrego
       for(int i=0; i<agregado.length; i++){
          for (int j=0; j<agregado[0].length; j++){
              agregado[i][j]=this.getFormaTabl()[i][j];
          }
       }
       
       // Agrego el tablero al historial
       this.getHistTableros().add(agregado);
    }
    
    // Metodo para eliminar el ultimo tablero del historial
    public void eliminarUltimoTablero(){
        
        // Defino aux como un alias de historial de tableros
        ArrayList<String[][]> aux = this.getHistTableros();
        int pos = aux.size()-1;
        this.setFormaTabl(aux.get(pos-1));
        
        // Le quito el ultimo tablero
        aux.remove(pos);
    }
    
    // Metodo para generar un tablero aleatorio
    public String[][] generarTablero(int fil,int col){
       Random random = new Random();

       // Le doy la opcion de ser Rojo o Azul de manera aleatoria
       int rojoOazul= random.nextInt(2) + 1;
       String[][] tab= new String[fil][col];
       
       // Defino cada elemeto del tablero de manera aleatoria
       for (int i=0; i<tab.length; i++){
           for (int j=0; j<tab[i].length; j++){
               
                // Por cada elemento del tablero randomizo una variable y segun el resultado le doy un simbolo
                int simbolo = random.nextInt(4) + 1;
                switch (simbolo) {
                    case 1:
                        tab[i][j]="/";
                        break;
                    case 2:
                        tab[i][j]="\\";
                        break;
                    case 3:
                        tab[i][j]="|";
                        break;
                    case 4:
                        tab[i][j]="-";
                        break;
                }
                
                // Defino dependiedo de la variable aleatoria el color inicial de la matriz
                if(rojoOazul==1){
                    tab[i][j]+="R";
                }
                else{
                    tab[i][j]+="A";
                }
            }
       }
       return tab;
    }
    
    // Metodo para agregar dificultad al tablero
    public void agregarDificultad(int dif){
       
        // Defino las Variables
        Random random = new Random();
        int[] solucionFila= new int[dif];
        int[] solucionColumna= new int[dif];
        String[] cordenadasCambiadas = new String[dif];

        // Genero fila y columna a cambiar de manera aleatoria
        for(int i=0; i< dif; i++){
            String fila = Integer.toString(random.nextInt(this.getFilas()));
            String columna = Integer.toString(random.nextInt(this.getColumnas()));
            
        // Verifico que la cordenada a cambiar no la haya cambiado antes     
        while(Arrays.asList(cordenadasCambiadas).contains(fila + columna)){
            fila = Integer.toString(random.nextInt(this.getFilas()));
            columna = Integer.toString(random.nextInt(this.getColumnas()));
        }
        cordenadasCambiadas[i] = fila + columna;

        }
        
        for (int i=0; i<dif; i++){
           int filAux = Integer.parseInt(cordenadasCambiadas[i].charAt(0)+"");
           int colAux= Integer.parseInt(cordenadasCambiadas[i].charAt(1)+"");
           
           // Gardo los movimientos realizados para agregar la dificultad 
           this.AgregarNuevoMovDificultad("("+(filAux+1)+","+(colAux+1)+")");
           
           // Cambio el color de los respectivos elementos de el tablero
           cambiarColorPorCoordenada(filAux, colAux);
       }
    }
    
    // Metodo para cambiar el color segun las cordenas dadas (fila, columna)
    public void cambiarColorPorCoordenada(int fila, int columna){
        
        // Defino una matriz con la forma del tablero
        String [][] mat = this.getFormaTabl();
        
        // Veo cual es el simbolo del elemento del tablero
        char tipo = mat[fila][columna].charAt(0);
        char simbolo;
        
        // Segun el simbolo cambio los respectivos elementos de color 
        switch (tipo){
            case '/':
                
                // La suma de las cordenadas deben ser iguales para que los elementos esten en diagonal hacia la derecha
                // Defino la suma de la cordenada dadas
                int suma = fila+columna;
                
                // Veo todos los elementos del tablero verificando si tienen la misma suma que las cordenadas dadas
                for(int i=0; i<mat.length; i++){
                    for(int j=0; j<mat[0].length; j++){
                        if (i+j == suma){
                            simbolo = mat[i][j].charAt(0);
                            
                            // Dependiendo de si el segundo caracter del elemento es R o A cambio el caracter al opuesto
                            if (mat[i][j].charAt(1) == 'A'){
                                mat[i][j] = simbolo+"R";
                            }else{
                                mat[i][j]=simbolo+"A";  
                            }
                        }
                    }
                }
            break;
            
            case '\\':
                
                // La diferecia de las cordenadas deben ser iguales para que los elementos esten en diagonal hacia la izquierda
                // Definio la diferencia de las cordenadas dadas
                int dif = fila-columna;
                
                // Veo todos los elementos del tablero verificando si tienen la misma diferencia que las cordenadas dadas
                for(int i=0; i<mat.length; i++){
                    for(int j=0; j<mat[0].length; j++){
                        if (i-j == dif){
                            simbolo = mat[i][j].charAt(0);
                            
                            // Dependiendo de si el segundo caracter del elemento es R o A cambio el caracter al opuesto
                            if (mat[i][j].charAt(1) == 'A'){
                                mat[i][j] = simbolo+"R";
                            }else{
                                mat[i][j]=simbolo+"A";  
                            }
                        }
                    }
                }
            break;
            
            case '|':
                
                // Si el elemento comparte la misma columna que la dada, cambio el caracter que define el color al opuesto
                for(int i=0; i<mat.length; i++){
                    for(int j=0; j<mat[0].length; j++){
                        if (j == columna){
                            simbolo = mat[i][j].charAt(0);
                            if (mat[i][j].charAt(1) == 'A'){
                                mat[i][j] = simbolo+"R";
                            }else{
                                mat[i][j]= simbolo+"A";  
                            }
                        }
                    }
                }
            break;
            
            case '-':
                // Si el elemento comparte la misma fila que la dada, cambio el caracter que define el color al opuesto
                for(int i=0; i<mat.length; i++){
                    for(int j=0; j<mat[0].length; j++){
                        if (i == fila){
                            simbolo = mat[i][j].charAt(0);
                            if (mat[i][j].charAt(1) == 'A'){
                                mat[i][j] = simbolo+"R";
                            }else{
                                mat[i][j]= simbolo+"A";  
                            }
                        }
                    }
                }
            break;     
        }
    }
}

