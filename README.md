#End to End Voice Encryption between Raspberry pi and PC using AES

Requirements:

1- PC or Laptop has java installed in it.

2- Raspberry pi run Raspbian(already Java is installed in it).

3-External speaker and usb sound card for raspberry pi.



Steps:

1- Download the [zip file](https://github.com/emara-geek/secure-voice-chat/archive/master.zip)
which contains two netbeans project one for Rapberry Pi "Securevoice_rpi" and the other "Securevoice" for PC 
 
2- Compile them.

3- Run each jar in its environment and enjoy secure chat.

System description: 

The system consists of two terminal PC and Raspberry PI, Of course they can be two PCs instead of Raspberry Pi.

Java provides us a way to capture sound data (Digital) from mic through TargetDataLine interface. Captured data is saved to byte buffer array and then make the encryption process on this array using AES algorithm and then it is sent through UDP socket to receiver. In receiver side the packet is received and converted from encrypted form and sent the voice data to speaker.
Sending and receiving process done simultaneously(Full Duplex) and each terminal has parameters (IP and port) as receiver and as transmitter so pay attention when you assign values of IPs and ports as show in the code.
Every terminal works as a receiver(RX) server and also as a transmitter(TX) client .

![alt tag](https://s32.postimg.org/nqjhmr911/Screen_Shot_2016_08_04_at_12_03_35_PM.png)


Test video:

[![Video](http://i.makeagif.com/media/8-04-2016/zOrEso.gif)](https://www.youtube.com/watch?v=LjOTGWgMYy0)



