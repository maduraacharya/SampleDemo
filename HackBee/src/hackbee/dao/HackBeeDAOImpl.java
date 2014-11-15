package hackbee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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

}
