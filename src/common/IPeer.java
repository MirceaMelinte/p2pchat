package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.Message;

public interface IPeer extends Remote
{
   void sendMessage(Message message) 
         throws RemoteException;
}
