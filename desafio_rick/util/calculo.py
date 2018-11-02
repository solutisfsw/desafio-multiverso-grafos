"""

   O método <definir_rotas_universos> calcula as rotas nos universos, podendo definir:
   1. O universo de partida e o de chegada
       (Obs: Caso queira representar o retorno ao universo, apenas o universo de partida deve ser informado.);
   2. Um universo como prioridade de passagem;
   3. O número máximo de paradas;
   4. O número máximo de unidades medidas em espaço-tempo

   * Logo após é possível capturar as rotas através do método <get_distancias>

   Atenção: É possível extrair a menor rota por este método, apenas capturando
            sua lista de retorno e demonstrando o menor valor. Porém existe
            um caminho menos custoso, que é definindo o parâmetro <menor_rota> como True e capturando o
            valor pelo método <get_valor_menor_rota>.

"""
from util.lista_dimensao import Multiverso

class Calculo:

    def __init__(self):
        self._distancias = []
        self._multiverso = Multiverso()
        self._valor_menor_rota = 0

    def definir_rotas_universos(self,
            partida,
            num_paradas_max=None,
            num_unidades_max=None,
            passando=None,
            chegada=None,
            distancia=0,
            passou=False,
            num_paradas=0,
            menor_rota=False,
            cont=0
        ):
        if cont == Multiverso.total:
            return
        cont += 1

        if self._validar_menor_rota(menor_rota, distancia):
            return

        if partida is chegada:
            cont=0
            if (passando is None or passou) \
                    and self._validar_paradas(num_paradas-1, num_paradas_max) \
                    and self._validar_unidades(distancia, num_unidades_max):
                self._atribuir_valor(menor_rota, distancia)
            return

        if chegada is not None:
            partida.set_ativo(False)
        else:
            chegada = partida

        for rota in partida.get_rotas():
            self.definir_rotas_universos(
                partida=rota.get_dimensao(),
                chegada=chegada,
                num_paradas_max=num_paradas_max,
                num_unidades_max=num_unidades_max,
                passando=passando,
                distancia=distancia + rota.get_distancia(),
                passou=passou or rota.get_dimensao() is passando,
                num_paradas=num_paradas + 1,
                menor_rota=menor_rota,
                cont=cont
            )

            if partida.get_ativo() is False:
                self._multiverso.ativar_universos()
                return

    def _validar_paradas(self, num_paradas, num_paradas_max):
        return num_paradas_max is None or num_paradas <= num_paradas_max

    def _validar_unidades(self, distancia, num_unidades_max):
        return num_unidades_max is None or distancia <= num_unidades_max

    def _atribuir_valor(self, menor_rota, distancia):
        if menor_rota:
            if self._valor_menor_rota is 0 or distancia < self._valor_menor_rota:
                self._valor_menor_rota = distancia
        else:
            self._distancias.append(distancia)

    def _validar_menor_rota(self, menor_rota, distancia):
        return menor_rota and self._valor_menor_rota is not 0 and distancia > self._valor_menor_rota

    def get_distancias(self):
        return self._distancias

    def get_valor_menor_rota(self):
        return self._valor_menor_rota