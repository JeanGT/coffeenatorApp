package com.br.ifpr.coffeenator.myclasses;

import com.br.ifpr.coffeenator.activitys.BibliotecaActivity;
import com.br.ifpr.coffeenator.activitys.CadastroActivity;
import com.br.ifpr.coffeenator.activitys.CasaActivity;
import com.br.ifpr.coffeenator.activitys.CreditosActivity;
import com.br.ifpr.coffeenator.activitys.IgrejaActivity;
import com.br.ifpr.coffeenator.activitys.InstrucoesActivity;
import com.br.ifpr.coffeenator.activitys.LojaActivity;
import com.br.ifpr.coffeenator.activitys.MainActivity;
import com.br.ifpr.coffeenator.activitys.Mapa01Activity;
import com.br.ifpr.coffeenator.activitys.Mapa02Activity;
import com.br.ifpr.coffeenator.activitys.Mapa03Activity;
import com.br.ifpr.coffeenator.activitys.MenuActivity;
import com.br.ifpr.coffeenator.activitys.OpcoesActivity;
import com.br.ifpr.coffeenator.activitys.StatusActivity;
import com.br.ifpr.coffeenator.activitys.TrabalhoActivity;

public class DicionarioDeActivitys {
    public static final int CADASTRO_ID = 0;
    public static final int CREDITOS_ID = 1;
    public static final int INSTRUCOES_ID = 2;
    public static final int MAIN_ID = 3;
    public static final int MAPA_01_ID = 4;
    public static final int MENU_ID = 5;
    public static final int OPCOES_ID = 6;
    public static final int STATUS_ID = 7;
    public static final int CASA_ID = 8;
    public static final int IGREJA_ID = 9;
    public static final int TRABALHO_ID = 10;
    public static final int LOJA_ID = 11;
    public static final int BIBLIOTECA_ID = 12;
    public static final int MAPA_02_ID = 13;
    public static final int MAPA_03_ID = 14;

    private DicionarioDeActivitys(){}

    public static Class<?> getClassById(int id) {
        switch (id) {
            case CADASTRO_ID:
                return CadastroActivity.class;
            case CREDITOS_ID:
                return CreditosActivity.class;
            case MAIN_ID:
                return MainActivity.class;
            case MAPA_01_ID:
                return Mapa01Activity.class;
            case INSTRUCOES_ID:
                return InstrucoesActivity.class;
            case OPCOES_ID:
                return OpcoesActivity.class;
            case STATUS_ID:
                return StatusActivity.class;
            case MENU_ID:
                return MenuActivity.class;
            case CASA_ID:
                return CasaActivity.class;
            case IGREJA_ID:
                return IgrejaActivity.class;
            case TRABALHO_ID:
                return TrabalhoActivity.class;
            case LOJA_ID:
                return LojaActivity.class;
            case BIBLIOTECA_ID:
                return BibliotecaActivity.class;
            case MAPA_02_ID:
                return Mapa02Activity.class;
            case MAPA_03_ID:
                return Mapa03Activity.class;
            default:
                return null;
        }
    }

    public static int getIdByClass(Class<?> classe) {
        if (classe.equals(CadastroActivity.class)) {
            return CADASTRO_ID;
        } else if (classe.equals(CreditosActivity.class)) {
            return CREDITOS_ID;
        } else if (classe.equals(MainActivity.class)) {
            return MAIN_ID;
        } else if (classe.equals(Mapa01Activity.class)) {
            return MAPA_01_ID;
        } else if (classe.equals(InstrucoesActivity.class)) {
            return INSTRUCOES_ID;
        } else if (classe.equals(OpcoesActivity.class)) {
            return OPCOES_ID;
        } else if (classe.equals(StatusActivity.class)) {
            return STATUS_ID;
        } else if(classe.equals(MenuActivity.class)) {
            return MENU_ID;
        } else if(classe.equals(CasaActivity.class)) {
            return CASA_ID;
        } else if(classe.equals(TrabalhoActivity.class)) {
            return TRABALHO_ID;
        } else if(classe.equals(LojaActivity.class)) {
            return LOJA_ID;
        } else if(classe.equals(IgrejaActivity.class)) {
            return IGREJA_ID;
        } else if(classe.equals(BibliotecaActivity.class)) {
            return BIBLIOTECA_ID;
        } else if(classe.equals(Mapa02Activity.class)) {
            return MAPA_02_ID;
        } else if(classe.equals(Mapa03Activity.class)) {
            return MAPA_03_ID;
        } else {
            return -1;
        }
    }
}
