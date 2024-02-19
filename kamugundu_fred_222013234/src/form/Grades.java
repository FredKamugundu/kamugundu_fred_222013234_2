package form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Grades extends JFrame {
	JFrame frame;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grades frame = new Grades();
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
	public Grades() {
		frame = new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 20, 725, 782);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
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
                int a = 0;
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
		
		JLabel lblNewLabel = new JLabel("Student Marks");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(171, 22, 179, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("markid:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(40, 145, 78, 25);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(189, 149, 192, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("CAT marks/30");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setBounds(40, 197, 150, 25);
		contentPane.add(lblNewLabel_2);
		textField_3 = new JTextField();
		textField_3.setBounds(189, 202, 192, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		
		JLabel lblNewLabel_3 = new JLabel("Final Exam/60");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_3.setBounds(40, 256, 157, 25);
		contentPane.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(192, 261, 189, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		JButton btnNewButton = new JButton("SEND");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {   
		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_portal_applicatin", "root", "");

		            con.setAutoCommit(true); // Set auto-commit to true

		            String sql = "INSERT INTO grades VALUES(?,?,?,?)";
		            try (PreparedStatement stm = con.prepareStatement(sql)) {
		            	  stm.setInt(1, Integer.parseInt(textField.getText()));
		            	  stm.setInt(2, Integer.parseInt(textField_3.getText()));
		            	  stm.setInt(3, Integer.parseInt(textField_1.getText()));
		                stm.setInt(4, Integer.parseInt(textField_2.getText()));
		                
		                stm.executeUpdate(); // Execute the update

		                JOptionPane.showMessageDialog(btnNewButton, "Data inserted successfully!");
		            }
		        } catch (Exception e2) {
		            e2.printStackTrace();
		            JOptionPane.showMessageDialog(btnNewButton, "An error occurred. Please check the console for details.");
		        }
		    }
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(21, 416, 89, 33);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("DELETE");
		btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteGrade();
            }
        });
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBounds(373, 415, 128, 34);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("Courseid");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4.setBounds(40, 300, 89, 25);
		contentPane.add(lblNewLabel_4);
		
		textField_2 = new JTextField();
		textField_2.setBounds(189, 300, 192, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		
		
		JButton btnNewButton_2 = new JButton("UPDATE");
		btnNewButton_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        updateGrade();
		    }
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_2.setBounds(150, 419, 107, 28);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("VIEW");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_3.setBounds(267, 416, 89, 33);
		contentPane.add(btnNewButton_3);
        // Add a JScrollPane and JTable for displaying data
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(29, 471, 471, 75);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        // Set up the table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("MARK ID");
        model.addColumn("CATmarks/30");
        model.addColumn("FinalExam/60");
        model.addColumn("courseid");

        // Set the model to the table
        table.setModel(model);

        // Action listener for the "VIEW" button
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                retrieveAllCoursesData();
            }
        });
		
		
	
}
	private void retrieveAllCoursesData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kamugundu_fred_s_p_a", "222013234", "222013234");

            String sql = "SELECT * FROM grades";
            try (PreparedStatement stm = con.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    int markid = rs.getInt("markid");
                    int CATmarks30 = rs.getInt("CATmarks/30");
                    int FinalExam60 = rs.getInt("FinalExam/60");
                    int courseid = rs.getInt("courseid");

                    // Add the retrieved data to the table model
                    model.addRow(new Object[]{markid, CATmarks30, FinalExam60,courseid});
                }

                JOptionPane.showMessageDialog(Grades.this, "Data retrieved successfully!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(Grades.this, "An error occurred while retrieving data. Please check the console for details.");
        }
    }

	private void updateGrade() {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kamugundu_fred_s_p_a", "222013234", "222013234");

	        String sql = "UPDATE grades SET `CATmarks/30`=?, `FinalExam/60`=?, courseid=? WHERE markid=?";
	        try (PreparedStatement stm = con.prepareStatement(sql)) {
	            stm.setInt(1, Integer.parseInt(textField_3.getText()));
	            stm.setInt(2, Integer.parseInt(textField_1.getText()));
	            stm.setInt(3, Integer.parseInt(textField_2.getText()));
	            stm.setInt(4, Integer.parseInt(textField.getText()));

	            int affectedRows = stm.executeUpdate(); // Execute the update
	            if (affectedRows > 0) {
	                JOptionPane.showMessageDialog(Grades.this, "Grade updated successfully!");
	            } else {
	                JOptionPane.showMessageDialog(Grades.this, "Grade not found or update failed.");
	            }
	        }
	    } catch (Exception e2) {
	        e2.printStackTrace();
	        JOptionPane.showMessageDialog(Grades.this, "An error occurred. Please check the console for details.");
	    }
	}

	private void deleteGrade() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_portal_applicatin", "root", "");

            String sql = "DELETE FROM grades WHERE markid=?";
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, Integer.parseInt(textField.getText()));

                int affectedRows = stm.executeUpdate(); // Execute the delete
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(Grades.this, "Grade deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(Grades.this, "Grade not found or delete failed.");
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            JOptionPane.showMessageDialog(Grades.this, "An error occurred. Please check the console for details.");
        }
    }
	
	}
