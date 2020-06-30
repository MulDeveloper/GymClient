/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.Modelos.MatriculasGym;


public interface ServiceMatriculas {
    public MatriculasGym altaMatricula(MatriculasGym m);
    public MatriculasGym get(int id);
}
