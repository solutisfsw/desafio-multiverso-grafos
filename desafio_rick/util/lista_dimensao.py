from model.dimensao import Dimensao
from model.rota import Rota

class Multiverso:
    a = Dimensao()
    b = Dimensao()
    c = Dimensao()
    d = Dimensao()
    e = Dimensao()
    total = 5

    def ativar_universos(self):
        self.a.set_ativo(True)
        self.b.set_ativo(True)
        self.c.set_ativo(True)
        self.d.set_ativo(True)
        self.e.set_ativo(True)

    e.set_nome("E")
    e.set_rotas(
        rotas=[
            Rota(distancia=30, dimensao=b)
        ]
    )

    d.set_nome("D")
    d.set_rotas(
        rotas=[
            Rota(distancia=80, dimensao=e),
            Rota(distancia=40, dimensao=c)
        ]
    )

    c.set_nome("C")
    c.set_rotas(
        rotas=[
            Rota(distancia=40, dimensao=d),
            Rota(distancia=20, dimensao=e)
        ]
    )

    b.set_nome("B")
    b.set_rotas(
        rotas=[
            Rota(distancia=40, dimensao=c)
        ]
    )

    a.set_nome("A")
    a.set_rotas(
        rotas=[
            Rota(distancia=50, dimensao=b),
            Rota(distancia=70, dimensao=e),
            Rota(distancia=50, dimensao=d)
        ]
    )
