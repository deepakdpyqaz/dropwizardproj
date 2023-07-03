package com.flipkart.jedi.DAO;
import java.util.List;
import com.flipkart.jedi.bean.Slot;

public interface SlotGMSDao {
	public List<Slot> getSlotsOfGym(int gym_id);
	public boolean isSlotAvailable(int slot_id);
	public boolean decrementSeats(int slot_id);
	public boolean createSlot(List<Slot> slots);

    Slot getSlot(int slot_id);
}
