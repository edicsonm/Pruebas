package Reportes;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import au.com.billingbuddy.business.objects.ReportFacade;
import au.com.billingbuddy.common.objects.ConfigurationSystem;
import au.com.billingbuddy.common.objects.Utilities;
import au.com.billingbuddy.exceptions.objects.ReportFacadeException;
import au.com.billingbuddy.vo.objects.TransactionVO;

public class ReporteAmountByDay {
	
	private static int dimensionXScreen = Integer.parseInt(ConfigurationSystem.getInstance().getKey("report.dimensionXScreen"));
	private static int dimensionYScreen = Integer.parseInt(ConfigurationSystem.getInstance().getKey("report.dimensionYScreen"));
	private static int adjustmentDimensionYScreen = Integer.parseInt(ConfigurationSystem.getInstance().getKey("report.adjustmentDimensionYScreen"));
	
	private double initialXPositionGrahic = Integer.parseInt(ConfigurationSystem.getInstance().getKey("report.initialXPositionGrahic"));
	private double initialYPositionGrahic = Integer.parseInt(ConfigurationSystem.getInstance().getKey("report.initialYPositionGrahic"));
	
	private double longXGrahic = Integer.parseInt(ConfigurationSystem.getInstance().getKey("report.longXGrahic"));
	private double longYGrahic = Integer.parseInt(ConfigurationSystem.getInstance().getKey("report.longYGrahic"));
	
	private double escalaX;
	private double mayorY;
	private double minorY;
	private double mayorX;
	private double minorX;
	
	private double scaleYFactor = initialYPositionGrahic + longYGrahic;
	private double scaleXFactor = initialXPositionGrahic + longXGrahic;
	
	private ReportFacade reportFacade = ReportFacade.getInstance();
	
	public ReporteAmountByDay() {
		try {
			TransactionVO transactionVO = new TransactionVO();
			transactionVO.setInitialDateReport("2015-01-03");
			CreateXml(reportFacade.searchAmountByDay(transactionVO));
		} catch (ReportFacadeException e) {
			e.printStackTrace();
			System.out.println("e.getMessage(): " + e.getMessage());
			System.out.println("e.getErrorMenssage(): " + e.getErrorMenssage());
			System.out.println("e.getErrorCode(): " + e.getErrorCode());
		}
	}
	
	public DOMSource CreateXml(ArrayList<TransactionVO> listaReport) {
		DOMSource domSource = null;
		try {
			
			TransactionVO transactionVOMAX = Collections.max(listaReport,new SortListByAmountDesc());
			TransactionVO transactionVOMIN = Collections.max(listaReport,new SortListByAmountAsc());
			mayorY = Integer.parseInt(transactionVOMAX.getAmountDateReport());
			minorY = Integer.parseInt(transactionVOMIN.getAmountDateReport());
			mayorX = listaReport.size();
			minorX = 0;
			
			escalaX = longXGrahic / listaReport.size();
			
			System.out.println("mayorY: " +  mayorY);
			System.out.println("minorY: " +  minorY);
			System.out.println("mayorX: " +  mayorX);
			System.out.println("minorX: " +  minorX);
			System.out.println("escalaX: " +  escalaX);
			System.out.println("scaleYFactor: " +  scaleYFactor);
			System.out.println("scaleXFactor: " +  scaleXFactor);
			
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();
			Element rootElement = document.createElement("grafica");
			document.appendChild(rootElement);
			
			double positionX = initialXPositionGrahic + escalaX;
			double positionY = 0;
			String path = "";
			Collections.sort(listaReport,new SortListByDate());
			for (TransactionVO transactionVO : listaReport) {
				positionY = scaleYFactor - scaleValue(longYGrahic, Double.parseDouble(transactionVO.getAmountDateReport()));
//				System.out.println("("+transactionVO.getDateReport()+" , "+transactionVO.getAmountDateReport()+") - ("+positionX+" , "+ positionY +")");
				path += "L"+positionX +","+ positionY + " ";
				
				Element point = document.createElement("point");
				rootElement.appendChild(point);
								
				Element pointx = document.createElement("pointx");
				pointx.appendChild(document.createTextNode(String.valueOf(positionX)));
				point.appendChild(pointx);
				
				Element pointxReference = document.createElement("pointxReference");
				pointxReference.appendChild(document.createTextNode(String.valueOf(positionX+10)));
				point.appendChild(pointxReference);
				
				
				Element pointy = document.createElement("pointy");
				pointy.appendChild(document.createTextNode(String.valueOf(positionY)));
				point.appendChild(pointy);
				
				Element label = document.createElement("label");
				label.appendChild(document.createTextNode("("+transactionVO.getDateReport()+" ,"+transactionVO.getAmountDateReport()+")"));
				point.appendChild(label);
				
				Element date = document.createElement("date");
				date.appendChild(document.createTextNode(Utilities.formatDate(transactionVO.getDateReport(), 2, 4)));
				point.appendChild(date);
				
				positionX += escalaX;
				
			}
			
			positionX -= escalaX;
			
			Element dimensionX = document.createElement("dimensionX");
			rootElement.appendChild(dimensionX);
			
			Element value = document.createElement("value");
			value.appendChild(document.createTextNode(String.valueOf(dimensionXScreen)));
			dimensionX.appendChild(value);
			
			Element dimensionY = document.createElement("dimensionY");
			rootElement.appendChild(dimensionY);
			
			value = document.createElement("value");
			value.appendChild(document.createTextNode(String.valueOf(dimensionYScreen)));
			dimensionY.appendChild(value);
			
			path = "M" + (initialXPositionGrahic + escalaX)+","+scaleYFactor + " "+path + "L"+positionX+","+scaleYFactor;
			
			Element pathLinearGradient = document.createElement("pathLinearGradient");
			rootElement.appendChild(pathLinearGradient);
			value = document.createElement("value");
			value.appendChild(document.createTextNode(path));
			pathLinearGradient.appendChild(value);
			
			/* Init HighestReference */
			positionY = scaleYFactor - scaleValue(longYGrahic, mayorY);
			Element highestReference = document.createElement("highestReference");
			rootElement.appendChild(highestReference);
			
			Element firstPoint = document.createElement("firtPointHighestReference");
			firstPoint.appendChild(document.createTextNode("M" + (initialXPositionGrahic)+","+ (positionY)));
			highestReference.appendChild(firstPoint);
			
			Element secondPoint = document.createElement("secondPointHighestReference");
			secondPoint.appendChild(document.createTextNode("L"+(scaleXFactor + escalaX) +","+ (positionY)));
			highestReference.appendChild(secondPoint);

			Element positionLabelHighestReference = document.createElement("positionLabelHighestReference");
			rootElement.appendChild(positionLabelHighestReference);
			
			value = document.createElement("X");
			value.appendChild(document.createTextNode(String.valueOf(scaleXFactor + escalaX - 10)));
			positionLabelHighestReference.appendChild(value);
			
			value = document.createElement("Y");
			value.appendChild(document.createTextNode(String.valueOf(positionY - 10)));
			positionLabelHighestReference.appendChild(value);
			
			value = document.createElement("value");
			value.appendChild(document.createTextNode(String.valueOf(mayorY)));
			positionLabelHighestReference.appendChild(value);
			
			/* Finish HighestReference */
			
			/* Init MiddleReference */
			positionY = ((scaleYFactor - scaleValue(longYGrahic, minorY)) + (scaleYFactor - scaleValue(longYGrahic, mayorY)))/2;
			Element middleReference = document.createElement("middleReference");
			rootElement.appendChild(middleReference);
			
			firstPoint = document.createElement("firtPointMiddleReference");
			firstPoint.appendChild(document.createTextNode("M" + (initialXPositionGrahic)+","+ (positionY)));
			middleReference.appendChild(firstPoint);
			
			secondPoint = document.createElement("secondPointMiddleReference");
			secondPoint.appendChild(document.createTextNode("L"+(scaleXFactor + escalaX) +","+ (positionY)));
			middleReference.appendChild(secondPoint);
			
			Element positionLabelMiddleReference = document.createElement("positionLabelMiddleReference");
			rootElement.appendChild(positionLabelMiddleReference);
			
			value = document.createElement("X");
			value.appendChild(document.createTextNode(String.valueOf(scaleXFactor + escalaX - 10)));
			positionLabelMiddleReference.appendChild(value);
			
			value = document.createElement("Y");
			value.appendChild(document.createTextNode(String.valueOf(positionY - 10)));
			positionLabelMiddleReference.appendChild(value);
			
			value = document.createElement("value");
			value.appendChild(document.createTextNode(String.valueOf((mayorY - minorY)/2)));
			positionLabelMiddleReference.appendChild(value);
			
			/* Finish MiddleReference */
			
			
			/* Init LessReference */
			positionY = scaleYFactor - scaleValue(longYGrahic, minorY);
			Element lessReference = document.createElement("lessReference");
			rootElement.appendChild(lessReference);
			
			firstPoint = document.createElement("firtPointLessReference");
			firstPoint.appendChild(document.createTextNode("M" + (initialXPositionGrahic)+","+ (positionY)));
			lessReference.appendChild(firstPoint);
			
			secondPoint = document.createElement("secondPointLessReference");
			secondPoint.appendChild(document.createTextNode("L"+(scaleXFactor + escalaX) +","+ (positionY)));
			lessReference.appendChild(secondPoint);
			
			Element positionLabelLessReference = document.createElement("positionLabelLessReference");
			rootElement.appendChild(positionLabelLessReference);
			
			value = document.createElement("X");
			value.appendChild(document.createTextNode(String.valueOf(scaleXFactor + escalaX - 10)));
			positionLabelLessReference.appendChild(value);
			
			value = document.createElement("Y");
			value.appendChild(document.createTextNode(String.valueOf(positionY - 10)));
			positionLabelLessReference.appendChild(value);
			
			value = document.createElement("value");
			value.appendChild(document.createTextNode(String.valueOf(minorY)));
			positionLabelLessReference.appendChild(value);
			
			/* Finish LessReference */
			
			positionY = scaleYFactor - scaleValue(longYGrahic, minorY) + 20;
			Element scaleXReference = document.createElement("scaleXReference");
			rootElement.appendChild(scaleXReference);
			value = document.createElement("value");
			value.appendChild(document.createTextNode(String.valueOf(positionY)));
			scaleXReference.appendChild(value);
			
//						
//			// creating and writing to xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
//			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			domSource = new DOMSource(document);
			
//			printContent(domSource);
			printDocument(domSource);
//			
			StreamResult streamResult = new StreamResult(new File("/run/media/Edicson/SVG Example/createFile.xml"));
			transformer.transform(domSource, streamResult);
			System.out.println("File saved to specified path!");
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		return domSource;
	}
	
	public void printDocument(DOMSource source) throws TransformerConfigurationException, TransformerException {
		String outputHTML = "/run/media/Edicson/SVG Example/ejemploGrafica/converted.html";
		String inputXSL = "file:///run/media/Edicson/SVG%20Example/ejemploGrafica/grafica.xsl";
		
		TransformerFactory factory = TransformerFactory.newInstance();
		StreamSource xslStream = new StreamSource(inputXSL);
		Transformer transformer = factory.newTransformer(xslStream);
		
		StreamResult out = new StreamResult(outputHTML);
		System.out.println("--> " + out.toString());
		transformer.transform(source, out);
		
		System.out.println(transformer.toString());
		System.out.println("The generated HTML file is:" + outputHTML);
	}
	
	public void printContent(DOMSource source){
		try {
			// Set up the output transformer
			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();
//			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");
			// Print the DOM node

			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			
			trans.transform(source, result);
			String xmlString = sw.toString();

			System.out.println("xmlString: \n" + xmlString);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public double scaleValue(double dimensionScreen, double value){
		return (value * dimensionScreen)/mayorY;
	}
	
	public String transformScale(int dimensionYScreen, double valueToTransfor){
		return String.valueOf(dimensionYScreen - valueToTransfor + adjustmentDimensionYScreen);
	}
	
	public static void main(String[] args) {
		new ReporteAmountByDay();
	}

	class SortListByAmountDesc implements Comparator<TransactionVO>{
		@Override
		public int compare(TransactionVO transactionVOA, TransactionVO transactionVOB) {
			return Integer.parseInt(transactionVOA.getAmountDateReport()) < Integer.parseInt(transactionVOB.getAmountDateReport()) ? -1 : Integer.parseInt(transactionVOA.getAmountDateReport()) == Integer.parseInt(transactionVOB.getAmountDateReport()) ? 0 : 1;
		}
	}
	
	class SortListByAmountAsc implements Comparator<TransactionVO>{
		@Override
		public int compare(TransactionVO transactionVOA, TransactionVO transactionVOB) {
			return Integer.parseInt(transactionVOA.getAmountDateReport()) > Integer.parseInt(transactionVOB.getAmountDateReport()) ? -1 : Integer.parseInt(transactionVOA.getAmountDateReport()) == Integer.parseInt(transactionVOB.getAmountDateReport()) ? 0 : 1;
		}
	}
	
	class SortListByDate implements Comparator<TransactionVO>{
		@Override
		public int compare(TransactionVO transactionVOA, TransactionVO transactionVOB) {
			
			if(Utilities.stringToDate(transactionVOA.getDateReport(),2).compareTo(Utilities.stringToDate(transactionVOB.getDateReport(),2)) > 0){
//        		System.out.println(Utilities.stringToDate(transactionVOA.getDateReport(),2) + " is after " + Utilities.stringToDate(transactionVOB.getDateReport(),2));
        		return 1;
			}else if(Utilities.stringToDate(transactionVOA.getDateReport(),2).compareTo(Utilities.stringToDate(transactionVOB.getDateReport(),2)) < 0){
        		System.out.println(Utilities.stringToDate(transactionVOA.getDateReport(),2) + " is before " + Utilities.stringToDate(transactionVOB.getDateReport(),2));
        		return -1; 
			
			}else if(Utilities.stringToDate(transactionVOA.getDateReport(),2).compareTo(Utilities.stringToDate(transactionVOB.getDateReport(),2)) == 0){
//        		System.out.println(Utilities.stringToDate(transactionVOA.getDateReport(),2) + " is equal " + Utilities.stringToDate(transactionVOB.getDateReport(),2));
        		return 0;
        	}
			return 1;
		}
		
	}
	
}
