package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.Peer;

public interface IAddressServer extends Remote
{
   void registerPeer(Peer peer)
      throws RemoteException;
   
   void removePeer(Peer peer)
      throws RemoteException;
   
   Peer findPeer(String alias)
      throws RemoteException;
}
