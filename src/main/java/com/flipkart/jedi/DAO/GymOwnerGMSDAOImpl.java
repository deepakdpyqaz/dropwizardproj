package com.flipkart.jedi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.jedi.bean.Gym;
import com.flipkart.jedi.bean.GymOwner;
import com.flipkart.jedi.utils.DBUtils;
import com.flipkart.jedi.constants.SQLConstants;
public class GymOwnerGMSDAOImpl implements GymOwnerGMSDao {
	public void createGymOwner(GymOwner newGymOwner) {
		Connection conn = DBUtils.getConnection();
		String sql = SQLConstants.CREATE_GYM_OWNER;
		try {			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, newGymOwner.getUsername());
			stmt.setString(2, newGymOwner.getAddress());
			stmt.setString(3, newGymOwner.getAadharNo());
			stmt.setString(4, newGymOwner.getPanCard());
			stmt.executeUpdate();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return ;
	}

	@Override
	public boolean isApproved(String gym_owner_id) {
		Connection conn = DBUtils.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(SQLConstants.CHECK_IS_GYM_OWNER_APPROVED);
			stmt.setString(1, gym_owner_id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getBoolean("is_approved");
			}
		}
		catch(SQLException se) {
			return false;
		}
		return false;
	}

	@Override
	public boolean approveGymOwner(String gym_owner_id) {
		Connection conn = DBUtils.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(SQLConstants.APPROVE_GYM_OWNER);
			stmt.setString(1, gym_owner_id);
			return stmt.executeUpdate()>0;
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return false;
	}

	@Override
	public List<GymOwner> getAllGymOwner() {
		Connection conn = DBUtils.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(SQLConstants.GET_ALL_GYMS_OWNERS);
			ResultSet rs = stmt.executeQuery();
			List<GymOwner> gymOwners = new ArrayList<GymOwner> ();
			while(rs.next()) {
				GymOwner gymOwner = new GymOwner(rs.getString("gym_owner_id"),rs.getString("password"),rs.getInt("role_id"),
						rs.getString("name"), rs.getString("registration_id"),rs.getString("address"),rs.getString("aadhar_no"),rs.getString("pan_no"),rs.getBoolean("is_approved"));
				gymOwners.add(gymOwner);
			}
			return gymOwners;
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return null;
	}
	
	

}
