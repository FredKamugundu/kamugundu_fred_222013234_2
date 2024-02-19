package form;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class courseAdmin extends JFrame {
	JFrame frame;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    courseAdmin frame = new courseAdmin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public courseAdmin() {
    	frame = new JFrame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 20, 768, 788);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(64, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Create menus
        JMenu fileMenu = new JMenu("File");

        // Add menus to the menu bar
        menuBar.add(fileMenu);

        // Create menu items for File menu
        JMenuItem exitMenuItem1 = new JMenuItem("student marks");
        JMenuItem exitMenuItem2 = new JMenuItem("courses");
        JMenuItem exitMenuItem3 = new JMenuItem("log out");
        
        fileMenu.add(exitMenuItem1);
        fileMenu.add(exitMenuItem2);
        fileMenu.add(exitMenuItem3);

        // Add action listener to the Exit menu item
        exitMenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = 0;
                // JOptionPane.setRootFrame(null);
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    Grades obj = new Grades();
                    // obj.setTitle("Student-Login");
                    obj.setVisible(true);
                    frame.dispose();
                }
            }
        });
        exitMenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a =0;
                // JOptionPane.setRootFrame(null);
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    courseAdmin obj = new courseAdmin();
                    // obj.setTitle("Student-Login");
                    obj.setVisible(true);
                    frame.dispose();
                }
            }
        });
        
        exitMenuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(exitMenuItem3, "Are you sure?");
                // JOptionPane.setRootFrame(null);
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    AdminLogin obj = new AdminLogin();
                    // obj.setTitle("Student-Login");
                    obj.setVisible(true);
                    frame.dispose();
                }
            }
        });
        
        JLabel lblNewLabel = new JLabel("STUDENT COURSES");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(203, 33, 215, 38);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Courseid:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1.setBounds(29, 161, 78, 20);
        contentPane.add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(199, 163, 203, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Coursename:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2.setBounds(29, 206, 105, 20);
        contentPane.add(lblNewLabel_2);

        textField_1 = new JTextField();
        textField_1.setBounds(199, 200, 203, 20);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Credits:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_3.setBounds(29, 237, 78, 20);
        contentPane.add(lblNewLabel_3);

        textField_2 = new JTextField();
        textField_2.setBounds(199, 238, 203, 20);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        JButton btnNewButton = new JButton("SEND");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kamugundu_fred_s_p_a", "222013234", "222013234");

                    con.setAutoCommit(true); // Set auto-commit to true

                    String sql = "INSERT INTO courses VALUES(?,?,?)";
                    try (PreparedStatement stm = con.prepareStatement(sql)) {
                        stm.setInt(1, Integer.parseInt(textField.getText()));
                        stm.setString(2, textField_1.getText());
                        stm.setString(3, textField_2.getText());

                        stm.executeUpdate(); // Execute the update

                        JOptionPane.showMessageDialog(btnNewButton, "Data inserted successfully!");
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    JOptionPane.showMessageDialog(btnNewButton, "An error occurred. Please check the console for details.");
                }
            }
        });

        btnNewButton.setBackground(new Color(0, 0, 0));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton.setBounds(29, 423, 105, 26);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("DELETE");
        btnNewButton_1.setBackground(new Color(0, 0, 50));
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton_1.setBounds(395, 423, 135, 29);
        contentPane.add(btnNewButton_1);

//        JLabel lblNewLabel_4 = new JLabel("Userid:");
//        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
//        lblNewLabel_4.setBounds(29, 276, 68, 20);
//        contentPane.add(lblNewLabel_4);

//        textField_3 = new JTextField();
//        textField_3.setBounds(203, 208, 199, 20);
//        contentPane.add(textField_3);
//        textField_3.setColumns(10);

        JButton btnNewButton_2 = new JButton("UPDATE");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCourse();
            }
        });
        btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton_2.setBounds(167, 424, 123, 26);
        contentPane.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("VIEW");
        btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton_3.setBounds(300, 423, 89, 29);
        contentPane.add(btnNewButton_3);

        // Add a JScrollPane and JTable for displaying data
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(29, 471, 471, 75);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        // Set up the table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Course ID");
        model.addColumn("Course Name");
        model.addColumn("Credits");

        // Set the model to the table
        table.setModel(model);

        // Action listener for the "VIEW" button
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                retrieveAllCoursesData();
            }
        });
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCourse();
            }
        });
//        JButton btnNewButton_2 = new JButton("UPDATE");
        
    }

	private void retrieveAllCoursesData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kamugundu_fred_s_p_a", "222013234", "222013234");

            String sql = "SELECT * FROM courses";
            try (PreparedStatement stm = con.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    int courseid = rs.getInt("courseid");
                    String coursename = rs.getString("coursename");
                    String credits = rs.getString("credits");
                    

                    // Add the retrieved data to the table model
                    model.addRow(new Object[]{courseid, coursename, credits});
                }

                JOptionPane.showMessageDialog(courseAdmin.this, "Data retrieved successfully!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(courseAdmin.this, "An error occurred while retrieving data. Please check the console for details.");
        }
    }
	 private void deleteCourse() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_portal_applicatin", "root", "");

	            String sql = "DELETE FROM courses WHERE courseid=?";
	            try (PreparedStatement stm = con.prepareStatement(sql)) {
	                stm.setInt(1, Integer.parseInt(textField.getText()));

	                int affectedRows = stm.executeUpdate(); // Execute the delete
	                if (affectedRows > 0) {
	                    JOptionPane.showMessageDialog(courseAdmin.this, "Course deleted successfully!");
	                } else {
	                    JOptionPane.showMessageDialog(courseAdmin.this, "Course not found or deletion failed.");
	                }
	            }
	        } catch (Exception e2) {
	            e2.printStackTrace();
	            JOptionPane.showMessageDialog(courseAdmin.this, "An error occurred. Please check the console for details.");
	        }
	    }
	 private void updateCourse() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_portal_applicatin", "root", "");

	            String sql = "UPDATE courses SET coursename=?, credits=? WHERE courseid=?";
	            try (PreparedStatement stm = con.prepareStatement(sql)) {
	                stm.setString(1, textField_1.getText());
	                stm.setString(2, textField_2.getText());
	                stm.setInt(3, Integer.parseInt(textField.getText()));

	                int affectedRows = stm.executeUpdate(); // Execute the update
	                if (affectedRows > 0) {
	                    JOptionPane.showMessageDialog(courseAdmin.this, "Course updated successfully!");
	                } else {
	                    JOptionPane.showMessageDialog(courseAdmin.this, "Course not found or update failed.");
	                }
	            }
	        } catch (Exception e2) {
	            e2.printStackTrace();
	            JOptionPane.showMessageDialog(courseAdmin.this, "An error occurred. Please check the console for details.");
	        }
	    }
}
