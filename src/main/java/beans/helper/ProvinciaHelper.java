package beans.helper;

import domain.dto.Provincia;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;

@ManagedBean
@RequestScoped
@Named
public class ProvinciaHelper {

    public List<Provincia> getProvincia() {
        List<Provincia> provincias = new ArrayList<>();
        long provinciaId = 1;
        Provincia provincia = new Provincia(provinciaId++, "Córdoba", 5000);//no anteponer el cero, sino es un valor octal
        provincias.add(provincia);
        provincia = new Provincia(provinciaId++, "Mendoza", 6000);
        provincias.add(provincia);
        provincia = new Provincia(provinciaId++, "Buenos Aires", 2000);
        provincias.add(provincia);
        return provincias;
    }

    public long getProvinciaIdPorNombre(String nombreProvincia) {
        long provinciaId = 0;
        List<Provincia> provincias = getProvincia();//Lista de colonias
        for (Provincia provincia : provincias) {
            if (provincia.getNombreProvincia().equals(nombreProvincia)) {
                provinciaId = provincia.getProvinciaId();
                break;
            }
        }
        return provinciaId;
    }

    public long getProvinciaIdPorCP(long codigoPostal) {
        long provinciaId = 0;
        List<Provincia> provincias = getProvincia();
        for (Provincia provincia : provincias) {
            if (provincia.getCodigoPostal() == codigoPostal) {
                provinciaId = provincia.getProvinciaId();
                break;
            }
        }
        return provinciaId;
    }

    public List<SelectItem> getSelectItems() {
        List<SelectItem> selectItems;
        selectItems = new ArrayList<>();
        List<Provincia> provincias = getProvincia();
        for (Provincia provincia : provincias) {
            SelectItem selectItem = new SelectItem(provincia.getProvinciaId(),
                    provincia.getNombreProvincia());
            selectItems.add(selectItem);
        }
        return selectItems;
    }
}