package assignment;

import java.io.IOException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ASCIIAssignment {

	/**
	    TODO
	    DONE count the number of each character occurrence and display it on the table using ASCII table.
	 	DONE count the number of visible characters
		DONE count the number of invisible characters
		DONE count the total number of visible and invisible characters
		DONE count the number of embedded search words
		DONE press any key to continue command block 
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	
	/**
	 * @param args
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws IOException, DocumentException {
		String input = args[0];
		String search = args[1];
		String inputDoc = "Entered Word/s: " + args[0];
		String searchDoc = "Searched Word: " + args[1];
		
		Document documentAscii = new Document();
		PdfWriter.getInstance(documentAscii, 
				new FileOutputStream("E:\\CORJAV1\\OutFile\\FABUL-MANDIGMA-ASCIIAssignment - "+ new java.util.Date().getTime() +".pdf"));
		
		documentAscii.open();
		
		PdfPTable table = new PdfPTable(3);
		
		Paragraph word = new Paragraph(inputDoc);
		Paragraph searched = new Paragraph(searchDoc);
		Paragraph decCode = new Paragraph("Decimal Code");
		Paragraph character = new Paragraph("Character");
		Paragraph occurrence = new Paragraph("Occurences");
		Paragraph visible = new Paragraph("Number of Visible Characters: " + visibleChar(input));
		Paragraph invisible = new Paragraph("Number of Invisible Characters: " + invisibleChar(input));
		Paragraph totalCharacters = new Paragraph("Total characters: " + (visibleChar(input)+invisibleChar(input)));
		Paragraph embeddedSearch = new Paragraph("Number of embedded word: " + embeddedWord(input, search));
		
		PdfPCell decimalHeader = new PdfPCell(decCode);
		PdfPCell characterHeader = new PdfPCell(character);
		PdfPCell occurrenceHeader = new PdfPCell(occurrence);
		
		Image imageFabul = Image.getInstance("E:\\CORJAV1\\WorkspaceCorjav1\\fabul-se22-corjav1-ws\\ASCIIAssignment\\img\\fabulMandigmaPic.png");

		imageFabul.scaleAbsolute(150f, 150f);
		imageFabul.setAlignment(Element.ALIGN_CENTER);
		documentAscii.add(imageFabul);
		documentAscii.add(word);
		documentAscii.add(searched);
		
		table.setHeaderRows(1);
		table.addCell(decimalHeader).setBackgroundColor(BaseColor.BLUE);
		table.addCell(characterHeader).setBackgroundColor(BaseColor.BLUE);
		table.addCell(occurrenceHeader).setBackgroundColor(BaseColor.BLUE);
		
		for (int ctr = 0; ctr <=255; ctr++) {
			char[] charA = input.toCharArray();
			char symbol = (char) ctr;
			int occ = 0;

			table.addCell(Integer.toString(ctr));
			
			if((ctr >= 0 && ctr <= 32) || (ctr >= 127 && ctr <= 160)) {
				table.addCell(noSymbolAscii(ctr));
			} else {
				table.addCell(Character.toString(symbol));
			}
			for (int i = 0; i < charA.length; i++) { // scan each letter in the array.
                if (symbol == charA[i]) { // if it's an occurrence,
                    occ+=1; // increment
                }
            }
            // add OCC cell
            table.addCell(Integer.toString(occ));
            occ = 0;
		}
		documentAscii.add(table);
		documentAscii.add(visible);
		documentAscii.add(invisible);
		documentAscii.add(totalCharacters);
		documentAscii.add(embeddedSearch);

		System.out.println("Number of Visible Characters: " + visibleChar(input));
		System.out.println("Number of Invisible Characters: " + invisibleChar(input));
		System.out.println("Total characters: " + (visibleChar(input)+invisibleChar(input)));
		System.out.println("Number of embedded word: " + embeddedWord(input, search));
		displayAscii(input);
		documentAscii.close();
	}
	
	public static void displayAscii(String word) throws IOException {
		char[] charA = word.toCharArray();
		int occurrence=0;
		System.out.println("DEC\tChar\toccurrence");
		for (int ctr = 0; ctr <=255; ctr++) {
			char symbol = (char) ctr;
			for(int i = 0; i < charA.length; i++) { // scan each letter in the array.
				if(symbol == charA[i]) { 			// if it's an occurrence, 
					occurrence++; 								// increment
				}
			}
			if((ctr >= 0 && ctr <= 32) || (ctr >= 127 && ctr <= 160)) { // unidentified character range
				System.out.println(ctr + "\t" + noSymbolAscii(ctr) + "\t" + occurrence);
			} else {
				System.out.println(ctr + "\t" + symbol + "\t" + occurrence);
			}
			if(ctr == 127) {
				// pause command
				System.out.println("Press any key to continue...");
				System.in.read();
			}
			occurrence = 0; // reset
		}
	}
	
	public static int visibleChar(String word) {
		char[] charA = word.toCharArray();
		int visible = 0;
		
		for(int index=0; index<charA.length;index++) {
			if(charA[index]!=' ') {
				visible+=1;
			}
		}
		
		return visible;
	}
	
	public static int invisibleChar(String word) {
		char[] charA = word.toCharArray();
		int invisible = 0;
		
		for(int index=0; index<charA.length;index++) {
			if(charA[index]==' ') {
				invisible+=1;
			}
		}
		
		return invisible;
	}

	
	public static int embeddedWord(String word, String search) {
		int embeddedWords=0;
		
		embeddedWords=(word.length() - word.replace(search, "").length()) / search.length();
		
		return embeddedWords;
	}
	
	public static String noSymbolAscii(int ASCIInum) { // ASCII 0 ~ 32 and 127 ~ 160
		String desc = null;
		switch(ASCIInum) {
			case 0:
				desc = "NULL";
				break;
			case 1:
				desc = "SOH";
				break;
			case 2:
				desc = "STX";
				break;
			case 3:
				desc = "ETX";
				break;
			case 4:
				desc = "EOT";
				break;
			case 5:
				desc = "ENQ";
				break;
			case 6:
				desc = "ACK";
				break;
			case 7:
				desc = "BEL";
				break;
			case 8:
				desc = "BS";
				break;
			case 9:
				desc = "HT";
				break;
			case 10:
				desc = "LF";
				break;
			case 11:
				desc = "VT";
				break;
			case 12:
				desc = "FF";
				break;
			case 13:
				desc = "CR";
				break;
			case 14:
				desc = "SO";
				break;
			case 15:
				desc = "SI";
				break;
			case 16:
				desc = "DLE";
				break;
			case 17:
				desc = "DC1";
				break;
			case 18:
				desc = "DC2";
				break;
			case 19:
				desc = "DC3";
				break;
			case 20:
				desc = "DC4";
				break;
			case 21:
				desc = "NAK";
				break;
			case 22:
				desc = "SYN";
				break;
			case 23:
				desc = "ETB";
				break;
			case 24:
				desc = "CAN";
				break;
			case 25:
				desc = "EM";
				break;
			case 26:
				desc = "SUB";
				break;
			case 27:
				desc = "ESC";
				break;
			case 28:
				desc = "FS";
				break;
			case 29:
				desc = "GS";
				break;
			case 30:
				desc = "RS";
				break;
			case 31:
				desc = "US";
				break;
			case 32:
				desc = "Space";
				break;
			case 127:
				desc = "DEL";
				break;
			case 128:
				desc = "Ç";
				break;
			case 129:
				desc = "ü";
				break;
			case 130:
				desc = "é";
				break;
			case 131:
				desc = "â";
				break;
			case 132:
				desc = "ä";
				break;
			case 133:
				desc = "à";
				break;
			case 134:
				desc = "å";
				break;
			case 135:
				desc = "ç";
				break;
			case 136:
				desc = "ê";
				break;
			case 137:
				desc = "ë";
				break;
			case 138:
				desc = "è";
				break;
			case 139:
				desc = "ï";
				break;
			case 140:
				desc = "î";
				break;
			case 141:
				desc = "ì";
				break;
			case 142:
				desc = "Ä";
				break;
			case 143:
				desc = "Å";
				break;
			case 144:
				desc = "É";
				break;
			case 145:
				desc = "æ";
				break;
			case 146:
				desc = "Æ";
				break;
			case 147:
				desc = "ô";
				break;
			case 148:
				desc = "ö";
				break;
			case 149:
				desc = "ò";
				break;
			case 150:
				desc = "û";
				break;
			case 151:
				desc = "ù";
				break;
			case 152:
				desc = "ÿ";
				break;
			case 153:
				desc = "Ö";
				break;
			case 154:
				desc = "Ü";
				break;
			case 155:
				desc = "¢";
				break;
			case 156:
				desc = "£";
				break;
			case 157:
				desc = "¥";
				break;
			case 158:
				desc = "₧";
				break;
			case 159:
				desc = "ƒ";
				break;
			case 160:
				desc = "á";
				break;
		}
		return desc;
	}
}
