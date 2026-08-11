/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.certificacao.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author rqped
 */
public class VisitaCalculoService {

    public long diasDeAtraso(LocalDate dataVisita, LocalDate hoje) {
        return ChronoUnit.DAYS.between(dataVisita, hoje);
    }

}
