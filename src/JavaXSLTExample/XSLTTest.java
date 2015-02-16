package JavaXSLTExample;

import java.io.File;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * 
 * @author M.Vasudevarao
 */
public class XSLTTest {
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		String dataXML = "/run/media/Edicson/SVG Example/ejemploGrafica/data.xml";
		String inputXSL = "file:///run/media/Edicson/SVG%20Example/ejemploGrafica/grafica.xsl";
		String outputHTML = "/run/media/Edicson/SVG Example/ejemploGrafica/converted.html";

		XSLTTest st = new XSLTTest();
		try {
			st.transform(dataXML, inputXSL, outputHTML);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			System.err.println("TransformerConfigurationException");
			System.err.println(e);
		} catch (TransformerException e) {
			e.printStackTrace();
			System.err.println("TransformerException");
			System.err.println(e);
		}
	}

	public void transform(String dataXML, String inputXSL, String outputHTML) throws TransformerConfigurationException, TransformerException {

		TransformerFactory factory = TransformerFactory.newInstance();
		StreamSource xslStream = new StreamSource(inputXSL);
		Transformer transformer = factory.newTransformer(xslStream);
		
		StreamSource in = new StreamSource(dataXML);
		StreamResult out = new StreamResult(outputHTML);
		System.out.println("--> " + out.toString());
		transformer.transform(in, out);
		
		System.out.println(transformer.toString());
		System.out.println("The generated HTML file is:" + outputHTML);
	}
}