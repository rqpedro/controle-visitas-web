/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.certificacao.services;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rqped
 */
public class VisitaCalculoServiceTest {

    @Test
    public void diasDeAtrasoDeveRetornarDiasQuandoVisitaEstaAtrasada() {
        // Arrange — prepara as entradas conhecidas
        VisitaCalculoService service = new VisitaCalculoService();
        LocalDate dataVisita = LocalDate.of(2026, 1, 10);
        LocalDate hoje = LocalDate.of(2026, 1, 15);

        // Act — chama o método sob teste
        long resultado = service.diasDeAtraso(dataVisita, hoje);

        // Assert — verifica se o resultado é o esperado
        assertEquals(5, resultado);
    }

    @Test
    public void diasDeAtrasoDeveRetornarZeroQuandoVisitaEhHoje() {
        VisitaCalculoService service = new VisitaCalculoService();
        LocalDate hoje = LocalDate.of(2026, 1, 15);
        long resultado = service.diasDeAtraso(hoje, hoje);
        assertEquals(0, resultado);
    }

    @Test
    public void diasDeAtrasoDeveRetornarNegativoQuandoVisitaEhFutura() {
        VisitaCalculoService service = new VisitaCalculoService();
        LocalDate dataVisita = LocalDate.of(2026, 1, 20);
        LocalDate hoje = LocalDate.of(2026, 1, 15);
        long resultado = service.diasDeAtraso(dataVisita, hoje);
        assertEquals(-5, resultado);
    }

}
