package com.flipkart.jedi.DAO;

import java.util.ArrayList;
import java.util.List;
import com.flipkart.jedi.bean.Slot;
import com.flipkart.jedi.utils.DBUtils;
import com.flipkart.jedi.constants.SQLConstants;
import java.sql.*;

public class SlotGMSDAOImpl implements SlotGMSDao {

	@Override
	public List<Slot> getSlotsOfGym(int gymId) {
		// TODO Auto-generated method stub
		Connection conn = DBUtils.getConnection();
		String sql = SQLConstants.GET_ALL_SLOTS;
		List<Slot> slots = new ArrayList<Slot>();
		try {			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, gymId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {		
				Slot slot = new Slot(rs.getInt("slot_id"),rs.getTime("slot_start_time"), rs.getTime("slot_end_time"), rs.getInt("available_seats"),rs.getInt("gym_id"),rs.getDate("day"));
				slots.add(slot);
			}
			return slots;
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isSlotAvailable(int slot_id) {
		Connection conn = DBUtils.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(SQLConstants.CHECK_IS_SLOT_AVAILABLE);
			stmt.setInt(1, slot_id);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean decrementSeats(int slot_id) {
		// TODO Auto-generated method stub
		Connection conn = DBUtils.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(SQLConstants.DECREMENT_SEATS);
			stmt.setInt(1, slot_id);
			int updated_rows = stmt.executeUpdate();
			return updated_rows>0;
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return false;
		
	}

	@Override
	public boolean createSlot(List<Slot> slots) {
		// TODO Auto-generated method stub
		Connection conn = DBUtils.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(SQLConstants.CREATE_SLOT);
			for(Slot st: slots) {
				stmt.setTime(1,st.getSlot_start_time());
				stmt.setTime(2, st.getSlot_end_time());
				stmt.setInt(3, st.getAvailSeats());
				stmt.setInt(4,st.getGymId());
				stmt.setDate(5, st.getDay());
				stmt.addBatch();
			}

			int[] added_rows = stmt.executeBatch();
			conn.commit();
			return true;
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return false;
	}

	@Override
	public Slot getSlot(int slot_id){
		Connection conn = DBUtils.getConnection();
		try{
			System.out.println(slot_id);
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SLOT WHERE slot_id=?");
			stmt.setInt(1,slot_id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				return new Slot(rs.getInt("slot_id"),rs.getTime("slot_start_time"), rs.getTime("slot_end_time"), rs.getInt("available_seats"),rs.getInt("gym_id"),rs.getDate("day"));
			}
			return null;
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		return null;
	}

}
