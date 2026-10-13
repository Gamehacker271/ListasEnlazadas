public class ListaEnlazada {

    // ===== CLASE INTERNA NODO =====
    // Representa cada elemento de la lista. Queda encapsulada para seguridad.
    private static class Nodo {
        int dato;
        Nodo siguiente; // Auto-referencia para crear la cadena

        Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    // ===== ATRIBUTO PRINCIPAL =====
    private Nodo cabeza; // Puntero al primer elemento de la lista

    public ListaEnlazada() {
        this.cabeza = null;
    }

    // O(1) - Tiempo constante
    public boolean estaVacia() {
        return cabeza == null;
    }

    // O(1) - Inserción súper rápida, solo cambia referencias
    public void insertarAlInicio(int dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }

    // O(n) - Requiere recorrer toda la lista hasta el último nodo
    public void insertarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }
        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = nuevo;
    }

    // O(n) - Búsqueda lineal
    public boolean buscar(int dato) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.dato == dato) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    // O(n) - Elimina reasignando el puntero del nodo anterior
    public boolean eliminar(int dato) {
        if (cabeza == null) return false;

        // Caso especial: el dato está en la cabeza
        if (cabeza.dato == dato) {
            cabeza = cabeza.siguiente;
            return true;
        }

        Nodo anterior = cabeza;
        Nodo actual = cabeza.siguiente;

        while (actual != null) {
            if (actual.dato == dato) {
                anterior.siguiente = actual.siguiente; // El Garbage Collector elimina el nodo huérfano
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    // O(n) - Cuenta los elementos
    public int contar() {
        int contador = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }

    // O(n) - Simula el acceso por índice de un Array
    public int obtener(int indice) {
        if (indice < 0) throw new IndexOutOfBoundsException("Índice negativo");
        Nodo actual = cabeza;
        int posicion = 0;
        while (actual != null) {
            if (posicion == indice) return actual.dato;
            actual = actual.siguiente;
            posicion++;
        }
        throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
    }

    // Método de depuración
    public void imprimir() {
        if (cabeza == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato);
            if (actual.siguiente != null) System.out.print(" → ");
            actual = actual.siguiente;
        }
        System.out.println();
    }
}