package dao;

import model.User;
import util.DBConnection;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {


    // =====================================================
    // LOGIN
    // =====================================================

    public User login(String username, String password) {

        User user = null;

        try {

            Connection con = DBConnection.getConnection();

          
            String sql =
                    "SELECT * FROM users WHERE username=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs =
                    ps.executeQuery();


            if (rs.next()) {

                String storedPassword =
                        rs.getString("password");

                boolean passwordMatches = false;


               
                if (isBcryptHash(storedPassword)) {

                    passwordMatches =
                            BCrypt.checkpw(
                                    password,
                                    storedPassword
                            );

                } else {

                    
                    passwordMatches =
                            password.equals(storedPassword);


        
                    if (passwordMatches) {

                        String hashedPassword =
                                BCrypt.hashpw(
                                        password,
                                        BCrypt.gensalt()
                                );

                        updatePasswordHash(
                                rs.getInt("user_id"),
                                hashedPassword
                        );
                    }
                }


                if (passwordMatches) {

                    user = new User();

                    user.setUserId(
                            rs.getInt("user_id")
                    );

                    user.setUsername(
                            rs.getString("username")
                    );

                   
                    user.setPassword(null);

                    user.setRole(
                            rs.getString("role")
                    );
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return user;
    }



    // =====================================================
    // ADD USER
    // =====================================================

    public boolean addUser(User user) {

        boolean result = false;

        try {

            Connection con =
                    DBConnection.getConnection();


            String sql =
                    "INSERT INTO users(username,password,role) VALUES(?,?,?)";


            PreparedStatement ps =
                    con.prepareStatement(sql);


            /*
             * Hash password before inserting it
             * into the database.
             */
            String hashedPassword =
                    BCrypt.hashpw(
                            user.getPassword(),
                            BCrypt.gensalt()
                    );


            ps.setString(
                    1,
                    user.getUsername()
            );

            ps.setString(
                    2,
                    hashedPassword
            );

            ps.setString(
                    3,
                    user.getRole()
            );


            int rows =
                    ps.executeUpdate();


            if (rows > 0) {

                result = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }



    // =====================================================
    // VIEW ALL USERS
    // =====================================================

    public ArrayList<User> getAllUsers() {

        ArrayList<User> users =
                new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();


            /*
             * There is no need to retrieve passwords
             * when displaying users.
             */
            String sql =
                    "SELECT user_id, username, role FROM users";


            PreparedStatement ps =
                    con.prepareStatement(sql);


            ResultSet rs =
                    ps.executeQuery();


            while (rs.next()) {

                User user =
                        new User();


                user.setUserId(
                        rs.getInt("user_id")
                );


                user.setUsername(
                        rs.getString("username")
                );


                /*
                 * Never send password hashes
                 * to the user management page.
                 */
                user.setPassword(null);


                user.setRole(
                        rs.getString("role")
                );


                users.add(user);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return users;
    }



    // =====================================================
    // UPDATE USER
    // =====================================================

    public boolean updateUser(User user) {

        boolean result = false;

        try {

            Connection con =
                    DBConnection.getConnection();


            /*
             * If a new password was supplied,
             * update username, password and role.
             */
            if (user.getPassword() != null &&
                    !user.getPassword().trim().isEmpty()) {


                String sql =
                        "UPDATE users " +
                        "SET username=?, password=?, role=? " +
                        "WHERE user_id=?";


                PreparedStatement ps =
                        con.prepareStatement(sql);


                String hashedPassword =
                        BCrypt.hashpw(
                                user.getPassword(),
                                BCrypt.gensalt()
                        );


                ps.setString(
                        1,
                        user.getUsername()
                );

                ps.setString(
                        2,
                        hashedPassword
                );

                ps.setString(
                        3,
                        user.getRole()
                );

                ps.setInt(
                        4,
                        user.getUserId()
                );


                int rows =
                        ps.executeUpdate();


                result = rows > 0;

            } else {

                /*
                 * If password field is empty,
                 * keep the existing password.
                 */
                String sql =
                        "UPDATE users " +
                        "SET username=?, role=? " +
                        "WHERE user_id=?";


                PreparedStatement ps =
                        con.prepareStatement(sql);


                ps.setString(
                        1,
                        user.getUsername()
                );

                ps.setString(
                        2,
                        user.getRole()
                );

                ps.setInt(
                        3,
                        user.getUserId()
                );


                int rows =
                        ps.executeUpdate();


                result = rows > 0;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }



    // =====================================================
    // DELETE USER
    // =====================================================

    public boolean deleteUser(int userId) {

        boolean result = false;

        try {

            Connection con =
                    DBConnection.getConnection();


            String sql =
                    "DELETE FROM users WHERE user_id=?";


            PreparedStatement ps =
                    con.prepareStatement(sql);


            ps.setInt(
                    1,
                    userId
            );


            int rows =
                    ps.executeUpdate();


            if (rows > 0) {

                result = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }



    // =====================================================
    // UPDATE OLD PASSWORD TO BCRYPT
    // =====================================================

    private void updatePasswordHash(
            int userId,
            String hashedPassword) {

        try {

            Connection con =
                    DBConnection.getConnection();


            String sql =
                    "UPDATE users SET password=? WHERE user_id=?";


            PreparedStatement ps =
                    con.prepareStatement(sql);


            ps.setString(
                    1,
                    hashedPassword
            );

            ps.setInt(
                    2,
                    userId
            );


            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }



    // =====================================================
    // CHECK WHETHER PASSWORD IS BCRYPT
    // =====================================================

    private boolean isBcryptHash(String password) {

        if (password == null) {
            return false;
        }

        return password.startsWith("$2a$")
                || password.startsWith("$2b$")
                || password.startsWith("$2y$");
    }
}