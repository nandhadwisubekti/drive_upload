# drive_upload

Aplikasi ini memungkinkan Anda untuk mengupload gambar ke Google Drive secara otomatis menggunakan Java 8 dan Google Drive API. Aplikasi ini menggunakan **Service Account** untuk otentikasi, sehingga tidak memerlukan pop-up untuk login.

## Fitur

- Mengupload gambar (image file) ke Google Drive
- Menggunakan Service Account untuk otentikasi tanpa interaksi pengguna (tanpa pop-up)
- Menggunakan Google Drive API v3 untuk mengelola file di Google Drive

## Persyaratan

Sebelum memulai, pastikan Anda sudah melakukan beberapa langkah konfigurasi:

### 1. Persiapan Project di Google Cloud Console
- Masuk ke [Google Cloud Console](https://console.cloud.google.com/).
- Buat project baru atau pilih project yang sudah ada.
- Aktifkan **Google Drive API** di project Anda.
- Buat **Service Account** di bagian "IAM & Admin" -> "Service Accounts".
- Unduh **kunci JSON** untuk Service Account.

### 2. Menambahkan Dependensi ke dalam Project
Pastikan Anda menggunakan **Maven** untuk mengelola dependensi. 

## Cara Menggunakan

### 1. Mengunduh Kredensial
- Unduh file kredensial Service Account JSON yang sudah Anda buat sebelumnya dari Google Cloud Console. Pastikan Anda menyimpan file ini di folder yang mudah diakses.

### 2. Mengkonfigurasi File Java
- Masukkan path ke file JSON kredensial Service Account Anda dalam kode.
- Sesuaikan path gambar yang ingin diupload 

### 3. Membangun dan Menjalankan Aplikasi
- Untuk menjalankan aplikasi, cukup lakukan kompilasi dan eksekusi menggunakan Maven atau IDE Java Anda.

Contoh perintah Maven:
mvn clean install
mvn exec:java

## Troubleshooting

Masalah Otentikasi: Pastikan file JSON kredensial sudah benar dan akses yang diberikan kepada Service Account sudah tepat.
File Tidak Terupload: Periksa izin akses ke folder di Google Drive dan pastikan file yang diupload dapat dikenali oleh API.

## Lisensi
Aplikasi ini dilisensikan di bawah MIT License.
