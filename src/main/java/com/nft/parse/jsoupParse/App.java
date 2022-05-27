package com.nft.parse.jsoupParse;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

	static Logger logger = LogManager.getLogger(App.class);

	public static void main(String[] args) throws Exception {
		try {
			String ipfsDir = args[0];
			String excelTemplate = args[1];
			logger.info("Connecting to IPFS for retrieving file details.....");

			List<String> ipfsCidList = IPFSParser.getFiles(ipfsDir);
			ExcelWriter.readAndUpdateExcel(ipfsCidList, excelTemplate);
			logger.info("IPFS file list fetched and mapped to excel");
		} catch (Exception e) {
			logger.error("Error occurred while generating excel", e);
		}
	}
}
