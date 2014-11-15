package hackbee.dao;

import hackbee.controllers.Event;
import hackbee.exceptions.DaoException;

import java.util.ArrayList;
import java.util.List;

public interface HackbeeDAO {

	public void addInterests(String userid,List<String> userInterests) throws DaoException;

	public ArrayList<Event> getAllEvents();
		
}
