package com.flipkart.jedi.DAO;

import java.util.ArrayList;
import java.util.List;

import com.flipkart.jedi.bean.Gym;
import com.flipkart.jedi.constants.SQLConstants;
import com.flipkart.jedi.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GymGMSDAOImpl implements GymGMSDao {
	
	public List<Gym> getAllGyms() {
		Connection conn = DBUtils.getConnection();
		try {
			String sql = SQLConstants.SHOW_ALL_GYMS;
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<Gym> gymnasiums = new ArrayList<Gym>();
			while (rs.next()) {
				Gym gym = new Gym(rs.getInt("gym_id"), rs.getString("gym_name"), rs.getString("gst_number"),
						rs.getString("address"), rs.getInt("tot_slots"), rs.getInt("number_of_machines"),
						rs.getInt("seats"), rs.getString("gym_owner_id"),rs.getInt("number_of_instructors"), 
						rs.getBoolean("is_cardio_available"), rs.getBoolean("is_crossfit_available"),
						rs.getInt("floor_area"), rs.getBoolean("is_approved"));
				gymnasiums.add(gym);

			}
			return gymnasiums;
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Gym> getAllAvailableGyms() {
		Connection conn = DBUtils.getConnection();
		try {
			String sql = SQLConstants.SHOW_ALL_AVAILABLE_GYMS;
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<Gym> gymnasiums = new ArrayList<Gym>();
			while (rs.next()) {
				Gym gym = new Gym(rs.getInt("gym_id"), rs.getString("gym_name"), rs.getString("gst_number"),
						rs.getString("address"), rs.getInt("tot_slots"), rs.getInt("number_of_machines"),
						rs.getInt("seats"), rs.getString("gym_owner_id"));
				gymnasiums.add(gym);

			}
			return gymnasiums;
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return null;
	}

	
	public List<Gym> getAllMyGyms(String username) {
		Connection conn = DBUtils.getConnection();
		try {
			String sql = SQLConstants.SHOW_ALL_GYMS_OF_OWNER;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			List<Gym> gymnasiums = new ArrayList<Gym>();
			while (rs.next()) {
				Gym gym = new Gym(rs.getInt("gym_id"), rs.getString("gym_name"), rs.getString("gst_number"),
						rs.getString("address"), rs.getInt("tot_slots"), rs.getInt("number_of_machines"),
						rs.getInt("seats"), rs.getString("gym_owner_id"));
				gymnasiums.add(gym);
			}
			return gymnasiums;
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return null;
	}	
	
	@Override
	public Gym getGymById(int gymId) {
		Connection conn = DBUtils.getConnection();
		String sql = SQLConstants.GET_GYM_BY_ID;
		try {			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, gymId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {				
				return new Gym(rs.getInt("gym_id"),
							   rs.getString("gym_name"),
							   rs.getString("gst_no"),
							   rs.getString("address"),
							   rs.getInt("tot_slots"),
							   rs.getInt("number_of_machines"),
							   rs.getInt("seats"),
							   rs.getString("gym_owner_id"));
			}
			else {
				return null;
			}
			
		}
		catch(SQLException se) {
			System.out.println("Error here");
			se.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateGym(Gym gym) {
		// deepak karega
	}
	
	public String removeGym(int gymId) {
		Connection conn = DBUtils.getConnection();
		//edit kardena isme sql deepak drop karna hai gym 
		String sql = "";
		try {			
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			return "Gym removed";
		}
		catch(SQLException se) {
			System.out.println("Error here");
			se.printStackTrace();
			return "Removal unsuccessful";
		}
	}
	
	public boolean updateApproval(int gymId) {
		Connection conn = DBUtils.getConnection();
		String sql = "UPDATE Gym SET is_approved=true WHERE gym_id=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, gymId);
			int status = stmt.executeUpdate();
			return status!=0;
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return false;
	}
	
	public Gym createGym(Gym gym) {
		Connection conn = DBUtils.getConnection();
		String sql = SQLConstants.CREATE_GYM;
		try {			
			PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, gym.getGymName());
			stmt.setString(2, gym.getGstNo());
			stmt.setString(3, gym.getAddress());
			stmt.setInt(4, gym.getTotSlots());
			stmt.setInt(5, gym.getNumberOfMachines());
			stmt.setInt(6, gym.getSeats());
			stmt.setString(7, gym.getGymOwnerId());
			stmt.setInt(8, gym.getNumberOfInstructors());
			stmt.setBoolean(9, gym.isIsCardioAvailable());
			stmt.setBoolean(10, gym.isIsCrossfitAvailable());
			stmt.setInt(11, gym.getFloorArea());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				gym.setGymId(rs.getInt(1));
				return gym;
			}
			return null;
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return null;
	}
}