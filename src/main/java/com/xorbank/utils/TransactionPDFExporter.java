package com.xorbank.utils;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.xorbank.model.Transaction;
 
 
public class TransactionPDFExporter {
    private List<Transaction> listTransactions;
     
    public TransactionPDFExporter(List<Transaction> listTransactions) {
        this.listTransactions = listTransactions;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Transaction Id", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Sender Account", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Receiver Account", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Amount", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Status", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Date and Time", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);
    }
     
    private void writeTableData(PdfPTable table) {
        for (Transaction transaction : listTransactions) {
            table.addCell(String.valueOf(transaction.getTransactionId()));
            table.addCell(String.valueOf(transaction.getFromAccount()));
            table.addCell(String.valueOf(transaction.getToAccount()));
            table.addCell(String.valueOf(transaction.getAmount()));
            table.addCell(transaction.getTransactionStatus());
            table.addCell(transaction.getTransactionDate());
            table.addCell(transaction.getDescription());
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(14);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Transaction Statements", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.0f, 3.0f, 3.0f, 2.5f, 2.5f, 3.5f, 3.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}