/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
//package gui1;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.time.format.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class main extends javax.swing.JFrame {
    String plateNo;
    String location;
    String entryDateTo,entryDateFrom;
    String entryTimeTo,entryTimeFrom;
    String locale;
    public main(String locale1) {
    	locale=locale1;
        initComponents();
    }
    
    /**
     *
     * @param plateNo
     * @param location
     * @param entryDateTo
     * @param entryDateFrom
     * @param entryTimeTo
     * @param entryTimeFrom
     * @return
     * @throws ClassNotFoundException
     */
    public ArrayList<user> userList(String plateNo,String location,String entryDateFrom,String entryDateTo,String entryTimeFrom,String entryTimeTo) throws ClassNotFoundException
    {
        
        ArrayList<user> usersList = new ArrayList();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/view_info","root","");
            
            //dynamic query generation for filtering
            String dynamicQuery="";
            if(plateNo.length()>0 || location.length()>0 || entryDateFrom!=null || entryDateTo!=null || entryTimeFrom!=null || entryTimeTo!=null){
                ArrayList<String> queryitems = new ArrayList();
                System.out.println(entryTimeFrom+"-"+entryTimeTo);
                
                if(plateNo.length()>0){
                    queryitems.add("plate_no like '"+plateNo+"%'");
                }
                
                if(location.length()>0){
                    queryitems.add("location like'"+location+"%'");
                }
                
                if(entryDateTo!=null && entryDateFrom!=null){
                    queryitems.add( "entry_date between '"+entryDateFrom+"' and '"+entryDateTo+"' ");
                    
                }else if(entryDateFrom!=null)
                {
                    queryitems.add("entry_date>='"+entryDateFrom+"'");
                    
                }else if(entryDateTo!=null)
                {
                    queryitems.add("entry_date<='"+entryDateTo+"'");
                    
                }else {}
                
                if(!entryTimeFrom.equals("") && !entryTimeTo.equals(""))
                {
                    queryitems.add( "entry_time between '"+entryTimeFrom+"' and '"+entryTimeTo+"' ");
                }else if(!entryTimeFrom.equals(""))
                {                    
                    queryitems.add("entry_time>='"+entryTimeFrom+"'");
                }else if(!entryTimeTo.equals(""))
                {
                    queryitems.add("entry_time<='"+entryTimeTo+"'");                    
                }else {}
                
                dynamicQuery = String.join(" and ", queryitems);
                String query;
                if(dynamicQuery.length()>0) {
                	 query="Select * from detail_information where "+dynamicQuery+" ORDER BY entry_date DESC,  entry_time DESC";
                }else {
                	 query="Select * from detail_information ORDER BY entry_date DESC,  entry_time DESC";
                }
               
                System.out.println(query);
                Statement st=con.createStatement();
                ResultSet rs =st.executeQuery(query);
                user user;
                while(rs.next())
                {
                    user= new user(rs.getString("plate_no"),rs.getString("location"),rs.getDate("entry_date"),rs.getTime("entry_time"),rs.getString("vehicle_brand"));
                    usersList.add(user);
                }
            }
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        return usersList;
    }
    public void show_user(String plateNo,String location,String entryDateFrom,String entryDateTo,String entryTimeFrom,String entryTimeTo) throws ClassNotFoundException
    {
        ArrayList<user> list = userList(plateNo,location,entryDateFrom,entryDateTo,entryTimeFrom,entryTimeTo);
        //System.out.println(list);
        DefaultTableModel model = (DefaultTableModel)show_table.getModel();
        Object[] row = new Object[5];
        model.setRowCount(0);
        for (user list1 : list) {
            System.out.println("loop");
            row[0] = list1.getpn();
            row[1] = list1.getloc();
            row[2] = list1.getd();
            row[3] = list1.getT();
            row[4] = list1.getbname();
            model.addRow(row);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        show_table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PlateNo = new javax.swing.JTextField();
        Location = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        EntryDateFrom = new com.toedter.calendar.JDateChooser();
        EntryTimeFrom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Filter = new javax.swing.JButton();
        EntryDateTo = new com.toedter.calendar.JDateChooser();
        EntryTimeTo = new javax.swing.JTextField();
        Filter1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        show_table.setBackground(new java.awt.Color(51, 51, 255));
        show_table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        show_table.setForeground(new java.awt.Color(255, 255, 255));
        show_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "         Plate No", "         Location ", "        Entry Date", "        Entry time", 
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        show_table.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setViewportView(show_table);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("    Plate No:");

        PlateNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PlateNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlateNoActionPerformed(evt);
            }
        });

        Location.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Location.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocationActionPerformed(evt);
            }
        });

        jLabel2.setText("   Location:");

        jLabel3.setText("   Entry Date:");

        EntryTimeFrom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("   Entry Time:");

        Filter.setText("Filter");
        Filter.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterActionPerformed(evt);
            }
        });

        EntryTimeTo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Filter1.setText("View Detail");
        Filter1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Adobe Hebrew", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 0));
        jLabel5.setText("Enter the vehicle information below to filter");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(PlateNo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(Location, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(EntryDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(485, 485, 485)
                                .addComponent(EntryDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EntryTimeTo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EntryTimeFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Filter1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(PlateNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(Location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel3))
                    .addComponent(EntryDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EntryTimeFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(Filter))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(EntryDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(EntryTimeTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Filter1)
                .addGap(19, 19, 19))
        );

        jMenu1.setText("Main Menu");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void PlateNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlateNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PlateNoActionPerformed
    
    private void FilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilterActionPerformed
        plateNo = PlateNo.getText();
        location = Location.getText();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        entryDateFrom = EntryDateFrom.getDate()!=null ? formatter.format(EntryDateFrom.getDate()) : null;
        entryDateTo = EntryDateTo.getDate()!=null ? formatter.format(EntryDateTo.getDate()) : null;
        Format timeFormat = new SimpleDateFormat("HH:mm:ss");
        // SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm:ss");
        //JFormattedTextField input = new JFormattedTextField(timeFormat);
        //entryTimeFrom = EntryTimeFrom.getText()!=null ? timeFormat.format(EntryTimeFrom.getText()) : null;
        // entryTimeTo = EntryTimeTo.getText()!=null ? timeFormat.format(EntryTimeTo.getText()) : null;
        entryTimeFrom = EntryTimeFrom.getText();
        entryTimeTo = EntryTimeTo.getText();
        try {
            show_user(plateNo,location,entryDateFrom,entryDateTo,entryTimeFrom,entryTimeTo);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_FilterActionPerformed

    private void LocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LocationActionPerformed
    
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
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new main("").setVisible(true);
               
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser EntryDateFrom;
    private com.toedter.calendar.JDateChooser EntryDateTo;
    private javax.swing.JTextField EntryTimeFrom;
    private javax.swing.JTextField EntryTimeTo;
    private javax.swing.JButton Filter;
    private javax.swing.JButton Filter1;
    private javax.swing.JTextField Location;
    private javax.swing.JTextField PlateNo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable show_table;
    // End of variables declaration//GEN-END:variables
    
    // private void show_user(String plateNo, String location, Date entryDateTo, Date entryDateFrom, Time entryTimeTo, Time entryTimeFrom) {
    //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    // }
    
    // private ArrayList<user> userList(String plateNo, String location, Date entryDateTo, Date entryDateFrom, Time entryTimeTo, Time entryTimeFrom) {
    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    // }
}
