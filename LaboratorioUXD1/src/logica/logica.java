/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package logica;

import GUI.*;
import java.util.LinkedList;

/**
 *
 * @author jairo
 */
public class logica {

    public static void main(String[] args) {
        Banco banco1 = new Banco();
        banco1.setVisible(true);
        for (int i = 0; i < 4; i++) {
            CRUDBanco.cajas.add(new LinkedList<>());
        }

    }
}
