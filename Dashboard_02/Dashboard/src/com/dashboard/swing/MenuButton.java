package com.dashboard.swing;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MenuButton extends JButton {

    // Índice usado para identificar si es un ítem principal o un submenú
    private int index;

    // Animador encargado del efecto ripple (onda circular)
    private Animator animator;

    // Tamaño máximo que tendrá la onda al expandirse
    private int targetSize;

    // Tamaño actual de la onda durante la animación
    private float animatSize;

    // Punto exacto donde el usuario hizo clic
    private Point pressedPoint;

    // Nivel de transparencia del efecto ripple
    private float alpha;

    // Color del efecto ripple (blanco semi-transparente)
    private Color effectColor = new Color(255, 255, 255, 150);

    // --- GETTERS Y SETTERS ---
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    // Constructor para botones con icono (menú principal)
    public MenuButton(Icon icon, String text) {
        super(text);              // Asigna el texto al JButton padre
        setIcon(icon);            // Establece el icono a la izquierda
        init();                   // Configura estilo y animación
        setBorder(new EmptyBorder(1, 20, 1, 1));   // Espacio para icono + texto
    }

    // Constructor para botones de submenú (solo texto)
    public MenuButton(String text) {
        super(text);
        init();
        setBorder(new EmptyBorder(1, 50, 1, 1));   // Indentación mayor → submenú
    }

    // Constructor alternativo (útil si se quiere detectar submenús)
    public MenuButton(String text, boolean subMenu) {
        super(text);
        init();
    }


    // --- CONFIGURACIÓN INICIAL (común a todos los botones) ---
    private void init() {

        // Elimina el fondo estándar del botón (más limpio y moderno)
        setContentAreaFilled(false);

        // Color del texto del menú (modificable)
        setForeground(new Color(114, 138, 147));

        // Alinea el texto y el icono hacia la izquierda
        setHorizontalAlignment(JButton.LEFT);

        // Listener para detectar clics y disparar la animación
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {

                // Tamaño final del círculo (efecto ripple)
                targetSize = Math.max(getWidth(), getHeight()) * 2;

                // Comienza pequeño
                animatSize = 0;

                // Guarda el punto donde se hizo clic
                pressedPoint = me.getPoint();

                // Inicia la transparencia del efecto
                alpha = 0.5f;

                // Si la animación sigue corriendo, la reinicia
                if (animator.isRunning()) {
                    animator.stop();
                }

                // Inicia animación
                animator.start();
            }
        });

        // Define la lógica del efecto ripple durante la animación
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {

                // A la mitad de la animación la onda empieza a desvanecerse
                if (fraction > 0.5f) {
                    alpha = 1 - fraction;
                }

                // La onda crece proporcionalmente al progreso
                animatSize = fraction * targetSize;

                // Redibuja para mostrar la animación
                repaint();
            }
        };

        // Configuración del animador (duración y suavidad)
        animator = new Animator(400, target);
        animator.setResolution(0);   // Animación más fluida
    }


    // --- DIBUJADO DEL EFECTO VISUAL ---
    @Override
    protected void paintComponent(Graphics grphcs) {

        Graphics2D g2 = (Graphics2D) grphcs;

        // Anti-Aliasing para bordes suaves
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Si hubo clic, dibuja la onda expansiva (ripple)
        if (pressedPoint != null) {

            // Color del efecto
            g2.setColor(effectColor);

            // Aplica transparencia variable
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

            // Dibuja el óvalo (onda circular)
            g2.fillOval(
                    (int) (pressedPoint.x - animatSize / 2),
                    (int) (pressedPoint.y - animatSize / 2),
                    (int) animatSize,
                    (int) animatSize
            );
        }

        // Restablece la opacidad
        g2.setComposite(AlphaComposite.SrcOver);

        // Dibuja el texto y el icono del botón
        super.paintComponent(grphcs);
    }

}
