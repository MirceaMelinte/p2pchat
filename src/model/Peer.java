package model;

import java.io.Serializable;

import common.IPeer;

public class Peer implements Serializable
{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private String alias;
   private IPeer proxy;
   
   
   public Peer( String alias, IPeer proxy )
   {
      this.alias = alias;
      this.proxy = proxy;
   }
   
   
   public String getAlias()
   {
      return alias;
   }
   
   
   public IPeer getProxy()
   {
      return proxy;
   }
}
