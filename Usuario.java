public abstract class Usuario
{
    public String nombre;
    public String id;
    public String edad;
    public String carrera;
    public boolean cuenta=false;

    // Constructor
    public Usuario(String nombre, String id, String edad, String carrera) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.carrera = carrera;
    }

    // Métodos concretos (ya implementados)
    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getEdad() {
        return edad;
    }
    
    public String getCarrera() {
        return carrera;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    public void setEdad(String edad) {
        this.edad = edad;
    }
    
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    public void setCuenta(Boolean cuenta) {
        this.cuenta = cuenta;
    }
    
    // Método opcional para mostrar información del usuario
    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("ID: " + id);
        System.out.println("Carrera: " + carrera);
    }
}

