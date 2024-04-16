package DAO;
import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class UserDAO {
    public static Handler fetchById = ctx -> {
        int id = Integer.parseInt(Objects.requireNonNull(ctx.pathParam("account_id")));
        Account dao = UserDAO.instance();
        Optional<User> user = dao.getUserById(id);

        // Return the user object in the response
        if (user.isPresent()) {
            ctx.json( user.get() );
            ctx.status(200);
        } 
        else {
            ctx.html("");
            ctx.status(401);
        }
    };
}
