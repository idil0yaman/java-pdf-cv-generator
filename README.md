# Java PDF Özgeçmiş (CV) Oluşturucu

Bu proje, Nesne Yönelimli Programlama (OOP) prensipleri ve Apache PDFBox kütüphanesi kullanılarak Java programlama dilinde geliştirilmiş, dinamik olarak PDF formatında özgeçmiş üreten bir uygulamadır.

## Proje Özellikleri
- Modüler OOP Mimarisi: İş deneyimi veri modeli (`Experience`) ve PDF üretim servisi (`ResumeGenerator`), `Main` sınıfından bağımsız olarak tasarlanmıştır.
- Dinamik İçerik ve Görsel Entegrasyonu: `PDImageXObject` kullanılarak yerel fotoğraf dizininden okunup PDF'e işlenmekte ve otomatik satır kaydırma (word-wrap) algoritmalarıyla uzun metinler yönetilmektedir.
- Sistem Fontu ile Türkçe Karakter Desteği: Standart PDF fontlarının Türkçe karakter sorunlarını aşmak için `PDType0Font` kullanılarak işletim sistemindeki TrueType (`Arial.ttf`) fontları doğrudan projeye entegre edilmiştir.

## Kullanılan Bileşenler, Sınıflar ve Nesneler

### 1. Domain ve Servis Sınıfları
* `Experience`: Özgeçmişteki iş deneyimlerini (`company`, `position`, `period`, `description`) kapsülleyen veri modelidir.
* `ResumeGenerator`: PDF oluşturma, metin hizalama ve satır bölme algoritmalarının (`drawWrappedText`) yer aldığı servis sınıfıdır.
* `Main`: Uygulamanın başlangıç noktasıdır, verileri örneklendirir ve oluşturucu servisi tetikler.

### 2. Apache PDFBox Nesneleri ve Kullanım Amaçları
* `PDDocument` & `PDPage`: PDF belgesini ve standart A4 boyutlarındaki sayfaları bellekte oluşturmak için kullanılmıştır.
* `PDPageContentStream`: X ve Y koordinat düzleminde sayfa üzerine metin yazdırmak, çizgi çekmek ve görsel yerleştirmek için kullanılmıştır.
* `PDImageXObject`: Projedeki profil fotoğrafını okuyup PDF içerisine çizilebilir bir nesneye dönüştürmek için kullanılmıştır.
* `PDType0Font`: Sisteme kurulu yerel fontları yükleyerek Türkçe karakter desteğini sağlamak amacıyla kullanılmıştır.

---

# Java PDF Resume (CV) Generator

This project is a dynamic PDF resume generator developed in Java using Object-Oriented Programming (OOP) principles and the Apache PDFBox library.

## Project Features
- Modular OOP Architecture: The work experience data model (`Experience`) and the PDF generation service (`ResumeGenerator`) are designed independently from the `Main` class.
- Dynamic Content and Image Integration: Local photos are read and processed into the PDF using `PDImageXObject`, and long texts are managed with automatic word-wrap algorithms.
- Turkish Character Support with System Fonts: To overcome the Turkish character issues of standard PDF fonts, TrueType (`Arial.ttf`) fonts from the operating system are directly integrated into the project using `PDType0Font`.

## Components, Classes, and Objects Used

### 1. Domain and Service Classes
* `Experience`: The data model that encapsulates the work experiences (`company`, `position`, `period`, `description`) in the resume.
* `ResumeGenerator`: The service class that contains PDF generation, text alignment, and word-wrapping algorithms (`drawWrappedText`).
* `Main`: The entry point of the application, which instantiates the data and triggers the generator service.

### 2. Apache PDFBox Objects and Their Purposes
* `PDDocument` & `PDPage`: Used to create the PDF document and standard A4 size pages in memory.
* `PDPageContentStream`: Used to draw text, draw lines, and place images on the page in the X and Y coordinate plane.
* `PDImageXObject`: Used to read the profile photo in the project and convert it into a drawable object within the PDF.
* `PDType0Font`: Used to provide Turkish character support by loading local fonts installed on the system.
