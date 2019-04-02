package com.zhulong.framework.common.util;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.util.XMLErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

public class XMLUtil {

	private XMLUtil() {
	}

	/**
	 * 用xsd验证xml是否合法
	 * @param xsdFileFullName xsd文件全路径
	 * @param xmlContent xml文件内容
	 * @return 验证错误信息（空字符串表示验证通过）
	 */
	public static String validateXML(String xsdFileFullName, String xmlContent) {
		String errorMessage = "";

		try {
			// 创建默认的XML错误处理器
			XMLErrorHandler errorHandler = new XMLErrorHandler();
			// 获取基于 SAX 的解析器的实例
			SAXParserFactory factory = SAXParserFactory.newInstance();
			// 解析器在解析时验证 XML 内容。
			factory.setValidating(true);
			// 指定由此代码生成的解析器将提供对 XML 名称空间的支持。
			factory.setNamespaceAware(true);
			// 使用当前配置的工厂参数创建 SAXParser 的一个新实例。
			SAXParser parser = factory.newSAXParser();
			// 创建一个读取工具
			SAXReader xmlReader = new SAXReader();
			// 获取要校验xml文档实例
			Document xmlDocument = (Document) xmlReader.read(new StringReader(
					xmlContent));
			// 设置 XMLReader 的基础实现中的特定属性。核心功能和属性列表可以在
			// [url]http://sax.sourceforge.net/?selected=get-set[/url] 中找到。
			parser.setProperty(
					"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
					"http://www.w3.org/2001/XMLSchema");
			parser.setProperty(
					"http://java.sun.com/xml/jaxp/properties/schemaSource",
					new File(xsdFileFullName));
			// 创建一个SAXValidator校验工具，并设置校验工具的属性
			SAXValidator validator = new SAXValidator(parser.getXMLReader());
			// 设置校验工具的错误处理器，当发生错误时，可以从处理器对象中得到错误信息。
			validator.setErrorHandler(errorHandler);
			// 校验
			validator.validate(xmlDocument);
			// 如果错误信息不为空，说明校验失败，打印错误信息
			if (errorHandler.getErrors().hasContent()) {
				errorMessage = errorHandler.getErrors().toString();
			}
		} catch (Exception ex) {
			errorMessage = ex.getMessage();
		}

		return errorMessage;
	}

	/**
	 * 将对象转换成xml
	 * 
	 * @param obj 要转成xml的对象
	 * @param xsdFileFullName xsd文件全路径
	 * @return xml格式的字符串
	 * @throws JAXBException
	 * @throws SAXException
	 */
	public static String objToXml(Object obj, String xsdFileFullName)
			throws JAXBException, SAXException {
		StringWriter sw = new StringWriter();
		JAXBContext jAXBContext;
		jAXBContext = JAXBContext.newInstance(obj.getClass());
		Marshaller marshaller = jAXBContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		if (xsdFileFullName != null && !xsdFileFullName.isEmpty()) {
			marshaller.setSchema(SchemaFactory.newInstance(
					XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(
					new File(xsdFileFullName)));
		}
		marshaller.marshal(obj, sw);
		return sw.toString();
	}

	/**
	 * 将xml转换为对象
	 * @param <T>
	 * @param xml xml字符串
	 * @param clazz 要转换成的对象类型
	 * @param xsdFileFullName xsd文件全路径
	 * @return 转换后的对象
	 * @throws JAXBException
	 * @throws SAXException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xmlToObj(String xml, Class<T> clazz,
                                 String xsdFileFullName) throws JAXBException, SAXException {
		JAXBContext jAXBContext = JAXBContext.newInstance(clazz);
		Unmarshaller um = jAXBContext.createUnmarshaller();
		if (xsdFileFullName != null && !xsdFileFullName.isEmpty()) {
			um.setSchema(SchemaFactory.newInstance(
					XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(
					new File(xsdFileFullName)));
		}
		return (T) um.unmarshal(new StreamSource(new StringReader(xml)));
	}

}
