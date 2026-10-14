package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("CV Olusturucu baslatiliyor...");

        // 1. İş Deneyimleri Nesnelerinin Oluşturulması (3 Adet)
        List<Experience> experiences = new ArrayList<>();

        experiences.add(new Experience(
                "Huawei Student Developers (HSD) Kırklareli",
                "Core Team Üyesi & Geliştirici",
                "2025 - Günümüz",
                "Kampüs çapında yazılım etkinlikleri, teknik atölyeler ve geliştirici buluşmalarının organize edilmesi. Topluluk projelerinde koordinasyon, görsel içerik tasarımı ve teknik sunum süreçlerinin yürütülmesi."
        ));

        experiences.add(new Experience(
                "LogiCore Bilişim Sistemleri",
                "Aday Yazılım Mühendisi (Part-Time)",
                "Şubat 2026 - Ağustos 2026",
                "C ve Java dilleri kullanılarak lojistik rota optimizasyonu için çizge (Graph) algoritmaları ve karma tablo (Hash Table) tabanlı veri yapılarının geliştirilmesi. Yüksek öncelikli istekler için Min-Heap tabanlı zamanlayıcı (Scheduler) modülünün kodlanması."
        ));

        experiences.add(new Experience(
                "NovaTech Yazılım Çözümleri",
                "Yazılım Geliştirme Stajyeri",
                "Haziran 2025 - Eylül 2025",
                "Nesne yönelimli programlama (OOP) ilkelerine uygun olarak kurumsal arka uç (Backend) servislerinin geliştirilmesi, birim testlerinin yazılması ve dokümantasyon süreçlerine destek verilmesi."
        ));

        // 2. Eğitim Bilgileri Listesi
        List<String> educations = Arrays.asList(
                "Kırklareli Üniversitesi - Yazılım Mühendisliği (2025 - 2029)",
                "İzmir Demokrasi Üniversitesi - İngilizce Mimarlık (2023 - 2025)"
        );

        // 3. Kişisel Bilgiler, Yetenekler ve Yabancı Dil
        String outputPath = "cv_output.pdf";
        String photoPath = "photo.jpeg";
        String fullName = "İdil Yaman";
        String title = "Yazılım Mühendisliği Öğrencisi";
        String email = "idilyamannn@gmail.com";
        String phone = "545 789 45 67";
        String location = "İstanbul, Türkiye";
        String summary = "Analitik tasarım geçmişini yazılım mühendisliği disipliniyle birleştiren; algoritma tasarımı, veri yapıları ve nesne yönelimli programlama (OOP) konularına odaklanan yazılım mühendisliği öğrencisi. Teknoloji topluluklarında aktif rol alarak ekip çalışması ve proje geliştirme süreçlerini yürütmektedir.";
        String skills = "Java (OOP)  -  C Programlama  -  Veri Yapıları ve Algoritmalar  -  Linux / Ubuntu (Container)  -  Git & GitHub  -  SQL  -  AutoCAD & Görsel Tasarım";
        String languages = "İngilizce (B2 - Upper Intermediate)";

        // 4. ResumeGenerator Nesnesinin Oluşturulması ve PDF Üretimi
        ResumeGenerator generator = new ResumeGenerator();
        generator.generateResume(
                outputPath, photoPath, fullName, title, email, phone, location,
                summary, experiences, educations, skills, languages
        );
    }
}
