package com.zhiyisoft.associations.cache.base;
/***********************************************************************
 * Module:  Cache.java
 * Author:  Administrator
 * Purpose: Defines the Interface Cache
 ***********************************************************************/

import java.util.*;

/** @pdOid e2ab1f70-94ef-48da-b351-f8f9d887a91c */
public interface Cache {
   /** @param cacheType
    * @pdOid 05b7c5e9-a22f-4a18-b039-7a76fbd1724b */
   Object getTheData(int cacheType);
   /** @param cacheType
    * @pdOid 0e11a933-60d9-4a07-9eeb-f6361c45d3e3 */
   boolean deleteTheData(int cacheType);
   /** @param list 
    * @param cacheType
    * @pdOid c1b5b327-4513-4a6e-8451-cb958dabc0ab */
   boolean addTheData(ArrayList list, int cacheType);

}