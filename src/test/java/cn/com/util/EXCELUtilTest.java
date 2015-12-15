package cn.com.util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class EXCELUtilTest extends TestCase {

	public void testSaveAsEXCEL() {
		List<Object[]> list = new ArrayList<Object[]>();
		Object[] results = new Object[3];
		results[0] = 12.33;
		results[1] = 123;
		results[2] = 123123.0;
		list.add(results);
		EXCELUtil.saveAsEXCEL(list, 0, "purityResult", 0);
	}

}
