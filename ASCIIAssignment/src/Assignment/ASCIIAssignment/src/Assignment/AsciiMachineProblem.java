package assignment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class AsciiMachineProblem {
	/**
	 * Program gets input through run configuration arguments
	 * 
	 * Program displays the following:
	 * - ASCII Table extended (ISO-8859-1/Latin-1 which represents first 256 unicode characters)
	 * - Show number of visible and invisible characters
	 * - The total character count
	 * - Number of embedded words(subtring)
	 * 
	 * @author MANDIGMA, Joselito Miguel
	 * @author FABUL, Francis Ray
	 * @since 2019-08-22
	 */
	
	public static void main(String[] args) throws IOException, DocumentException {
		String input = args[0];
	
		
		displayAscii(input);
		visibleCount(input);
		invisibleCount(input);
		totalCount(input);
		embeddedWord(input, args[1]);
		
		printToPDF(input, args[1]);
	}

	public static void displayAscii(String word) throws IOException {
		char[] charArray = word.toCharArray(); 
		int occ = 0;
		
		// will be used to scan occurrences of characters in word input
		System.out.println("DEC \tChar \tOCC");
		for (int ctr = 0; ctr <= 255; ctr++) {
			char symbol = (char) ctr;
			for (int i = 0; i < charArray.length; i++) {
				if (symbol == charArray[i]) {
					occ++;
				}
			}
			if (ctr == 128) {
				pressEnterToContinue();
			}
			if ((ctr >= 0 && ctr <= 32) || (ctr >= 127 && ctr <= 160)) {
				System.out.println(ctr + "\t" + noSymbolAscii(ctr) + "\t" + occ);
			} else {
				System.out.println(ctr + "\t" + symbol + "\t" + occ);
			}
			occ = 0;
		}
	}

	public static int visibleCount(String word) {
		char[] charArray = word.toCharArray();
		int count = 0;
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] != ' ') {
				count += 1;
			}
		}
		System.out.println("\nNumber of visible characters: " + count);
		return count;
	}

	public static int invisibleCount(String word) {
		char[] charArray = word.toCharArray();
		int count = 0;
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == ' ') {
				count += 1;
			}
		}
		System.out.println("Number of invisible characters: " + count);
		return count;
	}

	public static int totalCount(String word) {
		char[] charArray = word.toCharArray();
		int count;
		for (count = 0; count <= charArray.length; count++) {
			if (count == charArray.length) {
				System.out.println("Total characters: " + count);
			}
		}
		return count;
	}

	public static String noSymbolAscii(int ASCIInum) { 
		String desc = null;
		
		switch (ASCIInum) {
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

	public static int embeddedWord(String word, String search) {
		int embedded = 0;
		int indexCount = 0;
		
		//embedded = (word.length() - word.replace(search, "").length()) / search.length();
		while((indexCount = word.indexOf(search, indexCount++)) != -1) {
			embedded++;
			indexCount++;
		}
		System.out.println("No. of embedded words: " + embedded);
		return embedded;
	}

	public static void pressEnterToContinue() throws IOException {
		System.out.print("\nPress Enter key to continue...\n");
		System.in.read();
	}

	public static void printToPDF(String word, String search) throws IOException, DocumentException, FileNotFoundException {
		char[] charArray = word.toCharArray();
		int occ = 0;
		Document document = new Document();

		// PdfWriter.getInstance(document, new FileOutputStream("E:\\programs\\Java\\CORJAVA1\\PDFTesting\\PDFTesting\\ASCII_MP3_" + new java.util.Date().getTime() + ".pdf"));
		PdfWriter.getInstance(document, new FileOutputStream("D:\\CORJAV1\\OutFile\\FABUL-MANDIGMA-ASCIIAssignment - " + new java.util.Date().getTime() + ".pdf"));
		document.open();

		PdfPTable table = new PdfPTable(3);
		
		Image picture = Image.getInstance("D:\\CORJAV1\\WorkspaceCorjav1\\fabul-se22-corjav1-ws\\ASCIIAssignment\\img\\fabulMandigmaPic.png");
		picture.setAlignment(Element.ALIGN_CENTER);
		picture.scaleAbsolute(300f, 150f);
		document.add(picture);
		
		// header contents
		Paragraph title = new Paragraph("ASCII table", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, BaseColor.BLACK));
		Paragraph myName = new Paragraph("by Mandigma & Fabul SE22", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, BaseColor.BLACK));
		Paragraph mainWord = new Paragraph("Main Word: " + word, FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.BLACK));
		Paragraph embeddedWord = new Paragraph("Embedded Word: " + search + "\n\n", FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.BLACK));
		
		title.setAlignment(Element.ALIGN_CENTER);
		myName.setAlignment(Element.ALIGN_CENTER);
		document.add(title);
		document.add(myName);
		document.add(mainWord);
		document.add(embeddedWord);
		
		// column header text
		Paragraph DEC_Text = new Paragraph("Decimal Code", FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.WHITE));
		Paragraph Char_Text = new Paragraph("Character", FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.WHITE));
		Paragraph OCC_Text = new Paragraph("Occurences", FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.WHITE));
		
		// column header cells
		PdfPCell DEC_Column = new PdfPCell(DEC_Text);
		PdfPCell Char_Column = new PdfPCell(Char_Text);
		PdfPCell OCC_Column = new PdfPCell(OCC_Text);
		
		// column header text formatting
		DEC_Column.setHorizontalAlignment(Element.ALIGN_CENTER);
		Char_Column.setHorizontalAlignment(Element.ALIGN_CENTER);
		OCC_Column.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		// add the column headers
		table.addCell(DEC_Column).setBackgroundColor(BaseColor.BLUE);;
		table.addCell(Char_Column).setBackgroundColor(BaseColor.BLUE);;
		table.addCell(OCC_Column).setBackgroundColor(BaseColor.BLUE);;
		
		// the rest of the table
		for(int ctr = 0; ctr < 256; ctr++) {
			char symbol = (char) ctr;
			// add DEC cell
			table.addCell(Integer.toString(ctr));
			
			// add Char cell
			if((ctr >= 0 && ctr <= 32) || (ctr >= 127 && ctr <= 160)) {
				table.addCell(noSymbolAscii(ctr));
			} else {
				table.addCell(Character.toString(symbol));
			}
			
			// add OCC cell
			for (int i = 0; i < charArray.length; i++) { // scan each letter in the array.
				if (symbol == charArray[i]) { // if it's an occurrence,
					occ++; // increment
				}
			}
			table.addCell(Integer.toString(occ));
			occ = 0;
		}
		document.add(table);
		
		Paragraph visiChar = new Paragraph("Number of Visible haracters: " + visibleCount(word), FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.BLACK));
		Paragraph inviChar = new Paragraph("Number of Invisible characters: " + invisibleCount(word), FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.BLACK));
		Paragraph totalChar = new Paragraph("Total characters: " + totalCount(word), FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.BLACK));
		Paragraph embedded = new Paragraph("No. of embedded words: " + embeddedWord(word, search), FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.BLACK));
		document.add(visiChar);
		document.add(inviChar);
		document.add(totalChar);
		document.add(embedded);
		
		document.close();
		
	}
}
