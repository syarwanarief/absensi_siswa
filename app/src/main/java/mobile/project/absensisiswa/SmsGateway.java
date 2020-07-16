package mobile.project.absensisiswa;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import android.telephony.SmsManager;


public class SmsGateway {

    private ServerSocket serverSocket;

    /**
     * Digunakana untuk menjalankan server sms gateway
     *
     * @throws IOException
     *             jika terjadi kesalahan ketika menjalankan server
     */
    public void start() throws IOException {

        // buat socket server yang berjalan pada port 8989
        serverSocket = new ServerSocket(8989);

        // lakukan perulangan terus menerus sampai socket server ditutup
        while (serverSocket.isClosed()) {

            // terima client
            Socket client = serverSocket.accept();

            // terima input client
            InputStream inputStream = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    inputStream));

            // input pertama telpon
            String telpon = reader.readLine();
            // input kedua pesan yg akan dikirim
            String pesan = reader.readLine();

            // kirim pesan sms ke nomor telpon tujuan
            SmsManager.getDefault().sendTextMessage(telpon, null, pesan, null,
                    null);

            // tutup client
            client.close();
        }

    }

    /**
     * Digunakan jika selesai
     *
     * @throws IOException
     *             jika terjadi kesalahan ketika menutup koneksi server
     */
    public void stop() throws IOException {
        // tutup server socket
        serverSocket.close();
    }
}
