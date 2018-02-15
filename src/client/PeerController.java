package client;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

import model.Message;
import model.Peer;
import common.IAddressServer;
import common.IPeer;

public class PeerController extends UnicastRemoteObject implements IPeer {
   private PeerView view;
   private String alias;
   private IAddressServer server;
   private Peer thisPeer;

   private static final long serialVersionUID = 1L;

   protected PeerController(String alias) throws RemoteException
   {
      super();
      this.alias = alias;
      thisPeer = new Peer(alias, this);
      
      try {
         String ip = InetAddress.getLocalHost().getHostAddress();
         String URL = "rmi://" + ip + "/" + "chat";
         
         server = (IAddressServer) Naming.lookup( URL );
         
         server.registerPeer(thisPeer);
         
      } catch( Exception ex ) {
         ex.printStackTrace();
         JOptionPane.showMessageDialog(null, "Trouble connecting to server");
         
         System.exit(1);
      }
   }

   public void printMessage(String message) throws RemoteException
   {
	   System.out.println(message);
   }

   public String getAlias() throws RemoteException
   {
	   return alias;
   }
   
   public IAddressServer getServer()
   {
	   return server;
   }

   public PeerView getView() 
   {
	   return view;
   }
	
   public void setView(PeerView view) 
   {
	   this.view = view;
   }
   
   public Peer getPeer()
   {
      return thisPeer;
   }
   
   public void send( String toAlias, String text )
   {
         Peer receiver;
         try
         {
            receiver = findReceiver( toAlias );
            Message msg = new Message( text, thisPeer, receiver );
            
            receiver.getProxy().sendMessage( msg ); 
         }
         catch (RemoteException e)
         {
            e.printStackTrace();
         }

     
   }
   
   private Peer findReceiver( String alias )
      throws RemoteException
   {
         return server.findPeer( alias );       
   }

   @Override
   public void sendMessage(Message message) throws RemoteException
   {
         view.printMessage(message.getSender().getAlias() + ": " + message.getMessage() );
   }
}
