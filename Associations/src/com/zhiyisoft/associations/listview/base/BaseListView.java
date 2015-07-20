package com.zhiyisoft.associations.listview.base;
/***********************************************************************
 * Module:  BaseListView.java
 * Author:  Administrator
 * Purpose: Defines the Class BaseListView
 ***********************************************************************/

import java.util.ArrayList;

import com.zhiyisoft.associations.activity.base.BaseActivity;

/** @pdOid 49d81cae-38a8-4361-b177-b138895440b0 */
public class BaseListView {
   /** @pdOid deaa8df8-b12d-475d-931b-dfdfc373aea7 */
   private ArrayList mList;
   /** @pdOid 07f2f746-f37b-4a92-8d83-e26b77b94ee3 */
//   private DragDown mDragDown;
   /** @pdOid fae0097b-05e5-4f8f-92b2-a81375cfe6ee */
   private BaseActivity mBaseActivity;
   
   /** @pdRoleInfo migr=no name=BaseListView assc=association1 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<BaseListView> baseListViewB;
   
   /** @pdOid 2de1b48f-723d-45b7-b1ac-fdf82082d593 */
   public void initSet() {
      // TODO: implement
   }
   
   /** @pdOid 9e3fada0-c1f2-474b-91ad-d1a8a4fe5fa1 */
   public int setAdapter() {
      // TODO: implement
      return 0;
   }
   
   /** @pdOid febfc047-00c8-4ec2-a350-4c709a97ee89 */
   public void setHeaderView() {
      // TODO: implement
   }
   
   /** @pdOid 76d6fe17-de29-4233-afb8-63fba65cb5cb */
   public void setFooterView() {
      // TODO: implement
   }
   
   /** @pdOid 5e20f44d-9216-4808-a64b-fc8d0832dcd3 */
//   public void onClick();
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<BaseListView> getBaseListViewB() {
      if (baseListViewB == null)
         baseListViewB = new java.util.HashSet<BaseListView>();
      return baseListViewB;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorBaseListViewB() {
      if (baseListViewB == null)
         baseListViewB = new java.util.HashSet<BaseListView>();
      return baseListViewB.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newBaseListViewB */
   public void setBaseListViewB(java.util.Collection<BaseListView> newBaseListViewB) {
      removeAllBaseListViewB();
      for (java.util.Iterator iter = newBaseListViewB.iterator(); iter.hasNext();)
         addBaseListViewB((BaseListView)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newBaseListView */
   public void addBaseListViewB(BaseListView newBaseListView) {
      if (newBaseListView == null)
         return;
      if (this.baseListViewB == null)
         this.baseListViewB = new java.util.HashSet<BaseListView>();
      if (!this.baseListViewB.contains(newBaseListView))
         this.baseListViewB.add(newBaseListView);
   }
   
   /** @pdGenerated default remove
     * @param oldBaseListView */
   public void removeBaseListViewB(BaseListView oldBaseListView) {
      if (oldBaseListView == null)
         return;
      if (this.baseListViewB != null)
         if (this.baseListViewB.contains(oldBaseListView))
            this.baseListViewB.remove(oldBaseListView);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllBaseListViewB() {
      if (baseListViewB != null)
         baseListViewB.clear();
   }

}