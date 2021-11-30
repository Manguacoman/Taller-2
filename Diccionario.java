import java.util.InputMismatchException;
import java.util.Scanner;

public class Diccionario {
    public static void main(String[] args) throws Exception {
        
      Tree diccionario = new Tree();
      Node nodo = new Node("","","");
      int opcion=0;
      String palabra;
      String definicion;
      String clasificacion;
      boolean salir = false; 
      Scanner sc = new Scanner(System.in);
      /*Bucle que permite repetir el menu*/
      while (!salir) {
          System.out.print("Menu \n 1. Buscar  \n 2. Insertar  \n 3. Eliminar \n 4. Mostrar \n 5. Terminar ");
          System.out.print("Ingrese una opcion: ");
          try {
            opcion = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.print(e.getMessage()); //try to find out specific reason.
        }
          
          /*Switch con cada uno de los casos para las diferentes opciones del menu*/
          switch (opcion) {
              case 1:
                  System.out.print("Ingrese palabra a buscar. \n");
                  palabra = sc.next();
                  nodo = diccionario.find(palabra);

                  if(!nodo.nombre.equals(palabra)){
                     System.out.print("No se encuentra la palabra. \n");
                  }
                  else{
                     nodo.displayNode();
                  }  
                  break;
              case 2:
                  System.out.print("Ingrese palabra, deficion y clasificacion a insertar. \n");
                  System.out.print("palabra:");
                  palabra = sc.next();
                  System.out.print("deficion:");
                  definicion = sc.next();
                  System.out.print("clasificion:");
                  clasificacion = sc.next();
                  diccionario.insert(palabra, definicion,clasificacion);
            

                  break;
              case 3:
                  System.out.print("Ingrese palabra a eliminar. \n");
                  palabra = sc.next();
                  diccionario.delete(palabra);  
                  break;
              case 4:
                  System.out.print("Diccionario. \n");
                  diccionario.inOrder(diccionario.root); 
                  diccionario.ordenaTodo();

                  break;
              case 5:
                  System.out.print("Diccionario cerrado. \n");
                  salir = true;
                  break;        
                  
              default:
                  System.out.print("Opcion no valida!");
          }
      }
     
      }  


    }
