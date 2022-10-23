package pe.com.grupopalomino.sistema.boletaje.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MapperSQL {
	
	protected String ProjectStr;
	
	SqlSessionFactory sqlMapper = null;
	{
		String archivo = "ConfiguracionIbatis.xml";
		try {
			Reader reader = Resources.getResourceAsReader(archivo);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			System.out.println("Se creo esta cosa");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public MapperSQL(){
		ProjectStr = "BoletajePalomino03.";
	}
	

}
