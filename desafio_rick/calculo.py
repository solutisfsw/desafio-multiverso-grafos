"""

   O método <definir_rotas_universos> calcula as rotas nos universos, podendo definir:
   1. O universo de partida e o de chegada
       (Obs: Caso queira representar o retorno ao universo, apenas o universo de partida deve ser informado.);
   2. Um universo como prioridade de passagem;
   3. O número máximo de paradas;
   4. O número máximo de unidades medidas em espaço-tempo

   Atenção: É possível extrair a menor rota por este método, apenas capturando
            sua lista de retorno e demonstrando o menor valor. Porém existe
            um caminho menos custoso, que é definindo o parâmetro <menor_rota> como True

   """

from lista_dimensao import Multiverso


class Calculo:

    def __init__(self):
        self.distancias = []
        self.multiverso = Multiverso()

    def definir_rotas_universos(self,
            partida,
            num_paradas_max=None,
            num_unidades_max=None,
            passando=None,
            chegada=None,
            distancia=0,
            passou=False,
            num_paradas=0,
        ):

        if partida is chegada:
            if (passando is None or passou) \
                    and self._validar_paradas(num_paradas-1, num_paradas_max) \
                    and self._validar_unidades(distancia, num_unidades_max):
                self.distancias.append(distancia)
            return

        if chegada is not None:
            partida.set_ativo(False)
        else:
            chegada = partida

        partida.set_ativo(False)
        for rota in partida.rotas:
            self.definir_rotas_universos(
                partida=rota.dimensao,
                chegada=chegada,
                num_paradas_max=num_paradas_max,
                num_unidades_max=num_unidades_max,
                passando=passando,
                distancia=distancia + rota.distancia,
                passou=passou or rota.dimensao is passando,
                num_paradas=num_paradas + 1
            )

            if partida.get_ativo() is False:
                self.multiverso.ativar_universos()
                return



    def _validar_paradas(self, num_paradas, num_paradas_max):
        return num_paradas_max is None or num_paradas <= num_paradas_max

    def _validar_unidades(self, distancia, num_unidades_max):
        return num_unidades_max is None or distancia <= num_unidades_max