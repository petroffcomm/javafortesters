package com.smpp.tests.submits;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class SubmitSmDataGenerator {

	public static List<SubmitSmData> loadSubmitsFromXMLFile(File file) {
		XStream xstream = new XStream();
		xstream.alias("submitSM", SubmitSmData.class);
				
		return (List<SubmitSmData>)xstream.fromXML(file);
	}	

	public static void saveSubmitsToXMLFile(List<SubmitSmData> submits, File file) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("submitSM", SubmitSmData.class);
		String xml = xstream.toXML(submits);
		
		FileWriter writer = new FileWriter(file);
		writer.write(xml);
		writer.close();
	}
	
	public static List<Object[]> wrapSubmitsForDataProviders(List<SubmitSmData> submits) {
		List<Object[]> list = new ArrayList<Object[]>();
		
		for (SubmitSmData submitSM: submits){
			list.add(new Object[]{submitSM});
		}
		return list;
	}
	
}
