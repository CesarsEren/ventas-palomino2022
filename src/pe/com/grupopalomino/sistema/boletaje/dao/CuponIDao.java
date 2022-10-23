/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.grupopalomino.sistema.boletaje.dao;

import pe.com.grupopalomino.sistema.boletaje.bean.Cupon;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class CuponIDao extends MapperSQL implements B_CuponDao {

	private static final Log log = LogFactory.getLog(CuponIDao.class);

	@Override
	public Cupon validarcupon(Map<String, Object> parametros) {
		SqlSession sqlsesion = sqlMapper.openSession();
		Cupon rpta = new Cupon();
		try {
			rpta = (Cupon) sqlsesion.selectOne(ProjectStr + "SQL_VALIDAR_CUPON", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			sqlsesion.close();
		}
		return rpta;
	}
	/* /public List<V_Rutas> */
/*
	public static void main(String[] args) {
		Map<String,Object> parameteres = new HashMap<>();
		parameteres.put("codigocupon", "IDA15");
		parameteres.put("paracupon", "IDA");
		parameteres.put("fechaida", "2019-05-20");
		parameteres.put("fecharetorno", "");
		//#{codigocupon},#{paracupon},#{fechaida},#{fecharetorno}
		Cupon x = new CuponIDao().validarcupon(parameteres);
		log.info(x.toString());
		// System.out.println(x.toString());
	}
*/
}
