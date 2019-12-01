/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shanb
 */
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.text.*;
import java.awt.print.*;
import javax.swing.JTable;

public class frmHome extends javax.swing.JFrame {

    /**
     * Creates new form frmHome
     */
    public frmHome() {
        initComponents();
        Refresh();
    }
public String tempCode;
    public ArrayList<ComboItem> cmbdep = new ArrayList<>();

    public void Refresh() {
        Showdep();
        Showhall();
        setdepCombo();
        ShowCrs();
        ShowSub();
        ShowTT();
        ShowInv();
        setUserCmb();
    }

   
    public void SetHome() {

        Connection con = myConnection.getConnection();

    }
   
    public ArrayList<users> userList(){
    ArrayList<users> userlist = new ArrayList<>();
    Connection con = myConnection.getConnection();
    PreparedStatement ps;
    try{
    ps= con.prepareStatement("SELECT * FROM users");
    ResultSet rs =ps.executeQuery();
    
    users us;
    while(rs.next())
    {
    us = new users(rs.getString("user_id"));
    userlist.add(us);
    }  
    
    }catch(SQLException e){JOptionPane.showMessageDialog(null, e);}
    
    return userlist;
    }
    
    public void setUserCmb(){
     ArrayList<users> us = userList();
         
            cmbUserRemove.removeAllItems();
           
        for (int i = 0; i < us.size(); i++) {
           // cmbdep.add(new ComboItem(dep.get(i).getDepName(), dep.get(i).getDepCode()));
            cmbUserRemove.addItem(us.get(i).getUname());

        }
       
    
    }
    public ArrayList<Department> DepartmentList() {
        ArrayList<Department> departmentlist = new ArrayList<>();

        Connection con = myConnection.getConnection();

        PreparedStatement ps;

        try {
            ps = con.prepareStatement("SELECT * FROM department");

            ResultSet rs = ps.executeQuery();
            Department dep;
            while (rs.next()) {
                dep = new Department(rs.getString(1), rs.getString(2), rs.getString(3));
                departmentlist.add(dep);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return departmentlist;
    }
    
     public ArrayList<Cources> CourcesList() {
        ArrayList<Cources> courceslist = new ArrayList<>();

        Connection con = myConnection.getConnection();

        PreparedStatement ps;

        try {
            ps = con.prepareStatement("SELECT * FROM courses");

            ResultSet rs = ps.executeQuery();
            Cources crs;
            while (rs.next()) {
                crs = new Cources(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getInt("number_Of_Student"),rs.getString(6));
                courceslist.add(crs);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return courceslist;
    }
public ArrayList<Invigilators> InvList() {
    ArrayList<Invigilators> invlist = new ArrayList<>();

        Connection con = myConnection.getConnection();

        PreparedStatement ps;

        try {
            ps = con.prepareStatement("SELECT * FROM invigilators");

            ResultSet rs = ps.executeQuery();
           Invigilators inv;
            while (rs.next()) {
                inv = new Invigilators(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                invlist.add(inv);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return invlist;
    }
 public void ShowInv() {
        ArrayList<Invigilators> inv = InvList();
        DefaultTableModel model = (DefaultTableModel) tblINV.getModel();

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        Object[] row = new Object[6];

        for (int i = 0; i < inv.size(); i++) {
            row[0] = String.valueOf(i + 1);
            row[1] = inv.get(i).getInvCode();
            row[2] = inv.get(i).getInvName();
            row[3] = inv.get(i).getTell();
            row[4] = inv.get(i).getAddress();
           

            model.addRow(row);

        }
    }
    
    public ArrayList<Hall> HallList() {
        ArrayList<Hall> hallList = new ArrayList<>();
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM hall");
            ResultSet rs = ps.executeQuery();
            Hall hall;
            while (rs.next()) {
                hall = new Hall(rs.getString(2), rs.getString(3), rs.getInt("Capacity"));
                hallList.add(hall);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        return hallList;
    }
      public ArrayList<TimeTable> TTList() {
        ArrayList<TimeTable> ttList = new ArrayList<>();
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM timetabledirectry");
            ResultSet rs = ps.executeQuery();
            TimeTable table;
            while (rs.next()) {
                table = new TimeTable(rs.getString("date"), rs.getString("Table_Name"), rs.getString("Table_Code"));
                ttList.add(table);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        return ttList;
    }
      
       public void ShowTT() {
        ArrayList<TimeTable> tt = TTList();
        DefaultListModel modelttList = new DefaultListModel();
        
        modelttList.clear();
        
        for (int i = 0; i < tt.size(); i++) {
            
                 modelttList.addElement(tt.get(i).getTblCode()+" - "+tt.get(i).gettblName());
                
        }
       lbSelectTimeTable.setModel(modelttList);

    }
    public void Showdep() {
        ArrayList<Department> dep = DepartmentList();
        DefaultTableModel model = (DefaultTableModel) tblDep.getModel();

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        Object[] row = new Object[3];

        for (int i = 0; i < dep.size(); i++) {
            row[0] = String.valueOf(i + 1);
            row[1] = dep.get(i).getDepCode();
            row[2] = dep.get(i).getDepName();

            model.addRow(row);

        }
    }
    
    public void ShowCrs() {
        ArrayList<Cources> crs = CourcesList();
        DefaultTableModel model = (DefaultTableModel) tblCrs.getModel();

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        Object[] row = new Object[6];

        for (int i = 0; i < crs.size(); i++) {
            row[0] = String.valueOf(i + 1);
            row[1] = crs.get(i).getCCode();
            row[2] = crs.get(i).getCName();
            row[3] = crs.get(i).getCTitle();
            row[4] = crs.get(i).getCNofs();
            row[5] = crs.get(i).getDepCode();

            model.addRow(row);

        }
    }
   
    public void Showhall() {
        ArrayList<Hall> hall = HallList();
        DefaultTableModel model = (DefaultTableModel) tblHall.getModel();

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        Object[] row = new Object[4];

        for (int i = 0; i < hall.size(); i++) {
            row[0] = String.valueOf(i + 1);
            row[1] = hall.get(i).getHallCode();
            row[2] = hall.get(i).getHallName();
            row[3] = hall.get(i).getHallCapacity();

            model.addRow(row);

        }

    }
   

    public void setdepCombo() {
        ArrayList<Department> dep = DepartmentList();
         ArrayList<Cources> crs = CourcesList();
            cmbDepCode.removeAllItems();
            cmbCCode.removeAllItems();
        for (int i = 0; i < dep.size(); i++) {
           // cmbdep.add(new ComboItem(dep.get(i).getDepName(), dep.get(i).getDepCode()));
            cmbDepCode.addItem(dep.get(i).getDepCode());

        }
        for (int i = 0; i < crs.size(); i++) {
            // cmbDepCode.addItem(cmbdep.get(i).toString());
         cmbCCode.addItem(crs.get(i).getCCode());
        }
    }

    public ArrayList<Subject> SubjectList() {
        ArrayList<Subject> SubjectList = new ArrayList<>();

        Connection con = myConnection.getConnection();

        PreparedStatement ps;

        try {
            ps = con.prepareStatement("SELECT * FROM subject");

            ResultSet rs = ps.executeQuery();
            Subject sub;
            while (rs.next()) {
                sub = new Subject(rs.getString("subject_Code"), rs.getString("subject_Name"),rs.getString("course_code"), rs.getFloat("credite"),rs.getInt("exame_duration"));
                SubjectList.add(sub);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return SubjectList;
    }

    public void ShowSub() {
        ArrayList<Subject> sub = SubjectList();
        DefaultTableModel model = (DefaultTableModel) tblSubject.getModel();

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        Object[] row = new Object[6];

        for (int i = 0; i < sub.size(); i++) {
            row[0] = String.valueOf(i + 1);
            row[1] = sub.get(i).getSubCode();
            row[2] = sub.get(i).getSubName();
            row[3] = sub.get(i).getCourseCode();
            row[4] = sub.get(i).getExameHour()+"Hours";
            row[5] = sub.get(i).getCredit();

            model.addRow(row);

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        pnlMain = new javax.swing.JTabbedPane();
        Department = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDep = new javax.swing.JTable();
        btnAddDep = new javax.swing.JButton();
        txtDepCode = new javax.swing.JTextField();
        txtDepName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnDepRemove = new javax.swing.JButton();
        btnDepEdite = new javax.swing.JButton();
        Course = new javax.swing.JPanel();
        txyCourseCode = new javax.swing.JTextField();
        txtCourseName = new javax.swing.JTextField();
        txtCourseTitle = new javax.swing.JTextField();
        btnAddCourse = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCrs = new javax.swing.JTable();
        cmbDepCode = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtCNoOfStudent = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnCrsRemove = new javax.swing.JButton();
        btnCrsEdite = new javax.swing.JButton();
        Subject = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSubject = new javax.swing.JTable();
        txtSubCode = new javax.swing.JTextField();
        txtSubName = new javax.swing.JTextField();
        txtExameDuration = new javax.swing.JTextField();
        txtCredite = new javax.swing.JTextField();
        btnSubAdd = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cmbCCode = new javax.swing.JComboBox<>();
        btnSubEdite = new javax.swing.JButton();
        btnSubRemove = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblINV = new javax.swing.JTable();
        txtInvPhone = new javax.swing.JTextField();
        txtInvCode = new javax.swing.JTextField();
        txtInvName = new javax.swing.JTextField();
        txtInvAddress = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnAddInv = new javax.swing.JButton();
        btnInvEdit = new javax.swing.JButton();
        btnInvRemove = new javax.swing.JButton();
        pnlHall = new javax.swing.JPanel();
        txtHallCode = new javax.swing.JTextField();
        txtHallName = new javax.swing.JTextField();
        txtHallCapacity = new javax.swing.JTextField();
        btnAddHall = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblHall = new javax.swing.JTable();
        TimeTable = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblTimeTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        lbSelectTimeTable = new javax.swing.JList<>();
        btnRemoveTbl = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        txtNOfINV = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtUName = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        txtUPwrd = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        cmbUserRemove = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        btnUserRemove = new javax.swing.JButton();

        jLabel11.setText("Subject Name");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Timetable Management System");

        tblDep.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "id", "Department Code", "Department Name"
            }
        ));
        tblDep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDepMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDep);

        btnAddDep.setText("Add Department");
        btnAddDep.setEnabled(false);
        btnAddDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDepActionPerformed(evt);
            }
        });

        txtDepCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDepCodeFocusLost(evt);
            }
        });
        txtDepCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDepCodeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDepCodeKeyTyped(evt);
            }
        });

        txtDepName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDepNameKeyPressed(evt);
            }
        });

        jLabel1.setText("Department Code");

        jLabel3.setText("Department Name");

        btnDepRemove.setText("Remove");
        btnDepRemove.setEnabled(false);
        btnDepRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepRemoveActionPerformed(evt);
            }
        });

        btnDepEdite.setText("Edite");
        btnDepEdite.setEnabled(false);
        btnDepEdite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepEditeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DepartmentLayout = new javax.swing.GroupLayout(Department);
        Department.setLayout(DepartmentLayout);
        DepartmentLayout.setHorizontalGroup(
            DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DepartmentLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DepartmentLayout.createSequentialGroup()
                        .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDepCode, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(txtDepName, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(btnAddDep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(DepartmentLayout.createSequentialGroup()
                        .addComponent(btnDepRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDepEdite, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        DepartmentLayout.setVerticalGroup(
            DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DepartmentLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDepCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDepName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DepartmentLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel2))
                    .addGroup(DepartmentLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnAddDep, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDepRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDepEdite, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(114, 114, 114))
        );

        pnlMain.addTab("Department", Department);

        txyCourseCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txyCourseCodeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txyCourseCodeKeyTyped(evt);
            }
        });

        txtCourseName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCourseNameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCourseNameKeyTyped(evt);
            }
        });

        txtCourseTitle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCourseTitleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCourseTitleKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCourseTitleKeyTyped(evt);
            }
        });

        btnAddCourse.setText("Add Course");
        btnAddCourse.setEnabled(false);
        btnAddCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCourseActionPerformed(evt);
            }
        });

        jLabel10.setText("Course code");

        jLabel12.setText("Course Name");

        jLabel13.setText("Course Title");

        tblCrs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "Course Code", "Course Name", "Course Title", "Number Of Student", "Department Code"
            }
        ));
        tblCrs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCrsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblCrsMouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(tblCrs);

        cmbDepCode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDepCodeItemStateChanged(evt);
            }
        });

        jLabel14.setText("Department Code");

        txtCNoOfStudent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCNoOfStudentKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCNoOfStudentKeyTyped(evt);
            }
        });

        jLabel16.setText("Number Of Students");

        btnCrsRemove.setText("Remove");
        btnCrsRemove.setEnabled(false);
        btnCrsRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrsRemoveActionPerformed(evt);
            }
        });

        btnCrsEdite.setText("Edite");
        btnCrsEdite.setEnabled(false);
        btnCrsEdite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrsEditeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CourseLayout = new javax.swing.GroupLayout(Course);
        Course.setLayout(CourseLayout);
        CourseLayout.setHorizontalGroup(
            CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CourseLayout.createSequentialGroup()
                .addGroup(CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(CourseLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addGap(27, 27, 27)
                        .addGroup(CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCNoOfStudent)
                            .addComponent(txyCourseCode, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(txtCourseName, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(txtCourseTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(btnAddCourse, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(cmbDepCode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(CourseLayout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(btnCrsRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnCrsEdite, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE))
        );
        CourseLayout.setVerticalGroup(
            CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CourseLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txyCourseCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(11, 11, 11)
                .addGroup(CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCourseTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(8, 8, 8)
                .addGroup(CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCNoOfStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDepCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addComponent(btnAddCourse)
                .addGap(61, 61, 61)
                .addGroup(CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrsRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrsEdite, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
        );

        pnlMain.addTab("Courses", Course);

        tblSubject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "subjectID", "Subject Code", "SubjectName", "Course Code", "Exame Duration", "Credite"
            }
        ));
        tblSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSubjectMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSubject);

        txtSubCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSubCodeFocusLost(evt);
            }
        });
        txtSubCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSubCodeKeyTyped(evt);
            }
        });

        txtSubName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSubNameKeyTyped(evt);
            }
        });

        txtExameDuration.setText("0.00");
        txtExameDuration.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtExameDurationKeyTyped(evt);
            }
        });

        txtCredite.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCrediteKeyTyped(evt);
            }
        });

        btnSubAdd.setText("Add Subject");
        btnSubAdd.setEnabled(false);
        btnSubAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubAddActionPerformed(evt);
            }
        });

        jLabel6.setText("Subject Code");

        jLabel7.setText("Subject Name");

        jLabel8.setText("Exame duration");

        jLabel9.setText("Credite");

        jLabel15.setText("Course Code ");

        btnSubEdite.setText("Edite");
        btnSubEdite.setEnabled(false);
        btnSubEdite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubEditeActionPerformed(evt);
            }
        });

        btnSubRemove.setText("Remove");
        btnSubRemove.setEnabled(false);
        btnSubRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SubjectLayout = new javax.swing.GroupLayout(Subject);
        Subject.setLayout(SubjectLayout);
        SubjectLayout.setHorizontalGroup(
            SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SubjectLayout.createSequentialGroup()
                .addGroup(SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SubjectLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel15)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addGap(43, 43, 43)
                        .addGroup(SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSubName, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSubAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCCode, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtExameDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCredite, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSubCode, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(SubjectLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(btnSubRemove)
                        .addGap(68, 68, 68)
                        .addComponent(btnSubEdite)))
                .addContainerGap(661, Short.MAX_VALUE))
            .addGroup(SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SubjectLayout.createSequentialGroup()
                    .addContainerGap(362, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(22, 22, 22)))
        );
        SubjectLayout.setVerticalGroup(
            SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SubjectLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SubjectLayout.createSequentialGroup()
                        .addGroup(SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSubCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSubName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15))
                .addGroup(SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(SubjectLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel9))
                    .addGroup(SubjectLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtExameDuration)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCredite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSubAdd)
                .addGap(61, 61, 61)
                .addGroup(SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubEdite)
                    .addComponent(btnSubRemove))
                .addContainerGap(160, Short.MAX_VALUE))
            .addGroup(SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(SubjectLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pnlMain.addTab("Subjects", Subject);

        tblINV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "invigilator Code", "invigilator Name", "invigilator Phone", "invigilator Address"
            }
        ));
        tblINV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblINVMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblINV);

        txtInvPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInvPhoneActionPerformed(evt);
            }
        });
        txtInvPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInvPhoneKeyTyped(evt);
            }
        });

        txtInvCode.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtInvCodePropertyChange(evt);
            }
        });
        txtInvCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInvCodeKeyTyped(evt);
            }
        });

        txtInvName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInvNameKeyTyped(evt);
            }
        });

        txtInvAddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInvAddressKeyTyped(evt);
            }
        });

        jLabel5.setText("invigilator Code");

        jLabel17.setText("invigilator Name");

        jLabel18.setText("invigilator Phone");

        jLabel22.setText("invigilator Address");

        btnAddInv.setText("Add Invigilator");
        btnAddInv.setEnabled(false);
        btnAddInv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddInvActionPerformed(evt);
            }
        });

        btnInvEdit.setText("Edite");
        btnInvEdit.setEnabled(false);
        btnInvEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInvEditActionPerformed(evt);
            }
        });

        btnInvRemove.setText("Remove");
        btnInvRemove.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17)
                    .addComponent(jLabel5))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnInvRemove, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInvEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAddInv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtInvPhone)
                    .addComponent(txtInvCode)
                    .addComponent(txtInvName)
                    .addComponent(txtInvAddress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtInvCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtInvName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtInvPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtInvAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addComponent(btnAddInv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnInvEdit)
                            .addComponent(btnInvRemove))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnlMain.addTab("Invigilators", jPanel1);

        txtHallCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHallCodeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHallCodeKeyTyped(evt);
            }
        });

        txtHallName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHallNameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHallNameKeyTyped(evt);
            }
        });

        txtHallCapacity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHallCapacityKeyPressed(evt);
            }
        });

        btnAddHall.setBackground(java.awt.SystemColor.control);
        btnAddHall.setText("Add Hall");
        btnAddHall.setEnabled(false);
        btnAddHall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddHallActionPerformed(evt);
            }
        });

        jLabel19.setText("Hall Capacity");

        jLabel20.setText("Hall Name");

        jLabel21.setText("Hall code");

        jButton1.setBackground(java.awt.SystemColor.control);
        jButton1.setText("Remove");
        jButton1.setEnabled(false);

        jButton2.setBackground(java.awt.SystemColor.control);
        jButton2.setText("Edite");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tblHall.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "Hall Code", "Hall Name", "Capacity"
            }
        ));
        jScrollPane7.setViewportView(tblHall);

        javax.swing.GroupLayout pnlHallLayout = new javax.swing.GroupLayout(pnlHall);
        pnlHall.setLayout(pnlHallLayout);
        pnlHallLayout.setHorizontalGroup(
            pnlHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHallLayout.createSequentialGroup()
                .addGroup(pnlHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHallLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(pnlHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19))
                        .addGap(69, 69, 69)
                        .addGroup(pnlHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtHallCode)
                            .addComponent(txtHallName)
                            .addComponent(txtHallCapacity)
                            .addComponent(btnAddHall, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlHallLayout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
        );
        pnlHallLayout.setVerticalGroup(
            pnlHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHallLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(pnlHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHallCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHallName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(11, 11, 11)
                .addGroup(pnlHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHallCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addComponent(btnAddHall, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(pnlHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlHallLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlMain.addTab("Exam Hall", pnlHall);

        jLabel4.setText("Select Timetable");

        jButton3.setBackground(java.awt.Color.green);
        jButton3.setText("Add New Timetable");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tblTimeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Morning", "Evening", "Compatible Hall"
            }
        ));
        tblTimeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTimeTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblTimeTable);

        lbSelectTimeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbSelectTimeTableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(lbSelectTimeTable);

        btnRemoveTbl.setText("Remove Timetable");
        btnRemoveTbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveTblActionPerformed(evt);
            }
        });

        btnPrint.setText("Print");
        btnPrint.setEnabled(false);
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        jLabel28.setText("Number of Invigilators");

        javax.swing.GroupLayout TimeTableLayout = new javax.swing.GroupLayout(TimeTable);
        TimeTable.setLayout(TimeTableLayout);
        TimeTableLayout.setHorizontalGroup(
            TimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimeTableLayout.createSequentialGroup()
                .addGroup(TimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(TimeTableLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TimeTableLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel4))
                    .addGroup(TimeTableLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(TimeTableLayout.createSequentialGroup()
                        .addGroup(TimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TimeTableLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnRemoveTbl, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(TimeTableLayout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(38, 38, 38)
                        .addGroup(TimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(TimeTableLayout.createSequentialGroup()
                                .addComponent(txtNOfINV, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE))
        );
        TimeTableLayout.setVerticalGroup(
            TimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimeTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addGroup(TimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNOfINV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(TimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemoveTbl, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
        );

        pnlMain.addTab("Timetable", TimeTable);

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel23.setText("Add User");

        jLabel24.setText("User Name");

        jLabel25.setText("Password");

        jButton4.setText("Add User");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtUPwrd.setText("jPasswordField1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 46, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel24))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(txtUName)
                            .addComponent(txtUPwrd))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtUPwrd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));

        jLabel26.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel26.setText("Remove User");

        jLabel27.setText("User Name");

        btnUserRemove.setText("Remove");
        btnUserRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel26)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnUserRemove, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                    .addComponent(cmbUserRemove, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbUserRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUserRemove)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(227, Short.MAX_VALUE))
        );

        pnlMain.addTab("Settings", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDepActionPerformed
        
        Connection con = myConnection.getConnection();

       PreparedStatement ps;
       if(btnAddDep.getText()=="Save")
       {
        try {
            ps = con.prepareStatement("UPDATE department  SET  dep_Code = ? ,dep_Name = ? WHERE dep_Code =? ");
            ps.setString(1, txtDepCode.getText());
            ps.setString(2, txtDepName.getText());
            ps.setString(3,tempCode);
            
            
            if(!ps.execute())
            {
                JOptionPane.showMessageDialog(null, "Department Successfuly Updated","Sucess", JOptionPane.PLAIN_MESSAGE);
                Refresh();
                txtDepName.setText("");
                txtDepCode.setText("");
                btnAddDep.setText("Add Department");
                btnDepRemove.setEnabled(false);
        btnDepEdite.setEnabled(false);
        btnAddDep.setEnabled(false);
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalied input","Error", JOptionPane.ERROR_MESSAGE);
                
            }
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(Loging.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       
       }
       else{
        try {
            ps = con.prepareStatement("INSERT INTO department(dep_Code,dep_Name)VALUES(?,?)");
            ps.setString(1, txtDepCode.getText());
            ps.setString(2, txtDepName.getText());
         
            
            
            if(!ps.execute())
            {
                JOptionPane.showMessageDialog(null, "Department Successfuly Aded","Sucess", JOptionPane.PLAIN_MESSAGE);
                Refresh();
                txtDepName.setText("");
                txtDepCode.setText("");
            }else{
                JOptionPane.showMessageDialog(null, "Invalied input","Error", JOptionPane.ERROR_MESSAGE);
                
            }
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(Loging.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       
       }
       
       
        
        
    }//GEN-LAST:event_btnAddDepActionPerformed

    private void btnDepRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepRemoveActionPerformed
              DefaultTableModel model = (DefaultTableModel) tblDep.getModel(); 
             // model.removeRow(tblDep.getSelectedRow());
             System.out.println(tblDep.getValueAt(tblDep.getSelectedRow(),1));
             
             Connection con = myConnection.getConnection();

       PreparedStatement ps;
       
        try {
            ps = con.prepareStatement("DELETE FROM department WHERE dep_Code = ?");
            ps.setString(1, tblDep.getValueAt(tblDep.getSelectedRow(),1).toString());
            
            
            
            if(!ps.execute())
            {
                JOptionPane.showMessageDialog(null, "Department Successfuly Removed","Sucess", JOptionPane.PLAIN_MESSAGE);
                Refresh();
            }else{
                JOptionPane.showMessageDialog(null, "Invalied input","Error", JOptionPane.ERROR_MESSAGE);
                
            }
            // TODO add your handling code here:
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Please check department related to another table","Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Loging.class.getName()).log(Level.SEVERE, null, ex);
            
        }
      
    }//GEN-LAST:event_btnDepRemoveActionPerformed

    private void btnDepEditeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepEditeActionPerformed
       txtDepCode.setText(tblDep.getValueAt(tblDep.getSelectedRow(),1).toString()) ;
       txtDepName.setText(tblDep.getValueAt(tblDep.getSelectedRow(),2).toString()) ;
        tempCode =tblDep.getValueAt(tblDep.getSelectedRow(),1).toString();
        btnAddDep.setText("Save");
        btnAddDep.setEnabled(true);
        
        
    }//GEN-LAST:event_btnDepEditeActionPerformed

    private void btnCrsEditeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrsEditeActionPerformed

//txtDepCode.setText(tblDep.getValueAt(tblDep.getSelectedRow(),1).toString()) ;
       txyCourseCode.setText(tblCrs.getValueAt(tblCrs.getSelectedRow(),1).toString()) ;
       txtCourseName.setText(tblCrs.getValueAt(tblCrs.getSelectedRow(),2).toString()) ;
       txtCourseTitle.setText(tblCrs.getValueAt(tblCrs.getSelectedRow(),3).toString()) ;
       txtCNoOfStudent.setText(tblCrs.getValueAt(tblCrs.getSelectedRow(),4).toString()) ;
       cmbDepCode.setSelectedItem(tblCrs.getValueAt(tblCrs.getSelectedRow(),5));//setText(tblDep.getValueAt(tblDep.getSelectedRow(),1).toString()) ;
        tempCode =tblCrs.getValueAt(tblCrs.getSelectedRow(),1).toString();
        btnAddCourse.setText("Save");



      
    }//GEN-LAST:event_btnCrsEditeActionPerformed

    private void tblCrsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCrsMouseClicked
        btnCrsRemove.setEnabled(true);
        btnCrsEdite.setEnabled(true);



    }//GEN-LAST:event_tblCrsMouseClicked

    private void btnCrsRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrsRemoveActionPerformed
          DefaultTableModel model = (DefaultTableModel) tblCrs.getModel(); 
             // model.removeRow(tblDep.getSelectedRow());
             System.out.println(tblCrs.getValueAt(tblCrs.getSelectedRow(),1));
             
             Connection con = myConnection.getConnection();

       PreparedStatement ps;
       
        try {
            ps = con.prepareStatement("DELETE FROM courses WHERE course_code = ?");
            ps.setString(1, tblCrs.getValueAt(tblCrs.getSelectedRow(),1).toString());
            
            
            
            if(!ps.execute())
            {
                JOptionPane.showMessageDialog(null, "Course Successfuly Removed","Sucess", JOptionPane.PLAIN_MESSAGE);
                Refresh();
                 btnCrsRemove.setEnabled(false);
        btnCrsEdite.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(null, "Invalied input","Error", JOptionPane.ERROR_MESSAGE);
                
            }
            // TODO add your handling code here:
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Please check Course related to another table","Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Loging.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }//GEN-LAST:event_btnCrsRemoveActionPerformed

    private void tblCrsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCrsMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblCrsMouseEntered

    private void btnSubAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubAddActionPerformed
       System.out.println(cmbCCode.getSelectedItem().toString());
        Connection con = myConnection.getConnection();

       PreparedStatement ps;
       if(btnSubAdd.getText()=="Save")
       {
        try {
            ps = con.prepareStatement("UPDATE subject SET subject_Code =?,subject_Name =?,course_code =?,credite =?,exame_duration =? WHERE  subject_Code =?");
            ps.setString(1, txtSubCode.getText());
            ps.setString(2, txtSubName.getText());
            ps.setString(3, cmbCCode.getSelectedItem().toString());
            ps.setFloat(4, Float.parseFloat(txtExameDuration.getText()));
            ps.setFloat(5,Float.parseFloat(txtCredite.getText()) );
            ps.setString(6,tempCode);
            
            
            if(!ps.execute())
            {
                JOptionPane.showMessageDialog(null, "Subject Successfuly Updated","Sucess", JOptionPane.PLAIN_MESSAGE);
                Refresh();
                txtSubCode.setText("");
                txtSubName.setText("");
                txtExameDuration.setText("");
                txtCredite.setText("");
                
                btnSubAdd.setText("Add Subject");
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalied input","Error", JOptionPane.ERROR_MESSAGE);
                
            }
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(Loging.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       
       }
       else{
        try {
            ps = con.prepareStatement("INSERT INTO subject(subject_Code,subject_Name ,course_code ,credite ,exame_duration)VALUES (?,?,?,?,?)");
            ps.setString(1, txtSubCode.getText());
            ps.setString(2, txtSubName.getText());
            ps.setString(3, cmbCCode.getSelectedItem().toString());
            ps.setString(4, txtExameDuration.getText());
            ps.setString(5, txtCredite.getText());
         
         
            
            
            if(!ps.execute())
            {
                JOptionPane.showMessageDialog(null, "Subject Successfuly Aded","Sucess", JOptionPane.PLAIN_MESSAGE);
                Refresh();
                txtDepName.setText("");
                txtDepCode.setText("");
            }else{
                JOptionPane.showMessageDialog(null, "Invalied input","Error", JOptionPane.ERROR_MESSAGE);
                
            }
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(Loging.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       
       }
       
       
        


    }//GEN-LAST:event_btnSubAddActionPerformed

    private void btnSubEditeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubEditeActionPerformed
          txtSubCode.setText(tblSubject.getValueAt(tblSubject.getSelectedRow(),1).toString()) ;
       txtSubName.setText(tblSubject.getValueAt(tblSubject.getSelectedRow(),2).toString()) ;
       cmbCCode.setSelectedItem(tblSubject.getValueAt(tblSubject.getSelectedRow(),3)) ;
       txtExameDuration.setText(tblSubject.getValueAt(tblSubject.getSelectedRow(),4).toString()) ;
      txtCredite.setText(tblSubject.getValueAt(tblSubject.getSelectedRow(),5).toString());//setText(tblDep.getValueAt(tblDep.getSelectedRow(),1).toString()) ;
        tempCode =tblSubject.getValueAt(tblSubject.getSelectedRow(),1).toString();
        btnSubAdd.setText("Save");
    }//GEN-LAST:event_btnSubEditeActionPerformed

    private void btnSubRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubRemoveActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblSubject.getModel(); 
             // model.removeRow(tblDep.getSelectedRow());
             System.out.println(tblSubject.getValueAt(tblSubject.getSelectedRow(),1));
             
             Connection con = myConnection.getConnection();

       PreparedStatement ps;
       
        try {
            ps = con.prepareStatement("DELETE FROM subject WHERE subject_Code=?");
            ps.setString(1, tblSubject.getValueAt(tblSubject.getSelectedRow(),1).toString());
            
            
            
            if(!ps.execute())
            {
                JOptionPane.showMessageDialog(null, "Subject Successfuly Removed","Sucess", JOptionPane.PLAIN_MESSAGE);
                Refresh();
            }else{
                JOptionPane.showMessageDialog(null, "Invalied input","Error", JOptionPane.ERROR_MESSAGE);
                
            }
            // TODO add your handling code here:
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Please check Subject related to another table","Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Loging.class.getName()).log(Level.SEVERE, null, ex);
            
        }
      
    }//GEN-LAST:event_btnSubRemoveActionPerformed

    private void tblSubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSubjectMouseClicked


           btnSubRemove.setEnabled(true);
            btnSubEdite.setEnabled(true);







        // TODO add your handling code here:
    }//GEN-LAST:event_tblSubjectMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
        ExameShedule fm = new ExameShedule();
                fm.setVisible(true);
                fm.pack();
                fm.setLocationRelativeTo(null);
                this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnAddHallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddHallActionPerformed
          Connection con = myConnection.getConnection();

       PreparedStatement ps;
       if(btnAddHall.getText()=="Save")
       {
        try {
            ps = con.prepareStatement("UPDATE hall  SET hall_code = ?,hall_name = ?,Capacity =? WHERE  hall_code = ?");
            ps.setString(1, txtHallCode.getText());
            ps.setString(2, txtHallName.getText());
             ps.setString(3, txtHallCapacity.getText());
              
               ps.setString(4, tempCode);
           
            
            
            if(!ps.execute())
            {
                JOptionPane.showMessageDialog(null, "Hall Successfuly Updated","Sucess", JOptionPane.PLAIN_MESSAGE);
                Refresh();
                txtHallCapacity.setText("");
                txtHallName.setText("");
                txtHallCode.setText("");
               
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalied input","Error", JOptionPane.ERROR_MESSAGE);
                
            }
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(Loging.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       
       }
       else{
        try {
            ps = con.prepareStatement("INSERT INTO hall (hall_code  ,hall_name ,Capacity ) VALUES (?,?,?)");
            ps.setString(1, txtHallCode.getText());
            ps.setString(2, txtHallName.getText());
            ps.setString(3, txtHallCapacity.getText());
            
           
         
            
            
            if(!ps.execute())
            {
                JOptionPane.showMessageDialog(null, "hall Successfuly Aded","Sucess", JOptionPane.PLAIN_MESSAGE);
                Refresh();
                txtHallCapacity.setText("");
                txtHallName.setText("");
                txtHallCode.setText("");
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalied input","Error", JOptionPane.ERROR_MESSAGE);
                
            }
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(Loging.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       
       }
       
        
        
        
        
        
        
        
    }//GEN-LAST:event_btnAddHallActionPerformed
private void txtHallNameKeyTyped(java.awt.event.KeyEvent evt) {


}
private void txtHallCodeKeyTyped(java.awt.event.KeyEvent evt) {
}
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        txtHallCode.setText(tblHall.getValueAt(tblHall.getSelectedRow(),1).toString()) ;
        txtHallName.setText(tblHall.getValueAt(tblHall.getSelectedRow(),2).toString()) ;
        txtHallCapacity.setText(tblHall.getValueAt(tblHall.getSelectedRow(),3).toString()) ;
    
        tempCode =tblHall.getValueAt(tblHall.getSelectedRow(),1).toString();
       btnAddHall.setText("Save");       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void lbSelectTimeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSelectTimeTableMouseClicked
        btnPrint.setEnabled(true);
        btnRemoveTbl.setEnabled(true);
        DefaultTableModel model = (DefaultTableModel) tblTimeTable.getModel();
         Connection con = myConnection.getConnection();
        PreparedStatement ps,ps2;
                while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
            if(lbSelectTimeTable.getSelectedValue().toString().length()>18)
            {
                String tbCode = lbSelectTimeTable.getSelectedValue().toString().substring(0,18);
                try {
            ps = con.prepareStatement("SELECT * FROM "+tbCode+"");
            ps2= con.prepareStatement("SELECT  noINV FROM timetabledirectry WHERE Table_Code ='"+tbCode+"'");
            ResultSet rs = ps.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
           Object[] row = new Object[4];
            while (rs.next()) {
               
                row[0] = rs.getString("date");
                row[1] = rs.getString("morning");
                row[2] = rs.getString("evining");
                row[3] = rs.getString("Competible_hall");
                
               model.addRow(row);
            }
            while (rs2.next()) {
            txtNOfINV.setText(rs2.getString("noINV"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
                
                
                
                
            }
    }//GEN-LAST:event_lbSelectTimeTableMouseClicked

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        MessageFormat header = new MessageFormat("KDU Exame Time Table");
        MessageFormat body = new MessageFormat("total");
         MessageFormat fotter = new MessageFormat("page{0,number,integer}");
        try{
        tblTimeTable.print(JTable.PrintMode.NORMAL,header,fotter);
        }catch(java.awt.print.PrinterException e)
        {
        System.err.format("Cannot Print %s%n",e.getMessage());
        }
        
        
        
    }//GEN-LAST:event_btnPrintActionPerformed

    private void txtInvPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInvPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInvPhoneActionPerformed
public void subNcheck(){
    if(!(txtSubCode.getText().length()>4&&txtSubName.getText().length()>0&&txtExameDuration.getText().length()>0&&txtCredite.getText().length()>0))
    {
     btnSubAdd.setEnabled(false);
    }else{
   btnSubAdd.setEnabled(true);
    
    }
    
    }
public void hallNcheck(){
    if(!(txtHallCode.getText().length()>0&&txtHallName.getText().length()>0&&txtHallCapacity.getText().length()>0))
    {
     btnAddHall.setEnabled(false);
    }else{
   btnAddHall.setEnabled(true);
    
    }
    
    }
public void depNcheck(){
    if(!(txtDepName.getText().length()>0&&txtDepCode.getText().length()>0))
    {
     btnAddDep.setEnabled(false);
    }else{
   btnAddDep.setEnabled(true);
    
    }
    
    }
public void crsNcheck(){
    if(!(txyCourseCode.getText().length()>0&&txtCourseName.getText().length()>0&&txtCNoOfStudent.getText().length()>0))
    {
     btnAddCourse.setEnabled(false);
    }else{
   btnAddCourse.setEnabled(true);
    
    }
    
    }
public void invNcheck(){
    if(!(txtInvCode.getText().length()>0&&txtInvName.getText().length()>0&&txtInvPhone.getText().length()>9&&txtInvAddress.getText().length()>0))
    {
     btnAddInv.setEnabled(false);
    }else{
   btnAddInv.setEnabled(true);
    
    }
    
    }
    
    
    
    private void btnAddInvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddInvActionPerformed
        System.out.println(tempCode);
        
          Connection con = myConnection.getConnection();

       PreparedStatement ps;
       if(btnAddInv.getText()=="Save")
       {
        try {
            ps = con.prepareStatement("UPDATE invigilators SET invigilator_id = ?,invigilator_Name = ?,Tell_no = ?,Address = ? WHERE invigilator_id = ?");
            ps.setString(1, txtInvCode.getText());
            ps.setString(2, txtInvName.getText());
             ps.setString(3, txtInvPhone.getText());
             ps.setString(4, txtInvAddress.getText());
               ps.setString(5, tempCode);
           
            
            
            if(!ps.execute())
            {
                JOptionPane.showMessageDialog(null, "Invigilator Successfuly Updated","Sucess", JOptionPane.PLAIN_MESSAGE);
                Refresh();
                txtInvCode.setText("");
                txtInvName.setText("");
                txtInvPhone.setText("");
                txtInvAddress.setText("");
                btnAddCourse.setText("Add Invigilator");
                 btnInvRemove.setEnabled(false);
                btnInvEdit.setEnabled(false);
                btnAddInv.setEnabled(false);
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalied input","Error", JOptionPane.ERROR_MESSAGE);
                
            }
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(Loging.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       
       }
       else{
        try {
            ps = con.prepareStatement("INSERT INTO invigilators(invigilator_id,invigilator_Name,Tell_no,Address)VALUES (? ,?,?,?)");
            ps.setString(1, txtInvCode.getText());
            ps.setString(2, txtInvName.getText());
            ps.setString(3, txtInvPhone.getText());
            ps.setString(4, txtInvAddress.getText());
            
           
         
            
            
            if(!ps.execute())
            {
                JOptionPane.showMessageDialog(null, "Invigilator Successfuly Aded","Sucess", JOptionPane.PLAIN_MESSAGE);
                Refresh();
                txtInvCode.setText("");
                txtInvName.setText("");
                txtInvPhone.setText("");
                txtInvAddress.setText("");
                btnAddInv.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(null, "Invalied input","Error", JOptionPane.ERROR_MESSAGE);
                
            }
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(Loging.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       
       }
       
        
        
        
        
        
    }//GEN-LAST:event_btnAddInvActionPerformed

    private void btnInvEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInvEditActionPerformed
            btnAddInv.setText("Save");
            btnAddInv.setEnabled(true);
       txtInvCode.setText(tblINV.getValueAt(tblINV.getSelectedRow(),1).toString()) ;
       txtInvName.setText(tblINV.getValueAt(tblINV.getSelectedRow(),2).toString()) ;
       txtInvPhone.setText(tblINV.getValueAt(tblINV.getSelectedRow(),3).toString()) ;
      txtInvAddress.setText(tblINV.getValueAt(tblINV.getSelectedRow(),4).toString()) ;
      
        tempCode =tblINV.getValueAt(tblINV.getSelectedRow(),1).toString();
        
       
    }//GEN-LAST:event_btnInvEditActionPerformed

    private void tblINVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblINVMouseClicked
        btnInvRemove.setEnabled(true);
        btnInvEdit.setEnabled(true);
    }//GEN-LAST:event_tblINVMouseClicked

    private void txtInvPhoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInvPhoneKeyTyped
        char enter = evt.getKeyChar();
        invNcheck();
        
        if(!(Character.isDigit(enter)||enter=='+'))
        {
        evt.consume();
        }
        
        if ((txtInvPhone.getText().length()==0)) {
            if((enter=='0'))
            {
            
            }else
            {
            evt.consume();
            }
        }
         if (!(txtInvPhone.getText().length() <10)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtInvPhoneKeyTyped

    private void txtInvCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInvCodeKeyTyped
         if (!(txtInvCode.getText().length() <5)) {
            evt.consume();
        }
         invNcheck();
    }//GEN-LAST:event_txtInvCodeKeyTyped

    private void txtInvCodePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtInvCodePropertyChange
       invNcheck();
    }//GEN-LAST:event_txtInvCodePropertyChange

    private void txtInvNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInvNameKeyTyped
       invNcheck();
    }//GEN-LAST:event_txtInvNameKeyTyped

    private void txtInvAddressKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInvAddressKeyTyped
      invNcheck();
    }//GEN-LAST:event_txtInvAddressKeyTyped

    private void txtCNoOfStudentKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCNoOfStudentKeyTyped
      char enter = evt.getKeyChar();
        crsNcheck();
       if(!(Character.isDigit(enter)))
        {
        evt.consume();
        }
    }//GEN-LAST:event_txtCNoOfStudentKeyTyped

    private void txtCourseTitleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCourseTitleKeyTyped
        crsNcheck();
    }//GEN-LAST:event_txtCourseTitleKeyTyped

    private void txyCourseCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txyCourseCodeKeyPressed
       crsNcheck(); // TODO add your handling code here:
    }//GEN-LAST:event_txyCourseCodeKeyPressed

    private void txtCourseNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCourseNameKeyPressed
       crsNcheck(); // TODO add your handling code here:
    }//GEN-LAST:event_txtCourseNameKeyPressed

    private void txtCourseTitleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCourseTitleKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCourseTitleKeyReleased

    private void txtCourseTitleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCourseTitleKeyPressed
       crsNcheck(); // TODO add your handling code here:
    }//GEN-LAST:event_txtCourseTitleKeyPressed

    private void txtCNoOfStudentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCNoOfStudentKeyPressed
        crsNcheck();// TODO add your handling code here:
    }//GEN-LAST:event_txtCNoOfStudentKeyPressed

    private void txtDepCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepCodeKeyPressed
depNcheck();        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepCodeKeyPressed

    private void txtDepNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepNameKeyPressed
depNcheck();        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepNameKeyPressed

    private void tblDepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDepMouseClicked
        btnDepRemove.setEnabled(true);
        btnDepEdite.setEnabled(true);
    }//GEN-LAST:event_tblDepMouseClicked

    private void txtHallCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHallCodeKeyPressed
hallNcheck();        // TODO add your handling code here:
    }//GEN-LAST:event_txtHallCodeKeyPressed

    private void txtHallNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHallNameKeyPressed
      hallNcheck();    // TODO add your handling code here:
    }//GEN-LAST:event_txtHallNameKeyPressed

    private void txtHallCapacityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHallCapacityKeyPressed
        hallNcheck();  // TODO add your handling code here:
    }//GEN-LAST:event_txtHallCapacityKeyPressed

    private void tblTimeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTimeTableMouseClicked
        btnRemoveTbl.setEnabled(false);// TODO add your handling code here:
    }//GEN-LAST:event_tblTimeTableMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        Connection con = myConnection.getConnection();

       PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("INSERT INTO users(user_id ,password,create_date,privilage)VALUES(?,?,NOW(),?)");
            ps.setString(1, txtUName.getText());
            ps.setString(2, txtUPwrd.getText());
            ps.setString(3, "Admin");
            
           
         
            
            
            if(!ps.execute())
            {
                JOptionPane.showMessageDialog(null, "User Successfuly Aded","Sucess", JOptionPane.PLAIN_MESSAGE);
                Refresh();
                txtUName.setText("");
                txtUPwrd.setText("");
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalied input","Error", JOptionPane.ERROR_MESSAGE);
                
            }
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(Loging.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnRemoveTblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveTblActionPerformed
        if(lbSelectTimeTable.getSelectedValue().toString().length()>18)
        {
              String tbCode = lbSelectTimeTable.getSelectedValue().toString().substring(0,18);  
                Connection con = myConnection.getConnection();
              PreparedStatement ps,ps2;  
                
                 try {
            ps = con.prepareStatement("DROP TABLE IF EXISTS "+tbCode);
            ps2 =con.prepareStatement("DELETE FROM timetabledirectry WHERE Table_Code= '"+tbCode+"'");    
            
            if(!ps.execute())
            {
                if(!ps2.execute())
                {
            JOptionPane.showMessageDialog(null, "Table Delete Sucess","Sucess", JOptionPane.PLAIN_MESSAGE);
               Refresh();
                }
            }
            
           
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        }
    }//GEN-LAST:event_btnRemoveTblActionPerformed

    private void btnUserRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserRemoveActionPerformed
 
             
             
             Connection con = myConnection.getConnection();

       PreparedStatement ps;
       if(!(cmbUserRemove.getItemCount()<2))
       {
        try {
            ps = con.prepareStatement("DELETE FROM users WHERE user_id=?");
            ps.setString(1, cmbUserRemove.getSelectedItem().toString());
            
            
            
            if(!ps.execute())
            {
                JOptionPane.showMessageDialog(null, "User Successfuly Removed","Sucess", JOptionPane.PLAIN_MESSAGE);
                Refresh();
            }else{
                JOptionPane.showMessageDialog(null, "Invalied input","Error", JOptionPane.ERROR_MESSAGE);
                
            }
            // TODO add your handling code here:
        } catch (SQLException ex) {
            
            Logger.getLogger(Loging.class.getName()).log(Level.SEVERE, null, ex);
            
        }       
       }
       else{
       JOptionPane.showMessageDialog(null, "System has only one user Cannot delete","Error", JOptionPane.ERROR_MESSAGE);
       
       }
        
         Refresh();
        
    }//GEN-LAST:event_btnUserRemoveActionPerformed

    private void btnAddCourseActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println(tempCode);
        
          Connection con = myConnection.getConnection();

       PreparedStatement ps;
       if(btnAddCourse.getText()=="Save")
       {
        try {
            ps = con.prepareStatement("UPDATE courses SET course_code = ? ,course_name = ?  ,course_title = ? ,number_Of_Student = ?  ,course_department_ID = ? WHERE course_code = ?");
            ps.setString(1, txyCourseCode.getText());
            ps.setString(2, txtCourseName.getText());
             ps.setString(3, txtCourseTitle.getText());
             ps.setString(4, txtCNoOfStudent.getText());
              ps.setString(5, cmbDepCode.getSelectedItem().toString());
               ps.setString(6, tempCode);
           
            
            
            if(!ps.execute())
            {
                JOptionPane.showMessageDialog(null, "Course Successfuly Updated","Sucess", JOptionPane.PLAIN_MESSAGE);
                Refresh();
                txyCourseCode.setText("");
                txtCourseName.setText("");
                txtCourseTitle.setText("");
                txtCNoOfStudent.setText("");
                btnAddCourse.setText("Add Course");
                 btnCrsRemove.setEnabled(false);
                btnCrsEdite.setEnabled(false);
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalied input","Error", JOptionPane.ERROR_MESSAGE);
                
            }
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(Loging.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       
       }
       else{
        try {
            ps = con.prepareStatement("INSERT INTO courses(course_code,course_name,course_title,number_Of_Student,course_department_ID)VALUES(?,?,?,?,?)");
            ps.setString(1, txyCourseCode.getText());
            ps.setString(2, txtCourseName.getText());
            ps.setString(3, txtCourseTitle.getText());
            ps.setString(4, txtCNoOfStudent.getText());
            ps.setString(5, cmbDepCode.getSelectedItem().toString());
           
         
            
            
            if(!ps.execute())
            {
                JOptionPane.showMessageDialog(null, "course Successfuly Aded","Sucess", JOptionPane.PLAIN_MESSAGE);
                Refresh();
                txyCourseCode.setText("");
                txtCourseName.setText("");
                txtCourseTitle.setText("");
                txtCNoOfStudent.setText("");
            }else{
                JOptionPane.showMessageDialog(null, "Invalied input","Error", JOptionPane.ERROR_MESSAGE);
                
            }
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(Loging.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       
       }
       
        
        
        
        
        
        
        
       // Object item = cmbDepCode.getSelectedItem();
       // JOptionPane.showMessageDialog(null, ((ComboItem)item).getValue());
       
    }

    private void txtDepCodeFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtDepCodeFocusLost

    }// GEN-LAST:event_txtDepCodeFocusLost

    private void txtDepCodeKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtDepCodeKeyTyped

        char enter = evt.getKeyChar();
        if (!(txtDepCode.getText().length() < 5)) {
            evt.consume();
        }

        // TODO add your handling code here:
    }// GEN-LAST:event_txtDepCodeKeyTyped

    private void txtCrediteKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtCrediteKeyTyped
subNcheck();
        float duration = 0;
        char enter = evt.getKeyChar();

        String word = txtCredite.getText() + enter;
        System.out.println(word + "\b");
        if (!(Character.isDigit(enter))) {
            evt.consume();
        }
        try {
            duration = Float.parseFloat(word);
        } catch (Exception e) {
            evt.consume();
        }

        if (!(duration <= 5)) {
            evt.consume();
        }

        // Subject credite validation
        // only digit can enter
        // number must be lesthan 5

    }// GEN-LAST:event_txtCrediteKeyTyped

    private void txtExameDurationKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtExameDurationKeyTyped
        float duration = 0;
        subNcheck();
        char enter = evt.getKeyChar();

        String word = txtExameDuration.getText() + enter;
        System.out.println(word + "\b");
        if (!(Character.isDigit(enter) || enter == '.')) {
            evt.consume();
        }
        try {
            duration = Float.parseFloat(word);
        } catch (Exception e) {
            evt.consume();
        }

        if (!(duration <= 6)) {
            evt.consume();
        }

        // Validate exame duration
        // only digit can enter
        // number must be lesthan 6
    }// GEN-LAST:event_txtExameDurationKeyTyped

    private void txtSubNameKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtSubNameKeyTyped
        char enter = evt.getKeyChar();
subNcheck();
        // TODO add your handling code here:
    }// GEN-LAST:event_txtSubNameKeyTyped

    private void txyCourseCodeKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txyCourseCodeKeyTyped
crsNcheck();
        char enter = evt.getKeyChar();
        if (!(txyCourseCode.getText().length() < 5)) {
            evt.consume();
        }

        // TODO add your handling code here:
    }// GEN-LAST:event_txyCourseCodeKeyTyped

    private void txtCourseNameKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtCourseNameKeyTyped
        crsNcheck();
        char enter = evt.getKeyChar();
        if (!(Character.isAlphabetic(enter) || enter == ' ')) {
            evt.consume();
        }

        // TODO add your handling code here:
    }// GEN-LAST:event_txtCourseNameKeyTyped

    private void txtSubCodeKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtSubCodeKeyTyped

        char enter = evt.getKeyChar();

        String x = txtSubCode.getText();
        x = x.toUpperCase();
        txtSubCode.setText(x);
        if (txtSubCode.getText().length() > 5) {
            evt.consume();
        }
        if (enter == ' ' || enter == '.' || enter == ',' || enter == '[' || enter == ']' || enter == '.') {
            evt.consume();
        }
        if (txtSubCode.getText().length() > 1) {
            if (Character.isAlphabetic(enter)) {
                evt.consume();
            }
        } else {
            if (!Character.isAlphabetic(enter)) {
                evt.consume();
            }
        }

        if (!((txtSubCode.getText().length() > 1) || !(Character.isAlphabetic(enter)))) {

        } else {
            if (((txtSubCode.getText().length() > 6) && (txtSubCode.getText().length() < 1))
                    || (Character.isAlphabetic(enter))) {

            }
        }

        // TODO add your handling code here:
    }// GEN-LAST:event_txtSubCodeKeyTyped

    private void txtSubCodeFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtSubCodeFocusLost

        // TODO add your handling code here:
    }// GEN-LAST:event_txtSubCodeFocusLost

    private void txyCourseCode1KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txyCourseCode1KeyTyped
        crsNcheck();
    }// GEN-LAST:event_txyCourseCode1KeyTyped

    private void txtCourseName1KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtCourseName1KeyTyped
       crsNcheck();
    }// GEN-LAST:event_txtCourseName1KeyTyped

    private void cmbDepCodeItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_cmbDepCodeItemStateChanged
        Object item = cmbDepCode.getSelectedItem();
        // JOptionPane.showMessageDialog(null, ((ComboItem)item).getValue());

    }// GEN-LAST:event_cmbDepCodeItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Course;
    private javax.swing.JPanel Department;
    private javax.swing.JPanel Subject;
    private javax.swing.JPanel TimeTable;
    private javax.swing.JButton btnAddCourse;
    private javax.swing.JButton btnAddDep;
    private javax.swing.JButton btnAddHall;
    private javax.swing.JButton btnAddInv;
    private javax.swing.JButton btnCrsEdite;
    private javax.swing.JButton btnCrsRemove;
    private javax.swing.JButton btnDepEdite;
    private javax.swing.JButton btnDepRemove;
    private javax.swing.JButton btnInvEdit;
    private javax.swing.JButton btnInvRemove;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnRemoveTbl;
    private javax.swing.JButton btnSubAdd;
    private javax.swing.JButton btnSubEdite;
    private javax.swing.JButton btnSubRemove;
    private javax.swing.JButton btnUserRemove;
    private javax.swing.JComboBox<String> cmbCCode;
    private javax.swing.JComboBox<String> cmbDepCode;
    private javax.swing.JComboBox<String> cmbUserRemove;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList<String> lbSelectTimeTable;
    private javax.swing.JPanel pnlHall;
    private javax.swing.JTabbedPane pnlMain;
    private javax.swing.JTable tblCrs;
    private javax.swing.JTable tblDep;
    private javax.swing.JTable tblHall;
    private javax.swing.JTable tblINV;
    private javax.swing.JTable tblSubject;
    private javax.swing.JTable tblTimeTable;
    private javax.swing.JTextField txtCNoOfStudent;
    private javax.swing.JTextField txtCourseName;
    private javax.swing.JTextField txtCourseTitle;
    private javax.swing.JTextField txtCredite;
    private javax.swing.JTextField txtDepCode;
    private javax.swing.JTextField txtDepName;
    private javax.swing.JTextField txtExameDuration;
    private javax.swing.JTextField txtHallCapacity;
    private javax.swing.JTextField txtHallCode;
    private javax.swing.JTextField txtHallName;
    private javax.swing.JTextField txtInvAddress;
    private javax.swing.JTextField txtInvCode;
    private javax.swing.JTextField txtInvName;
    private javax.swing.JTextField txtInvPhone;
    private javax.swing.JTextField txtNOfINV;
    private javax.swing.JTextField txtSubCode;
    private javax.swing.JTextField txtSubName;
    private javax.swing.JTextField txtUName;
    private javax.swing.JPasswordField txtUPwrd;
    private javax.swing.JTextField txyCourseCode;
    // End of variables declaration//GEN-END:variables
}
