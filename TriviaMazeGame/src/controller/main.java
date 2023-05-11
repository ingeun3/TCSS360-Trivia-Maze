package controller;

import view.GameInterface;

import javax.swing.*;
import java.awt.*;

public class
main {
    /* private constructor to inhibit instantiation. */
    private main() {
        // do not instantiate objects of this class
        throw new IllegalStateException();
    }

    /**
     * Set the look and feel for the GUI program.
     */
    private static void setLookAndFeel() {

        try {

            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

        } catch (final UnsupportedLookAndFeelException e) {
            System.out.println("UnsupportedLookAndFeelException");
        } catch (final ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        } catch (final InstantiationException e) {
            System.out.println("InstantiationException");
        } catch (final IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        }

    }

    /**
     * The start point for the PowerPaint application.
     *
     * @param theArgs command line arguments - ignored in this program
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setLookAndFeel();
                new GameInterface(1, 10).start();
            }
        });
    }
}
