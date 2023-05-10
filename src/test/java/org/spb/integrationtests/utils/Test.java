package org.spb.integrationtests.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Test {
  public static void main(String[] args) {
//      LinkedHashMap<String, String> values = new LinkedHashMap<>();
//      Date date = new Date();
//      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
//      String currentDateTime = format.format(date);
//      values.put("group_code","IN111");
//      values.put("group_name","IN Group Name");
//      values.put("description", "Group for integration testing");
//      values.put("active","0");
//      values.put("deleted","0");
//      values.put("created_by","Integration Test");
//      values.put("updated_by","Integration Test");
//      values.put("created_on",(currentDateTime));
//      values.put("updated_on",(currentDateTime));
//      DBUtilities.insertIntoTable("org_group",values);
//      DBUtilities.deleteFromTable("org_group","group_code","IN111");
      HashMap<String, String> uniqueRowInTable = DBUtilities.findUniqueRowInTable("org_group", "group_code", "Group1");
    uniqueRowInTable.forEach(
        (k, v) -> {
          System.out.println(k+":" + v);
        });
  }
}
