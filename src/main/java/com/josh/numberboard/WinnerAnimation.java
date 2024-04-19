/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josh.numberboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WinnerAnimation extends JPanel implements ActionListener {
    private Timer timer;
    private int explosionSize;
    private int number;
    private boolean exploding;
    private int countdown; // Variable para el temporizador de cuenta regresiva
    private float alpha; // Variable para la transparencia
    private float scale; // Variable para la escala
    private List<Particle> particles;

    public WinnerAnimation() {
        timer = new Timer(10, this);
        exploding = false;
        countdown = 3; // Inicializa el temporizador de cuenta regresiva en 3 segundos
        alpha = 1.0f; // Inicializa la transparencia en 1.0 (completamente opaco)
        scale = 1.0f; // Inicializa la escala en 1.0 (tamaño normal)
        particles = new ArrayList<>();
    }

    public void startCountdown(int numberNums) {
        Timer countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdown--;
                if (countdown == 0) {
                    ((Timer) e.getSource()).stop();
                    startAnimation(numberNums);
                }
                repaint();
            }
        });
        countdownTimer.start();
    }

    public void startAnimation(int numberNums) {
        // Simulando un número ganador aleatorio
        number = (int) (Math.random()*numberNums-1 )+ 0; // Número aleatorio entre 1 y 100

        explosionSize = 0;
        exploding = true;
        timer.start();

        // Crear partículas
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int speed = random.nextInt(10) + 5; // Velocidad de la partícula
            double angle = Math.random() * 2 * Math.PI; // Ángulo aleatorio
            particles.add(new Particle(getWidth() / 2, getHeight() / 2, speed, angle));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        if (exploding) {
            drawExplosion(g2d);
            drawParticles(g2d);
        } else {
            drawWinnerNumber(g2d);
        }
        if (countdown > 0) {
            drawCountdown(g2d);
        }

        g2d.dispose();
    }

    private void drawExplosion(Graphics2D g2d) {
        g2d.setColor(new Color(255, 255, 0, (int) (255 * alpha))); // Color amarillo con transparencia
        int scaledExplosionSize = (int) (explosionSize * scale);
        g2d.fillOval(getWidth() / 2 - scaledExplosionSize / 2, getHeight() / 2 - scaledExplosionSize / 2, scaledExplosionSize, scaledExplosionSize);
    }

    private void drawWinnerNumber(Graphics2D g2d) {
        if (number > 0) {
            g2d.setColor(new Color(0, 0, 0, (int) (255 * alpha))); // Color negro con transparencia
            g2d.setFont(new Font("Arial", Font.BOLD, 48));
            String numberString = Integer.toString(number);
            FontMetrics fm = g2d.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(numberString)) / 2;
            int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
            g2d.drawString(numberString, x, y);
        }
    }

    private void drawCountdown(Graphics2D g2d) {
        g2d.setColor(new Color(255, 0, 0, (int) (255 * alpha))); // Color rojo con transparencia
        g2d.setFont(new Font("Arial", Font.BOLD , 48)); // Tamaño de fuente más grande para el temporizador
        String countdownString = countdown > 0 ? Integer.toString(countdown) : ""; // Evitar mostrar "0" al inicio
        FontMetrics fm = g2d.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(countdownString)) / 2;
        int y = (getHeight() + fm.getAscent()) / 2; // Centrar verticalmente
        g2d.drawString(countdownString, x, y);
    }

    private void drawParticles(Graphics2D g2d) {
        for (Particle particle : particles) {
            g2d.setColor(Color.WHITE);
            int scaledParticleSize = (int) (3 * scale); // Tamaño de partícula escalado
            g2d.fillOval((int) particle.getX(), (int) particle.getY(), scaledParticleSize, scaledParticleSize);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (exploding) {
            explosionSize += 5;
            if (explosionSize > getWidth()) {
                exploding = false;
                timer.stop();
            }
            alpha -= 0.01f; // Reducir la transparencia durante la explosión
            if (alpha < 0) alpha = 0;

            scale += 0.05f; // Aumentar la escala durante la explosión

            // Mover las partículas
            Iterator<Particle> iterator = particles.iterator();
            while (iterator.hasNext()) {
                Particle particle = iterator.next();
                particle.move();
                if (particle.getX() < 0 || particle.getX() > getWidth() || particle.getY() < 0 || particle.getY() > getHeight()) {
                    iterator.remove(); // Eliminar partículas fuera del área visible
                }
            }
            repaint();
        }
    }

    public static void main(String[] args) {
        int numberNums=100;
        JFrame frame = new JFrame("Winner Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        frame.setSize(400, 400);
        WinnerAnimation animationPanel = new WinnerAnimation();
        frame.getContentPane().add(animationPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Iniciar el temporizador de cuenta regresiva
        animationPanel.startCountdown(numberNums);
    }
    
}

class Particle {
    private double x;
    private double y;
    private double speed;
    private double angle;

    public Particle(double x, double y, double speed, double angle) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.angle = angle;
    }

    public void move() {
        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
