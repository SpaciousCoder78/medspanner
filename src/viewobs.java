//importing modules
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
public class viewobs {
    //defining swing elements
    private JTextArea textArea1;
    private JPanel panel1;

    private JFrame frame;
    //function for the gui
    public  viewobs(){
                //creating a frame
                frame = new JFrame("Viewing Observations");
                //textarea to fit the observations
                textArea1 = new JTextArea(45, 150);

                // Set the layout manager for the frame and add the panel to it
                frame.setLayout(new BorderLayout());
                frame.add(new JScrollPane(textArea1), BorderLayout.CENTER);
                //packing the frame
                frame.pack();
                //setting visibility to true
                frame.setVisible(true);
                //setting the window size to 600x600
                frame.setSize(600, 600);

                // Fetch and display the records
                fetchAndDisplayRecords();
            }
            //function to fetch and display records
            private void fetchAndDisplayRecords() {
                //defining the url of database
                String url = "jdbc:sqlite:medspannerdata.db";
                //try catch block to attempt to connect
                try (Connection conn = DriverManager.getConnection(url);
                     Statement stmt = conn.createStatement();
                     //selecting all tables and data from db
                     ResultSet rs = stmt.executeQuery("SELECT * FROM observations")) {

                    StringBuilder sb = new StringBuilder();
                    while (rs.next()) {
                        //retrieving id from db
                        int id = rs.getInt("id");
                        //retrieving date from db
                        String DATE = rs.getString("DATE");
                        //retrieving observation from db
                        String OBSERVATION = rs.getString("OBSERVATION");
                        //appending
                        sb.append("ID: ").append(id).append("\n");
                        sb.append("Date: ").append(DATE).append("\n");
                        sb.append("Observation: ").append(OBSERVATION).append("\n\n");
                    }

                    textArea1.setText(sb.toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


