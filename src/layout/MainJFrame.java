package layout;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import pojo.User;
import pojo.Course;
import pojo.Attendance;
import dao.UserDAO;
import dao.CourseDAO;
import dao.AttendanceDAO;
import javax.swing.JTextField;

public class MainJFrame extends JFrame {

	private TeacherJPanel teacherJPanel;
	private StudentJPanel studentJPanel;
	private boolean isStudent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJFrame frame = new MainJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
//		List<User> ds=UserDAO.getUserList(); for(int i=0; i<ds.size(); i++){
//		User sv=ds.get(i);
//		System.out.println("Username: "+sv.getmUsername());
//		System.out.println("Pass: "+sv.getmPassword()); }
//		
//		List<Course> dsm=CourseDAO.getCourseList(); 
//		for(int i=0;
//		i<dsm.size(); i++){ Course c=dsm.get(i);
//		System.out.println(c.getmCourseID());
//		System.out.println(c.getmName());
//		System.out.println(c.getmDateStarted());
//		System.out.println(c.getmDateFinished());
//		System.out.println(c.getmWeekday());
//		System.out.println(c.getmTimeStarted());
//		System.out.println(c.getmTimeFinished());
//		System.out.println(c.getmClassroom());
//		 
//		}
//		 
//		List<Attendance> dsa = AttendanceDAO.getAttendanceList(); for(int
//		i=0; i<dsa.size(); i++){ Attendance a =dsa.get(i);
//		System.out.println(a.getmAttendanceID());
//		System.out.println(a.getmWeek()); System.out.println(a.getmDate());
//		System.out.println(a.getmStatus()); }
		 
//		Course dm = CourseDAO.getCourse("JAVA1");
//		if (dm != null) {
//			System.out.println("MSSV: " + dm.getmName());
//		}

//		 Attendance attendance = AttendanceDAO.getAttendanceInfo(1);
//		 if (attendance != null) {
//			 Course c = attendance.getmCourse();
//			 System.out.println(c.getmCourseID());
//			 System.out.println(c.getmName());
//			 User u = attendance.getmStudent();
//			 System.out.println(u.getmUsername());
//			 System.out.println(u.getmPassword());
//			 //String courseID =attendance.getmCourse().getmCourseID();
//		 	//Course dm = CourseDAO.getCourse("JAVA1");
//		 }else{
//			 System.out.println("hello");
//		 }
		
//		Course dm=CourseDAO.getCourse("JAVA1"); 
//		System.out.println("Mã: "+dm.getmCourseID()); 
//		System.out.println("Tên: "+dm.getmName()); 
//		Iterator<Attendance> as =dm.getmAttendances().iterator(); 
//		while(as.hasNext()){ 
//			Attendance s= as.next(); 
//			System.out.println("Mã sách:"+ s.getmAttendanceID()); 
//			System.out.println("Tên sách: "+s.getmCourse().getmName()); 
//			System.out.println("Tác giả: "+s.getmStudent().getmUsername()); 
//			System.out.println("Giá bán: "+s.getmWeek()); 
//			System.out.println("Số lượng: "+s.getmDate()); 
//			}
	}

	/**
	 * Create the frame. This frame contain the Sign in panel - which has a Sign
	 * in button When clicked on it, it will change the appearance of the layout
	 * depends on which user is logging in.
	 */
	public MainJFrame() {
		setTitle("1412593 - Bài tập Hibernate");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 1200, 800);
		// contentPane = new JPanel();
		// contentPane.setBackground(Color.WHITE);
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// contentPane.setLayout(new BorderLayout(0, 0));
		// setContentPane(contentPane);

		// getContentPane().add(new TeacherJPanel());
		setMinimumSize(this.getSize());
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JPanel panelTitle = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelTitle, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panelTitle, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panelTitle, 100, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panelTitle, 0, SpringLayout.EAST, getContentPane());
		panelTitle.setBackground(new Color(0, 191, 255));
		getContentPane().add(panelTitle);
		SpringLayout sl_panelTitle = new SpringLayout();
		panelTitle.setLayout(sl_panelTitle);

		JLabel lblTitle = new JLabel("Ch\u01B0\u01A1ng tr\u00ECnh \u0111i\u1EC3m danh");
		sl_panelTitle.putConstraint(SpringLayout.NORTH, lblTitle, 20, SpringLayout.NORTH, panelTitle);
		sl_panelTitle.putConstraint(SpringLayout.WEST, lblTitle, 20, SpringLayout.WEST, panelTitle);

		panelTitle.add(lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JLabel lblSignin = new JLabel("ĐĂNG NHẬP");
		sl_panelTitle.putConstraint(SpringLayout.NORTH, lblSignin, 50, SpringLayout.NORTH, panelTitle);
		sl_panelTitle.putConstraint(SpringLayout.WEST, lblSignin, 40, SpringLayout.WEST, panelTitle);
		sl_panelTitle.putConstraint(SpringLayout.EAST, lblSignin, 300, SpringLayout.WEST, panelTitle);

		panelTitle.add(lblSignin);
		lblSignin.setForeground(Color.WHITE);
		lblSignin.setFont(new Font("Segoe UI Light", Font.PLAIN, 26));

		JButton btnSignOut = new JButton("\u0110\u0103ng xu\u1EA5t");
		sl_panelTitle.putConstraint(SpringLayout.NORTH, btnSignOut, 0, SpringLayout.NORTH, lblSignin);
		sl_panelTitle.putConstraint(SpringLayout.WEST, btnSignOut, -160, SpringLayout.EAST, panelTitle);
		sl_panelTitle.putConstraint(SpringLayout.SOUTH, btnSignOut, 0, SpringLayout.SOUTH, lblSignin);
		sl_panelTitle.putConstraint(SpringLayout.EAST, btnSignOut, -20, SpringLayout.EAST, panelTitle);

		// panelTitle.add(btnSignOut);
		btnSignOut.setForeground(new Color(0, 191, 255));
		btnSignOut.setBackground(new Color(255, 255, 255));
		btnSignOut.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnSignOut.setBorder(BorderFactory.createEmptyBorder());

		JLabel lblDate = new JLabel(LocalDate.now().toString());
		sl_panelTitle.putConstraint(SpringLayout.SOUTH, lblDate, 0, SpringLayout.SOUTH, lblTitle);
		sl_panelTitle.putConstraint(SpringLayout.EAST, lblDate, -20, SpringLayout.EAST, panelTitle);

		JPanel panelSignin = new JPanel();
		panelSignin.setBackground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.NORTH, panelSignin, 0, SpringLayout.SOUTH, panelTitle);
		springLayout.putConstraint(SpringLayout.WEST, panelSignin, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panelSignin, 0, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panelSignin, 0, SpringLayout.EAST, getContentPane());
		getContentPane().add(panelSignin);
		SpringLayout sl_panelSignin = new SpringLayout();
		panelSignin.setLayout(sl_panelSignin);

		JLabel lblBigSignin = new JLabel("Hãy dùng tài khoản của bạn để đăng nhập");
		sl_panelSignin.putConstraint(SpringLayout.WEST, lblBigSignin, 200, SpringLayout.WEST, panelSignin);
		sl_panelSignin.putConstraint(SpringLayout.EAST, lblBigSignin, -200, SpringLayout.EAST, panelSignin);
		lblBigSignin.setHorizontalAlignment(SwingConstants.CENTER);
		lblBigSignin.setForeground(new Color(0, 191, 255));
		lblBigSignin.setBackground(Color.WHITE);
		lblBigSignin.setFont(new Font("Segoe UI Light", Font.PLAIN, 30));
		sl_panelSignin.putConstraint(SpringLayout.NORTH, lblBigSignin, 138, SpringLayout.NORTH, panelSignin);
		panelSignin.add(lblBigSignin);

		JLabel lblUsername = new JLabel("Tên tài khoản");
		sl_panelSignin.putConstraint(SpringLayout.NORTH, lblUsername, 40, SpringLayout.SOUTH, lblBigSignin);
		sl_panelSignin.putConstraint(SpringLayout.WEST, lblUsername, 400, SpringLayout.WEST, panelSignin);
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panelSignin.add(lblUsername);

		JTextField textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		sl_panelSignin.putConstraint(SpringLayout.NORTH, textFieldUsername, -5, SpringLayout.NORTH, lblUsername);
		sl_panelSignin.putConstraint(SpringLayout.WEST, textFieldUsername, 20, SpringLayout.EAST, lblUsername);
		sl_panelSignin.putConstraint(SpringLayout.SOUTH, textFieldUsername, 5, SpringLayout.SOUTH, lblUsername);
		sl_panelSignin.putConstraint(SpringLayout.EAST, textFieldUsername, -400, SpringLayout.EAST, panelSignin);
		panelSignin.add(textFieldUsername);
		textFieldUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Mật khẩu");
		sl_panelSignin.putConstraint(SpringLayout.NORTH, lblPassword, 20, SpringLayout.SOUTH, lblUsername);
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		sl_panelSignin.putConstraint(SpringLayout.WEST, lblPassword, 0, SpringLayout.WEST, lblUsername);
		panelSignin.add(lblPassword);

		JPasswordField passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		sl_panelSignin.putConstraint(SpringLayout.NORTH, passwordFieldPassword, -5, SpringLayout.NORTH, lblPassword);
		sl_panelSignin.putConstraint(SpringLayout.WEST, passwordFieldPassword, 0, SpringLayout.WEST, textFieldUsername);
		sl_panelSignin.putConstraint(SpringLayout.SOUTH, passwordFieldPassword, 5, SpringLayout.SOUTH, lblPassword);
		sl_panelSignin.putConstraint(SpringLayout.EAST, passwordFieldPassword, 0, SpringLayout.EAST, textFieldUsername);
		panelSignin.add(passwordFieldPassword);
		passwordFieldPassword.setColumns(10);

		JButton btnSignin = new JButton("Đăng nhập");
		sl_panelSignin.putConstraint(SpringLayout.NORTH, btnSignin, 30, SpringLayout.SOUTH, passwordFieldPassword);
		sl_panelSignin.putConstraint(SpringLayout.WEST, btnSignin, 500, SpringLayout.WEST, panelSignin);
		sl_panelSignin.putConstraint(SpringLayout.SOUTH, btnSignin, 70, SpringLayout.SOUTH, passwordFieldPassword);
		sl_panelSignin.putConstraint(SpringLayout.EAST, btnSignin, -100, SpringLayout.EAST, passwordFieldPassword);
		btnSignin.setForeground(new Color(255, 255, 255));
		btnSignin.setBackground(new Color(255, 140, 0));
		btnSignin.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnSignin.setBorder(BorderFactory.createEmptyBorder());
		panelSignin.add(btnSignin);

		// panelTitle.add(lblDate);
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		

		btnSignin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				User u = UserDAO.getUserInfo(textFieldUsername.getText());
				if(u!=null){ 
					char[] pass = passwordFieldPassword.getPassword();
					String passStr = new String(pass);
					//System.out.println(passStr);
					//System.out.println(u.getmPassword());
					
					MessageDigest digest = null;
					try {
						digest = MessageDigest.getInstance("SHA-1");
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						digest.update(passStr.getBytes("utf8"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					byte[] digestBytes = digest.digest();
					String digestStr = javax.xml.bind.DatatypeConverter.printHexBinary(digestBytes);
					//System.out.println(digestStr);

			        
					if(u.getmPassword().equals(digestStr)){
						if (u.getmType() == 1){
							isStudent = false;
							getContentPane().remove(panelSignin);
							teacherJPanel = new TeacherJPanel();
							springLayout.putConstraint(SpringLayout.NORTH, teacherJPanel, 0, SpringLayout.SOUTH, panelTitle);
							springLayout.putConstraint(SpringLayout.WEST, teacherJPanel, 0, SpringLayout.WEST, getContentPane());
							springLayout.putConstraint(SpringLayout.SOUTH, teacherJPanel, 0, SpringLayout.SOUTH, getContentPane());
							springLayout.putConstraint(SpringLayout.EAST, teacherJPanel, 0, SpringLayout.EAST, getContentPane());

							// These things would keeps the button and labels in places
							sl_panelTitle.putConstraint(SpringLayout.NORTH, lblSignin, 50, SpringLayout.NORTH, panelTitle);
							sl_panelTitle.putConstraint(SpringLayout.WEST, lblSignin, 40, SpringLayout.WEST, panelTitle);
							sl_panelTitle.putConstraint(SpringLayout.EAST, lblSignin, 300, SpringLayout.WEST, panelTitle);

							sl_panelTitle.putConstraint(SpringLayout.SOUTH, lblDate, 0, SpringLayout.SOUTH, lblTitle);
							sl_panelTitle.putConstraint(SpringLayout.EAST, lblDate, -20, SpringLayout.EAST, panelTitle);
							panelTitle.add(lblDate);

							sl_panelTitle.putConstraint(SpringLayout.NORTH, btnSignOut, 0, SpringLayout.NORTH, lblSignin);
							sl_panelTitle.putConstraint(SpringLayout.WEST, btnSignOut, -160, SpringLayout.EAST, panelTitle);
							sl_panelTitle.putConstraint(SpringLayout.SOUTH, btnSignOut, 0, SpringLayout.SOUTH, lblSignin);
							sl_panelTitle.putConstraint(SpringLayout.EAST, btnSignOut, -20, SpringLayout.EAST, panelTitle);
							panelTitle.add(btnSignOut);
							//

							lblSignin.setText("GIÁO VỤ");
							getContentPane().add(teacherJPanel);
							getContentPane().validate();
							getContentPane().repaint();
						}else if (u.getmType() == 0){
							isStudent = true;
							MessageDigest digest1 = null;
							try {
								digest1 = MessageDigest.getInstance("SHA-1");
							} catch (NoSuchAlgorithmException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								digest1.update(passStr.getBytes("utf8"));
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							byte[] digestBytes1 = digest1.digest();
							String digestStrName = javax.xml.bind.DatatypeConverter.printHexBinary(digestBytes1);
							
							getContentPane().remove(panelSignin);
							if (digestStrName.equals(digestStr)){
								studentJPanel = new StudentJPanel(u.getmUsername(),true);
							}else{
								studentJPanel = new StudentJPanel(u.getmUsername(),false);
							}
							
							springLayout.putConstraint(SpringLayout.NORTH, studentJPanel, 0, SpringLayout.SOUTH, panelTitle);
							springLayout.putConstraint(SpringLayout.WEST, studentJPanel, 0, SpringLayout.WEST, getContentPane());
							springLayout.putConstraint(SpringLayout.SOUTH, studentJPanel, 0, SpringLayout.SOUTH, getContentPane());
							springLayout.putConstraint(SpringLayout.EAST, studentJPanel, 0, SpringLayout.EAST, getContentPane());

							// These things would keeps the button and labels in places
							sl_panelTitle.putConstraint(SpringLayout.NORTH, lblSignin, 50, SpringLayout.NORTH, panelTitle);
							sl_panelTitle.putConstraint(SpringLayout.WEST, lblSignin, 40, SpringLayout.WEST, panelTitle);
							sl_panelTitle.putConstraint(SpringLayout.EAST, lblSignin, 300, SpringLayout.WEST, panelTitle);

							sl_panelTitle.putConstraint(SpringLayout.SOUTH, lblDate, 0, SpringLayout.SOUTH, lblTitle);
							sl_panelTitle.putConstraint(SpringLayout.EAST, lblDate, -20, SpringLayout.EAST, panelTitle);
							panelTitle.add(lblDate);

							sl_panelTitle.putConstraint(SpringLayout.NORTH, btnSignOut, 0, SpringLayout.NORTH, lblSignin);
							sl_panelTitle.putConstraint(SpringLayout.WEST, btnSignOut, -160, SpringLayout.EAST, panelTitle);
							sl_panelTitle.putConstraint(SpringLayout.SOUTH, btnSignOut, 0, SpringLayout.SOUTH, lblSignin);
							sl_panelTitle.putConstraint(SpringLayout.EAST, btnSignOut, -20, SpringLayout.EAST, panelTitle);
							panelTitle.add(btnSignOut);
							//

							lblSignin.setText("SINH VIÊN");
							getContentPane().add(studentJPanel);
							getContentPane().validate();
							getContentPane().repaint();
						}
					} else{
						JOptionPane.showMessageDialog(null, "Sai mật khẩu", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "Tên đăng nhập không tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});

		btnSignOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (isStudent){
					getContentPane().remove(studentJPanel);
				}else{
					getContentPane().remove(teacherJPanel);
				}
				
				panelTitle.remove(btnSignOut);
				panelTitle.remove(lblDate);
				panelTitle.remove(lblSignin);
				lblSignin.setText("ĐĂNG NHẬP");
				textFieldUsername.setText("");
				passwordFieldPassword.setText("");
				getContentPane().add(panelSignin);
				panelTitle.add(lblSignin);
				
				//getContentPane().validate();
				getContentPane().repaint();

			}
		});

		// Display the window.
		pack();
		setVisible(true);

	}
}
