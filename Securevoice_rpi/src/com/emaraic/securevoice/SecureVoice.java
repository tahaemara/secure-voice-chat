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

import javax.sound.sampled.*;

public class SecureVoice {

    public static void main(String[] args) {

        Receiver rx = new Receiver();
        rx.start();
        
        Transmitter tx = new Transmitter();
        tx.start();

    }

    public static AudioFormat getAudioFormat() { //you may change these parameters to fit you mic
        float sampleRate = 11025.0F;  //8000,11025,16000,22050,44100
        int sampleSizeInBits = 16;    //8,16
        int channels = 1;             //1,2
        boolean signed = true;        //true,false
        boolean bigEndian = false;    //true,false
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }
    
}
