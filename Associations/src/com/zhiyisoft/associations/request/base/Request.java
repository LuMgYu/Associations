package com.zhiyisoft.associations.request.base;
/***********************************************************************
 * Module:  Request.java
 * Author:  Administrator
 * Purpose: Defines the Class Request
 ***********************************************************************/

import java.util.*;

/** @pdOid 01da130e-726f-4fd8-89cf-2713e63a0e17 */
public abstract class Request {
   /** @pdOid 7dcfcc4b-3292-4fbf-99cb-b7642d9f049f */
   private java.lang.String mHostUrl;
   
   /** @param name 
    * @param value
    * @pdOid de4017f6-1365-4e75-b9a9-4deb1c2bf91b */
   public abstract Request appendParam(String name, Object value);
   /** @pdOid 96bc442a-4f7e-431e-9001-2eb459c9dfd2 */
   public abstract java.lang.String combineTheCompleteUrl();
   /** @pdOid d73dccef-a60c-424d-837d-26a59821cd00 */
   public java.lang.Object run() {
      // TODO: implement
      return null;
   }
   
   /** @pdOid 2d9286ec-6add-4743-8d80-1d74543c38ea */
   public void filterTheUrl() {
      // TODO: implement
   }
   
   /** @pdOid 6c53134c-1622-4b2d-a23f-516e4960af35 */
   public int getTheHostUrl() {
      // TODO: implement
      return 0;
   }

}