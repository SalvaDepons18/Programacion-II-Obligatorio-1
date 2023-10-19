//Salvador Depons(303738) y Valentina Velazquez(305278)
package Dominio;
import java.util.*;

public class Vista {
    
    // Metodo para mostrar un tablero 
    public static void mostrarTablero(String[][] mat){
        
        // Defino una variable auxiliar
        String linea="  +";
        
        // Muestro numero de columna
        System.out.print("    1");
        
        // Muestro los numeros que faltan de las columnas
        for(int i=2; i<=mat[0].length; i++){
            System.out.print("   "+i);
        }
        
        // Redefino la Variable que sirve de marco para el tablero segun el largo
        for(int i=0; i<mat[0].length; i++){
            linea+="---+";
        }
        
        // Muestro una separacion y luego el marco superior previamente definido
        System.out.println();
        System.out.println(linea);
        
        for (int i=0; i<mat.length ;i++){
            
            // Muestro el numero de fila seguido del marco izquierdo
            System.out.print((i+1)+" | ");
            
            // Por cada elemento de la fila
            for (int j=0; j<mat[i].length; j++){
                
                // Dependiendo del 2do caracter envio a un metodo auxiliar, cuya funcion es mostrar el elemento con su respectivo color
                if ((mat[i][j].charAt(mat[i][j].length()-1)) == 'R'){
                    colorRojo(mat[i][j].charAt(0));  
                }else{
                    colorAzul(mat[i][j].charAt(0));   
                }
                
                // Muestro el marco derecho
                System.out.print(" | ");  
            }
            
            // Muestro una separacion y el marco inferior
            System.out.println();
            System.out.println(linea);
        }
    } 
    
    // Metodo para mostar el tablero anterior y a su lado el tablero nuevo
    public static void mostrarTableroConCambio(String[][] matAnterior, String[][] matNueva){
        
        // Defino variable auxiliar que me servira de marco 
        String linea="  +";
        
        // Muestro primer numero de columna
        System.out.print("    1");
        
        // Muestro el resto de los numeros de las columnas del primer tablero
        for(int i=2; i<=matAnterior[0].length; i++){
            System.out.print("   "+i);
        } 
        
        // Muestro los numeros de las columnas del segundo tablero
        System.out.print("            1");
        for(int i=2; i<=matAnterior[0].length; i++){
            System.out.print("   "+i);
        }
        System.out.println();
        
        // Redefino la variable linea que me servira de marco
        for(int i=0; i<matAnterior[0].length; i++){
            linea+="---+";
        }
        
        // Muestro el marco del primer tablero seguido de una flecha y luego el marco del segundo tablero
        System.out.print(linea+"  ==>  "+linea);
        System.out.println();
        
        for (int i=0; i<matAnterior.length ;i++){
            
            // Por cada fila muestro el numero correspondiente seguido de un '|' que me sirve de marco
            System.out.print((i+1)+" | ");
            for (int j=0; j<matAnterior[i].length; j++){
                
                // Por cada elemento del primer tablero muestro el simbolo con su color correspondiente, mediante un metodo auxiliar
                if ((matAnterior[i][j].charAt(matAnterior[i][j].length()-1)) == 'R'){
                    colorRojo(matAnterior[i][j].charAt(0));
                }else{
                    colorAzul(matAnterior[i][j].charAt(0));
                }
                
                // Muestro Marco derechod del primer tablero
                System.out.print(" | ");
            }
            
            // Muestro una flecha seguido del numero de fila correspondiente y luego un marco 
            System.out.print(" ==>  "+(i+1)+" | ");
            
            // Por cada elemento del segundo tablero muestro el simbolo con su color correspondiente, mediante un metodo auxiliar
            for (int j=0; j<matNueva[i].length; j++){
                if ((matNueva[i][j].charAt(matNueva[i][j].length()-1)) == 'R'){
                    colorRojo(matNueva[i][j].charAt(0));
                }else{
                    colorAzul(matNueva[i][j].charAt(0));
                }
                
                // Muestro un marco derecho
                System.out.print(" | ");
            }
            
            // Muestro el marco inferior del primer tablero seguido de una flecha y el marco inferior del segundo tablero
            System.out.println();
            System.out.println(linea+"  ==>  "+linea);
        }
    }
    
    // Metodo que Muestra el simbolo dado con el color rojo
    public static void colorRojo(char letra){
        System.out.print("\u001B[31m"); // (color rojo)
        System.out.print(letra);
        System.out.print("\u001B[0m"); // Restablecer a color predeterminado
    }
    
    // Metodo que Muestra el simbolo dado con el color azul
    public static void colorAzul(char letra){
        System.out.print("\u001B[34m"); // (color azul)
        System.out.print(letra);
        System.out.print("\u001B[0m"); // Restablecer a color predeterminado
    }

    // Metodo para verificar si todos los elementos de un tablero son del mismo color
    public static boolean validarGano(String[][] tab){
      
      // Defino el color de el primer elemento 
      char color = tab[0][0].charAt(1);
      boolean gano = true;
      
      // Verifico si todos los elementos son del color definido
      for(int i=0; i<tab.length && gano; i++){
          for(int j=0; j<tab.length && gano; j++){
              if(tab[i][j].charAt(1)!=color){
                 gano = false;
              }
          }
      }
      
      return gano;
    }
    
    // Metodo que muestra el historial de movimientos realizados
    public static void verHistorialMovs(Tablero t){
        
        // Valida que existan movimientos
        if (t.getHistMovimientos().size() == 0){
            System.out.println("No se han realizado movimentos");
        }else{
            
            // Muestra cada movimiento en el formato deseado
            for (int i=0; i<t.getHistMovimientos().size(); i++){
                if(i == t.getHistMovimientos().size()){
                    System.out.print(t.getHistMovimientos().get(i));
                }else{
                    System.out.print(t.getHistMovimientos().get(i)+",");
                }
            }
            System.out.println();
        }
    }
   
    // Metodo que muestra una posible solucion
    public static void MostrarSolucion(Tablero t) {
        
        // Defino un ArrayList para guardar los movimientos de la solucion
        ArrayList<String> aux = new ArrayList<>();
        
        // Defino el Array aux con los mismos elementos que el historial de movimientos
        for (int i=0; i<t.getHistMovimientos().size(); i++) {
            aux.add(t.getHistMovimientos().get(i)); 
        }

        // Defino un indice que mientras el indice sea menor al tamaño de aux el bucle continua
        int i=0;
        while (i<aux.size()){
            
            // Si el elemento de aux es "(-1,-1)" lo quita y el anterior elemento tambien
            if (aux.get(i).equals("(-1,-1)")){
                aux.remove(i); 

                if (i > 0){
                    aux.remove(i - 1); 
                    i--; 
                }
            } else{
                // Incremento el indice 
                i++;
            }
        }
        
        // Muestro los elementos de aux restantes luego del filtro, en orden inverso
        for (int j = aux.size() - 1; j >= 0; j--){
            System.out.print(aux.get(j) + ",");
        }
        
        // Muestro los movimientos realizados para la dificultad en el orden contrario
        for (int j=t.getMovsDificultad().size()-1; j>=0; j--){
            if (j == 0){
                System.out.print(t.getMovsDificultad().get(j));
            }else{
                System.out.print(t.getMovsDificultad().get(j) + ",");
            }
        }
    }
}