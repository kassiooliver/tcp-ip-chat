
package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class Cliente extends Thread { // parte que controla a recepção de mensagens do cliente

    private Socket conexao;  // construtor que recebe o socket do cliente
   
            public Cliente(Socket socket) {
            this.conexao = socket;
    }
    public static void main(String args[])
    {                   
        
        int p =0;
        Scanner input = new Scanner(System.in);
        Scanner endIp = new Scanner(System.in);
        String StringEndIp="";
        System.out.println("Qual o endereco ip do servidor? ");
        StringEndIp = endIp.nextLine();
        System.out.println("A qual sala vc quer se conectar?");
        p = input.nextInt();
        if (p == 1) {                
        
        try {
                        
            Socket socket = new Socket(StringEndIp, 5551);
            String mensagem;
            
            PrintStream out = new PrintStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Digite seu nome: ");
            String meuNome = in.readLine();
            out.println(meuNome.toUpperCase());
            
            Thread thread = new Cliente(socket);
            thread.start();
            
            
            while (true)
            {
                System.out.print("Responda: ");
                mensagem = in.readLine();
                out.println(mensagem);
            }
          } catch (IOException ex) {
            System.out.println("Nao conectou!" + " IOException: " + ex);
          }
          }
                        if (p == 2) {                
        
        try {
            Socket socket = new Socket(StringEndIp, 5552);
            String mensagem;
            
            PrintStream out = new PrintStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Digite seu nome: ");
            String meuNome = in.readLine();
            out.println(meuNome.toUpperCase());
            
            Thread thread = new Cliente(socket);
            thread.start();
            
            
            while (true)
            {
                System.out.print("Responda: ");
                mensagem = in.readLine();
                out.println(mensagem);
            }
            
          } catch (IOException ex) {
            System.out.println("Nao conectou!" + " IOException: " + ex);
          }
          }
                        if (p == 3) {                
        
        try {
           Socket socket = new Socket(StringEndIp, 5553);
            String mensagem;
            
            PrintStream out = new PrintStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Digite seu nome: ");
            String meuNome = in.readLine();
            out.println(meuNome.toUpperCase());
            
            Thread thread = new Cliente(socket);
            thread.start();
            
            
            while (true)
            {
                System.out.print("Responda: ");
                mensagem = in.readLine();
                out.println(mensagem);
            }
            
          } catch (IOException ex) {
            System.out.println("Nao conectou!" + " IOException: " + ex);
          }
          }    
                        

                        
                        
    }                  
                        
        
    
  // execução da thread
    public void run()
    {
        try {
            String mensagem;
            BufferedReader entrada = new BufferedReader(new InputStreamReader(this.conexao.getInputStream()));
            while (true)
            {
                mensagem = entrada.readLine();
                if (mensagem == null) {
                    System.out.println("Fim de conexao!");
                    System.exit(0);
                }
                System.out.println();
                System.out.println(mensagem);
                System.out.print("Responder: ");
            }
        } catch (IOException ex) {
            // caso ocorra alguma exceção de E/S, mostra qual foi.
            System.out.println("Erro" + 
				" IOException: " + ex);
        }
    }

}
