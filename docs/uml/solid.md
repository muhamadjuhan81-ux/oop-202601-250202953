# Prinsip SOLID pada AsetPinjam

## 1. Open/Closed Principle (OCP): AturanDenda, DendaFlat, DendaProgresif
Interface AturanDenda punya dua implementasi, yaitu DendaFlat dan
DendaProgresif. Kalau nanti ada aturan denda baru (misalnya denda
akhir pekan), cukup menambah kelas baru yang mengimplementasikan
AturanDenda. Kelas lain tidak perlu diubah, jadi kode terbuka untuk
ditambah tetapi tertutup untuk diubah.

## 2. Liskov Substitution Principle (LSP): Aset, AlatLab, Laptop, Ruang
AlatLab, Laptop, dan Ruang mewarisi Aset dan bisa dipakai di mana
pun tipe Aset diminta. KatalogAset menyimpan semuanya dalam satu
List<Aset> dan DendaFlat memanggil aset.hitungDenda() tanpa peduli
jenisnya. Tiap subclass menjaga aturan yang sama hari negatif ditolak.
