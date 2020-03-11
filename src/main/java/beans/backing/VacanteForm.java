package beans.backing;

import beans.model.Candidato;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.faces.event.ActionEvent;
/*import beans.helper.FacesContextHelper;*/
import beans.helper.ProvinciaHelper;
import javax.faces.bean.ManagedProperty;

@RequestScoped
@Named
public class VacanteForm {

    Logger log = LogManager.getRootLogger();
    private boolean comentarioEnviado = false;

    @Inject
    private Candidato candidato;
    
    @Inject
    private ProvinciaHelper provinciaHelper;
    
    public VacanteForm() {
        log.info("Creando objeto VacanteForm");
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    //Metodo de flujo de control
    public String enviar() {

        System.out.println("enviar() Nombre=" + this.candidato.getNombre());
        System.out.println("enviar() Apellido=" + this.candidato.getApellido());
        System.out.println("enviar() Sueldo deseado" + this.candidato.getSueldoDeseado());

        if (this.candidato.getNombre().equals("Juan")) {

            if (this.candidato.getApellido().equals("Perez")) {
                String msg = "Gracias, pero Juan Perez ya trabaja con nosotros.";
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
                FacesContext facesContext = FacesContext.getCurrentInstance();
                String clientId = null; //Este es un mensaje global
                facesContext.addMessage(clientId, facesMessage);
                return "index";
            }
            return "exito";//exito.xhtml
        } else {
            return "fallo"; //fallo.xhtml
        }
    }

    //Metodo de tipo Value Change Listener
    //ES el Control y escucha el cambio en nuestro codigoPostal:
    //importamos UIINPUT;
    //importamos UIVIEWROOT;
    //importamos ValueChangeEvent;
   public void codigoPostalListener(ValueChangeEvent valueChangeEvent) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot uiViewRoot = facesContext.getViewRoot();
        Long newCodigoPostal =  (Long) valueChangeEvent.getNewValue();
        log.info("Nuevo codigo postal: " + newCodigoPostal);
        UIInput ciudadInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:ciudad");
        String ciudad = "Córdoba";
        ciudadInputText.setValue(ciudad);
        ciudadInputText.setSubmittedValue(ciudad);
        UIInput provinciaInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:provinciaId");
        //Buscamos la colonia por id con ayuda del bean coloniaHelper 
        Long provinciaId = this.provinciaHelper.getProvinciaIdPorCP(newCodigoPostal);
        provinciaInputText.setValue(provinciaId);
        provinciaInputText.setSubmittedValue(provinciaId);
        //Enviamos la respuesta
        facesContext.renderResponse();
    }

    public void ocultarComentario(ActionEvent actionEvent) {
        this.comentarioEnviado = !this.comentarioEnviado;
        log.info("Mostrando/ocultando el comentario");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        /*FacesContextHelper.limpiarImmediateFacesMessages(facesContext);*/
    }

    public boolean isComentarioEnviado() {
        return comentarioEnviado;
    }

    public void setComentarioEnviado(boolean comentarioEnviado) {
        this.comentarioEnviado = comentarioEnviado;
    }

    public ProvinciaHelper getColoniaHelper() {
        return provinciaHelper;
    }

    public void setProvinciaHelper(ProvinciaHelper provinciaHelper) {
        this.provinciaHelper = provinciaHelper;
    }
}