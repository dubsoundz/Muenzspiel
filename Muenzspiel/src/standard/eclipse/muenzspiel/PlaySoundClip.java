package standard.eclipse.muenzspiel;


import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlaySoundClip {

	
 Clip clip = null;
	
	public PlaySoundClip() throws UnsupportedAudioFileException, IOException {
		// TODO Auto-generated constructor stub
		   // specify the sound to play
	    // (assuming the sound can be played by the audio system)

		File soundFile = new File("Sounds/analog.wav");
	    AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);

	    // load the sound into memory (a Clip)
	    DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
	   
		try {
			clip = (Clip) AudioSystem.getLine(info);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			clip.open(sound);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void startClip(){
		clip.start();
	}
	
	

	
	
	
	
	
}
