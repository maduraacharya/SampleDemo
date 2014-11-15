package hackbee.dao;

import hackbee.controllers.Event;
import hackbee.exceptions.DaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface HackbeeDAO {

	public void addInterests(String userid,List<String> userInterests) throws DaoException;

	public ArrayList<Event> getAllEvents();

	public Map<String, List<Event>> getSuggestions() throws DaoException;
		
}
