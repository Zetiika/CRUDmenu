
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;


public class LihatDataMahasiswa extends JFrame{
    DefaultTableModel model = new DefaultTableModel();
    
//    String[][] data = new String [500][3];//buat variabel data kebentuk array;
//    String[] kolom = {"nim","nama","alamat"};//,"gender"};//disesuaikan dengan kolom yang diinginkan
    JTable tabel ;
    JScrollPane scrollPane;
    JButton kembali;
    Statement statement;
    ResultSet resultSet;
    JLabel judul;
    
    public void Lihatdata(){
        setTitle("Data Mahasiswa");
        judul = new JLabel("SELURUH DATA MAHASISWA");
        model.addColumn("Nim");
        model.addColumn("Nama");
        model.addColumn("Alamat");
        tabel = new JTable(model);
        scrollPane = new JScrollPane(tabel);
        kembali = new JButton("Back");
        pack();
        setLayout(null);
       
        add(kembali);
        add(judul);
        add(scrollPane);
        
        judul.setHorizontalAlignment(SwingConstants.CENTER);
        setSize(500,500);
        
        judul.setBounds(130,20,200,25);
        kembali.setBounds(180,50,100,20);
        scrollPane.setBounds(20,100,450,350);
        try{
            int no=1;
            KoneksiDB koneksi = new KoneksiDB();
            statement = koneksi.getKoneksi().createStatement();
            
            String sql = "SELECT * FROM data_mhs";//perintah mengambil data
            resultSet  = statement.executeQuery(sql);//data disimpan di reultSet
            
//            int p = 0 ;
            while (resultSet.next()){
                //disesuaikan dengan kolom sesuai dengan di DB
                model.addRow(new Object[]{no++,resultSet.getString(1),resultSet.getString(1),resultSet.getString(2)});
//                data[p][0] = resultSet.getString("nim");
//                data[p][1] = resultSet.getString("nama");
//                data[p][2] = resultSet.getString("alamat");
                //data[p][3] = resultSet.getString("jk");
//                p++;
            }
            tabel.setModel(model);
            statement.close();
            koneksi.getKoneksi().close();
        } catch (SQLException sqle){
            JOptionPane.showMessageDialog(rootPane, "Data Gagal ditampilkan"+sqle);
        } catch (ClassNotFoundException classe){
            JOptionPane.showMessageDialog(rootPane, "Class tidak ditemukan"+classe);
        }catch (Exception e) {
        }
        
//        tabel = new JTable(model);
//        scrollPane = new JScrollPane(tabel);
//        kembali = new JButton("Back");
//        add(kembali);
//        kembali.setBounds(150, 20, 90, 20);
//        setLayout(new FlowLayout());
//        add(scrollPane); add(kembali);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
//        pack();
        setLocationRelativeTo(null);
        
        kembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Menu m = new Menu();
                m.PilihMenu();            
            }
        });
    }
}
