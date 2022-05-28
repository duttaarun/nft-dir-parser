package com.nft.parse.jsoupParse;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.nft.model.IPFSObj;

public class IPFSParser {

	static Logger logger = LogManager.getLogger(IPFSParser.class);

	public static final String IPFS_PREFIX = "ipfs://";
	public static final String CONTENT_TAG = "content";
	public static final String TABLE_TAG = "table-responsive";
	public static final String IPFS_HASH_TAG = "ipfs-hash";
	public static final String HREF_TAG = "href";

	public static List<String> getFiles(String url) throws Exception {

		try {
			List<IPFSObj> ipfsHashObj = new ArrayList<>();
			List<String> ipfsCidFromFolder = new ArrayList<>();
			Document doc = Jsoup.connect(url).get();
			Elements elems = doc.body().getElementById(CONTENT_TAG).getElementsByClass(TABLE_TAG).get(0)
					.getElementsByTag("table").get(0).getAllElements().get(0).getElementsByTag("tr");

			for (int count = 1; count < elems.size(); count++) {
				// System.out.println(ele.attr("href"));
				Element ele = elems.get(count);

				String fileName = ele.getElementsByTag("td").get(1).getElementsByTag("a").get(0).html();
				System.out.println(fileName);
				String ipfsCid = ele.getElementsByTag("td").get(2).getElementsByTag("a").get(0).attr(HREF_TAG)
						.substring(6);
				System.out.println(IPFS_PREFIX + ipfsCid);
				ipfsCidFromFolder.add(IPFS_PREFIX + ipfsCid);

			}

			return ipfsCidFromFolder;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

}
