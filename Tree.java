// tree.java
// demonstrates binary tree
// to run this program: C>java TreeApp
import java.io.*;
import java.util.*;               // for Stack class
////////////////////////////////////////////////////////////////
class Node
   {
   public String nombre;              // data item (key)
   public String definicion;
   public String clasificacion;           // data item
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child

   public Node(String nombre,String definicion, String clasificacion){
      this.nombre = nombre;
      this.definicion = definicion;
      this.clasificacion = clasificacion;

   }
   
   public void displayNode()      // display ourself
      {
      System.out.print('{');
      System.out.print(nombre);
      System.out.print(", ");
      System.out.print(definicion);
      System.out.print(", ");
      System.out.print(clasificacion);
      System.out.print("} ");
      }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getDefinicion() {
      return definicion;
   }

   public void setDefinicion(String definicion) {
      this.definicion = definicion;
   }

   public String getClasificacion() {
      return clasificacion;
   }

   public void setClasificacion(String clasificacion) {
      this.clasificacion = clasificacion;
   }

   public Node getLeftChild() {
      return leftChild;
   }

   public void setLeftChild(Node leftChild) {
      this.leftChild = leftChild;
   }

   public Node getRightChild() {
      return rightChild;
   }

   public void setRightChild(Node rightChild) {
      this.rightChild = rightChild;
   }



      
   }  // end class Node
////////////////////////////////////////////////////////////////
class Tree
   {
   public Node root = new Node ("Root","Root","Root");
   Node temp2;             // first node of tree
   static char lor = 'l';
   static int contadorGeneral = 0;
   List<Node> todo= new ArrayList<Node>();
// -------------------------------------------------------------
   public Tree()                  // constructor
      { 

        insert("Apotropaico", "Dicho de un rito, de un sacrificio, de una fórmula, etc.: Que, por su carácter mágico, se cree que aleja el mal o propicia el bien.", "verbo");
        insert("Bienquisto", "De buena fama y generalmente estimado", "advjetivo");
        insert("Cerúleo", "Dicho del color azul: Propio del cielo despejado, o de la alta mar o de los grandes lagos.", "advjetivo");
        insert("Decumbente", "Persona que yace en la cama o la guarda por enfermedad. Llegó tarde al colegio porque estaba decumbente y se quedó albanado (dormido).", "adjetivo");
        insert("Egresar", "Salir de alguna parte. Aunque todos ingresamos de vez en cuando en algún sitio, no nos atrevemos a egresar y preferimos simplemente salir.", "verbo");
        insert("Filis", " Habilidad, gracia y delicadeza en hacer o decir las cosas.", "adjetivo");
        insert("Hebdómada", "Espacio de siete años. Las setenta hebdómadas de Daniel. También, semana.", "adjetivo");
      }            // no nodes in tree yet
// -------------------------------------------------------------
   public Node find(String nom)      // find node with given key
      {                           // (assumes non-empty tree)
      Node temp = root;

      String posicion1 = temp.nombre.toLowerCase();
      String posicion2 = nom.toLowerCase();
                 
      boolean fin = true;
      while(!posicion1.equals(posicion2) && fin)        // while no match,
         {
           
           String nombre1 = temp.nombre.toLowerCase();
           String nombre2 = nom.toLowerCase();    

           char[] nom1 = nombre1.toCharArray();
           char[] nom2 = nombre2.toCharArray();
           
           char[] comparacion1;
           char[] comparacion2;
           
           int key;

           boolean condicion = true;
           if (nom1.length < nom2.length){
               comparacion1 = nom1;
               comparacion2 = nom2;    
               key = 0;
            }else{
               comparacion1 = nom2;
               comparacion2 = nom1;
               key = 1;
            } 
            
            int contador = 0;

            while (condicion && contador < comparacion1.length){
               int caracter1 = (int) comparacion1[contador];
               int caracter2 = (int) comparacion2[contador];
              
               
               if (caracter1 < caracter2){
                  temp2 = temp;
                  temp = temp.leftChild;
                  lor = 'l';
                  condicion = false;   

               }else if (caracter1 > caracter2){
                  temp2 = temp;  
                  temp = temp.rightChild;
                  lor = 'r';
                  condicion = false; 
               }else{
                  contador++;
               }
                  
            } 
            
            if (contador == comparacion1.length){
                
               if (lor == 'l'){
                  temp = temp.leftChild;
               
               }else{
                  temp = temp.rightChild;
                  lor = 'r';
               };

                  
            }

            try{
               if (temp.nombre == null){
               }else{
   
                  posicion1 = temp.nombre;
               }
            }catch(Exception e){
               temp = temp2;
               fin = false;
            }
            
                       // didn't find it
         }
      return temp;                    // found it
      }  // end find()
// -------------------------------------------------------------
   public void insert(String pal, String def, String clas)
      {  
         pal = pal.toLowerCase();
         Node lugar_insertar = find(pal);

         if(lor == 'l'){
            if (lugar_insertar.nombre.equals(pal)){
               System.out.println("la palabra existe");
            }else{
            lugar_insertar.leftChild = new Node(pal,def,clas);
            contadorGeneral++;    
            System.out.println("La palabra fue insertada en el diccionario");
            }
       
             
         }else if(lor == 'r'){
            if (lugar_insertar.nombre.equals(pal)){
               System.out.println("la palabra existe");
            }else{
            lugar_insertar.rightChild = new Node(pal, def, clas);
            contadorGeneral++;
            System.out.println("La palabra fue insertada en el diccionario");
            }
           
           
         }

         // fou
      }  // end insert()
// -------------------------------------------------------------
   public boolean delete(String pal) // delete node with given key
      {                           // (assumes non-empty list)
         boolean resultado;
         pal = pal.toLowerCase();
         boolean isLeftChild = true;
         Node parent = root;
         Node lugar_eliminar = find(pal);
   
     

            // if no children, simply delete it
      if(lugar_eliminar.leftChild==null && lugar_eliminar.rightChild==null)
         {

            lugar_eliminar.nombre = " ";
            lugar_eliminar.definicion = " ";
            lugar_eliminar.clasificacion = " ";

            System.out.println("la palabra fue eliminada");
            
         }

      // if no right child, replace with left subtree
      else if(lugar_eliminar.rightChild==null)
         if(lugar_eliminar == root)
            root = lugar_eliminar.leftChild;
         else if(isLeftChild)
            parent.leftChild = lugar_eliminar.leftChild;
         else
            parent.rightChild = lugar_eliminar.leftChild;

      // if no left child, replace with right subtree
      else if(lugar_eliminar.leftChild==null)
         if(lugar_eliminar == root)
            root = lugar_eliminar.rightChild;
         else if(isLeftChild)
            parent.leftChild = lugar_eliminar.rightChild;
         else
            parent.rightChild = lugar_eliminar.rightChild;

      else  // two children, so replace with inorder successor
         {
         // get successor of node to delete (current)
         Node successor = getSuccessor(lugar_eliminar);

         // connect parent of current to successor instead
         if(lugar_eliminar == root)
            root = successor;
         else if(isLeftChild)
            parent.leftChild = successor;
         else
            parent.rightChild = successor;

         // connect successor to current's left child
         successor.leftChild = lugar_eliminar.leftChild;
         }  // end else two children
      // (successor cannot have a left child)
      contadorGeneral--;
      return true;                                // s
        
                              // success
      }  // end delete()
// -------------------------------------------------------------
// -------------------------------------------------------------
   public void inOrder(Node localRoot)
      {

      if(localRoot != null)
         {
         inOrder(localRoot.leftChild);
         todo.add(localRoot);
         inOrder(localRoot.rightChild);
         }

     }

     public void  ordenaTodo(){
     
      Collections.sort(todo, new Comparator<Node>() {
         public int compare(Node obj1, Node obj2) {
            return obj1.getNombre().compareTo(obj2.getNombre());
         }
      });
            
      for(Node temp: todo){
         
         if (temp.getNombre()!= " "){
         System.out.println(temp.getNombre());
         }
     }
     todo = new ArrayList<Node>();  
   }
// -------------------------------------------------------------


   private Node getSuccessor(Node delNode)
      {
      Node successorParent = delNode;
      Node successor = delNode;
      Node current = delNode.rightChild;   // go to right child
      while(current != null)               // until no more
         {                                 // left children,
         successorParent = successor;
         successor = current;
         current = current.leftChild;      // go to left child
         }
                                           // if successor not
      if(successor != delNode.rightChild)  // right child,
         {                                 // make connections
         successorParent.leftChild = successor.rightChild;
         successor.rightChild = delNode.rightChild;
         }
      return successor;
      }

   }
