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
    private String username;
    private String password;
    private int tipo;
    private int estado;

    public Usuario(String username, String password, String tipo,String estado) {
        try {
        this.estado =Integer.valueOf(estado);
        this.username = username;
        this.password = password;
        this.tipo = Integer.valueOf(tipo);
        } catch (NumberFormatException e) {
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        try {
            this.tipo = Integer.valueOf(tipo);
        } catch (NumberFormatException e) {
        }
        
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        try {
            this.estado = Integer.valueOf(estado);
        } catch (NumberFormatException e) {
        }
    }

    
    
  
}
