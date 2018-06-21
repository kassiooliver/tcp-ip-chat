package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

public class Servidor extends Thread {
  
    private static Vector cliente;
    private Socket conexao;
    private String apelido;
    private static List lista = new ArrayList();
 
    public Servidor(Socket socket) {
        this.conexao = socket;
    }
  
    public boolean armazena(String newName){
       for (int i=0; i < lista.size(); i++){
         if(lista.get(i).equals(newName))
           return true;
       }
       lista.add(newName);
       return false;
    }
    public void remove(String oldName) {
       for (int i=0; i< lista.size(); i++){
         if(lista.get(i).equals(oldName))
           lista.remove(oldName);
       }
    }
    public static void main(String args[]) {
        cliente = new Vector();
        try {
            ServerSocket server1 = new ServerSocket(5551);

            System.out.println("Servidor rodando na porta 5551");
 
       
            while (true) {
                Socket conexao1 = server1.accept();
                Servidor s1 = new Servidor(conexao1);
                Thread a = new Thread(s1);
                a.start();
               
          
            }
        } catch (IOException ex) {
            System.out.println("IOException: " + ex);
        }
    }
    public void run()
    {
        try {
            
    BufferedReader entrada = new BufferedReader(new InputStreamReader(this.conexao.getInputStream()));
    PrintStream saida = new PrintStream(this.conexao.getOutputStream());
            this.apelido = entrada.readLine();
            if (armazena(this.apelido)){
              saida.println("Nao pode usar esse nome,utilize outro");
              cliente.add(saida);
              this.conexao.close();
              return;
            } else {
               System.out.println(this.apelido + " : Conectado!");
            }
            if (this.apelido == null) {
                return;
            }
            cliente.add(saida);
            String mensagem = entrada.readLine();
            while (mensagem != null && !(mensagem.trim().equals("Sair!")))
            {
                multicast(saida, " escreveu: ", mensagem);
                mensagem = entrada.readLine();
            }
            System.out.println(this.apelido + " saiu do bate-papo!");
            multicast(saida, " saiu", " do bate-papo!");
            remove(this.apelido);
            cliente.remove(saida);
            this.conexao.close();
        } catch (IOException e) {
            System.out.println("Erro"+" IOException: " + e);
        }
    }
   
    public void multicast(PrintStream saida, String status, String mensagem) throws IOException {
        Enumeration e = cliente.elements();
        while (e.hasMoreElements()) {
            PrintStream chat = (PrintStream) e.nextElement();
            if (chat != saida) {
                chat.println(this.apelido + status + mensagem);
            }
        }
      }
}