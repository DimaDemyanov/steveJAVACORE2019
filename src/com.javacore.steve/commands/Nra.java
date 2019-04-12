package commands;

import commandsUtils.Command;
import main.Steve;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import static javax.sound.midi.ShortMessage.NOTE_ON;

public class Nra extends Command {

    public Nra() {
        options.addRequiredOption("f", "file", true, "Path to midi file");
    }

    private static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    @Override
    public void before(Semaphore semaphore) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void perform(Steve steve, String[] options, Semaphore semaphore) throws ParseException {
        perform(steve, options);
        semaphore.release();
    }


    @Override
    public void perform(Steve steve, String[] options) throws ParseException {
        CommandLine commandLine = parser.parse(this.options, options);
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            Sequence sequence = MidiSystem.getSequence(new File(commandLine.getOptionValue('f')));
            sequencer.setSequence(sequence);
            sequencer.start();
            printNotes(sequence);
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.println("Type \"stop\" to stop the music");
            }while (!scanner.nextLine().equals("stop"));
            sequencer.stop();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Can't open specified midi file");
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void printNotes(Sequence sequence) {
        for (Track track : sequence.getTracks()) {
            for (int i = 0; i < track.size(); i++) {
                MidiEvent event = track.get(i);
                MidiMessage message = event.getMessage();
                if (message instanceof ShortMessage) {
                    ShortMessage sm = (ShortMessage) message;
                    if (sm.getCommand() == NOTE_ON) {
                        int key = sm.getData1();
                        int note = key % 12;
                        String noteName = NOTE_NAMES[note];
                        System.out.print(noteName);
                    }
                }
            }

            System.out.println();
        }
    }

    @Override
    public String getName() {
        return "nra";
    }
}
