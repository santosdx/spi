package com.nerv.sai.modelo.local.administracion;



import com.nerv.sai.modelo.entidad.SistemaInfo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author santosdx
 */
@Local
public interface SistemaInfoFacadeLocal {

    void create(SistemaInfo sistemaInfo);

    void edit(SistemaInfo sistemaInfo);

    void remove(SistemaInfo sistemaInfo);

    SistemaInfo find(Object id);

    List<SistemaInfo> findAll();

    List<SistemaInfo> findRange(int[] range);

    int count();
    
    public SistemaInfo obtenerUltimaVersion();
    
}
