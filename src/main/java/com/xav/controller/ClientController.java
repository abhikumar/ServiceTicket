package com.xav.controller;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xav.model.Subinfo;
import com.xav.model.Ticket;
import com.xav.model.TicketInfo;
import com.xav.model.UserStep;

@RestController
@CrossOrigin
@RequestMapping({ "/ticket" })
public class ClientController {

	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public Boolean save(@RequestBody TicketInfo ticketInfo) {

		final String insertSql = "INSERT INTO ITticket (" +

		"	Category, " + "	ContactNo, " + "	SubCategory, " + "	SeatLocation,"
				+ "ShiftTimingFrom," + "ShiftTimingTo," + "Title," + "Details,"
				+ "RequiredDuration," + "ProjectName," + "DepartmentName,"
				+ "BusinessJustification," + "userName," + "FullName,"
				+ "TicketOpen)" +

				"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = new Object[] { ticketInfo.getCategory(),
				ticketInfo.getContactNumber(), ticketInfo.getSubCategory(),
				ticketInfo.getSeatLocation(), ticketInfo.getShiftTimingFrom(),
				ticketInfo.getShiftTimingTo(), ticketInfo.getTitle(),
				ticketInfo.getDetails(), ticketInfo.getRequiredDuration(),
				ticketInfo.getProjectName(), ticketInfo.getDepartmentName(),
				ticketInfo.getBusinessJustification(),
				ticketInfo.getUserName(), ticketInfo.getFullName(),
				ticketInfo.getTicketOpen() };
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		int insertRecord = jdbcTemplate.update(insertSql, obj, types);
		if (insertRecord == 1) {
			try {
				Thread.sleep(1000);
				try {
					String c = "C:\\Users\\akumar18\\AppData\\Local\\UiPath\\app-17.1.6522\\UiRobot.exe"
							+ " /file:C:\\Users\\akumar18\\Documents\\UiPath\\New\\Main.xaml";
					Runtime.getRuntime().exec(c);
				} catch (Exception e) {
					System.out
							.println("HEY Buddy ! U r Doing Something Wrong ");
					e.printStackTrace();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (insertRecord == 0) {
			return false;
		}
		return true;

	}

	@RequestMapping(value = "/getStep", method = RequestMethod.POST, produces = "application/json")
	public Ticket getSteps(@RequestBody UserStep userStep) {
		Ticket ticket = null;
		List<Integer> validOption = null;
		if (userStep.getStep() == 0) {
			ticket = new Ticket();
			ticket.setContentValue("Please Select Ticket Type");
			ticket.setMessage("Success");
			Map<Integer, String> ticketType = new HashMap<Integer, String>();
			ticketType.put(1, "IT Ticket");
			ticketType.put(2, "Cab Ticket");
			ticketType.put(3, "HelpDesk Ticket");
			ticket.setCategoryDetails(ticketType);
			validOption = new ArrayList<Integer>();
			validOption.add(1);
			ticket.setValidOption(validOption);
			ticket.setShowOn("UN-Modified Text");
			ticket.setNextStep("1");
		}

		if (userStep.getStep() == 1) {
			ticket = new Ticket();
			ticket.setContentValue("Please Select Category");
			ticket.setMessage("Success");
			Map<Integer, String> cat = getCat();
			ticket.setCategoryDetails(cat);
			validOption = new ArrayList<Integer>();
			Iterator<Entry<Integer, String>> itr = cat.entrySet().iterator();
			while (itr.hasNext()) {
				Map.Entry<java.lang.Integer, java.lang.String> entry = (Map.Entry<java.lang.Integer, java.lang.String>) itr
						.next();
				validOption.add(entry.getKey());
			}
			ticket.setValidOption(validOption);
			ticket.setShowOn("UN-Modified Text");
			ticket.setNextStep("2");
			return ticket;
		} else if (userStep.getStep() == 2) {
			ticket = new Ticket();
			ticket.setContentValue("Please Select SubCategory");
			ticket.setMessage("Success");
			Map<Integer, String> cat = getCat();
			if (cat.containsKey(userStep.getCategory())) {
				Map<Integer, Map<Integer, String>> subCategory = getSubCategory(userStep
						.getCategory());

				ticket.setCategoryDetails(subCategory.get(userStep
						.getCategory()));
				validOption = new ArrayList<Integer>();
				Iterator<Entry<Integer, String>> itrSub = subCategory
						.get(userStep.getCategory()).entrySet().iterator();
				while (itrSub.hasNext()) {
					Map.Entry<java.lang.Integer, java.lang.String> entry = (Map.Entry<java.lang.Integer, java.lang.String>) itrSub
							.next();
					validOption.add(entry.getKey());
				}

				ticket.setValidOption(validOption);
				ticket.setShowOn("UN-Modified Text");
				ticket.setNextStep("3");

			}
			return ticket;
		} else if (userStep.getStep() == 3) {
			ticket = new Ticket();
			ticket.setMessage("Success");
			ticket.setContentValue("Please Enter Your Contact Number");
			// ticket.setContactNumber("Please Enter Your Contact Number");
			ticket.setNextStep("4");
			ticket.setShowOn("Text-Field");
			return ticket;
		} else if (userStep.getStep() == 4) {
			ticket = new Ticket();
			ticket.setMessage("Success");
			ticket.setContentValue("Please enter your seat Location");
			// ticket.setSeatLocation("Please enter your seat Location");
			ticket.setNextStep("5");
			ticket.setShowOn("Text-Field");
			return ticket;
		} else if (userStep.getStep() == 5) {
			ticket = new Ticket();
			ticket.setMessage("Success");
			ticket.setContentValue("Please enter Title");
			// ticket.setTitle("Please enter Title");
			ticket.setNextStep("6");
			ticket.setShowOn("Text-Field");
			return ticket;
		} else if (userStep.getStep() == 6) {
			ticket = new Ticket();
			ticket.setMessage("Success");
			ticket.setContentValue("Please enter Details");
			// ticket.setDetails("Please enter Details");
			ticket.setShowOn("Text-Field");
			// ticket.setNextStep("7");
			Subinfo[][] info = getSubinfo();
			if (info[userStep.getCategory()][userStep.getSubCategory()] != null) {
				if (info[userStep.getCategory()][userStep.getSubCategory()]
						.getBuisnessDetails() == false) {
					ticket.setNextStep("7");
				} else {
					ticket.setNextStep("-1");

				}

			} else {
				ticket.setNextStep("7");
			}
			return ticket;
		}

		/*
		 * else if (userStep.getStep() == 7) { ticket = new Ticket();
		 * ticket.setMessage("Success");
		 * ticket.setDetails("Please enter Details"); Subinfo[][] info =
		 * getSubinfo(); if
		 * (info[userStep.getCategory()][userStep.getSubCategory()] != null) {
		 * if (info[userStep.getCategory()][userStep.getSubCategory()]
		 * .getBuisnessDetails() == false) { ticket.setNextStep("8"); } else {
		 * ticket.setNextStep("10");
		 * 
		 * }
		 * 
		 * } else { ticket.setNextStep("8"); } }
		 */else if (userStep.getStep() == 7) {
			ticket = new Ticket();
			ticket.setMessage("Success");
			ticket.setShowOn("Text-Field");
			Subinfo[][] info = getSubinfo();
			if (info[userStep.getCategory()][userStep.getSubCategory()] != null) {
				if (info[userStep.getCategory()][userStep.getSubCategory()]
						.getRequiredDetails() == false) {
					ticket.setContentValue("Please enter Buisness Details");
					// ticket.setbDetails("Please enter Buisness Details");
					ticket.setNextStep("8");
				} else {
					ticket.setNextStep("-1");
				}
			} else {
				ticket.setContentValue("Please enter Buisness Details");
				// ticket.setbDetails("Please enter Buisness Details");
				ticket.setNextStep("8");
			}

		} else if (userStep.getStep() == 8) {
			ticket = new Ticket();
			ticket.setMessage("Success");
			ticket.setContentValue("Please enter Required Days");
			// ticket.setReqDetails("Please enter Required Days");
			ticket.setNextStep("-1");
			ticket.setShowOn("Text-Field");

		}
		return ticket;

	}

	Map<Integer, String> getCategory() {

		return getCat();
	}

	Subinfo[][] getSubinfo() {
		Subinfo[][] info = new Subinfo[9][24];
		info[1][4] = new Subinfo();
		info[1][4].setBuisnessDetails(true);
		info[1][4].setRequiredDetails(true);
		info[1][5] = new Subinfo();
		info[1][5].setBuisnessDetails(true);
		info[1][5].setRequiredDetails(true);
		info[1][6] = new Subinfo();
		info[1][6].setBuisnessDetails(true);
		info[1][6].setRequiredDetails(true);
		info[1][8] = new Subinfo();
		info[1][8].setBuisnessDetails(true);
		info[1][8].setRequiredDetails(true);

		info[3][3] = new Subinfo();
		info[3][3].setBuisnessDetails(true);
		info[3][3].setRequiredDetails(true);

		info[3][4] = new Subinfo();
		info[3][4].setBuisnessDetails(true);
		info[3][4].setRequiredDetails(true);

		info[3][5] = new Subinfo();
		info[3][5].setBuisnessDetails(true);
		info[3][5].setRequiredDetails(true);

		info[4][1] = new Subinfo();
		info[4][1].setBuisnessDetails(true);
		info[4][1].setRequiredDetails(true);
		info[4][2] = new Subinfo();
		info[4][2].setBuisnessDetails(true);
		info[4][2].setRequiredDetails(true);
		info[4][3] = new Subinfo();
		info[4][3].setBuisnessDetails(true);
		info[4][3].setRequiredDetails(true);
		info[4][4] = new Subinfo();
		info[4][4].setBuisnessDetails(true);
		info[4][4].setRequiredDetails(true);
		info[4][8] = new Subinfo();
		info[4][8].setBuisnessDetails(true);
		info[4][8].setRequiredDetails(true);
		info[4][13] = new Subinfo();
		info[4][13].setBuisnessDetails(true);
		info[4][13].setRequiredDetails(true);
		info[4][15] = new Subinfo();
		info[4][15].setBuisnessDetails(true);
		info[4][15].setRequiredDetails(true);
		info[4][16] = new Subinfo();
		info[4][16].setBuisnessDetails(true);
		info[4][16].setRequiredDetails(true);
		info[4][17] = new Subinfo();
		info[4][17].setBuisnessDetails(true);
		info[4][17].setRequiredDetails(true);

		info[5][2] = new Subinfo();
		info[5][2].setBuisnessDetails(true);
		info[5][2].setRequiredDetails(true);
		info[5][5] = new Subinfo();
		info[5][5].setBuisnessDetails(true);
		info[5][5].setRequiredDetails(true);

		info[6][1] = new Subinfo();
		info[6][1].setBuisnessDetails(true);
		info[6][1].setRequiredDetails(true);

		info[6][2] = new Subinfo();
		info[6][2].setBuisnessDetails(true);
		info[6][2].setRequiredDetails(true);

		info[6][3] = new Subinfo();
		info[6][3].setBuisnessDetails(true);
		info[6][3].setRequiredDetails(true);

		info[6][7] = new Subinfo();
		info[6][7].setBuisnessDetails(true);
		info[6][7].setRequiredDetails(true);
		info[6][8] = new Subinfo();
		info[6][8].setBuisnessDetails(true);
		info[6][8].setRequiredDetails(true);
		info[6][9] = new Subinfo();
		info[6][9].setBuisnessDetails(true);
		info[6][9].setRequiredDetails(true);
		info[6][10] = new Subinfo();
		info[6][10].setBuisnessDetails(true);
		info[6][10].setRequiredDetails(true);
		info[6][11] = new Subinfo();
		info[6][11].setBuisnessDetails(true);
		info[6][11].setRequiredDetails(true);
		info[6][13] = new Subinfo();
		info[6][13].setBuisnessDetails(true);
		info[6][13].setRequiredDetails(true);

		info[7][1] = new Subinfo();
		info[7][1].setBuisnessDetails(true);
		info[7][1].setRequiredDetails(true);

		info[7][2] = new Subinfo();
		info[7][2].setBuisnessDetails(true);
		info[7][2].setRequiredDetails(true);

		info[8][1] = new Subinfo();
		info[8][1].setBuisnessDetails(true);
		info[8][1].setRequiredDetails(true);

		info[8][2] = new Subinfo();
		info[8][2].setBuisnessDetails(true);
		info[8][2].setRequiredDetails(true);

		info[8][3] = new Subinfo();
		info[8][3].setBuisnessDetails(true);
		info[8][3].setRequiredDetails(true);

		info[8][4] = new Subinfo();
		info[8][4].setBuisnessDetails(true);
		info[8][4].setRequiredDetails(true);

		info[8][5] = new Subinfo();
		info[8][5].setBuisnessDetails(true);
		info[8][5].setRequiredDetails(true);

		info[8][6] = new Subinfo();
		info[8][6].setBuisnessDetails(true);
		info[8][6].setRequiredDetails(true);

		info[8][7] = new Subinfo();
		info[8][7].setBuisnessDetails(true);
		info[8][7].setRequiredDetails(true);

		info[8][8] = new Subinfo();
		info[8][8].setBuisnessDetails(true);
		info[8][8].setRequiredDetails(true);

		info[8][9] = new Subinfo();
		info[8][9].setBuisnessDetails(true);
		info[8][9].setRequiredDetails(true);

		info[8][10] = new Subinfo();
		info[8][10].setBuisnessDetails(true);
		info[8][10].setRequiredDetails(true);

		info[8][11] = new Subinfo();
		info[8][11].setBuisnessDetails(true);
		info[8][11].setRequiredDetails(true);

		info[8][12] = new Subinfo();
		info[8][12].setBuisnessDetails(true);
		info[8][12].setRequiredDetails(true);

		info[8][13] = new Subinfo();
		info[8][13].setBuisnessDetails(true);
		info[8][13].setRequiredDetails(true);

		info[8][23] = new Subinfo();
		info[8][23].setBuisnessDetails(true);
		info[8][23].setRequiredDetails(true);

		return info;

	}

	Map<Integer, String> getCat() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "Database Issue/Access Requests");
		map.put(2, "Secuirty Incidents");
		map.put(3, "Infrastructure Incidents/Requests");
		map.put(4, "Network-VOIP Incidents/Requests");
		map.put(5, "Server Infra Incidents/Requests");
		map.put(6, "Software/Applications-Incidents/Requests");
		map.put(7, "New Access Requests");
		map.put(8, "Hardware Issues/New Hardware Requests");
		return map;
	}

	Map<Integer, Map<Integer, String>> getSubCategory(int id) {
		Map<Integer, Map<Integer, String>> subMap = new HashMap<Integer, Map<Integer, String>>();
		Map<Integer, String> database_Issue = new HashMap<Integer, String>();
		database_Issue.put(1, "Create/Alter/Drop Database");
		database_Issue.put(2, "Database Restore Request");
		database_Issue.put(3, "Database Access Request");
		database_Issue.put(4, "Script Execution Request");
		database_Issue.put(5, "Create new User/Login Request");
		database_Issue.put(6, "Installation Request");
		database_Issue.put(7, "Others");
		database_Issue.put(8, "Database Access Problem");
		database_Issue.put(9, "Database Migration/Upgrade");
		subMap.put(1, database_Issue);

		Map<Integer, String> secuirty_Incidents = new HashMap<Integer, String>();
		secuirty_Incidents.put(1, "Virus Threat");
		secuirty_Incidents.put(2, "Server Alerts");
		secuirty_Incidents.put(3, "Network Alerts");
		secuirty_Incidents.put(4, "Camera Alerts");
		secuirty_Incidents.put(5, "OSSEC Monitoring Alerts");
		subMap.put(2, secuirty_Incidents);

		Map<Integer, String> infrastructure_Incidents = new HashMap<Integer, String>();
		infrastructure_Incidents.put(1, "Webcam Interview Setup");
		infrastructure_Incidents.put(2, "Conference/Meeting Room Setup");
		infrastructure_Incidents.put(3, "Printer/Scanner not working");
		infrastructure_Incidents.put(4, "Printer cartridge re-fill");
		infrastructure_Incidents.put(5, "Printer Installation/Configuration");
		subMap.put(3, infrastructure_Incidents);

		Map<Integer, String> network_Incidents = new HashMap<Integer, String>();
		network_Incidents.put(1, "Cisco Phone not Working");
		network_Incidents.put(2, "Cisco Phone Movement Request");
		network_Incidents.put(3, "Internet Browser Problem");
		network_Incidents.put(4, "Internet Connectivity Problem");
		network_Incidents.put(5, "New Cisco Phone Request");
		network_Incidents.put(6, "VLAN change Request");
		network_Incidents.put(7, "New VPN Access Request");
		network_Incidents.put(8, "VPN Problem");
		network_Incidents.put(9, "Phone/Cisco mapping Request");
		network_Incidents.put(10, "Dual Port Configuration Request");
		network_Incidents.put(11, "Wi-fi Request");
		network_Incidents.put(12, "Bridge Creation Request");
		network_Incidents.put(13, "Bridge Problem");
		network_Incidents.put(14, "VM Disconnection/Slowness Problem");
		network_Incidents.put(15, "VOIP server Problem");
		network_Incidents.put(16, "MPLS link Problem");
		network_Incidents.put(17, "Network Connectivity Outage");
		network_Incidents.put(18, "Website Access Request");
		network_Incidents.put(19, "IP Natting Request");
		network_Incidents.put(20, "New IPs/URLs to be allowed");
		network_Incidents.put(21, "MAC bind Request");
		network_Incidents.put(22, "Network Other");
		subMap.put(4, network_Incidents);

		Map<Integer, String> server_Incidents = new HashMap<Integer, String>();
		server_Incidents.put(1, "MAC bind/Remove Request");
		server_Incidents.put(2, "SAN controller Problem");
		server_Incidents.put(3, "SAN Hardware Problem");
		server_Incidents.put(4, "New Virtual Machine request");
		server_Incidents.put(5, "Server Hardware Malfunction");
		server_Incidents.put(6, "Server Hardware Request");
		server_Incidents.put(7, "Backup Add/Remove Request");
		subMap.put(5, server_Incidents);

		Map<Integer, String> software_Incidents = new HashMap<Integer, String>();
		software_Incidents.put(1, "Lync/Skype for Business Problem");
		software_Incidents.put(2, "New Software Installation Request");
		software_Incidents.put(3, "Software Un-installation Request");
		software_Incidents.put(4, "Software/Application not working");
		software_Incidents.put(5, "New Software procurement Request");
		software_Incidents.put(6, "New CVS Access");
		software_Incidents.put(7, "CVS not Working");
		software_Incidents.put(8,
				"Intranet Web Applications(XAP,LMS.HRMS, etc.) Problem");
		software_Incidents.put(9, "Outlook/Email/PST Problem");
		software_Incidents.put(10, "Share Point not Working");
		software_Incidents.put(11, "URL not Working");
		software_Incidents.put(12, "Share Point Access Request");
		software_Incidents.put(13, "FTP Problem");
		subMap.put(6, software_Incidents);

		Map<Integer, String> new_Incidents = new HashMap<Integer, String>();
		new_Incidents.put(1, "Welcome Letter new Account Creation");
		new_Incidents.put(2, "Account Deletion Request");
		new_Incidents.put(3, "DL-Create/Modify/Remove Request");
		new_Incidents.put(4, "Mail Size Quota/limit to be increased");
		new_Incidents.put(5, "Attachment Limit to be increased");
		new_Incidents.put(6, "Password Reset Request");
		new_Incidents.put(7, "RDP Access Request");
		new_Incidents.put(8, "OWA/Outlook Anywhere Acess");
		new_Incidents.put(9, "External FTP Acess Request");
		new_Incidents.put(10, "External Mail Sending Access Request");
		new_Incidents.put(11, "Local Admin Rights Access Request");
		new_Incidents.put(12, "USB Access Request");
		new_Incidents.put(13, "Other Requests");
		subMap.put(7, new_Incidents);

		Map<Integer, String> hardware_Incidents = new HashMap<Integer, String>();
		hardware_Incidents.put(1, "Booting Problem");
		hardware_Incidents.put(2, "System Login Problem");
		hardware_Incidents.put(3, "System Slowness Problem");
		hardware_Incidents.put(4, "RAM UP-gradation Request");
		hardware_Incidents.put(5, "System to be formatted");
		hardware_Incidents.put(6, "User Seat/System Movement Request");
		hardware_Incidents.put(7, "System backup/restoration Request");
		hardware_Incidents.put(8, "keyboard Problem");
		hardware_Incidents.put(9, "Mouse Problem");
		hardware_Incidents.put(10, "Monitor/Display Problem");
		hardware_Incidents.put(11, "Mobile device Problem");
		hardware_Incidents.put(12, "USB detection/Data card Problem");
		hardware_Incidents.put(13, "New Desktop/Laptop Request fro New Joinee");
		hardware_Incidents.put(14, "New Desktop Request");
		hardware_Incidents.put(15, "New Laptop Request");
		hardware_Incidents.put(16, "New Headphone Request");
		hardware_Incidents.put(17, "Data Card Request");
		hardware_Incidents.put(18, "New Mobile phone/SIM card Request");
		hardware_Incidents.put(19, "Laptop/Phone charger Problem");
		subMap.put(8, hardware_Incidents);

		return subMap;

	}

}
