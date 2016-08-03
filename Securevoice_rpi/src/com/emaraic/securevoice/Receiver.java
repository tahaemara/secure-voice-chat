package com.emaraic.securevoice;

/**
 *
 * @author Taha Emara 
 * Website: http://www.emaraic.com 
 * Email : taha@emaraic.com
 * Created on: Jul 30, 2016
 */
import com.emaraic.securevoice.utils.AES;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class Receiver extends Thread {
    // these parameters must by used in the Transmitter class of the other client
    private static final String RX_IP = "localhost"; 
    private static final int RX_PORT = 1034;


    @Override
    public void run() {
        byte b[] = null;
        while (true) {
            
                b = rxFromUDP();
                speak(b);
            
        }
    }


    public static byte[] rxFromUDP() {
        try {
            DatagramSocket sock = new DatagramSocket(RX_PORT);
             byte soundpacket[] = new byte[8192];
            DatagramPacket datagram = new DatagramPacket(soundpacket, soundpacket.length, InetAddress.getByName(RX_IP), RX_PORT);
            sock.receive(datagram);
            sock.close();

            return AES.decrypt(datagram.getData(),0,soundpacket.length); // soundpacket ;
            //return soundpacket; //  if you want to hear encrypted form 
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static void speak(byte soundbytes[]) {

        try {
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, SecureVoice.getAudioFormat());
            try (SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo)) {
                sourceDataLine.open(SecureVoice.getAudioFormat());
                sourceDataLine.start();
                sourceDataLine.write(soundbytes, 0, soundbytes.length);
                sourceDataLine.drain();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

   

}
