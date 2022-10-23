package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import pe.com.grupopalomino.sistema.boletaje.bean.V_Cliente_SMS_Web;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityClient;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_Usuarios_WebIDao extends MapperSQL implements V_Usuarios_WebDao{

	private static final Log log = LogFactory.getLog(V_Usuarios_WebIDao.class);
	
	@Override
	public SpringSecurityUser loginUsuario(String username) throws Exception {
		SqlSession session = sqlMapper.openSession();
		SpringSecurityUser bean = null;
		
		try {
			bean = (SpringSecurityUser) session.selectOne(ProjectStr+"SQL_login_usuario_web", username);
			//log.info("CONTRASEÑA ENCRYPTADA "+bean.getPassword());
			//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
			
			//log.info("ENCODER "+encoder.decode(bean.getPassword()));
			//encoder.encode(arg0)
		} catch (Exception e) {
			session.rollback();
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		
		return bean;
	}
	
	@Override
	public SpringSecurityUser cambiaEstadoUsuario(SpringSecurityUser bean) throws Exception {
		SqlSession session = sqlMapper.openSession();
		
		try {
			session.update(ProjectStr+"SQL_activa_desactiva_usuarios", bean.getId());
			session.commit();
		} catch (Exception e) {
			session.rollback();
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		return bean;
	}

	@Override 
	public SpringSecurityUser actualizaUsuario(SpringSecurityUser bean) throws Exception {
		SqlSession session = sqlMapper.openSession();
		
		try {
			
			if(bean.getPassword() != null && !bean.getPassword().trim().isEmpty()){
				PasswordEncoder encoder = new BCryptPasswordEncoder();
				bean.setPassword(encoder.encode(bean.getPassword()));
			}
			
		/*	bean.setApellidoMaterno(bean.getApellidoMaterno().toUpperCase());
			bean.setApellidoPaterno(bean.getApellidoPaterno().toUpperCase());
			bean.setRazonSocial(bean.getRazonSocial().toUpperCase()); */
			
			//bean.setUsername(bean.getNombres().charAt(0)+bean.getApellidoPaterno().replace(" ", "")+bean.getApellidoMaterno().charAt(0));
		//	bean.setNombreCompleto(bean.getNombres()+" "+bean.getApellidoPaterno()+" "+bean.getApellidoMaterno());
			
			
			session.update(ProjectStr+"SQL_update_usuario_web", bean);
			session.commit();
			
		} catch (Exception e) {
			session.rollback();
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return bean;
	}

	@Override
	public SpringSecurityUser nuevoUsuario(SpringSecurityUser bean) throws Exception {
		SqlSession session = sqlMapper.openSession();
		
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		bean.setPassword(encoder.encode(bean.getPassword()));
		
		//bean.setUsername(bean.getNombres().charAt(0)+bean.getApellidoPaterno().replace(" ", "")+bean.getApellidoMaterno().charAt(0));
		/*bean.setApellidoMaterno(bean.getApellidoMaterno().toUpperCase());
		bean.setApellidoPaterno(bean.getApellidoPaterno().toUpperCase());
		bean.setRazonSocial(bean.getRazonSocial().toUpperCase());*/
		//bean.setNombreCompleto(bean.getNombres().toUpperCase()+" "+bean.getApellidoPaterno().toUpperCase()+" "+bean.getApellidoMaterno().toUpperCase());
		
		try {
			session.insert(ProjectStr+"SQL_insert_empleado", bean);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		
		return bean;
	}


	@Override
	public SpringSecurityUser nuevoUsuarioTransporteDePersonal(SpringSecurityUser bean) throws Exception {
		SqlSession session = sqlMapper.openSession();
		
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		bean.setPassword(encoder.encode(bean.getPassword())); 
		try {
			session.insert(ProjectStr+"SQL_insert_usuarios_transporte_de_personal", bean);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		
		return bean;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SpringSecurityUser> listaUsuarios(String query, Integer limit, Integer offset) throws Exception {
		SqlSession session = sqlMapper.openSession();
		List<SpringSecurityUser> listaUsuarios = new ArrayList<SpringSecurityUser>();
		
		Map<String, Integer> parametros = new HashMap<String, Integer>();
		parametros.put("offset", offset);
		parametros.put("limit", limit);
		
		try {
			if(query == null || query.trim().isEmpty()){
				listaUsuarios = session.selectList(ProjectStr+"SQL_list_usuarios_web", parametros);
			}
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return listaUsuarios;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SpringSecurityUser> listaUsuariosTransporteDePersonal(String query, Integer limit, Integer offset) throws Exception {
		SqlSession session = sqlMapper.openSession();
		List<SpringSecurityUser> listaUsuarios = new ArrayList<SpringSecurityUser>();
		
		Map<String, Integer> parametros = new HashMap<String, Integer>();
		parametros.put("offset", offset);
		parametros.put("limit", limit);
		
		try {
			if(query == null || query.trim().isEmpty()){
				listaUsuarios = session.selectList(ProjectStr+"SQL_list_usuarios_web_transporte_de_personal", parametros);
			}
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return listaUsuarios;
	} 
	
	@Override
	public int cuentaUsuariosTotales() throws Exception {
		SqlSession session = sqlMapper.openSession();
		int usuariosTotales = 0;
		
		try {
			usuariosTotales = (int) session.selectOne(ProjectStr+"SQL_list_count_usuarios_web");
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return usuariosTotales;
	}
	
	@Override
	public int cuentaUsuariosTotalesTransporteDePersonal() throws Exception {
		SqlSession session = sqlMapper.openSession();
		int usuariosTotales = 0;
		
		try {
			usuariosTotales = (int) session.selectOne(ProjectStr+"SQL_list_count_usuarios_web_transporte_de_personal");
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return usuariosTotales;
	}
	
	
	@Override
	public SpringSecurityUser obtieneUsuarioXid(int id) throws Exception {
		SqlSession session = sqlMapper.openSession();
		SpringSecurityUser usuario = null;
		
		try {
			usuario = (SpringSecurityUser) session.selectOne(ProjectStr+"SQL_list_usuario_x_id_usuarios_web", id);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return usuario;
	}
	
	@Override
	public SpringSecurityClient loginClienteSMS(String codigo) throws Exception {
		SqlSession session = sqlMapper.openSession();
		SpringSecurityClient cliente = null;
		
		try {
			cliente = (SpringSecurityClient) session.selectOne(ProjectStr+"SQL_login_cliente_web", codigo);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return cliente;
	}
	
	@Override
	public SpringSecurityClient nuevoClienteSMS(SpringSecurityClient cliente) throws Exception {
		SqlSession session = sqlMapper.openSession();
		
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		cliente.setPassword(encoder.encode(cliente.getPassword()));
		
		try {
			session.insert(ProjectStr+"SQL_insert_client", cliente);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally{
			session.close();
		}
		
		return cliente;
	}
	
	@Override
	public SpringSecurityClient obtieneClienteSMS(String email) throws Exception {
		SqlSession session = sqlMapper.openSession();
		SpringSecurityClient cliente = null;
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("email", email);
		
		try {
			cliente = (SpringSecurityClient) session.selectOne(ProjectStr+"SQL_list_cliente_sms_web", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return cliente;
	}

	@Override
	public SpringSecurityUser verificaAgenciaExistente(String codigoAgencia) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		SpringSecurityUser usuario = null;
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigoAgencia", codigoAgencia);
		
		try {
			usuario = (SpringSecurityUser) session.selectOne(ProjectStr+"SQL_list_verifica_agencia_existente_usuarios_web", codigoAgencia);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return usuario;
	}

	@Override
	public SpringSecurityUser limiteCreditoUsuario(String username) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		SpringSecurityUser bean = null;
		
		try {
			bean = (SpringSecurityUser) session.selectOne(ProjectStr+"SQL_limite_credito_usuario_web", username);
		} catch (Exception e) {
			session.rollback();
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		
		return bean;
	}

	@Override
	public int updateMontoVentaActual(String username, double montoVentaActual) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		int resultado =-1;
		try{
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("username", username);
		parametros.put("montoVentaActual", montoVentaActual);
		
		resultado = session.update(ProjectStr+"SQL_update_monto_venta_actual_usuario_web", parametros);
		session.commit();
	} catch (Exception e) {
		session.rollback();
		log.info(Utils.printStackTraceToString(e));
	} finally{
		session.close();
	}
		return resultado;
	}

	@Override
	public int updateMontoVentaConfirmada(String username, double montoVentaConfirmada) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		int resultado =-1;
		try{
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("username", username);
		parametros.put("montoVentaConfirmada", montoVentaConfirmada);
		
		resultado = session.update(ProjectStr+"SQL_update_monto_venta_confirmada_usuario_web", parametros);
		session.commit();
	} catch (Exception e) {
		session.rollback();
		log.info(Utils.printStackTraceToString(e));
	} finally{
		session.close();
	}
		return resultado;
	}

	@Override
	public SpringSecurityClient actualizaClienteSMS(SpringSecurityClient cliente) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpringSecurityUser verificaRucExistente(String Ruc) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		SpringSecurityUser bean = null;
		
		try {
			bean = (SpringSecurityUser) session.selectOne(ProjectStr+"SQL_verificaRucExistente", Ruc);
		} catch (Exception e) {
			session.rollback();
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		
		return bean;
	}

	@Override
	public SpringSecurityClient confirmacionClienteSMS(String token) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		SpringSecurityClient bean = null;
		
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("token", token);
			bean = (SpringSecurityClient) session.selectOne(ProjectStr+"SQL_confirmacionClienteSMS", parametros);
		} catch (Exception e) {
			session.rollback();
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		
		return bean;
	}

	@Override
	public int updateCuentaSMS(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		int resultado =-1;
		try{
			
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("username", username);
		parametros.put("password", password);
		
		resultado = session.update(ProjectStr+"SQL_update_Cuenta_ClienteSMS", parametros);
		session.commit();
	} catch (Exception e) {
		session.rollback();
		log.info(Utils.printStackTraceToString(e));
	} finally{
		session.close();
	}
		return resultado;
	}

	@Override
	public int updateTokenSMS(String username, String tokencuenta, int confirmadocuenta) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		int resultado =-1;
		try{
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("username", username);
		parametros.put("tokencuenta", tokencuenta);
		parametros.put("confirmadocuenta", confirmadocuenta);
		
		resultado = session.update(ProjectStr+"SQL_update_Token_ClienteSMS", parametros);
		session.commit();
	} catch (Exception e) {
		session.rollback();
		log.info(Utils.printStackTraceToString(e));
	} finally{
		session.close();
	}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SpringSecurityUser> listaUsuarios() throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		List<SpringSecurityUser> listaUsuarios = new ArrayList<SpringSecurityUser>();
		
		try {
			
			listaUsuarios = session.selectList(ProjectStr+"SQL_list_usuarios_rutas_web");
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return listaUsuarios;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SpringSecurityUser> listaUsuariosRuc(String Ruc) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		List<SpringSecurityUser> listaUsuarios = new ArrayList<SpringSecurityUser>();
		
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Ruc", Ruc);
			
			listaUsuarios = session.selectList(ProjectStr+"SQL_list_usuarios_web_ruc", parametros);
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return listaUsuarios;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SpringSecurityUser> listaUsuariosProgramacionPago() throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		List<SpringSecurityUser> listaUsuarios = new ArrayList<SpringSecurityUser>();
		
		try {
			
			listaUsuarios = session.selectList(ProjectStr+"SQL_list_usuarios_programacion_pago_web");
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return listaUsuarios;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SpringSecurityUser> listaUsuariosAgentes() throws Exception {
		// TODO Auto-generated method stub
		

		SqlSession session = sqlMapper.openSession();
		List<SpringSecurityUser> listaUsuarios = new ArrayList<SpringSecurityUser>();
		
		try {
			
			listaUsuarios = session.selectList(ProjectStr+"SQL_list_usuarios_agentes_autorizados");
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return listaUsuarios;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SpringSecurityUser> listaUsuariosAgentesCiudad(String ciudad) throws Exception {
		// TODO Auto-generated method stub
		

		SqlSession session = sqlMapper.openSession();
		List<SpringSecurityUser> listaUsuarios = new ArrayList<SpringSecurityUser>();
		
		Map<String, Object> parametros = new HashMap<String,Object>();
		parametros.put("ciudad", ciudad);
		
		
		try {
			
			listaUsuarios = session.selectList(ProjectStr+"SQL_list_usuarios_agentes_autorizados_ciudad",parametros);
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return listaUsuarios;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> SQL_SELECT_AGENCIAS_USUARIOS() throws Exception {
		
		List<Map<String, Object>> listaAgencias = new ArrayList<Map<String, Object>>();
		
		SqlSession session = sqlMapper.openSession();
		
		try {
			
			listaAgencias = session.selectList(ProjectStr+"SQL_SELECT_AGENCIAS_USUARIOS");
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return listaAgencias;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<V_Cliente_SMS_Web> ListaUsuariosVentaTelefonica() throws Exception {
		// TODO Auto-generated method stub 
		List<V_Cliente_SMS_Web> listaUsuariosVentaTelefonica = new ArrayList<>();
		
		SqlSession session = sqlMapper.openSession();
		
		try { 
			listaUsuariosVentaTelefonica = (List<V_Cliente_SMS_Web>)session.selectList(ProjectStr+"SQL_Lista_Usuarios_VentaTelefonica"); 
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return listaUsuariosVentaTelefonica; 
		// 
	}

	@Override
	public List<Map<String, Object>> SQL_SELECT_AGENCIAS_POR_USUARIOS(String username) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> listaAgencias = new ArrayList<Map<String, Object>>();
		
		SqlSession session = sqlMapper.openSession();
		
		try { 
			listaAgencias = session.selectList(ProjectStr+"SQL_SELECT_AGENCIAS_USUARIOS",username);
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return listaAgencias;
	}
	
	//$2a$10$1BFICZUnXj6.Y.WV2DfhRugiLK/adzi3y3Pha/IwIqBDNI8awhHVm
}
