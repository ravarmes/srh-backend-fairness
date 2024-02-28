// Cálculo do Rpol referente ao exemplo

public class Rpol {
    public static void main(String[] args) {
        final int linha = 4;
        final int coluna = 5;
        double[][] xoriginal = new double[linha][coluna];
        double[][] xestimada = new double[linha][coluna];


        double totalSoma[] = new double[4];
        double totalMedia[] = new double[4];
        double totalVariancia[] = new double[4];
        double rpol = 0;


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

        //CALCULANDO O TOTAL DE CADA LINHA
        int i = 0;
        for (int le = 0; le < xestimada.length; le++) {
            for (int ce = 0; ce <= xestimada.length; ce++) {
                totalSoma[i] = totalSoma[i] + xestimada[le][ce];
            }
            i++;
        }


        for (i = 0; i < 4; i++) {
            //CALCULANDO AS MÉDIAS
            totalMedia[i] = 0 + (totalSoma[i] / coluna);
        }


        //APLICANDO A FÓRMULA DA VARIÂNCIA DE CADA LINHA
        i = 0;
        for (int le = 0; le < xestimada.length; le++) {
            for (int ce = 0; ce <= xestimada.length; ce++) {
                totalVariancia[i] = totalVariancia[i] + (Math.pow(xestimada[le][ce] - totalMedia[i], 2));

            }
            i++;
            totalVariancia[le] = totalVariancia[le] / coluna;
        }


        //IMPRIMINDO VALORES
        for (i = 0; i < 4; i++) {
            System.out.println("\n\nTotal soma do item" + i + ": " + totalSoma[i]);
            System.out.println("Total média do item" + i + ": " + totalMedia[i]);
            System.out.printf("Total variância do item%d: %6f", i, totalVariancia[i]);
        }


        // CALCULANDO O RPOL
        for (i = 0; i < 4; i++) {
            rpol = rpol + totalVariancia[i];
        }
        rpol = rpol / totalMedia.length;

        System.out.printf("\n\nPOLARIZAÇÃO - Rpol: %.2f", rpol);
    }
}




