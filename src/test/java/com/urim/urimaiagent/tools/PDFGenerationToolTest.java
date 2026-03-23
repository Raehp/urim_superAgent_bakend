package com.urim.urimaiagent.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PDFGenerationToolTest {

    @Test
    void generatePDF() {
        PDFGenerationTool pdfGenerationTool = new PDFGenerationTool();
        String fileName = "test.pdf";
        String content = "测试pdf";
        String result = pdfGenerationTool.generatePDF(fileName, content);
        Assertions.assertNotNull(result);
    }
}