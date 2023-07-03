
package com.flipkart.jedi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.jedi.bean.Booking;
import com.flipkart.jedi.bean.Gym;
import com.flipkart.jedi.utils.DBUtils;
import com.flipkart.jedi.constants.SQLConstants;

public class BookingGMSDAOImpl implements BookingGMSDao {
	public List<Booking> getAllBookings(String custId) {
		Connection conn = DBUtils.getConnection();
		try {
			String sql = SQLConstants.GET_ALL_BOOKINGS;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, custId);
			ResultSet rs = stmt.executeQuery();
			List<Booking> bookings = new ArrayList<Booking>();
			while (rs.next()) {
				Booking booking = new Booking(rs.getInt("booking_id"), rs.getInt("slot_id"),rs.getString("customer_id"),rs.getDate("date"));
				bookings.add(booking);

			}
			return bookings;
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean bookSlot(String customer_id, int slot_id) {
		Connection conn = DBUtils.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(SQLConstants.CREATE_BOOKING);
			stmt.setInt(1, slot_id);
			stmt.setString(2, customer_id);
			stmt.setInt(3, slot_id);
			int rows = stmt.executeUpdate();
			System.out.println(rows);
			return rows==1;
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return false;
	}

	@Override
	public Booking getClashingBooking(String customer_id, int slot_id) {
		Connection conn = DBUtils.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(SQLConstants.GET_CLASHING_SLOTS);
			stmt.setString(1, customer_id);
			stmt.setInt(2, slot_id);
			stmt.setInt(3, slot_id);
			stmt.setInt(4, slot_id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Booking booking = new Booking(rs.getInt("booking_id"), rs.getInt("slot_id"), rs.getString("customer_id"), rs.getDate("date"));
				System.out.println(booking);
				return booking;
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return null;
	}
	public boolean cancelBooking(int booking_id) {
		Connection conn = DBUtils.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(SQLConstants.CANCEL_BOOKING);
			stmt.setInt(1, booking_id);
			stmt.executeUpdate();
			return true;
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return false;
	}


}
