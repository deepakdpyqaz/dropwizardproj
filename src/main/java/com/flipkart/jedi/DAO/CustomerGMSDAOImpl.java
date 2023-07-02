/**
 * 
 */
package com.flipkart.jedi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.flipkart.jedi.bean.Customer;
import com.flipkart.jedi.bean.Registration;
import com.flipkart.jedi.constants.SQLConstants;
import com.flipkart.jedi.utils.DBUtils;

/**
 * 
 */
public class CustomerGMSDAOImpl implements CustomerGMSDao {
	public boolean createCustomer(Customer customer) {
		Connection conn = DBUtils.getConnection();
		try {			
			PreparedStatement create_customer_registration_stmt = conn.prepareStatement(SQLConstants.CREATE_CUSTOMER_REGISTRATION);
			create_customer_registration_stmt.setString(1,customer.getUsername());
			create_customer_registration_stmt.setString(2,customer.getPassword());
			create_customer_registration_stmt.setInt(3,customer.getRoleId());
			create_customer_registration_stmt.setString(4,customer.getName());
			create_customer_registration_stmt.setString(5,customer.getAddress());
			create_customer_registration_stmt.setString(6,customer.getPhone());
			create_customer_registration_stmt.setString(7,customer.getGender());
			create_customer_registration_stmt.execute();
			
			PreparedStatement create_user_stmt = conn.prepareStatement(SQLConstants.CREATE_USER);
			create_user_stmt.setString(1, customer.getUsername());
			create_user_stmt.setString(2, customer.getPassword());
			create_user_stmt.setInt(3, customer.getRoleId());
			create_user_stmt.setString(4, customer.getName());
			create_user_stmt.executeUpdate();
			
			PreparedStatement create_customer_stmt = conn.prepareStatement(SQLConstants.CREATE_CUSTOMER);
			create_customer_stmt.setString(1, customer.getUsername());
			create_customer_stmt.setString(2, customer.getAddress());
			create_customer_stmt.setString(3, customer.getPhone());
			create_customer_stmt.setString(4, customer.getGender());
			create_customer_stmt.execute();
			return true;
		}
		catch(SQLIntegrityConstraintViolationException sicve){
			return false;
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return false;
	}

	@Override
	public Registration getAllDetails(String userId) {
		Connection conn = DBUtils.getConnection();
		try {
			PreparedStatement get_customer_details_stmt = conn.prepareStatement(SQLConstants.GET_CUSTOMER_DETAILS);
			PreparedStatement stmt = conn.prepareStatement(get_customer_details_stmt.toString());
			stmt.setString(1, userId);
			ResultSet rs = stmt.executeQuery();
			Registration user = new Registration(
					rs.getString("regId"),
					rs.getString("address"),
					rs.getString("phoneNo"),
					rs.getString("name"),
					rs.getString("gender"),
					rs.getString("dob")
			);
			return user;
		}catch (SQLException sqlException){
			sqlException.printStackTrace();
		}
		return null;
	}
}
