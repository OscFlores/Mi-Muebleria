/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Clases;

/**
 *
 * @author Oscar
 */
public class Usuario {
    private String Nombre_Usuario;
    private String Password;
    private int Tipo;
    
    public Usuario(){}

    public Usuario(String Nombre_Usuario) {
        this.Nombre_Usuario = Nombre_Usuario;
    }

    public Usuario(int Tipo) {
        this.Tipo = Tipo;
    }

    public Usuario(String Nombre_Usuario, int Tipo) {
        this.Nombre_Usuario = Nombre_Usuario;
        this.Tipo = Tipo;
    }

    public Usuario(String Nombre_Usuario, String Password, int Tipo) {
        this.Nombre_Usuario = Nombre_Usuario;
        this.Password = Password;
        this.Tipo = Tipo;
    }

    public String getNombre_Usuario() {
        return Nombre_Usuario;
    }

    public String getPassword() {
        return Password;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setNombre_Usuario(String Nombre_Usuario) {
        this.Nombre_Usuario = Nombre_Usuario;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setTipo(int Tipo) {
        this.Tipo = Tipo;
    }
    
}
