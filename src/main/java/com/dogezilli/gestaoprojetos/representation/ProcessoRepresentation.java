package com.dogezilli.gestaoprojetos.representation;

import com.dogezilli.gestaoprojetos.model.Processo;

public class ProcessoRepresentation {

    public static Processo build(Processo processo) {
        return new Processo.Builder()
                .pareceres(processo.getPareceres())
                .build();
    }
}
