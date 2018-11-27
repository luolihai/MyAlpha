package com.web.oa.dao;

import java.sql.ResultSet;
import java.util.List;

public interface ResultSetHandler {
	
	Object objectHandler(ResultSet rs);
	
	List<Object> ListHandler(ResultSet rs);

}
