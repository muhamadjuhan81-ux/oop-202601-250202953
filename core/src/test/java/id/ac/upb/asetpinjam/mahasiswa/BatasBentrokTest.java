package id.ac.upb.asetpinjam.mahasiswa;

import static org.assertj.core.api.Assertions.assertThat;

import id.ac.upb.asetpinjam.jadwal.JadwalPeminjaman;
import id.ac.upb.asetpinjam.model.Peminjam;
import id.ac.upb.asetpinjam.model.Peminjaman;
import id.ac.upb.asetpinjam.model.Ruang;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

/**
 * FOLDER INI MILIK ANDA. Tulis tes Anda sendiri di sini (minggu 7 dan seterusnya).
 *
 * <p>Tes dosen sengaja tidak menentukan: apakah peminjaman 09:00-11:00 dan 11:00-13:00 pada aset yang sama
 * bentrok? Putuskan, catat alasannya di docs/asumsi.md, lalu tuliskan tesnya di sini.
 * Hapus @Disabled setelah Anda mengisinya.
 *
 * <p>Syarat minggu 10: folder ini memuat minimal 8 tes (tidak termasuk contoh bawaan ini).
 */
class BatasBentrokTest {

    private static LocalDateTime jam(int jam, int menit) {
        return LocalDateTime.of(2026, 1, 5, jam, menit);
    }

     /** Membuat peminjaman pada ruang dengan kode tertentu. */
    private Peminjaman buat(String id, String kodeAset, LocalDateTime mulai, LocalDateTime selesai) {
        Peminjam peminjam = new Peminjam("M001", "Budi");
        Ruang ruang = new Ruang(kodeAset, "Ruang " + kodeAset, 30);
        return new Peminjaman(id, peminjam, ruang, mulai, selesai);
    }

    @Test
    void selesaiSamaDenganMulai() {
        JadwalPeminjaman jadwal = new JadwalPeminjaman(1);
        jadwal.tambah(buat("P1", "RG-01", jam(9, 0), jam(11, 0)));

        Peminjaman baru = buat("P2", "RG-01", jam(11, 0), jam(13, 0));

        assertThat(jadwal.bentrok(baru)).isFalse();
    }

     @Test
    void tumpangTindihBentrok() {
        JadwalPeminjaman jadwal = new JadwalPeminjaman(1);
        jadwal.tambah(buat("P1", "RG-01", jam(9, 0), jam(11, 0)));

        Peminjaman baru = buat("P2", "RG-01", jam(10, 0), jam(12, 0));

        assertThat(jadwal.bentrok(baru)).isTrue();
    }

     @Test
    void terkandungBentrok() {
        JadwalPeminjaman jadwal = new JadwalPeminjaman(1);
        jadwal.tambah(buat("P1", "RG-01", jam(9, 0), jam(11, 0)));

        Peminjaman baru = buat("P2", "RG-01", jam(9, 30), jam(10, 30));

        assertThat(jadwal.bentrok(baru)).isTrue();
    }

     @Test
    void terpisahJauhTidakBentrok() {
        JadwalPeminjaman jadwal = new JadwalPeminjaman(1);
        jadwal.tambah(buat("P1", "RG-01", jam(9, 0), jam(11, 0)));

        Peminjaman baru = buat("P2", "RG-01", jam(14, 0), jam(16, 0));

        assertThat(jadwal.bentrok(baru)).isFalse();
    }

     @Test
    void asetBerbedaWaktuSamaTidakBentrok() {
        JadwalPeminjaman jadwal = new JadwalPeminjaman(1);
        jadwal.tambah(buat("P1", "RG-01", jam(9, 0), jam(11, 0)));

        Peminjaman baru = buat("P2", "RG-02", jam(9, 0), jam(11, 0));

        assertThat(jadwal.bentrok(baru)).isFalse();
    }

}
