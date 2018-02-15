package server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import model.Peer;
import common.IAddressServer;

public class AddressServer extends UnicastRemoteObject implements IAddressServer
{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private ArrayList<Peer> clients;

   protected AddressServer() throws RemoteException
   {
      super();
      clients = new ArrayList<Peer>();
      
      try {
         LocateRegistry.createRegistry(1099);
         Naming.rebind("chat", this);
         
    	 AddressServerView window = new AddressServerView();
    	 window.frmAddressServer.setVisible(true);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @Override
   public void registerPeer(Peer peer) throws RemoteException
   {
      clients.add(peer);
   }

   @Override
   public void removePeer(Peer peer) throws RemoteException
   {
      clients.remove(peer);
   }

   @Override
   public Peer findPeer(String alias) throws RemoteException
   {
      for (int i = 0; i < clients.size(); i++)
      {
         if(clients.get(i).getAlias().equals(alias))
         {
            return clients.get(i);
         }
      }
      return null;
   }
   
   public static void main(String[] args)
   {
      try {
         new AddressServer();
      } catch (RemoteException e) {
         e.printStackTrace();
      }
   }

}
