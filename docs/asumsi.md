# Asumsi dan Keputusan Desain

Isi bagian ini sendiri. Satu entri per keputusan. Dinilai dari kejelasan alasan dan konsistensi dengan kode dan tes Anda,
bukan dari jawaban "benar".

Format tiap entri:

```
## A-NN: judul singkat
- Pertanyaan: ...
- Keputusan: ...
- Alasan: ...
- Dampak ke kode: ... (kelas/metode)
- Tes yang membuktikan: ... (nama tes di folder mahasiswa/)
```

## Aturan ambigu dari docs/aturan-bisnis.md

Salin setiap pertanyaan ambigu (nomor 1 sampai 7) ke sini dan jawab. Contoh:

## A-01: batas waktu bentrok
- Pertanyaan: 09:00-11:00 dan 11:00-13:00 pada aset yang sama bentrok atau tidak?
- Keputusan: Tidak bentrok. Kalau satu peminjaman selesai tepat pada jam peminjaman lain mulai, keduanya boleh.
- Alasan: Waktu selesai saya anggap bukan bagian dari pemakaian, jadi rentangnya dihitung dari mulai sampai sebelum selesai. Dengan begitu peminjam berikutnya bisa langsung memakai aset tanpa menunggu jeda, seperti jadwal kuliah yang berurutan.
- Dampak ke kode: Method bentrok di JadwalPeminjaman memakai perbandingan "sebelum" (isBefore), bukan "sebelum atau sama dengan", sehingga jam yang sama tidak dihitung bentrok.
- Tes yang membuktikan:  selesaiSamaDenganMulai di mahasiswa/BatasBentrokTest.java. Tes lain di file itu (tumpangTindihBentrok, terkandungBentrok, terpisahJauhTidakBentrok, asetBerbedaWaktuSamaTidakBentrok) menjaga aturan lainnya tetap benar.

## Asumsi lain

- Rumus denda progresif = flat + (flat × hari / 10)

  Alasan: setiap 10 hari terlambat menambah satu kali denda flat, supaya keterlambatan yang lama dikenai denda lebih berat.