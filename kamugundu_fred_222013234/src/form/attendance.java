package form;

import java.awt.EventQueue;
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


public class attendance extends JFrame {
	JFrame frame;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					attendance frame = new attendance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public attendance() {
		frame = new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 913, 473);
		contentPane = new JPanel();
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
        JMenuItem exitMenuItem = new JMenuItem("log out");
        
        fileMenu.add(exitMenuItem);

        // Add action listener to the Exit menu item
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(exitMenuItem, "Are you sure?");
                // JOptionPane.setRootFrame(null);
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    StudentLogin obj = new StudentLogin();
                    // obj.setTitle("Student-Login");
                    obj.setVisible(true);
                    frame.dispose();
                }
//                dispose();
//                LoginForm obj = new LoginForm();
//
//               // obj.setTitle("Student-Login");
//                obj.setVisible(true);

            }
        });
		
		JLabel lblNewLabel = new JLabel("userid");
		lblNewLabel.setBounds(163, 36, 88, 34);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(261, 36, 171, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("coursename");
		lblNewLabel_1.setBounds(163, 92, 88, 34);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(261, 94, 171, 34);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("fullname");
		lblNewLabel_2.setBounds(163, 156, 88, 34);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(261, 156, 171, 34);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBounds(261, 220, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("view courses");
		btnNewButton_1.setBounds(360, 220, 135, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("course attendance");
		lblNewLabel_3.setBounds(280, 11, 135, 14);
		contentPane.add(lblNewLabel_3);
		
		// Add a JScrollPane and JTable for displaying data
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(29, 271, 471, 75);
        contentPane.add(scrollPane1);

        table = new JTable();
        scrollPane1.setViewportView(table);

        // Set up the table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Course ID");
        model.addColumn("Course Name");
        model.addColumn("Credits");

        // Set the model to the table
        table.setModel(model);

        // Action listener for the "VIEW" button
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                retrieveAllCoursesData();
            }
        });
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertAttendanceData();
            }
        });
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

                JOptionPane.showMessageDialog(attendance.this, "Data retrieved successfully!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(attendance.this, "An error occurred while retrieving data. Please check the console for details.");
        }
    }
	private void insertAttendanceData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kamugundu_fred_s_p_a", "222013234", "222013234");

            String sql = "INSERT INTO attendance (userid, coursename, fullname) VALUES (?, ?, ?)";
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, Integer.parseInt(textField.getText()));
                stm.setString(2, textField_1.getText());
                stm.setString(3, textField_2.getText());

                stm.executeUpdate(); // Execute the update

                JOptionPane.showMessageDialog(attendance.this, "Attendance data inserted successfully!");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            JOptionPane.showMessageDialog(attendance.this, "An error occurred. Please check the console for details.");
        }
    }

}
