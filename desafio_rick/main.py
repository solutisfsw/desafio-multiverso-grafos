from util.calculo import Calculo
from util.lista_dimensao import Multiverso

"""A distância de A a C passando por B?"""
def distancia_A_a_C_passando_B(calculo):
    try:
        calculo.definir_rotas_universos(partida=Multiverso.a, chegada=Multiverso.c, passando=Multiverso.b)
    except RecursionError:
        print("Inpossível chegar ao universo")

"""A distância entre A e D?"""
def distancia_A_a_D(calculo):
    try:
        calculo.definir_rotas_universos(partida=Multiverso.a, chegada=Multiverso.d)
    except RecursionError:
        print("Inpossível chegar ao universo")

"""A distância de A a C passando por D?"""
def distancia_A_a_C_passando_D(calculo):
    try:
        calculo.definir_rotas_universos(partida=Multiverso.a, chegada=Multiverso.c, passando=Multiverso.d)
    except RecursionError:
        print("Inpossível chegar ao universo")

"""O número de rotas saindo de C e voltando a C com no máximo 3 paradas?"""
def distancia_C_a_C_paradas_3(calculo):
    try:
        calculo.definir_rotas_universos(partida=Multiverso.c, num_paradas_max=3)
    except RecursionError:
        print("Inpossível chegar ao universo")

"""O número de rotas entre A e C com no máximo 4 paradas?"""
def distancia_A_a_C_paradas_4(calculo):
    try:
        calculo.definir_rotas_universos(partida=Multiverso.a, chegada=Multiverso.c, num_paradas_max=4)
    except RecursionError:
        print("Inpossível chegar ao universo")

"""A menor rota (em espaço-tempo) entre A e C?"""
def distancia_A_a_C_menor_rota(calculo):
    try:
        calculo.definir_rotas_universos(partida=Multiverso.a, chegada=Multiverso.c, menor_rota=True)
    except RecursionError:
        print("Inpossível chegar ao universo")

"""A menor rota (em espaço-tempo) saindo de B e voltando a B?"""
def distancia_B_a_B_menor_rota(calculo):
    try:
        calculo.definir_rotas_universos(partida=Multiverso.b, menor_rota=True)
    except RecursionError:
        print("Inpossível chegar ao universo")

"""O número de diferentes rotas saindo de C e voltando a C com distância máxima de 300 unidades de espaço-tempo?"""
def distancia_C_a_C_unidades_300(calculo):
    try:
        calculo.definir_rotas_universos(partida=Multiverso.c, num_unidades_max=300)
    except RecursionError:
        print("Inpossível chegar ao universo")


calculo=Calculo()
distancia_A_a_C_passando_B(calculo)
print("A distância de A a C passando por B:\n", calculo.get_distancias())
print("\n")

calculo=Calculo()
distancia_A_a_D(calculo)
print("A distância entre A e D:\n", calculo.get_distancias())
print("\n")

calculo=Calculo()
distancia_A_a_C_passando_D(calculo)
print("A distância de A a C passando por D:\n", calculo.get_distancias())
print("\n")

calculo=Calculo()
distancia_C_a_C_paradas_3(calculo)
print("O número de rotas de C e voltando a C com no máximo 3 paradas:\n", calculo.get_distancias().__len__())
print("\n")

calculo=Calculo()
distancia_A_a_C_paradas_4(calculo)
print("O número de rotas de A a C com no máximo 4 paradas:\n", calculo.get_distancias().__len__())
print("\n")

calculo=Calculo()
distancia_A_a_C_menor_rota(calculo)
print("A menor rota de A a C:\n", calculo.get_valor_menor_rota())
print("\n")

calculo=Calculo()
distancia_B_a_B_menor_rota(calculo)
print("A menor rota de B e voltando a B:\n", calculo.get_valor_menor_rota())
print("\n")

calculo=Calculo()
distancia_C_a_C_unidades_300(calculo)
print("O número de rotas de C e voltando a C com no máximo 300 unidades:\n", calculo.get_distancias().__len__())
