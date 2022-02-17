package com.xorbank.utils;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.xorbank.model.Account;
 
 
public class AccountPDFExporter {
    private List<Account> listAccounts;
     
    public AccountPDFExporter(List<Account> listAccounts) {
        this.listAccounts = listAccounts;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Account Number", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Account Type", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Creation Date Time", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Balance", font));
        table.addCell(cell);
    }
     
    private void writeTableData(PdfPTable table) {        
        for (Account account : listAccounts) {
            table.addCell(String.valueOf(account.getAccountId()));
            table.addCell(account.getAccountType());
            table.addCell(account.getDateCreated());
            table.addCell(String.valueOf(account.getBalance()));
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Accounts", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2.0f, 3.0f, 3.5f, 3.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}