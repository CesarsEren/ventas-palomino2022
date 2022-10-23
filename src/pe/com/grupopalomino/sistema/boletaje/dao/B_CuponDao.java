
package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.Map;

import pe.com.grupopalomino.sistema.boletaje.bean.Cupon;

public interface B_CuponDao {

    public abstract Cupon validarcupon(Map<String, Object> codigocupon);

}
