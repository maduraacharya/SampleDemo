package hackbee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hackbee.controllers.Event;
import hackbee.exceptions.DaoException;

public class HackBeeDAOImpl extends BaseDao implements HackbeeDAO {

	public HackBeeDAOImpl() throws DaoException {
		super();
	}

	@Override
	public void addInterests(String userId ,List<String> userInterests) throws DaoException {

		Connection connection = getConnection();
		PreparedStatement ps = null;
		
		try {
			for (String interest : userInterests) {
				ps = connection.prepareStatement("insert into Person_Type_Interest (user_id,type_name) values (?,?)");
				ps.setString(1, userId);
				ps.setString(2, interest);
				
				if(ps.executeUpdate() !=1){
					throw new DaoException("Unable to add interest");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(connection);
			closeStatement(ps);
		}
		
	}

	@Override
	public ArrayList<Event> getAllEvents() {
		// TODO Auto-generated method stub
		Connection connection = null;
		ArrayList<Event> listOfevents = new ArrayList<Event>();
		try {
			connection = getConnection();
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Statement stmt = null;
		try {
			Date currDate = new Date();
			java.sql.Date sqlCurrDate = new java.sql.Date(currDate.getTime());
			String sql = "SELECT event_id, type_name, name , venue, hosted_by FROM Event where date >="+sqlCurrDate;
			ResultSet rs = stmt.executeQuery(sql);
			
			Event e;
			while(rs.next()) {
				e = new Event(rs.getInt(0), rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				listOfevents.add(e);
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				closeConnection(connection);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return listOfevents;
	}
	

	public Map<String , List<Event>> getSuggestions() throws DaoException{
		  
		  Connection connection = getConnection();
		  PreparedStatement ps = null;
		  Map<String ,  List<Event>> map = new HashMap<String ,  List<Event>>();
		  try {
		    ps = connection.prepareStatement("select p.user_id, e.event_id, e.name " +
		            " from person_type_interest p, event e, event_suggested es " +
		            " where e.type_name = p.type_name  and e.date >= ? " +
		            " and not exists ( select es.user_id, es.event_id "+
		            "FROM Event_Suggested es where es.is_suggested = 1)");
		   // ps.setDate(1, new java.sql.Date );
		    
		    ResultSet rs = ps.executeQuery();
		    while (rs.next()){
		    	if(map.containsKey(rs.getString(1))){
		    		List<Event> events = map.get(rs.getString(1));
		    		events.add(new Event(rs.getInt(2), "", rs.getString(3), "", ""));
		    		map.put(rs.getString(1), events);
		    		
		    	}else{
		    		List<Event> events = new ArrayList<Event>();
		    		events.add(new Event(rs.getInt(2), "", rs.getString(3), "", ""));
		    		map.put(rs.getString(1), events);
		    	}
		    }
		    
		    
		   
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }finally{
		   closeConnection(connection);
		   closeStatement(ps);
		  }
		  return map;
	}
}
