package maquina_snacks_files.service;

import maquina_snacks_files.domain.Snack;

import java.util.List;

public interface ServiceSnacksI {
    void addSnack(Snack snack);
    void showSnacks();
    List<Snack> getSnacks();

}
