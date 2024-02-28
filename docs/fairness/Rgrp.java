// Cálculo do Rgrp referente ao exemplo

public class Rgrp {
    public static void main(String[] args) {

        final int linha = 4;
        final int coluna = 5;
        final int usuarios = 5;
        final int homens = 3;
        final int mulheres = 2;
        final int grupos = 2;

        double[][] xoriginal = new double[linha][coluna];
        double[][] xestimada = new double[linha][coluna];

        int[] celulasConhecidas = new int[usuarios];
        int[] celulasNaoConhecidas = new int[usuarios];

        double calculandoli[] = new double[usuarios];

        double li[] = new double[usuarios];
        double rindv = 0;

        double totaLiH = 0;
        double totaLiM = 0;
        double mediaLi = 0;
        double Li[] = new double[grupos];
        double rgrp = 0;

        //MATRIZ DE AVALIAÇÃO DAS RECOMENDAÇÕES
        xoriginal[0][0] = 2;
        xoriginal[0][1] = 0;
        xoriginal[0][2] = 5;
        xoriginal[0][3] = 0;
        xoriginal[0][4] = 3;
        xoriginal[1][0] = 0;
        xoriginal[1][1] = 4;
        xoriginal[1][2] = 0;
        xoriginal[1][3] = 2;
        xoriginal[1][4] = 3;
        xoriginal[2][0] = 1;
        xoriginal[2][1] = 0;
        xoriginal[2][2] = 1;
        xoriginal[2][3] = 3;
        xoriginal[2][4] = 0;
        xoriginal[3][0] = 0;
        xoriginal[3][1] = 1;
        xoriginal[3][2] = 1;
        xoriginal[3][3] = 0;
        xoriginal[3][4] = 3;

        //MATRIZ ESTIMADA ESTÁTICA
        xestimada[0][0] = 4;
        xestimada[0][1] = 4;
        xestimada[0][2] = 4;
        xestimada[0][3] = 4;
        xestimada[0][4] = 4;
        xestimada[1][0] = 5;
        xestimada[1][1] = 5;
        xestimada[1][2] = 4;
        xestimada[1][3] = 4.1;
        xestimada[1][4] = 3.55;
        xestimada[2][0] = 1.4;
        xestimada[2][1] = 2;
        xestimada[2][2] = 1.3;
        xestimada[2][3] = 5;
        xestimada[2][4] = 5;
        xestimada[3][0] = 3;
        xestimada[3][1] = 2.66;
        xestimada[3][2] = 1.7;
        xestimada[3][3] = 5;
        xestimada[3][4] = 4.1;

        //PERCORRENDO A MATRIZ ORIGINAL E IMPRIMINDO
        System.out.println("    Matriz Original:");
        for (int lo = 0; lo < xoriginal.length; lo++) {
            for (int co = 0; co <= xoriginal.length; co++) {
                System.out.printf("| %f |", xoriginal[lo][co]);
            }
            System.out.printf("%n");
        }

        //PERCORRENDO A MATRIZ ESTIMADA E IMPRIMINDO
        System.out.println("\n   Matriz Estimada:");
        for (int le = 0; le < xestimada.length; le++) {
            for (int ce = 0; ce <= xestimada.length; ce++) {
                System.out.printf("| %f |", xestimada[le][ce]);
            }
            System.out.printf("%n");

        }

        // SOMANDO O TOTAL DAS CÉLULAS DE AVALIAÇÕES CONHECIDAS DA XORIGINAL VS XESTIMADA DE UM USUÁRIO
        int u = 0;
        while (u < 5) {
            for (int lo = 0; lo < xoriginal.length; lo++) {
                if (xoriginal[lo][u] != 0) {
                    celulasConhecidas[u]++;
                } else {
                    celulasNaoConhecidas[u]++;
                }
            }
            u++;
        }

        //IMPRIMINDO AS QUANTIDADE DE CELULAS DE AVALIAÇÕES CONHECIDAS/ NAO CONHECIDAS
        for (int i = 0; i < usuarios; i++) {
            System.out.println("\nUsuário " + i + ": ");
            System.out.printf("Total células não avaliadas: %d", celulasNaoConhecidas[i]);
            System.out.printf("\nTotal células avaliadas: %d\n", celulasConhecidas[i]);
        }

        //CALCULANDO LI ( U = USUARIO DA POSIÇÃO ZERO (0))
        u = 0;
        while (u < 5) {
            //COMPRANDO OS ERROS DA MATRIZE ESTIMADAS - A MATRIZ ORIGINAL
            for (int lo = 0; lo < xoriginal.length; lo++) {
                //int u = 0;
                if (xoriginal[lo][u] != 0) {
                    calculandoli[u] = calculandoli[u] + (Math.pow(xestimada[lo][u] - xoriginal[lo][u], 2));
                }
            }
            u++;
        }

        //ENCONTRANDO O li DA JUSTIÇA INDIVIDUAL DE CADA USUÁRIO E IMPRIMINDO
        for (int i = 0; i < usuarios; i++) {
            li[i] = calculandoli[i] / celulasConhecidas[i];
            System.out.printf("\nPerda do usuário %d (li): %.2f ", i, li[i]);
        }

        //ENCONTRANDO O TOTAL DO Li DOS HOMENS
        for (int i = 0; i < homens; i++) {
            totaLiH = totaLiH + li[i];
        }
        System.out.printf("\n\nTotal perda (Li) do grupo dos homens: %.2f", totaLiH);

        //ENCONTRANDO O TOTAL DO Li DAS MULHERES
        for (int i = homens; i < usuarios; i++) {
            totaLiM = totaLiM + li[i];
        }
        System.out.printf("\n\nTotal perda (Li) do grupo das mulheres: %.2f", totaLiM);

        //ENCONTRANDO A MÉDIA DO Li DOS HOMENS
        Li[0] = Li[0] + (totaLiH / (usuarios - mulheres));
        //ENCONTRANDO A MÉDIA DO Li DAS MULHERES
        Li[1] = Li[1] + (totaLiM / (usuarios - homens));
        //ENCONTRANDO A MÉDIA DO Li DOS GRUPOS
        mediaLi = (Li[0] + Li[1]) / grupos;

        //IMPRIMINDO O Li dos homens e das mulheres
        System.out.printf("\n\nLi dos homens: %.2f", Li[0]);
        System.out.printf("\nLi das mulheres: %.2f", Li[1]);

        //ENCONTRANDO O RGRP - JUSTIÇA DO GRUPO (VARIANCIA POPULACIONAL DE TODOS OS Li's)
        for (int i = 0; i < grupos; i++) {
            rgrp = rgrp + (Math.pow(Li[i] - mediaLi, 2));
        }
        rgrp = rgrp / grupos;
        System.out.printf("\nJUSTIÇA DO GRUPO - Rgrp: %.4f", rgrp);

    }
}

