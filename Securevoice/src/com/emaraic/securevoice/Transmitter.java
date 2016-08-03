
package com.emaraic.securevoice;

import com.emaraic.securevoice.utils.AES;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author Taha Emara
 * Website: http://www.emaraic.com
 * Email  : taha@emaraic.com
 * Created on: Aug 2, 2016 
 */
public class Transmitter extends Thread{
    // these parameters must be copied and used in the Receiver class of the other client
    private static final String TX_IP = "192.168.10.18"; //ip to send to 
    private static final int TX_PORT = 1034;

    @Override
    public void run() {
         Mixer.Info minfo[] = AudioSystem.getMixerInfo();
        for (Mixer.Info minfo1 : minfo) {
            System.out.println(minfo1);
        }

        if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
            try {
                DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, SecureVoice.getAudioFormat());
                TargetDataLine line = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
                line.open(SecureVoice.getAudioFormat());
                line.start();
                byte tempBuffer[] = new byte[line.getBufferSize()];
                System.out.println(tempBuffer.length);
                while (true) {
                    int read = line.read(tempBuffer, 0, tempBuffer.length);
                    byte[] encrypt = AES.encrypt(tempBuffer, 0, read);
                    sendToUDP(encrypt);
                    // sendToUDP(tempBuffer);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }


    }


 

    public static void sendToUDP(byte soundpacket[]) {
        try {
            DatagramSocket sock = new DatagramSocket();
            sock.send(new DatagramPacket(soundpacket, soundpacket.length, InetAddress.getByName(TX_IP), TX_PORT));
            sock.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
