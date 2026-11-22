import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LatihanGui extends JFrame {
    private BorderLayout layoutUtama;
    private BorderLayout layoutAtas;
    private GridLayout layoutForm;

    private JPanel panelAtas;
    private JPanel panelBawah;
    private JScrollPane scrollPane;

    private JLabel labelJudul;
    private JLabel labelPinjam;
    private JLabel labelKategori;
    private JLabel labelStatus;

    private JTextField teksPinjam;
    private JRadioButton pilihanFiksi;
    private JRadioButton pilihanKomik;
    private ButtonGroup groupKategori;
    private JTextArea areaHasil;

    private JButton tombolSimpan;
    private JButton tombolHapus;

    public LatihanGui() {
        setTitle("Lucifer Library");
        setSize(420, 380);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        layoutUtama = new BorderLayout(10, 10);
        layoutAtas = new BorderLayout(5, 5);
        layoutForm = new GridLayout(4, 2, 10, 10);

        panelAtas = new JPanel();
        panelBawah = new JPanel();

        labelJudul = new JLabel("Lucifer Library", SwingConstants.CENTER);
        labelPinjam = new JLabel("Nama Peminjam:");
        labelKategori = new JLabel("Kategori Buku:");
        labelStatus = new JLabel("Status: Siap");

        teksPinjam = new JTextField();
        pilihanFiksi = new JRadioButton("Fiksi");
        pilihanKomik = new JRadioButton("Komik");
        groupKategori = new ButtonGroup();
        areaHasil = new JTextArea();
        scrollPane = new JScrollPane(areaHasil);

        tombolSimpan = new JButton("Simpan");
        tombolHapus = new JButton("Hapus");

        groupKategori.add(pilihanFiksi);
        groupKategori.add(pilihanKomik);

        panelAtas.setLayout(layoutAtas);
        panelAtas.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        labelJudul.setFont(new Font("Arial", Font.BOLD, 16));
        panelAtas.add(labelJudul, BorderLayout.NORTH);

        panelBawah.setLayout(layoutForm);
        panelBawah.add(labelPinjam);
        panelBawah.add(teksPinjam);
        panelBawah.add(labelKategori);
        panelBawah.add(pilihanFiksi);
        panelBawah.add(new JLabel());
        panelBawah.add(pilihanKomik);
        panelBawah.add(tombolSimpan);
        panelBawah.add(tombolHapus);
        panelAtas.add(panelBawah, BorderLayout.CENTER);

        areaHasil.setEditable(false);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Pinjaman"));

        setLayout(layoutUtama);
        add(panelAtas, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(labelStatus, BorderLayout.SOUTH);

        tombolSimpan.addActionListener(e -> {
            String namaPeminjam = teksPinjam.getText().trim();
            if (namaPeminjam.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama peminjam tidak boleh kosong.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String kategoriBuku = pilihanFiksi.isSelected() ? "Fiksi" : pilihanKomik.isSelected() ? "Komik" : "";
            if (kategoriBuku.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pilih kategori buku.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String hasil = "Nama Peminjam: " + namaPeminjam + "\nKategori Buku: " + kategoriBuku + "\n\n";
            areaHasil.append(hasil);
            teksPinjam.setText("");
            groupKategori.clearSelection();
            labelStatus.setText("Data berhasil disimpan.");
        });

        tombolHapus.addActionListener(e -> {
            teksPinjam.setText("");
            areaHasil.setText("");
            groupKategori.clearSelection();
            labelStatus.setText("Data berhasil dihapus.");
        });

        teksPinjam.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                labelStatus.setText("Mengetik (" + teksPinjam.getText().length() + " karakter)");
            }
        });
    }

    public static void main(String[] args) {
        LatihanGui gui = new LatihanGui();
        gui.setVisible(true);
    }
}