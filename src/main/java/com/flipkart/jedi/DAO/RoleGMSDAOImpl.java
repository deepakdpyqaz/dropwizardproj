/**
 * 
 */
package com.flipkart.jedi.DAO;

import com.flipkart.jedi.bean.Role;
import com.flipkart.jedi.constants.SQLConstants;
import com.flipkart.jedi.utils.DBUtils;

import java.sql.*;

/**
 * 
 */
public class RoleGMSDAOImpl implements RoleGMSDao {

	@Override
	public String getRoleNameById(int role_id) {
		Connection conn = DBUtils.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(SQLConstants.GET_ROLE_NAME_BY_ID);
			stmt.setInt(1,role_id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getString("role_name");
			}
			else {
				return null;
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int getRoleIdByName(String role_name) {
		Connection conn = DBUtils.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(SQLConstants.GET_ROLE_ID_BY_NAME);
			stmt.setString(1,role_name);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("role_id");
			}
			else {
				return -1;
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return -1;
	}

}
