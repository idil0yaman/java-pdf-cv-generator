package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeGenerator {

    private PDFont fontRegular;
    private PDFont fontBold;
    private boolean unicodeSupported = false;

    public void generateResume(String outputPath, String photoPath, String fullName, String title,
                               String email, String phone, String location, String summary,
                               List<Experience> experiences, List<String> educations,
                               String skills, String languages) {

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            initializeFonts(document);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                float yPosition = 780;
                float margin = 45;

                // 1. FOTOĞRAF EKLEME
                File imgFile = resolveFile(photoPath);
                if (imgFile != null && imgFile.exists()) {
                    try {
                        PDImageXObject pdImage = PDImageXObject.createFromFileByContent(imgFile, document);
                        contentStream.drawImage(pdImage, margin, yPosition - 95, 85, 110);
                    } catch (Exception imgEx) {
                        System.err.println("Fotograf yuklenemedi: " + imgEx.getMessage());
                    }
                }

                // 2. KİŞİSEL BİLGİLER (Fotoğrafın Sağ Tarafı - Tamamı Siyah)
                float infoX = margin + 105;
                drawText(contentStream, fullName, infoX, yPosition, fontBold, 20);
                drawText(contentStream, title, infoX, yPosition - 20, fontRegular, 12);
                drawText(contentStream, "E-posta: " + email, infoX, yPosition - 45, fontRegular, 10);
                drawText(contentStream, "Telefon: " + phone, infoX, yPosition - 60, fontRegular, 10);
                drawText(contentStream, "Konum: " + location, infoX, yPosition - 75, fontRegular, 10);

                yPosition -= 125;

                // 3. PROFESYONEL ÖZET
                yPosition = drawSectionHeader(contentStream, "PROFESYONEL ÖZET", margin, yPosition);
                yPosition = drawWrappedText(contentStream, summary, margin, yPosition, 500, fontRegular, 10);
                yPosition -= 10;

                // 4. İŞ VE TOPLULUK DENEYİMİ
                yPosition = drawSectionHeader(contentStream, "İŞ VE TOPLULUK DENEYİMİ", margin, yPosition);
                for (Experience exp : experiences) {
                    String expHeader = exp.getPosition() + " | " + exp.getCompany() + " (" + exp.getPeriod() + ")";
                    drawText(contentStream, expHeader, margin, yPosition, fontBold, 11);
                    yPosition -= 15;
                    yPosition = drawWrappedText(contentStream, exp.getDescription(), margin, yPosition, 500, fontRegular, 10);
                    yPosition -= 8;
                }

                // 5. EĞİTİM BİLGİLERİ
                yPosition -= 4;
                yPosition = drawSectionHeader(contentStream, "EĞİTİM BİLGİLERİ", margin, yPosition);
                for (String edu : educations) {
                    drawText(contentStream, "- " + edu, margin, yPosition, fontBold, 10);
                    yPosition -= 16;
                }

                // 6. TEKNİK YETENEKLER
                yPosition -= 6;
                yPosition = drawSectionHeader(contentStream, "TEKNİK YETENEKLER", margin, yPosition);
                yPosition = drawWrappedText(contentStream, skills, margin, yPosition, 500, fontRegular, 10);

                // 7. YABANCI DİL
                yPosition -= 10;
                yPosition = drawSectionHeader(contentStream, "YABANCI DİL", margin, yPosition);
                drawText(contentStream, languages, margin, yPosition, fontRegular, 10);
            }

            document.save(outputPath);
            System.out.println("PDF basariyla olusturuldu: " + outputPath);

        } catch (IOException e) {
            System.err.println("PDF olusturulurken hata meydana geldi: " + e.getMessage());
        }
    }

    private File resolveFile(String path) {
        File f1 = new File(path);
        if (f1.exists()) return f1;

        File f2 = new File(System.getProperty("user.dir"), path);
        if (f2.exists()) return f2;

        return f1;
    }

    private void initializeFonts(PDDocument document) {
        try {
            File macFont = new File("/System/Library/Fonts/Supplemental/Arial.ttf");
            File macFontBold = new File("/System/Library/Fonts/Supplemental/Arial Bold.ttf");

            if (macFont.exists() && macFontBold.exists()) {
                fontRegular = PDType0Font.load(document, macFont);
                fontBold = PDType0Font.load(document, macFontBold);
                unicodeSupported = true;
            } else {
                fontRegular = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
                fontBold = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
                unicodeSupported = false;
            }
        } catch (Exception e) {
            fontRegular = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
            fontBold = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
            unicodeSupported = false;
        }
    }

    // Siyah başlık ve siyah alt çizgi çizen yardımcı metot
    private float drawSectionHeader(PDPageContentStream stream, String title, float x, float y) throws IOException {
        drawText(stream, title, x, y, fontBold, 13);
        stream.setLineWidth(1f);
        stream.moveTo(x, y - 5);
        stream.lineTo(x + 500, y - 5);
        stream.stroke();
        return y - 22;
    }

    // Siyah metin yazdıran yardımcı metot
    private void drawText(PDPageContentStream stream, String text, float x, float y, PDFont font, float fontSize) throws IOException {
        stream.beginText();
        stream.setFont(font, fontSize);
        stream.newLineAtOffset(x, y);
        stream.showText(sanitizeText(text));
        stream.endText();
    }

    private float drawWrappedText(PDPageContentStream stream, String text, float x, float y, float maxWidth, PDFont font, float fontSize) throws IOException {
        String[] words = sanitizeText(text).split(" ");
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            String testLine = currentLine.length() == 0 ? word : currentLine + " " + word;
            float width = font.getStringWidth(testLine) / 1000 * fontSize;
            if (width > maxWidth) {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word);
            } else {
                currentLine = new StringBuilder(testLine);
            }
        }
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }

        for (String line : lines) {
            drawText(stream, line, x, y, font, fontSize);
            y -= (fontSize + 4);
        }
        return y;
    }

    private String sanitizeText(String text) {
        if (unicodeSupported) return text;
        return text.replace("İ", "I").replace("ı", "i")
                .replace("Ğ", "G").replace("ğ", "g")
                .replace("Ş", "S").replace("ş", "s")
                .replace("Ü", "U").replace("ü", "u")
                .replace("Ö", "O").replace("ö", "o")
                .replace("Ç", "C").replace("ç", "c")
                .replace("•", "-");
    }
}
