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
import au.com.billingbuddy.exceptions.objects.ReportFacadeException;
import au.com.billingbuddy.vo.objects.TransactionVO;

public class ReporteAmountByDay_BK {
	
	private static int dimensionXScreen = Integer.parseInt(ConfigurationSystem.getInstance().getKey("report.dimensionXScreen"));
	private static int dimensionYScreen = Integer.parseInt(ConfigurationSystem.getInstance().getKey("report.dimensionYScreen"));
	private static int adjustmentDimensionYScreen = Integer.parseInt(ConfigurationSystem.getInstance().getKey("report.adjustmentDimensionYScreen"));
	
	private ReportFacade reportFacade = ReportFacade.getInstance();
//	ConfigurationSystem configurationSystem = ConfigurationSystem.getInstance();
	
	public ReporteAmountByDay_BK() {
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
			TransactionVO transactionVOMAX = Collections.max(listaReport,new ComparatorTransactionVO("MajorY"));
			int mayorY = Integer.parseInt(transactionVOMAX.getAmountDateReport());
			TransactionVO transactionVOMIN = Collections.max(listaReport,new ComparatorTransactionVO("MinorY"));
			int minorY = Integer.parseInt(transactionVOMIN.getAmountDateReport());
			
			int mayorX = listaReport.size();
			double mayorRight = 0;
			
			String path = "M";
			String coordinate = "";
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			// define root elements
			Document document = documentBuilder.newDocument();
			Element rootElement = document.createElement("grafica");
			document.appendChild(rootElement);
			int position = 0;
			
			for (int i = listaReport.size()-1 ;i > 0 ; i--) {
				
				TransactionVO transactionVO = (TransactionVO)listaReport.get(i);
				Element point = document.createElement("point");
				rootElement.appendChild(point);
				
				coordinate = String.valueOf((scaleValue(dimensionXScreen, position += 1, mayorX)));
				Element pointx = document.createElement("pointx");
				pointx.appendChild(document.createTextNode(coordinate));
				point.appendChild(pointx);
				
				if(mayorRight < Double.parseDouble(coordinate)) mayorRight = Double.parseDouble(coordinate);
				
				path = path + " L" + coordinate;
				coordinate = transformScale(dimensionYScreen,scaleValue(dimensionYScreen, Integer.parseInt(transactionVO.getAmountDateReport()), mayorY));
				Element pointy = document.createElement("pointy");
				pointy.appendChild(document.createTextNode(coordinate));
				point.appendChild(pointy);
				
				Element label = document.createElement("label");
				label.appendChild(document.createTextNode("("+transactionVO.getDateReport()+" ,"+transactionVO.getAmountDateReport()+")"));
				point.appendChild(label);
				path = path + "," + coordinate;
				
			}
			
			path = path + " L" + mayorRight;
			path = path + "," + transformScale(dimensionYScreen,scaleValue(dimensionYScreen, minorY, mayorY));
			path = path.replace("M L", "M");
			
			Element pathLinearGradient = document.createElement("pathLinearGradient");
			rootElement.appendChild(pathLinearGradient);
			
			Element value = document.createElement("value");
			value.appendChild(document.createTextNode(path));
			pathLinearGradient.appendChild(value);
			
			
			Element highestReference = document.createElement("highestReference");
			rootElement.appendChild(highestReference);
			
			Element firtPoint = document.createElement("firtPointHighestReference");
			firtPoint.appendChild(document.createTextNode("M10," + (transformScale(dimensionYScreen,scaleValue(dimensionYScreen, mayorY, mayorY)))));
			highestReference.appendChild(firtPoint);
			
			Element secondPoint = document.createElement("secondPointHighestReference");
			secondPoint.appendChild(document.createTextNode("L"+dimensionXScreen +","+ (transformScale(dimensionYScreen,scaleValue(dimensionYScreen, mayorY, mayorY)))));
			highestReference.appendChild(secondPoint);
			
			/**/
			Element middleReference = document.createElement("middleReference");
			rootElement.appendChild(middleReference);
			
			firtPoint = document.createElement("firtPointMiddleReference");
			firtPoint.appendChild(document.createTextNode("M10," + (transformScale(dimensionYScreen,scaleValue(dimensionYScreen, (mayorY - minorY)/2, mayorY)))));
			middleReference.appendChild(firtPoint);
			
			secondPoint = document.createElement("secondPointMiddleReference");
			secondPoint.appendChild(document.createTextNode("L"+dimensionXScreen +","+ (transformScale(dimensionYScreen,scaleValue(dimensionYScreen, (mayorY - minorY)/2, mayorY)))));
			middleReference.appendChild(secondPoint);
			/**/
			
			
			Element lessReference = document.createElement("lessReference");
			rootElement.appendChild(lessReference);
			
			firtPoint = document.createElement("firtPointLessReference");
			firtPoint.appendChild(document.createTextNode("M10," + (transformScale(dimensionYScreen,scaleValue(dimensionYScreen, minorY, mayorY)))));
			lessReference.appendChild(firtPoint);
			
			secondPoint = document.createElement("secondPointLessReference");
			secondPoint.appendChild(document.createTextNode("L"+dimensionXScreen +","+ (transformScale(dimensionYScreen,scaleValue(dimensionYScreen, minorY, mayorY)))));
			lessReference.appendChild(secondPoint);
			
			
			Element dimensionX = document.createElement("dimensionX");
			rootElement.appendChild(dimensionX);
			
			value = document.createElement("value");
			value.appendChild(document.createTextNode(String.valueOf(dimensionXScreen)));
			dimensionX.appendChild(value);
			
			Element dimensionY = document.createElement("dimensionY");
			rootElement.appendChild(dimensionY);
			
			value = document.createElement("value");
			value.appendChild(document.createTextNode(String.valueOf(dimensionXScreen)));
			dimensionY.appendChild(value);
			
			Element labelHighestReference = document.createElement("labelHighestReference");
			rootElement.appendChild(labelHighestReference);
			
			firtPoint = document.createElement("firtPointLabelHighestReference");
			firtPoint.appendChild(document.createTextNode(String.valueOf(dimensionXScreen)));
			labelHighestReference.appendChild(firtPoint);
			
			secondPoint = document.createElement("secondPointLabelHighestReference");
			secondPoint.appendChild(document.createTextNode(String.valueOf(Double.parseDouble(transformScale(dimensionYScreen,scaleValue(dimensionYScreen, mayorY, mayorY))) - 2)));
			labelHighestReference.appendChild(secondPoint);
			
			value = document.createElement("value");
			value.appendChild(document.createTextNode(String.valueOf(mayorY)));
			labelHighestReference.appendChild(value);
			
			
			Element labelMiddleReference = document.createElement("labelMiddleReference");
			rootElement.appendChild(labelMiddleReference);
			
			firtPoint = document.createElement("firtPointLabelMiddleReference");
			firtPoint.appendChild(document.createTextNode(String.valueOf(dimensionXScreen)));
			labelMiddleReference.appendChild(firtPoint);
			
			secondPoint = document.createElement("secondPointLabelMiddleReference");
			secondPoint.appendChild(document.createTextNode(String.valueOf(Double.parseDouble(transformScale(dimensionYScreen,scaleValue(dimensionYScreen, (mayorY - minorY)/2, mayorY))) - 2)));
			labelMiddleReference.appendChild(secondPoint);
			
			value = document.createElement("value");
			value.appendChild(document.createTextNode(String.valueOf((mayorY - minorY)/2)));
			labelMiddleReference.appendChild(value);
			
						
			// creating and writing to xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
//			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			domSource = new DOMSource(document);
			
			printContent(domSource);
			printDocument(domSource);
			
			StreamResult streamResult = new StreamResult(new File("/run/media/Edicson/SVG Example/createFile.xml"));
			transformer.transform(domSource, streamResult);
			System.out.println("File saved to specified path!");
			System.out.println("position " + position);
			System.out.println("mayorY " + mayorY);
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
	
	public double percent(int number, int mayor){
		return (number * 100)/mayor;
	}
	
	public double scaleValue(int dimensionXScreen, int number, int mayor){
		return (dimensionXScreen*percent(number, mayor)/100);
	}
	
	public String transformScale(int dimensionYScreen, double valueToTransfor){
		return String.valueOf(dimensionYScreen - valueToTransfor + adjustmentDimensionYScreen);
	}
	
	public static void main(String[] args) {
		new ReporteAmountByDay_BK();
	}

	class ComparatorTransactionVO implements Comparator<TransactionVO>{
		private String comparator;
		public ComparatorTransactionVO(String comparator){
			this.comparator = comparator;
		}

		@Override
		public int compare(TransactionVO transactionVOA, TransactionVO transactionVOB) {
			if (comparator.equalsIgnoreCase("MinorY"))return Integer.parseInt(transactionVOA.getAmountDateReport()) > Integer.parseInt(transactionVOB.getAmountDateReport()) ? -1 : Integer.parseInt(transactionVOA.getAmountDateReport()) == Integer.parseInt(transactionVOB.getAmountDateReport()) ? 0 : 1;
			else if (comparator.equalsIgnoreCase("MajorY"))return Integer.parseInt(transactionVOA.getAmountDateReport()) < Integer.parseInt(transactionVOB.getAmountDateReport()) ? -1 : Integer.parseInt(transactionVOA.getAmountDateReport()) == Integer.parseInt(transactionVOB.getAmountDateReport()) ? 0 : 1;
			else return Integer.parseInt(transactionVOA.getAmountDateReport()) < Integer.parseInt(transactionVOB.getAmountDateReport()) ? -1 : Integer.parseInt(transactionVOA.getAmountDateReport()) == Integer.parseInt(transactionVOB.getAmountDateReport()) ? 0 : 1;
		}
		
	}
	
}
