package cn.com.util;

import java.util.List;

import cn.com.model.Point;
import junit.framework.TestCase;

public class TextUtilTest extends TestCase {

	public void testGetAllInformation() {
		List<Point> list = TextUtil.getAllInformation("data/dataset1.dat");
		this.assertNotNull(list);
	}

}
