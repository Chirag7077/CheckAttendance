package layout;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTable;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

import pojo.Attendance;
import pojo.Course;
import pojo.User;
import dao.AttendanceDAO;
import dao.CourseDAO;
import dao.UserDAO;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;



public class TeacherJPanel extends JPanel {
	private JTextField textFieldSearch;
	private JTable tableCourses;
	private JButton btnShowCourses;
	private JButton btnEditCourses;
	private JButton btnShowAttendance;
	private JPanel panelContent;
	private JLabel lblPageTitle;
	private JLabel lblSearch;
	private JButton btnSearch;
	private JButton btnAddCourse;
	private JLabel lblSearchDetail;
	private JComboBox comboBox;
	private SpringLayout sl_panelMenu;
	private JPanel panelMenu;
	private SpringLayout sl_panelContent;
	private JLabel lblOr;
	private JButton btnNewCourseDetail;
	private JLabel lblTitleDetail;
	private JLabel lblCourseID;
	private JTextField textFieldCourseID;
	private DefaultTableModel model;
	private TableColumnModel tcm;
	private JLabel lblCourseName;
	private JTextField textFieldCourseName;
	private JLabel lblDateStarted;
	private DatePicker datepickerDateStarted;
	private JLabel lblDateFinished;
	private DatePicker datepickerDateFinished;
	private JLabel lblWeek;
	private JComboBox comboBoxWeek;
	private JLabel lblClassroom;
	private JTextField textFieldClassroom;
	private DatePickerSettings dateSettings1;
	private DatePickerSettings dateSettings2;
	private LocalDate date1;
	private LocalDate date2;
	private JLabel lblTimeFinished;
	private JLabel lblTimeStarted;
	private TimePicker timepickerTimeFinished;
	private TimePicker timepickerTimeStarted;
	private JScrollPane scrollPaneCourses;
	private JButton btnShowEnrolled;
	private JButton btnShowNonEnrolled;
	private JButton btnAddNew;
	private JButton btnImportCSV;
	private JButton btnSave;
	private JLabel lblStatus;
	private boolean hasDetail = false;
	private int optionInput = 0;
	private List<Course> listCourse;
	private JScrollPane spEnrolled;
	private String editCourse;
	private JScrollPane spNonEnrolled;
	private JButton btnOp1;
	private JButton btnOp2;
	private boolean addNew = false;
	private JButton btnOp3;
	private JTextField textFieldAddNew;
	private JComboBox comboBoxAttendance;
	private JLabel lblChooseCourse;
	private JScrollPane spAttendance;
	private JTextField textFieldAddName;
	private JButton btnExport;
	private JButton btnImport;

	/**
	 * Create the panel.
	 */
	public TeacherJPanel() {
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
		
		btnShowCourses = new JButton("Xem danh s\u00E1ch m\u00F4n h\u1ECDc");
		sl_panelMenu.putConstraint(SpringLayout.NORTH, btnShowCourses, 0, SpringLayout.NORTH, panelMenu);
		sl_panelMenu.putConstraint(SpringLayout.WEST, btnShowCourses, 0, SpringLayout.WEST, panelMenu);
		sl_panelMenu.putConstraint(SpringLayout.SOUTH, btnShowCourses, 60, SpringLayout.NORTH, panelMenu);
		sl_panelMenu.putConstraint(SpringLayout.EAST, btnShowCourses, 0, SpringLayout.EAST, panelMenu);
		
		panelMenu.add(btnShowCourses);
		btnShowCourses.setForeground(new Color(255, 255, 255));
		btnShowCourses.setBackground(new Color(255, 140, 0));
		btnShowCourses.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnShowCourses.setBorder(BorderFactory.createEmptyBorder());
		
		btnEditCourses = new JButton("Thêm/Chỉnh sửa môn học");
		sl_panelMenu.putConstraint(SpringLayout.NORTH, btnEditCourses, 0, SpringLayout.SOUTH, btnShowCourses);
		sl_panelMenu.putConstraint(SpringLayout.WEST, btnEditCourses, 0, SpringLayout.WEST, panelMenu);
		sl_panelMenu.putConstraint(SpringLayout.SOUTH, btnEditCourses, 60, SpringLayout.SOUTH, btnShowCourses);
		sl_panelMenu.putConstraint(SpringLayout.EAST, btnEditCourses, 0, SpringLayout.EAST, panelMenu);
		
		panelMenu.add(btnEditCourses);
		btnEditCourses.setForeground(new Color(255, 255, 255));
		btnEditCourses.setBackground(new Color(112, 128, 144));
		btnEditCourses.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnEditCourses.setBorder(BorderFactory.createEmptyBorder());
		
		btnShowAttendance = new JButton("Xem kết quả điểm danh");
		sl_panelMenu.putConstraint(SpringLayout.NORTH, btnShowAttendance, 0, SpringLayout.SOUTH, btnEditCourses);
		sl_panelMenu.putConstraint(SpringLayout.WEST, btnShowAttendance, 0, SpringLayout.WEST, panelMenu);
		sl_panelMenu.putConstraint(SpringLayout.SOUTH, btnShowAttendance, 60, SpringLayout.SOUTH, btnEditCourses);
		sl_panelMenu.putConstraint(SpringLayout.EAST, btnShowAttendance, 0, SpringLayout.EAST, panelMenu);
		
		panelMenu.add(btnShowAttendance);
		btnShowAttendance.setBackground(new Color(112, 128, 144));
		btnShowAttendance.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnShowAttendance.setForeground(Color.WHITE);
		btnShowAttendance.setBorder(BorderFactory.createEmptyBorder());
		
		panelContent = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelContent, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panelContent, 0, SpringLayout.EAST, panelMenu);
		springLayout.putConstraint(SpringLayout.EAST, panelContent, 0, SpringLayout.EAST, this);
		panelContent.setBackground(new Color(255, 255, 255));

		springLayout.putConstraint(SpringLayout.SOUTH, panelContent, 0, SpringLayout.SOUTH, this);
		add(panelContent);
		sl_panelContent = new SpringLayout();
		panelContent.setLayout(sl_panelContent);
		listCourse = CourseDAO.getCourseList(); 
		
		addShowCourse();
		
		btnShowCourses.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelContent.removeAll();
				hasDetail = false;
				addShowCourse();
				btnShowCourses.setBackground(new Color(255, 140, 0));
				btnShowAttendance.setBackground(new Color(112, 128, 144));
				btnEditCourses.setBackground(new Color(112, 128, 144));
				panelContent.validate();
				panelContent.repaint();
			}
		});
		btnEditCourses.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelContent.removeAll();
				addEditCourses();
				hasDetail = false;
				btnShowCourses.setBackground(new Color(112, 128, 144));
				btnShowAttendance.setBackground(new Color(112, 128, 144));
				btnEditCourses.setBackground(new Color(255, 140, 0));
				panelContent.validate();
				panelContent.repaint();
			}
		});
		
		btnShowAttendance.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelContent.removeAll();
				addAttendance();
				hasDetail = false;
				btnShowCourses.setBackground(new Color(112, 128, 144));
				btnEditCourses.setBackground(new Color(112, 128, 144));
				btnShowAttendance.setBackground(new Color(255, 140, 0));
				panelContent.validate();
				panelContent.repaint();
			}
		});  
	}
	void addShowCourse(){
		lblPageTitle = new JLabel("Danh sách môn học");
		sl_panelContent.putConstraint(SpringLayout.NORTH, lblPageTitle, 20, SpringLayout.NORTH, panelContent);
		sl_panelContent.putConstraint(SpringLayout.WEST, lblPageTitle, 40, SpringLayout.WEST, panelContent);
		
		panelContent.add(lblPageTitle);
		lblPageTitle.setForeground(new Color(0, 191, 255));
		lblPageTitle.setBackground(Color.WHITE);
		lblPageTitle.setFont(new Font("Segoe UI Light", Font.PLAIN, 28));
		
		lblSearch = new JLabel("T\u00ECm ki\u1EBFm m\u00F4n h\u1ECDc");
		sl_panelContent.putConstraint(SpringLayout.NORTH, lblSearch, 80, SpringLayout.NORTH, panelContent);
		sl_panelContent.putConstraint(SpringLayout.WEST, lblSearch, 40, SpringLayout.WEST, panelContent);
		panelContent.add(lblSearch);
		lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		
		textFieldSearch = new JTextField();
		sl_panelContent.putConstraint(SpringLayout.NORTH, textFieldSearch, 78, SpringLayout.NORTH, panelContent);
		sl_panelContent.putConstraint(SpringLayout.WEST, textFieldSearch, 10, SpringLayout.EAST, lblSearch);
		sl_panelContent.putConstraint(SpringLayout.SOUTH, textFieldSearch, 112, SpringLayout.NORTH, panelContent);
		sl_panelContent.putConstraint(SpringLayout.EAST, textFieldSearch, -400, SpringLayout.EAST, panelContent);
		
		panelContent.add(textFieldSearch);
		textFieldSearch.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textFieldSearch.setColumns(10);
		
		btnSearch = new JButton("T\u00ECm");
		sl_panelContent.putConstraint(SpringLayout.NORTH, btnSearch, 0, SpringLayout.NORTH, textFieldSearch);
		sl_panelContent.putConstraint(SpringLayout.WEST, btnSearch, 10, SpringLayout.EAST, textFieldSearch);
		sl_panelContent.putConstraint(SpringLayout.SOUTH, btnSearch, 0, SpringLayout.SOUTH, textFieldSearch);
		sl_panelContent.putConstraint(SpringLayout.EAST, btnSearch, -300, SpringLayout.EAST, panelContent);
		
		panelContent.add(btnSearch);
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnSearch.setBackground(new Color(112, 128, 144));
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBorder(BorderFactory.createEmptyBorder());
		
		btnAddCourse = new JButton("Thêm môn học mới");
		sl_panelContent.putConstraint(SpringLayout.NORTH, btnAddCourse, 0, SpringLayout.NORTH, btnSearch);
		sl_panelContent.putConstraint(SpringLayout.WEST, btnAddCourse, 20, SpringLayout.EAST, btnSearch);
		sl_panelContent.putConstraint(SpringLayout.SOUTH, btnAddCourse, 0, SpringLayout.SOUTH, btnSearch);
		sl_panelContent.putConstraint(SpringLayout.EAST, btnAddCourse, -40, SpringLayout.EAST, panelContent);
		
		panelContent.add(btnAddCourse);
		btnAddCourse.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnAddCourse.setBackground(new Color(255, 140, 0));
		btnAddCourse.setForeground(new Color(255, 255, 255));
		btnAddCourse.setBorder(BorderFactory.createEmptyBorder());
		Vector<String> columnNames = new Vector<String>();
        columnNames.addElement("Mã môn học");
        columnNames.addElement("Tên môn học");
        columnNames.addElement("Tình trạng");
        columnNames.addElement("#");
        Vector<Vector> rowData = new Vector<Vector>();
		tableCourses = new JTable(rowData, columnNames){
		    public boolean isCellEditable(int row, int column){  
		        return column == 3;  
		      }  
		};
		model = (DefaultTableModel) tableCourses.getModel();

        model.setDataVector(null,columnNames);
        
        for(int i=0; i<listCourse.size(); i++){ 
        	model.addRow(Course.getTableRow(i, listCourse));
        }

        // Make cell centered
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tcm = tableCourses.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(150);
		tcm.getColumn(1).setPreferredWidth(370); 
		tcm.getColumn(2).setPreferredWidth(200);    
		tcm.getColumn(3).setPreferredWidth(150);
		
		tableCourses.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tableCourses.setRowHeight(30);
		
        //model = (DefaultTableModel) tableCourses.getModel();
        tableCourses.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        tableCourses.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        tableCourses.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        
        scrollPaneCourses = new JScrollPane(tableCourses);
        sl_panelContent.putConstraint(SpringLayout.NORTH, scrollPaneCourses, 140, SpringLayout.NORTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.WEST, scrollPaneCourses, 40, SpringLayout.WEST, panelContent);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, scrollPaneCourses, -40, SpringLayout.SOUTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.EAST, scrollPaneCourses, -40, SpringLayout.EAST, panelContent);
        
        panelContent.add(scrollPaneCourses);
        tableCourses.setSelectionBackground(new Color(255, 235, 205));
        

		tableCourses.getColumn("#").setCellRenderer(new ButtonRenderer());
		tableCourses.getColumn("#").setCellEditor(new ButtonEditor(new JCheckBox()));
		

		tableCourses.getModel().addTableModelListener(new TableModelListener() {

			  public void tableChanged(TableModelEvent e) {
			     // your code goes here, whatever you want to do when something changes in the table
				  //System.out.println(model.getValueAt(e.getFirstRow(),0));
				  hasDetail = false;
				  
				  panelContent.removeAll();
				  addEditCourses();
				  comboBox.setSelectedIndex(e.getFirstRow()+1);
				  btnShowCourses.setBackground(new Color(112, 128, 144));
				  btnShowAttendance.setBackground(new Color(112, 128, 144));
				  btnEditCourses.setBackground(new Color(255, 140, 0));
				  panelContent.validate();
				  panelContent.repaint();
				  
			  }
			});
		
		btnAddCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelContent.removeAll();
				addNew = true;
				addEditCourses();
				Date date = new Date();
				Time time = new Time(date.getTime());
				System.out.println(time.toString());
				addDetailCourse("Thêm môn học mới","","--","",date,date,2,time,time,"");
				hasDetail = true;
				editCourse="";
				btnShowCourses.setBackground(new Color(112, 128, 144));
				btnShowAttendance.setBackground(new Color(112, 128, 144));
				btnEditCourses.setBackground(new Color(255, 140, 0));
				panelContent.validate();
				panelContent.repaint();
			}
		});
		
	}
	
	void removeShowCourse(){
		panelContent.remove(lblPageTitle);
		panelContent.remove(lblSearch);
		panelContent.remove(textFieldSearch);
		panelContent.remove(btnSearch);
		panelContent.remove(btnAddCourse);
		panelContent.remove(lblSearch);
		panelContent.remove(scrollPaneCourses);
        panelContent.remove(scrollPaneCourses);
	}
	
	void addEditCourses(){
		lblSearchDetail = new JLabel("Chỉnh sửa môn học");
        sl_panelContent.putConstraint(SpringLayout.NORTH, lblSearchDetail, 20, SpringLayout.NORTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.WEST, lblSearchDetail, 40, SpringLayout.WEST, panelContent);
        lblSearchDetail.setBackground(new Color(255, 255, 255));
        lblSearchDetail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelContent.add(lblSearchDetail);
        
        Vector<String> course = new Vector<String>();
        course.add("");
        //listCourses.clear();
        listCourse = CourseDAO.getCourseList(); 
        for(int i=0; i< listCourse.size(); i++){ 
        	Course c= listCourse.get(i); 
        	course.addElement(c.getmCourseID()+" - "+ c.getmName());
        }
        
        
        
        
        comboBox = new JComboBox(course);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        sl_panelContent.putConstraint(SpringLayout.NORTH, comboBox, -5, SpringLayout.NORTH, lblSearchDetail);
        sl_panelContent.putConstraint(SpringLayout.WEST, comboBox, 10, SpringLayout.EAST, lblSearchDetail);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, comboBox, 5, SpringLayout.SOUTH, lblSearchDetail);
        sl_panelContent.putConstraint(SpringLayout.EAST, comboBox, 600, SpringLayout.WEST, panelContent);
        //comboBox.setEditable(true);
        panelContent.add(comboBox);
        
        lblOr = new JLabel("hoặc");
        lblOr.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblOr.setHorizontalAlignment(SwingConstants.CENTER);
        sl_panelContent.putConstraint(SpringLayout.NORTH, lblOr, 0, SpringLayout.NORTH, lblSearchDetail);
        sl_panelContent.putConstraint(SpringLayout.WEST, lblOr, 20, SpringLayout.EAST, comboBox);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, lblOr, 0, SpringLayout.SOUTH, lblSearchDetail);
        panelContent.add(lblOr);
        
        btnNewCourseDetail = new JButton("Thêm môn học mới");
        btnNewCourseDetail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnNewCourseDetail.setForeground(new Color(255, 255, 255));
        btnNewCourseDetail.setBackground(new Color(255, 140, 0));
        sl_panelContent.putConstraint(SpringLayout.NORTH, btnNewCourseDetail, 0, SpringLayout.NORTH, comboBox);
        sl_panelContent.putConstraint(SpringLayout.WEST, btnNewCourseDetail, 20, SpringLayout.EAST, lblOr);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, btnNewCourseDetail, 0, SpringLayout.SOUTH, comboBox);
        sl_panelContent.putConstraint(SpringLayout.EAST, btnNewCourseDetail, -40, SpringLayout.EAST, panelContent);
        panelContent.add(btnNewCourseDetail);
        btnNewCourseDetail.setBorder(BorderFactory.createEmptyBorder());
        
        comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (comboBox.getSelectedIndex()!= 0){
					addNew = false;
					int index = comboBox.getSelectedIndex() - 1;
					Course c = listCourse.get(index);
					Date date = new Date();
					//Time time = new Time(date.getTime());
					//System.out.println(time.toString());
					String status ="";
					if (c.getmDateFinished().compareTo(date)< 0){
						status = "Đã kết thúc";
					}
					else if (c.getmDateStarted().compareTo(date)>0){
						status = "Chưa bắt đầu";
					}else {
						status = "Đang diễn ra";
					}
					if (hasDetail){
						removeCourseDetail();
					}
					if (optionInput == 1){
						panelContent.remove(spEnrolled);
						panelContent.remove(btnOp1);
					}else if(optionInput == 2){
						panelContent.remove(spNonEnrolled);
						panelContent.remove(btnOp2);
					}else if(optionInput == 3){
						panelContent.remove(textFieldAddNew);
						panelContent.remove(textFieldAddName);
						panelContent.remove(btnOp3);
					}else if(optionInput ==4){
						panelContent.remove(btnExport);
						panelContent.remove(btnImport);
					}
					hasDetail = true;
					editCourse = c.getmCourseID();
					addDetailCourse("Chỉnh sửa môn học",c.getmCourseID(),status,c.getmName(),
							c.getmDateStarted(),c.getmDateFinished(),c.getmWeekday(),
							c.getmTimeStarted(),c.getmTimeFinished(),c.getmClassroom());
					optionInput = 0;
					panelContent.validate();
					panelContent.repaint();
				}
			}
		});
        
        btnNewCourseDetail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				panelContent.removeAll();
				addNew = true;
				addEditCourses();
				if (hasDetail){
					removeCourseDetail();
				}

				Date date = new Date();
				Time time = new Time(date.getTime());
				System.out.println(time.toString());
				addDetailCourse("Thêm môn học mới","","--","",date,date,2,time,time,"");
				hasDetail = true;
				btnShowCourses.setBackground(new Color(112, 128, 144));
				btnShowAttendance.setBackground(new Color(112, 128, 144));
				btnEditCourses.setBackground(new Color(255, 140, 0));
				panelContent.validate();
				panelContent.repaint();
			}
		});

	}
	void removeEditCourse(){
        panelContent.add(lblSearchDetail);
        panelContent.add(comboBox);
        panelContent.add(lblOr);
        panelContent.add(btnNewCourseDetail);
        comboBox.removeActionListener(comboBox);
        btnNewCourseDetail.setBorder(BorderFactory.createEmptyBorder());
	}
	
	void addDetailCourse(String title, String courseID, String status, String courseName, Date dateStarted, Date dateFinished, int week, Time timeStarted, Time timeFinished, String classroom ){
        lblTitleDetail = new JLabel(title);
        sl_panelContent.putConstraint(SpringLayout.NORTH, lblTitleDetail, 70, SpringLayout.NORTH, panelContent);
        lblTitleDetail.setForeground(new Color(0, 191, 255));
        lblTitleDetail.setFont(new Font("Segoe UI Light", Font.PLAIN, 28));
        lblTitleDetail.setBackground(new Color(255, 255, 255));
        sl_panelContent.putConstraint(SpringLayout.WEST, lblTitleDetail, 40, SpringLayout.WEST, panelContent);
        panelContent.add(lblTitleDetail);
        lblCourseID = new JLabel("Mã môn học");
        sl_panelContent.putConstraint(SpringLayout.NORTH, lblCourseID, 140, SpringLayout.NORTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.WEST, lblCourseID, 40, SpringLayout.WEST, panelContent);
        lblCourseID.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelContent.add(lblCourseID);
        
        textFieldCourseID = new JTextField(courseID);
        sl_panelContent.putConstraint(SpringLayout.NORTH, textFieldCourseID, -5, SpringLayout.NORTH, lblCourseID);
        sl_panelContent.putConstraint(SpringLayout.WEST, textFieldCourseID, 15, SpringLayout.EAST, lblCourseID);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, textFieldCourseID, 5, SpringLayout.SOUTH, lblCourseID);
        sl_panelContent.putConstraint(SpringLayout.EAST, textFieldCourseID, 350, SpringLayout.WEST, panelContent);
        panelContent.add(textFieldCourseID);
        textFieldCourseID.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textFieldCourseID.setColumns(10);
        
        lblStatus = new JLabel("Tình trạng: "+ status);
        sl_panelContent.putConstraint(SpringLayout.EAST, lblStatus, 630, SpringLayout.WEST, panelContent);
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        sl_panelContent.putConstraint(SpringLayout.NORTH, lblStatus, 0, SpringLayout.NORTH, lblCourseID);
        sl_panelContent.putConstraint(SpringLayout.WEST, lblStatus, 40, SpringLayout.EAST, textFieldCourseID);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, lblStatus, 0, SpringLayout.SOUTH, lblCourseID);
        panelContent.add(lblStatus);
        
        /*
        JButton btnDeleteCourse = new JButton("Xóa môn học");
        sl_panelContent.putConstraint(SpringLayout.NORTH, btnDeleteCourse, 0, SpringLayout.NORTH, textFieldCourseID);
        sl_panelContent.putConstraint(SpringLayout.WEST, btnDeleteCourse, 45, SpringLayout.EAST, lblStatus);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, btnDeleteCourse, 0, SpringLayout.SOUTH, textFieldCourseID);
        sl_panelContent.putConstraint(SpringLayout.EAST, btnDeleteCourse, -40, SpringLayout.EAST, panelContent);
        btnDeleteCourse.setForeground(new Color(255, 255, 255));
        btnDeleteCourse.setBackground(new Color(255, 99, 71));
        btnDeleteCourse.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnDeleteCourse.setBorder(BorderFactory.createEmptyBorder());
        panelContent.add(btnDeleteCourse);
        */
        lblCourseName = new JLabel("Tên môn học");
        sl_panelContent.putConstraint(SpringLayout.NORTH, lblCourseName, 50, SpringLayout.NORTH, lblCourseID);
        sl_panelContent.putConstraint(SpringLayout.WEST, lblCourseName, 40, SpringLayout.WEST, panelContent);
        lblCourseName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelContent.add(lblCourseName);
        
        textFieldCourseName = new JTextField(courseName);
        sl_panelContent.putConstraint(SpringLayout.NORTH, textFieldCourseName, -5, SpringLayout.NORTH, lblCourseName);
        sl_panelContent.putConstraint(SpringLayout.WEST, textFieldCourseName, 0, SpringLayout.WEST, textFieldCourseID);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, textFieldCourseName, 5, SpringLayout.SOUTH, lblCourseName);
        sl_panelContent.putConstraint(SpringLayout.EAST, textFieldCourseName, -350, SpringLayout.EAST, panelContent);
        panelContent.add(textFieldCourseName);
        textFieldCourseName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textFieldCourseName.setColumns(10);
        
        lblDateStarted = new JLabel("Ngày bắt đầu");
        sl_panelContent.putConstraint(SpringLayout.NORTH, lblDateStarted, 50, SpringLayout.NORTH, lblCourseName);
        sl_panelContent.putConstraint(SpringLayout.WEST, lblDateStarted, 40, SpringLayout.WEST, panelContent);
        lblDateStarted.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelContent.add(lblDateStarted);
        
        dateSettings1 = new DatePickerSettings();
        datepickerDateStarted = new DatePicker(dateSettings1);
        dateSettings1.setFontValidDate(new Font("Segoe UI", Font.PLAIN, 18));
        //System.out.println(dateStarted.toString());
        if (courseID.equals("")){
        	date1 = dateStarted.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        	date2 = dateStarted.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }else{
        	date1= LocalDate.parse(dateStarted.toString());
        	date2= LocalDate.parse(dateFinished.toString());
        }
		datepickerDateStarted.setDate(date1);
        sl_panelContent.putConstraint(SpringLayout.NORTH, datepickerDateStarted, -5, SpringLayout.NORTH, lblDateStarted);
        sl_panelContent.putConstraint(SpringLayout.WEST, datepickerDateStarted, 0, SpringLayout.WEST, textFieldCourseID);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, datepickerDateStarted, 5, SpringLayout.SOUTH, lblDateStarted);
        sl_panelContent.putConstraint(SpringLayout.EAST, datepickerDateStarted, 16, SpringLayout.EAST, textFieldCourseID);
        panelContent.add(datepickerDateStarted);
        datepickerDateStarted.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        //datepickerDateStarted.setColumns(10);
        
        lblDateFinished = new JLabel("Ngày kết thúc");
        sl_panelContent.putConstraint(SpringLayout.NORTH, lblDateFinished, 0, SpringLayout.NORTH, lblDateStarted);
        sl_panelContent.putConstraint(SpringLayout.WEST, lblDateFinished, 0, SpringLayout.WEST, lblStatus);
        lblDateFinished.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelContent.add(lblDateFinished);
        
        dateSettings2 = new DatePickerSettings();
        datepickerDateFinished = new DatePicker(dateSettings2);
        dateSettings2.setFontValidDate(new Font("Segoe UI", Font.PLAIN, 18));
        //date2 = dateFinished.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        datepickerDateFinished.setDate(date2);
        sl_panelContent.putConstraint(SpringLayout.NORTH, datepickerDateFinished, 0, SpringLayout.NORTH, datepickerDateStarted);
        sl_panelContent.putConstraint(SpringLayout.WEST, datepickerDateFinished, 10, SpringLayout.EAST, lblDateFinished);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, datepickerDateFinished, 0, SpringLayout.SOUTH, datepickerDateStarted);
        sl_panelContent.putConstraint(SpringLayout.EAST, datepickerDateFinished, 220, SpringLayout.EAST, lblDateFinished);
        datepickerDateFinished.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelContent.add(datepickerDateFinished);
        //datepickerDateFinished.setColumns(10);
        
        lblWeek = new JLabel("Thứ");
        sl_panelContent.putConstraint(SpringLayout.NORTH, lblWeek, 50, SpringLayout.NORTH, lblDateStarted);
        sl_panelContent.putConstraint(SpringLayout.WEST, lblWeek, 40, SpringLayout.WEST, panelContent);
        lblWeek.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelContent.add(lblWeek);
        
        String[] arrWeekdays = { "Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm","Thứ sáu","Thứ bảy","Chủ nhật" };
        comboBoxWeek = new JComboBox<String>(arrWeekdays);
        sl_panelContent.putConstraint(SpringLayout.NORTH, comboBoxWeek, -5, SpringLayout.NORTH, lblWeek);
        sl_panelContent.putConstraint(SpringLayout.WEST, comboBoxWeek, 0, SpringLayout.WEST, datepickerDateStarted);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, comboBoxWeek, 5, SpringLayout.SOUTH, lblWeek);
        sl_panelContent.putConstraint(SpringLayout.EAST, comboBoxWeek, 0, SpringLayout.EAST, datepickerDateStarted);
        comboBoxWeek.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelContent.add(comboBoxWeek);
        comboBoxWeek.setSelectedIndex(week-2);
        
        lblClassroom = new JLabel("Phòng học");
        sl_panelContent.putConstraint(SpringLayout.NORTH, lblClassroom, 0, SpringLayout.NORTH, lblCourseName);
        sl_panelContent.putConstraint(SpringLayout.WEST, lblClassroom, 25, SpringLayout.EAST, textFieldCourseName);
        lblClassroom.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelContent.add(lblClassroom);
        
        textFieldClassroom = new JTextField(classroom);
        sl_panelContent.putConstraint(SpringLayout.NORTH, textFieldClassroom, 0, SpringLayout.NORTH, textFieldCourseName);
        sl_panelContent.putConstraint(SpringLayout.WEST, textFieldClassroom, 15, SpringLayout.EAST, lblClassroom);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, textFieldClassroom, 0, SpringLayout.SOUTH, textFieldCourseName);
        sl_panelContent.putConstraint(SpringLayout.EAST, textFieldClassroom, 200, SpringLayout.EAST, lblClassroom);
        textFieldClassroom.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelContent.add(textFieldClassroom);
        textFieldClassroom.setColumns(10);
        
        lblTimeStarted = new JLabel("Giờ bắt đầu");
        sl_panelContent.putConstraint(SpringLayout.NORTH, lblTimeStarted, 0, SpringLayout.NORTH, lblWeek);
        sl_panelContent.putConstraint(SpringLayout.WEST, lblTimeStarted, 25, SpringLayout.EAST, comboBoxWeek);
        lblTimeStarted.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelContent.add(lblTimeStarted);
        
        TimePickerSettings timeSettings1 = new TimePickerSettings();
        timeSettings1.initialTime = timeStarted.toLocalTime();
        timepickerTimeStarted = new TimePicker(timeSettings1);
        sl_panelContent.putConstraint(SpringLayout.NORTH, timepickerTimeStarted, 0, SpringLayout.NORTH, comboBoxWeek);
        sl_panelContent.putConstraint(SpringLayout.WEST, timepickerTimeStarted, 0, SpringLayout.WEST, datepickerDateFinished);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, timepickerTimeStarted, 0, SpringLayout.SOUTH, comboBoxWeek);
        //sl_panelContent.putConstraint(SpringLayout.EAST, timepickerTimeStarted, 220, SpringLayout.EAST, lblDateFinished);
        panelContent.add(timepickerTimeStarted);
        
        lblTimeFinished = new JLabel("Giờ kết thúc");
        sl_panelContent.putConstraint(SpringLayout.NORTH, lblTimeFinished, 0, SpringLayout.NORTH, lblTimeStarted);
        sl_panelContent.putConstraint(SpringLayout.WEST, lblTimeFinished, 25, SpringLayout.EAST, timepickerTimeStarted);
        lblTimeFinished.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelContent.add(lblTimeFinished);
        
        
        TimePickerSettings timeSettings2 = new TimePickerSettings();
        timeSettings2.initialTime = timeFinished.toLocalTime();
        timepickerTimeFinished = new TimePicker(timeSettings2);
        sl_panelContent.putConstraint(SpringLayout.NORTH, timepickerTimeFinished, 0, SpringLayout.NORTH, timepickerTimeStarted);
        sl_panelContent.putConstraint(SpringLayout.WEST, timepickerTimeFinished, 25, SpringLayout.EAST, lblTimeFinished);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, timepickerTimeFinished, 0, SpringLayout.SOUTH, timepickerTimeStarted);
        sl_panelContent.putConstraint(SpringLayout.EAST, timepickerTimeFinished, -40, SpringLayout.EAST, panelContent);
        panelContent.add(timepickerTimeFinished);
        
        btnShowEnrolled = new JButton("Danh sách sinh viên đăng ký");
        btnShowEnrolled.setBackground(new Color(112, 128, 144));
        btnShowEnrolled.setForeground(Color.WHITE);
        sl_panelContent.putConstraint(SpringLayout.NORTH, btnShowEnrolled, 60, SpringLayout.NORTH, lblWeek);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, btnShowEnrolled, 100, SpringLayout.NORTH, lblWeek);
        sl_panelContent.putConstraint(SpringLayout.WEST, btnShowEnrolled, 40, SpringLayout.WEST, panelContent);
        sl_panelContent.putConstraint(SpringLayout.EAST, btnShowEnrolled, 0, SpringLayout.EAST, comboBoxWeek);
        btnShowEnrolled.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnShowEnrolled.setBorder(BorderFactory.createEmptyBorder());
        panelContent.add(btnShowEnrolled);
        
        btnShowNonEnrolled = new JButton("Danh sách sinh viên chưa đăng ký");
        sl_panelContent.putConstraint(SpringLayout.NORTH, btnShowNonEnrolled, 50, SpringLayout.NORTH, btnShowEnrolled);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, btnShowNonEnrolled, 90, SpringLayout.NORTH, btnShowEnrolled);
        btnShowNonEnrolled.setBackground(new Color(112, 128, 144));
        btnShowNonEnrolled.setForeground(new Color(255, 255, 255));
        sl_panelContent.putConstraint(SpringLayout.WEST, btnShowNonEnrolled, 0, SpringLayout.WEST, btnShowEnrolled);
        sl_panelContent.putConstraint(SpringLayout.EAST, btnShowNonEnrolled, 0, SpringLayout.EAST, btnShowEnrolled);
        btnShowNonEnrolled.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnShowNonEnrolled.setBorder(BorderFactory.createEmptyBorder());
        panelContent.add(btnShowNonEnrolled);
        
        btnAddNew = new JButton("Thêm sinh viên mới");
        btnAddNew.setBackground(new Color(112, 128, 144));
        btnAddNew.setForeground(new Color(255, 255, 255));
        btnAddNew.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        sl_panelContent.putConstraint(SpringLayout.NORTH, btnAddNew, 50, SpringLayout.NORTH, btnShowNonEnrolled);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, btnAddNew, 90, SpringLayout.NORTH, btnShowNonEnrolled);
        sl_panelContent.putConstraint(SpringLayout.WEST, btnAddNew, 0, SpringLayout.WEST, btnShowNonEnrolled);
        sl_panelContent.putConstraint(SpringLayout.EAST, btnAddNew, 0, SpringLayout.EAST, btnShowNonEnrolled);
        btnAddNew.setBorder(BorderFactory.createEmptyBorder());
        panelContent.add(btnAddNew);
        
        btnImportCSV = new JButton("Import từ CSV");
        btnImportCSV.setBackground(new Color(112, 128, 144));
        btnImportCSV.setForeground(new Color(255, 255, 255));
        btnImportCSV.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        sl_panelContent.putConstraint(SpringLayout.NORTH, btnImportCSV, 50, SpringLayout.NORTH, btnAddNew);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, btnImportCSV, 90, SpringLayout.NORTH, btnAddNew);
        sl_panelContent.putConstraint(SpringLayout.WEST, btnImportCSV, 0, SpringLayout.WEST, btnAddNew);
        sl_panelContent.putConstraint(SpringLayout.EAST, btnImportCSV, 0, SpringLayout.EAST, btnAddNew);
        btnImportCSV.setBorder(BorderFactory.createEmptyBorder());
        panelContent.add(btnImportCSV);
      
        btnSave = new JButton("Lưu thay đổi");
        btnSave.setBackground(new Color(255, 99, 71));
        btnSave.setForeground(new Color(255, 255, 255));
        btnSave.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        sl_panelContent.putConstraint(SpringLayout.NORTH, btnSave, -80, SpringLayout.SOUTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.WEST, btnSave, 300, SpringLayout.WEST, panelContent);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, btnSave, -40, SpringLayout.SOUTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.EAST, btnSave, -300, SpringLayout.EAST, panelContent);
        btnSave.setBorder(BorderFactory.createEmptyBorder());
        panelContent.add(btnSave);
        
        btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean matched = false;
				boolean accept = false;
				LocalDate dateActual = null;
				LocalDate dateActualF = null;
				if (textFieldCourseID.getText().equals("") || textFieldCourseName.getText().equals("") || textFieldClassroom.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Xin hãy điền đầy đủ thông tin môn học.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}else{
					if (addNew){
						for (int i = 0; i<listCourse.size(); ++i){
							if (textFieldCourseID.getText().equals(listCourse.get(i).getmCourseID())){
								matched = true;
							}
						}
					}
					if (matched){
						JOptionPane.showMessageDialog(null, "Mã môn học đã tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					}else{
						if (timepickerTimeStarted.getTime().compareTo(timepickerTimeFinished.getTime()) >= 0){
							JOptionPane.showMessageDialog(null, "Giờ bắt đầu phải bé hơn giờ kết thúc", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						}else{
							int dowstudy = (comboBoxWeek.getSelectedIndex() != 6) ?(comboBoxWeek.getSelectedIndex()+ 2) : 1;
							int dow1 = datepickerDateStarted.getDate().get(WeekFields.SUNDAY_START.dayOfWeek());
							if (dow1 == dowstudy){
								dateActual = datepickerDateStarted.getDate();
							}else if (dow1 < dowstudy){
								dateActual = datepickerDateStarted.getDate().plusDays(dowstudy-dow1);
							}else{
								dateActual = datepickerDateStarted.getDate().plusDays(7 - (dow1-dowstudy));
							}
							dateActualF = dateActual.plusDays(7*15);
							//System.out.println(dowstudy);
							//System.out.println(dow1);
							//System.out.println(dateActual);
							//System.out.println(dateActualF);
							Course c = new Course();
							c.setmCourseID(textFieldCourseID.getText());
							c.setmName(textFieldCourseName.getText());
							c.setmDateStarted(Date.from(dateActual.atStartOfDay(ZoneId.systemDefault()).toInstant()));
							c.setmDateFinished(Date.from(dateActualF.atStartOfDay(ZoneId.systemDefault()).toInstant()));
							
							if (dowstudy == 1){
								c.setmWeekday(8);
							}else{
								c.setmWeekday(dowstudy);
							}
							c.setmTimeStarted(Time.valueOf(timepickerTimeStarted.getTime()));
							c.setmTimeFinished(Time.valueOf(timepickerTimeFinished.getTime()));
							c.setmClassroom(textFieldClassroom.getText());
							
							
							boolean res = false;
							if (addNew){
								res= CourseDAO.addNew(c);
							}else{
								res= CourseDAO.update(c); 
							}
							
							if (res == true) { 
								JOptionPane.showMessageDialog(null, "Thêm/cập nhật thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
								listCourse.clear();
								listCourse = CourseDAO.getCourseList();
								panelContent.removeAll();
								addShowCourse();
								btnShowCourses.setBackground(new Color(255, 140, 0));
								btnShowAttendance.setBackground(new Color(112, 128, 144));
								btnEditCourses.setBackground(new Color(112, 128, 144));
								panelContent.validate();
								panelContent.repaint();
							} 
							else { 
								JOptionPane.showMessageDialog(null, "Thêm/cập nhật thất bại.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
				}
				
		
			}
		});
        
        btnShowEnrolled.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (editCourse.equals("")){
					JOptionPane.showMessageDialog(null, "Xin hãy hoàn thành thông tin môn học. Sau đó bấm 'Lưu thay đổi' để tiếp tục.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}else{
					addEnrolledStudents(editCourse);
					panelContent.validate();
					panelContent.repaint();
				}
			}
		});
        
        btnShowNonEnrolled.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (editCourse.equals("")){
					JOptionPane.showMessageDialog(null, "Xin hãy hoàn thành thông tin môn học. Sau đó bấm 'Lưu thay đổi' để tiếp tục.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}else{
					addNonEnrolledStudents(editCourse);
					panelContent.validate();
					panelContent.repaint();
				}
			}
		});
        
        btnAddNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (editCourse.equals("")){
					JOptionPane.showMessageDialog(null, "Xin hãy hoàn thành thông tin môn học. Sau đó bấm 'Lưu thay đổi' để tiếp tục.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}else{
					addNewStudents(editCourse);
					panelContent.validate();
					panelContent.repaint();
				}
			}
		});
        
        btnImportCSV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (editCourse.equals("")){
					JOptionPane.showMessageDialog(null, "Xin hãy hoàn thành thông tin môn học. Sau đó bấm 'Lưu thay đổi' để tiếp tục.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}else{
					addCSV();
					panelContent.validate();
					panelContent.repaint();
				}
			}
		});
	}	
	void removeCourseDetail(){
        panelContent.remove(lblTitleDetail);
        panelContent.remove(lblCourseID);
        panelContent.remove(textFieldCourseID);
        panelContent.remove(lblStatus);
        //panelContent.remove(btnDeleteCourse);
        panelContent.remove(lblCourseName);
        panelContent.remove(textFieldCourseName);
        panelContent.remove(lblDateStarted);
        panelContent.remove(datepickerDateStarted);
        panelContent.remove(lblDateFinished);
        panelContent.remove(datepickerDateFinished);
        panelContent.remove(lblWeek);
        panelContent.remove(comboBoxWeek);
        panelContent.remove(lblClassroom);
        panelContent.remove(textFieldClassroom);
        panelContent.remove(lblTimeStarted);
        panelContent.remove(timepickerTimeStarted);
        panelContent.remove(lblTimeFinished);
        panelContent.remove(timepickerTimeFinished);
        panelContent.remove(btnShowEnrolled);
        panelContent.remove(btnShowNonEnrolled);
        panelContent.remove(btnAddNew);
        panelContent.remove(btnImportCSV);
        panelContent.remove(btnSave);	
	}
	
	void addEnrolledStudents(String courseID){
		if(optionInput == 2){
			panelContent.remove(spNonEnrolled);
			panelContent.remove(btnOp2);
			panelContent.validate();
			panelContent.repaint();
		}else if(optionInput == 3){
			panelContent.remove(textFieldAddNew);
			panelContent.remove(textFieldAddName);
			panelContent.remove(btnOp3);
			panelContent.validate();
			panelContent.repaint();
		}else if(optionInput == 4){
			panelContent.remove(btnExport);
			panelContent.remove(btnImport);
			panelContent.validate();
			panelContent.repaint();
		}
		
		String colNames[] = {"CheckBox", "MSSV"};
		Object[][] data = {};
		DefaultTableModel dtm;
		dtm = new DefaultTableModel(data,colNames);
	    JTable table = new JTable(dtm);
	    spEnrolled = new JScrollPane(table);
	    TableColumn tc = table.getColumnModel().getColumn(0);
	    tc.setCellEditor(table.getDefaultEditor(Boolean.class));
	    tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	    sl_panelContent.putConstraint(SpringLayout.NORTH, spEnrolled, -300, SpringLayout.SOUTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.WEST, spEnrolled, -350, SpringLayout.EAST, panelContent);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, spEnrolled, -140, SpringLayout.SOUTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.EAST, spEnrolled, -100, SpringLayout.EAST, panelContent);
        
        btnOp1 = new JButton("Xóa sinh viên");
        sl_panelContent.putConstraint(SpringLayout.NORTH, btnOp1, 10, SpringLayout.SOUTH, spEnrolled);
        sl_panelContent.putConstraint(SpringLayout.WEST, btnOp1, 20, SpringLayout.WEST, spEnrolled);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, btnOp1, 36, SpringLayout.SOUTH, spEnrolled);
        sl_panelContent.putConstraint(SpringLayout.EAST, btnOp1, -20, SpringLayout.EAST, spEnrolled);
        btnOp1.setForeground(new Color(255, 255, 255));
        btnOp1.setBackground(new Color(255, 99, 71));
        btnOp1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnOp1.setBorder(BorderFactory.createEmptyBorder());
        panelContent.add(btnOp1);
        
        
        
        List<Attendance> listAttendance = AttendanceDAO.getAttendanceList();
        Vector<String> temp = new Vector<String>();
        boolean matched = false;
        for (int i =0; i < listAttendance.size(); ++i){
        	Attendance a = listAttendance.get(i);
        	if (a.getmCourse().getmCourseID().equals(courseID)){
	        	if (temp.size()>0){
	        		for (int j = 0; j < temp.size(); ++j){
	        			if (a.getmStudent().getmUsername().equals(temp.get(j))){
	        				matched = true;
	        			}
	        		}
	        		if (!matched){
	        			temp.add(a.getmStudent().getmUsername());
	            		dtm.addRow(new Object[]{new Boolean(true),a.getmStudent().getmUsername()});
	        		}
	        		matched = false;
	        	}else{
	        		temp.add(a.getmStudent().getmUsername());
	        		dtm.addRow(new Object[]{new Boolean(true),a.getmStudent().getmUsername()});
	        	}
        	}
        }
        
        
        panelContent.add(spEnrolled);
        optionInput = 1;
        
        Vector <Integer> tempDelete = new Vector<Integer>();
        
        table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			  public void tableChanged(TableModelEvent e) {
				if (e.getFirstRow()!=-1 && e.getColumn() != -1 ){
					tempDelete.add(e.getFirstRow());
				}
			  }
			});
        
        btnOp1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tempDelete.size()>0){
					List <Attendance> lsa = AttendanceDAO.getAttendanceList();
					for (int i = 0; i< tempDelete.size();++i){
						for (int j = 0; j < lsa.size(); ++j){
							Attendance a = lsa.get(j);
							if (a.getmStudent().getmUsername().equals(table.getModel().getValueAt(tempDelete.get(i),1)) &&
									a.getmCourse().getmCourseID().equals(editCourse)){
								boolean res = AttendanceDAO.delete(a.getmAttendanceID());
							}
						}
					}
					for (int i = 0; i< tempDelete.size()-1; ++i){
						for (int j = i+1; j < tempDelete.size(); ++j){
							if (tempDelete.get(i)<tempDelete.get(j)){
								int temp = tempDelete.get(i);
								tempDelete.set(i, tempDelete.get(j));
								tempDelete.set(j, temp);
							}
						}
					}
					String info="";
					for (int i = 0; i < tempDelete.size(); ++i){
						info = info + dtm.getValueAt(tempDelete.get(i), 1) + "\n";
						dtm.removeRow(tempDelete.get(i));
					}
					JOptionPane.showMessageDialog(null, "Xóa các sinh viên: \n"+info, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
        
	}
	void addNonEnrolledStudents(String courseID){
		if(optionInput == 1){
			panelContent.remove(spEnrolled);
			panelContent.remove(btnOp1);
			panelContent.validate();
			panelContent.repaint();
		}else if(optionInput == 3){
			panelContent.remove(textFieldAddNew);
			panelContent.remove(textFieldAddName);
			panelContent.remove(btnOp3);
			panelContent.validate();
			panelContent.repaint();
		}else if(optionInput == 4){
			panelContent.remove(btnExport);
			panelContent.remove(btnImport);
			panelContent.validate();
			panelContent.repaint();
		}
		
		String colNames[] = {"CheckBox", "MSSV"};
		Object[][] data = {};
		DefaultTableModel dtm;
		dtm = new DefaultTableModel(data,colNames);
	    JTable table = new JTable(dtm);
	    spNonEnrolled = new JScrollPane(table);
	    TableColumn tc = table.getColumnModel().getColumn(0);
	    tc.setCellEditor(table.getDefaultEditor(Boolean.class));
	    tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	    sl_panelContent.putConstraint(SpringLayout.NORTH, spNonEnrolled, -300, SpringLayout.SOUTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.WEST, spNonEnrolled, -350, SpringLayout.EAST, panelContent);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, spNonEnrolled, -140, SpringLayout.SOUTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.EAST, spNonEnrolled, -100, SpringLayout.EAST, panelContent);
        
        btnOp2 = new JButton("Thêm sinh viên");
        sl_panelContent.putConstraint(SpringLayout.NORTH, btnOp2, 10, SpringLayout.SOUTH, spNonEnrolled);
        sl_panelContent.putConstraint(SpringLayout.WEST, btnOp2, 20, SpringLayout.WEST, spNonEnrolled);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, btnOp2, 36, SpringLayout.SOUTH, spNonEnrolled);
        sl_panelContent.putConstraint(SpringLayout.EAST, btnOp2, -20, SpringLayout.EAST, spNonEnrolled);
        btnOp2.setForeground(new Color(255, 255, 255));
        btnOp2.setBackground(new Color(255, 99, 71));
        btnOp2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnOp2.setBorder(BorderFactory.createEmptyBorder());
        panelContent.add(btnOp2);
        
        
        List<Attendance> listAttendance = AttendanceDAO.getAttendanceList();
        Vector<String> temp = new Vector<String>();
        boolean matched = false;
        for (int i =0; i < listAttendance.size(); ++i){
        	Attendance a = listAttendance.get(i);
        	if (a.getmCourse().getmCourseID().equals(courseID)){
	        	if (temp.size()>0){
	        		for (int j = 0; j < temp.size(); ++j){
	        			if (a.getmStudent().getmUsername().equals(temp.get(j))){
	        				matched = true;
	        			}
	        		}
	        		if (!matched){
	        			temp.add(a.getmStudent().getmUsername());
	        		}
	        		matched = false;
	        	}else{
	        		temp.add(a.getmStudent().getmUsername());
	        	}
        	}
        }
        
        List<User> lsu = UserDAO.getUserList();
        for (int i = 0; i < lsu.size(); i++){
        	User u = lsu.get(i);
        	for (int j = 0; j <temp.size();++j){
        		if (u.getmUsername().equals(temp.get(j)) && u.getmType() == 0){
        			matched =true;
        			break;
        		}
        	}
        	if (!matched && u.getmType()==0){
        		dtm.addRow(new Object[]{new Boolean(false),u.getmUsername()});
        	}
        	matched = false;
        }
        
        panelContent.add(spNonEnrolled);
        optionInput = 2;
        
        Vector <Integer> tempAdd = new Vector <Integer>();
        table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			  public void tableChanged(TableModelEvent e) {
				if (e.getFirstRow()!=-1 && e.getColumn() != -1 ){
					tempAdd.add(e.getFirstRow());
				}
			  }
			});
        
        btnOp2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tempAdd.size()>0){
					for (int i = 0; i< tempAdd.size();++i){
						for (int k = 0; k < 15; ++k){
							Attendance a1 = new Attendance();
							a1.setmStudent(UserDAO.getUserInfo(dtm.getValueAt(tempAdd.get(i),1).toString()));
							a1.setmCourse(CourseDAO.getCourse(editCourse));
							a1.setmWeek(k+1);
							Date dateDate = CourseDAO.getCourse(editCourse).getmDateStarted();
							LocalDate date= LocalDate.parse(dateDate.toString());
							//System.out.println(i+1);
							date = date.plusDays(k*7);
							Date dateAdd = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
							a1.setmDate(dateAdd);
							a1.setmStatus(0);
							boolean res = AttendanceDAO.addNew(a1); 
						}
					}
					for (int i = 0; i< tempAdd.size()-1; ++i){
						for (int j = i+1; j < tempAdd.size(); ++j){
							if (tempAdd.get(i)<tempAdd.get(j)){
								int temp = tempAdd.get(i);
								tempAdd.set(i, tempAdd.get(j));
								tempAdd.set(j, temp);
							}
						}
					}
					String info="";
					for (int i = 0; i < tempAdd.size(); ++i){
						info = info + dtm.getValueAt(tempAdd.get(i), 1) + "\n";
						dtm.removeRow(tempAdd.get(i));
					}
					JOptionPane.showMessageDialog(null, "Thêm các sinh viên: \n"+info, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
	
	void addNewStudents(String courseID){
		if(optionInput == 1){
			panelContent.remove(spEnrolled);
			panelContent.remove(btnOp1);
			panelContent.validate();
			panelContent.repaint();
		}else if(optionInput == 2){
			panelContent.remove(spNonEnrolled);
			panelContent.remove(btnOp2);
			panelContent.validate();
			panelContent.repaint();
		}else if (optionInput == 4){
			panelContent.remove(btnExport);
			panelContent.remove(btnImport);
			panelContent.validate();
			panelContent.repaint();
		}
		

        
	    textFieldAddNew = new JTextField("MSSV");
	    sl_panelContent.putConstraint(SpringLayout.NORTH, textFieldAddNew, -300, SpringLayout.SOUTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.WEST, textFieldAddNew, -350, SpringLayout.EAST, panelContent);
        //sl_panelContent.putConstraint(SpringLayout.SOUTH, textFieldAddNew, -140, SpringLayout.SOUTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.EAST, textFieldAddNew, -100, SpringLayout.EAST, panelContent);
        textFieldAddNew.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textFieldAddNew.setColumns(10);
        
	    textFieldAddName = new JTextField("Họ và tên");
	    sl_panelContent.putConstraint(SpringLayout.NORTH, textFieldAddName, 20, SpringLayout.SOUTH, textFieldAddNew);
        sl_panelContent.putConstraint(SpringLayout.WEST, textFieldAddName, -350, SpringLayout.EAST, panelContent);
        //sl_panelContent.putConstraint(SpringLayout.SOUTH, textFieldAddName, -140, SpringLayout.SOUTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.EAST, textFieldAddName, -100, SpringLayout.EAST, panelContent);
        textFieldAddName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textFieldAddName.setColumns(10);
        
        btnOp3 = new JButton("Thêm sinh viên");
        sl_panelContent.putConstraint(SpringLayout.NORTH, btnOp3, 10, SpringLayout.SOUTH, textFieldAddName);
        sl_panelContent.putConstraint(SpringLayout.WEST, btnOp3, 20, SpringLayout.WEST, textFieldAddName);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, btnOp3, 36, SpringLayout.SOUTH, textFieldAddName);
        sl_panelContent.putConstraint(SpringLayout.EAST, btnOp3, -20, SpringLayout.EAST, textFieldAddName);
        btnOp3.setForeground(new Color(255, 255, 255));
        btnOp3.setBackground(new Color(255, 99, 71));
        btnOp3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnOp3.setBorder(BorderFactory.createEmptyBorder());
        
        
        panelContent.add(textFieldAddNew);
        panelContent.add(textFieldAddName);
        panelContent.add(btnOp3);

        optionInput = 3;
        
        
        btnOp3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean matched = false;
				if (textFieldAddNew.getText().equals("")|| textFieldAddName.getText().equals("") ){
					JOptionPane.showMessageDialog(null, "Xin hãy điền đầy đủ thông tin.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}else{
					if(UserDAO.getUserInfo(textFieldAddNew.getText())== null){
						//
						MessageDigest digest = null;
						try {
							digest = MessageDigest.getInstance("SHA-1");
						} catch (NoSuchAlgorithmException e) {
							e.printStackTrace();
						}
						try {
							digest.update(textFieldAddNew.getText().getBytes("utf8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						byte[] digestBytes = digest.digest();
						String digestStr = javax.xml.bind.DatatypeConverter.printHexBinary(digestBytes);
						
						User u = new User();
						u.setmUsername(textFieldAddNew.getText());
						u.setmPassword(digestStr);
						u.setmType(0);
						u.setmFullname(textFieldAddName.getText());
						boolean res = UserDAO.addNew(u); 
						if (res == true) { 
							//JOptionPane.showMessageDialog(null, "Sinh viên đã được thêm vào CSDL.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						} else { 
							//JOptionPane.showMessageDialog(null, "Sinh viên không được thêm vào CSDL.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
				List<Attendance> listAttendance = AttendanceDAO.getAttendanceList();
				for (int i =0; i < listAttendance.size(); ++i){
		        	Attendance a = listAttendance.get(i);
		        	if (a.getmCourse().getmCourseID().equals(courseID) && a.getmStudent().getmUsername().equals(textFieldAddNew.getText())){
		        		matched = true;
		        		break;
		        	}
				}
				if (matched){
					JOptionPane.showMessageDialog(null, "Sinh viên đã đăng ký môn học này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}else{
					for (int i = 0; i < 15; i++){
						Attendance a = new Attendance();
						a.setmStudent(UserDAO.getUserInfo(textFieldAddNew.getText()));
						a.setmCourse(CourseDAO.getCourse(editCourse));
						a.setmWeek(i+1);
						Date dateDate = CourseDAO.getCourse(editCourse).getmDateStarted();
						LocalDate date= LocalDate.parse(dateDate.toString());
						//System.out.println(i+1);
						date = date.plusDays(i*7);
						Date dateAdd = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
						a.setmDate(dateAdd);
						a.setmStatus(0);
						
						boolean res = AttendanceDAO.addNew(a); 
					}
					JOptionPane.showMessageDialog(null, "Thêm sinh viên thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

				}
			
			}
		});
	}
	
	void addCSV(){
		if(optionInput == 1){
			panelContent.remove(spEnrolled);
			panelContent.remove(btnOp1);
			panelContent.validate();
			panelContent.repaint();
		}else if(optionInput == 2){
			panelContent.remove(spNonEnrolled);
			panelContent.remove(btnOp2);
			panelContent.validate();
			panelContent.repaint();
		}else if (optionInput == 3){
			panelContent.remove(textFieldAddNew);
			panelContent.remove(textFieldAddName);
			panelContent.remove(btnOp3);
			panelContent.validate();
			panelContent.repaint();
		}
		btnExport = new JButton("Export template");
	    sl_panelContent.putConstraint(SpringLayout.NORTH, btnExport, -300, SpringLayout.SOUTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.WEST, btnExport, -350, SpringLayout.EAST, panelContent);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, btnExport, -270, SpringLayout.SOUTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.EAST, btnExport, -100, SpringLayout.EAST, panelContent);
        btnExport.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnExport.setForeground(new Color(255, 255, 255));
        btnExport.setBackground(new Color(255, 99, 71));
        btnExport.setBorder(BorderFactory.createEmptyBorder());
        
		btnImport = new JButton("Import CSV");
	    sl_panelContent.putConstraint(SpringLayout.NORTH, btnImport, 20, SpringLayout.SOUTH, btnExport);
        sl_panelContent.putConstraint(SpringLayout.WEST, btnImport, 0, SpringLayout.WEST, btnExport);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, btnImport, 50, SpringLayout.SOUTH, btnExport);
        sl_panelContent.putConstraint(SpringLayout.EAST, btnImport, 0, SpringLayout.EAST, btnExport);
        btnImport.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnImport.setForeground(new Color(255, 255, 255));
        btnImport.setBackground(new Color(255, 99, 71));
        btnImport.setBorder(BorderFactory.createEmptyBorder());
        
        panelContent.add(btnExport);
        panelContent.add(btnImport);
        
        optionInput=4;
        
        btnExport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Chọn đường dẫn lưu template"); 
				String str = "MSSV, Họ và tên\n";
				int result = fileChooser.showSaveDialog(panelContent);
				if (result == JFileChooser.APPROVE_OPTION) {
				    // user selects a file
					try {
			            PrintWriter fw = new PrintWriter(fileChooser.getSelectedFile()+".csv","UTF-8");
			            fw.write(str.toString());
			            fw.close();
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
				}
			}
		});
        
        btnImport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Chọn đường dẫn lấy template"); 
				int result = fileChooser.showOpenDialog(panelContent);
				if (result == JFileChooser.APPROVE_OPTION) {
				    // user selects a file
					try{
					File selectedFile = fileChooser.getSelectedFile();
					BufferedReader in = new BufferedReader(
							   new InputStreamReader(
					                      new FileInputStream(selectedFile), "UTF8"));
							String str, strID, strName;
							while ((str = in.readLine()) != null) {
								int pos = str.indexOf(",");
								strID = str.substring(0, pos);
								strName = str.substring(pos+1,str.length());
								if (strID.equals("MSSV")){
									//Do nothing
								}else if(strID.equals("") || strName.equals("")){
									//Do nothing
								}else{
									User u = UserDAO.getUserInfo(strID);
									if (u == null){
										u = new User();
										u.setmUsername(strID);
										u.setmPassword(getSHA1pass(strID));
										u.setmType(0);
										u.setmFullname(strName);
										
										boolean res = UserDAO.addNew(u);
									}else{
										
									}
									for (int i = 0; i < 15; i++){
										Attendance a = new Attendance();
										a.setmStudent(UserDAO.getUserInfo(strID));
										a.setmCourse(CourseDAO.getCourse(editCourse));
										a.setmWeek(i+1);
										Date dateDate = CourseDAO.getCourse(editCourse).getmDateStarted();
										LocalDate date= LocalDate.parse(dateDate.toString());
										//System.out.println(i+1);
										date = date.plusDays(i*7);
										Date dateAdd = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
										a.setmDate(dateAdd);
										a.setmStatus(0);
										
										boolean res = AttendanceDAO.addNew(a); 
									}
								}
							    //System.out.println(str);
							    //System.out.println(strID);
							    //System.out.println(strName);
							}
					                in.close();
						    }
						    catch (UnsupportedEncodingException e)
						    {
								System.out.println(e.getMessage());
						    }
						    catch (IOException e)
						    {
								System.out.println(e.getMessage());
						    }
						    catch (Exception e)
						    {
								System.out.println(e.getMessage());
						    }
					JOptionPane.showMessageDialog(null, "Các sinh viên đã được thêm vào", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
	
	void addAttendance(){
		lblChooseCourse = new JLabel("Chọn môn học");
        sl_panelContent.putConstraint(SpringLayout.NORTH, lblChooseCourse, 20, SpringLayout.NORTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.WEST, lblChooseCourse, 40, SpringLayout.WEST, panelContent);
        lblChooseCourse.setBackground(new Color(255, 255, 255));
        lblChooseCourse.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelContent.add(lblChooseCourse);
        
        Vector<String> course = new Vector<String>();
        //course.add("");
        if (listCourse.size()>0){
            listCourse.clear();
        }

        listCourse = CourseDAO.getCourseList(); 
        for(int i=0; i< listCourse.size(); i++){ 
        	Course c= listCourse.get(i); 
        	course.addElement(c.getmCourseID()+" - "+ c.getmName());
        }
       
        comboBoxAttendance = new JComboBox(course);
        sl_panelContent.putConstraint(SpringLayout.EAST, comboBoxAttendance, -40, SpringLayout.EAST, panelContent);
        comboBoxAttendance.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        sl_panelContent.putConstraint(SpringLayout.NORTH, comboBoxAttendance, -5, SpringLayout.NORTH, lblChooseCourse);
        sl_panelContent.putConstraint(SpringLayout.WEST, comboBoxAttendance, 10, SpringLayout.EAST, lblChooseCourse);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, comboBoxAttendance, 5, SpringLayout.SOUTH, lblChooseCourse);
        //comboBoxAttendance.setEditable(true);
        panelContent.add(comboBoxAttendance);
        
		//String colNames[] = {"MSSV", "Tuần 1","Tuần 2","Tuần 3","Tuần 4","Tuần 5","Tuần 6","Tuần 7","Tuần 8","Tuần 9","Tuần 10","Tuần 11","Tuần 12","Tuần 13","Tuần 14","Tuần 15"};
		String colNames[] = {"MSSV", "T1","T2","T3","T4","T5","T6","T7","T8","T9","T10","T11","T12","T13","T14","T15"};
		Object[][] data = {};
		DefaultTableModel dtmAttendance;
		dtmAttendance = new DefaultTableModel(data,colNames);
	    JTable table = new JTable(dtmAttendance){
	    	@Override
	    	public Class<?> getColumnClass(int columnIndex) {
	    	    Class type = String.class;
	    	    switch (columnIndex) {
	    	        case 0:
	    	            type = String.class;
	    	            break;
	    	        case 1:
	    	            type = Boolean.class;
	    	            break;
	    	        case 2:
	    	            type = Boolean.class;
	    	            break;
	    	        case 3:
	    	            type = Boolean.class;
	    	            break;
	    	        case 4:
	    	            type = Boolean.class;
	    	            break;
	    	        case 5:
	    	            type = Boolean.class;
	    	            break;
	    	        case 6:
	    	            type = Boolean.class;
	    	            break;
	    	        case 7:
	    	            type = Boolean.class;
	    	            break;
	    	        case 8:
	    	            type = Boolean.class;
	    	            break;
	    	        case 9:
	    	            type = Boolean.class;
	    	            break;
	    	        case 10:
	    	            type = Boolean.class;
	    	            break;
	    	        case 11:
	    	            type = Boolean.class;
	    	            break;
	    	        case 12:
	    	            type = Boolean.class;
	    	            break;
	    	        case 13:
	    	            type = Boolean.class;
	    	            break;
	    	        case 14:
	    	            type = Boolean.class;
	    	            break;
	    	        case 15:
	    	            type = Boolean.class;
	    	            break;
	    	    }
	    	    return type;
	    	}
	    	
	    	
	    };
	    spAttendance = new JScrollPane(table);
	    table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table.setRowHeight(30);
		JTableHeader header = table.getTableHeader();
	    header.setBackground(new Color(255, 140, 0));
	    header.setForeground(Color.white);
	    header.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    sl_panelContent.putConstraint(SpringLayout.NORTH, spAttendance, 100, SpringLayout.SOUTH, comboBoxAttendance);
        sl_panelContent.putConstraint(SpringLayout.WEST, spAttendance, 40, SpringLayout.WEST, panelContent);
        sl_panelContent.putConstraint(SpringLayout.SOUTH, spAttendance, -40, SpringLayout.SOUTH, panelContent);
        sl_panelContent.putConstraint(SpringLayout.EAST, spAttendance, -40, SpringLayout.EAST, panelContent);
        panelContent.add(spAttendance);
        
        List<Attendance> listAttendance = AttendanceDAO.getAttendanceList();
        Vector<String> temp = new Vector<String>();
        boolean matched = false;
        for (int i =0; i < listAttendance.size(); ++i){
        	Attendance a = listAttendance.get(i);
        	if (a.getmCourse().getmCourseID().equals(listCourse.get(comboBoxAttendance.getSelectedIndex()).getmCourseID())){
	        	if (temp.size()>0){
	        		for (int j = 0; j < temp.size(); ++j){
	        			if (a.getmStudent().getmUsername().equals(temp.get(j))){
	        				matched = true;
	        			}
	        		}
	        		if (!matched){
	        			temp.add(a.getmStudent().getmUsername());
	        		}
	        		matched = false;
	        	}else{
	        		temp.add(a.getmStudent().getmUsername());
	        	}
        	}
        }
        for (int i = 0; i <temp.size();++i){
        	boolean attendance[] = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
        	for (int j = 0; j< listAttendance.size(); ++j){
        		Attendance a = listAttendance.get(j);
        		System.out.println(a.getmStudent().getmUsername());
        		System.out.println(temp.get(i));
        		System.out.println(a.getmStudent().getmUsername());
        		System.out.println(listCourse.get(comboBoxAttendance.getSelectedIndex()).getmCourseID());
        		if (a.getmStudent().getmUsername().equals(temp.get(i))&&
        			a.getmCourse().getmCourseID().equals(listCourse.get(comboBoxAttendance.getSelectedIndex()).getmCourseID())){
        			if(a.getmStatus() == 1){
        				attendance[a.getmWeek()-1] = true;
        			}
        		}
        	}
        	dtmAttendance.addRow(new Object[]{temp.get(i),
        			new Boolean(attendance[0]),
        			new Boolean(attendance[1]),
        			new Boolean(attendance[2]),
        			new Boolean(attendance[3]),
        			new Boolean(attendance[4]),
        			new Boolean(attendance[5]),
        			new Boolean(attendance[6]),
        			new Boolean(attendance[7]),
        			new Boolean(attendance[8]),
        			new Boolean(attendance[9]),
        			new Boolean(attendance[10]),
        			new Boolean(attendance[11]),
        			new Boolean(attendance[12]),
        			new Boolean(attendance[13]),
        			new Boolean(attendance[14])
        			});
        	if (temp.size()>0){
        		temp.clear();
        	}
        }
        
        
        comboBoxAttendance.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				int addColor = 0;
				dtmAttendance.setDataVector(null,colNames);
				List<Attendance> listAttendance = AttendanceDAO.getAttendanceList();
		        Vector<String> temp = new Vector<String>();
		        boolean matched = false;
		        editCourse = listCourse.get(comboBoxAttendance.getSelectedIndex()).getmCourseID();
		        for (int i =0; i < listAttendance.size(); ++i){
		        	Attendance a = listAttendance.get(i);
		        	if (a.getmCourse().getmCourseID().equals(listCourse.get(comboBoxAttendance.getSelectedIndex()).getmCourseID())){
			        	if (temp.size()>0){
			        		for (int j = 0; j < temp.size(); ++j){
			        			if (a.getmStudent().getmUsername().equals(temp.get(j))){
			        				matched = true;
			        			}
			        		}
			        		if (!matched){
			        			temp.add(a.getmStudent().getmUsername());
			        		}
			        		matched = false;
			        	}else{
			        		temp.add(a.getmStudent().getmUsername());
			        	}
		        	}
		        }
		        for (int i = 0; i <temp.size();++i){
		        	boolean attendance[] = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
		        	for (int j = 0; j< listAttendance.size(); ++j){
		        		Attendance a = listAttendance.get(j);
		        		if (a.getmStudent().getmUsername().equals(temp.get(i))&&
		        			a.getmCourse().getmCourseID().equals(listCourse.get(comboBoxAttendance.getSelectedIndex()).getmCourseID())){
		        			if(a.getmStatus() == 1){
		        				attendance[a.getmWeek()-1] = true;
		        			}
		        		}
		        	}
		        	dtmAttendance.addRow(new Object[]{temp.get(i),
		        			new Boolean(attendance[0]),
		        			new Boolean(attendance[1]),
		        			new Boolean(attendance[2]),
		        			new Boolean(attendance[3]),
		        			new Boolean(attendance[4]),
		        			new Boolean(attendance[5]),
		        			new Boolean(attendance[6]),
		        			new Boolean(attendance[7]),
		        			new Boolean(attendance[8]),
		        			new Boolean(attendance[9]),
		        			new Boolean(attendance[10]),
		        			new Boolean(attendance[11]),
		        			new Boolean(attendance[12]),
		        			new Boolean(attendance[13]),
		        			new Boolean(attendance[14])
		        			});
		        }
				panelContent.validate();
				panelContent.repaint();
			}
		});
        
        table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			  public void tableChanged(TableModelEvent e) {
				if (e.getFirstRow()!=-1 && e.getColumn() != -1 ){
					table.getModel().getValueAt(e.getFirstRow(), e.getColumn());
					 
						  User u = UserDAO.getUserInfo(table.getModel().getValueAt(e.getFirstRow(), 0).toString());
						  if (u != null){	  
							  Iterator<Attendance> lsa = u.getmAttendances().iterator(); 
							  while(lsa.hasNext()){
								  Attendance a = lsa.next();
								  //System.out.println(listCourse.get(comboBoxAttendance.getSelectedIndex()).getmCourseID());
								  //System.out.println(a.getmWeek());
								  //System.out.println(e.getColumn());
								  if(a.getmCourse().getmCourseID().equals(listCourse.get(comboBoxAttendance.getSelectedIndex()).getmCourseID()) &&
										  a.getmWeek() == e.getColumn()){
									  //System.out.println(table.getModel().getValueAt(e.getFirstRow(), e.getColumn()).toString());
									  if (table.getModel().getValueAt(e.getFirstRow(), e.getColumn()).equals(true)){
										  a.setmStatus(1);
										  JOptionPane.showMessageDialog(null, "Đánh dấu 'có mặt' sinh viên có MSSV "+table.getModel().getValueAt(e.getFirstRow(), 0), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
									  }else if(table.getModel().getValueAt(e.getFirstRow(), e.getColumn()).equals(false)){
										  JOptionPane.showMessageDialog(null, "Đánh dấu 'vắng' sinh viên có MSSV "+table.getModel().getValueAt(e.getFirstRow(), 0), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
										  a.setmStatus(2);
									  }
									  boolean res = AttendanceDAO.update(a);
								  }
							  }
						  }
						  

				}
			  }
			});
	}
	
	String getSHA1pass(String passStr){		
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
		return digestStr;
	}
}


class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        //setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
    	setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	setForeground(new Color(0, 191, 255));
        setBackground(new Color(255, 255, 255));
        setBorder(BorderFactory.createEmptyBorder());

        setText((value == null) ? "" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private String courseID;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        //button.setOpaque(true);
    	button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	button.setForeground(new Color(0, 191, 255));
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

        if (isSelected) {
            button.setForeground(new Color(255, 140, 0));
        } else {
        	button.setForeground(new Color(0, 191, 255));
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
