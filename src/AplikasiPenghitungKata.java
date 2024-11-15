
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class AplikasiPenghitungKata extends javax.swing.JFrame {
    // Fungsi untuk menyimpan teks dan hasil perhitungan ke dalam file
// Fungsi untuk menyimpan teks dan hasil perhitungan ke file
private void simpan() {
    // Ambil teks dari JTextArea
    String teks = inputText.getText().trim();

    // Ambil hasil perhitungan dari label
    String jumlahKata = labelKata.getText();
    String jumlahKarakter = labelKarakter.getText();
    String jumlahKalimat = labelKalimat.getText();
    String jumlahParagraf = labelParagraf.getText();

    // Gunakan JFileChooser untuk memilih lokasi dan nama file untuk menyimpan
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Pilih lokasi untuk menyimpan file");

    // Menyaring hanya file teks (opsional)
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("File Teks", "txt"));

    int result = fileChooser.showSaveDialog(this);
    
    if (result == JFileChooser.APPROVE_OPTION) {
        // Ambil file yang dipilih
        File file = fileChooser.getSelectedFile();
        
        // Pastikan file memiliki ekstensi .txt
        if (!file.getName().endsWith(".txt")) {
            file = new File(file.getAbsolutePath() + ".txt");
        }

        // Simpan teks dan hasil perhitungan ke file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Menulis teks dari JTextArea ke file
            writer.write("Teks yang dimasukkan:\n");
            writer.write(teks);
            writer.newLine();
            writer.newLine();

            // Menulis hasil perhitungan ke file
            writer.write("Hasil Perhitungan:\n");
            writer.write("Jumlah Kata: " + jumlahKata + "\n");
            writer.write("Jumlah Karakter: " + jumlahKarakter + "\n");
            writer.write("Jumlah Kalimat: " + jumlahKalimat + "\n");
            writer.write("Jumlah Paragraf: " + jumlahParagraf + "\n");

            // Menampilkan pesan sukses
            JOptionPane.showMessageDialog(this, "File berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            // Menangani error saat menyimpan file
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    
    // Fungsi untuk mencari kata dalam teks dan menghitung kemunculannya
private void cariKata() {
    // Ambil kata yang dicari dari JTextField
    String kataDicari = cariKata.getText().trim();

    // Ambil teks dari JTextArea
    String teks = inputText.getText();

    if (kataDicari.isEmpty()) {
        // Jika kata yang dicari kosong, beri peringatan
        JOptionPane.showMessageDialog(this, "Masukkan kata yang ingin dicari!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Hitung jumlah kemunculan kata yang dicari
    int jumlahKemunculan = hitungKemunculan(teks, kataDicari);

    // Tampilkan jumlah kemunculan pada label atau dialog
    JOptionPane.showMessageDialog(this, "Kata '" + kataDicari + "' ditemukan " + jumlahKemunculan + " kali.", 
                                  "Hasil Pencarian", JOptionPane.INFORMATION_MESSAGE);
}

// Fungsi untuk menghitung jumlah kemunculan kata dalam teks
private int hitungKemunculan(String teks, String kata) {
    // Pisahkan teks berdasarkan spasi untuk mendapatkan kata-kata
    String[] kataArray = teks.split("\\s+");

    // Variabel untuk menyimpan jumlah kemunculan kata
    int jumlahKemunculan = 0;

    // Loop untuk memeriksa setiap kata dalam array
    for (String kataTeks : kataArray) {
        // Jika kata yang dicari ditemukan, increment jumlahKemunculan
        if (kataTeks.equalsIgnoreCase(kata)) {
            jumlahKemunculan++;
        }
    }

    return jumlahKemunculan;
}

    
    // Fungsi untuk menghitung jumlah kata, karakter, kalimat, dan paragraf
private void hitung() {
    // Ambil teks dari JTextArea
    String teks = inputText.getText().trim();

    // Hitung jumlah kata
    int jumlahKata = hitungKata(teks);

    // Hitung jumlah karakter (termasuk spasi)
    int jumlahKarakter = teks.length();

    // Hitung jumlah kalimat
    int jumlahKalimat = hitungKalimat(teks);

    // Hitung jumlah paragraf
    int jumlahParagraf = hitungParagraf(teks);

    // Set hasil ke label
    labelKata.setText(String.valueOf(jumlahKata));
    labelKarakter.setText(String.valueOf(jumlahKarakter));
    labelKalimat.setText(String.valueOf(jumlahKalimat));
    labelParagraf.setText(String.valueOf(jumlahParagraf));
}

// Fungsi untuk menghitung jumlah kata
private int hitungKata(String teks) {
    if (teks.isEmpty()) return 0;

    // Pisahkan kata berdasarkan spasi dan karakter pemisah lainnya
    String[] kataArray = teks.split("\\s+");
    return kataArray.length;
}

// Fungsi untuk menghitung jumlah kalimat
private int hitungKalimat(String teks) {
    if (teks.isEmpty()) return 0;

    // Hitung kalimat berdasarkan tanda titik (.) atau tanda lainnya yang menandakan akhir kalimat
    String[] kalimatArray = teks.split("[.!?]");
    return kalimatArray.length;
}

// Fungsi untuk menghitung jumlah paragraf
private int hitungParagraf(String teks) {
    if (teks.isEmpty()) return 0;

    // Hitung paragraf berdasarkan pemisah baris baru (line breaks)
    String[] paragrafArray = teks.split("\n");
    return paragrafArray.length;
}


    /**
     * Creates new form AplikasiPenghitungKata
     */
    public AplikasiPenghitungKata() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        cariKata = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputText = new javax.swing.JTextArea();
        btnCari = new javax.swing.JButton();
        btnHitung = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelKata = new javax.swing.JLabel();
        labelKarakter = new javax.swing.JLabel();
        labelKalimat = new javax.swing.JLabel();
        labelParagraf = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 3, 7);
        jPanel1.add(cariKata, gridBagConstraints);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 200));

        inputText.setColumns(20);
        inputText.setRows(5);
        jScrollPane1.setViewportView(inputText);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 3, 7);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        btnCari.setText("Cari Kata");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 3, 7);
        jPanel1.add(btnCari, gridBagConstraints);

        btnHitung.setText("Hitung!");
        btnHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitungActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 3, 7);
        jPanel1.add(btnHitung, gridBagConstraints);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 3, 7);
        jPanel1.add(btnSimpan, gridBagConstraints);

        jLabel1.setText("Jumlah Kata:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 3, 7);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Jumlah Karakter:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 3, 7);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Jumlah Kalimat:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 3, 7);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Jumlah Paragraf");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 3, 7);
        jPanel1.add(jLabel4, gridBagConstraints);

        labelKata.setText(".");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 3, 7);
        jPanel1.add(labelKata, gridBagConstraints);

        labelKarakter.setText(".");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 3, 7);
        jPanel1.add(labelKarakter, gridBagConstraints);

        labelKalimat.setText(".");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 3, 7);
        jPanel1.add(labelKalimat, gridBagConstraints);

        labelParagraf.setText(".");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 3, 7);
        jPanel1.add(labelParagraf, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitungActionPerformed
    hitung();        // TODO add your handling code here:
    }//GEN-LAST:event_btnHitungActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
    cariKata();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
    simpan();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSimpanActionPerformed

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
            java.util.logging.Logger.getLogger(AplikasiPenghitungKata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AplikasiPenghitungKata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AplikasiPenghitungKata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AplikasiPenghitungKata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AplikasiPenghitungKata().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHitung;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JTextField cariKata;
    private javax.swing.JTextArea inputText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelKalimat;
    private javax.swing.JLabel labelKarakter;
    private javax.swing.JLabel labelKata;
    private javax.swing.JLabel labelParagraf;
    // End of variables declaration//GEN-END:variables
}
