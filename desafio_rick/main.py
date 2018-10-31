from lista_dimensao import Multverso

distancias = []
multverso = Multverso()
def definir_rotas(
        atual,
        final,
        passando,
        distancia=0,
        passou=False):

    if atual == final:
        if passou or passando==None:
            distancias.append(distancia)
        return


    atual.set_ativo(False)
    for rota in atual.rotas:
        definir_rotas(
            atual=rota.dimensao,
            final=final,
            passando=passando,
            distancia=distancia+rota.distancia,
            passou=passou or rota.dimensao == passando
        )

        if atual.get_ativo() is False:
            multverso.ativar_universos()
            return


"""A distância de A a C passando por B?"""
def distancia_A_a_C_passando_B():
    definir_rotas(atual=multverso.a, final=multverso.c, passando=multverso.b)

"""A distância entre A e D?"""
def distancia_A_a_D():
    definir_rotas(atual=multverso.a, final=multverso.d, passando=None)

"""A distância de A a C passando por D?"""
def distancia_A_a_C_passando_D():
    definir_rotas(atual=multverso.a, final=multverso.c, passando=multverso.d)


distancia_A_a_C_passando_B()
print("A distância de A a C passando por B:\n", distancias)
print("\n")

distancias=[]
distancia_A_a_D()
print("A distância entre A e D:\n", distancias)
print("\n")

distancias=[]
distancia_A_a_C_passando_D()
print("A distância de A a C passando por D:\n", distancias)