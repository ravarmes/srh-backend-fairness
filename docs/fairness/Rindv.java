// Cálculo do Rindv referente ao exemplo

public class Rindv {
    public static void main(String[] args) {

        final int linha = 4;
        final int coluna = 5;
        final int usuarios = 5;

        double[][] xoriginal = new double[linha][coluna];
        double[][] xestimada = new double[linha][coluna];

        int[] celulasConhecidas = new int[usuarios];
        int[] celulasNaoConhecidas = new int[usuarios];


        double calculandoli[] = new double[usuarios];

        double li[] = new double[usuarios];
        double totali = 0;
        double mediali = 0;
        double rindv = 0;


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

        //ENCONTRANDO O TOTAL DO li
        for (int i = 0; i < usuarios; i++) {
            totali = totali + li[i];
        }
        System.out.println("\n\nTotal perda (li) dos usuários: " + totali);

        //ENCONTRANDO A MÉDIA DO li
        mediali = mediali + (totali / li.length);
        System.out.println("Média da li: " + mediali);

        //ENCONTRANDO O RINDV - JUSTIÇA INDIVIDUAL (VARIANCIA POPULACIONAL DE TODOS OS li's)
        for (int i = 0; i < usuarios; i++) {
            rindv = rindv + (Math.pow(li[i] - mediali, 2));
        }
        rindv = rindv / usuarios;

        System.out.printf("\nJUSTIÇA INDIVIDUAL - Rindv: %.4f", rindv);
    }
}







