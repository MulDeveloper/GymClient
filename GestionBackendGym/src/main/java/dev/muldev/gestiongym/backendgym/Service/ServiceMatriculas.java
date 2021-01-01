/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.Modelos.MembershipEntity;


public interface ServiceMatriculas {
    public MembershipEntity altaMatricula(MembershipEntity m);
    public MembershipEntity get(int id);
}
