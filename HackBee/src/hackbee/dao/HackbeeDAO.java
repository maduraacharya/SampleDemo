package hackbee.dao;

import hackbee.exceptions.DaoException;

import java.util.List;

public interface HackbeeDAO {

	public void addInterests(String userid,List<String> userInterests) throws DaoException;

	public void getSuggestions() throws DaoException;
	
}
