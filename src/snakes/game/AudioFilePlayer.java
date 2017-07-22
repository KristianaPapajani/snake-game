package snakes.game;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class AudioFilePlayer {
	private Thread music;
    public AudioFilePlayer() {
    	runner();
    }
    public void runner(){
    	Runnable play = new Runnable(){
    		
			@Override
			public void run() {
		    	try{
		    		File file = new File("jb.wav");
		    		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
		    		AudioFormat format = audioInputStream.getFormat();
		    		Clip clip = AudioSystem.getClip();
		    		clip.open(audioInputStream);
		    		clip.start();
		    		long audioFileLength = file.length();
		    		int frameSize = format.getFrameSize();
		    		float frameRate = format.getFrameRate();
		    		float durationInSeconds = (audioFileLength / (frameSize * frameRate));
		    		Thread.sleep((long) (durationInSeconds * 1000));	
		    	}
		    	catch( Exception e){
		    		System.out.println(e);
		    	}
			}
    	};
    	music = new Thread(play);
    	music.start();
    }
    public void pause() throws InterruptedException{
    	music.wait();
    }
    public void resume(){
    	music.notify();
    }
}