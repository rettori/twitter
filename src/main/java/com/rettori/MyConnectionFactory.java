package com.rettori;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.camel.component.properties.PropertiesFunction;
import org.apache.qpid.jms.JmsConnectionFactory;

public class MyConnectionFactory extends JmsConnectionFactory implements PropertiesFunction {
	String url;

	private String queueName;

	public MyConnectionFactory() {
		super("amqp://" + readFile("/data/internal-messaging-host") + ":5672");
		String queueName = readFile("/data/destination-address");
		this.queueName = queueName;

	}
	// TODO Auto-generated constructor stub

	static String readFile(String filename) {

		String content = null;
		File file = new File(filename); // for ex foo.txt
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return content != null ? content.trim().replaceAll("\n ", "") : null;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	@Override
	public String getName() {
		return "queue";
	}

	@Override
	public String apply(String remainder) {
		return queueName;
		
	}

}
