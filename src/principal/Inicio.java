package principal;

public class Inicio {

   
    public static void main(String[] args) {
        //Segmentos de reta
        int AB = 50;
        int BC = 40;
        int AD = 50;
        int AE = 70;
        int EB = 30;
        int DE = 80;
        int CD = 40;
        int CE = 20;
        int DC = 40;        
        //Todos os cálculo desse trecho de código foram calculados usando a soma dos segmentos de reta 
        int R1_AC = AB+BC;
        int R2_AC = AE+EB+BC;        
        System.out.println("Distância de A para C passando apenas por B:"+R1_AC);
        System.out.println("Distância de A para C passando por B e E é:"+R2_AC+"\n");
       
        System.out.println("Distância entre A e D sem passar por nenhum outro ponto: "+AD);
        System.out.println("Distância entre A e D passando por B e C: "+(AB+BC+CD));
        System.out.println("Distância entre A e D passando por B, E e C: "+(AE+EB+BC+CD)+"\n");
        //
        
        //Calaculo de menor distância
        int R1_B = BC+CD+DE+EB;
        String texto = "A menor distância saindo de B e voltando a B é o segmento BCDE de tamanho:";
        int menor = R1_B;
        int R2_B = BC+CE+EB;
        int R3_B = BC+CD+DC+CE+EB;
        if(R2_B<menor){
           menor = R2_B;
           texto = "A menor distância saindo de B e voltando a B é o segmento BCE de tamanho:";
        }else if(R3_B<menor){
                  menor = R3_B;
                  texto = "A menor distância saindo de B e voltando a B é o segmento BCDE de tamanho:";
              }
        
        System.out.println(texto+" "+menor);               
        System.out.println(R1_AC<R2_AC?"A menor rota entre A e C é o segmento ABC = "+R1_AC:"A menor rota entre AC é o segmento AEBC = "+R2_AC+"\n");               
        
        //Número de rotas
        System.out.print("\nNúmero de rotas saindo de C e voltando para C é com no máximo 3 paradas é 7, pois existem duas bifurcações, uma com 3 e outra com 4 setas\n");
        System.out.print("Número de rotas entre A e C com no máximo 4 paradas é 7, pois:");
        System.out.print("Passando de A para D e indo para C(usa-se duas setas)");
        System.out.print("Passando de A para B e indo para C(usa-se duas setas)");
        System.out.print("Passando por  A,E,B e indo para C(usa-se 3 setas)");
        System.out.print("O número de diferentes rotas saindo de C e voltando a C com distância máxima de 300 unidades de espaço-tempo é 9:");
        System.out.print("Passando por D e voltando para C(duas setas)");
        System.out.print("Passando por D,E,B e voltando para C(quatro setas)");
        System.out.print("Passando por E,B e voltando para C(três setas)");
    }   
}
