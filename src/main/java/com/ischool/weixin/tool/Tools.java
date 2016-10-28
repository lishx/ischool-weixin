package com.ischool.weixin.tool;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class Tools {
	public static final String inputStream2String(InputStream in)
			throws UnsupportedEncodingException, IOException {
		if (in == null) {
			return "";
		}
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		int n;
		while ((n = in.read(b)) != -1) {
			out.append(new String(b, 0, n, "UTF-8"));
		}
		return out.toString();
	}

	public static final boolean checkSignature(String token, String signature,
			String timestamp, String nonce) {
		List<String> params = new ArrayList<String>();
		params.add(token);
		params.add(timestamp);
		params.add(nonce);
		Collections.sort(params, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		String temp = (String) params.get(0) + (String) params.get(1)
				+ (String) params.get(2);
		return SHA1.encode(temp).equals(signature);
	}
}