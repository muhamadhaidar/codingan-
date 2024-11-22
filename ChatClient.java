import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public ChatClient(String serverAddress, int port) {
        try {
            socket = new Socket(serverAddress, port);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            new Thread(new ReceiveMessage()).start();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Masukkan pesan Anda (ketik 'keluar' untuk mengakhiri):");
            while (true) {
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase("keluar")) {
                    break;
                }
                output.println(message);
            }

            close();
        } catch (IOException e) {
            System.err.println("Kesalahan saat menghubungkan ke server: " + e.getMessage());
        }
    }

    private void close() {
        try {
            if (socket != null) socket.close();
            if (input != null) input.close();
            if (output != null) output.close();
            System.out.println("Koneksi ditutup.");
        } catch (IOException e) {
            System.err.println("Gagal menutup koneksi: " + e.getMessage());
        }
    }

    private class ReceiveMessage implements Runnable {
        public void run() {
            try {
                String message;
                while ((message = input.readLine()) != null) {
                    System.out.println("Server: " + message);
                }
            } catch (IOException e) {
                System.err.println("Koneksi ke server terputus: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 12345;
        new ChatClient(serverAddress, port);
    }
}