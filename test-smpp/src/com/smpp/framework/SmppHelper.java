package com.smpp.framework;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.smpp.Data;
import org.smpp.Session;
import org.smpp.TCPIPConnection;
import org.smpp.pdu.AddressRange;
import org.smpp.pdu.BindReceiver;
import org.smpp.pdu.BindRequest;
import org.smpp.pdu.BindResponse;
import org.smpp.pdu.BindTransciever;
import org.smpp.pdu.BindTransmitter;
import org.smpp.pdu.SubmitSM;
import org.smpp.pdu.SubmitSMResp;
import org.smpp.pdu.UnbindResp;
import org.smpp.pdu.WrongDateFormatException;

import com.google.common.base.CaseFormat;
import com.smpp.tests.submits.SubmitSmData;

public class SmppHelper extends BaseHelper {
	
	private boolean isBoundToSMSC;	
	
	private TCPIPConnection tcpConnection;
	private Session smppSession;
	private PDUEventListener pduListener;

	private String syncMode;
	private String bindMode;
	private String smscIp;
	private int smscPort;
	private String systemId;
	private String password;
	private String systemType;
	private String phoneRange;
	

	public SmppHelper(ApplicationManager manager) {
		super(manager);
		loadSmppProperties();
	}

	private void loadSmppProperties() {
		syncMode = manager.getProperty("smpp.syncMode");		
		bindMode = manager.getProperty("smpp.bindMode");
		
		smscIp = manager.getProperty("smpp.smscIp"); 
		smscPort = Integer.parseInt(manager.getProperty("smpp.smscPort"));
		
		systemId = manager.getProperty("smpp.systemId");
		password = manager.getProperty("smpp.password");
		
		systemType = manager.getProperty("smpp.systemType");
		
		phoneRange = manager.getProperty("smpp.phoneRange");
		//= manager.getProperty("");		 
		
	}

	public void close() {
		//sendUnBindRequest();
	}

	public void sendBindRequest() {
		try {
			if (isBoundToSMSC) {
				System.out.println("Already bound, unbind first.");
				return;
			}
			
			BindRequest request = null;
			BindResponse response = null;
			
			if (bindMode.compareToIgnoreCase("t") == 0) {
				request = new BindTransmitter();
			} else if (bindMode.compareToIgnoreCase("r") == 0) {
				request = new BindReceiver();
			} else if (bindMode.compareToIgnoreCase("tr") == 0) {
				request = new BindTransciever();
			} else {
				System.out
						.println("Invalid bind mode, expected t, r or tr, got "
								+ bindMode + ". Operation canceled.");
				return;
			}
			
			tcpConnection = new TCPIPConnection(smscIp, smscPort);
			tcpConnection.setReceiveTimeout(20 * 1000);
			smppSession = new Session(tcpConnection);
			
			// set request params
			request.setSystemId(systemId);
			request.setPassword(password);
			request.setSystemType(systemType);
			request.setInterfaceVersion((byte) 0x34);
			request.setAddressRange(
					new AddressRange(Data.GSM_TON_UNKNOWN, Data.GSM_NPI_E164, phoneRange));
			
			// send the request
			System.out.println("Bind request " + request.debugString());
			
			if (syncMode.equals("async")) {
				pduListener = new PDUEventListener(smppSession);
				response = smppSession.bind(request, pduListener);
			} else if (syncMode.equals("sync")) {
				response = smppSession.bind(request);
			}
			
			System.out.println("Bind response " + response.debugString());
			
			if (response.getCommandStatus() == Data.ESME_ROK) {
				isBoundToSMSC = true;
			}
		} catch (Exception e) {
			//event.write(e, "");
			//debug.write("Bind operation failed. " + e);
			System.out.println("Bind operation failed. " + e);
		} finally {
			//debug.exit(this);
		}
	}
	
	public void sendUnBindRequest() {
		try {
			if (!isBoundToSMSC) {
				System.out.println("Not bound, cannot unbind.");
				return;
			}
			
			// send the request
			System.out.println("Going to unbind.");
			if (smppSession.getReceiver().isReceiver()) {
				System.out.println("It can take a while to stop the receiver.");
			}
			
			UnbindResp response = smppSession.unbind();
			System.out.println("Unbind response: " + response.debugString());
			isBoundToSMSC = false;
		} catch (Exception e) {
			/*event.write(e, "");
			debug.write("Unbind operation failed. " + e);*/
			System.out.println("Unbind operation failed. " + e);
		} finally {
			//debug.exit(this);
		}
		
	}
	
	public void sendSubmitSm(SubmitSmData submitData) {
		try {
			SubmitSM submitReq = new SubmitSM();
			SubmitSMResp response;
			
			// set values
			submitReq.setServiceType(submitData.serviceType);
			submitReq.setEsmClass(submitData.esmClass);
			submitReq.setProtocolId(submitData.protocolId);
			submitReq.setPriorityFlag(submitData.priorityFlag);
			submitReq.setReplaceIfPresentFlag(submitData.replaceIfPresentFlag);
			submitReq.setRegisteredDelivery(submitData.registeredDelivery);
			
			submitReq.setSourceAddr(Data.GSM_TON_UNKNOWN, Data.GSM_NPI_E164, submitData.sourceAddr);
			submitReq.setDestAddr(Data.GSM_TON_INTERNATIONAL, Data.GSM_NPI_E164, submitData.destAddr);
			setSmppDates(submitReq, submitData);
			//submitReq.setScheduleDeliveryTime(submitData.scheduleDeliveryTime);
			//submitReq.setValidityPeriod(submitData.validityPeriod);
			
			submitReq.setDataCoding(submitData.dataCoding);
			String smppMsgEncoding;
			
			switch (submitData.dataCoding){
				case 0x00: smppMsgEncoding = Data.ENC_UTF8; break;
				case 0x03: smppMsgEncoding = Data.ENC_ISO8859_1; break;
				case 0x08: smppMsgEncoding = Data.ENC_UTF16_LE; break;
				default: smppMsgEncoding = Data.ENC_UTF8;
			}
			
			submitReq.setShortMessage(submitData.shortMessage, smppMsgEncoding);				
			submitReq.setSmDefaultMsgId(submitData.smDefaultMsgId);
			
			// send the request
			submitReq.assignSequenceNumber(true);
				
			if (syncMode.equals("a")) {
				System.out.println("Submit request " + submitReq.debugString());
				smppSession.submit(submitReq);
				System.out.println();
			} else {
				System.out.println("Submit request " + submitReq.debugString());
				response = smppSession.submit(submitReq);
				System.out.println("Submit response "
						+ response.debugString());
				//messageId = response.getMessageId();
			}

		} catch (Exception e) {
			//event.write(e, "");
			//debug.write("Submit operation failed. " + e);
			System.out.println("Submit operation failed. " + e);
			e.printStackTrace();
		} finally {
			//debug.exit(this);
		}
	}

	private void setSmppDates(SubmitSM submitReq, SubmitSmData submitData) throws WrongDateFormatException {
		
		if (submitData.scheduleDeliveryTime.endsWith("R") ||
				submitData.scheduleDeliveryTime.isEmpty()){
			submitReq.setScheduleDeliveryTime(submitData.scheduleDeliveryTime);
		}else if (submitData.scheduleDeliveryTime.equals("auto")){
			submitReq.setScheduleDeliveryTime(calculateSmppDate(Calendar.SECOND, 20));
		}
		
		if (submitData.validityPeriod.endsWith("R") ||
				submitData.validityPeriod.isEmpty()){
			submitReq.setValidityPeriod(submitData.validityPeriod);
		}else if (submitData.validityPeriod.equals("auto")){
			submitReq.setValidityPeriod(calculateSmppDate(Calendar.DATE, 0));
		}
		
	}
	
	private String calculateSmppDate(int dateUnitType, int unitOffset){
		Calendar cal = Calendar.getInstance(new Locale("ru", "RU"));
		cal.setTime(new Date());
		cal.add(dateUnitType, unitOffset);
		
		return String.format("%2d%02d%02d%02d%02d%02d020+", new Object[] {cal.get(cal.YEAR)%100,cal.get(cal.MONTH),cal.get(cal.DAY_OF_MONTH),cal.get(cal.HOUR_OF_DAY),cal.get(cal.MINUTE),cal.get(cal.SECOND)});
	}

}
