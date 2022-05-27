package com.nft.parse.jsoupParse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class IPFSParser {

	static Logger logger = LogManager.getLogger(IPFSParser.class);

	public static final String IPFS_PREFIX = "ipfs://";
	public static final String CONTENT_TAG = "content";
	public static final String TABLE_TAG = "table-responsive";
	public static final String IPFS_HASH_TAG = "ipfs-hash";
	public static final String HREF_TAG = "href";

	public static List<String> getFiles(String url) throws Exception {

		try {
			List<String> ipfsCidFromFolder = new ArrayList<>();
			Document doc = Jsoup.connect(url).get();
			Elements elems = doc.body().getElementById(CONTENT_TAG).getElementsByClass(TABLE_TAG).get(0)
					.getElementsByClass(IPFS_HASH_TAG);
			elems.forEach(ele -> {
				// System.out.println(ele.attr("href"));
				String ipfsCid = ele.attr(HREF_TAG).substring(6);
				ipfsCidFromFolder.add(IPFS_PREFIX + ipfsCid);
			});

			return ipfsCidFromFolder;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

}
