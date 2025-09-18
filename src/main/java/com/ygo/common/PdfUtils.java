package com.ygo.common;

import com.ygo.model.dto.CardDTO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class PdfUtils {

    private static final float MARGIN = 50;
    private static final float LEADING = 15f;
    private static final float PAGE_HEIGHT = 800;


    public byte[] generatePdf(List<CardDTO> cardDTOs) throws IOException {
        try (PDDocument document = new PDDocument();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            PDType0Font font = PDType0Font.load(document, new File("src/main/resources/fonts/arial.ttf"));
            float yPosition = PAGE_HEIGHT;

            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(font, 12);

            for (CardDTO card : cardDTOs) {
                if (yPosition < MARGIN + LEADING * 8) {
                    contentStream.close();
                    page = new PDPage();
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    contentStream.setFont(font, 12);
                    yPosition = PAGE_HEIGHT;
                }
                yPosition = writeCard(contentStream, card, yPosition);
            }

            contentStream.close();
            document.save(baos);
            return baos.toByteArray();
        }
    }


    private float writeCard(PDPageContentStream contentStream, CardDTO card, float yPosition) throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset(MARGIN, yPosition);

        contentStream.showText("ID: " + safeString(card.getId()));
        contentStream.newLineAtOffset(0, -LEADING);
        contentStream.showText("Name: " + safeString(card.getName()));
        contentStream.newLineAtOffset(0, -LEADING);
        contentStream.showText("Type: " + safeString(card.getType()));
        contentStream.newLineAtOffset(0, -LEADING);
        contentStream.showText("Race: " + safeString(card.getRace()));
        contentStream.newLineAtOffset(0, -LEADING);
        contentStream.showText("ATK: " + safeString(card.getAtk()));
        contentStream.newLineAtOffset(0, -LEADING);
        contentStream.showText("DEF: " + safeString(card.getDef()));
        contentStream.newLineAtOffset(0, -LEADING);
        contentStream.showText("Level: " + safeString(card.getLevel()));
        contentStream.newLineAtOffset(0, -LEADING);
        contentStream.showText("-----------------------------");

        contentStream.endText();

        return yPosition - LEADING * 8;
    }

    private String safeString(Object obj) {
        return obj != null ? obj.toString() : "N/A";
    }
}
