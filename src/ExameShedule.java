/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shanb
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.DateFormat; 
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar; 
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import java.sql.Timestamp;

import org.joda.time.*;

public class ExameShedule extends javax.swing.JFrame {
     DefaultListModel modellist = new DefaultListModel();
     DefaultListModel modelSubList = new DefaultListModel();
     DefaultTableModel submodel = new  DefaultTableModel();
             
     Date date=null,dateEnd=null;
     float dayC=0;
    /**
     * Creates new form ExameShedule
     */
    public ExameShedule() {
        initComponents();
        refresh();
    }
    
    public void refresh(){
    setComponent();
    
    setdepCombo();
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
    public void setdepCombo() {
        ArrayList<Department> dep = DepartmentList();
         //ArrayList<Cources> crs = CourcesList();
            cmbDep.removeAllItems();
           // cmbCCode.removeAllItems();
        for (int i = 0; i < dep.size(); i++) {
           // cmbdep.add(new ComboItem(dep.get(i).getDepName(), dep.get(i).getDepCode()));
            cmbDep.addItem(dep.get(i).getDepCode());

        }
       // for (int i = 0; i < crs.size(); i++) {
            // cmbDepCode.addItem(cmbdep.get(i).toString());
       //  cmbCCode.addItem(crs.get(i).getCCode());
       // }
    }
    public ArrayList<Cources> CourcesList(String depID) {
        ArrayList<Cources> courceslist = new ArrayList<>();

        Connection con = myConnection.getConnection();

        PreparedStatement ps;

        try {
            ps = con.prepareStatement("SELECT * FROM courses WHERE course_department_ID =?");
                ps.setString(1, depID);
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
     public void setcrsCombo(String depID) {
        ArrayList<Cources> crs = CourcesList(depID);
         //ArrayList<Cources> crs = CourcesList();
            cmbSelectCrs.removeAllItems();
           // cmbCCode.removeAllItems();
        for (int i = 0; i < crs.size(); i++) {
           // cmbdep.add(new ComboItem(dep.get(i).getDepName(), dep.get(i).getDepCode()));
            cmbSelectCrs.addItem(crs.get(i).getCCode());

        }
       // for (int i = 0; i < crs.size(); i++) {
            // cmbDepCode.addItem(cmbdep.get(i).toString());
       //  cmbCCode.addItem(crs.get(i).getCCode());
       // }
    }
    
     public ArrayList<Subject> SubjectList(String crs) {
        ArrayList<Subject> SubjectList = new ArrayList<>();

        Connection con = myConnection.getConnection();

        PreparedStatement ps;

        try {
            ps = con.prepareStatement("SELECT * FROM subject WHERE course_code=?");
            ps.setString(1, crs);

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
     
       public void ShowSub(String crs ) {
        ArrayList<Subject> sub = SubjectList(crs);
       
        
        
        modelSubList.clear();

        for (int i = 0; i < sub.size(); i++) {
            String halld ="";
                halld =sub.get(i).getSubCode();
                if(!subcheck(halld))
                {
                modelSubList.addElement(halld);
                }
        }
        lstSubject.setModel(modelSubList);

    }
     
     
     public boolean subcheck(String subcode){
         //Cheking that subject available on the sbject table 
     int rowcount=tblSubject.getRowCount();
     String rowdata ="";
     boolean mark=false;
        for(int i=0; i<rowcount;i++)
        {
            rowdata = tblSubject.getValueAt(i, 1).toString();
            
            if(rowdata.equalsIgnoreCase(subcode))
            {
                mark=true;
            }
        
        }
     return mark;
     }
     
     
public void setComponent(){

}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTimeTable = new javax.swing.JTable();
        txtTableName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnSaveTable = new javax.swing.JButton();
        jtpgen = new javax.swing.JTabbedPane();
        pnlConfig = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        dtpStart = new org.jdesktop.swingx.JXDatePicker();
        dtpEnd = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtbldate = new javax.swing.JTable();
        cbAllowSat = new javax.swing.JCheckBox();
        cbAllowSun = new javax.swing.JCheckBox();
        jButton6 = new javax.swing.JButton();
        pnlSelectSub = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstSubject = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSubject = new javax.swing.JTable();
        btnSubRemove = new javax.swing.JButton();
        cmbDep = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbSelectCrs = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtNumOfINV = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHall = new javax.swing.JTable();
        pnlGenarate = new javax.swing.JPanel();
        btnGenarate = new javax.swing.JButton();
        lblConfiWarning = new javax.swing.JLabel();
        cbSkip = new javax.swing.JCheckBox();
        cbOLSpace = new javax.swing.JCheckBox();
        btnBackToHome = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Time Table Management System");

        tblTimeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Morning", "Evining", "Comapatible Hall"
            }
        ));
        jScrollPane2.setViewportView(tblTimeTable);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setText("Enter TimeTable Caption");

        btnSaveTable.setText("Save Table");
        btnSaveTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(2, 2, 2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTableName, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btnSaveTable, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(176, 176, 176))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTableName, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSaveTable, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        jLabel1.setText("Select Exam Start Date");

        jLabel2.setText("Select Exam End Date");

        jButton5.setText("Calculate Days");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        dtpStart.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                dtpStartPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        dtpStart.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtpStartPropertyChange(evt);
            }
        });
        dtpStart.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                dtpStartVetoableChange(evt);
            }
        });

        dtpEnd.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtpEndPropertyChange(evt);
            }
        });

        jtbldate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jtbldate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Exam Start Day", null},
                {"Exam End Day", null},
                {"Number of Days", null},
                {"Saturday Count", null},
                {"Sunday count", null},
                {"Number of Available Days", null}
            },
            new String [] {
                "Discription", "Value"
            }
        ));
        jtbldate.setEnabled(false);
        jScrollPane6.setViewportView(jtbldate);

        cbAllowSat.setText("Allow Saturday");

        cbAllowSun.setText("Allow Sunday");

        jButton6.setText("Next");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlConfigLayout = new javax.swing.GroupLayout(pnlConfig);
        pnlConfig.setLayout(pnlConfigLayout);
        pnlConfigLayout.setHorizontalGroup(
            pnlConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConfigLayout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlConfigLayout.createSequentialGroup()
                .addGroup(pnlConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlConfigLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlConfigLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlConfigLayout.createSequentialGroup()
                                .addGroup(pnlConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(33, 33, 33)
                                .addGroup(pnlConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dtpEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dtpStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbAllowSat)
                                    .addComponent(cbAllowSun))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlConfigLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        pnlConfigLayout.setVerticalGroup(
            pnlConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConfigLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(pnlConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dtpStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(dtpEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(cbAllowSat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbAllowSun)
                .addGap(30, 30, 30)
                .addComponent(jButton5)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jtpgen.addTab("Configuration", pnlConfig);

        lstSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstSubjectMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstSubject);

        tblSubject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "no", "Subject Code", "Subject Name", "Course Code", "Credite", "exame Duration"
            }
        ));
        jScrollPane3.setViewportView(tblSubject);

        btnSubRemove.setText("remove");
        btnSubRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubRemoveActionPerformed(evt);
            }
        });

        cmbDep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbDep.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDepItemStateChanged(evt);
            }
        });

        jLabel3.setText("Select Department");

        cmbSelectCrs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSelectCrs.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSelectCrsItemStateChanged(evt);
            }
        });

        jLabel4.setText("Select Course");

        txtNumOfINV.setEnabled(false);

        jLabel5.setText("Number of Invigilators Need");

        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblHall.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "hall Code", "hall Name", "Capacity"
            }
        ));
        jScrollPane4.setViewportView(tblHall);

        javax.swing.GroupLayout pnlSelectSubLayout = new javax.swing.GroupLayout(pnlSelectSub);
        pnlSelectSub.setLayout(pnlSelectSubLayout);
        pnlSelectSubLayout.setHorizontalGroup(
            pnlSelectSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectSubLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSelectSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSelectSubLayout.createSequentialGroup()
                        .addGroup(pnlSelectSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSelectSubLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(pnlSelectSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlSelectSubLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNumOfINV))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSelectSubLayout.createSequentialGroup()
                                        .addGroup(pnlSelectSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pnlSelectSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cmbSelectCrs, 0, 145, Short.MAX_VALUE)
                                            .addComponent(cmbDep, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addComponent(btnSubRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlSelectSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 8, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        pnlSelectSubLayout.setVerticalGroup(
            pnlSelectSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectSubLayout.createSequentialGroup()
                .addGroup(pnlSelectSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSelectSubLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(pnlSelectSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(19, 19, 19)
                        .addGroup(pnlSelectSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbSelectCrs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(24, 24, 24)
                        .addGroup(pnlSelectSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtNumOfINV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSelectSubLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlSelectSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(98, 98, 98))
        );

        jtpgen.addTab("SelectSubject", pnlSelectSub);

        btnGenarate.setText("Genarate");
        btnGenarate.setEnabled(false);
        btnGenarate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenarateActionPerformed(evt);
            }
        });

        lblConfiWarning.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblConfiWarning.setForeground(new java.awt.Color(255, 0, 51));
        lblConfiWarning.setText("Set Configuration First...!");

        cbSkip.setText("Skip Saturday Sunday");

        cbOLSpace.setText("optimize to Last Time  Space");

        javax.swing.GroupLayout pnlGenarateLayout = new javax.swing.GroupLayout(pnlGenarate);
        pnlGenarate.setLayout(pnlGenarateLayout);
        pnlGenarateLayout.setHorizontalGroup(
            pnlGenarateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGenarateLayout.createSequentialGroup()
                .addGroup(pnlGenarateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGenarateLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(btnGenarate, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlGenarateLayout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(lblConfiWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlGenarateLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(pnlGenarateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbOLSpace)
                            .addComponent(cbSkip))))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        pnlGenarateLayout.setVerticalGroup(
            pnlGenarateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGenarateLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(cbSkip)
                .addGap(18, 18, 18)
                .addComponent(cbOLSpace)
                .addGap(111, 111, 111)
                .addComponent(lblConfiWarning)
                .addGap(44, 44, 44)
                .addComponent(btnGenarate, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        jtpgen.addTab("Genarate", pnlGenarate);

        btnBackToHome.setText("Back To Home");
        btnBackToHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToHomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtpgen, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(btnBackToHome)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtpgen, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBackToHome, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackToHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToHomeActionPerformed

        frmHome fm = new frmHome();
                fm.setVisible(true);
                fm.pack();
                fm.setLocationRelativeTo(null);
                this.dispose();

    }//GEN-LAST:event_btnBackToHomeActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if(!(dtpEnd.getDate()==null&&dtpStart.getDate()==null))
        {

        String StartDate=null, EndDate =null;
       
        try {
            
            DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
            
            date = (Date)formatter.parse(dtpStart.getDate().toString());  
            dateEnd =(Date) formatter.parse(dtpEnd.getDate().toString());
            //System.out.println(date);
            
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            
            cal.setTime(date);
            cal2.setTime(dateEnd);
   
    
    StartDate = cal.get(Calendar.DATE) + "-" + 
            (cal.get(Calendar.MONTH) + 1) + 
            "-" +         cal.get(Calendar.YEAR);
    
    EndDate = cal2.get(Calendar.DATE) + "-" + 
            (cal2.get(Calendar.MONTH) + 1) + 
            "-" +         cal2.get(Calendar.YEAR);
   
       
        
        } catch (ParseException ex) {
            Logger.getLogger(ExameShedule.class.getName()).log(Level.SEVERE, null, ex);
        }
      int totaldayCount = 0;
      int satCount =0;
      int sunCount =0;
      int dayCount =0;
     
      try{
           hollyDay nh = new hollyDay(date,dateEnd);
          
      totaldayCount = nh.dayCount();
      sunCount=nh.sunCount();
      satCount=nh.satCount();
      dayCount = totaldayCount;
      if(cbAllowSun.isSelected()==false)
        {
            
        dayCount -= sunCount ;
        
        }
      if(cbAllowSat.isSelected()==false)
        {
        dayCount -= satCount;
        
        }
      }catch(Exception e){
      }
       
         DefaultTableModel model = (DefaultTableModel) jtbldate.getModel();
         model.setValueAt(StartDate, 0, 1);
          model.setValueAt(EndDate, 1, 1);
           model.setValueAt(totaldayCount, 2, 1);
            model.setValueAt(satCount, 3, 1);
             model.setValueAt(sunCount, 4, 1);
              model.setValueAt(dayCount, 5, 1);
        dayC =dayCount;
        
     System.out.println(StartDate);
     System.out.println(EndDate);
     btnGenarate.setEnabled(true);
     lblConfiWarning.setVisible(false);
        }
        else{
            JOptionPane.showMessageDialog(cmbDep,"Please select End and Start Date.","Date error",JOptionPane.ERROR_MESSAGE);
        
        }
    }//GEN-LAST:event_jButton5ActionPerformed
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
           jtpgen.setSelectedIndex(1);
    
// TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    
    public int totalINV(String crs)
    {
        int totalinv =0;
        
        Connection con = myConnection.getConnection();

        PreparedStatement ps;

        try {
            ps = con.prepareStatement("SELECT number_Of_Student FROM courses WHERE course_code=?");
            ps.setString(1, crs);

            ResultSet rs = ps.executeQuery();
            Subject sub;
            while (rs.next()) {
                int stu = rs.getInt(1);
               totalinv = (stu/30)+1;
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    return totalinv;
    }
    //==============================================================================================================
     public void getHall(String crs)
    {
        try {
            String dp ="xxx";
                     try{
        dp=cmbDep.getSelectedItem().toString();
        
        
        }catch(Exception e){}
        ArrayList<Cources> crs1 = CourcesList(dp);
   DefaultTableModel hmodel =     (DefaultTableModel) tblHall.getModel(); 
   
    while (hmodel.getRowCount() > 0) {
            hmodel.removeRow(0);
        }
    
    
        int capacity =0;
        for (int i = 0; i < crs1.size(); i++) {
           // cmbdep.add(new ComboItem(dep.get(i).getDepName(), dep.get(i).getDepCode()));
           if(crs1.get(i).getCCode().equalsIgnoreCase(crs))
           {
           capacity = crs1.get(i).getCNofs();
           System.out.println(capacity);
           Connection con = myConnection.getConnection();

        PreparedStatement ps;

        
            ps = con.prepareStatement("SELECT * FROM hall WHERE Capacity>=?");
            ps.setInt(1, capacity);

            ResultSet rs = ps.executeQuery();
            Subject sub;
            while (rs.next()) {
                
                Object[] row = new Object[3];
                row[0]=rs.getString("hall_code");
                row[1]=rs.getString("hall_name");
                row[2]=rs.getString("Capacity");
               hmodel.addRow(row);
               
            }
           
           }

        }
        
        
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    
    }
    
    
    private void cmbDepItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDepItemStateChanged
        String dep ="xxx";
        
        System.out.println(cmbDep.getSelectedItem());
        try{
        dep=cmbDep.getSelectedItem().toString();
        
        }catch(Exception e){}
        setcrsCombo(dep);
        
        
    }//GEN-LAST:event_cmbDepItemStateChanged

    private void cmbSelectCrsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSelectCrsItemStateChanged
        
        while (submodel.getRowCount() > 0) {
            submodel.removeRow(0);
        }
                String crs ="xxx";
                     try{
        crs=cmbSelectCrs.getSelectedItem().toString();
        
        
        }catch(Exception e){}
                     ShowSub(crs);
                     getHall(crs);
           int totalinv = totalINV(crs);
           String tINV =Integer.toString(totalinv);
       txtNumOfINV.setText(tINV);
           
    }//GEN-LAST:event_cmbSelectCrsItemStateChanged

    private void lstSubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstSubjectMouseClicked
       submodel =     (DefaultTableModel) tblSubject.getModel();                
            
        DefaultListModel modells = (DefaultListModel) lstSubject.getModel();
        ArrayList<Subject> sub = SubjectList(cmbSelectCrs.getSelectedItem().toString());
        int selectedInex = lstSubject.getSelectedIndex();
        int i =1;
            for(Subject s : sub )
            {   
                if(s.getSubCode().equals(lstSubject.getSelectedValue()))
                { 
                    Object[] row = new Object[6];
                    row[0] = i;
                    row[1] = s.getSubCode();
                    row[2] = s.getSubName();
                     row[3]=cmbSelectCrs.getSelectedItem().toString();
                    row[4]=s.getCredit();
                    row[5]=s.getExameHour();
                     submodel.addRow(row);
                     modells.remove(selectedInex);
                    break;
                }
            
            }

    }//GEN-LAST:event_lstSubjectMouseClicked

    private void btnSubRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubRemoveActionPerformed
  DefaultTableModel model = (DefaultTableModel) tblSubject.getModel(); 
       
       //modellist.addElement(tblSelectedHall.getValueAt(tblSelectedHall.getSelectedRow(),1).toString());
        //lstHall.setModel(modellist);
        model.removeRow(tblSubject.getSelectedRow());
        
        
                String crs ="xxx";
                     try{
        crs=cmbSelectCrs.getSelectedItem().toString();
        
        }catch(Exception e){}
                     ShowSub(crs);
      // TODO add your handling code here:
    }//GEN-LAST:event_btnSubRemoveActionPerformed
public void Genarate(){

        if(tblSubject.getRowCount()>0)
{
        
        DefaultTableModel timeTablemodel = (DefaultTableModel) tblTimeTable.getModel(); 
 while (timeTablemodel.getRowCount() > 0) {
            timeTablemodel.removeRow(0);
        }
Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    
    c1.setTime(date);
    c2.setTime(dateEnd);
    int totalCredite=0;
    ArrayList<dateSet> dateList = new ArrayList<>();
         DateFormatSymbols dfs = new DateFormatSymbols();
String weekdays[] = dfs.getWeekdays();

int nofdate=0;
    dateSet ndateset;
    
             while (! c1.after(c2)) {
                 nofdate++;
                int d = c1.get(Calendar.DAY_OF_WEEK);
                    String nameOfDay = weekdays[d];
                  
                        String nd=c1.get(Calendar.DATE) + "-" + 
            (c1.get(Calendar.MONTH) + 1) + 
            "-" +         c1.get(Calendar.YEAR);
                   
                         ndateset = new dateSet(nd,nameOfDay);
                         dateList.add(ndateset);
                        
                        
                    
    c1.add(Calendar.DATE, 1);
     }

             float timesloat = dateList.size();
             
             
             for(int i=0; i<dateList.size();i++)
             {
                 System.out.println(dateList.get(i).date());
                 System.out.println(dateList.get(i).day());
             
             }

        ArrayList<Subject> SubjectList = new ArrayList<>();
        int tblRow =tblSubject.getRowCount();
        Subject sub;
        for(int i=0;i<tblRow;i++)
        {double d = Double.parseDouble(tblSubject.getValueAt(i,4).toString());
            sub = new Subject(tblSubject.getValueAt(i,1).toString(),
                    tblSubject.getValueAt(i,2).toString(),
                   tblSubject.getValueAt(i,3).toString(),
                    (int) d, 
                    (int) Float.parseFloat(tblSubject.getValueAt(i,5).toString()));
                SubjectList.add(sub);
        
        }
        for(Subject s : SubjectList)
        {
        System.out.println(s.getCredit());
        
        }
        System.out.println("After Short");
        Collections.sort(SubjectList);
        
        for(Subject s : SubjectList)
        {
            totalCredite += s.getCredit();
        
        
        }
        System.out.println("total credite="+totalCredite);
        
        double sloatpercredite = ((timesloat)*2)/(totalCredite);
        System.out.println("timeSloat="+timesloat*2);
        System.out.println("sloat per credite="+sloatpercredite);
        System.out.println("date list Size ="+dateList.size());
        System.out.println(sloatpercredite-Math.floor(sloatpercredite));
        
     
     subAndDate[][] ttarray = new subAndDate[dateList.size()][2];
     int added =0;
     int counter=0;
     double ttlslt=0;
     int freeslat =0;
     for(int i=0;i<dateList.size();i++)
     {
            for(int j=0;j<2;j++)
                   
            {   int nextC =0;
                try{  
                nextC =(int) SubjectList.get(added+1).getCredit();
                    }
                    catch(Exception e){}
            
                    double slt = nextC*sloatpercredite;
                if((tblSubject.getRowCount())>added)
                {
                        if(i==0&&j==0)
                        {
                             ttarray[i][j] =new subAndDate(SubjectList.get(added).getSubCode(),dateList.get(i).day(), (int) SubjectList.get(added).getCredit());
                               counter++;
                               ttlslt+=slt;
                               added++;
                               
                        }else{

                    
                               
                                   if(counter>ttlslt)
                                       {
                                           ttarray[i][j] =new subAndDate(SubjectList.get(added).getSubCode(),dateList.get(i).day(), (int) SubjectList.get(added).getCredit());
                                           counter++;
                                           ttlslt+=slt;
                                           added++;

                                       }
                                   else
                                       {
                                           ttarray[i][j] =new subAndDate("--",dateList.get(i).day(),-1);
                                           counter++;
                                       }

                               


                        }
                }
                else
                {
                ttarray[i][j] =new subAndDate("--",dateList.get(i).day(),-1);
                counter++;
                freeslat++;
                }

            }
       }
     int fd = freeslat/2;
     System.out.println("freeslt=>"+fd+","+fd++);
     
     if(cbOLSpace.isSelected()==true)
     {
       if(fd>2) 
       {
           // System.out.println("freeslat loop =>"+i);
          for(int j=ttarray.length-1;j>=0;j--)
            {System.out.println("date loop =>"+j);
                for(int k=1;k>=0;k--)
                {//System.out.println("slt loop =>"+k);
                    if(ttarray[j][k].getCredit()!=-1)
                    {
                            if(k==0)
                            {System.out.println("j,k =>"+j+","+k);
                                int i=0;
                                if(fd>0){i =--fd;}
                               ttarray[j+(i)][k+1]=new subAndDate(ttarray[j][k].getSub(),dateList.get(j+i).day(),ttarray[j][k].getCredit());
                            }
                            else
                            {int i=0;
                                if(fd>0){i =--fd;}
                               ttarray[j+(i)][k-1]=new subAndDate(ttarray[j][k].getSub(),dateList.get(j+i).day(),ttarray[j][k].getCredit());

                            }
                            if(j!=0)
                            {
                            ttarray[j][k]=new subAndDate("--",ttarray[j][k].getday(),-1);
                            }
                    }
                
                }
            
            }
        
        
       }
     }
        String halls ="";
        
        for(int i=0;i<tblHall.getRowCount();i++)
        {
            halls = halls+tblHall.getValueAt(i, 0).toString()+"-"+tblHall.getValueAt(i, 1).toString()+"  /  ";
        
        }
     
     System.out.println("===============================================================================================");
     if(cbSkip.isSelected()==true)
     {
     for(int i =0; i<ttarray.length;i++)
         {
         for(int j=0; j<2;j++)
         {
             if(ttarray[i][j].getCredit()!=-1&&ttarray[i][j].getday().equalsIgnoreCase("Sunday")&&cbAllowSun.isSelected()==false)
             {
                 if(ttarray[i+1][j].getCredit()==-1)
                 {
                 ttarray[i+1][j]=new subAndDate(ttarray[i][j].getSub(),dateList.get(i+1).day(),ttarray[i][j].getCredit());
                 }
             ttarray[i][j]=new subAndDate("--",ttarray[i][j].getday(),-1);
             }
             if(ttarray[i][j].getCredit()!=-1&&ttarray[i][j].getday().equalsIgnoreCase("Saturday")&&cbAllowSat.isSelected()==false)
             {
                 if(ttarray[i-1][j].getCredit()==-1)
                 {
                 ttarray[i-1][j]=new subAndDate(ttarray[i][j].getSub(),dateList.get(i-1).day(),ttarray[i][j].getCredit());
                 }
             ttarray[i][j]=new subAndDate("--",ttarray[i][j].getday(),-1);
             }
             
         }
         
     }
     }
        for(int i=0; i<dateList.size();i++)
        {
        Object[] row = new Object[4];
        String Wday =dateList.get(i).day();
        
if((cbAllowSun.isSelected()==false)&&(Wday.equalsIgnoreCase("Sunday"))||((cbAllowSat.isSelected()==false)&&Wday.equalsIgnoreCase("Saturday"))) {
    
    } 

else {
        row[0] = dateList.get(i).date()+"   -  "+dateList.get(i).day();

}     

        timeTablemodel.addRow(row);
      
        }
         for(int i =0; i<ttarray.length;i++)
      {
      for(int j=0; j<2;j++)
         {
             
             for(Subject s : SubjectList)
             {
             if(s.getSubCode().equals(ttarray[i][j].getSub()))
             {
               tblTimeTable.setValueAt(s.getSubCode()+"-"+s.getSubName(), i, j+1);
               tblTimeTable.setValueAt(halls, i, 3);
             
             }
             }
         
         System.out.print(ttarray[i][j].getSub());
         }
      System.out.print(ttarray[i][1].getday());
     System.out.println();
      } 
        int numOfAvailableDay=0;
       
        // TODO add your handling code here:
}else{

JOptionPane.showMessageDialog(cmbDep,"Please Select at least one Subject."," Subjects Not Selected",JOptionPane.ERROR_MESSAGE);
jtpgen.setSelectedIndex(1);
        }

}
    private void btnGenarateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenarateActionPerformed
Genarate();

    }//GEN-LAST:event_btnGenarateActionPerformed

    private void btnSaveTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveTableActionPerformed
Connection con = myConnection.getConnection();

Date dat =new Date();
long tme = dat.getTime();
String Tme ="table"+Long.toString(tme);
       PreparedStatement ps ,ps2;
        try {
        ps = con.prepareStatement("CREATE TABLE "+Tme+"( id int(11) NOT NULL AUTO_INCREMENT, date varchar(255) DEFAULT NULL, morning varchar(255) DEFAULT NULL, evining varchar(255) DEFAULT NULL, Competible_hall varchar(255) DEFAULT NULL, PRIMARY KEY (id))");
        ps2 = con.prepareStatement("INSERT INTO timetabledirectry(date,Table_Code ,Table_Name,noINV)VALUES(NOW(),?,?,?)");
        ps2.setString(1,Tme);
        ps2.setString(2,txtTableName.getText());
        ps2.setString(3,txtNumOfINV.getText());
        
        
        
        if(!ps.execute())
            {
               ps2.execute();
            for(int i=0;i<tblTimeTable.getRowCount();i++)
            {
             ps = con.prepareStatement("INSERT INTO "+Tme+" (date ,morning,evining,Competible_hall)VALUES( ? ,?,? ,?)");
           
            ps.setString(1, (String)tblTimeTable.getValueAt(i, 0));
            ps.setString(2, (String)tblTimeTable.getValueAt(i, 1));
            ps.setString(3, (String)tblTimeTable.getValueAt(i, 2));
            ps.setString(4, (String)tblTimeTable.getValueAt(i, 3));
            ps.execute();
            
            }
           
            }
        else{
       JOptionPane.showMessageDialog(null, "Table Name AlredyUse","Error", JOptionPane.ERROR_MESSAGE);
           
        }
            }
            // TODO add your handling code here:
         catch (SQLException ex) {
          Logger.getLogger(Loging.class.getName()).log(Level.SEVERE, null, ex);
         }
       
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveTableActionPerformed

    private void dtpStartPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_dtpStartPopupMenuWillBecomeInvisible
        
        System.out.println("11111111111111");
        
    }//GEN-LAST:event_dtpStartPopupMenuWillBecomeInvisible

    private void dtpStartPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtpStartPropertyChange
       if(dtpStart.getDate()!=null)
       {
           if(!dtpStart.getDate().after(new Date()))
           {
           JOptionPane.showMessageDialog(cmbDep,"You selected past date.","Invaled Date",JOptionPane.WARNING_MESSAGE);
           
           }
       
       }
    }//GEN-LAST:event_dtpStartPropertyChange

    private void dtpStartVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_dtpStartVetoableChange
       System.out.println("3333333333333333333");
    }//GEN-LAST:event_dtpStartVetoableChange

    private void dtpEndPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtpEndPropertyChange
      Calendar d1 = Calendar.getInstance();
     
        if(dtpEnd.getDate()!=null)
       {
            if(!(dtpStart.getDate()==null))
      {
           if(!dtpEnd.getDate().after(dtpStart.getDate()))
           {
               d1.setTime(dtpStart.getDate());
               d1.add(Calendar.DATE, 5);
           JOptionPane.showMessageDialog(cmbDep,"You selected date past than Start .","Invaled Date",JOptionPane.ERROR_MESSAGE);
           dtpEnd.setDate(d1.getTime());
           }
           }else{
      JOptionPane.showMessageDialog(cmbDep,"Please Select Start Date first.","Invaled Date",JOptionPane.ERROR_MESSAGE);
      dtpEnd.setDate(null);
      
      }
       }
      
    }//GEN-LAST:event_dtpEndPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jtpgen.setSelectedIndex(2);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ExameShedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExameShedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExameShedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExameShedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExameShedule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackToHome;
    private javax.swing.JButton btnGenarate;
    private javax.swing.JButton btnSaveTable;
    private javax.swing.JButton btnSubRemove;
    private javax.swing.JCheckBox cbAllowSat;
    private javax.swing.JCheckBox cbAllowSun;
    private javax.swing.JCheckBox cbOLSpace;
    private javax.swing.JCheckBox cbSkip;
    private javax.swing.JComboBox<String> cmbDep;
    private javax.swing.JComboBox<String> cmbSelectCrs;
    private org.jdesktop.swingx.JXDatePicker dtpEnd;
    private org.jdesktop.swingx.JXDatePicker dtpStart;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jtbldate;
    private javax.swing.JTabbedPane jtpgen;
    private javax.swing.JLabel lblConfiWarning;
    private javax.swing.JList<String> lstSubject;
    private javax.swing.JPanel pnlConfig;
    private javax.swing.JPanel pnlGenarate;
    private javax.swing.JPanel pnlSelectSub;
    private javax.swing.JTable tblHall;
    private javax.swing.JTable tblSubject;
    private javax.swing.JTable tblTimeTable;
    private javax.swing.JTextField txtNumOfINV;
    private javax.swing.JTextField txtTableName;
    // End of variables declaration//GEN-END:variables
}
