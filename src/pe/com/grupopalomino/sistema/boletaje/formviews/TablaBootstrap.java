package pe.com.grupopalomino.sistema.boletaje.formviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TablaBootstrap {

	private String query;
	private Integer limit, offset, total;
	private List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<Map<String, Object>> getRows() {
		return rows;
	}
	public void addParametro(Map<String, Object> parametro){
		rows.add(parametro);
	}
	
}
