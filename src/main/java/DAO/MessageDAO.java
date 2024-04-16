package DAO;
import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class MessageDAO {
    public List<String> getAllGroceries(){
        Connection connection = ConnectionUtil.getConnection();
        List<String> messages = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "select * from Message;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                messages.add(rs.getString("message_id"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return messages;
    }

    public static Handler fetchById = ctx -> {
        int id = Integer.parseInt(Objects.requireNonNull(ctx.pathParam("message_id")));
        MessageDao dao = MessageDao.instance();
        Optional<Message> message = dao.getUserById(id);

        // Return the user object in the response
        if (message.isPresent()) {
            ctx.json( message.get() );
            ctx.status(200);
        } 
        else {
            ctx.html("");
        }
    };

    
}
