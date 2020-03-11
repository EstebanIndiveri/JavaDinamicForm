/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.dto;

/**
 *
 * @author estel
 */
public class Provincia {
    private long provinciaId;
    private String nombreProvincia;
    private long codigoPostal;
    
    public Provincia(long provinciaId,String nombreProvincia,long codigoPostal){
        this.provinciaId=provinciaId;
        this.nombreProvincia=nombreProvincia;
        this.codigoPostal=codigoPostal;
        
    }
    public long getProvinciaId(){
        return provinciaId;
    }
    public void setProvinciaId(long provinciaId){
        this.provinciaId=provinciaId;
    }
    public String getNombreProvincia(){
        return nombreProvincia;
    }
    public void setNombreProvincia(String nombreProvincia){
        this.nombreProvincia=nombreProvincia;
    }
    public long getCodigoPostal(){
        return codigoPostal;
    }
    public void setCodigoPostal(long codigoPostal){
        this.codigoPostal=codigoPostal;
    }
}
