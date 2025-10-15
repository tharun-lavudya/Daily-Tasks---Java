package com.test;

import org.junit.Assert;
import org.junit.Test;

public class TestApp {
   @Test
   public void testAdd() {
	   TestApplication app = new TestApplication();
       Assert.assertEquals(5, app.add(2, 3));
   }
}