package layout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.CalendarSelectionListener;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.privatejgoodies.forms.factories.CC;

import dao.AttendanceDAO;
import dao.CourseDAO;
import dao.UserDAO;
import pojo.Attendance;
import pojo.Course;
import pojo.User;
import javax.swing.JTextField;

public class StudentJPanel extends JPanel {

	private JPanel panelMenu;
	private SpringLayout sl_panelMenu;
	private JPanel panelContent;
	private SpringLayout sl_panelContent;
	private JButton btnShowCal;
	private JButton btnChangePass;
	private JTable tableCourses;
	private DefaultTableModel model;
	private JScrollPane scrollPaneCourses;
	private TableColumnModel tcm;
	private Vector<String> columnNames;
	private JLabel lblNewPass;
	private JLabel lblRetypePass;
	private JPasswordField textFieldOld;
	private JPasswordField textFieldNew;
	private JPasswordField textFieldRetype;
	private JButton btnSave;
	private JLabel lblOldPass;



	/**
	 * Create the panel.
	 */
	public StudentJPanel(String username, boolean isFirstTime) {
		setBackground(Color.WHITE);
		setBounds(200, 100, 1200, 700);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		panelMenu = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelMenu, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, panelMenu, 250, SpringLayout.WEST, this);
		panelMenu.setBackground(new Color(245, 245, 245));
		springLayout.putConstraint(SpringLayout.WEST, panelMenu, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panelMenu, 0, SpringLayout.SOUTH, this);
		add(panelMenu);
		
		sl_panelMenu = new SpringLayout();
		panelMenu.setLayout(sl_panelMenu);
		
		btnShowCal = new JButton("Xem lịch điểm danh");
		sl_panelMenu.putConstraint(SpringLayout.NORTH, btnShowCal, 0, SpringLayout.NORTH, panelMenu);
		sl_panelMenu.putConstraint(SpringLayout.WEST, btnShowCal, 0, SpringLayout.WEST, panelMenu);
		sl_panelMenu.putConstraint(SpringLayout.SOUTH, btnShowCal, 60, SpringLayout.NORTH, panelMenu);
		sl_panelMenu.putConstraint(SpringLayout.EAST, btnShowCal, 0, SpringLayout.EAST, panelMenu);
		
		panelMenu.add(btnShowCal);
		btnShowCal.setForeground(new Color(255, 255, 255));
		btnShowCal.setBackground(new Color(255, 140, 0));
		btnShowCal.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnShowCal.setBorder(BorderFactory.createEmptyBorder());
		
		btnChangePass = new JButton("Đổi mật khẩu");
		sl_panelMenu.putConstraint(SpringLayout.NORTH, btnChangePass, 0, SpringLayout.SOUTH, btnShowCal);
		sl_panelMenu.putConstraint(SpringLayout.WEST, btnChangePass, 0, SpringLayout.WEST, panelMenu);
		sl_panelMenu.putConstraint(SpringLayout.SOUTH, btnChangePass, 60, SpringLayout.SOUTH, btnShowCal);
		sl_panelMenu.putConstraint(SpringLayout.EAST, btnChangePass, 0, SpringLayout.EAST, panelMenu);
		
		panelMenu.add(btnChangePass);
		btnChangePass.setForeground(new Color(255, 255, 255));
		btnChangePass.setBackground(new Color(112, 128, 144));
		btnChangePass.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnChangePass.setBorder(BorderFactory.createEmptyBorder());
		
		panelContent = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelContent, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panelContent, 0, SpringLayout.EAST, panelMenu);
		springLayout.putConstraint(SpringLayout.EAST, panelContent, 0, SpringLayout.EAST, this);
		panelContent.setBackground(new Color(255, 255, 255));

		springLayout.putConstraint(SpringLayout.SOUTH, panelContent, 0, SpringLayout.SOUTH, this);
		add(panelContent);
		sl_panelContent = new SpringLayout();
		panelContent.setLayout(sl_panelContent);
		
		//listCourse = CourseDAO.getCourseList(); 
		//addShowCourse();
		
		btnShowCal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelContent.removeAll();
				addCal(username);
				btnShowCal.setBackground(new Color(255, 140, 0));
				btnChangePass.setBackground(new Color(112, 128, 144));
				panelContent.validate();
				panelContent.repaint();
			}
		});
		btnChangePass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelContent.removeAll();
				addChangePass(username);
				//addEditCourses();
				//hasDetail = false;
				btnShowCal.setBackground(new Color(112, 128, 144));
				btnChangePass.setBackground(new Color(255, 140, 0));
				panelContent.validate();
				panelContent.repaint();
			}
		});
		
		if (isFirstTime){
			btnShowCal.setBackground(new Color(112, 128, 144));
			btnChangePass.setBackground(new Color(255, 140, 0));
			addChangePass(username);
		}else{
			btnShowCal.setBackground(new Color(255, 140, 0));
			btnChangePass.setBackground(new Color(112, 128, 144));
			addCal(username);
			JOptionPane.showMessageDialog(null, "Bạn cần đổi mật khẩu ở lần đăng nhập đầu tiên.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	void updateAttendance(String username){
		User u = UserDAO.getUserInfo(username);
        Iterator<Attendance> attendances = u.getmAttendances().iterator(); 
        while(attendances.hasNext()){ 
        	Attendance a = attendances.next();
        	LocalDate localdate = LocalDate.now();
        	Date date = Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        	if((a.getmDate().compareTo(date)<0 &&
        				a.getmStatus() == 0)||
        			(LocalTime.now().compareTo(a.getmCourse().getmTimeFinished().toLocalTime())>0 && 
        				LocalDate.now().compareTo(LocalDate.parse(a.getmDate().toString()))==0) ){
        		a.setmStatus(2);
        		boolean res = AttendanceDAO.update(a);
        	}
        }
	}
	
	void addCal(String username){
		
		DatePickerSettings settings = new DatePickerSettings();
        CalendarPanel calendarPanel = new CalendarPanel(settings);
        calendarPanel.setSelectedDate(LocalDate.now());
        calendarPanel.setBorder(new LineBorder(Color.lightGray));
        sl_panelContent.putConstraint(SpringLayout.NORTH, calendarPanel, 40, SpringLayout.NORTH, panelContent);
		//sl_panelContent.putConstraint(SpringLayout.WEST, calendarPanel, 10, SpringLayout.EAST, lblSearch);
		//sl_panelContent.putConstraint(SpringLayout.SOUTH, calendarPanel, 112, SpringLayout.NORTH, panelContent);
		sl_panelContent.putConstraint(SpringLayout.EAST, calendarPanel, -40, SpringLayout.EAST, panelContent);
        panelContent.add(calendarPanel, CC.xy(2, 2));
        
        calendarPanel.addCalendarSelectionListener(new CalendarSelectionListener() {
			@Override
			public void selectionChanged(CalendarSelectionEvent event) {
				LocalDate newDate = event.getNewDate();
				
				model.setDataVector(null,columnNames);
				//System.out.println(newDate.toString());
				
				User u = UserDAO.getUserInfo(username);
		        Iterator<Attendance> attendances = u.getmAttendances().iterator(); 
		        while(attendances.hasNext()){ 
		        	Attendance a = attendances.next();
		        	LocalDate localdate = newDate;
		        	Date date = Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		        	if(a.getmDate().compareTo(date)==0){
		        		Vector<String> row = new Vector<String>();
		        		row.addElement(a.getmCourse().getmCourseID());
		        		row.addElement(a.getmCourse().getmName());
		        		row.addElement(a.getmCourse().getmTimeStarted().toString());
		        		row.addElement(a.getmCourse().getmTimeFinished().toString());
		        		Date currentDate = new Date();
						Time time = new Time(currentDate.getTime());
						LocalTime localTime = time.toLocalTime();
		        		if (localTime.compareTo(a.getmCourse().getmTimeStarted().toLocalTime())<0 && 
		        				localTime.compareTo(a.getmCourse().getmTimeFinished().toLocalTime())>0 &&
		        				a.getmStatus() == 0){
		        			row.addElement("Điểm danh");
		        		}else if(a.getmStatus() == 1){
		        			row.addElement("Có mặt");
		        		}else if(a.getmStatus() == 2){
		        			row.addElement("Vắng");
		        		}else if(a.getmStatus()==0){
		        			row.addElement("Chưa có dữ liệu");
		        		}else{
		        			row.addElement("");
		        		}
		        		model.addRow(row);
		        	}
		        }
		        
		        
				
			}
		});
        
        updateAttendance(username);
        
        columnNames = new Vector<String>();
        columnNames.addElement("Mã môn học");
        columnNames.addElement("Tên môn học");
        columnNames.addElement("Giờ bắt đầu");
        columnNames.addElement("Giờ kết thúc");
        columnNames.addElement("Điểm danh");
        Vector<Vector> rowData = new Vector<Vector>();
		tableCourses = new JTable(rowData, columnNames){
		    public boolean isCellEditable(int row, int column){  
		        return column == 4;  
		      }  
		};
		model = (DefaultTableModel) tableCourses.getModel();

        model.setDataVector(null,columnNames);
        
        User u = UserDAO.getUserInfo(username);
        Iterator<Attendance> attendances = u.getmAttendances().iterator(); 
        while(attendances.hasNext()){ 
        	Attendance a = attendances.next();
        	LocalDate localdate = calendarPanel.getSelectedDate();
        	Date date = Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        	if(a.getmDate().compareTo(date)==0){
        		Vector<String> row = new Vector<String>();
        		row.addElement(a.getmCourse().getmCourseID());
        		row.addElement(a.getmCourse().getmName());
        		row.addElement(a.getmCourse().getmTimeStarted().toString());
        		row.addElement(a.getmCourse().getmTimeFinished().toString());
        		Date currentDate = new Date();
				Time time = new Time(currentDate.getTime());
				LocalTime localTime = time.toLocalTime();
        		if (localTime.compareTo(a.getmCourse().getmTimeStarted().toLocalTime())>0 && 
        				localTime.compareTo(a.getmCourse().getmTimeFinished().toLocalTime())<0 &&
        				a.getmStatus() == 0){
        			row.addElement("Điểm danh");
        		}else if(a.getmStatus() == 1){
        			row.addElement("Có mặt");
        		}else if(a.getmStatus() == 2){
        			row.addElement("Vắng");
        		}else if(a.getmStatus()==0){
        			row.addElement("Chưa có dữ liệu");
        		}else{
        			row.addElement("");
        		}
        		model.addRow(row);
        	}
        }
        /*
        for(int i=0; i<listCourse.size(); i++){ 
        	model.addRow(Course.getTableRow(i, listCourse));
        }*/

        // Make cell centered
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tcm = tableCourses.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(100);
		tcm.getColumn(1).setPreferredWidth(300); 
		tcm.getColumn(2).setPreferredWidth(100);    
		tcm.getColumn(3).setPreferredWidth(100);
		tcm.getColumn(4).setPreferredWidth(150);
		
		tableCourses.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tableCourses.setRowHeight(30);
		
        //model = (DefaultTableModel) tableCourses.getModel();
        tableCourses.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        tableCourses.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        tableCourses.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        tableCourses.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        tableCourses.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
        
        scrollPaneCourses = new JScrollPane(tableCourses);
        sl_panelContent.putConstraint(SpringLayout.NORTH, scrollPaneCourses, 40, SpringLayout.NORTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.WEST, scrollPaneCourses, 40, SpringLayout.WEST, panelContent);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, scrollPaneCourses, -40, SpringLayout.SOUTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.EAST, scrollPaneCourses, -40, SpringLayout.WEST, calendarPanel);
        
        panelContent.add(scrollPaneCourses);
        tableCourses.setSelectionBackground(new Color(255, 235, 205));
        

		tableCourses.getColumn("Điểm danh").setCellRenderer(new ButtonRenderer());
		tableCourses.getColumn("Điểm danh").setCellEditor(new ButtonEditor2(new JCheckBox()));
        
		tableCourses.getModel().addTableModelListener(new TableModelListener() {
			@Override
			  public void tableChanged(TableModelEvent e) {
			     // your code goes here, whatever you want to do when something changes in the table
				  //System.out.println(model.getValueAt(e.getFirstRow(),0));
				  
				  //panelContent.validate();
				  //panelContent.repaint();
				if (e.getFirstRow()!=-1){
					  if (model.getValueAt(e.getFirstRow(), 4).equals("Điểm danh")){
						  List<Attendance> lsa = AttendanceDAO.getAttendanceList();
						  for (int i = 0; i < lsa.size();++i){
							  Attendance a = lsa.get(i);
							  if (a.getmCourse().getmCourseID().equals(model.getValueAt(e.getFirstRow(),0))&&
									  a.getmStudent().getmUsername().equals(username)&&
									  LocalDate.parse(a.getmDate().toString()).compareTo(calendarPanel.getSelectedDate())==0){
								  a.setmStatus(1);
								  boolean res = AttendanceDAO.update(a);
								  JOptionPane.showMessageDialog(null, "Điểm danh thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
								  model.setValueAt("Có mặt", e.getFirstRow(), e.getColumn());
							  }
						  }
					  }
				}
			  }
			});
	}
	
	void addChangePass(String username){

		lblOldPass = new JLabel("Mật khẩu cũ");
		sl_panelContent.putConstraint(SpringLayout.EAST, lblOldPass, 300, SpringLayout.WEST, panelContent);
		lblOldPass.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panelContent.add(lblOldPass);
		
		lblNewPass = new JLabel("Mật khẩu mới");
		sl_panelContent.putConstraint(SpringLayout.NORTH, lblNewPass, 195, SpringLayout.NORTH, panelContent);
		sl_panelContent.putConstraint(SpringLayout.EAST, lblNewPass, 0, SpringLayout.EAST, lblOldPass);
		sl_panelContent.putConstraint(SpringLayout.SOUTH, lblOldPass, -20, SpringLayout.NORTH, lblNewPass);
		lblNewPass.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panelContent.add(lblNewPass);
		
		lblRetypePass = new JLabel("Nhập lại mật khẩu");
		sl_panelContent.putConstraint(SpringLayout.NORTH, lblRetypePass, 20, SpringLayout.SOUTH, lblNewPass);
		sl_panelContent.putConstraint(SpringLayout.EAST, lblRetypePass, 0, SpringLayout.EAST, lblNewPass);
		lblRetypePass.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panelContent.add(lblRetypePass);
		
		textFieldOld = new JPasswordField();
		textFieldOld.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		sl_panelContent.putConstraint(SpringLayout.NORTH, textFieldOld, -5, SpringLayout.NORTH, lblOldPass);
		sl_panelContent.putConstraint(SpringLayout.WEST, textFieldOld, 20, SpringLayout.EAST, lblOldPass);
		sl_panelContent.putConstraint(SpringLayout.SOUTH, textFieldOld, 5, SpringLayout.SOUTH, lblOldPass);
		sl_panelContent.putConstraint(SpringLayout.EAST, textFieldOld, -200, SpringLayout.EAST, panelContent);
		panelContent.add(textFieldOld);
		
		textFieldNew = new JPasswordField();
		sl_panelContent.putConstraint(SpringLayout.EAST, textFieldNew, 0, SpringLayout.EAST, textFieldOld);
		textFieldNew.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		sl_panelContent.putConstraint(SpringLayout.NORTH, textFieldNew, -5, SpringLayout.NORTH, lblNewPass);
		sl_panelContent.putConstraint(SpringLayout.WEST, textFieldNew, 0, SpringLayout.WEST, textFieldOld);
		sl_panelContent.putConstraint(SpringLayout.SOUTH, textFieldNew, 5, SpringLayout.SOUTH, lblNewPass);
		panelContent.add(textFieldNew);
		
		textFieldRetype = new JPasswordField();
		sl_panelContent.putConstraint(SpringLayout.NORTH, textFieldRetype, -5, SpringLayout.NORTH, lblRetypePass);
		sl_panelContent.putConstraint(SpringLayout.WEST, textFieldRetype, 0, SpringLayout.WEST, textFieldOld);
		sl_panelContent.putConstraint(SpringLayout.SOUTH, textFieldRetype, 5, SpringLayout.SOUTH, lblRetypePass);
		sl_panelContent.putConstraint(SpringLayout.EAST, textFieldRetype, 0, SpringLayout.EAST, textFieldOld);
		textFieldRetype.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panelContent.add(textFieldRetype);
		
		btnSave = new JButton("Lưu lại");
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnSave.setBackground(new Color(255, 99, 71));
		sl_panelContent.putConstraint(SpringLayout.NORTH, btnSave, 35, SpringLayout.SOUTH, textFieldRetype);
		sl_panelContent.putConstraint(SpringLayout.WEST, btnSave, 350, SpringLayout.WEST, panelContent);
		sl_panelContent.putConstraint(SpringLayout.SOUTH, btnSave, 70, SpringLayout.SOUTH, textFieldRetype);
		sl_panelContent.putConstraint(SpringLayout.EAST, btnSave, -350, SpringLayout.EAST, panelContent);
		btnSave.setBorder(BorderFactory.createEmptyBorder());
		panelContent.add(btnSave);
		
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				User u = UserDAO.getUserInfo(username);
				char[] pass = textFieldOld.getPassword();
				String passStr = new String(pass);
				MessageDigest digest = null;
				try {
					digest = MessageDigest.getInstance("SHA-1");
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				try {
					digest.update(passStr.getBytes("utf8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				byte[] digestBytes = digest.digest();
				String digestStr = javax.xml.bind.DatatypeConverter.printHexBinary(digestBytes);
				
				if(digestStr.equals(u.getmPassword())){
					if(Arrays.equals(textFieldNew.getPassword(), textFieldRetype.getPassword())){
						char[] pass1 = textFieldNew.getPassword();
						String passStr1 = new String(pass1);
						MessageDigest digest1 = null;
						try {
							digest1 = MessageDigest.getInstance("SHA-1");
						} catch (NoSuchAlgorithmException e) {
							e.printStackTrace();
						}
						try {
							digest1.update(passStr1.getBytes("utf8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						byte[] digestBytes1 = digest1.digest();
						String digestStr1 = javax.xml.bind.DatatypeConverter.printHexBinary(digestBytes1);
						User newu = new User();
						newu.setmUsername(u.getmUsername());
						newu.setmPassword(digestStr1);
						newu.setmFullname(u.getmFullname());
						newu.setmType(0);
						//u.setmPassword(digestStr1);
						boolean res = UserDAO.update(newu);

						textFieldOld.setText("");
						textFieldNew.setText("");
						textFieldRetype.setText("");
						JOptionPane.showMessageDialog(null, "Thay đổi mật khẩu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "Nhập lại mật khẩu không đúng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "Mật khẩu cũ không đúng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				

			}
		});
		
	}
}

class ButtonEditor2 extends DefaultCellEditor {

    protected JButton button;
    private String label;
    boolean isPushed;
    private String courseID;

    public ButtonEditor2(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        //button.setOpaque(true);
        if(checkBox.getText().equals("Điểm danh")){
        	button.setForeground(new Color(0, 191, 255));
        }else{
        	button.setForeground(new Color(0, 0, 0));
        }
    	button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	
        button.setBackground(new Color(240,248,255));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }
 

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {

        if (isSelected && value.equals("Điểm danh")) {
        	button.setForeground(new Color(255, 140, 0));
        } else {
        	//button.setForeground(new Color(0, 191, 255));
        }
        label = (value == null) ? "" : value.toString();
        courseID = (value == null) ? "" : table.getModel().getValueAt(row, 0).toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            //JOptionPane.showMessageDialog(button, courseID + ": Ouch!");

        	
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
