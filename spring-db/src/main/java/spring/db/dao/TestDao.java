package spring.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {

	private final String SQL_SELECT="select * from g_lokasyon where arayan_tel=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String getName(String msisdn){

		List<String> names= jdbcTemplate.query(SQL_SELECT, new ParameterizedRowMapper<String>() {
			
			public String mapRow(java.sql.ResultSet rs, int arg1) throws java.sql.SQLException {
				
					if(rs.next()){
						return rs.getString("ADI");
					}else{
						return "";
					}
				};
				
			
			
		},msisdn );
		return names.size()>0 ? names.get(0):"";
		
		
	}
	
}
